package com.tower.reback.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class CpyQueryBean {
    private List<String> cpyBranch;
    private List<String> cpyArea;
    private List<String> cpyCustomer;
    private String cpyStartPaymentDate;
    private String cpyEndPaymentDate;



}
