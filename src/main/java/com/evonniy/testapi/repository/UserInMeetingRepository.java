package com.evonniy.testapi.repository;

import com.evonniy.testapi.model.entity.UserInMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInMeetingRepository extends JpaRepository<UserInMeeting, Long> {
}
