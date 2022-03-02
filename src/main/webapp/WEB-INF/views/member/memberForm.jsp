<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
    <c:set var="result" value="${param.id }" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 </title>
<script>
function fn_overlapped(){
    var _id=$("#_member_id").val();
    if(_id==''){
       alert("ID를 입력하세요");
       return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
              alert("사용할 수 있는 ID입니다.");
              $('#btnOverlapped').prop("disabled", true);
              $('#_member_id').prop("disabled", true);
              $('#member_id').val(_id);
          }else{
             alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax    
 }   
</script>

</head>
<body>
   <form method="post"  action="${contextPath}/member/addMember.do">
   <h1 style="text-align:center">회원 등록창 </h1>
   <table align="center">
      <tr class="dot_line">
               <td class="fixed_join">아이디</td>
               <td>
                 <input type="text" name="_member_id"  id="_member_id"  size="20" />
                 <input type="hidden" name="member_id"  id="member_id" />
                 
                 <input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
               </td>
            </tr>
      <tr>
         <td width="200"><p align="right">비밀번호</p></td>
         <td width="400"><input type="password" name="member_pw"></td>
      </tr>
      <tr>
         <td width="200"><p align="right">이름</p></td>
         <td width="400"><input type="text" name="member_name"></td>
      </tr>
      <tr>
         <td width="200"><p align="right">성별</p></td>
         <td width="400"><input type="text" name="member_gender"></td>
      </tr>
     
       <tr>
         <td width="200"><p align="right">생일</p></td>
         <td width="400"><input type="text" name="member_birth"></td>
      </tr>   
      <tr>
         <td width="200"><p align="right">휴대번호</p></td>
         <td width="400"><input type="text" name="member_phoneno"></td>
      </tr>
      <tr>
         <td width="200"><p align="right">이메일</p></td>
         <td width="400"><input type="text" name="member_email"></td>
      </tr>
      <tr>
         <td width="200"><p align="right">주소</p></td>
         <td width="400"><input type="text" name="member_address"></td>
      </tr>
            
         <td width="200"><p>&nbsp;</p></td>
         <td width="400">
         <input type="submit" value="가입하기">
         <input type="reset" value="다시입력">
         </td>
      </tr>
   </table>
   </form>
</body>
</html> --%>

<!-- bookshop꺼 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('roadAddress').value = fullRoadAddr;
						document.getElementById('jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
						} else {
							document.getElementById('guide').innerHTML = '';
						}

					}
				}).open();
	}

	function fn_overlapped() {
		var _id = $("#_member_id").val();
		if (_id == '') {
			alert("ID를 입력하세요");
			return;
		}
		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/member/overlapped.do",
			dataType : "text",
			data : {
				id : _id
			},
			success : function(data, textStatus) {
				if (data == 'false') {
					alert("사용할 수 있는 ID입니다.");
					$('#btnOverlapped').prop("disabled", true);
					$('#_member_id').prop("disabled", true);
					$('#member_id').val(_id);
				} else {
					alert("사용할 수 없는 ID입니다.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
			}
		}); //end ajax	 
	}
</script>
</head>
<body>
	<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">
		<div id="detail_table">
			<table>
				<tbody>
					<tr class="dot_line">
						<td class="fixed_join">아이디</td>
						<td><input type="text" name="_member_id" id="_member_id"
							size="20" /> <input type="hidden" name="member_id"
							id="member_id" /> <input type="button" id="btnOverlapped"
							value="중복체크" onClick="fn_overlapped()" /></td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">비밀번호</td>
						<td><input name="member_pw" type="password" size="20" /></td>
					</tr>

					<tr class="dot_line">
						<td class="fixed_join">이름</td>
						<td><input name="member_name" type="text" size="20" /></td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">성별</td>
						<td><input type="radio" name="member_gender" value="F" />
							여<span style="padding-left: 120px"></span> <input type="radio"
							name="member_gender" value="M" checked />남</td>
					</tr>

					<tr>
						<td width="200"><p align="right">생년월일</p></td>
						<td width="400"><input type="text" name="member_birth"></td>
					</tr>
					<!-- <tr>
						<td width="200"><p align="right">성별</p></td>
						<td width="400"><input type="text" name="member_gender"></td>
					</tr> -->
					<tr>
						<td width="200"><p align="right">휴대번호</p></td>
						<td width="400"><input type="text" name="member_phoneno"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">이메일</p></td>
						<td width="400"><input type="text" name="member_email"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">주소</p></td>
						<td width="400"><input type="text" name="member_address"></td>
					</tr>

					<td width="200"><p>&nbsp;</p></td>
					<td width="400"><input type="submit" value="가입하기"> <input
						type="reset" value="다시입력"></td>
					</tr>

				</tbody>
			</table>
		</div>
		<div class="clear">
			<br> <br>
			<table align=center>
				<tr>
					<td><input type="submit" value="회원 가입"> <input
						type="reset" value="다시입력"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>