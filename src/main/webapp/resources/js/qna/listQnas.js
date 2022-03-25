function fn_QnaForm(isLogOn, QnaForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = QnaForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/qna/QnaForm.do';
		}
	}
	
	
	// 모달창 보이기
	function fn_modalOpen(no) {
		var modal = document.getElementById('qna_modal');
		modal.style.display = 'block';
		qna_no.value = Number(no);
	}
	
	// 모달창 감추기
	function fn_modalClose() {
		var modal = document.getElementById('qna_modal');
		modal.style.display = 'none';
	}