package com.motionblue.mi.login.intercept;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.motionblue.mi.common.CommonService;
import com.motionblue.mi.common.CommonVo;
import com.motionblue.mi.common.MenuVo;
import com.motionblue.mi.login.LoginService;
import com.motionblue.mi.login.NoAuthCheck;
import com.motionblue.mi.user.UserService;
import com.motionblue.mi.user.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthManagerIntercept extends HandlerInterceptorAdapter{

	private static Logger log = Logger.getLogger(AuthManagerIntercept.class);

	@Autowired
	protected LoginService loginService;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected CommonService commonService;
	
	private String defaultUrl = "";	// 이동 전 url 저장

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	/**
	 * 메뉴 권한 체크 인터셉트
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		NoAuthCheck usingAuth = null;
		if(handler instanceof HandlerMethod)
		{
			usingAuth = ((HandlerMethod) handler).getMethodAnnotation(NoAuthCheck.class);
		}
		
		if(usingAuth == null)
		{
			String nowUrl = request.getRequestURL().toString();
			log.debug("================== goUrl : "+ nowUrl + " ==================");
			
			
			UserVo userVo = (UserVo) loginService.getUserVo();
			UserVo paramVo = new UserVo();
			String msg = "권한이 없습니다.";
			if(userVo != null){						// 로그인 한사람이 없으면 그냥 무시됨 (이미 로그인 체크에서 걸림)
				log.debug("========================= 사용자 등급 : " + userVo.getUserCls() + "======================");
				long userSeq = userVo.getUserSeq();
				paramVo.setUserSeq(userSeq);
				
				//마스터 계정은 모두 볼수 있음
				if("C003".equals(userVo.getUserCls()))
				{
					List<MenuVo> depthMenuList = commonService.getMenuCodeList();
					
					List<MenuVo> permitList = commonService.getPermitList(userSeq);

					boolean authCheck = false;
					boolean serviceMenuChk = false;
					
					//계정관리, 부서관리, 소셜미디어 카테고리 관리
					if(depthMenuList != null && depthMenuList.size() > 0)
					{
						for(int i = 0 ; i < depthMenuList.size(); i++)
						{
							MenuVo vo = depthMenuList.get(i);
							if(nowUrl.indexOf(vo.getUrl()) > -1)
							{
								serviceMenuChk = true;
							}
						}
					}
					
					if(serviceMenuChk)
					{
						if(permitList != null && permitList.size() > 0)
						{
							for(int i = 0 ; i < permitList.size(); i++)
							{
								MenuVo vo = permitList.get(i);
								if(nowUrl.indexOf(vo.getUrl()) > -1)
								{
									authCheck = true;
								}
							}
						}
						
					}
					//예외 사항 lscm, 정량데이터 조사 화면은 조회계정이 접근 불가능하다.
					if(nowUrl.indexOf("/social/lscm/") > -1 || nowUrl.indexOf("/model/jdata/") > -1)
					{
						authCheck = false;
					}
					
					//고객센터 조회계정은 쓰기, 삭제, 수정 권한이 없음
					if(nowUrl.indexOf("/bbs/") > -1)
					{
						if(nowUrl.indexOf("/input.do") > -1 || nowUrl.indexOf("/remove.do") > -1 || nowUrl.indexOf("/edt.do") > -1 || nowUrl.indexOf("/add.do") > -1)
						{
							authCheck = false;
						}
					}
					
					if(!authCheck){		// 권한 체크 실패시 얼럿 및 history back
						StringBuffer sb = new StringBuffer();
						sb.append("<script type=\"text/javascript\" >");
						sb.append("alert('"+msg+"');");
						sb.append("history.back();");
						sb.append("</script>");
						
						response.setCharacterEncoding("utf-8");
						response.setContentType("text/html;charset:UTF-8");
						response.setHeader("Cache-Control", "no-cache");
						PrintWriter out = response.getWriter();
						out.println(sb.toString());
						out.flush();
						out.close();
						
						return false;
					}
					
				
				}
				
				if(nowUrl.indexOf("/mypage/info/input.do") > -1)
				{
					String chkPw = (String)request.getSession().getAttribute("chkPw");
					log.info("chkPw = " + chkPw);
					if(chkPw == null || !chkPw.equals("t"))
					{			
						response.sendRedirect("./inputPw.do");
						return false;
					}
					request.getSession().setAttribute("chkPw", "f");
				}
				//임시 범위
				/*
				if( nowUrl.indexOf("model/rank/list.do") > -1 )
				{
					StringBuffer sb = new StringBuffer();
					sb.append("<script type=\"text/javascript\" >");
					sb.append("alert('서비스 준비중입니다.');");
					sb.append("history.back();");
					sb.append("</script>");
					
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset:UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					PrintWriter out = response.getWriter();
					out.println(sb.toString());
					out.flush();
					out.close();
					
					return null;
				}
				*/
				if( nowUrl.indexOf("/mng/code/") > -1 )
				{
					//관리 운영자만 접속 가능하게 한다.
					if(!"C001".equals(userVo.getUserCls()))
					{
						
						StringBuffer sb = new StringBuffer();
						sb.append("<script type=\"text/javascript\" >");
						sb.append("alert('"+msg+"');");
						sb.append("history.back();");
						sb.append("</script>");
						
						response.setCharacterEncoding("utf-8");
						response.setContentType("text/html;charset:UTF-8");
						response.setHeader("Cache-Control", "no-cache");
						PrintWriter out = response.getWriter();
						out.println(sb.toString());
						out.flush();
						out.close();
						
						return false;
					}
					
				}
			}
		}
		return true;
	}
	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor{postHandle}............................................ end");
	}
	*/
}
