package org.example.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String URL;

    @Column(nullable = true)
    private String review;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Date date;
}
