package com.stream.app.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "Course")
    private String courseId;

    @Column(nullable = false, unique = true)
    private String courseName;

    @Column(length = 1000)
    private String description;

    private String type;

    private boolean active;

    private String status;

    private Integer duration;

    private Double price;

    @OneToMany(mappedBy = "course")
    private List<Video> list = new ArrayList<>();

}
