package com.playdata.domain.mentee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenteeRepository extends JpaRepository<Long, UUID> {
}
