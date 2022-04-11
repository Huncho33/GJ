function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	var cnt = 1;
	function fn_addFile() {
		$("#d_file").append(
				"<br>" + "<input type='file' name='up_fileName"+cnt+"' />");
		cnt++;
	}