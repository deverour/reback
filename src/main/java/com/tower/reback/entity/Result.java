package com.tower.reback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Result {
	private boolean flag;//执行结果，true为执行成功 false为执行失败
	private String message;//返回结果信息
	private Object data;//返回数据

	public Result(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}


}
