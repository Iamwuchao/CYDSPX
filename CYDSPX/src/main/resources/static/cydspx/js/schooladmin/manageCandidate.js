
function removeCandidate(object){
	var candidateId = $(object).attr("id");
	
	$.ajax({
		url:"/cydspx/candidate/removecandidate",
		type:"post",
		data:{
			candidateId:candidateId
		},
		dataType:"json",
		success:function(response){
			alert(response.message);
			window.location="/cydspx/schoolAdmin/managecandidate";
		}
		
	});
}


