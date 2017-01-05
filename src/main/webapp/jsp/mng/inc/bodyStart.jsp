<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var curMenuCd = "${menuCd}";
var lastDepth = curMenuCd.substring(3,4);

//alert("curMenuCd : " + curMenuCd);
$(function(){
	
	if(lastDepth != "0")
	{
		setMenuList(2);
		setMenuList(3);
	}
	else
	{
		setMenuList(2);
	}
	
	//상위 박스 클릭 이벤트
	$(".depth > span").each(function(){
		
		$(this).click(function(){
			var subMenu = $(this).parent().find("ul").attr("id");
			//alert(subMenu);
			if(subMenu == "subMenu1")
			{
				$("#subMenu2").hide();
				$("#subMenu3").hide();
			}
			else if(subMenu == "subMenu2")
			{
				$("#subMenu1").hide();
				$("#subMenu3").hide();
			}
			else
			{
				$("#subMenu1").hide();
				$("#subMenu2").hide();
			}

			$(this).parent().find("ul").slideToggle("fast");
		})
	});
	
	searchTotal();
	$("#autoOpen").click(function(){
		if($(this).attr("class").indexOf("active") > -1)
		{
			$(".ui-autocomplete").slideUp("fast");
			$("#autoOpen").removeClass("active");//화살표 방향 아래로 변경
		}
		else
		{
			$(".ui-autocomplete").slideDown("fast");
			$("#autoOpen").addClass("active");//화살표 방향 위로 변경
		}
	});
	$("#searchMntr").click(function(){
		$(".ui-autocomplete").slideToggle("fast", function(){
			if($(this).is(":hidden"))
			{
				$("#autoOpen").removeClass("active");//화살표 방향 아래로 변경
			}
			else
			{
				$("#autoOpen").addClass("active");//화살표 방향 위로 변경
			}
		});		
	});

	//navi 마지막 뎁스 볼드 처리
	var menuCnt = $('.selectArea:not([style*="display: none"])').length;
	$("#subMenu" + menuCnt).parent().parent().find("span > a").addClass("last");
});

function searchTotal()
{
	//모니터링 조회
	$("#searchMntr").autocomplete({
		
		minLength:1,/* focus 함수를 사용함으로 인해 minLength 값을 1 로 주어도됨.*/
		multiple:true,
        autoFocus: true,
        delay: 50, //실행 지연 시간 1000분 1초 단위
		source : function(request, response)
		{
			var mntrNm = $(this.element).val();
			$.ajax({
				url : __globalContextPath + "/common/totList.do",
				type : "post",
				dataType : "json",
				data : {
						"mntrNm" : mntrNm
				},
				success : function(data)
				{
					if(data.length > 0)
					{
						response($.map(data, function(item) {
							
	                         return {
	                          	label : item.mntrNm, 
	                          	mntrSeq : item.strMntrSeq,
	                          	mntrType : item.mntrType
	                         };
						}));
						
						var mntrTypeCls = "";
						var cnt = 0;
						
						$(".ui-menu-item").each(function(i){
							if(data[i].mntrType == "E001")
							{
								mntrTypeCls = "iconBrand";
							}
							else if(data[i].mntrType == "E002")
							{
								mntrTypeCls = "iconMarketing";
							}
							else if(data[i].mntrType == "E003")
							{
								mntrTypeCls = "iconModel";
							}
							else if(data[i].mntrType == "E004")
							{
								mntrTypeCls = "iconSocial";
							}
							$(this).find("a").addClass(mntrTypeCls)
						});
						
						$("#autoOpen").addClass("active");	//화살표 방향 위로 변경
					}
				},
				error : function(data)
				{
					alert("에러가 발생하였습니다.");	
				}
			});
		},
		select: function(event, ui) {
			var data = ui.item;

			
			if (event.keyCode == 13 || event.button == 0)
			{ 
				var url = "/dashboard/list.do?h-mntrSeq="+data.mntrSeq;
				if(data.mntrType == "E001")
				{
					url = __globalContextPath + "/brand"+url;
				}
				else if(data.mntrType == "E002")
				{
					url = __globalContextPath + "/market"+url;
				}
				else if(data.mntrType == "E003")
				{
					url = __globalContextPath + "/model"+url;
				}
				else if(data.mntrType == "E004")
				{
					url = __globalContextPath + "/social"+url;
				}
				location.href = url;
				$("#searchMntr").val(data.label);
				//$("#searchMntr").focus();
				
			}
			return false;
	    
	    },
		
        focus: function () {
             // prevent value inserted on focus 
             //자동완성 검색 키워를 입력하여 콤보박스 형태로 자동완성되었을때
             //Key Down, Key Up 으로 선택시 입력 Text 박스에 입력한 
             //자동완성 키워드가 리셋되는 형상을 방지 하기위해 return false 함.
             //focus: function () 을 사용하지 않거나 사용하되 return true 를 하면
             // 입력 Text 박스에 입력한 자동완성 키워드가 리셋 됨.
             return false;
        }
	    
	});
}

function setMenuList(depth)
{ 
	//현재 메뉴
	$(".menuList > li").each(function(){
		
		var menuCd = $(this).attr("id");
		var menuCd2 = "";
		var curMenuCd2 = "";
		var menuCd3 = "";
		var curMenuCd3 = "";
		var depthFlag = false;
		
		if(curMenuCd != null && menuCd != null && curMenuCd != "" && menuCd != "")
		{
			if(depth == "2")
			{
				menuCd2 = menuCd.substring(0,2);
				curMenuCd2 = curMenuCd.substring(0,2);
				
				if(menuCd2 == curMenuCd2)
				{					
					menuClick($(this), false);
				}
			}
			
			if(depth == "3")
			{
				menuCd3 = menuCd.substring(0,3);
				curMenuCd3 = curMenuCd.substring(0,3);
				
				if(menuCd3 == curMenuCd3)
				{					
					menuClick($(this), false);
				}
			}
		}
		$(this).click(function(){
			menuClick($(this), true);
		});
		
	});
	
}
//메뉴 클릭 이벤트
function menuClick(obj, urlCall)
{
	if(obj.parent().attr("id") == null || obj.parent().attr("id").indexOf("subMenu") < 0)
	{
		return false;	
	}
	var menuDepth = Number(obj.parent().attr("id").replace(/subMenu/g, '')) + 1; 
	$("#subMenu"+menuDepth).parent().parent().find("span > a").text("");
	//alert(menuDepth);
	//alert(obj.find("a").text());
	obj.parent().parent().find("span > a").text(obj.find("a").text());
	obj.parent().hide("fast");
	var menuUrl = obj.find("input").val(); 
	getSubMenuList(obj.attr("id"), menuDepth, menuUrl, urlCall);
	
}

function getSubMenuList(selMenuCd, menuDepth, menuUrl, urlCall)
{
	//alert("selMenuCd : " + selMenuCd + " || menuDepth : " + menuDepth);
	var url = __globalContextPath + "/common/subMenuList.do";
	var cnt = 0;
    var menuCdUp = "";
	//alert(selMenuCd);	
	if(selMenuCd == null || selMenuCd == "")
	{
		menuCdUp = curMenuCd.substring(0,Number(menuDepth));	
	}
	else
	{
		menuCdUp = selMenuCd.substring(0,Number(menuDepth));	
	}
	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			"menuCdUp" : menuCdUp
			,"menuDepth" : menuDepth
		},
		async:false,
		dataType : "json",
		success : function(data) {
			var subMenu = $("#subMenu"+menuDepth);
			subMenu.html("");
			
			cnt = data.length;
			if(cnt > 0)
			{
				for(var i = 0 ; i < cnt ; i++)
				{
					//2차 범위 제외
					/*
					if(data[i].menuCd == "MA40")//1:1문의
					{
						continue;	
					}
					*/
					//조회계정의 경우 LSCM 제외
					if(("<%=_userCls%>" == "C003" && data[i].menuCd == "M450")|| ("<%=_userCls%>" == "C003" && data[i].menuCd == "M340"))
					{
						continue;
					}
					//2차 범위 제외, 위기관리 설정(조회계정의 경우) 제외
					<%--
					if("<%=_userCls%>" == "C003" && data[i].menuCd == "MA20")
					{
						continue;
					}
					--%>
					//alert(data[i].menuCd + "==" + curMenuCd);
					$("#subMenu"+menuDepth).append(
						$("<li>").attr("id", data[i].menuCd).append(
							$("<a>").attr("href", "#none").text(data[i].menuNm)
							,$("<input>").attr("type", "hidden")
										 .attr("name", "menuUrl"+menuDepth)
										 .attr("id", "menuUrl"+menuDepth+i).val(data[i].url)
						).click(function(){
							menuClick($(this), true);
						})
						
					);
					if(data[i].menuCd == curMenuCd)
					{
						subMenu.parent().parent().find("span > a").text(data[i].menuNm);
					}
				}

				subMenu.parent().parent().show();
				
				var selectMenuNm = subMenu.parent().parent().find("span > a").text();
				//alert(selectMenuNm);
				//alert(subMenu.parent().parent().find("span > a").text() + " || " + );
				if(selectMenuNm == null || selectMenuNm ==  "")
				{					
					subMenu.parent().parent().find("span > a").text(subMenu.find("a:first").text());
				}
				//subMenu.parent().parent().find("span > a").text(subMenu.find("a:first").text());
				//alert(menuDepth);
				//기본값으로 하위 메뉴 조회
				//if(menuDepth == "2" )
				//{
					//alert(subMenu.find("li:first").attr("id"));
					
					var subMenuCd = subMenu.find("li:first").attr("id");
					//var menuUrl = subMenu.find("li:first > input").val(); 
					//alert("subMenuCd : " + subMenuCd);
					//alert("menuUrl : " + menuUrl);
					//alert("subMenuCd : " + subMenuCd);
					if(subMenuCd != null && subMenuCd != "")
					{						
						getSubMenuList(subMenuCd, Number(menuDepth)+1, '', urlCall);
					}
				//}
			}
			else
			{	
				if(menuUrl != null && menuUrl != "" && urlCall)
				{		
					goMenu(menuUrl);
				}
				subMenu.parent().parent().hide();	
				//if(menuDepth == "2")
				//{
					$("#subMenu"+(Number(menuDepth)+1)).parent().parent().hide();	
				//}
			}
			subMenu.hide();	
		},
		failure : function(data) {
			alert("조회가 실패했습니다.");
			return ;
		}
	});

}

function goMenu(menuUrl)
{	
	location.href = __globalContextPath + menuUrl;	
}
</script>

</head>
<body>

	<span id="skipNav">
		<a href="#container">본문바로가기</a>
		<a href="#contentBox">대메뉴바로가기</a>
	</span>

	<!-- Header -->
	<header id="subHeader">
		<div class="conset">
			<h1><a href="<%=_globalContextPath %>/"><img src="<%=_globalContextPath %>/images/common/logo.png" alt="DAEHONG CUMMUNICATIONS"></a></h1>
			<div class="location">
				<span class="home">home</span>
				<div class="selectArea">
					<span class="bar" id="bar1">&gt;</span>
					<div class="depth" >
						<span class="tit"><a href="#none">브랜드 인사이트</a></span>
						<ul class="list menuList" id="subMenu1" style="display:none;">
							<c:forEach items="${permitMenuList }" var="item" varStatus="sts">
							<li id="${item.menuCd}">
								<a href="#none">${item.menuNm}</a>
								<input type="hidden" name="menuUrl1" id="menuUrl1${sts.count}" value="${item.url}" />
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="selectArea">
					<span class="bar" id="bar2" >&gt;</span>
	
					<div class="depth" >
						<span class="tit"><a href="#none"></a></span>
						<ul class="list menuList" id="subMenu2" style="display:none;">
							
						</ul>
					</div>
				</div>	
				<div class="selectArea">
					<span class="bar" id="bar3">&gt;</span>
				
					<div class="depth" >
						<span class="tit"><a href="#none"></a></span>
						<ul class="list menuList" id="subMenu3" style="display:none;">
							
						</ul>
					</div>
				</div> 
			</div>

			<form class="searchForm" name="" method="get" action="" onsubmit="return false;">
				<div class="searchArea">
					<section class="searchBox">
						<fieldset>
							<legend>브랜드명 및 모델명 검색</legend>
							<input type="text" name="searchMntr" id="searchMntr" value="" class="keyword" placeholder="브랜드명 및 모델명 입력" title="브랜드명 및 모델명 입력" />
							<a href="#none" class="arrow"  id="autoOpen"><span>자동완성 펼치기</span></a>
							<span class="bar"></span>
							<!-- <a href="#none" class="btnSearch"><span>검색</span></a> -->
							<!-- <button type="submit" class="btnSearch" title="검색"><span>검색</span></button> -->
							<input type="button" name="" value="" class="btnSearch" title="검색">
							<!-- 
							<div class="autoText">
								<ul>
									<li><span class="catecory"><img src="../../images/common/icon_b.png" alt="브랜드"></span><a href="#none">피파온라인3</a></li>
									<li><span class="catecory"><img src="../../images/common/icon_i.png" alt="브랜드"></span><a href="#none">피파온라인2</a></li>
									<li><span class="catecory"><img src="../../images/common/icon_m.png" alt="브랜드"></span><a href="#none">피파</a></li>
									<li><span class="catecory"><img src="../../images/common/icon_s.png" alt="브랜드"></span><a href="#none">피온3</a></li>
									<li><span class="catecory"><img src="../../images/common/icon_s.png" alt="브랜드"></span><a href="#none">피온3</a></li>
								</ul>
							</div>
							-->
						</fieldset>
					</section>
				</div>
			</form>
			
			<div class="util">
				<span class="account"><span><%=_userNm %></span>님 안녕하세요!</span>
				<ul>
					<li class="logout"><a href="./doLogout.do"><span class="icon"></span>로그아웃</a></li>
					<li class="mypage">
						<a href="#none"><span class="icon"></span>마이페이지</a>
						<div class="utilBox">
							<ul>
								<li><a href="<%=_globalContextPath %>/mypage/mntr/list.do">모니터링 설정</a></li>
								<%--
								<c:if test="${_userCls ne 'C003'}"><!-- 2차 개발 추가 예정 -->
								 --%>
								<li><a href="<%=_globalContextPath %>/mypage/risk/list.do">위기관리 설정</a></li>
								<%--
								</c:if>
								 --%>
								<li><a href="<%=_globalContextPath %>/mypage/info/input.do">내 정보변경</a></li>
								<!-- 2차 범위
								 -->
								<li><a href="<%=_globalContextPath %>/mypage/qna/list.do">1:1 문의</a></li>
							</ul>
						</div>
					</li>
					<!-- 2차 범위
					 -->
					<li class="customer"><a href="#none"><span class="icon"></span>고객지원</a>
						<div class="utilBox">
							<ul>
								<li><a href="<%=_globalContextPath %>/bbs/notice/list.do">공지사항</a></li>
								<li><a href="<%=_globalContextPath %>/bbs/faq/list.do">FAQ</a></li>
								<li><a href="<%=_globalContextPath %>/bbs/qna/list.do">1:1 문의</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>

		</div>
	</header>
	<hr/>
	<!-- // Header -->

	<!-- Container -->
	<div id="container">
		<div id="contentBox">
			<div class="conset">
				<!-- Navigation -->
				<c:set var="oneDepth" value="${fn:substring(menuCd,0,2)}" />
				<c:set var="twoDepth" value="${fn:substring(menuCd,0,3)}" />
				<c:set var="threeDepth" value="${fn:substring(menuCd,0,4)}" />
				<nav class="lnbArea">
					<ul class="lnb">
					<!-- 권한 체크 -->
					<c:forEach items="${permitMenuList }" var="item" varStatus="sts">
						<c:if test="${item.menuCd eq 'M100'}">
						<li class="tit brand<c:if test="${oneDepth eq 'M1'}"> cur</c:if>"><a href="#none"><span class="icon"></span>브랜드 인사이트</a>
							<ul class="depth">
								<li <c:if test="${twoDepth eq 'M11'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/dashboard/list.do" >대시보드</a></li>
								<li <c:if test="${twoDepth eq 'M12'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/buzz/list.do">Buzz 분석</a></li>
								<li <c:if test="${twoDepth eq 'M13'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/keyword/list.do">키워드 분석</a></li>
								<li ><a href="#none" style="cursor:default;">감성 분석</a>
									<ul>
										<li <c:if test="${threeDepth eq 'M141'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/sensibilities/site/list.do" ><span class="bar">&minus;</span>채널별 긍&middot;부정 분석</a></li>
										<li <c:if test="${threeDepth eq 'M142'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/sensibilities/express/list.do" ><span class="bar">&minus;</span>감성어 목록</a></li>
									</ul>
								</li>
								<li <c:if test="${twoDepth eq 'M15'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/brand/influencer/list.do">영향력자 관리</a></li>
							</ul>
						</li>
						</c:if>
						<c:if test="${item.menuCd eq 'M200'}">
						<li class="tit marketing<c:if test="${oneDepth eq 'M2'}"> cur</c:if>"><a href="#none"><span class="icon"></span>마케팅 인사이트</a>
							<ul class="depth">
								<li <c:if test="${twoDepth eq 'M21'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/dashboard/list.do" >대시보드</a></li>
								<li <c:if test="${twoDepth eq 'M22'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/buzz/list.do">Buzz 심화 분석</a></li>
								<li <c:if test="${twoDepth eq 'M23'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/keyword/list.do">키워드 심화 분석</a></li>
								<li><a href="#none" style="cursor:default;">감성 심화 분석</a>
									<ul>
										<li <c:if test="${threeDepth eq 'M241'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/sensibilities/position/list.do"><span class="bar">&minus;</span>포지셔닝 맵</a></li>
										<li <c:if test="${threeDepth eq 'M242'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/sensibilities/site/list.do" ><span class="bar">&minus;</span>채널별 긍&middot;부정 분석</a></li>
										<li <c:if test="${threeDepth eq 'M243'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/market/sensibilities/express/list.do"><span class="bar">&minus;</span>감성어 목록</a></li>
									</ul>
								</li>
							</ul>
						</li>
						</c:if>
						<c:if test="${item.menuCd eq 'M300'}">
						<li class="tit model<c:if test="${oneDepth eq 'M3'}"> cur</c:if>"><a href="#none"><span class="icon"></span>모델 인사이트</a>
							<ul class="depth">
								<li <c:if test="${twoDepth eq 'M31'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/model/dashboard/list.do" >대시보드</a></li>
								<li <c:if test="${twoDepth eq 'M32'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/model/repute/list.do">모델평판 분석</a></li>
								<li <c:if test="${twoDepth eq 'M33'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/model/rank/list.do">모델순위 분석</a></li>
								<c:if test="${_userCls ne 'C003'}"><!-- 조회계정은 보이지 않게 한다. -->
								<li <c:if test="${twoDepth eq 'M34'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/model/jdata/list.do">정량데이터 조사</a></li>
								</c:if>
							</ul>
						</li>
						</c:if>
						<c:if test="${item.menuCd eq 'M400'}">
						<li class="tit social<c:if test="${oneDepth eq 'M4'}"> cur</c:if>"><a href="#none"><span class="icon"></span>소셜 인사이트</a>
							<ul class="depth">
								<li <c:if test="${twoDepth eq 'M41'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/social/dashboard/list.do">대시보드</a></li>
								<li <c:if test="${twoDepth eq 'M42'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/social/media/list.do">소셜미디어 분석</a></li>
								<li <c:if test="${twoDepth eq 'M43'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/social/effect/list.do">디지털 캠페인 효과분석</a></li>
								<li <c:if test="${twoDepth eq 'M44'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/social/profile/list.do" >소셜 프로파일 관리</a></li>
								<c:if test="${_userCls ne 'C003'}"><!-- 조회계정은 보이지 않게 한다. -->
								<li <c:if test="${twoDepth eq 'M45'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/social/lscm/list.do">LSCM</a></li>
								</c:if>
							</ul>
						</li>
						</c:if>
						<c:if test="${item.menuCd eq 'M500'}">
						<li class="tit monitoring<c:if test="${oneDepth eq 'M5'}"> cur</c:if>"><a href="#none"><span class="icon"></span>실시간 모니터링</a>
							<ul class="depth">
								<li <c:if test="${twoDepth eq 'M51'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/realtime/buzzkeyword/list.do">실시간 Buzz&middot;키워드</a></li>
								<li <c:if test="${twoDepth eq 'M52'}">class="cur"</c:if>><a href="<%=_globalContextPath %>/realtime/scmntr/list.do">소셜 모니터링</a></li>
							</ul>
						</li>
						</c:if>			
						<c:if test="${item.menuCd eq 'M600'}">
						<li class="tit realTimeKeyword <c:if test="${oneDepth eq 'M6'}"> cur</c:if>"><a href="<%=_globalContextPath %>/realtimekeyword/list.do"><span class="icon"></span>실시간 급상승 키워드</a></li>
						</c:if>
					</c:forEach>
					</ul>
					<ul class="etc">
					<%if("C001".equals(_userCls) || "C002".equals(_userCls)){ %>
						<li class="manage01<c:if test="${oneDepth eq 'MC'}"> cur</c:if>"><a href="<%=_globalContextPath %>/mng/user/list.do"><span class="icon"></span>계정 관리</a></li>
						<li class="manage02<c:if test="${oneDepth eq 'MD'}"> cur</c:if>"><a href="<%=_globalContextPath %>/mng/dept/list.do"><span class="icon"></span>부서 관리</a></li>
						<li class="manage03<c:if test="${oneDepth eq 'ME'}"> cur</c:if>"><a href="<%=_globalContextPath %>/mng/sc/list.do"><span class="icon"></span>소셜 인사이트 카테고리 관리</a></li>
					<%} %>	
					<!-- 2차 범위
					 -->
						<li class="manualDown"><a href="<%=_globalContextPath %>/manualDownload.do"><span class="icon"></span>매뉴얼 다운로드 </a></li>
					</ul>
				</nav>
				<!-- // Navigation -->
				<hr/>
