package com.playdata.mentoring.service;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.mentoring.entity.Mentoring;
import com.playdata.domain.mentoring.entity.MentoringStatus;
import com.playdata.domain.mentoring.repository.MentoringRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MentoringService {

    private final MentoringRepository mentoringRepository;
    private final MemberRepository memberRepository;

    // 예를 들어 front에서 멘토링 신청을 누른 memberId는 fromMemberId가 된다
    //  멘토링 요청을 받은 memberId는 toMemberId가 된다
    // 각각의 id를 back에서 전달 받고 요청을 보내고 처음 상태는 PENDING 상태로 저장이 된다
    public void sendRequest(UUID fromMemberId, UUID toMemberId) {

        Member fromMember = findMemberById(fromMemberId);
        Member toMember = findMemberById(toMemberId);

        Mentoring mentoring = Mentoring.builder()
                .mentee(fromMember)
                .mentor(toMember)
                .status(MentoringStatus.PENDING)
                .build();
        mentoringRepository.save(mentoring);
    }

    // 멘토링 요청을 수락한다
    public void acceptRequest(UUID mentorId, UUID menteeId) {
        Mentoring mentoring = mentoringRepository.findById(mentorId, menteeId);
        mentoring.setStatus(MentoringStatus.ACCEPTED);
        mentoringRepository.save(mentoring);
    }

//    //멘토링 요청을 거절한다
    public void rejectRequest(UUID mentorId, UUID menteeId) {
        Mentoring mentoring = mentoringRepository.findById(mentorId, menteeId);
        mentoring.setStatus(MentoringStatus.REJECTED);
        mentoringRepository.save(mentoring);
    }

    private Member findMemberById(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
    }
}
