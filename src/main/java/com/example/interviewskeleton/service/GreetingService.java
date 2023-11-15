package com.example.interviewskeleton.service;

import com.example.interviewskeleton.dto.TimeZone;
import com.example.interviewskeleton.util.AppUtils;
import jakarta.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private static Map<String, Map<String, String>> greetings;

    private static List<TimeZone> timeZoneList;

    @PostConstruct
    public void postConstruct() {
        //It is necessary in order to map the data structure we need from the .yaml properties file.
        YamlMapFactoryBean factory = new YamlMapFactoryBean();
        factory.setResources(new ClassPathResource("greetings.yaml"));
        Map<String, Object> map = factory.getObject();
        greetings = map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> (Map<String, String>) e.getValue())
                );
        timeZoneList = AppUtils.fetchTimeZoneData();
    }

    public String greet(String name, String locale) {
        if (!checkIfLocaleExistsInProperties(locale)) {
            throw new RuntimeException("Sorry, this locale is not available in the properties file.");
        }
        ZonedDateTime localTime = computeLocalDateTime(locale);
        int localTimeHour = localTime.getHour();
        String localMessage =
            switch (localTimeHour) {
                case 23, 0, 1, 2, 3, 4 -> greetings.get("night").get(locale).toString();
                case 5, 6, 7, 8, 9, 10 -> greetings.get("morning").get(locale).toString();
                case 11, 12, 13, 14, 15, 16 -> greetings.get("afternoon").get(locale).toString();
                case 17, 18, 19, 20, 21, 22 -> greetings.get("evening").get(locale).toString();
                default -> throw new RuntimeException("Sorry, unknown hour.");
            };
        return AppUtils.replaceText(localMessage, name);
    }

    private ZonedDateTime computeLocalDateTime(String locale) {
        Optional<TimeZone> timeZone = timeZoneList.stream()
                .filter(t -> t.getCountryCode().equalsIgnoreCase(locale))
                .findAny();
        ZonedDateTime localTime;
        if (timeZone.isPresent()) {
            localTime = ZonedDateTime.now(
                    ZoneId.of(timeZone.get().getTimeZone())
            );
        } else {
            throw new RuntimeException("Sorry, we haven't found a matching between provided country code and time zone.");
        }
        return localTime;
    }

    private boolean checkIfLocaleExistsInProperties(String locale) {
        // We check if the locale provided in the request exists for all the greetings in the properties file.
        Long count = greetings.entrySet()
                .stream()
                .map(e -> e.getValue().entrySet()
                        .stream()
                        .map(f -> f.getKey())
                        .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(g -> g.equalsIgnoreCase(locale))
                .count();
        if (count == greetings.size()) {
            return true;
        }
        return false;
    }
}
