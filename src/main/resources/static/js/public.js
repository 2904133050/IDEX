
$(document).ready(function(){
	//获得文本框对象
	var t = $("#item_num");
	//初始化数量为1,并失效减
	$('#jian').attr('disabled',true);
	//数量增加操作   
	$("#jia").click(function(){
		// 给获取的val加上绝对值，避免出现负数   
		t.val(Math.abs(parseInt(t.val()))+1);
		if (parseInt(t.val())!=1){     
			$('#jia').attr('disabled',false);  
		}; 
})   
//数量减少操作   
$("#jian").click(function(){  
	t.val(Math.abs(parseInt(t.val())-1));  
	if (parseInt(t.val())==1){   
		t.val(0); 
	};   
}) 
});





