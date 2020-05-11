package com.tower.reback.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class User implements Serializable {
    private String name;
    private String username;
    private String password;
    private String group;
    private String newpassword;


}