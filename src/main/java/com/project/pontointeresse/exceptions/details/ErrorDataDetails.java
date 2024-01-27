package com.project.pontointeresse.exceptions.details;

import lombok.Builder;

public class ErrorDataDetails extends ErrorDetails {

    @Builder
    public ErrorDataDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }

}
