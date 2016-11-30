$(document).ready(function(){
	var originCandidateList = [{
		name:"name",
		sex:"sex",
		birthday:"birthday",
		mobile_phone:"mobile_phone",
		email:"email",
		workunit:"workunit",
		origin_recommand:"origin_recommand",
		job:"job",
		title:"title"
	}];
	
	var showCandidate = null;
	getCandidate();
	console.log("fsfsfs");
	function getCandidate(){
		$.ajax({
			url:"/cydspx/getCandidateList",
			type:"get",
			dataType:"json",
			success:function(data){
				console.log("success");
				console.log(data);
				showCandidate = new Vue({
					el:"#candidateTable",
					data:{
						candidateList:originCandidateList
					}
				});
				showCandidate.candidateList = data;
			}
		});
	}
});