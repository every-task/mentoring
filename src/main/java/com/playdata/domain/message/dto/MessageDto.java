package com.playdata.domain.message.dto;

import com.playdata.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDto {

    private String message;
    private String senderNickname;
    private String receiverNickname;
    private LocalDateTime sentAt;


    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getMessage(),
                message.getSender().getNickname(),
                message.getReceiver().getNickname(),
                message.getSentAt()
        );

    }
}

