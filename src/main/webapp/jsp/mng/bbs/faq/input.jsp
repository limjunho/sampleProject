<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/mng/inc/headerStart.jsp" %>
${ paramVo.setSearchWord(pu:noHtml(paramVo.searchWord)) }
<c:set var="paramHtml" value="searchItem=${paramVo.searchItem}&amp;searchWord=${paramVo.searchWord}" />
<c:set var="paramStr" value="searchItem=${paramVo.searchItem}&searchWord=${paramVo.searchWord}" />
<c:set var="menuCd" value="MB20" />
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		if( "${bbsVo.searchItem2}" != ""){			
			onSelect("${bbsVo.searchItem1}");			
		}else{

		}
	});
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
		if(confirm("게시물을 삭제 하시겠습니까? ")){
			document.frmRemove.submit();
		}
	}
	
	function fileAdd(){
		if($(".add_list").length < 5){
			FILE_BOX_NUM++;
			var html = '<div class="add_list" id="fileBox'+FILE_BOX_NUM+'">' +
			'<input name="file'+FILE_BOX_NUM+'" class="input_gray_fix w500" id="file'+FILE_BOX_NUM+'" type="file"/>' +
			'<a class="delete" onclick="fileDel('+FILE_BOX_NUM+'); return false;"><img alt="삭제" src="../../images/icons/ico_del02.gif"></a>' +
			'</div>';
			
			$("#file_box").append(html);
		}else{
			alert("첨부파일 최대 5개까지 가능합니다.");
		}
	}
	//파일 등록창 삭제
	function fileDel(val){
		if($(".add_list").length > 1){
			$("#fileBox"+val).remove();
			$(".add_list .first").removeClass("first");
			$(".add_list").first().addClass("first");
		}
	}
	//파일 등록창 삭제 및 저장된 파일도 삭제 준비
	function fileDelEdit(val, bbsAttachSeq){
		if(!confirm("파일을 삭제 하시겠습니까?"))
		{
			return false;
		}
		$("#fileBox"+val).remove();
		$(".add_list .first").removeClass("first");
		$(".add_list").first().addClass("first");
		removeFile(bbsAttachSeq);
	}
	
	//저장
	function doSubmit(val){
		
		if($("#searchItem1").val()==""){
			alert("카테고리를 작성해 주시기 바랍니다.");
			$("#searchItem1").focus();
			return false;
		}
		
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
// 		if(confirm("목록 페이지 이동 하시겠습니까?")){
// 			location.href = "./list.do";
// 		}
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
	function onSelectChange(val){		
			
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
				
				$("#searchItem2").empty();
				var html ='';	
				var chv = "";
				if(list.length > 0 ){
					$("#searchItem2").prop("disabled","");
					
					html += '<option value="" >카테고리를 선택하세요</option>'
					for(i=0;i<list.length;i++){
						html += '<option value="'+list[i].cd+'" >'+list[i].cdNm+'</option>';
					}			
					$("#searchItem2").append(html);
					if(chv == ""){
						$("#searchItem2").parent().find(".selecttext04").html("카테고리를 선택하세요");	
					}else{
						$("#searchItem2").parent().find(".selecttext04").html(chv);	
					}
				}else{
					html += '<option value="" >카테고리를 선택하세요</option>';
					$("#searchItem2").prop("disabled","disabled");
					$("#searchItem2").append(html);
					$("#searchItem2").parent().find(".selecttext04").html("카테고리를 선택하세요");
					
				}
// 				$("#searchItem2").append(html);
			},
			failure : function (data) {
				alert("에러입니다.");
				//$(".dept_popup").hide();
				//location.reload();
			}
		})
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
						var vv = "${bbsVo.searchItem2}";
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
</script>
<%@ include file="/jsp/mng/inc/bodyStart.jsp" %>



	<!--                      -->
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
							
							<c:set var="editUrl" value="./edt.do?${ paramHtml }" />
							<form action='${pu:isEqualsElse(bbsVo.bbsSeq, "0", "./add.do", editUrl)}' name="frm" id="frm" method="post" enctype="multipart/form-data">
							<input type="hidden" name="h-bbsSeq" value="${pu:getEncrypt(bbsVo.bbsSeq)}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->
							<input type="hidden" name="bbsInfoId" value="${paramVo.bbsInfoId}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->
							
								<div class="tableArea">
									<div class="boardForm02">
										<table summary="FAQ 수정이며 카테고리, 제목, 내용으로 이루어져 있습니다.">
											<caption>FAQ 수정</caption>
											<colgroup>
												<col width="201px">
												<col width="888px">
											</colgroup>
											<tbody>
												<tr>
													<th scope="row"><span>카테고리</span><span class="chk">*</span></th>
													<td>
														<div class="searchBar03">
<!-- 															<select name="" class="selectType04"> -->
<!-- 																<option>카테고리를 선택하세요</option> -->
<!-- 																<option selected="selected">서비스 이용</option> -->
<!-- 															</select>																												 -->
<!-- 															<select name="" class="selectType04"> -->
<!-- 																<option selected="selected">카테고리를 선택하세요</option> -->
<!-- 															</select> -->
															
															<select id="searchItem1" name="searchItem1" class="selectType04"  placeholder="카테고리를 선택하세요" onchange="javascript:onSelectChange(this.value); return false;"><!-- javascript:onSelectChange(this.value); return false alert(this.value); -->
																<option value="">카테고리를 선택하세요</option>
																<c:choose>
																<c:when test="${fn:length(codeList) > 0}">				
																		
																		<c:forEach var="cl1" items="${codeList}" varStatus="sts">
																			<option value="${cl1.cd}" ${pu:isEquals(cl1.cd, bbsVo.searchItem1, "selected='selected'") } >${cl1.cdNm}</option>
																		</c:forEach>
																</c:when>		
																<c:otherwise>
																</c:otherwise>
																
																</c:choose>
															</select>
															
															<select id="searchItem2" name="searchItem2" class="selectType04" placeholder="카테고리를 선택하세요" disabled="disabled">
																<option value="">카테고리를 선택하세요</option>
															</select>
														</div>
													</td>
												</tr>
												<tr>
													<th scope="row"><span><label for="inquiryTit">제목</label></span><span class="chk">*</span></th>
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
											</tbody>
										</table>
									</div>
								</div>
								</form>
								<!-- 등록페이지 버튼 -->
								<c:if test="${bbsVo.bbsSeq == 0}">
									<div class="btnCenter">
										<a href="#none" class="btnCom02 btnRegister02" onclick="doSubmit('insert'); return false;">등록</a>
										<a href="#none" class="btnCom btnCancel02" onclick="doList(); return false;">취소</a>
									</div>

								</c:if>
								<!-- // 등록페이지 버튼 -->
								
								<form action='./remove.do' name="frmRemove" id="frmRemove" method="post">
									<input type="hidden" id="bbsSeq" name="bbsSeq" value="${bbsVo.bbsSeq}"/>
									<input type="hidden" name="bbsInfoId" value="${paramVo.bbsInfoId}"/>	<!-- 게시판 구분 필수 값 (NOTICE:공지사항) -->
								</form>
			
								<!-- 수정페이지 버튼 -->
								<c:if test="${bbsVo.bbsSeq > 0}">
									<div class="btnCenter">
										<a href="#none" class="btnCom02 btnRegister02" onclick="doSubmit('update'); return false;">수 정</button>
										<a href="#none" class="btnCom btnCancel02"  onclick="doList(); return false;" >취소</a>
									</div>

								</c:if>
								<!-- // 수정페이지 버튼 -->
								
								
							</div>
						</section>
						<!-- // FAQ -->
						
					</div>
				</div>
				<!-- // Contents -->
	
	
	
	<!--                        -->
	
	
	<!-- //End #container -->
	<!-- Begin #footer -->
<%@ include file="/jsp/mng/inc/footer.jsp" %>