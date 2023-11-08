package com.playdata.domain.message.entity;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.mentoring.entity.MentoringStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_box")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;

    private String message;

    private boolean deletedBySender;

    private boolean deletedByReceiver;

    private LocalDateTime sentAt;

    public void deleteBySender() {
        this.deletedBySender = true;
    }

    public void deleteByReceiver() {
        this.deletedByReceiver = true;
    }

    public boolean deleted() {
        return isDeletedBySender() && isDeletedByReceiver();
    }

    @Builder
    public Message(Member sender, Member receiver, String message, LocalDateTime sentAt ) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.sentAt = sentAt;

    }
}
