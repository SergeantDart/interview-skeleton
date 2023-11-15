package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.dto.FirstProblemRequest;
import com.example.interviewskeleton.service.FirstProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class FirstProblemController {

    private final FirstProblemService firstProblemService;

    @PostMapping("/first/words/save")
    public ResponseEntity<Void> saveWords(@RequestBody FirstProblemRequest firstProblemRequest) {
        firstProblemService.saveWords(firstProblemRequest.getWords());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
