package com.dayio.automation.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public enum NumberEmployees {
    UP_TO_NINE("up to 9"),
    NINE_TO_FIFTY("9 to 50"),
    FIFTY_TO_NINETY_NINE("50 to 99"),
    HUNDRED_PLUS("100+");

    @Getter
    private final String value;
}
