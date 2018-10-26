package com.yayao.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.exception.CommonRollbackException;
import com.yayao.exception.NotAnymoreException;
import com.yayao.exception.NotIsNotExistException;
import com.yayao.service.BaseService;
import com.yayao.util.ResultUtil;
import com.yayao.util.StateResultList;


/**
 * 基础控制类
 * @author yy
 *
 */
public class BaseController<T,ID> {
	@Autowired
	private BaseService<T,ID> baseService;
    Logger logger=	LoggerFactory.getLogger(getT());

	/**
	 * 获取类型名。再首字母转小写
	 */
	String getName(T t){
		String s=t.getClass().getSimpleName();
		String s1 = s.substring(0, 1); 
		 String s2 = s.substring(1); 
		 String s3 = s1.toLowerCase();  
		 String s4 = s3.concat(s2);  
		return s4;
	}
	/**
	 * 获取类类型
	 */
	Class<ID> getID(){
		Type genType = getClass().getGenericSuperclass();  
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
		@SuppressWarnings("unchecked")
		Class<ID> entityClass = (Class<ID>) params[0];
		return entityClass;
	}
	/**
	 * 获取类类型
	 */
	Class<T> getT(){
		Type genType = getClass().getGenericSuperclass();  
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
		@SuppressWarnings("unchecked")
		Class<T> entityClass = (Class<T>) params[0];
		return entityClass;
	}
	/**
	 * 获取泛型类型的属性值
	 * @param t 泛型类型
	 * @param fieldName 泛型类型属性名称
	 * @return
	 */
	Object getDeclaredField(T t,Object fieldName){
		Object fieldValue = null;
		 Field[] fields = t.getClass().getDeclaredFields();//取得所有类成员变量  
        for(Field f:fields){  
        	f.setAccessible(true); 
        	if(f.getName().equals(fieldName)){
        		try {
					// 将属性的首字符大写，方便构造get，set方法  
					String paramname = ((String) fieldName).substring(0, 1).toUpperCase() + ((String) fieldName).substring(1);
        			//参数对象的方法
					Method m = t.getClass().getMethod("get" + paramname);
					//参数对象的属性值
                    Object value =m.invoke(t);
        			fieldValue=value;
        		} catch (Exception e) {
        			throw new CommonRollbackException("类型找不到异常");
        		} 
        	}
        }  
        return fieldValue;
	}
	
	/**
	 * 增加
	 * @return 
	 */
	public  StateResultList<List<T>> add( T t) {
		List<T> list = new ArrayList<T>();
		boolean am = baseService.add(t);
		if(am){
			list.add(t);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		throw new CommonRollbackException("增加失败");
	}
	/**
	 * 修改
	 * @return
	 */
	public  StateResultList<List<T>> update( T t)  {
		boolean um = baseService.update(t);
		if(um){
			/*
			List<T> list = new ArrayList<T>();
			list.add(t);
			return ResultUtil.getSlefSRSuccessList(list);*/
			@SuppressWarnings("unchecked")
			StateResultList<List<T>> ll = load((ID)getDeclaredField(t,getName(t)+"Id"));
			return ll;
		}
		throw new CommonRollbackException("修改失败");
	}
	/**
	 * 删除
	 * @return
	 */
	public StateResultList<List<T>> delete(ID id)  {
		StateResultList<List<T>> ll = load(id);
		boolean dm = baseService.delete(id);
		if(dm){
			return ll;
		}
		throw new CommonRollbackException("删除失败");
	}
	/**
	 * 数量
	 * @return
	 */
	public StateResultList<List<Integer>> count(Wrapper<T> wrapper)  {
		List<Integer> list = new ArrayList<Integer>();
		int count = baseService.count(wrapper);
		if(count>=0){			
		list.add(count);
		return ResultUtil.getSlefSRSuccessList(list);
		}else{
			throw new NotIsNotExistException("");//不存在
		}
	}
	/**
	 * 单个加载
	 * @return
	 */
	public  StateResultList<List<T>> load(ID id)  {
		List<T> list = new ArrayList<T>();
		T t = baseService.load(id);
			if(t!=null &&!t.equals("")){
				list.add(t);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("");//不存在
			}
	}
	/**
	 * 分页浏览
	 * @return
	 */
	public  StateResultList<List<T>> list(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay,
			Wrapper<T> wrapper)  {
		
			List<T> list = baseService.list(pageNum,pageSize,orderName,orderWay,wrapper);
			if(list!=null&&list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	
}
