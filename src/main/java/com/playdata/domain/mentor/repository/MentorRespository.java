package com.playdata.domain.mentor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentorRespository extends JpaRepository<Long, UUID> {
}
