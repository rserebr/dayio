package com.dayio.automation.ui.domains;

import com.dayio.automation.ui.enums.NumberEmployees;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "fill", buildMethodName = "create")
public class Company {
    private String companyName;
    private String address;
    private String category;
    private NumberEmployees numberEmployees;
}
