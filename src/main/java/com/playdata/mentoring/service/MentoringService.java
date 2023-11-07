package com.playdata.mentoring.service;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.mentoring.entity.Mentoring;
import com.playdata.domain.mentoring.entity.MentoringStatus;
import com.playdata.domain.mentoring.repository.MentoringRepository;
import com.playdata.exception.MemberNotFoundException;
import com.playdata.exception.MentoringRequestNotAllowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MentoringService {

    private final MentoringRepository mentoringRepository;
    private final MemberRepository memberRepository;


    public void sendRequest(UUID menteeId, UUID mentorId) {

        Optional<Mentoring> existRequest = mentoringRepository.findByMentorIdAndMenteeId(menteeId, mentorId);
        if (existRequest.isPresent()) {
            throw new MentoringRequestNotAllowedException("Mentoring request already exists.");
        } else {
            Mentoring mentoring = Mentoring.builder()
                    .mentee(findMemberById(menteeId))
                    .mentor(findMemberById(mentorId))
                    .status(MentoringStatus.PENDING)
                    .build();
            mentoringRepository.save(mentoring);
        }

    }

    // 멘토링 요청을 수락한다
    public void acceptRequest(UUID mentorId, UUID menteeId) {
        Optional<Mentoring> mentoringOptional = mentoringRepository.findByMentorIdAndMenteeId(mentorId, menteeId);
        Mentoring mentoring = mentoringOptional.get();
        mentoring.setStatus(MentoringStatus.ACCEPTED);
        mentoringRepository.save(mentoring);
    }

    //멘토링 요청을 거절한다
    public void rejectRequest(UUID mentorId, UUID menteeId) {
        Optional<Mentoring> mentoringOptional = mentoringRepository.findByMentorIdAndMenteeId(mentorId, menteeId);
        Mentoring mentoring = mentoringOptional.get();
        mentoring.setStatus(MentoringStatus.REJECTED);
        mentoringRepository.save(mentoring);
    }

    public List<Member> getMenteesForMentor(UUID mentorId) {
        List<Mentoring> mentorings = mentoringRepository.findMenteesByMentorId(mentorId);
        return mentorings.stream()
                .map(Mentoring::getMentee)
                .collect(Collectors.toList());
    }

    public List<Member> getMentorsForMentee(UUID menteeId) {
        List<Mentoring> mentorings = mentoringRepository.findMentorsByMenteeId(menteeId);
        return mentorings.stream()
                .map(Mentoring::getMentor)
                .collect(Collectors.toList());
    }


    private Member findMemberById(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
    }
}
