package com.solactive.tickservice.repository;

import com.solactive.tickservice.entity.Tick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TickRepository extends JpaRepository<Tick, Long> {

    List<Tick> findByRic(String ric);

    List<Tick> findByIdBetweenAndRic(long startId, long endId, String ric);

}