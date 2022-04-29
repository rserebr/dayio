package com.dayio.automation.ui.domains;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "fill", buildMethodName = "create")
public class AdminUser {
    private String fullName;
    private String email;
    private String mobilePhone;
    private String password;

}
