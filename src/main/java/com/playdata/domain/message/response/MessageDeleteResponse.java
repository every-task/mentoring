package com.playdata.domain.message.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDeleteResponse {
        private boolean success;
        private String message;
}
