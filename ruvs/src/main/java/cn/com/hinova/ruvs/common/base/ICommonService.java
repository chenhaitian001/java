package cn.com.hinova.ruvs.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.com.hinova.ruvs.common.bean.Paging;

public interface ICommonService {

	public void add(SuperModel superModel) throws Exception;

	public void update(SuperModel superModel) throws Exception;

	public void delete(SuperModel superModel) throws Exception;

	public void deletes(Class clazz,Serializable[] id) throws Exception;

	public Paging findPaging(Class clazz,Paging paging) throws Exception;

	public Object findById(SuperModel superModel) throws Exception;

	public List<? extends SuperModel> findByIds(Class clazz, Serializable[] ids) throws Exception;

	public List<? extends SuperModel> find(Class clazz, String sort, String order);

	public long findCount(Class clazz) throws Exception;
	public List<Map<String,Object>> getMapList(String sql, List<Object> params);
}
