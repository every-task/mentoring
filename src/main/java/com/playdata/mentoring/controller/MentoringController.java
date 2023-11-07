package com.playdata.mentoring.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.mentoring.response.MentoringRequest;
import com.playdata.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MentoringController {

    private final MentoringService mentoringService;


    // 멘토링 요청 보내기
    @PostMapping("/request")
    public void sendMentoringRequest(@AuthenticationPrincipal TokenInfo tokenInfo,
                                     @RequestBody MentoringRequest request) {
        mentoringService.sendRequest(
                tokenInfo.getId(), UUID.fromString(request.getMentorId()));
    }

    // 멘토링 요청 수락
    @PostMapping("/accept")
    public void acceptMentoringRequest(@AuthenticationPrincipal TokenInfo tokenInfo,
                                       @RequestBody MentoringRequest request) {
        mentoringService.acceptRequest(
                tokenInfo.getId(), UUID.fromString(request.getMenteeId()));
    }

    // 멘토링 요청 거절
    @PostMapping("/reject")
    public void rejectMentoringRequest(@AuthenticationPrincipal TokenInfo tokenInfo,
                                       @RequestBody MentoringRequest request) {
        mentoringService.rejectRequest(
                tokenInfo.getId(), UUID.fromString(request.getMenteeId()));
    }
    //멘티 목록 불러오기
    @GetMapping("/mentees")
    public List<Member> getMenteesForMentor(@AuthenticationPrincipal TokenInfo tokenInfo) {
        return mentoringService.getMenteesForMentor(tokenInfo.getId());
    }
    //멘토 목록 불러오기
    @GetMapping("/mentors")
    public List<Member> getMentorsForMentee(@AuthenticationPrincipal TokenInfo tokenInfo) {
        return mentoringService.getMentorsForMentee(tokenInfo.getId());
    }
}
