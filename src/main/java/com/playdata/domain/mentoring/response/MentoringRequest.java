package com.playdata.domain.mentoring.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentoringRequest {
    private String mentorId;
    private String menteeId;
}
