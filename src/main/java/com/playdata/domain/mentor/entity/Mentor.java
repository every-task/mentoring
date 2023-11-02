package com.playdata.domain.mentor.entity;

import com.playdata.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "mentors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentor {
    @Id
    private UUID id;

    @OneToOne
    private Member member;
}
