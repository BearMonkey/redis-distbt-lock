package org.monkey.distbtlock.feign.pojo;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String actType;
    private String actUser;
    private String actPwd;
}
