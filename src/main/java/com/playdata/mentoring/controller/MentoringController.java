package com.playdata.mentoring.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.mentoring.response.MentoringRequest;
import com.playdata.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MentoringController {

    private final MentoringService mentoringService;


    // 멘토링 요청 보내기
    @PostMapping("/request")
    public void sendMentoringRequest(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @RequestBody MentoringRequest request) {

        mentoringService.sendRequest(tokenInfo.getId(), UUID.fromString(request.getToMemberId()));
    }

    // 멘토링 요청 수락
    @PostMapping("/accept")
    public void acceptMentoringRequest(@RequestBody UUID mentorId, UUID menteeId) {
        mentoringService.acceptRequest(mentorId, menteeId);
    }

    // 멘토링 요청 거절
    @PostMapping("/reject")
    public void rejectMentoringRequest(@RequestBody UUID mentorId, UUID menteeId) {
        mentoringService.rejectRequest(mentorId, menteeId);
    }
}
