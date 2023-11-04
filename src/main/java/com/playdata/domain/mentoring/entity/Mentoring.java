package com.playdata.domain.mentoring.entity;

import com.playdata.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mentoring")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Mentoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member mentor;

    @ManyToOne
    private Member mentee;

    @Enumerated(EnumType.STRING)
    private MentoringStatus status;

    @Builder
    public Mentoring(Member mentee, Member mentor, MentoringStatus status) {
        this.mentee = mentee;
        this.mentor = mentor;
        this.status = status;
    }

    public void setStatus(MentoringStatus status) {
        this.status = status;
    }
}