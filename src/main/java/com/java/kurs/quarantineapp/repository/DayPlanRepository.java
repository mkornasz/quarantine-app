package com.java.kurs.quarantineapp.repository;

import com.java.kurs.quarantineapp.model.DayPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayPlanRepository extends JpaRepository<DayPlan, Integer> {

    Optional<DayPlan> findByCourierIdAndDate (Integer courierId, LocalDate date);

    List<DayPlan> findByCourierId(Integer courierId);
}