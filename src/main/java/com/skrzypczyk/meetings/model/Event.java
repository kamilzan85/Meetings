package com.skrzypczyk.meetings.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;

    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Place placeOfMeeting;

    private Integer seats;
}
