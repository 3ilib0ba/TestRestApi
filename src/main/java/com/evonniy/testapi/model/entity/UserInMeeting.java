package com.evonniy.testapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "meetings_users_relation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInMeeting {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "age")
    private int age;

    @Column(name = "pcr")
    private String pcr;
}
