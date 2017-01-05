package com.motionblue.mi.common;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionblue.mi.user.UserFileVo;
import com.motionblue.mi.user.UserService;
import com.motionblue.mi.user.UserVo;

import portal.common.exception.PortalServiceException;
import portal.common.model.FileInfoVO;
import portal.common.util.FileHandler;
import portal.common.util.MbUploadForSpring;
import portal.common.util.RequestHelperForSpring;

/**
 * 공통
 * @author
 *
 */
@Controller
public class CommonController extends PortalBaseController{

	private static Logger log = Logger.getLogger(CommonController.class);

	@Autowired
	protected CommonService commonService;

	
	/**
	 * 사원관리 서비스
	 */
	@Autowired
	protected UserService userService;


	/**
	 * 조직원 팝업 목록
	 */
	public void empList(CommonVo paramVo) throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		List<CommonVo> empList = new ArrayList<CommonVo>();
		
		empList = commonService.getEmpList(paramVo);
		
		JSONObject obj = null;
		JSONArray jsonList = new JSONArray();
		if(empList != null){
			for(int i=0; i<empList.size(); i++){
				obj = new JSONObject();
				obj.put("empNo", empList.get(i).getEmpNo());
				obj.put("userNm", empList.get(i).getUserNm());
				
				jsonList.put(obj);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
	    sb.append(jsonList);
	    res.setContentType("json");
	    res.setCharacterEncoding("UTF-8");
	    PrintWriter out = null;
	    out = res.getWriter();
	    out.write(sb.toString());
	    out.flush();
	    out.close();
	}
	
	/**
	 * 조직원 팝업 직급직책 목록
	 */
	public void empSelectbox(CommonVo paramVo) throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		List<CommonVo> empSbboxList = new ArrayList<CommonVo>();
		String cls = "JG0000";	//직급 상위코드
		String dr = "JC0000";	//직책 상위코드
		empSbboxList = commonService.empSelectbox(paramVo);
		
		JSONObject obj = null;
		JSONObject obj2 = null;
		JSONArray clsList = new JSONArray();
		JSONArray drList = new JSONArray();
		if(empSbboxList != null){
			obj = new JSONObject();
			for(int i=0; i<empSbboxList.size(); i++){
				obj2 = new JSONObject();
				obj2.put("cd", empSbboxList.get(i).getCd());
				obj2.put("codeNm", empSbboxList.get(i).getCodeNm());
				
				if(cls.equals(empSbboxList.get(i).getUpCode())){
					clsList.put(obj2);
				}else if(dr.equals(empSbboxList.get(i).getUpCode())){
					drList.put(obj2);
				}
			}
			obj.put("clsList", clsList);
			obj.put("drList", drList);
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
		sb.append(obj);
		res.setContentType("json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = res.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	/**
	 * 예시용으로 남겨둠 - 사용 안함
	 */
	public String emp(CommonVo paramVo) throws Exception{
		String resultMsg = "";
		
//		List<UserVo> lvList = new ArrayList<UserVo>();
		
		try{
			RequestHelperForSpring.getParameter(paramVo);
			
			
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		return path;
	}
	
	/**
	 * 개인정보 수정팝업 - 조회
	 */
	public void getUser(CommonVo paramVo) throws Exception{
		
		UserVo mvo = this.getUserVo();
		long userSeq = mvo.getUserSeq();
		
		//사원 정보 조회
		UserVo dataVo = new UserVo();
		dataVo.setUserSeq(userSeq);
		dataVo = userService.get(dataVo);
		
		//사원 프로필 첨부파일 조회
		UserFileVo fileVo = new UserFileVo();
		fileVo.setUserSeq(userSeq);
		fileVo = userService.getAttach(fileVo);
		
		JSONObject obj = new JSONObject();
		if(dataVo != null){
			obj.put("userId", dataVo.getUserId());
			obj.put("userHp", dataVo.getUserHp());
			obj.put("userEmail", dataVo.getUserEmail());
			if(fileVo != null){
				obj.put("saveNm", fileVo.getSaveNm());
			}else{
				obj.put("saveNm", "");
			}
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
	    sb.append(obj);
	    res.setContentType("json");
	    res.setCharacterEncoding("UTF-8");
	    PrintWriter out = null;
	    out = res.getWriter();
	    out.write(sb.toString());
	    out.flush();
	    out.close();
	}
	
	/**
	 * 개인정보 수정 처리
	 */
	public void userEdit(CommonVo paramVo) throws Exception{
		String resultMsg = "";
		try
		{
			this.proc(paramVo, "userEdit");
		}
		catch(PortalServiceException e)
		{
			resultMsg = e.getMessage();
		}
		catch(Exception e)
		{
			log.error(getStackTrace(e));
			throw e;
		}
		
		if ( "".equals(resultMsg)){
			String beforeUrl = getCurrentRequest().getHeader("Referer").toString();
			alert("정상적으로 수정되었습니다.", "location.href = '"+beforeUrl+"';");
			return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
	/**
	 * 비밀번호 수정 처리
	 */
	public void userEditPw(CommonVo paramVo) throws Exception{
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			if ( "".equals(paramVo.getUserPw())){
				throw new PortalServiceException("암호를 입력해주세요.");
			}
			
			UserVo mvo = this.getUserVo();
			UserVo userVo = new UserVo();
			
			userVo.setUserSeq(mvo.getUserSeq());
			userVo.setRegId(mvo.getUserId());
			userVo.setUserPw(paramVo.getUserPw());
			userService.updateUserPw(userVo);
			
			StringBuffer sb = new StringBuffer();
			HttpServletResponse res = getCurrentResponse();
			sb.append("1");
		    res.setContentType("text");
		    res.setCharacterEncoding("UTF-8");
		    PrintWriter out = null;
		    out = res.getWriter();
		    out.write(sb.toString());
		    out.flush();
		    out.close();
		}catch(Exception e){
			log.error(getStackTrace(e));
			throw e;
		}
	}
	
	/**
	 * 추가/수정/삭제 공통
	 */
	private void proc(CommonVo paramVo, String mode) throws Exception{
		String resultMsg ="";
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			//validation(mode);
			
			UserVo mvo = this.getUserVo();
			
			if ( "userEdit".equals(mode) ){
				UserVo userVo = new UserVo();
				long userSeq = this.getUserVo().getUserSeq();
				userVo.setUserSeq(userSeq);
				userVo = userService.get(userVo);			//시퀀스 값으로 select
				
//				userVo.setUserPw(paramVo.getUserPw());
				userVo.setUserHp(paramVo.getUserHp());
				userVo.setUserEmail(paramVo.getUserEmail());
				
				userService.updatePop(userVo);
				
				//파일 업로드
				UserFileVo paramFileVo = new UserFileVo();
				String fileUploadPath = "";
				String filePath = getUploadRoot() + "/user";
				fileUploadPath = context.getRealPath(filePath);
				
				MbUploadForSpring upload = new MbUploadForSpring();
				upload.setRepositoryPath(fileUploadPath);
				upload.setUseDateFolder(true);
				upload.init();
				List<FileInfoVO> fileInfoList = upload.upload();
				int rst = 0;
				if ( fileInfoList == null || fileInfoList.isEmpty()){
					log.debug("업로드 파일이 없습니다.");
				}else{
					paramFileVo.setUserSeq(userSeq);
					paramFileVo.setFolder(fileInfoList.get(0).getFolder());
					paramFileVo.setNm(fileInfoList.get(0).getName());
					paramFileVo.setSaveNm(fileInfoList.get(0).getSaveName());
					paramFileVo.setExt(fileInfoList.get(0).getExt());
					paramFileVo.setMimeTy(fileInfoList.get(0).getMimeType());
					paramFileVo.setSize(fileInfoList.get(0).getSize());
					paramFileVo.setFieldNm(fileInfoList.get(0).getFieldName());
					rst = userService.edtAttach(paramFileVo);
				}
				
			}
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			log.error(getStackTrace(e));
			throw e;
		}
	}
	
	//파일 다운로드
	public void fileDownload(CommonVo paramVo) throws Exception{
		UserVo mvo = this.getUserVo();
		long userSeq = mvo.getUserSeq();
		UserFileVo paramFileVo = new UserFileVo();
		paramFileVo.setUserSeq(userSeq);
		paramFileVo = userService.getAttach(paramFileVo);			//시퀀스 값으로 select
		
		//파일 다운로드
		String uploadWebRoot = getUploadRoot() + "/user";
		String fileUploadPath = context.getRealPath(uploadWebRoot);
		if ( paramVo != null ){
			String fileNm = paramFileVo.getNm();
			if ( !"".equals(fileNm) ){
				String folder = paramFileVo.getFolder();
				String file = fileUploadPath + File.separator + folder + File.separator + fileNm;
				log.debug(file);
				
				getCurrentResponse().setContentType(paramFileVo.getMimeTy());
				getCurrentResponse().setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(paramFileVo.getNm(), "UTF-8"));
				
				FileHandler.downView(getCurrentResponse(), file);
			}
		}
	}
	
	/**
	 * 이름 검색
	 */
	public void userList(CommonVo paramVo) throws Exception
	{
		List<UserVo> userList = new ArrayList<UserVo>();
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			if (!"".equals(paramVo.getSearchWord().trim())){	
				UserVo userVo = new UserVo();
				userVo.setSearchWord(paramVo.getSearchWord());
				userList = userService.getUserList(userVo);			//시퀀스 값으로 select
			}
		}catch(Exception e){
			log.error(e.getStackTrace());
		}
		
		JSONObject obj = null;
		JSONArray jsonList = new JSONArray();
		if(userList != null){
			for(int i=0; i < userList.size(); i++){
				obj = new JSONObject();
				obj.put("userNm", userList.get(i).getUserNm());
				obj.put("userHp", userList.get(i).getUserHp());
				
				jsonList.put(obj);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
	    sb.append(jsonList);
	    res.setContentType("json");
	    res.setCharacterEncoding("UTF-8");
	    PrintWriter out = null;
	    out = res.getWriter();
	    out.write(sb.toString());
	    out.flush();
	    out.close();
	}
}
