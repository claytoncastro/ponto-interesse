package com.project.pontointeresse.exceptions.details;

import lombok.Builder;

public class ParseDataDetails extends ErrorDetails {

    @Builder
    public ParseDataDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }

}
