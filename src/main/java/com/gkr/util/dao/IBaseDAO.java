package com.gkr.util.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<K, V> {
	public boolean doCreate(V vo);//增
	public boolean doUpdate(V vo);//改
	public boolean doDelete(K id);//删
	public boolean doRemoveBatch(K[] ids);//批量删除
    public V findById(K id);//查询返回V对象
	public List<V> findAll();//查询返回list
	public List<V> findAllSplitNoKeyWord(Integer currentPage,Integer lineSize);//分页查询方法
	public List<V> findAllSplit(Map<String,Object> param);//按条件进行分页查询
	public Long getAllCountNoKeyWord();//进行分页查询时取得表中所有的总行数
	public Long getAllCount(Map<String,Object> param);//进行调教分页查询时取得表中所有的总行数
	
}
