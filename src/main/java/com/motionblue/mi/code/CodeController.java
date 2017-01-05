package com.motionblue.mi.code;

import java.util.ArrayList;
import java.util.List;

import com.motionblue.mi.common.PortalBaseController;
import com.motionblue.mi.user.UserVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import portal.common.exception.PortalServiceException;
import portal.common.security.seed.SeedUtil;
import portal.common.util.RequestHelperForSpring;
import portal.common.util.StringUtil;
@Controller
public class CodeController extends PortalBaseController{
	
	private static Logger log = Logger.getLogger(CodeController.class);

	/**
	 * 코드 서비스
	 */
	@Autowired
	protected CodeService codeService;
	
	/**
	 * 코드 목록
	 */
	public String list(CodeVo paramVo, Model model) throws Exception{
		int numbering = 0;
		int totalCount = 0;
		
		String resultMsg = "";
		
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		List<CodeVo> lvList = new ArrayList<CodeVo>();
		
		try{
			RequestHelperForSpring.getParameter(paramVo);
			
			totalCount = codeService.getCount(paramVo);
			codeList = codeService.getList(paramVo);
			lvList = codeService.getList2(paramVo);
		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}
		
		model.addAttribute("numbering", numbering);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("codeList", codeList);
		model.addAttribute("lvList", lvList);
		
		return path;
	}
	
	public String input(CodeVo paramVo, Model model)
	{
		CodeVo codeVo = new CodeVo();
		CodeVo codeVo2 = new CodeVo();
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			
			if (paramVo.getCdSeq() > 0){
				codeVo = codeService.get(paramVo);
				codeVo2.setCdUp(codeVo.getCd());
				codeList = codeService.getSList(codeVo2);
			}
			
			model.addAttribute("codeVo", codeVo);
			model.addAttribute("codeList", codeList);
		}catch(Exception e){
			log.error(e.getStackTrace());
		}
		
		return path;
	}
	
	/**
	 * 추가/수정/삭제 공통
	 */
	private void proc(CodeVo paramVo, String mode) throws Exception{

		RequestHelperForSpring.getParamDecode(paramVo);
		validation(paramVo, mode);

		UserVo mvo = this.getUserVo();
		
		paramVo.setActId(mvo.getUserId());
		log.debug(paramVo.toString());
		
		if ( "add".equals(mode) ){
			codeService.add(paramVo);
		}else if ( "edt".equals(mode) ){
			codeService.update(paramVo);
		}else if ( "remove".equals(mode) ){
			codeService.remove(paramVo);
		}

	}
	/**
	 * 추가 처리
	 */
	public void add(CodeVo paramVo, Model model) throws Exception{
		String resultMsg = "";
		try
		{
			this.proc(paramVo, "add");
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
	public void edt(CodeVo paramVo, Model model) throws Exception{
		String resultMsg = "";
		try
		{
			this.proc(paramVo, "edt");
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
		moveParam.append("h-commCdSeq=");
		moveParam.append(SeedUtil.encryptStr(String.valueOf(paramVo.getCdSeq())));
		moveParam.append("&searchItem=");
		moveParam.append(paramVo.getSearchItem());
		moveParam.append("&searchWord=");
		moveParam.append(paramVo.getSearchWord());
		moveParam.append("&searchCodeLv=");
		moveParam.append(paramVo.getSearchCdLv());
		moveParam.append("&gotoPage=");
		moveParam.append(paramVo.getGotoPage());
		
		if ( "".equals(resultMsg)){
			alert("정상적으로 수정되었습니다.", "location.href='./input.do?" + moveParam.toString() + "';");
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
	public void remove(CodeVo paramVo, Model model)throws Exception{
		
		String resultMsg = "";
		try
		{
			this.proc(paramVo, "remove");
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
		moveParam.append("&searchCodeLv=");
		moveParam.append(paramVo.getSearchCdLv());
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
	
	
	private void validation(CodeVo paramVo, String mode) throws Exception
	{
		if("add".equals(mode) || "edt".equals(mode))
		{
			if("".equals(paramVo.getCd().trim()))
			{
				throw new PortalServiceException("코드를 입력해 주십시오.");
			}
			log.debug("StringUtil.getStrLength(paramVo.getCd().trim()) : " + StringUtil.getStrLength(paramVo.getCd().trim()));
			if(StringUtil.getStrLength(paramVo.getCd().trim()) > 6)
			{
				throw new PortalServiceException("코드 값이 너무 큽니다.");
			}
			if("".equals(paramVo.getCdNm().trim()))
			{
				throw new PortalServiceException("코드 명을 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getCdNm().trim()) > 50)
			{
				throw new PortalServiceException("코드 명 값이 너무 큽니다.");
			}
			if(StringUtil.getStrLength(paramVo.getCdNm().trim()) > 6)
			{
				throw new PortalServiceException("상위코드 값이 너무 큽니다.");
			}
			if(paramVo.getCdLv() <= 0)
			{
				throw new PortalServiceException("코드레벨을 입력해 주십시오.");
			}
			if(paramVo.getCdOrd() <= 0)
			{
				throw new PortalServiceException("정렬순서를 입력해 주십시오.");
			}
			if("".equals(paramVo.getCdEtc().trim()))
			{
				throw new PortalServiceException("비고를 입력해 주십시오.");
			}
			if(StringUtil.getStrLength(paramVo.getCdEtc().trim()) > 256)
			{
				throw new PortalServiceException("비고 값이 너무 큽니다.");
			}
			
			String [] lowCd =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCd");
			String [] lowCdNm =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCdNm");
			String [] lowCdUp =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCdUp");
			String [] lowCdLv =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCdLv");
			String [] lowCdOrd =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCdOrd");
			String [] lowUseYn =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowUseYn");
			String [] lowCdEtc =  RequestHelperForSpring.getParameters(getCurrentRequest(), "lowCdEtc");
			String strLowCd = "";
			String strLowCdNm = "";
			String strLowCdUp = "";
			int intLowCdLv = 0;
			int intLowCdOrd = 0;
			//String strLowUseYn = "";
			String strLowCdEtc = "";
			if(lowCd != null)
			{
				for(int i = 0 ; i < lowCd.length ; i++)
				{
					strLowCd = lowCd[i];
					strLowCdNm = lowCdNm[i];
					strLowCdUp = lowCdUp[i];
					if("".equals(lowCdLv[i]))
					{
						intLowCdLv = 0;
					}
					else
					{
						intLowCdLv = Integer.parseInt(lowCdLv[i]);						
					}
					if("".equals(lowCdOrd[i]))
					{
						intLowCdOrd = 0;
					}
					else
					{
						intLowCdOrd = Integer.parseInt(lowCdOrd[i]);
					}
					//strLowUseYn = lowUseYn[i];
					strLowCdEtc = lowCdEtc[i];
					
					if("".equals(strLowCd.trim()))
					{
						throw new PortalServiceException("하위 코드의 코드를 입력해 주십시오.");
					}
					if(StringUtil.getStrLength(strLowCd.trim()) > 6)
					{
						throw new PortalServiceException("하위 코드의 코드 값이 너무 큽니다.");
					}
					if("".equals(strLowCdNm.trim()))
					{
						throw new PortalServiceException("하위 코드의 코드 명을 입력해 주십시오.");
					}
					if(StringUtil.getStrLength(strLowCdNm.trim()) > 50)
					{
						throw new PortalServiceException("하위 코드의 코드 명 값이 너무 큽니다.");
					}
					if("".equals(strLowCdUp.trim()))
					{
						throw new PortalServiceException("하위 코드의 상위코드를 입력해 주십시오.");
					}
					if(StringUtil.getStrLength(strLowCdUp.trim()) > 6)
					{
						throw new PortalServiceException("하위 코드의 상위코드 값이 너무 큽니다.");
					}
					if(intLowCdLv <= 0)
					{
						throw new PortalServiceException("하위 코드의 코드레벨을 입력해 주십시오.");
					}
					if(intLowCdOrd <= 0)
					{
						throw new PortalServiceException("하위 코드의 정렬순서를 입력해 주십시오.");
					}
					if("".equals(strLowCdEtc.trim()))
					{
						throw new PortalServiceException("하위 코드의 비고를 입력해 주십시오.");
					}
					if(StringUtil.getStrLength(strLowCdEtc.trim()) > 256)
					{
						throw new PortalServiceException("하위 코드의 비고 값이 너무 큽니다.");
					}
				}
				String lowCdList = StringUtil.serializeString(lowCd);
				paramVo.setLowCdList(lowCdList);
				log.debug("lowCdList : " + paramVo.getLowCdList());
				String lowCdNmList = StringUtil.serializeString(lowCdNm);
				paramVo.setLowCdNmList(lowCdNmList);
				log.debug("lowCdNmList : " + paramVo.getLowCdNmList());
				String lowCdUpList = StringUtil.serializeString(lowCdUp);
				paramVo.setLowCdUpList(lowCdUpList);
				log.debug("lowCdUpList : " + paramVo.getLowCdUpList());
				String lowCdLvList = StringUtil.serializeString(lowCdLv);
				paramVo.setLowCdLvList(lowCdLvList);
				log.debug("lowCdLvList : " + paramVo.getLowCdLvList());
				String lowCdOrdList = StringUtil.serializeString(lowCdOrd);
				paramVo.setLowCdOrdList(lowCdOrdList);
				log.debug("lowCodeOrdList : " + paramVo.getLowCdOrdList());
				String lowUseYnList = StringUtil.serializeString(lowUseYn);
				paramVo.setLowUseYnList(lowUseYnList);
				log.debug("lowUseYnList : " + paramVo.getLowUseYnList());
				String lowCdEtcList = StringUtil.serializeString(lowCdEtc);
				paramVo.setLowCdEtcList(lowCdEtcList);
				log.debug("lowCdEtcList : " + paramVo.getLowCdEtcList());
			}
		}
	}
}
