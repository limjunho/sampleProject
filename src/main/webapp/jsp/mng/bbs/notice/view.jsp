<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/mng/inc/headerStart.jsp" %>
${ paramVo.setSearchWord(pu:noHtml(paramVo.searchWord)) }
<c:set var="paramHtml" value="searchItem=${paramVo.searchItem}&amp;searchWord=${paramVo.searchWord}" />
<c:set var="paramStr" value="searchItem=${paramVo.searchItem}&searchWord=${paramVo.searchWord}" />
<c:set var="menuCd" value="MB10" />
<script type="text/javascript">
	$(document).ready(function() {
	 	var a = 100;
	 	
		 // 자리수 50자 자르기
		$(".diva").each(function(){									
			
			var n = $(this).text();
			if(  getByte(n)  > a){
				$(this).text(cut_str(n, a)+"...");	
			}		
	
		});
	 	
		
	});
	// 목록이동
	function doList()
	{
		location.href = "./list.do?${paramStr}&gotoPage=${paramVo.gotoPage}";
	}
	
	//수정화면 이동
	function doEdit()
	{
// 		if(confirm("수정화면으로 이동하시겠습니까?")){
			document.frmEdit.submit();
// 		}
	}
	
	// 게시글 삭제처리
	function doRemove(){
		if(confirm("게시글을 삭제하시겠습니까?")){
			document.frmRemove.submit();
		}
	}
	
	// 수정화면 이동
	function doModify()
	{
		var frm = document.frm;
		frm.method = "post";
		frm.action = "./modify.do?${paramStr}&gotoPage=${paramVo.gotoPage}";
		frm.submit();
	}
	
	function funcAddCmtSubmit(){
		var str = $("#frmAddCmt").find('#content').val();
		
		if($.trim(str) == ''){
			alert("댓글 내용을 입력하세요.");
			$("#frmAddCmt").find('#content').focus();
			return false;
		}
		document.frmAddCmt.submit();
	}
	
	function funcEditCmtOpen(seq){
		$("#viewCmt"+seq).toggle();
		$("#editCmt"+seq).toggle();
	}
	
	function funcEditCmtSubmit(seq){
		var form = document.getElementsByName('frmEditCmt'+seq);
		var str = $(form).find('#content').val();
		
		if($.trim(str) == ''){
			alert("댓글 내용을 입력하세요.");
			$(form).find('#content').focus();
			return false;
		}
		$(form).submit();
	}
	function fucDelCmtSubmit(seq){
		if(confirm("댓글을 삭제하시겠습니까?")){
			var form = document.getElementsByName('frmRemoveCmt'+seq);
			$(form).submit();
		}
	}
	
</script>
<% pageContext.setAttribute("enter","\n"); %>

<%@ include file="/jsp/mng/inc/bodyStart.jsp" %>

	<!-- Contents -->
	<div id="contents">
		<div class="contArea">
			<h2 class="hidden">고객지원</h2>
			<h3 class="hidden">공지사항</h3>
			<div class="topArea">
				<div class="titleArea">
					<h4 class="title">공지사항</h4>
				</div>
			</div>

			<!-- 공지사항 -->
			<section class="mypagewrap">
				<div class="titleArea">
				</div>
				<div class="mypageCon">
					<div class="tableArea">
						<div class="boardForm02 v02">
							<table summary="공지사항 제목, 날짜, 내용으로 이루어져 있습니다.">
								<caption>공지사항 상세</caption>
								<colgroup>
									<col width="968px">
									<col width="122px">
								</colgroup>
								<tbody>
									<tr>
										<td class="tit"><strong>${bbsVo.title}</strong></td>
										<td class="date">${fn:replace(bbsVo.regDt, '-', '.')}</td>
									</tr>
									<tr>
										<td colspan="2" class="contBox">
											<div class="textArea">
												<c:set var="content" value="${fn:replace(bbsVo.content,enter,'<br>') }"/>
												${content}
											</div>
											<div class="attachBox">
												<dl>
													<dt><span class="attachIcon"></span>첨부파일 : </dt>
													<dd>
													<c:forEach var="list" items="${bbsFileList}" varStatus="sts">
														<a href="./fileDownload.do?nm=${list.nm}&amp;folder=${list.folder}&amp;mimeTy=${list.mimeTy}">${list.nm}</a>
												</c:forEach>
													</dd>
												</dl>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<form action='./input.do?${ paramHtml }' name="frmEdit" id="frmEdit" method="post">
							<input type="hidden" id="bbsSeq" name="bbsSeq" value="${bbsVo.bbsSeq}"/>
						</form>
						<form action='./remove.do' name="frmRemove" id="frmRemove" method="post">
							<input type="hidden" id="bbsSeq" name="bbsSeq" value="${bbsVo.bbsSeq}"/>
							<input type="hidden" name="bbsInfoId" value="${paramVo.bbsInfoId}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->
						</form>
						<ul class="pagingVer03">
							<li class="next">
							<c:choose>
								<c:when test="${!empty nextBbsVo }">
								<a href="./view.do?h-bbsSeq=${pu:getEncrypt(nextBbsVo.bbsSeq)}&amp;${paramHtml}" class="icon"><span class="iconNext"></span>다음글</a><a href="./view.do?h-bbsSeq=${pu:getEncrypt(nextBbsVo.bbsSeq)}&amp;${paramHtml}"><em class="diva">${ pu:noHtml(nextBbsVo.title) }</em></a><span class="date">${fn:replace(nextBbsVo.regDt, '-', '.')}</span>
								</c:when>
								<c:otherwise>
								<span class="icon"><span class="iconNext"></span>다음글</span><span><em>다음글이 없습니다.</em></span><span class="date"></span>
								</c:otherwise>
							</c:choose>
							</li>
							<li class="prev">
							<c:choose>
								<c:when test="${!empty prevBbsVo}">
								<a href="./view.do?h-bbsSeq=${pu:getEncrypt(prevBbsVo.bbsSeq)}&amp;${paramHtml}" class="icon"><span class="iconPrev"></span>이전글</a><a href="./view.do?h-bbsSeq=${pu:getEncrypt(prevBbsVo.bbsSeq)}&amp;${paramHtml}"><em class="diva">${ pu:noHtml(prevBbsVo.title) }</em></a><span class="date">${fn:replace(prevBbsVo.regDt, '-', '.')}</span>
								</c:when>
								<c:otherwise>
								<span class="icon"><span class="iconPrev"></span>이전글</span><span><em>이전글이 없습니다.</em></span><span class="date"></span>
								</c:otherwise>
							</c:choose>	
							</li>
						</ul>
						<!-- 다음글/이전글이 없을 경우 -->

						<!-- // 다음글/이전글이 없을 경우 -->

						<div class="btnCenter">
							<div class="btnLeft">
								<c:if test="${_userCls ne 'C003'}">
								<a href="#none" class="btnCom02 btnRegister02" onclick="doEdit(); return false;">수정</a>
								<a href="#none" class="btnCom btnCancel02" onclick="doRemove(); return false;">삭제</a>
								</c:if>
							</div>
							<div class="btnRight">
								<a href="#none" class="btnCom02 btnRegister02" onclick="doList(); return false;">목록</a>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- //공지사항 -->
		</div>
	</div>
	<!-- // Contents -->
	
<!-- Begin #footer -->
<%@ include file="/jsp/mng/inc/footer.jsp" %>
