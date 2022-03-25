function backToList(obj){
       obj.action="${contextPath}/boardFree/listArticles.do";
       obj.submit();
     }
     
    function fn_enable(obj){
       document.getElementById("fr_title").disabled=false;
       document.getElementById("fr_context").disabled=false;
       document.getElementById("tr_btn_modify").style.display="block";
       document.getElementById("fr_view_btn").style.display="none";
       fn_tr_modEnable();
    }
    
    function fn_tr_modEnable(){
       x=document.getElementsByClassName("tr_modEnable");
       for(var i =0; i < x.length; i++){
          x[i].style.display="block";
       }
    }
    
    
    function fn_modify_article(obj){
       obj.action="${contextPath}/boardFree/modArticle.do";
       obj.submit();
    }
    
    function fn_remove_article(url,fr_NO){
       var form = document.createElement("form");
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
        var fr_NOInput = document.createElement("input");
        fr_NOInput.setAttribute("type","hidden");
        fr_NOInput.setAttribute("name","fr_NO");
        fr_NOInput.setAttribute("value", fr_NO);
       
        form.appendChild(fr_NOInput);
        document.body.appendChild(form);
        form.submit();
    
    }
    

    function readURL(input, index) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview'+index).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    
/*    var cnt = 1;
      function fn_addFile() {
         $("#d_file").append(
               "<br>" + "<input type='file' name='up_fileName"+cnt+"' />");
         cnt++;
      }*/
      
      function fn_removeModImage(_up_fileNO, _fr_NO, _up_fileName, rowNum){
          alert(rowNum);
             $.ajax({
                type:"post",
                async:false,  
                url:"http://localhost:8090/GJ/boardFree/removeModImage.do",
                dataType:"text",
                data: {up_fileNO : _up_fileNO,  fr_NO : _fr_NO, up_fileName : _up_fileName},
                success:function (result, textStatus){
                   if(result == 'success'){
                	      
                       alert("파일을 삭제했습니다.");
                     
                      location.href="http://localhost:8090/GJ/boardFree/viewArticle.do?removeCompleted=true&fr_NO=" + _fr_NO;
                             
                    $('#tr_'+rowNum).remove();
                   $('#tr_sub'+rowNum).remove();
                  
                   
              	 
                     
                   }else{
                      alert("다시 시도해 주세요");
                   }
                },
             error:function(data,textStatus){
                alert("에러가 발생했습니다.");ㅣ
             },
             complete:function(data,textStatus){
            
             }
          });  //end ajax   
       }
       
       var pre_img_num = 0;  //수정 이전의 이미지 수
       var img_index = 0;     //수정 후 이미지 수 
       
       var isFirstAddImage = true;
       function fn_addModImage(_img_index){
          
          if(isFirstAddImage == true){
             pre_img_num = _img_index;
             img_index = ++_img_index;
             isFirstAddImage = false;
          }else{
             ++img_index;    
          }
          
          var innerHtml = "";
          innerHtml +='<tr  width=200px  align=center>';
          innerHtml +='<td >'+
                            "<input  type=file  name=up_fileName" + img_index + "  onchange='readURL(this,"+ img_index+")'   />"+
                         '</td>';
          innerHtml +='<td>'+
                        "<img  id=preview"+img_index+"  />"+
                               '</td>';
          innerHtml +='</tr>';
          $("#tb_addImage").append(innerHtml);
      
          $("#added_img_num").val(img_index);  // 추가된 이미지수를 히든 태그에 저장해서 컨트롤러로 보낸다.
       }