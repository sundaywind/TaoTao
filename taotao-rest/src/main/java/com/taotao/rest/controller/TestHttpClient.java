package com.taotao.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;

@Controller
public class TestHttpClient {

	@RequestMapping(value = "/httpclient/post", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult testPost() {
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/httpclient/postwithparam", method = RequestMethod.POST)
	@ResponseBody
	public String testPostWithParam(HttpServletResponse response, String username, String password) {
		response.setHeader("content-Type", "text/html;charset=UTF-8");
		return "用户名为：" + username + "\t；密码是：" + password;
	}
}
