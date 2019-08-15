package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	/*
	 * 	去首页
	 */
	@RequestMapping("/")
	public String toIndexPage() {
		return "index";
	}
	/*
	 * 	去其他页面
	 */
	@RequestMapping("/{page}")
	public String toOtherPage(@PathVariable String page) {
		return page;
	}
}
