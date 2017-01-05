<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/mng/inc/headerStart.jsp" %>
<%-- XSS 및 Link Injection 대응 --%>
${ paramVo.setSearchWord(pu:noHtml(paramVo.searchWord)) }
<c:set var="paramHtml" value="searchItem=${paramVo.searchItem}&amp;searchWord=${paramVo.searchWord}" />
<c:set var="paramStr" value="searchItem=${paramVo.searchItem}&searchWord=${paramVo.searchWord}" />
<c:set var="menuCd" value="MB10" />
<script type="text/javascript">
	function doInput()
	{
		location.href = "./input.do?${paramStr}&gotoPage=${paramVo.gotoPage}";
	}
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
	
</script>

										
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
					<form name="frmSearch" id="frmSearch" method="get" action="./list.do">
						<fieldset>
							<legend>공지사항 검색</legend>
								<div class="searchBar">
									<select name="searchItem" id="searchItem" class="selectType03">
										<option value="">전체</option>
										<option value="1" ${pu:isEquals("1", paramVo.searchItem, "selected='selected'") }>제목</option>
										<option value="2" ${pu:isEquals("2", paramVo.searchItem, "selected='selected'") }>내용</option>
										
									</select>
									<input type="text" name="searchWord" id="searchWord" value="${paramVo.searchWord}" class="textType">
									<input type="hidden" name="searchItem1" id="searchItem1" value="" class="textType">
									<input type="hidden" name="searchItem2" id="searchItem2" value="" class="textType">
									<!-- <a href="#none" class="btnSearch03"><span>검색</span></a>
									<button type="submit" class="btnSearch03" title="검색"><span>검색</span></button> -->
									<input type="button" name="" value="검색" class="btnSearch03" title="검색" onclick="javascript:document.frmSearch.submit();">
								</div>
						</fieldset>
					</form>
					<div class="tableArea">
						<div class="boardList03">
							<table summary="공지사항 목록이며 번호, 제목, 등록일로 이루어져 있습니다.">
								<caption>공지사항 목록</caption>
								<colgroup>
									<col width="67px" />
									<col width="900px" />
									<col width="123px" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">번호</th>
										<th scope="col">제목</th>
										<th scope="col">등록일</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
									<c:when test="${fn:length(bbsVoList) > 0}">
									<c:forEach var="list" items="${bbsVoList}" varStatus="sts">
									<tr>
										<td>${numbering}</td>

										<td class="tit02"><a href="./view.do?h-bbsSeq=${pu:getEncrypt(list.bbsSeq)}&amp;${paramHtml}&amp;gotoPage=${paramVo.gotoPage}" class="diva">${list.title}</a></td>
										
										<td >${pu:getSqlDateString(list.regDt)}</td>
									</tr>
									<c:set var="numbering" value="${ numbering-1 }"/>
									</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 데이터 없을 경우 -->
										<tr>
											<td colspan="3" class="noData">
												<span class="icon"></span>
												<p class="txt">등록 된 글이 없습니다.</p>
											</td>
										</tr>
										<!-- // 데이터 없을 경우 -->
									</c:otherwise>
								</c:choose>	
								</tbody>
							</table>
						</div>
					</div>
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
						<div class="btnArea"><a href="#none" class="btnCom btnRegister" onclick="doInput();">등록<span class="icon"></span></a></div>
						</c:if>
					</div>
					<!-- //btnPaging -->
				</div>
			</section>
			<!-- //공지사항 -->
		</div>
	</div>
	<!-- // Contents -->
<%@ include file="/jsp/mng/inc/footer.jsp" %>