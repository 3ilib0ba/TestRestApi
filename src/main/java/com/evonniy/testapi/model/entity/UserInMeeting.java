package com.evonniy.testapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInMeeting that = (UserInMeeting) o;
        return id == that.id
                && age == that.age
                && Objects.equals(meeting, that.meeting)
                && Objects.equals(user, that.user)
                && Objects.equals(fullname, that.fullname)
                && Objects.equals(pcr, that.pcr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meeting, user, fullname, age, pcr);
    }
}
