package com.taotao.service;

import com.taotao.common.pojo.Page;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	
	public Page getPage(int page, int rows);
	
	public TaotaoResult insertItem(TbItem item, String desc) throws Exception;
	
}
