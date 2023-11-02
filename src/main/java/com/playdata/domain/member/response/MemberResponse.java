package com.playdata.domain.member.response;

import com.playdata.domain.member.entity.Member;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MemberResponse {
    private UUID id;
    private String nickname;
    private String profileImageUrl;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
