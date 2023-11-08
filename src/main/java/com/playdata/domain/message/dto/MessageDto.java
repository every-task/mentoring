package com.playdata.domain.message.dto;

import com.playdata.domain.message.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDto {

    private String message;
    private String senderNickname;
    private String receiverNickname;

    @Builder
    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getMessage(),
                message.getSender().getNickname(),
                message.getReceiver().getNickname()
        );

    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }
}

