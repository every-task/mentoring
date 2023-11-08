package com.playdata.domain.message.repository;

import com.playdata.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiverId(UUID receiverId);
    List<Message> findAllBySenderId(UUID senderId);

}
