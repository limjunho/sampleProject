package com.motionblue.mi.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.common.PortalProperty;
import portal.common.util.ExceptionUtil;
import portal.common.util.FileHandler;
import portal.common.util.StringUtil;

@Service
public class EditorService {

	private static Logger log = Logger.getLogger(EditorService.class);
	
	@Autowired
	private EditorDao editorDao;
	
	public void add(EditorFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		editorDao.insert(vo);
	}

	
	public void removeByName(EditorFileVo vo) throws Exception {
		// TODO Auto-generated method stub
		editorDao.deleteByName(vo);
	}
	
	
	public EditorFileVo getFile(EditorFileVo vo) throws Exception{
		return editorDao.selectBySaveName(vo);
	}
	
	
	public List<EditorFileVo> getNewFile(String newCont, String oldCont) throws Exception{
		
		List<EditorFileVo> list = new ArrayList<EditorFileVo>();
		
		// 새글로 부터 파일 추출
		List<String> newList = StringUtil.getSrcFromImageTag(newCont);
		
		// 이전글로 부터 파일 추출
		List<String> oldList = StringUtil.getSrcFromImageTag(oldCont);
				
		int length = newList.size();
		for ( int i=length-1; i>=0; i-- ){
			String newPath = newList.get(i);
			
			// 기존 존재하는 파일인 경우 제외.
			for ( String path : oldList){
				if ( path.equals(newPath) ){
					newList.remove(i);
					break;
				}
			}
		}
		
		// db로 부터 파일 상세 정보 조회
		for ( String str : newList ){
			EditorFileVo vo = new EditorFileVo();
			
			String saveName = this.getFileFromUrl(str);
			saveName = saveName.replaceAll("\r\n", "");
			
			// folder
			vo.setFolder(this.getDateFolderFormUrl(str));			// /~somepath/2014/09/14/파일명.ext 로부터 날짜 폴더 추출.
			// 실저장파일명
			vo.setSaveName(saveName);
			// 필드명 고정
			vo.setFieldName("edit");
			// 저장된 파일 조회.
			vo = this.getFile(vo);
			
			if ( vo != null ) list.add(vo);
		}
		
		return list;
	}

	
	public List<EditorFileVo> getDeleteFile(String newCont, String oldCont) throws Exception{
		log.debug("======================================== 에디터 파일 정리 시작");
		
		List<EditorFileVo> delFileList = new ArrayList<EditorFileVo>();
		
		// 새글로 부터 파일 추출
		List<String> newList = StringUtil.getSrcFromImageTag(newCont);
				
		// 이전글로 부터 파일 추출
		List<String> oldList = StringUtil.getSrcFromImageTag(oldCont);
		
		// 이전 파일 정리대상 존재하지 않으므로 종료. 
		if ( oldList.size() == 0 ){
			return delFileList;
		}
		
		// 파일 비교 - 이전글의 삭제 대상 추출.
		for ( String path : oldList ){
			boolean bDel = true;
			for ( String newPath : newList){
				if ( path.equals(newPath) ){
					bDel = false;
					break;
				}
			}

			// 삭제 대상에 추가.
			if ( bDel ){
				EditorFileVo vo = new EditorFileVo();
				
				String saveName = this.getFileFromUrl(path);
				saveName = saveName.replaceAll("\r\n", "");
				
				// folder
				vo.setFolder(this.getDateFolderFormUrl(path));			// /~somepath/2014/09/14/파일명.ext 로부터 날짜 폴더 추출.
				// 실저장파일명
				vo.setSaveName(saveName);
				// 필드명 고정
				vo.setFieldName("edit");
				
				delFileList.add(vo);
			}
		}
		return delFileList;
	}
	
	public List<String> getDeleteFile2(String newCont, String oldCont) throws Exception{
		log.debug("======================================== 에디터 파일 정리 시작");
		
		List<String> delFileList = new ArrayList<String>();
		
		// 새글로 부터 파일 추출
		List<String> newList = StringUtil.getSrcFromImageTag(newCont);
				
		// 이전글로 부터 파일 추출
		List<String> oldList = StringUtil.getSrcFromImageTag(oldCont);
		
		if ( newList.size() > 0 ){
			log.debug("DB 데이터 삭제 처리.");
			// DB 데이터 삭제 처리.
			// this.removeEditorTmpData(newList);
		}
		
		// 이전 파일 정리대상 존재하지 않으므로 종료. 
		if ( oldList.size() == 0 ){
			log.debug("이전 파일 없으므로 종료.");
			return delFileList;
		}
		
		// 파일 비교 - 이전글의 삭제 대상 추출.
		for ( String path : oldList ){
			boolean bDel = true;
			for ( String newPath : newList){
				if ( path.equals(newPath) ){
					bDel = false;
					break;
				}
			}

			// 삭제 대상에 추가.
			if ( bDel ){
				log.debug("삭제 대상 추가 : " + path);
				delFileList.add(path);
			}
		}
		return delFileList;
	}

	public void removeEditorFile(List<String> list){

		// 파일 삭제 처리
		if ( list.size() > 0 ){
			log.debug("삭제 처리 시작");
			for ( String path : list ){
				try{
					String [] arrPath = path.split("/");
					
					log.debug(arrPath[arrPath.length-1]);
					log.debug(arrPath[arrPath.length-2]);
					log.debug(arrPath[arrPath.length-3]);
					log.debug(arrPath[arrPath.length-4]);
					
					path = PortalProperty.get("EDITOR_UPLOAD_FILE_PATH")
							+ File.separator + arrPath[arrPath.length-4]
									+ File.separator + arrPath[arrPath.length-3]
											+ File.separator + arrPath[arrPath.length-2]
													+ File.separator + arrPath[arrPath.length-1];
					FileHandler.deleteFile(path);
				}catch(Exception e){
					log.error(ExceptionUtil.getStackTrace(e));
				}
			}
		}
	}
	
	public String getDateFolderFormUrl(String fullPath){
		String result = "";
		String [] arrPath = fullPath.split("/");
		
		if ( arrPath == null )
			return "";
		
		if ( (arrPath.length - 1) < 0 )
			return "";
		
		/*
		log.debug(arrPath[arrPath.length-1]);
		log.debug(arrPath[arrPath.length-2]);
		log.debug(arrPath[arrPath.length-3]);
		log.debug(arrPath[arrPath.length-4]);
		*/
		
		result = arrPath[arrPath.length-4]
						+ "/" + arrPath[arrPath.length-3]
								+ "/" + arrPath[arrPath.length-2];
		return result;
	}
	
	private String getFileFromUrl(String fullPath){
		String result = "";
		
		String [] arrPath = fullPath.split("/");
		if ( arrPath == null )
			return "";
		
		if ( (arrPath.length - 1) < 0 )
			return "";
		/*
		log.debug(arrPath[arrPath.length-1]);
		log.debug(arrPath[arrPath.length-2]);
		log.debug(arrPath[arrPath.length-3]);
		log.debug(arrPath[arrPath.length-4]);
		*/
		result = arrPath[arrPath.length-1];
		
		return result;
	}
}
