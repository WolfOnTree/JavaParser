package org.example.repo;

import org.example.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByProductName(String productName);
    Review findByURL(String URL);
}
