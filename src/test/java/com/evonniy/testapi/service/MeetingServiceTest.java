package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.DocumentIsNotSignedYetException;
import com.evonniy.testapi.exception.exceptions.MeetMustBeInFutureException;
import com.evonniy.testapi.exception.exceptions.MeetingNotFoundException;
import com.evonniy.testapi.exception.exceptions.YouAreAlreadyAtSignedUpForMeetingException;
import com.evonniy.testapi.model.dto.CreateMeetingDto;
import com.evonniy.testapi.model.dto.SignUpForMeetingDto;
import com.evonniy.testapi.model.entity.Meeting;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.model.entity.UserInMeeting;
import com.evonniy.testapi.model.enums.Role;
import com.evonniy.testapi.repository.MeetingRepository;
import com.evonniy.testapi.repository.UserInMeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {
    @Mock
    private MeetingRepository meetingRepository;
    @Mock
    private UserInMeetingRepository userInMeetingRepository;
    @Mock
    private UserService userService;
    @Mock
    private DocumentService documentService;
    @InjectMocks
    private MeetingService meetingService;

    private static final long MILS_IN_DAY = 86400000;

    private static final long MEET_ID = 1;
    private static final long ORG_ID = 1;
    private static final User ORG = new User(
            ORG_ID,
            "ORG",
            "ORG_PASS",
            Role.ORGANIZATOR
    );
    private static Meeting MEETING = new Meeting(
            MEET_ID,
            ORG,
            "MEET_1",
            100,
            new Date((new Date()).getTime() + MILS_IN_DAY),
            new ArrayList<>() // participants
    );

    @BeforeEach
    void setUp() {
        MEETING.getParticipants().clear();
    }

    @Test
    void checkGetMeetingById_correctMeeting_shouldCallRepository() {
        Meeting meeting = mock(Meeting.class);
        when(meetingRepository.findById(MEET_ID)).thenReturn(Optional.ofNullable(meeting));

        final Meeting actual = meetingService.getMeetingById(MEET_ID);
        assertNotNull(actual);
        assertEquals(meeting, actual);
        verify(meetingRepository).findById(MEET_ID);
    }

    @Test
    void checkGetMeetingById_notCorrectMeeting_shouldCallRepository() {
        when(meetingRepository.findById(MEET_ID)).thenReturn(Optional.empty());

        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.getMeetingById(MEET_ID);
        });
        verify(meetingRepository).findById(MEET_ID);
    }

    @Test
    void checkUserInMeeting_correctParticipant() {
        int userAge = 20;
        User user = new User(
                2,
                "participant",
                "password",
                Role.USER
        );
        UserInMeeting participant = new UserInMeeting(
                1,
                MEETING,
                user,
                "user_fullname",
                userAge,
                "pcr"
        );
        MEETING.getParticipants().add(participant);

        assertTrue(meetingService.isUserInMeeting(MEETING, user));
    }

    @Test
    void checkUserInMeeting_participantNotInMeeting() {
        User anotherUser = new User(
                3,
                "not_participant",
                "password",
                Role.USER
        );

        assertFalse(meetingService.isUserInMeeting(MEETING, anotherUser));
    }

    @Test
    void checkSignUpUserForMeeting_correctSignUp_shouldCallUserInMeetingRepository() {
        int userAge = 20;
        User user = new User(
                2,
                "participant",
                "password",
                Role.USER
        );
        UserInMeeting participant = new UserInMeeting(
                1,
                MEETING,
                user,
                "user_fullname",
                userAge,
                "pcr"
        );

        when(userInMeetingRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UserInMeeting actual = meetingService.signUpUserForMeeting(participant);
        assertNotNull(actual);
        assertEquals(actual, participant);
        assertEquals(1, MEETING.getParticipants().size());
        verify(userInMeetingRepository).save(participant);
    }

    @Test
    void checkSignUpUserForMeeting_whenUserIsAlreadyInMeeting() {
        int userAge = 20;
        User user = new User(
                2,
                "participant",
                "password",
                Role.USER
        );
        UserInMeeting participant = new UserInMeeting(
                1,
                MEETING,
                user,
                "user_fullname",
                userAge,
                "pcr"
        );
        MEETING.getParticipants().add(participant);

        assertThrows(YouAreAlreadyAtSignedUpForMeetingException.class, () -> {
            meetingService.signUpUserForMeeting(participant);
        });
    }

    @Test
    void checkFindUserInMeetingForSignUp_returnCorrectUserInMeeting() {
        String user_fullname = "user_fullname";
        int userAge = 20;
        String user_pcr = "user_pcr";
        SignUpForMeetingDto info = new SignUpForMeetingDto(
                MEET_ID,
                user_fullname,
                userAge,
                user_pcr
        );
        User user = new User(
                2,
                "participant",
                "password",
                Role.USER
        );
        when(userService.checkForUserOrOrganizatorAndGetIt()).thenReturn(user);
        when(meetingRepository.findById(MEET_ID)).thenReturn(Optional.ofNullable(MEETING));

        UserInMeeting expect = new UserInMeeting(
                0,
                MEETING,
                user,
                user_fullname,
                userAge,
                user_pcr
        );
        UserInMeeting actual = meetingService.findUserInMeetingForSignUp(info);

        assertEquals(expect, actual);
        verify(meetingRepository).findById(MEET_ID);
    }

    @Test
    void checkCheckDateForFuture_futureDate() {
        Date future = new Date(new Date().getTime() + MILS_IN_DAY);
        assertTrue(meetingService.checkDateForFuture(future));
    }

    @Test
    void checkCheckDateForFuture_beforeDate() {
        Date before = new Date(new Date().getTime() - MILS_IN_DAY);
        assertFalse(meetingService.checkDateForFuture(before));
    }

    @Test
    void checkCreateMeeting_correctMeeting() {
        String meetingName = "meeting_name";
        long meeting_price = 100;
        Date meeting_date = new Date((new Date()).getTime() + MILS_IN_DAY);
        CreateMeetingDto meetingInfo = new CreateMeetingDto(
                meetingName,
                meeting_price,
                meeting_date
        );

        when(userService.checkForOrganizatorAndGetIt()).thenReturn(ORG);
        when(documentService.checkSignedForOrganizator(ORG)).thenReturn(true);
        when(meetingRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        Meeting expect = new Meeting(
                0, ORG, meetingName, meeting_price, meeting_date, List.of()
        );

        Meeting actual = meetingService.createMeeting(meetingInfo);
        assertEquals(expect, actual);
        verify(userService).checkForOrganizatorAndGetIt();
        verify(documentService).checkSignedForOrganizator(ORG);
        verify(meetingRepository).save(any());
    }

    @Test
    void checkCreateMeeting_notCorrectDate() {
        String meetingName = "meeting_name";
        long meeting_price = 100;
        Date meeting_date = new Date((new Date()).getTime() - MILS_IN_DAY);
        CreateMeetingDto meetingInfo = new CreateMeetingDto(
                meetingName,
                meeting_price,
                meeting_date
        );

        when(userService.checkForOrganizatorAndGetIt()).thenReturn(ORG);
        when(documentService.checkSignedForOrganizator(ORG)).thenReturn(true);

        assertThrows(MeetMustBeInFutureException.class, () -> {
            meetingService.createMeeting(meetingInfo);
        });
        verify(userService).checkForOrganizatorAndGetIt();
        verify(documentService).checkSignedForOrganizator(ORG);
    }

    @Test
    void checkCreateMeeting_notSignedDocument() {
        String meetingName = "meeting_name";
        long meeting_price = 100;
        Date meeting_date = new Date((new Date()).getTime() + MILS_IN_DAY);
        CreateMeetingDto meetingInfo = new CreateMeetingDto(
                meetingName,
                meeting_price,
                meeting_date
        );

        when(userService.checkForOrganizatorAndGetIt()).thenReturn(ORG);
        when(documentService.checkSignedForOrganizator(ORG)).thenReturn(false);

        assertThrows(DocumentIsNotSignedYetException.class, () -> {
            meetingService.createMeeting(meetingInfo);
        });
    }

}
