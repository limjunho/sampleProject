package com.motionblue.mi.user;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.motionblue.mi.common.PortalBaseController;
import com.motionblue.mi.login.NoAuthCheck;
import com.motionblue.mi.login.NoLoginCheck;
import com.motionblue.mi.user.UserVo;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import portal.common.PortalProperty;
import portal.common.exception.PortalServiceException;
import portal.common.model.FileInfoVO;
import portal.common.util.FileHandler;
import portal.common.util.MbUploadForSpring;
import portal.common.util.RequestHelperForSpring;
import portal.common.util.StringUtil;
@Controller
public class UserController extends PortalBaseController{
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	protected UserVo paramVo = new UserVo();
	protected UserFileVo paramFileVo = new UserFileVo();
	private List<UserVo> userList = null;
	private List<UserVo> organList = null;
	private List<UserVo> jgList = null;
	private List<UserVo> jcList = null;
	private UserVo userVo = new UserVo();
	
	public UserFileVo getParamFileVo() {
		return paramFileVo;
	}
	public void setParamFileVo(UserFileVo paramFileVo) {
		this.paramFileVo = paramFileVo;
	}
	public UserVo getParamVo() {
		return paramVo;
	}
	public List<UserVo> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public List<UserVo> getOrganList() {
		return organList;
	}
	public void setOrganList(List<UserVo> organList) {
		this.organList = organList;
	}
	public List<UserVo> getJgList() {
		return jgList;
	}
	public void setJgList(List<UserVo> jgList) {
		this.jgList = jgList;
	}
	public List<UserVo> getJcList() {
		return jcList;
	}
	public void setJcList(List<UserVo> jcList) {
		this.jcList = jcList;
	}


	/**
	 * 사용자 서비스
	 */
	@Autowired
	protected UserService userService;
	
	
	/**
	 * 로그인 처리 공통
	 * @return
	 */
	protected String doLoginProc(UserVo paramVo) throws Exception{
		String resultMsg = "";
		UserVo resultVo = null;
		try{
			RequestHelperForSpring.getParameter(paramVo);
			
			resultVo = userService.txLogin(paramVo);
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		// 세션 생성.
		if ( StringUtil.isEmpty(resultMsg) ){
			loginService.doLogin(resultVo);
		}

		return resultMsg;
	}
	
	/**
	 * 일반 로그인 - 처리 후 페이지 이동.
	 */
	@RequestMapping("doLogin")
	@NoLoginCheck
	@NoAuthCheck
	@ResponseBody 	//return 값이 필요없는 경우
	public void doLogin(UserVo paramVo) throws Exception{
		
		String additionalMsg = "";
		String rurl = "";
		String loginReturnUrl = "";

		// 로그인 시도
		String resultMsg = this.doLoginProc(paramVo);
		if ( !StringUtil.isEmpty(resultMsg)){
			// 로그인 실패 처리.
			alertHistoryBackMsg(resultMsg);
		}
		rurl = (String)getCurrentRequest().getParameter("rurl") == null ? "" : (String)getCurrentRequest().getParameter("rurl");
		if("".equals(rurl)){
			loginReturnUrl = this.getLoginReturnUrl();
	
			rurl = loginReturnUrl;	
		}
		
		// 리턴 URL 검증 - 보안 적용 대상.
		checkReturnUrl(rurl);

		log.debug(resultMsg);
		if ( StringUtil.isEmpty(resultMsg)){
			rurl = StringUtil.isNull(rurl);
			
			if ( "".equals(rurl) ){
				String servicePort = PortalProperty.get("SERVICE_PORT");
				String host = getCurrentRequest().getServerName();
				host = ( "80".equals(servicePort) ) ? host : (host + ":" + servicePort);
				
				rurl = "http://" + host + getContextPath() + "/";
			}
			
			log.debug("========================= rurl : " + rurl);
			if ( "".equals(additionalMsg) ){
				log.debug("사용자 아이디: " + paramVo.getUserId());
				if("Y".equals(paramVo.getChkId()))
				{
					RequestHelperForSpring.setCookie(getCurrentResponse(), "savedId", paramVo.getUserId(), (60*60*24*1000));
				}
				//return "redirect:"+rurl;
				getCurrentResponse().sendRedirect(rurl);
			}
			else
			{				
				alert(additionalMsg, "location.href='" + rurl + "';");
			}
		}
	}
	
	
	/**
	 * 로그아웃 처리
	 */
	@RequestMapping("doLogout")
	@NoLoginCheck
	@NoAuthCheck
	@ResponseBody
	public void doLogout(){
		super.doLogout();
		
		try{
			alert("안전하게 로그아웃 되었습니다.", "location.href='" + getContextPath() + "/';");
			// response.sendRedirect(getContextPath() + "/");
		}catch(Exception e){
			log.error(getStackTrace(e));
		}
	}

	
	/**
	 * 로그인 화면 처리
	 */

	@RequestMapping("login")
	@NoLoginCheck
	@NoAuthCheck
	public String login(HttpSession session, UserVo paramVo, Model model){
		path = "login";
		log.info("Welcome login! {}" + session.getId());
		return path;
	}
	
	
	/**
	 * 사용자 목록
	 */
	public String list() throws Exception{
		int numbering = 0;
		int totalCount = 0;
		int nowCnt = 0;
		int pageSize = 10;
		
		String resultMsg = "";
		
//		List<UserVo> lvList = new ArrayList<UserVo>();
		
		try{
			RequestHelperForSpring.getParameter(paramVo);
			if(paramVo.getSearchItem() == null){
				paramVo.setSearchItem("");
			}
			if(paramVo.getSearchWord() == null){
				paramVo.setSearchWord("");
			}
			if(paramVo.getPageSize() != 0){  	// 한화면에 뿌려줄 리스트 갯수가 0가 아닌 다른 지정값이 있으면 변수에 저장
				pageSize = paramVo.getPageSize(); 
			}

			totalCount = userService.getCount(paramVo);			//총 사용자 수 ( 페이징 용 )
			
			
			userList = userService.getList(paramVo);			//리스트 셀렉트
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		return path;
	}
	/**
	 * 등록화면
	 */
	public String input()
	{
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			//기본적으로 셀렉트 해올 데이터1 (모든 조직)
			organList = userService.getOrganList(paramVo);
			//기본적으로 셀렉트 해올 데이터2 (모든 직책)
			jcList = userService.getJcList(paramVo);
			//기본적으로 셀렉트 해올 데이터3 (모든 직급)
			jgList = userService.getJgList(paramVo);
			
		}catch(Exception e){
			log.error(e.getStackTrace());
		}
		
		return path;
	}
	/**
	 * 상세 화면
	 */
	public String view()
	{
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			if (paramVo.getUserSeq() > 0){						
				userVo = userService.get(paramVo);			//시퀀스 값으로 select
				paramFileVo.setUserSeq(paramVo.getUserSeq());
				paramFileVo = userService.getAttach(paramFileVo);			//시퀀스 값으로 select
			}
		}catch(Exception e){
			log.error(e.getStackTrace());
		}
		
		return path;
	}
	/**
	 * 수정 화면
	 */
	public String modify()
	{
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			//기본적으로 셀렉트 해올 데이터1 (모든 조직)
			organList = userService.getOrganList(paramVo);
			//기본적으로 셀렉트 해올 데이터2 (모든 직책)
			jcList = userService.getJcList(paramVo);
			//기본적으로 셀렉트 해올 데이터3 (모든 직급)
			jgList = userService.getJgList(paramVo);
			
			if (paramVo.getUserSeq() > 0){						//수정화면일때 조직 상세 정보를 가져옴
				userVo = userService.get(paramVo);			//시퀀스 값으로 select
			}
		}catch(Exception e){
			log.error(e.getStackTrace());
		}
		
		return path;
	}

	
	// 이메일주소 유효성체크
	public static boolean isValidEmail(String email) {
		  boolean err = false;
		  String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";   
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(email);
		  if(m.matches()) {
		   err = true; 
		  }
		  return err;
	}
	
	/**
	 * 자동 사용자번호 부여 Ajax
	 */
	public void autoNewCompanyNum() throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		String rst = "";
		String lastNum = userService.getLastNum(paramVo);			//해당 조직의 마지막 사용자번호 조회
		int temp;
		try{
			if(lastNum != null && !"".equals(lastNum)){
				lastNum = lastNum.substring(2);
				temp = Integer.parseInt(lastNum) + 1;
				
				String strNum = Integer.toString(temp);
				
				while(strNum.length() < 4){
					strNum = "0"+strNum;
				}
				/*
				if("JC0005".equals(paramVo.getUserDr())){	//프리랜서 전용 사용자번호
					rst = "ME"+strNum;
				}else{
					rst = "MB"+strNum;
				}
				*/
			}
		}catch(NumberFormatException e){
			rst = "";
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
		sb.append(rst);
		res.setContentType("text");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = res.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * ID 중복 체크 Ajax
	 */
	public void idCheck() throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		String rst = "able";
		int idCheck = userService.getIdCheck(paramVo);			//해당 조직의 마지막 사용자번호 조회
		if(idCheck > 0){
			rst = "disable";
		}
		
		StringBuffer sb = new StringBuffer();
		HttpServletResponse res = getCurrentResponse();
		sb.append(rst);
		res.setContentType("text");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = res.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 추가/수정/삭제 공통
	 */
	private void proc(String mode) throws Exception{

		RequestHelperForSpring.getParamDecode(paramVo);
		validation(mode);

		UserVo mvo = this.getUserVo();
		
		paramVo.setRegId(mvo.getUserId());
		log.debug(paramVo.toString());
		long userSeq = 0;
		if ( "add".equals(mode) ){
			UserVo tempParamVo = new UserVo();
			tempParamVo = userService.add(paramVo);
			userSeq = tempParamVo.getUserSeq();
		}
		else if ( "edt".equals(mode) ){
			userService.update(paramVo);
			userSeq = paramVo.getUserSeq();
		}
//			else if ( "remove".equals(mode) ){
//			codeService.remove(paramVo);
//		}
		
		//파일 업로드
		String fileUploadPath = "";
		String filePath = getUploadRoot() + "/user";
		String resultMsg = "";
		try{
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
				if ( "add".equals(mode) ){
					rst = userService.addAttach(paramFileVo);
				}else if( "edt".equals(mode) ){
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
	
	
	/**
	 * 추가 처리
	 */
	public void add() throws Exception{
		String resultMsg = "";
		try
		{
			this.proc("add");
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
			alert("정상적으로 등록되었습니다.", "location.href='./list.do'");
			return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);
		}
	}
	/**
	 * 수정 처리
	 */
	public void edt() throws Exception{
		String resultMsg = "";
		try
		{
			this.proc("edt");
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
		StringBuffer moveParam = new StringBuffer();
		moveParam.append("userSeq=");
		moveParam.append(paramVo.getUserSeq());
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 수정되었습니다.", "location.href='./view.do?" + moveParam.toString() + "';");
			return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
	/**
	 * 삭제 처리
	 */
	public void remove()throws Exception{
		
		String resultMsg = "";
		try
		{
			this.proc("remove");
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
		StringBuffer moveParam = new StringBuffer();
		moveParam.append("searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 삭제되었습니다.", "location.href='./list.do?" + moveParam.toString() + "';");
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
	//파일 다운로드
	public void fileDownload() throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		paramFileVo.setUserSeq(paramVo.getUserSeq());
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
	
	private void validation(String mode) throws Exception
	{
		if("add".equals(mode))
		{
			// 기본사항
			if("".equals(paramVo.getUserNm().trim()))
			{
				throw new PortalServiceException("성명을 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getUserNm()) > 20)
			{
				throw new PortalServiceException("성명이 너무 큽니다.");
			}
			if("".equals(paramVo.getUserId().trim()))
			{
				throw new PortalServiceException("ID를 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getUserId()) > 20)
			{
				throw new PortalServiceException("ID가 너무 큽니다.");
			}
			if("".equals(paramVo.getUserPw().trim()))
			{
				throw new PortalServiceException("비밀번호를 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getUserPw()) > 20)
			{
				throw new PortalServiceException("비밀번호가 너무 큽니다.");
			}
			//세부사항
			if(StringUtil.getStrLength(paramVo.getUserHp()) > 13)
			{
				throw new PortalServiceException("휴대폰번호 값이 너무 큽니다.");
			}

			if(StringUtil.getStrLength(paramVo.getUserEmail()) > 80)
			{
				throw new PortalServiceException("개인메일 값이 너무 큽니다.");
			}
		}
		
		if("edt".equals(mode))
		{
			// 기본사항
			if("".equals(paramVo.getUserNm().trim()))
			{
				throw new PortalServiceException("성명을 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getUserNm()) > 20)
			{
				throw new PortalServiceException("성명이 너무 큽니다.");
			}
			if("".equals(paramVo.getUserPw().trim()))
			{
				throw new PortalServiceException("비밀번호를 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getUserPw()) > 20)
			{
				throw new PortalServiceException("비밀번호가 너무 큽니다.");
			}
			
			//세부사항
			if(StringUtil.getStrLength(paramVo.getUserHp()) > 13)
			{
				throw new PortalServiceException("휴대폰번호 값이 너무 큽니다.");
			}
			if(StringUtil.getStrLength(paramVo.getUserEmail()) > 80)
			{
				throw new PortalServiceException("개인메일 값이 너무 큽니다.");
			}

		}
	}
	
    /**
	 * 언어 처리
	 */
	@RequestMapping("index")
	@NoLoginCheck
	@NoAuthCheck
	public String index(Model model){
		log.debug("lang : " + request.getParameter("lang"));
		path = "index";
		return path;
	}
	
	/**
	 * 비밀번호 등록
	 */
	@RequestMapping("updateUserPw")
	@NoLoginCheck
	@NoAuthCheck
	@ResponseBody
	public void updateUserPw(UserVo paramVo)throws Exception{
		
		String resultMsg = "";
		try
		{
			paramVo.setUserPw(passwordEncoder.encode(paramVo.getUserPw()));
			userService.updateUserPw(paramVo);
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
		StringBuffer moveParam = new StringBuffer();
		moveParam.append("userSeq=");
		moveParam.append(paramVo.getUserSeq());
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 수정되었습니다.", "location.href='./modifypw?" + moveParam.toString() + "';");
			return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
    /**
	 * 비밀번호 수정 화면
	 */
	@RequestMapping("modifypw")
	@NoLoginCheck
	@NoAuthCheck
	public String modifypw(Model model){
		log.debug("lang : " + request.getParameter("lang"));
		path = "modify";
		return path;
	}
	
    
    @RequestMapping(value="/signinDo", method=RequestMethod.GET)
    @ResponseBody
    public void signinDo() {
    	try{
			alert("안전하게 로그아웃 되었습니다.", "location.href='" + getContextPath() + "/';");
			// response.sendRedirect(getContextPath() + "/");
		}catch(Exception e){
			log.error(getStackTrace(e));
		}
   		log.info("sign~~~~");
    }
	
}
