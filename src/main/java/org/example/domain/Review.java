package org.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String URL;

    @Column(nullable = true, length = 5000)
    private String review;

    @Column(nullable = false)
    private int rating;

    public Review() {

    }

    public Review(String productName, String URL, String review, int rating){
        this.productName = productName;
        this.URL = URL;
        this.review = review;
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public String getURL() {
        return URL;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }
}