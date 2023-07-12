package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.DocumentIsNotSignedYetException;
import com.evonniy.testapi.exception.exceptions.MeetMustBeInFutureException;
import com.evonniy.testapi.exception.exceptions.MeetingNotFoundException;
import com.evonniy.testapi.exception.exceptions.YouAreAlreadyAtSignedUpForMeetingException;
import com.evonniy.testapi.model.dto.CreateMeetingDto;
import com.evonniy.testapi.model.dto.MeetingInfoDto;
import com.evonniy.testapi.model.dto.SignUpForMeetingDto;
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
    private final DocumentService documentService;

    public MeetingService(
            MeetingRepository meetingRepository,
            UserInMeetingRepository userInMeetingRepository,
            UserService userService,
            DocumentService documentService
    ) {
        this.meetingRepository = meetingRepository;
        this.userInMeetingRepository = userInMeetingRepository;
        this.userService = userService;
        this.documentService = documentService;
    }

    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public Meeting createMeeting(CreateMeetingDto createMeetingDto) {
        User organizator = userService.checkForOrganizatorAndGetIt();
        if (!documentService.checkSignedForOrganizator(organizator)) {
            throw new DocumentIsNotSignedYetException();
        }

        Date dateOf = createMeetingDto.getDate();
        String name = createMeetingDto.getName();
        long price = createMeetingDto.getPrice();
        if (!checkDateForFuture(dateOf)) {
            throw new MeetMustBeInFutureException();
        }

        Meeting meeting = new Meeting(0, organizator, name, price, dateOf, List.of());
        return meetingRepository.save(meeting);
    }

    public boolean checkDateForFuture(Date date) {
        Date now = new Date();
        return now.before(date);
    }

    public UserInMeeting signUpForMeeting(SignUpForMeetingDto signUpForMeetingDto) {
        UserInMeeting participant = findUserInMeetingForSignUp(signUpForMeetingDto);
        return signUpUserForMeeting(participant);
    }

    public UserInMeeting findUserInMeetingForSignUp(SignUpForMeetingDto signUpForMeetingDto) {
        User userParticipant = userService.checkForUserOrOrganizatorAndGetIt();
        Meeting meeting = getMeetingById(signUpForMeetingDto.getMeetingId());

        return new UserInMeeting(
                0,
                meeting,
                userParticipant,
                signUpForMeetingDto.getFullName(),
                signUpForMeetingDto.getAge(),
                signUpForMeetingDto.getPcr()
        );
    }

    public UserInMeeting signUpUserForMeeting(UserInMeeting userInMeeting) {
        Meeting meeting = userInMeeting.getMeeting();
        if (isUserInMeeting(meeting, userInMeeting.getUser())) {
            throw new YouAreAlreadyAtSignedUpForMeetingException();
        }

        meeting.getParticipants().add(userInMeeting);
        return userInMeetingRepository.save(userInMeeting);
    }

    public Meeting getMeetingById(Long meetingId) {
        Optional<Meeting> isMeeting = meetingRepository.findById(meetingId);
        if (isMeeting.isEmpty()) {
            throw new MeetingNotFoundException();
        }
        return isMeeting.get();
    }

    public boolean isUserInMeeting(Meeting meeting, User user) {
        List<User> userParticipants = meeting.getParticipants().stream()
                .map(UserInMeeting::getUser)
                .toList();
        return userParticipants.stream().anyMatch(us -> us.getId() == user.getId());
    }

}
