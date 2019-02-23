package com.taotao.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description
 */
@Data
@Accessors(chain = true)
public class EUTreeNode {


	/**
	 * id : 1
	 * text : Node 1
	 * state : closed
	 * children : [{"id":11,"text":"Node 11"},{"id":12,"text":"Node 12"}]
	 */

	private Long id;
	private String text;
	private String state;
	private List<ChildrenBean> children;

	@Data
	@Accessors(chain = true)
	public static class ChildrenBean {
		/**
		 * id : 11
		 * text : Node 11
		 */

		private Long id;
		private String text;

	}
}
