package com.taotao.rest.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 
 */
@Data
@Accessors(chain = true)
public class CatResult {
	private List data;
}
