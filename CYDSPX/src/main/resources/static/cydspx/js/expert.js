var graded = 1;//已评价
var notgraded = 2;//未评价

function getungradedcandidatetable(){
	$.ajax({
		url : '/cydspx/notgradecandidatetable',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#candidateTable_div").html(data);
			updatecandidateList();
		}
	});
}

function getgradedcandidatetable(){
	$.ajax({
		url : '/cydspx/gradedcandidatettable',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#candidateTable_div").html(data);
			updateCandidateList();
		}
	});
}

function updateCandidateList(){
	var val = $("#selectkind").val();
	if(val == graded){
		$.ajax({
			url:'/cydspx/notgradecandidatetable',
			type:'get',
			datatype:'json',
			success:function(data){
				
			}
		});
	}
	else if(val == notgraded){
		$.ajax({
			url:'/cydspx/notgradecandidatetable'
		});
	}
	
}
