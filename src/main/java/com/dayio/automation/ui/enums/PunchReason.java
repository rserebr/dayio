package com.dayio.automation.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public enum PunchReason {
    EARLY_LEAVE("Early leave"),
    LATE_ARRIVAL("Late arrival"),
    FORGOT_TO_PUNCH("Forgot to punch"),
    TECHNICAL_PROBLEMS("Technical problems"),
    OTHER("Other");

    @Getter
    private final String value;
}
