package com.evonniy.testapi.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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


}
