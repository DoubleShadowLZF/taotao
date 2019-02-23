package com.taotao.portal.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author Double
 */
@Data
@Accessors(chain = true)
public class CartItem {
	private Long id ;
	private String title;
	private Long price;
	private Integer num;
	private String image;
}
