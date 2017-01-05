package com.motionblue.mi.common;

import org.springframework.stereotype.Repository;

@Repository
public interface EditorDao {
	public void insert(EditorFileVo vo) throws Exception;
	
	public void deleteByName(EditorFileVo vo) throws Exception;
	
	public EditorFileVo selectBySaveName(EditorFileVo vo) throws Exception;
}
