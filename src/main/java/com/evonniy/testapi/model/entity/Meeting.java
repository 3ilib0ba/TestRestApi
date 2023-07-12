package com.evonniy.testapi.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meetings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meeting {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "organizator_id", nullable = false)
    private User organizator;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "date_of", nullable = false)
    private Date dateOf;

    @OneToMany(mappedBy = "meeting")
    private List<UserInMeeting> participants;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id
                && price == meeting.price
                && Objects.equals(organizator, meeting.organizator)
                && Objects.equals(name, meeting.name)
                && Objects.equals(dateOf, meeting.dateOf)
                && Objects.equals(participants, meeting.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizator, name, price, dateOf, participants);
    }
}
