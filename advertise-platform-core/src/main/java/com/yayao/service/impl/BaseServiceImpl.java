package com.yayao.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.dao.BaseDao;
import com.yayao.service.BaseService;
@Service
public  class BaseServiceImpl<T,ID> implements BaseService<T,ID>{
	@Autowired
	BaseDao<T> baseDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(T t) {
		boolean b =false;
		Integer aid = baseDao.insert(t);
		//System.err.println("aid="+aid);
		if(aid>0){
			b=true;
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(ID id) {
		boolean b=false;
		Integer did = baseDao.deleteById((Serializable) id);
		//System.err.println("did="+did);
		if(did>0){
			b=true;
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean update(T t) {
		boolean b=false;
		Integer uid = baseDao.updateById(t);
		//Wrapper<T> w=new EntityWrapper<>();
		//Integer uid = baseDao.update(t, wrapper);
		if(uid>0){
			b=true;
		}
		return b;
	}

	@Override
	public T load(ID id) {
		T t = baseDao.selectById( (Serializable) id);
		//System.err.println("t="+t);
		return t;
	}

	@Override
	public int count(Wrapper<T> wrapper) {
		int c = baseDao.selectCount(wrapper);
		return c;
	}
	@Override
	public List<T> list(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay,
			Wrapper<T> wrapper) {
		//分页
		if(pageNum<1){//最小是逻辑1，实际0，
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;
		}
		RowBounds rowBounds=new RowBounds(pageNum-1,pageSize);
		//排序
		if(wrapper==null){
			wrapper=new EntityWrapper<T>();
		}
		if(!StringUtils.isEmpty(orderName)&&!StringUtils.isEmpty(orderWay)){
			//ArrayList<String> list = new ArrayList<String>();
			//list.add(orderName);
			if(orderWay.equals("asc")){
				wrapper=wrapper.orderBy(orderName, true);
				//wrapper=wrapper.orderAsc(list);
				
			}else if(orderWay.equals("desc")){
				wrapper=wrapper.orderBy(orderName, false);
				//wrapper=wrapper.orderDesc(list);
			}
		}
		List<T> list = baseDao.selectPage(rowBounds, wrapper);
		return list;
	}

	

	
}
