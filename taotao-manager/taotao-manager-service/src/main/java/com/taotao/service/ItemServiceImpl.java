package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(Long itemId) {
		/*根据ID*/
		// TbItem selectByPrimaryKey = itemMapper.selectByPrimaryKey(itemId);
		
		/*根据查询条件*/
		// 1.创建example对象
		TbItemExample example = new TbItemExample();
		// 2.创建查询条件【Criteria：条件】
		Criteria criteria = example.createCriteria();
		// 3.根据ID查，将id放入条件中
		criteria.andIdEqualTo(itemId);
		// 4.根据ID肯定只返回一条数据，取List的第一条就行，前提是List不能为空
		List<TbItem> itemList = itemMapper.selectByExample(example);
		// 如果list不等于空并且list的长度大于0，取第一个并返回，如果为空 返回空
		if (itemList != null && itemList.size() > 0) {
			TbItem item = itemList.get(0);
			return item;
		}
		return null;
	}

	
}
