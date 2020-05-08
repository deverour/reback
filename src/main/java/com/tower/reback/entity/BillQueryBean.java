package com.tower.reback.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class BillQueryBean {
    private List<String> billBranch;
    private List<String> billArea;
    private List<String> billCustomer;
    private String billPayNumber;
    private String billStartPaymentDate;
    private String billEndPaymentDate;



}
