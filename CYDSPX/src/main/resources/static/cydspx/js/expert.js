var graded = "已评审";//已评价
var notgraded = "未评审";//未评价
var showCandidate;
var originalCandidateList = [{
	name:"name",
	workunit:"dlut",
	job:"job",
	title:"title",
	attachment:"a.doc",
}]

function showCandidateTable(){
	var val = $("#selectkind").val();
	var urlTable="";
	var urlList = "";
	if(val == graded){
		urlTable = '/cydspx/gradedcandidatettable';
		urlList='/cydspx/gradedcandidatelist';
	}
	else if(val == notgraded){
		urlTable="/cydspx/notgradecandidatetable";
		urlList="/cydspx/ungradedcandidatelist";
	}
	
	getgradedcandidatetable(urlTable,urlList);
}

/*
 * 显示未评分的候选人列表
 */
function getgradedcandidatetable(urlTable,urlList){
	$.ajax({
		url : urlTable,
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#candidateTable").html(data);
			updateCandidateList(urlList);
		}
	});
}



function updateCandidateList(urlList){
	
		$.ajax({
			url:urlList,
			type:'get',
			datatype:'json',
			success:function(data){
				showCandidate =   new Vue({
	    			el:'#candidateTable',
	    			data:{
	    				candidateList:originalCandidateList
	    			}
	    		});
				
				for(index in data){
					if(data[index].attachment==null || data[index].attachment==""){
						data[index].attachment="#";
					}
					else
						data[index].attachment = "/cydspx/attachment/" + data[index].attachment+"/";
				}
				showCandidate.candidateList = data;
			}
		});
}

function grade(object){
	var candidateId = $(object).attr("id");
	var score = $("#"+"score_"+candidateId).val();
	$.ajax({
		url:"/cydspx/gradeforcandidate",
		type:"post",
		dataType:"json",
		data:{
			candidateId:candidateId,
			score:score
		},
		success:function(response){
			//if(response.)
		//	alert(response.message);
			//updateProjectList();
			if(response.code==0)
				showCandidateTable();
			else
				alert(response.message);
		}
	});
}

function setZero(object){
	var candidateId = $(object).attr("id");
	var score = -100;//$("#"+"score_"+candidateId).val();
	$.ajax({
		url:"/cydspx/gradeforcandidate",
		type:"post",
		dataType:"json",
		data:{
			candidateId:candidateId,
			score:score
		},
		success:function(response){
			//if(response.)
		//	alert(response.message);
			//updateProjectList();
			if(response.code==0)
				showCandidateTable();
			else
				alert(response.message);
		}
	});
}


function set100(object){
	var candidateId = $(object).attr("id");
	var score = 100;//$("#"+"score_"+candidateId).val();
	$.ajax({
		url:"/cydspx/gradeforcandidate",
		type:"post",
		dataType:"json",
		data:{
			candidateId:candidateId,
			score:score
		},
		success:function(response){
			//if(response.)
		//	alert(response.message);
			//updateProjectList();
			if(response.code==0)
				showCandidateTable();
			else
				alert(response.message);
		}
	});
}

