package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    @Query("SELECT p FROM Player p LEFT JOIN p.stat st WHERE p.birthDate > :after AND p.birthDate < :before " +
            "ORDER BY st.shooting DESC, st.passing DESC , st.endurance DESC , p.lastName ASC")
    List<Player> findAllByBirthDateBetweenOrderByStat(LocalDate after, LocalDate before);
}
