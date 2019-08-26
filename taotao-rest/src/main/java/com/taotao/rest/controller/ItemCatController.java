package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	/*
	 * 	接收页面传递过来的参数。
	 * 	参数就是方法的名称。
	 * 	返回一个json数据，需要把json数据包装成一句js代码。
	 * 	返回一个字符串。
	 */
	// 方法一：
	/*@RequestMapping(value="/itemcat/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	}*/
	
	// 方法二：Spring4.1版本后，自带了一个工具类 MappingJacksonValue
	@RequestMapping(value="/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}