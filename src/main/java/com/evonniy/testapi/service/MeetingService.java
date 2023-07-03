package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.MeetMustBeInFutureException;
import com.evonniy.testapi.exception.exceptions.MeetingNotFoundException;
import com.evonniy.testapi.exception.exceptions.YouAreAlreadyAtSignedUpForMeetingException;
import com.evonniy.testapi.model.dto.CreateMeetingDto;
import com.evonniy.testapi.model.dto.MeetingInfoDto;
import com.evonniy.testapi.model.dto.SignUpForMeetingDto;
import com.evonniy.testapi.model.dto.UserInMeetingDto;
import com.evonniy.testapi.model.entity.Meeting;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.model.entity.UserInMeeting;
import com.evonniy.testapi.model.mapper.MeetingMapper;
import com.evonniy.testapi.repository.MeetingRepository;
import com.evonniy.testapi.repository.UserInMeetingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserInMeetingRepository userInMeetingRepository;
    private final UserService userService;
    private final MeetingMapper meetingMapper;

    public MeetingService(
            MeetingRepository meetingRepository,
            UserInMeetingRepository userInMeetingRepository,
            UserService userService,
            MeetingMapper meetingMapper
    ) {
        this.meetingRepository = meetingRepository;
        this.userInMeetingRepository = userInMeetingRepository;
        this.userService = userService;
        this.meetingMapper = meetingMapper;
    }

    public List<MeetingInfoDto> getAll() {
        return meetingMapper.toDtoFromList(meetingRepository.findAll());
    }

    public MeetingInfoDto createMeet(CreateMeetingDto createMeetingDto) {
        Date dateOf = createMeetingDto.getDate();
        String name = createMeetingDto.getName();
        long price = createMeetingDto.getPrice();
        if (!checkDateForFuture(dateOf)) {
            throw new MeetMustBeInFutureException();
        }
        User organizator = userService.checkForOrganizatorAndGetIt();

        Meeting meeting = new Meeting(0, organizator, name, price, dateOf, List.of());
        return meetingMapper.toDto(meetingRepository.save(meeting));
    }

    private boolean checkDateForFuture(Date date) {
        Date now = new Date();
        return now.before(date);
    }

    public UserInMeetingDto signUpForMeeting(SignUpForMeetingDto signUpForMeetingDto) {
        User userParticipant = userService.checkForUserOrOrganizatorAndGetIt();
        Meeting meeting = getMeetingById(signUpForMeetingDto.getMeetingId());

        if (isUserInMeeting(meeting, userParticipant)) {
            throw new YouAreAlreadyAtSignedUpForMeetingException();
        }

        UserInMeeting participant = new UserInMeeting(
                0,
                meeting,
                userParticipant,
                signUpForMeetingDto.getFullName(),
                signUpForMeetingDto.getAge(),
                signUpForMeetingDto.getPcr()
        );

        meeting.getParticipants().add(participant);
        return meetingMapper.toUserInMeetingDto(userInMeetingRepository.save(participant));
    }

    private Meeting getMeetingById(Long meetingId) {
        Optional<Meeting> isMeeting = meetingRepository.findById(meetingId);
        if (isMeeting.isEmpty()) {
            throw new MeetingNotFoundException();
        }
        return isMeeting.get();
    }

    private boolean isUserInMeeting(Meeting meeting, User user) {
        List<User> userParticipants = meeting.getParticipants().stream()
                .map(UserInMeeting::getUser)
                .toList();
        return userParticipants.stream().anyMatch(us -> us.getId() == user.getId());
    }

}
