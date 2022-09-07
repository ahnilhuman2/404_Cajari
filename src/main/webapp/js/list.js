$(function(){
	$("[name='pageRows']").change(function(){
		
		//alert($(this).val());  // 확인용
		
		let frm = $("[name='frmPageRows']");
		frm.attr("method", "POST");
		frm.attr("action", "pageRows");  // /board/pageRows
		frm.submit();
	});
});