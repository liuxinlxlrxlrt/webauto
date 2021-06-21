package com.webauto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {
    private String isNegative;
    private String desc;
    private String mobilePhone;
    private String password;
    private String errorMsg;
}
