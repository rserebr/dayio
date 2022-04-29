package com.dayio.automation.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CalendarComponent {

    public CalendarComponent selectDateInRangeFormNow(int numberOfDays) {


        LocalDate requiredDate = LocalDate.now().plusDays(numberOfDays);
        $x("//input[@name='date']").setValue(requiredDate.getMonth().name().substring(0, 3)
        + " " + requiredDate.getDayOfMonth() + ", " + requiredDate.getYear());
        log.info("Date is set");
        return this;
    }
}
