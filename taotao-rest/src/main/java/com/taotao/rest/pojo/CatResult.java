package com.taotao.rest.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 
 */
public class CatResult {
	private List data;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
}
