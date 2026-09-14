package org.example.repo;

import org.example.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

}
