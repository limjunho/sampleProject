<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/mng/inc/headerStart.jsp" %>
${ paramVo.setSearchWord(pu:noHtml(paramVo.searchWord)) }
<c:set var="paramHtml" value="searchItem=${paramVo.searchItem}&amp;searchWord=${paramVo.searchWord}" />
<c:set var="paramStr" value="searchItem=${paramVo.searchItem}&searchWord=${paramVo.searchWord}" />
<c:set var="menuCd" value="MB10" />
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	var oEditors = [];
	var FILE_BOX_NUM = 0;
	var DEL_BBS_FILE_SEQ = '';
	
	function doList()
	{
		alert("글 등록이 취소 되었습니다.");
		location.href = "./list.do?${paramStr}&gotoPage=${paramVo.gotoPage}";
		
	}
	
	// 게시글 삭제처리
	function doRemove(){
		if(confirm("글을 삭제하시겠습니까?")){
			document.frmRemove.submit();
		}
	}
	
	function fileAdd(){
		FILE_BOX_NUM = $(".fileAreaTd li").length;
		if($(".fileAreaTd li").length < 5){
			var html = '<li id="fileBox' + FILE_BOX_NUM + '">' +
			'<div class="fileArea">' +
			'<input class="fileText" id="fileRoute' + FILE_BOX_NUM + '" type="text" readonly="readonly" title="첨부된 파일경로">' +
			'<span class="fileWrap">' + 
			'<input type="file" id="file' + FILE_BOX_NUM + '" name="file' + FILE_BOX_NUM + '" class="fileAdd" onchange="javascript:document.getElementById(\'fileRoute' + FILE_BOX_NUM + '\').value=this.value" title="첨부 파일선택">' +
			'</span>' +
			'</div>' +
			'<a href="#none" onclick="fileDelEdit(' + FILE_BOX_NUM + ',\'\'); return false;" class="btnDel"><span>삭제</span></a>' +
			'</li>';
			
			$(".fileBox").append(html);
		}else{
			alert("첨부파일 최대 5개까지 가능합니다.");
		}
	}
	//파일 등록창 삭제
	/*
	function fileDel(val){
		if($(".fileArea").length > 1){
			$("#fileBox"+val).remove();
			$(".add_list .first").removeClass("first");
			$(".add_list").first().addClass("first");
		}
	}
	*/
	//파일 등록창 삭제 및 저장된 파일도 삭제 준비
	function fileDelEdit(val, bbsAttachSeq){
		if(bbsAttachSeq != null && bbsAttachSeq != "")
		{
			if(!confirm("파일을 삭제 하시겠습니까?"))
			{
				return false;
			}
			removeFile(bbsAttachSeq);
		}
		$("#fileBox"+val).remove();

	}
	
	//저장
	function doSubmit(val){
		if($.trim($("#title").val())==""){
			alert("제목을 작성해 주시기 바랍니다.");
			$("#title").focus();
			return false;
		}
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
		
		if($("#content").val() == "<p>&nbsp;</p>"){
			alert("내용을 작성해 주시기 바랍니다.");
			$("#content").focus();
			return false;
		}
		var msg = "";
		if(val == "insert"){
			msg = "게시물을 등록 하시 겠습니까?"
		}else{
			msg = "게시물을 수정 하시 겠습니까?"
		}
		if(confirm(msg)){
			document.frm.submit();
			
// 			setTimeout(function(){
// 				location.href = "./list.do";
// 			},300);
			
		}
		
	}
	
	function removeFile(bbsAttachSeq)
	{

		var url = "./fileDelete.do";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				bbsAttachSeq : bbsAttachSeq
			},
			async:false,
			dataType : "json",
			success : function(data) {
				if (data.result == "true") {
					alert("파일이 삭제 되었습니다.");
					return;
				} else {
					alert("파일 삭제가 실패했습니다.");
					return ;
				}
			},
			failure : function(data) {
				alert("파일 삭제가 실패했습니다.");
				return ;
			}
		});
	}
	$(function(){
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
			
			<c:set var="editUrl" value="./edt.do?${ paramHtml }" />
			<form action='${pu:isEqualsElse(bbsVo.bbsSeq, "0", "./add.do", editUrl)}' name="frm" id="frm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="h-bbsSeq" value="${pu:getEncrypt(bbsVo.bbsSeq)}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->
				<input type="hidden" name="bbsInfoId" value="${paramVo.bbsInfoId}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->

			<!-- 공지사항 -->
			<section class="mypagewrap">
				<div class="titleArea">
				</div>
				<div class="mypageCon">
					<div class="tableArea">
						<div class="boardForm02">
							<table summary="공지사항 입력이며 제목, 내용, 파일첨부로 이루어져 있습니다.">
								<caption>공지사항 입력</caption>
								<colgroup>
									<col width="201px">
									<col width="888px">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><span><label for="title">제목</label></span><span class="chk">*</span></th>
										<td>
											<input type="text" name="title" value="${bbsVo.title}" id="title" class="textType inquiryTit">
										</td>
									</tr>
									<tr>
										<th scope="row"><span>내용</span><span class="chk">*</span></th>
										<td class="editorArea">
											<div class="editor">
												<textarea id="content" name="content" style="width: 99%; height:440px;">${bbsVo.content}</textarea>
												<script style="text/javascript">
												//<![CDATA[
												nhn.husky.EZCreator.createInIFrame({
													oAppRef: oEditors,
													elPlaceHolder: "content",
													sSkinURI: "/smarteditor/SmartEditor2Skin.jsp",	
													htParams : {
														bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
														bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
														bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
														//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
														fOnBeforeUnload : function(){
														}
													}, //boolean
													fOnAppLoad : function(){
													},
													fCreator: "createSEditor2"
												});
												//]]>
												</script>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row"><span>파일첨부</span></th>
										<td class="fileAreaTd">
											<c:set var="index" value="-1" />
											<c:if test="${fn:length(bbsFileList) > 0 }">
											<ul class="fileList">
												<c:forEach var="fileList" items="${bbsFileList}" varStatus="sts">
												<li id="fileBox${sts.index}"><a href="./fileDownload.do?nm=${fileList.nm}&amp;folder=${fileList.folder}&amp;mimeTy=${fileList.mimeTy}">${fileList.nm}</a> <a href="#none" class="btnDel" onclick="fileDelEdit(${sts.index},${fileList.bbsAttachSeq}); return false;"><span>삭제</span></a></li>
												<c:set var="index" value="${sts.index}" />
												</c:forEach>
											</ul>
											</c:if>
											<ul class="fileBox">
												<li id="fileBox${index+1}">
													<div class="fileArea">
														<input type="text" value="" id="fileRoute${index+1}" class="fileText" readonly="readonly" title="첨부된 파일경로">
														<span class="fileWrap"> 
															<input type="file" id="file${index+1}" name="file${index+1}" class="fileAdd" onchange="javascript:document.getElementById('fileRoute${index+1}').value=this.value" title="첨부 파일선택">
														</span>
													</div>
													<!-- 
													<a href="#none" class="btnDel" onclick="fileDelEdit(${index+1},''); return false;"><span>삭제</span></a>
													 -->	
													<div class="btnRight"><a href="#none" class="sbtn03 addSbtn" onclick="fileAdd(); return false;">추가<span class="icon"></span></a></div>
												</li>
											</ul>
		
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="btnCenter">
					<!-- 등록페이지 버튼 -->
					<c:if test="${bbsVo.bbsSeq == 0}">
						<a href="#none" class="btnCom02 btnRegister02" onclick="doSubmit('insert'); return false;">등록</a>						
						<a href="#none" class="btnCom btnCancel02" onclick="doList(); return false;">취소</a>
					</c:if>
					<!-- // 등록페이지 버튼 -->	
					<!-- 수정페이지 버튼 -->
					<c:if test="${bbsVo.bbsSeq > 0}">
						<div class="btnCenter">
							<a href="#none" class="btnCom02 btnRegister02" onclick="doSubmit('update'); return false;">수 정</a>
							<a href="#none" class="btnCom btnCancel02"  onclick="doList(); return false;" >취소</a>
						</div>

					</c:if>
					
					</div>
					
				</div>
			</section>
			<!-- //공지사항 -->
			</form>
		</div>
	</div>
	<!-- Begin #footer -->
<%@ include file="/jsp/mng/inc/footer.jsp" %>