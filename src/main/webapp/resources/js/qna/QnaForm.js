
	// 체크박스 체크 여부로 비밀번호란 활성화-비활성화
	$(function (){
		$("#qna_secret").change(function(){
			if(this.checked){
				$("input#qna_pw").prop("disabled", false);
			} else {
				$("input#qna_pw").prop("disabled", true);
			}
		});
	});

	
	// 목록으로 돌아가기
	function backToList(obj) {
		if (confirm("글 작성을 취소하고 목록으로 돌아가시겠습니까?") == true) {
			window.location.href = "${contextPath}/qna/listQnas.do";
		} else {
			return false;
		}
	}

	// 제목, 내용 필수 alert
	function QnaformCheck(form) {
		var pw_chkBox = document.getElementById('qna_secret').checked;

		if (form.qna_title.value == "") {
			alert("제목 입력은 필수입니다.")
			form.qna_title.focus();
			return false;
		}
		if (pw_chkBox == true && form.qna_pw.value == ""){
			alert("비밀글로 등록하려면 비밀번호를 입력하세요.")
			form.qna_pw.focus();
			return false;
		}
		if (pw_chkBox == false && form.qna_pw.value != ""){
			alert("비밀글로 등록하려면 비밀글 여부에 체크해주세요.")
			form.qna_secret.focus();
			return false;
		}
		if (form.qna_content.value == "") {
			alert("내용 입력은 필수입니다.")
			form.qna_content.focus();
			return false;
		}
		if (form.qna_policy.value == "none") {
			alert("상담 받을 정책 분야를 선택해주십시오.")
			form.qna_policy.focus();
			return false;
		}
	}
	