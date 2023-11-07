package com.playdata.domain.mentoring.repository;

import com.playdata.domain.mentoring.entity.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    Optional<Mentoring> findByMentorIdAndMenteeId(UUID mentorId, UUID menteeId);
    List<Mentoring> findMenteesByMentorId(UUID mentorId);
    List<Mentoring> findMentorsByMenteeId(UUID menteeId);
}
