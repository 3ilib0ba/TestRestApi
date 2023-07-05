package com.evonniy.testapi.controller;

import com.evonniy.testapi.model.dto.CreateMeetingDto;
import com.evonniy.testapi.model.dto.SignUpForMeetingDto;
import com.evonniy.testapi.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping(value = "/meetings/get_all")
    public ResponseEntity<?> getAllMeetings() {
        return ResponseEntity.ok(meetingService.getAll());
    }

    @PostMapping(value = "/meetings/create")
    public ResponseEntity<?> createMeeting(
            @Valid @RequestBody CreateMeetingDto dateOfMeeting
    ) {
        return ResponseEntity.ok(meetingService.createMeetingAndReturnDto(dateOfMeeting));
    }

    @PostMapping(value = "/meetings/signUpForMeeting")
    public ResponseEntity<?> signUpForMeeting(
            @Valid @RequestBody SignUpForMeetingDto signUpForMeetingDto
    ) {
        return ResponseEntity.ok(meetingService.signUpForMeetingAndReturnDto(signUpForMeetingDto));
    }

}
