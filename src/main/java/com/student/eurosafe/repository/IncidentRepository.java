package com.student.eurosafe.repository;

import com.student.eurosafe.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    // Custom query: Find all incidents reported by a specific user
    List<Incident> findByUserId(Long userId);
}
