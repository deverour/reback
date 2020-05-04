package com.tower.reback.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class User {
    private String name;
    private String username;
    private String password;
    private String group;
    private String newpassword;


}