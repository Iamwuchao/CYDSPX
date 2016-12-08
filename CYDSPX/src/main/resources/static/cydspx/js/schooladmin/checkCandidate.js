

$(document).ready(function(){
	
	
	var showCandidate = null;
	var originCandidateList = [{
			id:"1",
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

	function getCandidate(){
			$.ajax({
				url:"/cydspx/getCandidateList",
				type:"get",
				dataType:"json",
				success:function(data){
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
	getCandidate();
	
	
	$("#addfile").click(function(){
    	$.ajax({
			url : '',
			type : 'get',
			dataType : 'text',
			success : function(data) {	
				console.log("success");
				$("#fileInput").show();
				$("#attachement_file").fileinput({
					language : 'zh',
					allowedFileExtensions : ["pdf"], 
					uploadUrl : "/cydspx/attachmentfile",
					uploadAsync : true,
					browseClass:"btn btn-primary",
					uploadClass:"btn btn-success",
					removeClass:"btn btn-danger",
					showPreview : false,
					showUpload: true, //是否显示上传按钮
				});
				//绑定上传文件的事件回调
				initFileInputEvent();
			},
			error : function(data){
				console.log(00);
			}
		});
    });
	
	function initFileInputEvent() {
		$("#attachement_file").on("fileuploaded", function(event, data, previewId, index) {
			var rep = data.response;
			if(rep == null || rep.code == "1") {
				alert("附件上传失败，请重新尝试！");
				return;
			}
			//设置附件ID隐藏表单域
			$("#attachementFileId").val(rep.message);
			alert("附件上传成功！");
		});
		$("#attachement_file").on("fileuploaderror", function(event, data, msg) {
			$("#attachementFileId").val("");
			alert("附件上传错误！"+msg);
		} );
	}
	
	$("#submitBtn").click(function(){
		console.log($("#attachementFileId").val());
		var fileId = $("#attachementFileId").val();
		if(!fileId){
			alert("请上传汇总表！");
		}
		$.ajax({
			url:"/cydspx/saveSummarize",
			type:"POST",
			dataType:"json",
			data:{
				fileDir:fileId,
			},
			success:function(data) {
				//清空，附件ID
				alert("提交成功");
				$("#attachementId").val("");
				if(data.code == 0) {
					console.log(data.message);
				}	
				else
					console.log(data.message);
			},
			error:function() {
				alert("汇总表上传失败，请重新尝试！");
			}
		});
	});
});