package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 
 */
@Data
@Accessors(chain = true)
public class CatNode {
	@JsonProperty("u")
	private String url;
	@JsonProperty("n")
	private String name ;
	@JsonProperty("i")
	private List item;
}
