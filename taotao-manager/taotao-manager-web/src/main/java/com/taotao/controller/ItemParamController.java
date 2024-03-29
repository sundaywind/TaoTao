package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCid}")
	@ResponseBody
	private TaotaoResult getItemParamByCid(@PathVariable Long itemCid) {
		TaotaoResult result = itemParamService.getItemParamByCid(itemCid);
		return result;
	}
	
	@RequestMapping("save/{itemCid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable long itemCid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(itemCid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
}
