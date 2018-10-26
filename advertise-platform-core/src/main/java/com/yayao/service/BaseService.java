package com.yayao.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * 基础逻辑层接口
 * @author yy
 *
 */
public interface BaseService<T,ID> {
	/** 新增 */	
	 boolean add(T t) ;
	/** 删除 */	
	 boolean delete(ID id) ;
	/** 更新*/	
	 boolean update(T t);
	/** 装载 */	
	 T load(ID id);
	/** 总共数目
	 *  根据 Wrapper 条件，查询总记录数
	 */	
	 int count(Wrapper<T> wrapper);
	/**
     * <p>
     * 根据 t 条件，查询全部记录（并翻页）
     * </p>
     * @param wrapper   实体对象封装操作类（可以为 null）
     * @return List<T>
     */
	 List<T> list(
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay,
            Wrapper<T> wrapper) ;
}
