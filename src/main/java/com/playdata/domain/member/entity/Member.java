package com.playdata.domain.member.entity;

import com.playdata.domain.mentoring.entity.MentoringStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    private UUID id;
    private String nickname;
    private String profileImageUrl;
    private MentoringStatus status;

    @Builder
    public Member(UUID id, String nickname, String profileImageUrl, MentoringStatus status) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
    }

    public void setStatus(MentoringStatus status) {
        this.status = status;
    }
}
