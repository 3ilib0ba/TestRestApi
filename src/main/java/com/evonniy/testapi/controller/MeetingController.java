package com.evonniy.testapi.controller;

import com.evonniy.testapi.model.dto.CreateMeetingDto;
import com.evonniy.testapi.model.dto.SignUpForMeetingDto;
import com.evonniy.testapi.model.entity.Meeting;
import com.evonniy.testapi.model.entity.UserInMeeting;
import com.evonniy.testapi.model.mapper.MeetingMapper;
import com.evonniy.testapi.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MeetingController {
    private final MeetingMapper meetingMapper;
    private final MeetingService meetingService;

    public MeetingController(
            MeetingMapper meetingMapper,
            MeetingService meetingService
    ) {
        this.meetingMapper = meetingMapper;
        this.meetingService = meetingService;
    }

    @GetMapping(value = "/meetings/get_all")
    public ResponseEntity<?> getAllMeetings() {
        List<Meeting> meetings = meetingService.getAll();
        return ResponseEntity.ok(meetingMapper.toDtoFromList(meetings));
    }

    @PostMapping(value = "/meetings/create")
    public ResponseEntity<?> createMeeting(
            @Valid @RequestBody CreateMeetingDto dateOfMeeting
    ) {
        Meeting result = meetingService.createMeeting(dateOfMeeting);
        return ResponseEntity.ok(meetingMapper.toDto(result));
    }

    @PostMapping(value = "/meetings/signUpForMeeting")
    public ResponseEntity<?> signUpForMeeting(
            @Valid @RequestBody SignUpForMeetingDto signUpForMeetingDto
    ) {
        UserInMeeting addedParticipant = meetingService.signUpForMeeting(signUpForMeetingDto);
        return ResponseEntity.ok(meetingMapper.toUserInMeetingDto(addedParticipant));
    }

}
