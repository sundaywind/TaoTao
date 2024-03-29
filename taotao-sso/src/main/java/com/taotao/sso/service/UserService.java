package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

	public TaotaoResult checkData(String content, Integer type);
	
	public TaotaoResult createUser(TbUser user);
	
}
