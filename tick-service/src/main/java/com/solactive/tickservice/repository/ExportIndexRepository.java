package com.solactive.tickservice.repository;

import com.solactive.tickservice.entity.ExportIndex;
import com.solactive.tickservice.entity.ExportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExportIndexRepository extends JpaRepository<ExportIndex, Long> {

    Optional<ExportIndex> findFirstByRicOrderByIdDesc(String ric);

    Optional<ExportIndex> findFirstByStatus(ExportStatus status);

}