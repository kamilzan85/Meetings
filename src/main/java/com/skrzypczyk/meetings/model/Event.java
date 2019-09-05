package com.skrzypczyk.meetings.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identity;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private LocalTime time;

    private String title;

    @Column(length=1000000)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Place placeOfMeeting;

    private Integer seats;

    public Event(){
    }

}
