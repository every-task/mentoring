package com.playdata.kafka;

import com.playdata.domain.member.kafka.MemberKafkaData;
import com.playdata.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConsumer {
    private final MemberRepository memberRepository;

    @KafkaListener(topics = TopicConfig.MEMBER)
    public void listen(MemberKafkaData data) {
        memberRepository.save(data.ToEntity());
    }
}
