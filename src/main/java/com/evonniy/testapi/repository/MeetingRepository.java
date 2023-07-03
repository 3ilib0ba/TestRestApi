package com.evonniy.testapi.repository;

import com.evonniy.testapi.model.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    public List<Meeting> findAll();

    public Optional<Meeting> findById(Long id);

}
