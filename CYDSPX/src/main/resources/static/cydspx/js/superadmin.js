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
		type:'post',
		dataType:'json',
		success:function(data){
			//$("#contains").html(data);
			//getProjectList(kind);
			alert(data.message);
		}
		
	});
}

function setCandidateManageInfo(){
	var groupSize = $("#judgeGroupCount").val();
	$.ajax({
		url:'/cydspx/superAdmin/setgroupsize',
		tyep:'post',
		data:{
			"groupSize":groupSize
		},
		dataType:'json',
		success:function(data){
			//alert(data.message);
			candidateGrouping();
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
	    	  $("#candidateTable").html(data);
	      }
	    });
}

var originalCandidateList = [{
	name:"",
	workunit:"",
	job:"",
	title:"",
	attachment:""
}];

var showCandidate;

function getCandidateList(){
	var count =  $("#count").val();
	console.log("count "+count);
	if(count==null || count==""){
		alert("请先设置导出个数");
		return ;
	}
	$.ajax({
		url:'/cydspx/superadmin/resultlist',
		type:'post',
		dataType:'json',
		data:{
			"count":count
		},
		success:function(data){
			//showCandidate.mounted=generateDoc();
			showCandidate.candidateList = data;
			
		}
		});
}

function getResultPage(){
	$.ajax({
		url:'/cydspx/superadmin/resultpage',
		type:'get',
		dataType:'text',
		success:function(page){
			$("#candidateTable").html(page);
			showCandidate =   new Vue({
    			el:'#candidateTable',
    			data:{
    				candidateList:originalCandidateList
    			}
    		/*	methods:{
    				push:function(){
    					this.$nextTick(function(){
    						generateDoc();
    					});
    				}
    			}*/
    		});
		}
	});
}

function generateDoc(){
	console.log("###### generateDoc");
	var htmlContent = $("#htmlContent").html();
	$.ajax({
		url:"/cydspx/superadmin/generatedoc",
		type:'post',
		dataType:'json',
		data:{
			"htmlContent":htmlContent
		},
		success:function(data){
			//$('#download').attr('href',"/cydspx/attachment/"+data.message+"/");
			window.location.href="/cydspx/attachment/"+data.message+"/";

		}
	});
}
