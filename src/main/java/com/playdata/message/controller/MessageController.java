package com.playdata.message.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.message.dto.MessageDto;
import com.playdata.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message/sendmentee")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto sendMenteeMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                  @RequestBody MessageDto messageDto) {

        MessageDto sentMessage = messageService.sendMenteeMessage(messageDto, tokenInfo.getId());
        return sentMessage;
    }
    @PostMapping("/message/sendmentor")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto sendMentorMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                  @RequestBody MessageDto messageDto) {

        MessageDto sentMessage = messageService.sendMentorMessage(messageDto, tokenInfo.getId());
        return sentMessage;
    }

    @GetMapping("/message/list-received")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> receivedMessages(@AuthenticationPrincipal TokenInfo tokenInfo) {
        UUID receiverId = tokenInfo.getId();
        List<MessageDto> receivedMessages = messageService.receivedMessage(receiverId);
        return receivedMessages;
    }

    @DeleteMapping("/message/received/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReceivedMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                        @PathVariable Long id) {
        UUID receiverId = tokenInfo.getId();
        messageService.deleteMessageByReceiver(id, receiverId);
        return "Received message deleted";
    }

    @GetMapping("/message/list-sent")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> sentMessages(@AuthenticationPrincipal TokenInfo tokenInfo) {
        UUID senderId = tokenInfo.getId();
        List<MessageDto> sentMessages = messageService.sentMessage(senderId);
        return sentMessages;
    }

    @DeleteMapping("/message/sent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSentMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                    @PathVariable Long id) {
        UUID senderId = tokenInfo.getId();
        messageService.deleteMessageBySender(id, senderId);
        return "Sent message deleted";
    }

}
