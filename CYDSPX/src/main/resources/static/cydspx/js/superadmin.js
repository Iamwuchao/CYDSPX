function showCandidateManagepage(){
	$.ajax({
		url:'/cydspx/allocationpage',
		type:'get',
		dataType:'text',
		success:function(data){
			$("#candidateTable").html(data);
		}
		
	});
}

function candidateGrouping(){
	$.ajax({
		url:'/cydspx/superAdmin/allocation',
		type:'get',
		dataType:'json',
		success:function(data){
			//$("#contains").html(data);
			//getProjectList(kind);
			alert(data.responseMessage);
		}
		
	});
}

function getPage(pageName)
{
// 	alert(pageName);
	$.ajax({
	      url: '/cydspx/superAdmin/getPage',
	      type: 'get',
	      dataType: 'text',
	      data: {"pageName":pageName},
	      
	      success: function(data){
// 	    	  alert(data)
	    	  $("#contains").html(data);
	      }
	    });
	
}