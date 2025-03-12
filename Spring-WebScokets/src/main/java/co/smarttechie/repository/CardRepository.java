package co.smarttechie.repository;

import co.smarttechie.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByBoardIdOrderByPosition(Long boardId);
} 