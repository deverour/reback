package com.tower.reback.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@ToString
@Getter
@Setter
public class RebackQueryBean implements Serializable {
	private Integer currentPage;//页码
	private Integer pageSize;//每页记录数

	private List<String> rebackBranch;
	private List<String>rebackArea;
	private List<String>rebackCustomer;

	private String rebacked;
	private String rebackScanned;
	private String rebackNumber;

	private String rebackStartPaymentDate;
	private String rebackEndPaymentDate;

	private String rebackStartReceivableDate;
	private String rebackEndReceivableDate;

}
