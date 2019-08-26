package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatNode {

	/*
	 * 	@JsonProperty("")：
	 * 	标志转换为json数据之后，它的K值是什么，默认是属性名，标注了的话 用标注的名称。
	 */
	@JsonProperty("n")
	private String name;
	@JsonProperty("u")
	private String url;
	@JsonProperty("i")
	private List<?> item;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<?> getItem() {
		return item;
	}
	public void setItem(List<?> item) {
		this.item = item;
	}
	
}
