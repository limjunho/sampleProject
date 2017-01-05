//변수
var UserAgent = navigator.userAgent;

// 널체크
function checkNullStr(s){
	if (s.length+1 == s.split(" ").length) {		
		return false;
    	}
    	return true;
}


// 숫자인지 체크
function IsNum(numstr){
	var tempstr = "0123456789";
	for(var i=0;i<numstr.length;i++) 
	{
		if(tempstr.indexOf(numstr.charAt(i)) == -1) {
			return false;
		}
	}
	return true;
}
// 2014.12.29, 숫자인지 체크
function IsNum2(numstr){
	var pattern = /^[0-9]+$/;
	for(var i=0;i<numstr.length;i++) 
	{
		if(pattern.test(numstr.charAt(i))) {
			return false;
		}
	}
	return true;
}

//숫자인지 체크
function checkNum (object)
{
	with (object)
	{
		checkValue=object.value;
		realValue=0;
		if (checkValue == "")
			return true;
		if (!checkValue.match (/^[\d]+$/))
		{
				object.value=""
				object.focus();
				alert("숫자만 입력해 주세요.");
				return false;
		}else if ( checkValue == "0"){
		
				object.value=""
				object.focus();
				alert("'0' 이상만 가능 합니다.");
				return false;
		
		}
		return true;
	}
}

// 숫자,특수문자 불가 체크
function IsChar(aStr, aLen, aHan){
        var isValid = true;
        for (var i=0; i< aLen; i++){
          if (aHan == "ENG") {
             if (!( (aStr.charCodeAt(i) >= 65 && aStr.charCodeAt(i) <= 90 ) ||
                    (aStr.charCodeAt(i) >= 97 && aStr.charCodeAt(i) <= 122) )) {
                isValid = false;
                break;
             }
          } else if (aHan == "HAN") {
             if (!( aStr.charCodeAt(i) >  127 )  )  {
                isValid = false;
                break;
             }
          } else {
             if (!( (aStr.charCodeAt(i) >= 65 && aStr.charCodeAt(i) <= 90 ) ||
                    (aStr.charCodeAt(i) >= 97 && aStr.charCodeAt(i) <= 122) ||
                    (aStr.charCodeAt(i) >  127 ) ))  {
                isValid = false;
                break;
             }
          } 
        } 
        return isValid;
}




// 주민번호 유효성체크
function IsResId(id1, id2){
	a = Array(6);
	b = Array(7);

	for (var i=0; i<6; i++)
		a[i] = parseInt(id1.charAt(i));

	for (var j=0; j<7; j++)
		b[j] = parseInt(id2.charAt(j));

	ssntot = (a[0]*2)+(a[1]*3)+(a[2]*4)+(a[3]*5)+(a[4]*6)+(a[5]*7) + (b[0]*8)+(b[1]*9)+(b[2]*2)+(b[3]*3)+(b[4]*4)+(b[5]*5);
	ssnave = 11 - (ssntot%11);

	if (ssnave == 11) ssnave = 1;
	else if(ssnave == 10) ssnave = 0;

	if (b[6] == ssnave) return true;
	else return false;
}

// 이메일주소 유효성체크
function IsMailStr(aStr){
     tempstr = "0123456789abcdefghijklmnopqrstuvwxyz_-@.";
     str1cnt = 0;
     str2cnt = 0;
     for(i=0;i<aStr.length;i++) { 
       if(tempstr.indexOf(aStr.charAt(i)) == -1) return false;
       if(aStr.charAt(i) == '@')  str1cnt += 1;
       if(aStr.charAt(i) == '.')  str2cnt += 1;
     } 
     if (str1cnt != 1 || str2cnt < 1 || str2cnt > 2) return false;
     return true;
}

// 전화 지역번호 체크
function IsValidDDD(obj){
        if ( obj == '02'  || obj == '031' || obj == '032' || obj == '033' ||
             obj == '041' || obj == '042' || obj == '043' || obj == '051' ||
             obj == '052' || obj == '053' || obj == '054' || obj == '055' ||
             obj == '061' || obj == '062' || obj == '063' || obj == '064' ||
             obj == '011' || obj == '017' || obj == '016' || obj == '018' ||
             obj == '019'
           )
            return true;
        else
            return false;
}

// 전화번호 4자리로 채워줌(스페이스로 채움)("012"-->"012 ")
function GetFourDigit(v){
	var ret="";
	if(v.length==4){ ret=v; }
	else if(v.length==3){ ret=v+" "; }
	else if(v.length==2){ ret=v+"  "; }
	else{ ret=v+"   "; }
	return ret;
}

// 날짜형식체크
function IsDate(src){
	if(src.length==8){
		if(src.substring(4,6)<13){
			if(src.substring(6,8)>0 && src.substring(6,8)<32){
				return true;
			}
		}
	}
	return false;
}

// 공백 필드 체크 후 alert 메세지, 포커싱 처리
function feildCheck(field, msg, bForcus){
	if ( !checkNullStr(field.value)) {
		alert(msg);

		if(bForcus != null && bForcus){
			field.focus();
		}
	
		return false;
	}else{
		return true;
	}
}

//str은 모두 소문자여야하고 첫글자는 영문이어야 한다. 영문과 0~9, _ 는 허용한다. 
function CheckChar(str) { 
    strarr = new Array(str.length); 
    var flag = true; 
    for (i=0; i<str.length; i++) { 
        strarr[i] = str.charAt(i) 
        if (i==0) { 
            if (!((strarr[i]>='a')&&(strarr[i]<='z'))) { 
                flag = false; 
            } 
        } else { 
            if (!((strarr[i]>='a')&&(strarr[i]<='z')||(strarr[i]>='0')&&(strarr[i]<='9')||(!IsChar(strarr[i])))) { 
                flag = false; 
            } 
        } 
    } 
    if (flag) { 
        return true; 
    } else { 
        return false; 
    } 
} 

//str은  영어여야하고  영문과 0~9, _ 는 허용한다. 
function CheckChar1(str) { 
  strarr = new Array(str.length); 
  var flag = true; 
  for (i=0; i<str.length; i++) { 
      strarr[i] = str.charAt(i) 
      if (!((strarr[i]>='a')&&(strarr[i]<='z')||(strarr[i]>='A')&&(strarr[i]<='Z')||(strarr[i]>='0')&&(strarr[i]<='9'))) { 
    	  flag = false; 
      }
  }
  if (flag) { 
      return true; 
  } else { 
      return false; 
  } 
}
//str은 모두 영문소문자여야 한다. 
function CheckChar2(str) { 
    strarr = new Array(str.length); 
    var flag = true; 
    for (i=0; i<str.length; i++) { 
        strarr[i] = str.charAt(i) 
        if (!((strarr[i]>='a')&&(strarr[i]<='z'))) { 
            flag = false; 
        } 
    } 
    if (flag) { 
        return true; 
    } else { 
        return false; 
    } 
} 

//str은 한글이어야만 한다. 
function CheckHangul(str) { 
    strarr = new Array(str.length); 
    schar = new Array('/','.','>','<',',','?','}','{',' ','\\','|','(',')','+','='); 
    flag = true; 
    for (i=0; i<str.length; i++) { 
        for (j=0; j<schar.length; j++) { 
            if (schar[j] ==str.charAt(i)) { 
                flag = false; 
            } 
        } 
        strarr[i] = str.charAt(i) 
        if ((strarr[i] >=0) && (strarr[i] <=9)) { 
            flag = false; 
        } else if ((strarr[i] >='a') && (strarr[i] <='z')) { 
            flag = false; 
        } else if ((strarr[i] >='A') && (strarr[i] <='Z')) { 
            flag = false; 
        } else if ((escape(strarr[i]) > '%60') && (escape(strarr[i]) <'%80') ) { 
            flag = false; 
        } 
    } 
    if (flag) { 
        return true; 
    } else { 
        return false; 
    }
}

function isIncludeHangul(str){

	var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var chk_han = str.match(check);

	return ( chk_han != null );
}

//이메일 체크
function email_check(value)
{
	var flag = false; //true when validation successful.
	var msg = "";

	if (value == "") return flag; //입력값 없는 경우는 Pass

	var tsTarget = value;
	var regExpEmail = /^\w+((-|\.)\w+)*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/;

	return regExpEmail.test(tsTarget);
}

/** 숫자 인지 여부 **/
function isNumeric(s)
{
/*
     var isNum = /\d/;
     if( !isNum.test(s) ) return 0; 
     return 1;
*/
	var pattern = /^[0-9]+$/;
	return (pattern.test(s)) ? true : false;
}

/** 문자열의 바이트 길이를 되돌린다 **/ //호인 주석 : 특수 키와 소문자 영문자 영어만 1byte
function getByte(str) {
	
	if ( str == null || str == "" ) {
		return 0;
	}
	
    var len = 0;
    str = this != window ? this : str;
    for (j=0; j<str.length; j++) {
        var chr = str.charAt(j);
        len += (chr.charCodeAt() > 128) ? 2 : 1;
    }
    return len;
}

/**
70. 해당 객체의 입력값 검사 및 byte 수 검사
1. 숫자 / 한글 / 영문
2. byte 수
3. 다음 입력 박스
**/
function checkInputCondition(){

	var i = -1;
	var checkValue = "";
	var args = checkInputCondition.arguments;
	var oTargetObj = args[++i];
	var validType = args[++i];
	var valueByte = args[++i];
	var nextInput = args[++i];	

	checkValue = oTargetObj.value;
	var bResult = true;
	var errMsg = "";
	
	if ( checkValue == "" ){
	}else{
		if ( validType == "number" ){
			bResult = isNumeric(checkValue);
			errMsg = "숫자만 입력하세요.";
		}
		else if ( validType == "han" ){
			bResult = isHanOnly(checkValue);
			errMsg = "한글만 입력하세요.";
		}
		else if ( validType == "eng" ){
			bResult = isEnglishStr(checkValue);
			errMsg = "영문만 입력하세요.";
		}
		else{
		}
	}
	// 검사 결과
	if ( !bResult ){
		// alert(errMsg);
		try{
			oTargetObj.value = "";
			oTargetObj.focus();
		}
		catch(e){
		}
		return false;
	}

	checkValue = oTargetObj.value;
	var currentValueByte = getByte(checkValue);
	// alert(currentValueByte);
	if ( currentValueByte > valueByte ){
		alert("입력된 값의 길이는 " + valueByte + " 바이트 까지 가능합니다.");
		try{
			oTargetObj.focus();
		}
		catch(e){
		}
		return false;
	}
	else if ( currentValueByte == valueByte && nextInput != null ){
		try{
			nextInput.focus();
		}
		catch(e){
		}
	}

	return true;

}

/**
2011-07-14 박재영
정규식 치환이 잘 안되네 걍 div로 처리.
**/
function removeHtml2(html){
	var oBody = document.body;
	var oNewDiv = document.createElement("div");
	oBody.appendChild(oNewDiv);
	oNewDiv.innerHTML = html;
	return oNewDiv.innerText;
}


/** 문자열을 바이트 단위로 자른다. **/
function cut_str(str, length)
{
	var args = cut_str.arguments;
	var glueString = "";
	var bGlueString = false;
	if ( args.length == 3 )
	{
		glueString = args[2];
	}

	var tmpStr;
	var temp=0;
	var onechar;
	var tcount;
	var cutStr = "";
	tcount = 0;

	tmpStr = new String(str);
	temp = tmpStr.length;

	if ( getByte(str) > length )
	{
		bGlueString = true;
	}

	for(k=0;k<temp;k++)
	{
		var oneCharByte = getByte(tmpStr.charAt(k));

		if ( ( getByte(cutStr) + oneCharByte ) > length )
		{
		}
		else{
			cutStr += tmpStr.charAt(k);
		}
	}

	if ( bGlueString )
	{
		cutStr += glueString;
	}

	return cutStr;
}

/*
1000 자리 콤마 표시
*/
function numberWithCommas(x) {
	if(x == null)
	{
		return 0;
	}
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/*
support debugConsole
*/
function debugConsole(str){
	try{
		console.log(str);
	}catch(e){
	}
}

/*
 * 날짜 비교
 */
function getDate(date1, date2){
	var startDate = date1.split("-");
	var endDate = date2.split("-");
	
	var sDate = new Date(startDate[0], startDate[1], startDate[2]).valueOf();
	var eDate = new Date(endDate[0], endDate[1], endDate[2]).valueOf();
	
	if(sDate > eDate){
		return true;
	}else if(sDate == eDate){
		return false;
	}else{
		return false;
	}
}

$(function () {

	/**
	 * @author      : 박진수
	 * @date        : 2014. 8. 12.
	 * @description : 체크박스 전체선택/전체해체 
	 * 				  체크박스 선택시 체크 박스 전체가 선택 되면 전체 선택 박스 체크 그렇지 않으면 전체 체크 박스 해제
	 * 			      규칙 - class="chkList"추가, 전체 체크 박스는 id는 맨 뒤에 All 포함하여 만들고 하위 체크박스 name은 전체 체크박스 id이름에 'All' 대신 'Row'를 넣는다.
	 *                예) id="sampleCheckAll"(전체 체크 박스), name="sampleCheckRow"(하위 체크박스)
	*/
	$(".chkList").bind("click", function(obj){

		//전체 체크 박스 여부 구분
		if((obj.target.id != null && obj.target.id != "") && obj.target.id.indexOf("All") > -1)	
		{	
			//체크박스 전체선택/전체해체 
			var chkNm = obj.target.id.replace("All", "Row");
			if($("#"+ obj.target.id).is(":checked"))
			{
				for(var i = 0 ; i < $("input[name=" + chkNm + "]").length ; i++)
				{
					$("input[name=" + chkNm + "]")[i].checked = true;
				}
			}
			else
			{
				for(var i = 0 ; i < $("input[name=" + chkNm + "]").length ; i++)
				{
					$("input[name=" + chkNm + "]")[i].checked = false;
				}
			}
		}
		if((obj.target.name != null && obj.target.name != "") && obj.target.name.indexOf("Row") > -1)
		{
			//체크박스 선택시 체크 박스 전체가 선택 되면 전체 선택 박스 체크 그렇지 않으면 전체 체크 박스 해제
			var chkId = obj.target.name.replace("Row", "All");
			if($("input[name=" + obj.target.name + "]").length == $("input[name=" + obj.target.name + "]:checkbox:checked").length)
			{
				$("#"+ chkId)[0].checked = true;
			}
			else
			{
				$("#"+ chkId)[0].checked = false;
			}
		}
	});
	
	//ajax 호출 에러 체크
	$.ajaxSetup({
        beforeSend: function(xhr) {
        	xhr.setRequestHeader("AJAX", true);
	    },
	    complete:function(xhr, status)
	    {
        	resetTimer();	//timer...
	    },
	    error: function(xhr, status, err) {
	    	if (xhr.status == 401) {
	    		//alert("401");//에러페이지 이동 예정
	    		location.href = "/error/";
	        } 
	    	else if (xhr.status == 403) {
	            //alert("403");//에러페이지 이동 예정
	    		location.href = "/error/";
	        } 
	    	else if (xhr.status == 500) {
	    		//alert("500");//에러페이지 이동 예정
	    		location.href = "/error/";
	    	} 
	    	else if(xhr.status == 200)
	        {
	        	//location.href = __globalContextPath + "/intro.do";
	    		//alert("장시간 사용하지 않아 자동 로그아웃 되었습니다.\n로그인 후 이용해 주시기 바랍니다.");
	    		window.location.reload(); 
	        }
	    	else if(xhr.status == 0)
	    	{
	    	}
	        else 
	        {
	            alert("예외가 발생했습니다. 관리자에게 문의하세요.");
	        }
	    }
	});

});

/**
 * @author      : 박진수
 * @date        : 2014. 8. 12.
 * 문자열 길이값 반환 한글 포함
 */
function getByteStrLength(str)
{
	var len = 0;
	if(str == null)
	{
		return 0;
	}
	for(var i = 0 ; i < str.length ; i++)
	{
		var c = escape(str.charAt(i));
		if(c.length == 1) 
		{
			len++;
		}
		else if(c.indexOf("%u") > -1)
		{
			len += 2;
		
		}
		else if(c.indexOf("%") > -1)
		{
			len += c.length/3;
		}
		if(c == "%0D")
		{
			len += 1;
		}
		if(c == "%0A")
		{
			len += 1;
		}
	}
	return len;
}

/**
 * @author      : 박진수
 * @date        : 2014. 8. 25.
 * @description : 이미지 파일 여부
 * obj : 해당 파일의 value 값
 * 예) <input type="file" name="imgFile" value onchange="fileValidation(this.value);" />
 */
function fileValidation(obj)
{
	
	var pathpoint = obj.lastIndexOf('.');
	var filepoint = obj.substring(pathpoint + 1, obj.length);
	var filetype = filepoint.toLowerCase();
	if(obj != null && obj != "")
	{
		if (filetype == 'jpg' || filetype == 'gif' || filetype == 'png' || filetype == 'jpeg' || filetype == 'bmp') {
			return true;
		} else {
			alert('이미지 파일만 가능합니다.');
			fileReset();
			return false;
		}
	}
	else
	{
		return true;
	}
}

/**
 * @author      : 박진수
 * @date        : 2014. 8. 25.
 * @description : 파일 리셋 
 */
function fileReset()
{
	//debugConsole(UserAgent);

	if(UserAgent.indexOf("MSIE") > -1) {
        $("input[type='file']").replaceWith( $("input[type='file']").clone(true) );
	}
	else 
	{
	    $("input[type='file']").val("");
	}
}


/**
 * @author 유정석
 * @description 완성형 한글 체크
 * @param {String} s 체크할 문자열
 * @return {Boolean}
 * */
function checkCompletedHangul( s ){
	var ustr = escape( s ),
	c,
	result;

	if( ustr.indexOf( '%' ) > -1 ){
		ustr = ustr.replace( /\%/gi, '\\' );
	}
	
	var i=0, len=s.length;
	for( ;i<len;i+=1 ){
		c = (s.substr(i, 1)).charCodeAt();
		if (c >= 0xAC00 && c <= 0xD7AF){ // 완성형 한글
			result = true;
		}else if( c >= 0x3130 && c <= 0x318F ) { // 완성형 아님
			result = false;
			break;
		}else{ // 한글아님
			result = false;
			break;
		};
	}
	return result;
}
/**
 * 아이디 정책
 * @param id
 * @returns {Boolean}
 */
function canUseUserIdPattern(id){
	
	var regExpId = /[^0-9^a-z]/g;
	var bTest = regExpId.test(id);
	
	return !bTest;
}

/**
 * @author 서승현
 * @description 레이어 팝업 화면의 가운데 위치로 조정 함수
 * @param {String} '.'을 제외한 클래스명
 * */
function funcPosiCenter(className){
	var w;
	var h;
	
	//다양한 브라우저에서 창크기를 가져옴
	if(typeof(window.innerWidth) == 'number' ) {
		 w = window.innerWidth;
		 h = window.innerHeight;
	}else if(document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ){
		w = document.documentElement.clientWidth;
		h = document.documentElement.clientHeight;
	}else if(document.gody && (document.body.clientWidth || document.body.clientHeight ) ){
		w = document.body.clientWidth;
		h = document.body.clientHeight;
	}
	w= Number(w/2);
	h= Number(h/2);
	var classWidth =  Number($("."+className).outerWidth()/2);
	var classHeight =  Number($("."+className).outerHeight()/2+50);
	
	$("."+className).stop().animate({"left":(w-classWidth) + 'px',"top":(h-classHeight +$(window).scrollTop()) + 'px'}, "fast");
//	$("."+className).css({"left":(w-classWidth) + 'px',"top":(h-classHeight +$(window).scrollTop()) + 'px'});	//어두운 div가 창크기에 맞게 조절
}


/**
 * @author 최수영
 * @description 지정한 길이만큼 왼쪽에 원하는 문자를 붙여서 반환
 * @param {int} 지정길이, {String} 기본문자열, {String} 왼쪽부터 붙일 문자(기본 0)
 * @return {String} 문자열
 * */
function leftPad(len, str, ch) {
	var o = str.toString();
	if ( !ch ) {
		ch = '0';
	}
	while ( o.length < len ) {
		o = ch + o;
	}
	return o;
}

//시간이 23을 넘는지 체크한다.
function timeChk(time)
{
	if(time != null && time != "")
	{
		if(parseInt(time) > 23)
		{
			return false;	
		}
		else
		{
			return true;
		}
	}
	else
	{
		return true;
	}
}

//분이 59을 넘는지 체크한다.
function minChk(min)
{
	if(min != null && min != "")
	{
		if(parseInt(min) > 59)
		{
			return false;	
		}
		else
		{
			return true;
		}
	}
	else
	{
		return true;
	}
}

//시간, 분을 2자리로 맞춤
function timeSet(str)
{
	if(str != null && str != "")
	{
		if(parseInt(str) < 10)
		{
			return "0" + parseInt(str); 
		}
	}
	return str;
}

//input 키 핸들러 파이어폭스 한글 안됨
function remainOnlyTargetValue(regEx, obj, e) {
	if(e.which=='229' || e.which=='197' && $.browser.opera) {
		setInterval(function(){
			obj.trigger('keyup');
		}, 100);
	}
	if (!(e.which && (e.which > 47 && e.which < 58)|| e.which ==8 || e.which ==9|| e.which ==0 || (e.ctrlKey && e.which ==86) /* 조건추가: 우측 숫자판, del, home, end, ←, → */ || (e.which > 95 && e.which < 106) || e.which == 46 || e.which == 36 || e.which == 35 || e.which == 37 || e.which == 39 ) ) {	
		e.preventDefault();
	}
	var value = obj.val().match(regEx);
	if(value!=null) {
		obj.val(obj.val().replace(regEx,''));
		
	}	 

}

$(function(){
	var regEx = /[^0-9]/gi;	//숫자만 체크
	$(".onlyNumber").keydown(function(e){
		remainOnlyTargetValue(regEx, $(this), e);
	}).keyup(function(e){
		remainOnlyTargetValue(regEx, $(this), e);
	});
    $(".notHangul").keyup(function(event){
    	if (!(event.keyCode >=37 && event.keyCode<=40)) {
    		var inputVal = $(this).val();
    		$(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
        }
    });
});

/**
 * 2015.05.07, shseo
 * replaceAll 기능 추가
 * param ( 문장글, 문장글 안에 특정 단어, 교체할 단어)
 */
function replaceAll(str, searchStr, replaceStr) {
	if(str == null)
	{
		return "";
	}
    return str.split(searchStr).join(replaceStr);
}

function getMenuNm()
{
	//2016.1.2, 메뉴명 추가
    var subMenu1 = $("#subMenu1").parent().find("span > a").text();
    var subMenu2 = $("#subMenu2").parent().find("span > a").text();
    var subMenu3 = $("#subMenu3").parent().find("span > a").text();
    var menuNm = "";
    if(subMenu1 != "")
    {
    	menuNm = subMenu1;
    }
    if(subMenu2 != "")
    {
    	menuNm = menuNm + "_" + subMenu2;
    }
    if(subMenu3 != "")
    {
    	menuNm = menuNm + "_" + subMenu3;
    }
    return menuNm;
}

//pdf 초기화
function initPdf()
{
	$("input[name='img_val']").remove();

	$("div").each(function(){
		if($(this).attr("id") != null && $(this).attr("id").indexOf("pdfArea") > -1 && !$(this).hasClass("pdfArea"))
		{
			$(this).addClass("pdfArea");
		}
	});
	window.setTimeout(function(){
		$(".contArea").addClass("contAreaPdf");
	}, 1);
}

//svg canvas 형식으로 변환
function setSvgCanvas(obj)
{
	// svg 그래프가 있을 경우 canvas로 변경함
    var nodesToRecover = [];
    var nodesToRemove = [];
    var svgElem = obj.find("svg");
    //debugConsole(obj.attr("id") + " || svgElem2.length : " + svgElem.length);

    svgElem.each(function(index, node) {
    	//debugConsole("index : " + index + " || node : " + node);
        var parentNode = node.parentNode;
        var svg = parentNode.innerHTML;

        var canvas = document.createElement("canvas");
        
        var oSerializer = new XMLSerializer();
        var sXML = oSerializer.serializeToString(node);
        sXML = sXML.replace(/xmlns=\"http:\/\/www\.w3\.org\/2000\/svg\"/, '');
        canvg(canvas, sXML,{ ignoreMouse: true, ignoreAnimation: true });

        nodesToRecover.push({
            parent: parentNode,
            child: node
        });
        parentNode.removeChild(node);

        nodesToRemove.push({
            parent: parentNode,
            child: canvas
        });

        parentNode.appendChild(canvas);
    });
}

//html 이미지화
function setHtml2Canvas(svgElem, obj, id, idIdx, i, maxLength)
{
	var delayTime = 20;
	if( UserAgent.indexOf("Firefox") > -1 )
	{
		delayTime = 1000;
	}
	//브라우저 랜더링 제각각... 맞추기 힘들다....
	html2canvas(obj, {
        onrendered: function(canvas) {

			$("#img_val"+idIdx).val(canvas.toDataURL("image/jpeg", 0.5));

        	//Set hidden field's value to image data (base-64 string)
			if(svgElem != null && svgElem.length > 0)
			{							
				obj.remove();
			}

			$("#"+id).show();				
			obj.find(".topPdfArea").remove();	//pdf 로그삭제
    		//Submit the form manually  
			//debugConsole("i : " + i + " maxLength : " + maxLength);
			if(i+1 == maxLength)
			{	
				$(".contArea").removeClass("contAreaPdf");
				window.setTimeout(function(){
					document.getElementById("pdfForm").submit();
				}, delayTime);
				
			}

        },
        useCORS : true,
        allowTaint : true,
        taintTest : true,
        //logging:true,
        letterRendering : true
    });
}

/**
 * 2015.10.06, 서승현
 * pdf download
 */
function getPdf() {
	var delayTime = 20;
	if( UserAgent.indexOf("Firefox") > -1 )
	{
		delayTime = 200;
	}
	initPdf();//초기화
	
	var maxLength = 0;
	
	//ie9 html2canvas 사용시 IndexSizeError 로 인한 화면출력이 없는곳은 제외 시킴 
	$(".pdfArea").each(function(){
		if($(this).children("section").css("display") == "none" || $(this).parent().css("display") == "none" || $(this).children("div").css("display") == "none")
		{
			$(this).removeClass("pdfArea");
		}
		else
		{
			maxLength++;
		}
	});

	document.pdfForm.menuNm.value = getMenuNm();
	
	var headerText = setHeaderText();

	
	var headerHtml = "<div class='topPdfArea'><span><img src='" + __globalContextPath + "/images/common/logo_pdf.png' alt='DAEHONG CUMMUNICATIONS'>" + headerText + "</span></div>";//페이지 상단 로고

	$(".pdfArea").each(function(i){
		var id = $(this).attr("id");

		var idIdx = id.replace("pdfArea", "");
		
		$("#pdfForm").append(
				$("<input>").attr("type", "hidden")
				.attr("name", "img_val")
				.attr("id", "img_val"+idIdx)
		);	
	
		var obj;

		//svg 존재 여부 확인 - svg가 잇으면 복제한다.
		var svgElem = $("#"+id).find("svg");
		//debugConsole("i : " + i);

		//svg가 존재하면 복제
		if(svgElem != null && svgElem.length > 0)
		{
			$("#"+id).after("<div id='pdfTarget" + idIdx + "' name='pdfTarget' class='pdfTarget'>" + headerHtml + $("#"+id).html() + "</div>");	
			$("#"+id).hide();
			obj = $("#pdfTarget"+idIdx);
		}
		else	//svg가 존재하지 않으면 그대로 사용
		{
			//debugConsole("agent : " + UserAgent);

			$("#"+id).children("section:first").before(headerHtml);
			obj = $("#"+id);
		}
		// 백그라운드가 검은색으로 pdf 가 출력되서 css 추가함
		
		
		obj.css("background","#fff");	//html2canvas bug로 배경색을 넣어준다.
		window.setTimeout(function(){  
			if(svgElem != null && svgElem.length > 0)
			{
				setSvgCanvas(obj);			
			}
			setHtml2Canvas(svgElem, obj, id, idIdx, i, maxLength);
		}, delayTime);
	});
}

function setHeaderText()
{
	//pdf 헤더 문구 
	var startDt = document.pdfForm.pdfStartDt.value;
	var endDt = document.pdfForm.pdfEndDt.value;
	var mntrNm = ""; 
	if($("#excelBrandArray").val() != null && $("#excelBrandArray").val() != "")
	{
		mntrNm = $("#excelBrandArray").val();
	}
	else if($("#excelModelArray").val() != null && $("#excelModelArray").val() != "")
	{
		mntrNm = $("#excelModelArray").val();
	}
	else
	{
		mntrNm = document.pdfForm.mntrNm.value;
	}
	var today = getToday() + "";//int형을 String형으로 변환하기 위해 "" 추가
	var headerText = "<ul class='txtArea'>";
	
	if(mntrNm != null && mntrNm != "")
	{
		headerText += "<li><strong>" + mntrNm + "</strong>"; 
	}
	
	if(startDt != null && startDt != "")
	{
		headerText += " ( <strong> 조회기간 : </strong> " + startDt + " ~ ";  
	}
	
	if(endDt != null && endDt != "")
	{
		headerText += endDt + " ) </li>"; 
	}

	if(_userNm != null && _userNm != "")
	{
		headerText += "<li><strong> 출력자 : </strong> " + _userNm + "</li>"; 
	}
	
	if(today != null && today != "")
	{
		headerText += "<li><strong> 출력일자 : </strong> " + today.substring(0, 4) + "년 " + today.substring(4, 6) + "월 " + today.substring(6, 8) + "일</li>";
	}
	headerText += "</ul>";
	return headerText;
}

/**
 * @author      : Administrator
 * @date        : 2015. 10. 8.
 * @description : 프린트
 */
function getPrint(){
	alert("A4 기준으로 가로 방향, 여백 자동을 설정하여 출력해야 합니다.");
	window.print();
}



//엑셀 다운로드
function getExcel(targetId)
{
	document.excelForm.targetId.value = targetId;
    document.excelForm.menuNm.value = getMenuNm();
	document.excelForm.submit();
}


/*jsbak, 2015.10.14, 시작 로딩바 설정
 * classIdName : 클래스나 아이디를 jquery값 형식으로 가져와야 한다. 예: #idName
 * type : 로딩바를 붙이는 곳이 table에 있으면 table 표시 
 * colspan : table일때 가로 개수를 표시 
 * view : 화면 구분 layer, origin
 * paddingTop : 위 여백 사이즈
 * paddingBottom : 아래 여백 사이즈
 * name: 유일한 값을 넣으면 됨
 */
function startLoadingBar(classIdName, type, colspan, view, paddingTop, paddingBottom, name)
{
	var loaderImg = "loader_com.gif";
	if(paddingTop == null || paddingTop == "")
	{
		paddingTop = "40";
	}
	if(paddingBottom == null || paddingBottom == "")
	{
		paddingBottom = "40";
	}
	if(view == "layer")
	{
		loaderImg = "loader_layer.gif";
	}
	if(name == null){
		name="";
	}
	var tempHtml = "";
	if(type == null || type == ""){
		tempHtml = "<div id='loading"+name+"' style='position:relative; width:100%; height:15px; font-size:15px; line-height:15px; text-align:center; padding-top:" + paddingTop + "px; padding-bottom:" + paddingBottom + "px;'><img alt='loading' src='/images/common/" + loaderImg + "' style='vertical-align:middle;'/></div>";
	}else{
		tempHtml = "<div style='position:relative; width:100%; height:15px; font-size:15px; line-height:15px; text-align:center; padding-top:" + paddingTop + "px; padding-bottom:" + paddingBottom + "px;'><img alt='loading' src='/images/common/" + loaderImg + "' style='vertical-align:middle;'/></div>";
	}
	if(type == "table")
	{
		tempHtml = "<tr id='loading"+name+"'><td colspan='" + colspan + "'> " + tempHtml + " </td></tr>"; 
	}
	if(type == "ul")
	{
		tempHtml = "<li id='loading"+name+"'>" + tempHtml + "</li>";
	}
	if(type == "ul2")	//실시간 대시보드 원문보기 탭 사용후 css 강제 세팅, shseo
	{
		tempHtml = "<li id='loading"+name+"' style='width:633px;'>" + tempHtml + "</li>";
	}
	if(type == "keyword")	// 키워드랭킹용 추가
	{
		tempHtml = "<li id='loading"+name+"' style='text-align:center; float:none;'>" + tempHtml + "</li>";
		$(classIdName).empty();
		$(classIdName+":eq(0)").append(tempHtml);
	}else{
		$(classIdName).empty();
		$(classIdName).append(tempHtml);
	}
	$("#loading"+name).fadeIn("fast"); 
}
/*
 * jsbak, 2015.10.14, 종료 로딩바 설정
 * name: 유일한 값을 넣으면 됨
 */
function endLoadingBar(name)
{
	if(name == null){
		name="";
	}
	//$("#loading"+name).fadeOut("fast");
	$("#loading"+name).remove();
}
var asd;
var aed;
function setCal(dayType)
{
	var curDt = new Date();
	if(dayType == "1w")
	{
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()-6))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 			
	}
	else if(dayType == "1m")
	{
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth()-1, curDt.getDate()+1))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 		
	}
	else if(dayType == "3m")
	{
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth()-3, curDt.getDate()+1))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 		
	}
	else if(dayType == "6m")
	{
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth()-6, curDt.getDate()+1))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 		
	}
	else if(dayType == "1y")
	{
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear()-1, curDt.getMonth(), curDt.getDate()+1))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 
	}
	else if(dayType == "all")
	{
		$("#searchStartDt").val(asd); 		
		$("#searchEndDt").val(aed); 
//		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth()-18, curDt.getDate()))); 		
//		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 
	}	
}

function setCalTwo(dayType)
{
	var endDay = replaceAll(aed, '.', '');
	if(getToday() < Number(endDay)){
		endDay = $.datepicker.formatDate('yy.mm.dd', new Date());
	}else{
		endDay = aed;
	}
	var endDayArray  = endDay.split('.');
	var curDt = new Date(endDayArray[0], endDayArray[1]-1, endDayArray[2]);
	
	var strDt = new Date(endDayArray[0], endDayArray[1]-1, endDayArray[2]);
	if(dayType == "1w")
	{
		strDt.setDate(strDt.getDate()-6);
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(strDt.getFullYear(), strDt.getMonth(), strDt.getDate()))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()))); 			
	}
	else if(dayType == "1m")
	{
		strDt.setMonth(strDt.getMonth()-1);
		strDt.setDate(strDt.getDate()+1);
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(strDt.getFullYear(), strDt.getMonth(), strDt.getDate()))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()))); 		
	}
	else if(dayType == "3m")
	{
		strDt.setMonth(strDt.getMonth()-3);
		strDt.setDate(strDt.getDate()+1);
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(strDt.getFullYear(), strDt.getMonth(), strDt.getDate()))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()))); 		
	}
	else if(dayType == "6m")
	{
		strDt.setMonth(strDt.getMonth()-6);
		strDt.setDate(strDt.getDate()+1);
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(strDt.getFullYear(), strDt.getMonth(), strDt.getDate()))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()))); 		
	}
	else if(dayType == "1y")
	{
		strDt.setFullYear(strDt.getFullYear()-1);
		strDt.setDate(strDt.getDate()+1);
		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(strDt.getFullYear(), strDt.getMonth(), strDt.getDate()))); 		
		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth(), curDt.getDate()))); 
	}
	else if(dayType == "all")
	{
		$("#searchStartDt").val(asd); 		
		$("#searchEndDt").val(aed); 
//		$("#searchStartDt").val($.datepicker.formatDate('yy.mm.dd', new Date(curDt.getFullYear(), curDt.getMonth()-18, curDt.getDate()))); 		
//		$("#searchEndDt").val($.datepicker.formatDate('yy.mm.dd', new Date())); 
	}	
}


function addFavorite(){
	//var title = "Quick dictionary for Programmer - Qdic.kr";   
	//var url = "http://qdic.kr";
	var title = document.title; //현재 보고 있는 페이지의 Title
	var url = location.href; //현재 보고 있는 페이지의 Url
		 
	var browser=navigator.userAgent.toLowerCase();
 
	// Mozilla, Firefox, Netscape
	if (window.sidebar && window.sidebar.addPanel) {
		window.sidebar.addPanel(title, url,"");
	}// IE or chrome
	else if( window.external && ('AddFavorite' in window.external)) { 
		// IE 
		if (browser.indexOf('chrome')==-1){
			window.external.AddFavorite( url, title);
		} else {
		   // chrome
		   alert('CTRL+D 또는 Command+D를 눌러 즐겨찾기에 추가해주세요.');
		}
	}// Opera - automatically adds to sidebar if rel=sidebar in the tag	 
	else if(window.opera && window.print) { 
		return true;
	}// Konqueror
	else if (browser.indexOf('konqueror')!=-1) { 
		alert('CTRL+B를 눌러 즐겨찾기에 추가해주세요.');
	}// safari	 
	else if (browser.indexOf('webkit')!=-1){
		alert('CTRL+B 또는 Command+B를 눌러 즐겨찾기에 추가해주세요.');
	} else {
		alert('사용하고 계시는 브라우저에서는 이 버튼으로 즐겨찾기를 추가할 수 없습니다. 수동으로 링크를 추가해주세요.')
		 
	}
}


//숫자, 영문 조합 자리수 체크
function chkNumEn(str)
{
	if(!/^[a-zA-Z0-9]{8,12}$/.test(str))
    { 
        alert('비밀번호는 숫자와 영문자 조합으로 8~12자리를 사용해야 합니다.'); 
        return false;
    }

    var chk_num = str.search(/[0-9]/g); 
    var chk_eng = str.search(/[a-z]/ig);

    if(chk_num < 0 || chk_eng < 0)

    { 
        alert('비밀번호는 영대소문, 숫자 조합으로 입력해주시기 바랍니다.'); 
        return false;
    }
    /*
    if(/(\w)\1\1\1/.test(upw))

    {
        alert('비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.'); 
        return false;
    }

    if(upw.search(uid)>-1)

    {
        alert('ID가 포함된 비밀번호는 사용하실 수 없습니다.'); 
        return false;
    }
   */
    return true;
}

//오늘 날짜 가져오기
function getToday(){
	var date = new Date();
	var today = "";

	today = date.getFullYear();
	if((date.getMonth()+1) >= 10){
		today = today +""+ (date.getMonth()+1);
	}else{
		today = today+ "0" + (date.getMonth()+1);
	}
	if(date.getDate() >= 10){
		today = today +""+ date.getDate();
	}else{
		today = today + "0"+date.getDate();
	}
	return parseInt(today);
}

//조회기간 시작날짜 체크
function checkStartDate($date, dateText, asDate, aeDate ){
	var sdate = replaceAll($("input[name='searchStartDt']").val(),".","").substring(0,8);
	var edate = replaceAll($("input[name='searchEndDt']").val(),".","").substring(0,8);
	
	if(sdate==""||edate==""||asDate==""||aeDate==""){
		$date.val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));
		return;
	}
	if(asDate > dateText ){	//수집기간 내 인지 검사
		alert("분석 기간에 해당되지 않습니다.");
		dateText = asDate;
	}else if(dateText > aeDate){
		alert("분석 기간에 해당되지 않습니다.");
		dateText = aeDate;
	}
	
	if(dateText > getToday()){	//오늘날짜를 넘는지 검사
		alert("분석 기간에 해당되지 않습니다.");
		dateText = getToday();
	}
	
	if(dateText > edate){	//마지막날짜를 넘는지 검사
		//dateText = edate;
		$("input[name='searchEndDt']").val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));	//수집기간내 변경된 날짜인데 edate를 넘어서는 경우 제한이 아닌 edate가 sdate를 따라가도록 수정함, shseo, 2013.03.27
	}
	$date.val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));
}
//조회기간 마지막날짜 체크
function checkEndDate($date, dateText, asDate, aeDate ){
	var sdate = replaceAll($("input[name='searchStartDt']").val(),".","").substring(0,8);
	var edate = replaceAll($("input[name='searchEndDt']").val(),".","").substring(0,8);
	
	if(sdate==""||edate==""||asDate==""||aeDate==""){
		$date.val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));
		return;
	}
	if(asDate > dateText ){	//수집기간 내 인지 검사
		alert("분석 기간에 해당되지 않습니다.");
		dateText = asDate;
	}else if(dateText > aeDate){
		alert("분석 기간에 해당되지 않습니다.");
		dateText = aeDate;
	}
	
	if(dateText > getToday()){	//오늘날짜를 넘는지 검사
		alert("분석 기간에 해당되지 않습니다.");
		dateText = getToday()+""; //스트링으로 강제 형변환, shseo, 2012.12.26
	}
	
	if(dateText < sdate){	//시작날짜 이전인지 검사
		//dateText = sdate;
		$("input[name='searchStartDt']").val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));	//수집기간내 변경된 날짜인데 sdate를 이전일 경우 제한이 아닌 sdate가 edate를 따라가도록 수정함, shseo, 2013.03.27
	}
	$date.val(dateText.substring(0,4)+"."+dateText.substring(4,6)+"."+dateText.substring(6,8));
}
//달력 파라미터(조회기간설정의 시작날짜ID, 마지막날짜ID, 수집시작날짜, 수집마지막날짜)
function getDatePicker(analSDate, analEDate){
	asd = analSDate;	//전역변수 : 시작날짜 초기값 저장하기 위한 변수
	aed = analEDate;	//전역변수 : 끝 날짜 초기값 저장하기 위한 변수
	var sdate = "";
	var edate = "";
	var asDate = "";
	var aeDate = "";
	
	if(analSDate == null){
		analSDate = "";
	}
	if(analEDate == null){
		analEDate = "";
	}

	if($("input[name='searchStartDt']").val() != "" && $("input[name='searchEndDt']").val() != ""){
		sdate = replaceAll($("input[name='searchStartDt']").val(),".","").substring(0,8);
		edate = replaceAll($("input[name='searchEndDt']").val(),".","").substring(0,8);
		asDate = replaceAll(analSDate,".","").substring(0,8);
		aeDate = replaceAll(analEDate,".","").substring(0,8);
	}else {
		alert("날짜 입력 오류 발생");
		return false;
	}
	
	//조회기간설정의 시작날짜설정
	$("input[name='searchStartDt']").datepicker({
		showOn : "button",
		defaultDate : new Date( sdate.substring(0,4) , Number( sdate.substring(4,6) )-1 , sdate.substring(6,8) ),
		buttonImage : "/images/btn/btn_calendar.gif", //버튼이미지에 사용할 이미지 경로
		buttonImageOnly : true, //버튼이미지를 나오게 한다.
		buttonText: "조회시작날짜",
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월 한글로 출력
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월 한글로 출력
		dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'], // 요일 한글로 출력
		weekHeader: 'Wk',
		closeText: '닫기',
		nextText: '다음 달', // next 아이콘의 툴팁.
		prevText: '이전 달', // prev 아이콘의 툴팁.
		dateFormat : 'yymmdd', //데이터 포멧형식
		//minDate : interval1+'d', //오늘부터 과거 몇일까지 선택 할 수 있다.
		//maxDate : interval2+'d', //오늘부터 미래 몇일까지 선택 할 수 있다.
		changeYear : true, //년별로 선택 할 수 있다.
		changeMonth : true, //달별로 선택 할 수 있다.
		showMonthAfterYear : true, //년 뒤에 월 표시
		showOtherMonths : true, //이번달 달력안에 상/하 빈칸이 있을경우 전달/다음달 일로 채워준다.
		selectOtherMonths : true,
		numberOfMonths : 1, //오늘부터 3달치의 달력을 보여준다.
		showButtonPanel : true, //오늘 날짜로 돌아가는 버튼 및 닫기 버튼을 생성한다.
		onSelect : function(dateText){ checkStartDate($(this),dateText, asDate, aeDate ); }
	}).mask('9999.99.99');
	$("input[name='searchEndDt']").datepicker({
		showOn : "button",
		defaultDate : new Date( edate.substring(0,4) , Number( edate.substring(4,6) )-1 , edate.substring(6,8) ),
		buttonImage : "/images/btn/btn_calendar.gif", //버튼이미지에 사용할 이미지 경로
		buttonImageOnly : true, //버튼이미지를 나오게 한다.
		buttonText: "조회마지막날짜",
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월 한글로 출력
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월 한글로 출력
		dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'], // 요일 한글로 출력
		weekHeader: 'Wk',
		closeText: '닫기',
		nextText: '다음 달', // next 아이콘의 툴팁.
		prevText: '이전 달', // prev 아이콘의 툴팁.
		dateFormat : 'yymmdd', //데이터 포멧형식
		//minDate : interval1+'d', //오늘부터 과거 몇일까지 선택 할 수 있다.
		//maxDate : interval2+'d', //오늘부터 미래 몇일까지 선택 할 수 있다.
		changeYear : true, //년별로 선택 할 수 있다.
		changeMonth : true, //달별로 선택 할 수 있다.
		showMonthAfterYear : true, //년 뒤에 월 표시
		showOtherMonths : true, //이번달 달력안에 상/하 빈칸이 있을경우 전달/다음달 일로 채워준다.
		selectOtherMonths : true,
		numberOfMonths : 1, //오늘부터 3달치의 달력을 보여준다.
		showButtonPanel : true, //오늘 날짜로 돌아가는 버튼 및 닫기 버튼을 생성한다.
		onSelect : function(dateText){ checkEndDate($(this),dateText, asDate, aeDate ); }
	}).mask('9999.99.99');
	
}

function getDayDatePicker(targetId, htmlTargetId, startDayForDayId, endDayForDayId) {
	var startDate;
    var endDate;
    
    $('#'+targetId).datepicker( {
    	changeMonth: true,
        changeYear: true,
    	defaultDate: endSearchDate,
    	dateFormat: 'yy.mm.dd',
        showOtherMonths: true,
        selectOtherMonths: true,
        showOn : "button",
		buttonImage : __globalContextPath + "/images/btn/btn_calendar.gif", //버튼이미지에 사용할 이미지 경로
		buttonImageOnly : true, //버튼이미지를 나오게 한다.
		buttonText: "조회날짜",
        onSelect: function(dateText, inst) { 
        	onSelectEventForDay(targetId, htmlTargetId, startDayForDayId, endDayForDayId);
            
        },
        monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ], // 요일 한글로 출력
		nextText: '이전 일', // next 아이콘의 툴팁.
		prevText: '다음 일', // prev 아이콘의 툴팁.
        beforeShowDay: function(date) {
        	setTimeout(function(){
                $(".ui-datepicker").css("z-index", 2000);}, 10); 
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        onChangeMonthYear: function(year, month, inst) {
        }
    });
   
    $('.week-picker .ui-datepicker-calendar tr').bind('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.week-picker .ui-datepicker-calendar tr').bind('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
    
}

function getWeekDatePicker(targetId, htmlTargetId, startDayForWeekId, endDayForWeekId) {
	var startDate;
    var endDate;
    
    var selectCurrentWeek = function() {
        window.setTimeout(function () {
            $('.calendar').find('.ui-datepicker-current-day a').addClass('ui-state-active')
        }, 1);
    }
    
    $('#'+targetId).datepicker( {
    	changeMonth: true,
        changeYear: true,
    	defaultDate: endSearchDate,
    	dateFormat: 'yy.mm.dd',
        showOtherMonths: true,
        selectOtherMonths: true,
        showOn : "button",
		buttonImage : __globalContextPath + "/images/btn/btn_calendar.gif", //버튼이미지에 사용할 이미지 경로
		buttonImageOnly : true, //버튼이미지를 나오게 한다.
		buttonText: "조회날짜",
        onSelect: function(dateText, inst) { 
        	onSelectEventForWeek(targetId, htmlTargetId, startDayForWeekId, endDayForWeekId);
            
        },
        monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ], // 요일 한글로 출력
		nextText: '다음 주', // next 아이콘의 툴팁.
		prevText: '이전 주', // prev 아이콘의 툴팁.
        beforeShowDay: function(date) {
        	setTimeout(function(){
                $(".ui-datepicker").css("z-index", 2000);}, 10); 
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        onChangeMonthYear: function(year, month, inst) {
        }
    });
    
    $('.week-picker .ui-datepicker-calendar tr').bind('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.week-picker .ui-datepicker-calendar tr').bind('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
    
}

function getMonthDatePicker(targetId, htmlTargetId, startDayForMonthId, endDayForMonthId) {
	var startDate;
    var endDate;
    
    $('#'+targetId).datepicker( {
    	changeMonth: true,
        changeYear: true,
    	defaultDate: endSearchDate,
    	dateFormat: 'yy.mm.dd',
        showOtherMonths: true,
        selectOtherMonths: true,
        showOn : "button",
		buttonImage : __globalContextPath + "/images/btn/btn_calendar.gif", //버튼이미지에 사용할 이미지 경로
		buttonImageOnly : true, //버튼이미지를 나오게 한다.
		buttonText: "조회날짜",
        onSelect: function(dateText, inst) { 
        	onSelectEventForMonth(targetId, htmlTargetId, startDayForMonthId, endDayForMonthId);
            
        },
        monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ], // 월 한글로 출력
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ], // 요일 한글로 출력
		nextText: '다음 달', // next 아이콘의 툴팁.
		prevText: '이전 달', // prev 아이콘의 툴팁.
        beforeShowDay: function(date) {
        	setTimeout(function(){
                $(".ui-datepicker").css("z-index", 2000);}, 10); 
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        changeMonth: true,
        changeYear: true,
        onChangeMonthYear: function(year, month, inst) {
        }
    });
   
    $('.week-picker .ui-datepicker-calendar tr').bind('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.week-picker .ui-datepicker-calendar tr').bind('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
    
}
//날짜 보정
function changeDatePickerDate(day) {
	if(day == 0) {
		return 6;
	} else if(day == 6) {
		return 7;
	} else {
		return day - 1;
	}
}

function moveDayDatePicker(targetId, htmlTargetId, startDayForDayId, endDayForDayId, type) {
	var date;
	var minDate;
	var maxDate;
	
	if($("#"+ targetId).datepicker("getDate") == null) {
		$("#"+ targetId).datepicker("setDate", new Date());
	}
	
	date = $("#"+ targetId).datepicker("getDate");
	
	var year = date.getFullYear();
	var month = date.getMonth();
	var date = date.getDate();

	if(type == 'pre') {
		if((new Date(year, month, date -1)).getTime() >= startDateForDatepicker.getTime()) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month, date -1)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		} 
		
	} else if(type == 'next') {
		if((new Date(year, month, date +1)).getTime() <= endDateForDatepicker.getTime()) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month, date +1)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		}
		
	} else {
		
	}
	onSelectEventForDay(targetId, htmlTargetId, startDayForDayId, endDayForDayId);
	
}

function moveWeekDatePicker(targetId, htmlTargetId, startDayForWeekId, endDayForWeekId, type) {
	var date;
	
	if($("#"+ targetId).datepicker("getDate") == null) {
		$("#"+ targetId).datepicker("setDate", new Date());
	}
	
	date = $("#"+ targetId).datepicker("getDate");
	var modifyDay = changeDatePickerDate(date.getDay());
	var year = date.getFullYear();
	var month = date.getMonth();
	var date = date.getDate();

	if(type == 'pre') {
		
		if((new Date(year, month, date - modifyDay -1)).getTime() >= startDateForDatepicker.getTime()) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month, date -7)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		} 
		
	} else if(type == 'next') {
		if((new Date(year, month, date - modifyDay +6)).getTime() <= endDateForDatepicker.getTime()) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month, date +7)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		}
		
	} else {
		
	}
	
	onSelectEventForWeek(targetId, htmlTargetId, startDayForWeekId, endDayForWeekId);
	
}

function moveMonthDatePicker(targetId, htmlTargetId, startDayForMonthId, endDayForMonthId, type) {
	var date;
	
	if($("#"+ targetId).datepicker("getDate") == null) {
		$("#"+ targetId).datepicker("setDate", new Date());
	}
	
	date = $("#"+ targetId).datepicker("getDate");
	
	
	var year = date.getFullYear();
	var month = date.getMonth();
	var date = date.getDate();

	//date.getFullYear(), date.getMonth(), 1
	
	if(type == 'pre') {
		if((new Date(year, month -1, 1)).getTime() >= new Date(startDateForDatepicker.getFullYear(), startDateForDatepicker.getMonth(), 1)) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month - 1, 1)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		} 
		
	} else if(type == 'next') {
		if((new Date(year, month +1, 1)).getTime() <= new Date(endDateForDatepicker.getFullYear(), endDateForDatepicker.getMonth(), 1)) {
			$("#"+ targetId).val($.datepicker.formatDate('yy.mm.dd', new Date(year, month + 1, 1)));
			
		} else {
			alert("분석기간에 해당되지 않습니다.");
			return false;
		}
		
	} else {
		
	}
	
	onSelectEventForMonth(targetId, htmlTargetId, startDayForMonthId, endDayForMonthId);
	
}

function onSelectEventForDay(targetId, htmlTargetId, startDayForDayId, endDayForDayId) {
	var html ="";
	var date = $("#" + targetId).datepicker('getDate');
	var inst = $("#" + targetId).datepicker();
    startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate());
    endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate());
    var dateFormat = 'yy.mm.dd';
    
    html = html + $.datepicker.formatDate(dateFormat, startDate);
    $("#" + htmlTargetId).text(html);
    $("#" + startDayForDayId).val($.datepicker.formatDate(dateFormat, startDate));
    $("#" + endDayForDayId).val($.datepicker.formatDate(dateFormat, endDate));
    
    window.setTimeout(function () {
        $('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
    }, 1);
    
    $("#dailySearchButton").click();
    
    
}

function onSelectEventForWeek(targetId, htmlTargetId, startDayForWeekId, endDayForWeekId) {
	var html ="";
	var date = $("#" + targetId).datepicker('getDate');
	var inst = $("#" + targetId).datepicker();
    startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - changeDatePickerDate(date.getDay()) );
    endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - changeDatePickerDate(date.getDay()) + 6);
    var dateFormat = 'yy.mm.dd';
    
    html = html + $.datepicker.formatDate(dateFormat, startDate) + " ~ " + $.datepicker.formatDate(dateFormat, endDate);
    $("#" + htmlTargetId).text(html);
    
    //시작 날짜 분기 처리
    if(startDate.getTime() <= startDateForDatepicker.getTime()) {
    	$("#" + startDayForWeekId).val($.datepicker.formatDate(dateFormat, startDateForDatepicker));
    	
    } else {
    	$("#" + startDayForWeekId).val($.datepicker.formatDate(dateFormat, startDate));

    }
    
    //마지막 날짜 분기 처리
    if(endDate.getTime() >= endDateForDatepicker.getTime()) {
    	$("#" + endDayForWeekId).val($.datepicker.formatDate(dateFormat, endDateForDatepicker));
    	
    } else {
    	$("#" + endDayForWeekId).val($.datepicker.formatDate(dateFormat, endDate));
    	
    }
    
    window.setTimeout(function () {
        $('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
    }, 1);
    
    $("#weeklySearchButton").click();
}

function onSelectEventForMonth(targetId, htmlTargetId, startDayForMonthId, endDayForMonthId) {
	var html ="";
	var date = $("#" + targetId).datepicker('getDate');
	var inst = $("#" + targetId).datepicker();
    startDate = new Date(date.getFullYear(), date.getMonth(), 1 );
    endDate = new Date(date.getFullYear(), date.getMonth() + 1, 0 );
	
    var dateFormat = 'yy.mm';
    var monthDateFormat = 'yy.mm.dd';
    
    html = $.datepicker.formatDate(dateFormat, startDate);
    $("#" + htmlTargetId).text(html);
    
    //시작 날짜 분기 처리
    if(startDate.getTime() <= startDateForDatepicker.getTime()) {
    	$("#" + startDayForMonthId).val($.datepicker.formatDate(monthDateFormat, startDateForDatepicker));
    	
    } else {
    	$("#" + startDayForMonthId).val($.datepicker.formatDate(monthDateFormat, startDate));

    }
    
    //마지막 날짜 분기 처리
    if(endDate.getTime() >= endDateForDatepicker.getTime()) {
    	$("#" + endDayForMonthId).val($.datepicker.formatDate(monthDateFormat, endDateForDatepicker));
    	
    } else {
    	$("#" + endDayForMonthId).val($.datepicker.formatDate(monthDateFormat, endDate));
    	
    }
    
    window.setTimeout(function () {
        $('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
    }, 1);
    
    $("#monthlySearchButton").click();
}

//페이징 처리
//예시
/*
pager({
	pageSize:5,
	listSize:10,
	pageNum:1,
	totalCount:parseInt(totalCount),
	container:"#account_top5_sub #pager2",
	callback:function(opt){ 
		$container.init_Top5(2);
	}
});
*/
function pager(arg){
	function paging (opt){
		var options = $.extend({
			pageSize:5,
			listSize:10,
			totalCount:1,
			pageNum:1,
			callback:function(e){ }
		}, opt);
		
		var $container = $(opt.container);
		var page_size 	= options.pageSize; 
		var list_size 		= options.listSize;
		var total_cnt 		= options.totalCount;
		var page_num  = options.pageNum;
		var numBegin 	= 0;
		var maxSize 		= 0;
		var isFit  			= total_cnt % list_size == 0;
		var lastPage = 0;	//마지막페이지 저장용 변수, shseo, 2013.03.12
		
		$container.empty();
		if(total_cnt == 0){
			return false;
		}
		numBegin = ((parseInt(page_num / page_size) - ((page_num % page_size) == 0 ? 1 : 0)) * page_size) + 1;
		maxSize  = parseInt(total_cnt / list_size) + (isFit ? 0 : 1);
		lastPage = maxSize;	//마지팍 페이지 저장함, shseo, 2013.03.12
		
		if(maxSize > numBegin + page_size - 1){
			maxSize = numBegin + page_size - 1; 
		}
		$("<a href='#none' style='cursor:pointer;' class='prevEnd'>FIRST</a>").appendTo($container);
		$("<a href='#none' style='cursor:pointer;' class='prev'>PREV</a>").appendTo($container).after("&nbsp;");
		
		for(var i = numBegin ; i <= maxSize ; i++){
			if(i == page_num){
				$("<strong class='on'>"+i+"</strong>").appendTo($container).after("&nbsp;");
			}else{
				$("<a href='#none' style='cursor:pointer;' seq="+i+"><em>" + i + "</em></a>").click(function(e){
					if($.isFunction(options.callback)){
						options.callback($(this).attr("seq"));					
					}
				}).appendTo($container).after("&nbsp;");
			}
		}
		$("<a href='#none' style='cursor:pointer;' class='next'>NEXT</a>").appendTo($container);
		$("<a href='#none' style='cursor:pointer;' class='nextEnd'>END</a>").appendTo($container);
		
		
	/*	if(numBegin > 1){
			$wrap.find("a").first()
			.before("<a style='cursor:pointer;' class='prevEnd'>FIRST</a>" + 첫페이지이동 화살표 추가, shseo, 2013.03.12
					"<a style='cursor:pointer;' class='prev'>PREV</a>");
		}
//		if((maxSize - (numBegin - 1)) >= page_size){
		if((total_cnt/list_size) > maxSize){	//pager 수정 - shSeo
			$wrap.find("a").last()
			.after("<a style='cursor:pointer;' class='next'>NEXT</a>" +
					"<a style='cursor:pointer;' class='nextEnd'>END</a>"); 마지막페이지이동 화살표 추가, shseo, 2013.03.12
		}*/
		
		$container.find(".prev").click(function(){
			if((numBegin-1) > 0){
				options.callback(numBegin - 1);
			}
		});
		
		$container.find(".next").click(function(){
			if( (numBegin + page_size) <= lastPage ){
				options.callback(numBegin + page_size);
			}
		});
		
		$container.find(".prevEnd").click(function(){ /*첫페이지 이동 화살표 누를시 실행될 함수 추가, shseo, 2013.03.12*/
			options.callback('1');
		});
		
		$container.find(".nextEnd").click(function(){ /*마지막페이지 이동 화살표 누를시 실행될 함수 추가, shseo, 2013.03.12*/
			options.callback(lastPage);
		});
		
		//$container.empty().append($wrap);
		
		this.beginNum = function(){
			return numBegin - 1;
		};
		
		this.endNum = function(){
			return numBegin - 1 + page_size;
		};
		
		this.setTotalCount = function (totCnt){
			if(totCnt < page_size){
				$container.empty();
			}	
		};
	}
	
	var $wrapper = $(arg.container);
	
	$wrapper.data("pager", new paging(arg));
	
	return $wrapper.data("pager");
}

function commonJson(url, param, callback) {
//	if(param.targetId) startLoadingBar("#"+param.targetId,null,null,null,null,null,"1");
	$.ajax({
	    url: url,
	    dataType: "json",
	    data : param,
	    async : false,
	    success: function( data ) {
//	    	endLoadingBar("1");
	    	if(param.targetId) {
	    		if(data.result) {
	    			if(data.result == 'error') {
	    				$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">네트워크 통신 오류입니다.</p></div>');
	    				$("#figureLegendImage").hide();
	    			} else if(data.result == 'nothing') {
	    				if(param.grade){
	    					$("#"+param.targetId).html("");
	    					if(param.targetId == "issueData"){
	    						$("#"+param.targetId).html('<h4 class="title">SNS <br>이슈도</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} else if(param.targetId == "mediaData"){
	    						$("#"+param.targetId).html('<h4 class="title">미디어 <br>파급력</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} else if(param.targetId == "goodData"){
	    						$("#"+param.targetId).html('<h4 class="title">SNS <br>호감도</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} 
	    				} else if(param.dual){ 
	    					$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    					$("#"+param.targetId2).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    				} else if(param.dashboardRanking){
	    					$("#"+param.targetId).html("");
	    					if(param.targetId == "noDataDaily"){
	    						$("#"+param.targetId).html('<dl class="day"><dt>일간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} else if(param.targetId == "noDataWeekly"){
	    						$("#"+param.targetId).html('<dl class="week"><dt>주간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} else if(param.targetId == "noDataMonthly"){
	    						$("#"+param.targetId).html('<dl class="month"><dt>월간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} 
	    				}else if(param.text) {
	    					$("#"+param.targetId).html("");
	    					$("#"+param.targetId).html('데이터가 없습니다.');
	    				} else {
	    					$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    				}
	    			}
	    			return false;
	    		}
	    	}  
	    	callback(data);
	    },
		error : function(data)
		{
			//alert("에러가 발생하였습니다.");	
		}
	});
	
}

//비동기
function commonJsonTrue(url, param, callback) {
	var type = "";
	var colspan = 0;
	if(param.targetId == "modelRankGrid")
	{
		type = "table";
		colspan = 9;
	}
	if(param.targetId) startLoadingBar("#"+param.targetId, type, colspan, "", 200, 100, param.targetId);
	$.ajax({
	    url: url,
	    dataType: "json",
	    data : param,
	    async : true,
	    success: function( data ) {
	    	endLoadingBar(param.targetId);
	    	if(param.targetId) {
	    		if(data.result) {
	    			if(data.result == 'error') {
	    				$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">네트워크 통신 오류입니다.</p></div>');
	    			} else if(data.result == 'nothing') {
	    				if(param.grade){
	    					$("#"+param.targetId).html("");
	    					if(param.targetId == "issueData"){
	    						$("#"+param.targetId).html('<h4 class="title">SNS <br>이슈도</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} else if(param.targetId == "mediaData"){
	    						$("#"+param.targetId).html('<h4 class="title">미디어 <br>파급력</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} else if(param.targetId == "goodData"){
	    						$("#"+param.targetId).html('<h4 class="title">SNS <br>호감도</h4><div class="noData"><p class="txt">데이터가 없습니다.</p></div>');
	    					} 
	    				} else if(param.dual){ 
	    					$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    					$("#"+param.targetId2).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    				} else if(param.dashboardRanking){
	    					$("#"+param.targetId).html("");
	    					if(param.targetId == "noDataDaily"){
	    						$("#"+param.targetId).html('<dl class="day"><dt>일간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} else if(param.targetId == "noDataWeekly"){
	    						$("#"+param.targetId).html('<dl class="week"><dt>주간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} else if(param.targetId == "noDataMonthly"){
	    						$("#"+param.targetId).html('<dl class="month"><dt>월간 자동 빈출 키워드</dt><dd class="top"><strong class="noData">데이터가 없습니다.</strong></dd></dl>');
	    					} 
	    				}else if(param.text) {
	    					$("#"+param.targetId).html("");
	    					$("#"+param.targetId).html('데이터가 없습니다.');
	    				} else {
	    					$("#"+param.targetId).html('<div class="noData"><span class="icon"></span><p class="txt">데이터가 없습니다.</p></div>');
	    				}
	    			}
	    			return false;
	    		}
	    	}  
	    	callback(data);
	    },
		error : function(data)
		{
			//alert("에러가 발생하였습니다.");	
		}
	});
	
}


//로그인
function doLogin()
{
	layerOpen("loginLayer");
}

function getOs()
{
	var userOs = "";
	if(UserAgent.indexOf("NT 5.0") != -1 ) 
	{
    	userOs = "Windows 2000";
    }
    else if( UserAgent.indexOf("NT 5.1") != -1 ) 
    {
    	userOs = "Windows XP";
    }
    else if( UserAgent.indexOf("NT 5.2") != -1 ) 
    {
    	userOs = "Windows Server 2003";
    }
    else if( UserAgent.indexOf("NT 6.0") != -1 )
    {
    	userOs = "Windows Vista";
    }
    else if( UserAgent.indexOf("NT 6.1") != -1 ) 
    {
    	userOs = "Windows 7 or Server 2008";
    }
    else if( UserAgent.indexOf("NT 6.2") != -1 ) 
    {
    	userOs = "Windows 8 or Server 2012";
    }
    else if( UserAgent.indexOf("98") != -1 )
    {
    	userOs = "Windows 98";
    }
    else if( UserAgent.indexOf("95") != -1 ) 
    {
    	userOs = "Windows 95";
    }
    else if( UserAgent.indexOf("Linux") != -1 ) 
    {
    	userOs = "Linux";
    }
    else if( UserAgent.indexOf("Mac") != -1 ) 
    {
    	userOs = "Mac";
    }
    else
    {
    	userOs = "I Don't Know OS";
    }
	return userOs;
	
}


