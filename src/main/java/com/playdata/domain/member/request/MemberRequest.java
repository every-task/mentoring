package com.playdata.domain.member.request;

import com.playdata.domain.member.entity.Member;

import java.util.UUID;

public record MemberRequest(String id, String nickname, String profileImageUrl)  {

    public Member ToEntity() {
        return Member.builder()
                .id(UUID.fromString(id))
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}