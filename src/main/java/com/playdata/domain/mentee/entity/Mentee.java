package com.playdata.domain.mentee.entity;

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
@Table(name = "mentees")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentee {

    @Id
    private UUID id;

    @OneToOne
    private Member member;

}
