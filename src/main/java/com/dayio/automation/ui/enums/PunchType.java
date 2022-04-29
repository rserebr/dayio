package com.dayio.automation.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public enum PunchType {
    ENTRY("Entry"),
    BREAK_START("Break start"),
    BREAK_END("Break end"),
    EXIT("Exit");

    @Getter
    private final String value;
}
