<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/mng/inc/headerStart.jsp" %>
<%-- XSS 및 Link Injection 대응 --%>
${ paramVo.setSearchWord(pu:noHtml(paramVo.searchWord)) }
<c:set var="paramHtml" value="searchItem=${paramVo.searchItem}&amp;searchItem1=${paramVo.searchItem1}" />
<c:set var="paramStr" value="searchItem=${paramVo.searchItem}&searchItem1=${paramVo.searchItem1}" />
<c:set var="menuCd" value="MB20" />
<script type="text/javascript">
	$(document).ready(function() {
		
		if( "${paramVo.searchItem1}" != ""){			
			onSelect("${paramVo.searchItem1}");			
		}else{			
		}
	});
	
	
	function doInput()
	{
		location.href = "./input.do?${paramStr}&gotoPage=${paramVo.gotoPage}";
	}
	
	
	//수정화면 이동
	function doEdit1(bbsSeq,s1,s2)
	{
		var a = "document.frmEdit_"+bbsSeq;	
		var frm = a;
	
// 		if(confirm("수정화면으로 이동하시겠습니까?")){
// 			frm.submit();
			location.href = "./input.do?${paramStr}&gotoPage=${paramVo.gotoPage}&h-bbsSeq="+bbsSeq;
// 		}
	}
	function doDelete(bbsSeq)
	{
		if(!confirm("게시물을 삭제 하시겠습니까?"))
		{
			return false;	
		}
		location.href = "./remove.do?${paramStr}&gotoPage=${paramVo.gotoPage}&h-bbsSeq="+bbsSeq;
	}

	function onSelectChange(val){		
		document.frmSearch.submit();		
	}
	
	
	function onSelect(val) {	
		
		$.ajax({
			type : "post",
			url: "./deptslist.do",
			dataType : "json",
			data : {				
				"cdUp" : val
			},
			async:false,
			success : function(data) {
				var list = data.list;
				$("#searchItem2 option").remove();
				var html ='';
				var chv ="";
				if(list.length > 0 ){
					$("#searchItem2").prop("disabled","");	
					html += '<option value="" >카테고리를 선택하세요</option>'
					for(i=0;i<list.length;i++){
						var cdval = list[i].cd;					
						var vv = "${paramVo.searchItem2}";
						if(vv == cdval){
							chv = list[i].cdNm;
							html += '<option value="'+cdval+'" id="checkedboxVal" selected=\'selected\' >'+list[i].cdNm+'</option>';	
							
						}else{
							html += '<option value="'+cdval+'" >'+list[i].cdNm+'</option>';
						}							
					}
					$("#searchItem2").append(html);		
					$("#searchItem2").parent().find(".selecttext04").html(chv);	
					if(chv == ""){
						$("#searchItem2").parent().find(".selecttext04").html("카테고리를 선택하세요");
					}else{
						$("#searchItem2").parent().find(".selecttext04").html(chv);	
					}
										
				}else{
					html += '<option value="" >카테고리를 선택하세요</option>';
					$("#searchItem2").prop("disabled","disabled");
					$("#searchItem2").append(html);

				}
			},
			failure : function (data) {
				alert("에러입니다.");
			}
		})
		
	}
	function onSelectChangedept2(){
// 		$("#searchItem2").prop("disabled","");
		document.frmSearch.submit();
	}
	
	
	
	
	
</script>
<%@ include file="/jsp/mng/inc/bodyStart.jsp" %>
		<!-- Container -->
	
		<!-- Contents -->
		<div id="contents">
			<div class="contArea">
				<h2 class="hidden">고객지원</h2>
				<h3 class="hidden">FAQ</h3>
				<div class="topArea">
					<div class="titleArea">
						<h4 class="title">FAQ</h4>
					</div>
				</div>

				<!-- FAQ -->
				<section class="mypagewrap">
					<div class="titleArea">
					</div>
					<div class="mypageCon">
<!-- 								<form name="" method="" action=""> -->
<!-- 									<fieldset> -->
<!-- 										<legend>FAQ 검색</legend> -->
<!-- 											<div class="searchBar03"> -->
<!-- 												<select name="" class="selectType04"> -->
<!-- 													<option selected="selected">카테고리를 선택하세요</option> -->
<!-- 												</select> -->
<!-- 												<select name="" class="selectType04"> -->
<!-- 													<option selected="selected">카테고리를 선택하세요</option> -->
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 									</fieldset> -->
<!-- 								</form> -->
												
						<form id="frmSearch" name="frmSearch" action="./list.do" method="get">
							<fieldset>
								<legend>FAQ 검색</legend>
									<div class="searchBar03">
<!-- 												<select name="" class="selectType04"> -->
<!-- 													<option selected="selected">카테고리를 선택하세요</option> -->
<!-- 												</select> -->

										<select id="searchItem1" name="searchItem1" class="selectType04"  placeholder="카테고리를 선택하세요" onchange="javascript:onSelectChange(this.value); return false;"><!-- javascript:onSelectChange(this.value); return false alert(this.value); -->
											<option value="">카테고리를 선택하세요</option>
										<c:choose>
										<c:when test="${fn:length(codeList) > 0}">
											
<!-- 												<option value=""  >전체</option> -->
												<c:forEach var="cl1" items="${codeList}" varStatus="sts">
													<option value="${cl1.cd}" ${pu:isEquals(cl1.cd, paramVo.searchItem1, "selected='selected'") } >${cl1.cdNm}</option>
												</c:forEach>
										</c:when>		
										<c:otherwise>
										</c:otherwise>
										
										</c:choose>
										
										
<!-- 										private long cdSeq = 0; -->
<!-- 	private String cd = ""; -->
<!-- 	private String cdNm = ""; -->
<!-- 	private String cdUp = ""; -->
<!-- 	private int cdLv = 0; -->
<!-- 	private int cdOrd = 0; -->
<!-- 	private String cdEtc = ""; -->
<!-- 	private String useYn = ""; -->
<!-- 	private int searchCdLv = 0; -->
											
<%-- 											<option value="1" ${pu:isEquals("1", paramVo.searchItem, "selected='selected'") } >인트로</option> --%>
<%-- 											<option value="2" ${pu:isEquals("2", paramVo.searchItem, "selected='selected'") }>브랜드인사이드</option> --%>
<%-- 											<option value="3" ${pu:isEquals("3", paramVo.searchItem, "selected='selected'") }>마케팅인사이트</option> --%>
<%-- 											<option value="4" ${pu:isEquals("4", paramVo.searchItem, "selected='selected'") }>소셜인사이트</option> --%>
<%-- 											<option value="5" ${pu:isEquals("5", paramVo.searchItem, "selected='selected'") }>실시간모니터링</option> --%>
<%-- 											<option value="6" ${pu:isEquals("6", paramVo.searchItem, "selected='selected'") }>로그인/아이디</option> --%>
<%-- 											<option value="7" ${pu:isEquals("7", paramVo.searchItem, "selected='selected'") }>비번찾기</option> --%>
<%-- 											<option value="8" ${pu:isEquals("8", paramVo.searchItem, "selected='selected'") }>기타 항목</option> --%>
										</select>
										
										<select id="searchItem2" name="searchItem2" class="selectType04" placeholder="카테고리를 선택하세요" disabled="disabled" onchange="javascript:onSelectChangedept2(this.value); return false;">
											<option value="" >카테고리를 선택하세요</option>
										</select>
										
									</div>
							</fieldset>																
						</form>
						<c:choose>
						<c:when test="${fn:length(bbsVoList) > 0}">
						
						<div class="faqArea">
							<c:forEach var="list" items="${bbsVoList}" varStatus="sts">
								<c:set var="seq" value="${list.bbsSeq}"/>
								<form action='./input.do?${ paramHtml }' name="frmEdit_${seq}" id="frmEdit_${seq}" method="post">
								<input type="hidden" id="bbsSeq" name="bbsSeq" value="${list.bbsSeq}"/>
								</form>
<!-- 							<script>alert('${list.title}');</script> -->
<!-- 							<script>alert('${list.content}');</script> -->
								<dl>
									<dt><a href="#none"><span class="txtQ">Q</span><em>${list.title}</em></a></dt>
									<dd>
										<div class="txt">										
											<c:set var="content" value="${fn:replace(list.content,enter,'<br>') }"/>
											${content}
										</div>
										<div class="inquiry">
											<p>원하시는 답변을 찾지 못하셨다면, 1:1 문의를 해보세요.</p>
											<div class="btnRight">
												<a href="<%=_globalContextPath %>/mypage/qna/list.do" class="btnCom btnRegister">1:1 문의<span class="icon"></span></a>
												<c:if test="${_userCls ne 'C003'}">
													<a href="#none" class="btnCom btnManage" onclick="javascript:doEdit1('${pu:getEncrypt(list.bbsSeq)},${list.searchItem1},${list.searchItem2}'); return false;"  >수정</a>
													<a href="#none" class="btnCom btnManage" onclick="javascript:doDelete('${pu:getEncrypt(list.bbsSeq)}'); return false;">삭제</a>
												</c:if>

											</div>
										</div>
									</dd>

								</dl>
								

							</c:forEach>
						</div>	
						</c:when>
						<c:otherwise>
						<div class="faqArea space">
							<div class="noData">
								<span class="icon"></span>
								<p class="txt">등록 된 게시글이 없습니다.</p>
							</div>
						</div>
						</c:otherwise>
						</c:choose>
						<!-- btnPaging -->
						<div class="btnPaging">
							<!-- PAGING -->
							<div class="pagingArea">

								<div class="paging">
									${pu:getNavi(paramVo.gotoPage, paramVo.pageSize, totalCount, './list.do', paramHtml)}
								</div>
							</div>
							<!-- // PAGING -->
							<c:if test="${_userCls ne 'C003'}">
								<div class="btnArea"><a href="#none" class="btnCom btnRegister"  onclick="javascript:doInput();" >등록<span class="icon"></span></a></div>
							</c:if>
															
						</div>
						<!-- //btnPaging -->
					</div>
				</section>
				<!-- // FAQ -->
			</div>
		</div>
		<!-- // Contents -->
	
	<!-- //End #container -->
	<!-- Begin #footer -->
<%@ include file="/jsp/mng/inc/footer.jsp" %>