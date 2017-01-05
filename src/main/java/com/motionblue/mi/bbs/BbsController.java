package com.motionblue.mi.bbs;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.motionblue.mi.common.PortalBaseController;
import com.motionblue.mi.user.UserService;
import com.motionblue.mi.user.UserVo;
import portal.common.util.MbUploadForSpring;
import portal.common.util.RequestHelperForSpring;

import portal.common.exception.PortalServiceException;
import portal.common.model.FileInfoVO;
import portal.common.security.seed.SeedUtil;
import portal.common.util.FileHandler;

@Controller
public class BbsController extends PortalBaseController{

	@Autowired
	protected BbsService bbsService;
	
	@Autowired
	protected UserService userService;
	
	protected BbsFileVo paramBbsFileVo = new BbsFileVo();
	protected List<BbsVo> bbsVoList = new ArrayList<BbsVo>();
	protected List<BbsFileVo> bbsFileVoList = new ArrayList<BbsFileVo>();
	protected List<BbsVo> bbsUserCheckList = new ArrayList<BbsVo>();
	
	public BbsFileVo getParamBbsFileVo() {
		return paramBbsFileVo;
	}

	public void setParamBbsFileVo(BbsFileVo paramBbsFileVo) {
		this.paramBbsFileVo = paramBbsFileVo;
	}

	public List<BbsVo> getBbsVoList() {
		return bbsVoList;
	}

	public void setBbsVoList(List<BbsVo> bbsVoList) {
		this.bbsVoList = bbsVoList;
	}

	public List<BbsFileVo> getBbsFileVoList() {
		return bbsFileVoList;
	}

	public void setBbsFileVoList(List<BbsFileVo> bbsFileVoList) {
		this.bbsFileVoList = bbsFileVoList;
	}

	public List<BbsVo> getBbsUserCheckList() {
		return bbsUserCheckList;
	}

	public void setBbsUserCheckList(List<BbsVo> bbsUserCheckList) {
		this.bbsUserCheckList = bbsUserCheckList;
	}

	protected String bbsPath = "bbs/";
	protected String bbsMngPath = "mng/bbs/";
	
	@RequestMapping("bbs/{ServicePath}/list")
	public String list(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{

		int numbering = 0;
		int totalCount = 0;

		UserVo loginUserVo = this.getUserVo();
		
		RequestHelperForSpring.getParameter(paramVo);
		paramVo.setBbsInfoId(ServicePath.toUpperCase());
		if(loginUserVo != null){			
			paramVo.setUserId(loginUserVo.getUserId());
		}
		totalCount = bbsService.getCount(paramVo);
		List<BbsVo> bbsVoList = bbsService.getList(paramVo);
		
		numbering = this.getStartNumbering(totalCount, paramVo.getGotoPage(), paramVo.getPageSize());

		model.addAttribute("bbsVoList", bbsVoList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("numbering", numbering);
		
		path = bbsPath + ServicePath + "/list";
		
		return path;
	}
	
	@RequestMapping("mng/bbs/{ServicePath}/list")
	public String listMng(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		
		int numbering = 0;
		int totalCount = 0;
		
		UserVo loginUserVo = this.getUserVo();
		
		RequestHelperForSpring.getParameter(paramVo);
		paramVo.setBbsInfoId(ServicePath.toUpperCase());
		if(loginUserVo != null){			
			paramVo.setUserId(loginUserVo.getUserId());
		}
		totalCount = bbsService.getCount(paramVo);
		List<BbsVo> bbsVoList = bbsService.getList(paramVo);
		
		numbering = this.getStartNumbering(totalCount, paramVo.getGotoPage(), paramVo.getPageSize());
		
		model.addAttribute("bbsVoList", bbsVoList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("numbering", numbering);
		
		path = bbsMngPath + ServicePath + "/list";
		
		return path;
	}
	
	@RequestMapping("bbs/{ServicePath}/input")
	public String input(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		
		UserVo mvo = this.getUserVo();
		
		UserVo vo = new UserVo();
		vo.setUserSeq(mvo.getUserSeq());
		UserVo userVo = userService.get(vo);
		
		BbsVo bbsVo = new BbsVo();
		List<BbsFileVo> bbsFileList = new ArrayList<BbsFileVo>(); 
		
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		if(paramVo.getBbsSeq() > 0){
			bbsVo = bbsService.get(paramVo);
			date = bbsVo.getRegDt();
			bbsFileList = bbsService.getFileList(paramVo);
		}
		
		model.addAttribute("userVo", userVo);
		model.addAttribute("bbsVo", bbsVo);
		model.addAttribute("date", date);
		model.addAttribute("bbsFileList", bbsFileList);

		path = bbsPath + ServicePath + "/input";
		return path;
	}
	
	@RequestMapping("mng/bbs/{ServicePath}/input")
	public String inputMng(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		
		UserVo mvo = this.getUserVo();
		
		UserVo vo = new UserVo();
		vo.setUserSeq(mvo.getUserSeq());
		UserVo userVo = userService.get(vo);
		
		BbsVo bbsVo = new BbsVo();
		List<BbsFileVo> bbsFileList = new ArrayList<BbsFileVo>(); 
		
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		if(paramVo.getBbsSeq() > 0){
			bbsVo = bbsService.get(paramVo);
			date = bbsVo.getRegDt();
			bbsFileList = bbsService.getFileList(paramVo);
		}
		
		model.addAttribute("userVo", userVo);
		model.addAttribute("bbsVo", bbsVo);
		model.addAttribute("date", date);
		model.addAttribute("bbsFileList", bbsFileList);

		path = bbsMngPath + ServicePath + "/input";
		return path;
	}
	
	@RequestMapping("bbs/{ServicePath}/view")
	public String view(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		
		
		String resultMsg = "";
		BbsVo viewBbsVo = new BbsVo();
		BbsVo userCheckCntVo = new BbsVo();
		BbsVo nextBbsVo = new BbsVo();
		BbsVo prevBbsVo = new BbsVo();
		
		UserVo loginUserVo = this.getUserVo();
		
		List<BbsVo> bbsCommentList = new ArrayList<BbsVo>();
		List<BbsFileVo> bbsFileList = new ArrayList<BbsFileVo>(); 
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			paramVo.setBbsInfoId(ServicePath.toUpperCase());
			paramVo.setUserId(loginUserVo.getUserId());
			viewBbsVo = bbsService.get(paramVo);						// 현재글 관련 정보
			bbsFileList = bbsService.getFileList(paramVo);			// 파일정보
			userCheckCntVo = bbsService.getUserCheckCnt(paramVo);	// 확인, 미확인 사람수 정보
			bbsCommentList = bbsService.getCommentList(paramVo);	// 댓글리스트
			nextBbsVo = viewBbsVo.getNextBbsVo();						// 다음글 관련 정보
			prevBbsVo = viewBbsVo.getPrevBbsVo();						// 이전글 관련 정보
			
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		model.addAttribute("loginUserVo", loginUserVo);
		model.addAttribute("bbsVo", viewBbsVo);
		model.addAttribute("userCheckCntVo", userCheckCntVo);
		model.addAttribute("bbsFileList", bbsFileList);
		model.addAttribute("bbsCommentList", bbsCommentList);
		model.addAttribute("nextBbsVo", nextBbsVo);
		model.addAttribute("prevBbsVo", prevBbsVo);
		
		path = bbsPath + ServicePath + "/view";
		return path;
	}
	
	@RequestMapping("mng/bbs/{ServicePath}/view")
	public String viewMng(BbsVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		
		
		String resultMsg = "";
		BbsVo viewBbsVo = new BbsVo();
		BbsVo userCheckCntVo = new BbsVo();
		BbsVo nextBbsVo = new BbsVo();
		BbsVo prevBbsVo = new BbsVo();
		
		UserVo loginUserVo = this.getUserVo();
		
		List<BbsVo> bbsCommentList = new ArrayList<BbsVo>();
		List<BbsFileVo> bbsFileList = new ArrayList<BbsFileVo>(); 
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			paramVo.setBbsInfoId(ServicePath.toUpperCase());
			paramVo.setUserId(loginUserVo.getUserId());
			viewBbsVo = bbsService.get(paramVo);						// 현재글 관련 정보
			bbsFileList = bbsService.getFileList(paramVo);			// 파일정보
			userCheckCntVo = bbsService.getUserCheckCnt(paramVo);	// 확인, 미확인 사람수 정보
			bbsCommentList = bbsService.getCommentList(paramVo);	// 댓글리스트
			nextBbsVo = viewBbsVo.getNextBbsVo();						// 다음글 관련 정보
			prevBbsVo = viewBbsVo.getPrevBbsVo();						// 이전글 관련 정보
			
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		model.addAttribute("loginUserVo", loginUserVo);
		model.addAttribute("bbsVo", viewBbsVo);
		model.addAttribute("userCheckCntVo", userCheckCntVo);
		model.addAttribute("bbsFileList", bbsFileList);
		model.addAttribute("bbsCommentList", bbsCommentList);
		model.addAttribute("nextBbsVo", nextBbsVo);
		model.addAttribute("prevBbsVo", prevBbsVo);
		
		path = bbsMngPath + ServicePath + "/view";
		return path;
	}
	
	/**
	 * 확인 리스트 조회 Ajax
	 */
	@RequestMapping("bbs/{ServicePath}/userCheckListAjax")
	@ResponseBody
	public void userCheckListAjax(BbsVo paramVo, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		
		bbsVoList = bbsService.getUserCheckList(paramVo);
		
		JSONObject obj = null;
		JSONArray jsonList = new JSONArray();
		
		if(bbsVoList != null){
			for(int i=0; i<bbsVoList.size(); i++){
				obj = new JSONObject();
				obj.put("userNm", bbsVoList.get(i).getUserNm());
				obj.put("userClsNm", bbsVoList.get(i).getUserCls());
				obj.put("regDt", bbsVoList.get(i).getRegDt());
				
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
	 * 미확인 리스트 조회 Ajax
	 */
	@RequestMapping("bbs/{ServicePath}/userUncheckListAjax")
	@ResponseBody
	public void userUncheckListAjax(BbsVo paramVo, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		RequestHelperForSpring.getParamDecode(paramVo);
		
		bbsVoList = bbsService.getUserUncheckList(paramVo);
		
		JSONObject obj = null;
		JSONArray jsonList = new JSONArray();
		
		if(bbsVoList != null){
			for(int i=0; i<bbsVoList.size(); i++){
				obj = new JSONObject();
				obj.put("userNm", bbsVoList.get(i).getUserNm());
				obj.put("userClsNm", bbsVoList.get(i).getUserCls());
				
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
	 * 추가/수정/삭제 공통
	 */
	private void proc(BbsVo paramVo, MultipartFile[] files, String mode) throws Exception{

		RequestHelperForSpring.getParamDecode(paramVo);
		validation(paramVo, mode);
		
		UserVo mvo = this.getUserVo();
		
		paramVo.setRegId(mvo.getUserId());
		log.debug(paramVo.toString());
		int bbsSeq;
		if ( "add".equals(mode) ){
			BbsVo bbsVo = bbsService.add(paramVo);
			bbsSeq = bbsVo.getBbsSeq();
			
			//파일 업로드
			String fileUploadPath = "";
			String filePath = getUploadRoot() + "/bbs";
			String resultMsg = "";
			try{
				fileUploadPath = context.getRealPath(filePath);
				
				MbUploadForSpring upload = new MbUploadForSpring();
				upload.setRepositoryPath(fileUploadPath);
				upload.setUseDateFolder(true);
				upload.init();
				List<FileInfoVO> fileInfoList = upload.upload(files, "");
				int rst = 0;
				if ( fileInfoList == null || fileInfoList.isEmpty()){
					log.debug("업로드 파일이 없습니다.");
				}else{

					for(int i=0; i<fileInfoList.size(); i++)
					{
						paramBbsFileVo.setBbsSeq(bbsSeq);
						paramBbsFileVo.setFolder(fileInfoList.get(i).getFolder());
						paramBbsFileVo.setNm(fileInfoList.get(i).getName());
						paramBbsFileVo.setSaveNm(fileInfoList.get(i).getSaveName());
						paramBbsFileVo.setExt(fileInfoList.get(i).getExt());
						paramBbsFileVo.setMimeTy(fileInfoList.get(i).getMimeType());
						paramBbsFileVo.setSize(fileInfoList.get(i).getSize());
						paramBbsFileVo.setFieldNm(fileInfoList.get(i).getFieldName());
						rst = bbsService.addAttach(paramBbsFileVo);
					}
				}
			}catch(PortalServiceException pse){
				resultMsg = pse.getMessage();
			}catch(Exception e){
				log.error(getStackTrace(e));
				throw e;
			}
		}else if ( "edt".equals(mode) ){
			bbsService.update(paramVo);
			
			try{
				String bbsAttachSeqs = paramVo.getBbsAttachSeqs();
				if(bbsAttachSeqs != null && bbsAttachSeqs.length() > 0){
					String[] seqs = bbsAttachSeqs.split(",");
					for(int i=0; i<seqs.length; i++){
						paramBbsFileVo.setBbsAttachSeq(Integer.parseInt(seqs[i]));
						bbsService.removeAttach(paramBbsFileVo);
					}
				}
			}catch(Exception e){
				log.error(getStackTrace(e));
			}
			
			bbsSeq = paramVo.getBbsSeq();
			
			//파일 업로드
			String fileUploadPath = "";
			String filePath = getUploadRoot() + "/bbs";
			String resultMsg = "";
			try{
				fileUploadPath = context.getRealPath(filePath);
				
				MbUploadForSpring upload = new MbUploadForSpring();
				upload.setRepositoryPath(fileUploadPath);
				upload.setUseDateFolder(true);
				upload.init();
				List<FileInfoVO> fileInfoList = upload.upload(files, "");
				int rst = 0;
				if ( fileInfoList == null || fileInfoList.isEmpty()){
					log.debug("업로드 파일이 없습니다.");
				}else{
					for(int i=0; i<fileInfoList.size(); i++){
						paramBbsFileVo.setBbsSeq(bbsSeq);
						paramBbsFileVo.setFolder(fileInfoList.get(i).getFolder());
						paramBbsFileVo.setNm(fileInfoList.get(i).getName());
						paramBbsFileVo.setSaveNm(fileInfoList.get(i).getSaveName());
						paramBbsFileVo.setExt(fileInfoList.get(i).getExt());
						paramBbsFileVo.setMimeTy(fileInfoList.get(i).getMimeType());
						paramBbsFileVo.setSize(fileInfoList.get(i).getSize());
						paramBbsFileVo.setFieldNm(fileInfoList.get(i).getFieldName());
						rst = bbsService.addAttach(paramBbsFileVo);
					}
				}
			}catch(PortalServiceException pse){
				resultMsg = pse.getMessage();
			}catch(Exception e){
				log.error(getStackTrace(e));
				//throw e;
			}
		}else if ( "remove".equals(mode) ){
			bbsService.remove(paramVo);
		}else if ( "addCmt".equals(mode) ){
			bbsService.addCmt(paramVo);
		}else if ( "edtCmt".equals(mode) ){
			paramVo.setBbsSeq(paramVo.getBbsCmtSeq());
			bbsService.edtCmt(paramVo);
		}else if ( "removeCmt".equals(mode) ){
			paramVo.setBbsSeq(paramVo.getBbsCmtSeq());
			bbsService.removeCmt(paramVo);
		}

	}
	/**
	 * 추가 처리
	 */
	@RequestMapping("bbs/{ServicePath}/add")
	@ResponseBody
	public void add(BbsVo paramVo, @RequestParam("file") MultipartFile[] files) throws Exception{
		log.debug("file size : " + files.length);
		String resultMsg = "";
		try
		{
			this.proc(paramVo, files, "add");
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
			//return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);
		}
	}
	/**
	 * 수정 처리
	 */
	@RequestMapping("bbs/{ServicePath}/edt")
	@ResponseBody
	public void edt(BbsVo paramVo, @RequestParam("file") MultipartFile[] files) throws Exception{
		String resultMsg = "";
		try
		{
	
			this.proc(paramVo, files, "edt");
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
		moveParam.append("h-bbsSeq=");
		moveParam.append(SeedUtil.encryptStr(String.valueOf(paramVo.getBbsSeq())));
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){

			alert("정상적으로 수정되었습니다.", "location.href='./view.do?" + moveParam.toString() + "';");
			//return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
	/**
	 * 삭제 처리
	 */
	@RequestMapping("bbs/{ServicePath}/remove")
	@ResponseBody
	public void remove(BbsVo paramVo)throws Exception{
		
		String resultMsg = "";
		try
		{
			this.proc(paramVo, null, "remove");
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

			alert("정상적으로 삭제되었습니다.", "location.href='./list.do?" + moveParam.toString() + "'");
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	/**
	 * 추가 처리
	 */
	@RequestMapping("bbs/{ServicePath}/addCmt")
	@ResponseBody
	public void addCmt(BbsVo paramVo) throws Exception{
		String resultMsg = "";
		try
		{
			this.proc(paramVo, null, "addCmt");
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
		moveParam.append("h-bbsSeq=");
		moveParam.append(SeedUtil.encryptStr(String.valueOf(paramVo.getBbsSeq())));
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 등록되었습니다.", "location.href='./view.do?" + moveParam.toString() + "';");
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
	@RequestMapping("bbs/{ServicePath}/edtCmt")
	@ResponseBody
	public void edtCmt(BbsVo paramVo) throws Exception{
		String resultMsg = "";
		int tmpBbsSeq = paramVo.getBbsSeq();
		try
		{
			this.proc(paramVo, null, "edtCmt");
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
		moveParam.append("h-bbsSeq=");
		moveParam.append(SeedUtil.encryptStr(String.valueOf(tmpBbsSeq)));
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
	@RequestMapping("bbs/{ServicePath}/removeCmt")
	@ResponseBody
	public void removeCmt(BbsVo paramVo)throws Exception{
		
		String resultMsg = "";
		int tmpBbsSeq = paramVo.getBbsSeq();
		try
		{
			this.proc(paramVo, null, "removeCmt");
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
		moveParam.append("h-bbsSeq=");
		moveParam.append(SeedUtil.encryptStr(String.valueOf(tmpBbsSeq)));
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 삭제되었습니다.", "location.href='./view.do?" + moveParam.toString() + "';");
			return;
		}
		else
		{
			this.alertHistoryBackMsg(resultMsg);			
		}
	}
	
	//파일 다운로드
	@RequestMapping({"fileDownload","**/fileDownload/","**/**/fileDownload"})
	@ResponseBody
	public void fileDownload(BbsFileVo paramBbsFileVo) throws Exception{
		
		RequestHelperForSpring.getParamDecode(paramBbsFileVo);
		
		//파일 다운로드
		String uploadWebRoot = getUploadRoot() + "/bbs";
		String fileUploadPath = context.getRealPath(uploadWebRoot);
		if ( paramBbsFileVo != null ){
			String fileNm = paramBbsFileVo.getSaveNm();
			if ( !"".equals(fileNm) ){
				String folder = paramBbsFileVo.getFolder();
				String file = fileUploadPath + File.separator + folder + File.separator + fileNm;
				log.debug(file);
				
				getCurrentResponse().setContentType(paramBbsFileVo.getMimeTy());
				getCurrentResponse().setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(paramBbsFileVo.getNm(), "UTF-8"));
				
				FileHandler.downView(getCurrentResponse(), file);
			}
		}
	}
	
	private void validation(BbsVo paramVo, String mode) throws Exception
	{
		if("add".equals(mode) || "edt".equals(mode))
		{
//			if("".equals(paramVo.getCd().trim()))
//			{
//				throw new PortalServiceException("코드를 입력해 주십시오.");
//			}
//			log.debug("StringUtil.getStrLength(paramVo.getCd().trim()) : " + StringUtil.getStrLength(paramVo.getCd().trim()));
//			if(StringUtil.getStrLength(paramVo.getCd().trim()) > 6)
//			{
//				throw new PortalServiceException("코드 값이 너무 큽니다.");
//			}
//			if("".equals(paramVo.getCodeNm().trim()))
//			{
//				throw new PortalServiceException("코드 명을 입력해 주십시오.");
//			}
//			if(StringUtil.getStrLength(paramVo.getCodeNm().trim()) > 50)
//			{
//				throw new PortalServiceException("코드 명 값이 너무 큽니다.");
//			}
//			if(StringUtil.getStrLength(paramVo.getUpCode().trim()) > 6)
//			{
//				throw new PortalServiceException("상위코드 값이 너무 큽니다.");
//			}
//			if(paramVo.getCodeLv() <= 0)
//			{
//				throw new PortalServiceException("코드레벨을 입력해 주십시오.");
//			}
//			if(paramVo.getCodeOrd() <= 0)
//			{
//				throw new PortalServiceException("정렬순서를 입력해 주십시오.");
//			}
//			if("".equals(paramVo.getCodeEtc().trim()))
//			{
//				throw new PortalServiceException("비고를 입력해 주십시오.");
//			}
//			if(StringUtil.getStrLength(paramVo.getCodeEtc().trim()) > 256)
//			{
//				throw new PortalServiceException("비고 값이 너무 큽니다.");
//			}
			
		}
	}
}
