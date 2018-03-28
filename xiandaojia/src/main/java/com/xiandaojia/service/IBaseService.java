package com.xiandaojia.service;

public interface IBaseService<T> {

	/**
	 * 插入
	 * 
	 * @param t
	 */
	public void insert(T t);

	/**
	 * 修改
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 根据id删除记录
	 * 
	 * @param id
	 */
	public void delete(Long id);

}
