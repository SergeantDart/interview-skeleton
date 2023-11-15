package com.example.interviewskeleton.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TimeZone {

    private String countryCode;

    private String timeZone;
}
