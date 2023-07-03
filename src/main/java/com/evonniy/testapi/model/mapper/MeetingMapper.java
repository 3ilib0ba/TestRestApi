package com.evonniy.testapi.model.mapper;

import com.evonniy.testapi.model.dto.MeetingInfoDto;
import com.evonniy.testapi.model.dto.OnlyMeetingDto;
import com.evonniy.testapi.model.dto.OnlyUserInMeetingDto;
import com.evonniy.testapi.model.dto.UserInMeetingDto;
import com.evonniy.testapi.model.entity.Meeting;
import com.evonniy.testapi.model.entity.UserInMeeting;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingMapper {
    private final UserMapper userMapper;

    public MeetingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserInMeetingDto toUserInMeetingDto(UserInMeeting userInMeeting) {
        return new UserInMeetingDto(
                toOnlyMeetingDto(userInMeeting.getMeeting()),
                userMapper.toDto(userInMeeting.getUser()),
                userInMeeting.getFullname(),
                userInMeeting.getAge(),
                userInMeeting.getPcr()
        );
    }

    public MeetingInfoDto toDto(Meeting meeting) {
        return new MeetingInfoDto(
                meeting.getId(),
                meeting.getOrganizator().getUsername(),
                meeting.getName(),
                meeting.getPrice(),
                meeting.getDateOf(),
                meeting.getParticipants().stream()
                        .map(this::toOnlyUserInMeetingDto)
                        .collect(Collectors.toList())
        );
    }

    public List<MeetingInfoDto> toDtoFromList(List<Meeting> meetings) {
        return meetings.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OnlyUserInMeetingDto toOnlyUserInMeetingDto(UserInMeeting userInMeeting) {
        return new OnlyUserInMeetingDto(
                userMapper.toDto(userInMeeting.getUser()),
                userInMeeting.getFullname(),
                userInMeeting.getAge(),
                userInMeeting.getPcr()
        );
    }

    public OnlyMeetingDto toOnlyMeetingDto(Meeting meeting) {
        return new OnlyMeetingDto(
                meeting.getId(),
                userMapper.toDto(meeting.getOrganizator()),
                meeting.getName(),
                meeting.getPrice(),
                meeting.getDateOf()
        );
    }
}
