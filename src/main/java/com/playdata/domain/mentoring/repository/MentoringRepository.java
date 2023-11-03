package com.playdata.domain.mentoring.repository;

import com.playdata.domain.mentoring.entity.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MentoringRepository extends JpaRepository<Mentoring, UUID> {
    Mentoring findById(UUID mentorId, UUID menteeId);
}
