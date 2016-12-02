$(document).ready(function(){
    var vue = null;
    var url = "/cydspx/attachment";
    var imgpath = "/cydspx/image/candidate.jpg";

	$.ajax({
		url:"/cydspx/candidate/getFormChoices",
		type: "get",
		datatype:"json",
		success:initFun
	});
	function initFun(data){
	    vue = new Vue({
	        el: ".content",
	        data: {
	        	picpath:imgpath,
	            sexList: sexList,
	            nationList:data.nations,
	            politicsList:politicsList,
	            stateList:data.stateList,
	            cert_typeList:cert_typeList,
	            edu_typeList:data.edu_typeList,
	            edu_hierarchyList:data.edu_hierarchyList,
	            subject_categoryList:data.subject_categories,
	            degree_typeList:data.degree_types,
	            vocationList:data.vocations,
	            titleList:data.titles,
	            service_intentionList:data.services,
	            prize_levelList:prize_levelList,
	            elect_levelList:elect_levelList
	        }
	    });
    }
    // 添加获奖信息和参评信息的按钮点击事件
    $(document).on("click", ".add-btn", function(){
        var $parent = $(this).parent();
        if($parent.find(".row:visible").length<6){
            var $rows = $parent.find(".row");
            for(var i=0;i<6;i+=2){
                var $cur = $($rows[i]);
                if($cur.css("display") === "none"){
                    $cur.show("slow");
                    $cur.next().show("slow");
                    return;
                }
            }
        }
    });
    // 获奖信息和参评信息的删除按钮的点击事件
    $(document).on("click", ".delete-btn", function(){
    	$(this).parents(".row").prev().hide("slow");
        $(this).parents(".row").hide("slow");
    });
    // 获奖信息和参评信息的删除按钮的mouseenter和mouseleave事件控制按钮的显示和隐藏
    $(document).on("mouseenter", ".special-fieldset .row", function(){
        $(this).find(".delete-btn").show();
    });
    $(document).on("mouseleave", ".special-fieldset .row", function(){
        $(this).find(".delete-btn").hide();
    });
    //上传头像
    $("#addpic").click(function(){
    	$.ajax({
			url : '',
			type : 'get',
			dataType : 'text',
			success : function(data) {	
				console.log("success");
				$("#picInput").show();
				$("#attachement_pic").fileinput({
					language : 'zh',
					allowedFileExtensions : ["jpg"], 
					uploadUrl : url+"pic",
					uploadAsync : true,
					browseClass:"btn btn-primary",
					uploadClass:"btn btn-success",
					removeClass:"btn btn-danger",
					showPreview : false,
					showUpload: true, //是否显示上传按钮
					minFileSize:5.0,
					maxFileSize:80.0,
					minImageHeight:100,
					maxImageHeight:640,
					minImageWidth:90,
					maxImageWidth:480
				});
				//绑定上传文件的事件回调
				initPicInputEvent();
			},
			error : function(data){
				console.log(00);
			}
		});
    });
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
					uploadUrl : url+"file",
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
    function initPicInputEvent() {
		$("#attachement_pic").on("fileuploaded", function(event, data, previewId, index) {
			var rep = data.response;
			if(rep == null || rep.code == "1") {
				alert("头像上传失败，请重新尝试！");
				return;
			}
			//设置附件ID隐藏表单域
			$("#attachementPicId").val(rep.message);
			alert("头像上传成功！");
			var path = url + "/"+ rep.message + "/";
			vue.picpath = path;
		});
		$("#attachement_pic").on("fileuploaderror", function(event, data, msg) {
			$("#attachementPicId").val("");
			alert("头像上传错误！"+msg);
		} );
	}
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
    
    var sexList = [
        {value:1,text:"男"},
        {value:2,text:"女"}
    ];
    var cert_typeList = [
        {value:1,text:"中华人民共和国居民身份证"},
        {value:2,text:"港澳居民来往内地通行证"},
        {value:3,text:"台湾居民来往大陆通行证"},
        {value:4,text:"护照"}
    ];
    var politicsList = [
        {value:1,text:"中共产党"},
        {value:2,text:"中共预备党员"},
        {value:3,text:"共青团员"},
        {value:4,text:"民革会员"},
        {value:5,text:"民盟盟员"},
        {value:6,text:"民建会员"},
        {value:7,text:"民进党员"},
        {value:8,text:"农工党党员"},
        {value:9,text:"致公党党员"},
        {value:10,text:"九三学社社员"},
        {value:11,text:"台盟盟员"},
        {value:12,text:"无党派民主人士"},
        {value:13,text:"群众"},
    ];
    var service_intentionList = [
        {value:"openclass", text:"开课"},
        {value:"lecture", text:"讲座"},
        {value:"juge", text:"担任评委"},
        {value:"direct", text:"指导帮扶"},
        {value:"other", text:"其他"},
    ];
    var prize_levelList = [
        {value:"country", text:"国家"},
        {value:"province", text:"省级"},
    ];
    var elect_levelList = [
        {value:"country", text:"国家"},
        {value:"province", text:"省级"},
        {value:"college", text:"校级"}
    ]; 
    var stateList = [{text:"中国"},{text:"美国"}];
    var edu_typeList = ["硕士","博士"];
    var edu_hierarchyList = [{text:"高级"},{text:"中级"}];
    function initFun(data){
	    var vue = new Vue({
	        el: ".content",
	        data: {
	            sexList: sexList,
	            nationList:data.nations,
	            politicsList:politicsList,
	            stateList:stateList,
	            cert_typeList:cert_typeList,
	            edu_typeList:edu_typeList,
	            edu_hierarchyList:edu_hierarchyList,
	            subject_categoryList:data.subject_categories,
	            degree_typeList:data.degree_types,
	            vocationList:data.vocations,
	            titleList:data.titles,
	            service_intentionList:data.services,
	            prize_levelList:prize_levelList,
	            elect_levelList:elect_levelList
	        }
	    });
    }
    $(document).on("click", "#submitBtn", function(){
    	var param = ($("#applyForm").serialize());
    	$.ajax({
    		url:"/cydspx/candidate/addCandidate",
    		type:"get",
    		data:param,
    		success:function(){
    			alert("提交成功！");
    		}
    	});
    });
    
    //表单验证
    $("#applyForm").validate({
        rules: {
        	name:{
                required:true,
                maxlength:80
            },
			sex:{
			    required:true,
			    maxlength:4
			},
			nation:{
			    required:true,
			    maxlength:20
			},
			birthday:{
			    required:true,
			    birthday:true
			},
			politics:{
			    required:true,
			    maxlength:40
			},
			state:{
			    required:true,
			    maxlength:100
			},
			cert_type:{
			    required:true,
			    maxlength:60
			},
			cert_no:{
			    required:true,
			    maxlength:18
			},
			address:{
			    maxlength:200
			},
			postal_code:{
			    postcode: true
			},
			mobile_phone:{
			    required:true,
			    maxlength:11
			},
			tel_phone:{
			    required:true,
			    tel_phone:true
			},
			email:{
			    required:true,
			    email:true,
			    maxlength:50
			},
			edu_type:{
				maxlength:4
			},
			edu_hierarchy:{
				maxlength:80
			},
			subject_category:{
				maxlength:40
			},
			degree_type:{
				maxlength:40
			},
			academy_name:{
				maxlength:80
			},
			specialty_name:{
				maxlength:80
			},
			vocation:{
				required:true,
				maxlength:100
			},
			job:{
				required:true,
				maxlength:100
			},
			title:{
				maxlength:40
			},
			workunit:{
				required:true,
				maxlength:100
			},
			origin_recommand:{
				required:true,
				maxlength:100
			},
			service_intention:{
				required:true,
				maxlength:5
			},
			resume:{
				required:true,
				maxlength:800
			},
			achievement1:{
				required:true,
				maxlength:200
			},
			prize_year1:{
				required:true,
				year:true
			},
			prize_level1:{
				required:true,
			},
			achievement2:{
				required:true,
				maxlength:200
			},
			prize_year2:{
				required:true,
				year:true
			},
			prize_level2:{
				required:true,
			},
			achievement3:{
				required:true,
				maxlength:200
			},
			prize_year3:{
				required:true,
				year:true
			},
			prize_level3:{
				required:true,
			},
			project_name1:{
				required:true,
				maxlength:200
			},
			elect_year1:{
				required:true,
				year:true
			},
			elect_level1:{
				required:true
			},
			project_name2:{
				required:true,
				maxlength:200
			},
			elect_year2:{
				required:true,
				year:true
			},
			elect_level2:{
				required:true
			},
			project_name3:{
				required:true,
				maxlength:200
			},
			elect_year3:{
				required:true,
				year:true
			},
			elect_level3:{
				required:true
			},
        },
        messages:{
        	name:{
                required:"必须填写姓名",
                maxlength:"不能超过40字"
            },
			sex:{
			    required:"必须填写性别",
			    maxlength:"不能超过2个字"
			},
			nation:{
			    required:"必须填写民族",
			    maxlength:"不能超过10个字"
			},
			birthday:{
			    required:"必须填写出生日期",
			},
			politics:{
			    required:"必须填写政治面貌",
			    maxlength:"不能超过20个字"
			},
			state:{
			    required:"必须填写国家或地区",
			    maxlength:"不能超过50个字"
			},
			cert_type:{
			    required:"必须填写证件类型",
			    maxlength:"不能超过30个字"
			},
			cert_no:{
			    required:"必须填写证件号码",
			    maxlength:"不能超过18个字符"
			},
			address:{
			    maxlength:"不能超过100个字"
			},
			postal_code:{
			},
			mobile_phone:{
			    required:"必须填写移动电话",
			    maxlength:"不能超过11个字符"
			},
			tel_phone:{
			    required:"必须填写固定电话",
			},
			email:{
			    required:"必须填写邮箱",
			    maxlength:"不能超过50个字符",
			    email:"请填写正确邮箱"
			},
			edu_type:{
				maxlength:"不能超过2个字"
			},
			edu_hierarchy:{
				maxlength:"不能超过40个字"
			},
			subject_category:{
				maxlength:"不能超过20个字"
			},
			degree_type:{
				maxlength:"不能超过20个字"
			},
			academy_name:{
				maxlength:"不能超过40个字"
			},
			specialty_name:{
				maxlength:"不能超过40个字"
			},
			vocation:{
				required:"必须填写行业",
				maxlength:"不能超过50个字"
			},
			job:{
				required:"必须填写职务",
				maxlength:"不能超过50个字"
			},
			title:{
				maxlength:"不能超过20个字"
			},
			workunit:{
				required:"必须填写工作单位",
				maxlength:"不能超过50个字"
			},
			origin_recommand:{
				required:"必须填写原始推荐单位",
				maxlength:"不能超过50个字"
			},
			service_intention:{
				required:"必须填写服务意向",
				maxlength:"最多可以选5项"
			},
			resume:{
				required:"必须填写工作简历",
				maxlength:"最多填写400个字"
			},
			achievement:{
				required:"必须填写成果名称",
				maxlength:"不能超过100个字"
			},
			prize_year:{
				required:"必须填写获奖年份",
			},
			prize_level:{
				required:"必须选择级别",
			},
			project_name:{
				required:"必须填写项目名称",
				maxlength:"不能超过100个字"
			},
			elect_year:{
				required:"必须填写参与评审年份",
			},
			elect_level:{
				required:"必须选择级别"
			}
        },
        errorPlacement:function(error, element){
        	error.appendTo(element.parent());
        	error.addClass("errormessage");
        },
//        submitHandle:function(form){
//            var param = ($("#applyForm").serialize());
//        	$.ajax({
//        		url:"/cydspx/candidate/addCandidate",
//        		type:"get",
//        		data:param,
//        		success:function(){
//        			alert("提交成功！");
//        		}
//        	});
//        }
    });
    $.validator.addMethod("postcode", function(value, ele, params){
        var postcode = /^[0-9]{6}$/;
        return this.optional(ele) || (postcode.test(value));
    }, "请填写正确的邮编！");
    $.validator.addMethod("birthday", function(value, ele, params){
        var birthday = /^[1-2][0-9]{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][1-9]|3[0-1])$/;
        return this.optional(ele) || (birthday.test(value));
    }, "格式必须如20161007");
    $.validator.addMethod("year", function(value, ele, params){
        var year = /^[1-2][0-9]{3}$/;
        return this.optional(ele) || (year.test(value));
    }, "格式必须如2016");
    $.validator.addMethod("tel_phone", function(value, ele, params){
    	var tel_phone = /^[0-9]{3,5}-[0-9]{7,10}$/;
    	return this.optional(ele) || (tel_phone.test(value));
    }, "格式必须为“区号-电话号码”")

});
