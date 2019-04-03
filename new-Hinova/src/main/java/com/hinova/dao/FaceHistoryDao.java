package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.FaceHistory;


public interface FaceHistoryDao {
	public List<FaceHistory> list(Map<String, Object> map);
	public List<FaceHistory> list_last(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
}
