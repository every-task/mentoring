package com.playdata.message.service;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.mentoring.entity.Mentoring;
import com.playdata.domain.mentoring.entity.MentoringStatus;
import com.playdata.domain.mentoring.repository.MentoringRepository;
import com.playdata.domain.message.dto.MessageDto;
import com.playdata.domain.message.entity.Message;
import com.playdata.domain.message.repository.MessageRepository;
import com.playdata.domain.message.response.MessageDeleteResponse;
import com.playdata.exception.MemberNotFoundException;
import com.playdata.exception.MessageNotFoundException;
import com.playdata.exception.StatusNotAcceptedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final MentoringRepository mentoringRepository;

    //쪽지 보내기
    public MessageDto sendMessage(MessageDto messageDto, UUID senderId) {

        Member sender = memberRepository.findById(senderId)
                .orElseThrow(() -> new MemberNotFoundException("Member by senderId not found"));
        Member receiver = memberRepository.findByNickname(messageDto.getReceiverNickname());

        Optional<Mentoring> existRequest = mentoringRepository.findByMentorIdAndMenteeId(senderId, receiver.getId());
        boolean isMentoringAccepted = existRequest.map(
                        request -> request.getStatus() == MentoringStatus.ACCEPTED)
                .orElse(false);

        if (!isMentoringAccepted) {
            throw new StatusNotAcceptedException("Mentoring status is not ACCEPTED");
        }
        Message messages = Message.builder()
                .sender(sender)
                .receiver(receiver)
                .message(messageDto.getMessage())
                .sentAt(LocalDateTime.now())
                .build();
        messageRepository.save(messages);
        return MessageDto.toDto(messages);
    }


    // 받은 쪽지함 불러오기
    // 한 명의 유저가 받은 모든 쪽지
    public List<MessageDto> receivedMessage(UUID receiverId) {
        List<Message> messages = messageRepository.findAllByReceiverId(receiverId);
        List<MessageDto> messageDtos = new ArrayList<>();

        for (Message message : messages) {
            if (!message.isDeletedByReceiver()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    // 쪽지함에서 받은 쪽지 삭제
    public MessageDeleteResponse deleteMessageByReceiver(Long id, UUID receiverId) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            throw new MessageNotFoundException("Message by receiverId not found");
        });

        if (receiverId.equals(message.getReceiver())) {
            message.deleteByReceiver(); // 받은 사람의 쪽지함에서 쪽지 삭제
            if (message.deleted()) { // 양쪽 다 쪽지가 삭제됐으면 db에서 삭제
                messageRepository.delete(message);
                return new MessageDeleteResponse(true, "deleteByReceiver && deleteBySender");
            }
            return new MessageDeleteResponse(true, "deleteByReceiver");
        }
        return new MessageDeleteResponse(false, "Failed to delete");

    }

    // 보낸 쪽지함 불러오기
    // 한 명의 유저가 받은 모든 쪽지
    public List<MessageDto> sentMessage(UUID senderId) {
        List<Message> messages = messageRepository.findAllBySenderId(senderId);
        List<MessageDto> messageDtos = new ArrayList<>();

        for (Message message : messages) {
            if (!message.isDeletedBySender()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    // 보낸 쪽지 삭제
    public MessageDeleteResponse deleteMessageBySender(Long id, UUID senderId) {

        Message message = messageRepository.findById(id).orElseThrow(() ->
                new MessageNotFoundException("Message by senderId not found"));


        if (senderId.equals(message.getSender())) {
            message.deleteBySender(); // 보낸사람의 쪽지함에서 쪽지 삭제
            if (message.deleted()) { // 양쪽 다 쪽지가 삭제됐으면 db에서 삭제
                messageRepository.delete(message);

                return new MessageDeleteResponse(true, "deleteByReceiver && deleteBySender");
            }
            return new MessageDeleteResponse(true, "deleteBySender");
        }
        return new MessageDeleteResponse(false, "Failed to delete");
    }



}
