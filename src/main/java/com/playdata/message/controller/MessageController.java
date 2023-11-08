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
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                  @RequestBody MessageDto messageDto) {

        MessageDto sentMessage = messageService.sendMessage( messageDto, tokenInfo.getId());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/list-received")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MessageDto>> receivedMessages(@AuthenticationPrincipal TokenInfo tokenInfo) {
        UUID receiverId = tokenInfo.getId();
        List<MessageDto> receivedMessages = messageService.receivedMessage(receiverId);
        return ResponseEntity.ok(receivedMessages);
    }

    @DeleteMapping("/received/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReceivedMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                        @PathVariable Long id) {
        UUID receiverId = tokenInfo.getId();
        messageService.deleteMessageByReceiver(id, receiverId);
        return ResponseEntity.ok("Received message deleted");
    }

    @GetMapping("/list-sent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MessageDto>> sentMessages(@AuthenticationPrincipal TokenInfo tokenInfo) {
        UUID senderId = tokenInfo.getId();
        List<MessageDto> sentMessages = messageService.sentMessage(senderId);
        return ResponseEntity.ok(sentMessages);
    }

    @DeleteMapping("/sent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteSentMessage(@AuthenticationPrincipal TokenInfo tokenInfo,
                                                    @PathVariable Long id) {
        UUID senderId = tokenInfo.getId();
        messageService.deleteMessageBySender(id, senderId);
        return ResponseEntity.ok("Sent message deleted");
    }

}
