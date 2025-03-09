package co.smarttechie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.smarttechie.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
} 