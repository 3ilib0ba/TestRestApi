//package com.evonniy.testapi.mapper;
//
//import com.evonniy.testapi.model.dto.UserInMeetingDto;
//import com.evonniy.testapi.model.entity.Meeting;
//import com.evonniy.testapi.model.entity.User;
//import com.evonniy.testapi.model.entity.UserInMeeting;
//import com.evonniy.testapi.model.enums.Role;
//import com.evonniy.testapi.model.mapper.MeetingMapper;
//import com.evonniy.testapi.model.mapper.UserMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class MeetingMapperTest {
//    private UserMapper userMapper;
//    private MeetingMapper meetingMapper;
//
//    private final User USER_ORGANIZATOR = new User(1, "USER_ORG", "pass", Role.ORGANIZATOR);
//    private final User USER_2 = new User(2, "USER_2", "pass", Role.USER);
//
//    private final Meeting MEETING = new Meeting(1, USER_ORGANIZATOR, "meeting_1", 100, new Date(), List.of());
//    private final UserInMeeting USER_IN_MEETING = new UserInMeeting(1, MEETING, USER_2, "Ivanov A A", 20, "pcr 1");
//    private final UserInMeetingDto USER_IN_MEETING_DTO = new UserInMeetingDto();
//
//    @BeforeEach
//    void setup() {
//        userMapper = new UserMapper();
//        meetingMapper = new MeetingMapper(userMapper);
//    }
//
//    @Test
//    public void toUserInMeetingDto_shouldCreateDtoFromObject() {
//        UserInMeetingDto result = meetingMapper.toUserInMeetingDto(USER_IN_MEETING);
//        assertEquals();
//    }
//
//}
