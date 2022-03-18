// 우편번호 검색 기능
        function execDaumPostcode() {
            new daum.Postcode(
                {
                    oncomplete: function (data) {
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
                        document.getElementById('member_zipcode').value = data.zonecode; //5자리 새우편번호 사용
                        document.getElementById('member_roadAddress').value = fullRoadAddr;
                        document.getElementById('member_jibunAddress').value = data.jibunAddress;

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

        // 아이디 중복체크 기능
        function fn_overlapped() {
            var _id = $("#_member_id").val();
            if (_id == '') {
                alert("ID를 입력하세요");
                return;
            }
            $.ajax({
                type: "post",
                async: false,
                url: "${contextPath}/member/overlapped.do",
                dataType: "text",
                data: {
                    id: _id
                },
                success: function (data, textStatus) {
                    if (data == 'false') {
                        alert("사용할 수 있는 ID입니다.");
                        $('#btnOverlapped').prop("disabled", true);
                        $('#_member_id').prop("disabled", true);
                        $('#member_id').val(_id);
                    } else {
                        alert("이미 사용 중인 ID입니다.");
                    }
                },
                error: function (data, textStatus) {
                    alert("에러가 발생했습니다.");
                    ㅣ
                },
                complete: function (data, textStatus) {
                    //alert("작업을완료 했습니다");
                }
            }); //end ajax    
        }


        // 이메일 주소 선택 - 자동입력 기능
        function emailSelect() {
            $('#member_email2').val($('#_member_email2').val())
        }


        //
        // 빈칸 유효성 검사
        function formCheck(form) {
            if (form.member_id.value == "") {
                alert('아이디를 입력하세요.')
                form._member_id.focus();
                return false;
            }

            if (form.member_pw.value == "") {
                alert('비밀번호를 입력하세요.')
                form.member_pw.focus();
                return false;
            }

            if (form.member_name.value == "") {
                alert('이름을 입력하세요.')
                form.member_name.focus();
                return false;
            }
            if (form.member_birth.value == "") {
                alert('생년월일을 입력하세요.')
                form.member_birth.focus();
                return false;
            }
            if (form.member_phoneno.value == "") {
                alert('휴대전화번호를 입력하세요.')
                form.member_phoneno.focus();
                return false;
            }
            if (form.member_email1.value == "" || form.member_email2.value == "") {
                alert('이메일을 입력하세요.')
                form.member_email1.focus();
                return false;
            }
            if (form.member_zipcode.value == "" || form.member_roadAddress.value == "") {
                alert('우편번호와 도로명 주소를 입력하세요.')
                form.member_roadAddress.focus();
                return false;
            }

        }
