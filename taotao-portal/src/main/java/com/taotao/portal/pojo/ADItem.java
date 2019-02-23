package com.taotao.portal.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description
 */
@Data
@Accessors(chain = true)
public class ADItem {
	private Integer width;
	private Integer widthB;

	private Integer height;
	private Integer heightB;

	private String src;
	private String srcB;

	private String alt;
	private String href;
}
