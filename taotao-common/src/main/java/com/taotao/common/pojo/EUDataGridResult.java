package com.taotao.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *
 * @Description
 */
@Data
@Accessors(chain = true)
public class EUDataGridResult {
	private Long total;
	private List<?> rows;
}
