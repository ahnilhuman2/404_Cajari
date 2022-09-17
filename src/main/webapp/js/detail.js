// ** qna_detail에 쓰였음

$(
   function(){
      // 현재 글의 id 값 -> dto(t404_qna_write).id
      const id = $("input[name='id']").val().trim();
      
      // 현재 글 읽어오기
      // 여기서 data란 변수 선언함
      loadComment(id);
      
      
       // 댓글 작성 버튼 누르면 댓글 등록 하기.  
       // 출처:qna_detail.jsp->qnacomment.jsp->#btn_comment
       $("#btn_comment").click(function(){
      
          // 입력한 값
          // 출처:qna_detail.jsp->qnacomment.jsp->#input_comment
           const content = $("#input_comment").val().trim();
   
           // 검증
           if(!content){
               alert("댓글 입력을 하세요");
               return;
           }
           
           // 전달할 parameter 준비
           const data = {
            // 1. 어느글에 대한 댓글
            "qna_id": id,
            // 2. 어느 사용자가 작성한 댓글
            "user_id": logged_id,
            // 3. 댓글 내용은 무엇
            "content": content,
         };
         
         $.ajax({
            url: conPath + "/qnacomment/write",
            type: "POST",
            data: data,
            cache: false,
               success : function(data, status, xhr){
                   if(status == "success") {
                       //alert(xhr.responseText);  // JSON 데이터 확인용.
                       if(data.status !== "OK"){
                           alert(data.status) ;
                           return;
                       }
                       
                       loadComment(id);  // 댓글 목록 다시 업데이트
                       $("#input_comment").val('');  // 입력 비우기
                   }
               }
         });
      });
   });



// 특정 글 (qna_id) 의 댓글 목록 읽어오기

   function loadComment(qna_id){
      $.ajax({
         url: conPath + "/qnacomment/list?id=" + qna_id,
         type: "GET",
         cache: false,
         success: function(data, status){
            // 정상적으로 response가 되었다면(200)
            // data == t404_qna_comment
            if(status == "success"){
               // 서버측 에러 메세지 있는 경우
               if(data.status !== "OK"){
                  alert(data.status);
                  return;
               }
               // data 필터링
               buildComment(data);  
                   addDelete();
            }
         },
      });
   }; // end loadComment()

// json data 같은 경우는 js object로 파싱되어서 받는다.

   function buildComment(result){
      $("#cmt_cnt").text(result.count);   // 댓글 총 개수
      
      const out = [];
      
      // 필터링
      result.data.forEach(comment => {
         // comment == dto
           let id = comment.id;
           let content = comment.content.trim();
           let regdate = comment.regDate;
   
         // 외래키
           let user_id = parseInt(comment.user.id);
           let username = comment.user.username;
           let name = comment.user.name;
           
           // 삭제버튼 여부 : 작성자 본인의 댓글인 경우에만 보이기
           // === 값 & 타입 비교
           const delBtn = (logged_id !== user_id) ? '' : `
                   <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip"
                       data-cmtdel-id="${id}" title="삭제"></i>
               `;
               
           const row = `
              <tr>
              <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
              <td>
                  <span>${content}</span>${delBtn}            
              </td>
              <td><span><small class="text-secondary">${regdate}</small></span></td>
              </tr>      
              `;
         out.push(row);      
      });
      
      $("#cmt_list").html(out.join("\n"));
   }; // end buildComment()

// 댓글 삭제버튼이 눌렸을때. 해당 댓글 삭제하는 이벤트를 삭제버튼에 등록

   function addDelete(){
   
       // 현재 글의 id -> qna_detail.jsp에서 detail.js 사용함 -> t404_qna_write의 id
       const id = $("input[name='id']").val().trim();
   
       $("[data-cmtdel-id]").click(function(){
           if(!confirm("댓글을 삭제하시겠습니까?")) return;
   
           // 삭제할 댓글의 id
           const comment_id = $(this).attr("data-cmtdel-id")
   
           $.ajax({
               url :  conPath + "/qnacomment/delete",
               type : "POST",
               cache : false,
               data : {"id": comment_id},
               success : function(data, status, xhr){
                   if(status == "success") {
                       if(data.status !== "OK"){
                           alert(data.status) ;
                           return;
                       }
                       // 삭제후에도 다시 목록 불러와야 한다.
                       loadComment(id);
                   }
               }
           });
       });
   }; // end addDelete()










