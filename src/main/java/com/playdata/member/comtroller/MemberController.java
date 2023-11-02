package com.playdata.member.comtroller;

import com.playdata.domain.member.request.MemberRequest;
import com.playdata.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public void memberSave(@RequestBody MemberRequest memberRequest) {
        memberService.memberSave(memberRequest);
    }
}
