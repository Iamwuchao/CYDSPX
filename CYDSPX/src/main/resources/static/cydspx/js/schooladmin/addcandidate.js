$(document).ready(function(){
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
    $(document).on("click", "#uploadPicBtn", function(){
        var imgPath = $("#uploadPic").val();
        if(imgPath === ""){
            alert("请选择上传图片");
            return;
        }
        var imgSuffix = imgPath.substr(imgPath.lastIndexOf(".") + 1).toLowerCase();
        if(imgSuffix !== "jpg"){
            alert("请选择JPG格式图片文件");
            $("#uploadPic").val("");
            return;
        }
        var img = new Image();
        img.src = imgPath;
        console.log(img.width);
        console.log(img.height);
        $.ajax({
            type: "POST",
            url: "",
            data: {imgPath: imgPath},
            cache: false,
            success: function(data){
                alert("上传成功");
                $("#pic").attr("src", data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert("上传失败，请检查网络后重试");
            }
        });
    });
    //上传简历文件
    $(document).on("click", "#uploadFileBtn", function(){
        var filePath = $("#uploadFile").val();
        if(filePath === ""){
            alert("请选择上传文件");
            return;
        }
        var fileSuffix = filePath.substr(filePath.lastIndexOf(".")+1).toLowerCase();
        if(fileSuffix !== "word" && fileSuffix !== "pdf"){
            alert("请选择word或pdf格式文件");
            $("#uploadFile").val("");
            return;
        }
        $.ajax({
        	url:"",
        	type:"post",
        	data:{filePath:filePath},
        	success:function(){
        		alert("文件上传成功！");
        	}
        });
    });
    
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
    var vue = new Vue({
        el: ".content",
        data: {
//        	name:"",
//        	sex: "男",
            sexList: sexList,
//            nation:"汉族",
            nationList:[],
//            birthday:"",
            politics:"中共产党",
//            politicsList:politicsList,
//            state:"中华人民共和国",
            stateList:[],
//            cert_type:"中华人民共和国居民身份证",
            cert_typeList:cert_typeList,
//            cert_no:"",
//            address:"",
//            postal_code:"",
//            mobile_phone:"",
//            tel_phone:"",
//            email:"",
//            edu_type:"",
            edu_typeList:[],
//            edu_hierarchy:"",
            edu_hierarchyList:[],
//            subject_category:"",
            subject_categoryList:[],
//            degree_type:"",
            degree_typeList:[],
//            academy_name:"",
//            specialty_name:"",
//            vocation:"",
            vocationList:[],
//            job:"",
//            title:"",
            titleList:[],
//            workunit:"",
//            origin_recommand:"",
//            service_intention:[],
            service_intentionList:service_intentionList,
//            resume:"",
//            achievement1:"",
//            achievement2:"",
//            achievement3:"",
//            prize_year1:"",
//            prize_year2:"",
//            prize_year3:"",
//            prize_level1:"",
//            prize_level2:"",
//            prize_level3:"",
            prize_levelList:prize_levelList,
//            project_name1:"",
//            project_name2:"",
//            project_name3:"",
//            elect_year1:"",
//            elect_year2:"",
//            elect_year3:"",
//            elect_level1:"",
//            elect_level2:"",
//            elect_level3:"",
            elect_levelList:elect_levelList
        }
    });
//    var param = {
//   		name:vue.name,
//       	sex: vue.sex,
//        nation:vue.nation,
//        birthday:vue.birthday,
//        politics:vue.politics,
//        state:vue.state,
//        cert_type:vue.cert_type,
//        cert_no:vue.cert_no,
//        address:vue.address,
//        postal_code:vue.postal_code,
//        mobile_phone:vue.mobile_phone,
//        tel_phone:vue.tel_phone,
//        email:vue.email,
//        edu_type:vue.edu_type,
//        edu_hierarchy:vue.edu_hierarchy,
//        subject_category:vue.subject_category,
//        degree_type:vue.degree_type,
//        academy_name:vue.academy_name,
//        specialty_name:vue.specialty_name,
//        vocation:vue.vocation,
//        job:vue.job,
//        title:vue.title,
//        workunit:vue.workunit,
//        origin_recommand:vue.origin_recommand,
//        service_intention:vue.service_intention,
//        resume:vue.resume,
//        achievement1:vue.achievement1,
//        achievement2:vue.achievement2,
//        achievement3:vue.achievement3,
//        prize_year1:vue.prize_year1,
//        prize_year2:vue.prize_year2,
//        prize_year3:vue.prize_year3,
//        prize_level1:vue.prize_level1,
//        prize_level2:vue.prize_level2,
//        prize_level3:vue.prize_level3,
//        project_name1:vue.project_name1,
//        project_name2:vue.project_name2,
//        project_name3:vue.project_name3,
//        elect_year1:vue.elect_year1,
//        elect_year2:vue.elect_year2,
//        elect_year3:vue.elect_year3,
//        elect_level1:vue.elect_level1,
//        elect_level2:vue.elect_level2,
//        elect_level3:vue.elect_level3,
//    };
    $(document).on("click", "#submitBtn", function(){
    	alert($("#applyForm").serialize());
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
			achievement:{
				required:true,
				maxlength:200
			},
			prize_year:{
				required:true,
				year:true
			},
			prize_level:{
				required:true,
			},
			project_name:{
				required:true,
				maxlength:200
			},
			elect_year:{
				required:true,
				year:true
			},
			elect_level:{
				required:true
			}
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
        submitHandle:function(form){
            var param = ($("#applyForm").serialize());
        	$.ajax({
        		url:"",
        		type:"post",
        		dataType:"json",
        		data:param,
        		success:function(){
        			alert("提交成功！");
        		}
        	});
        }
    });
    $.validator.addMethod("postcode", function(value, ele, params){
        var postcode = /^[0-9]{6}$/;
        return this.optional(ele) || (postcode.test(value));
    }, "请填写正确的邮编！");
    $.validator.addMethod("birthday", function(value, ele, params){
        var birthday = /^[1-2]\\d{3}(0?[1-9]||1[0-2])(0?[1-9]||[1-2][1-9]||3[0-1])$/;
        return this.optional(ele) || (birthday.test(value));
    }, "格式必须如20161007");
    $.validator.addMethod("year", function(value, ele, params){
        var year = /^[1-2]\\d{3}$/;
        return this.optional(ele) || (year.test(value));
    }, "格式必须如2016");
    $.validator.addMethod("tel_phone", function(value, ele, params){
    	var tel_phone = /^[0-9]{3,5}-[0-9]{7-10}$/;
    	return this.optional(ele) || (tel_phone.test(value));
    }, "格式必须为“区号-电话号码”")

});