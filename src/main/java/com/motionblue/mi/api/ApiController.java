package com.motionblue.mi.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import portal.common.exception.PortalServiceException;
import portal.common.util.JsonUtil;
import portal.common.util.RequestHelperForSpring;

import com.motionblue.mi.code.CodeService;
import com.motionblue.mi.code.CodeVo;
import com.motionblue.mi.common.PortalBaseController;
import com.motionblue.mi.login.NoAuthCheck;
import com.motionblue.mi.login.NoLoginCheck;
import com.motionblue.mi.user.UserVo;
@Controller
public class ApiController extends PortalBaseController{
	
	private static Logger log = Logger.getLogger(ApiController.class);

	/**
	 * API 서비스
	 */
	@Autowired
	protected ApiService apiService;
	
	@Autowired
	protected CodeService codeService;
	
	/**
	 * API 목록
	 */
	@RequestMapping("api/{ServicePath}/list")
	@NoLoginCheck
	@NoAuthCheck
	@ResponseBody
	public void list(ApiVo paramVo, Model model, @PathVariable(value="ServicePath") String ServicePath) throws Exception{
		String resultMsg = "";
		try{
			RequestHelperForSpring.getParamDecode(paramVo);
			JsonUtil jsonUtil = new JsonUtil();
			
			if ( "code".equals(ServicePath) ){
				List<CodeVo> codeList = new ArrayList<CodeVo>();//모니터링 구분
				log.debug("하위 데이터     start 2");
				CodeVo codeVo = new CodeVo();
				codeVo.setUseYn("Y");
				UserVo loginUserVo = this.getUserVo();	
				//paramVo.setActId(loginUserVo.getUserId());
				codeVo.setCdUp("G000");
				codeList = codeService.getSList(codeVo);//모니터링 구분 조회
				
				jsonUtil.add("list", codeList);
			}else if ( "appdown".equals(ServicePath) ){
				List<AppDownVo> appDownList = new ArrayList<AppDownVo>();
				appDownList = this.apiService.selectAppList(paramVo);
				int totalCount = this.apiService.selectAppCount(paramVo);
				
				jsonUtil.add("appList", appDownList);
				jsonUtil.add("totalSize", totalCount);
			}
			
			printJson(jsonUtil.getJson());

		}catch(PortalServiceException pse){
			resultMsg = pse.getMessage();
		}catch(Exception e){
			throw e;
		}

	}
	
}
