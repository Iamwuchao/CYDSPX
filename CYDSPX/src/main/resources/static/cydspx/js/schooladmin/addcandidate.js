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
    });
    //表单提交
    $("#applyForm").validate({
        rules: {
            candidatename:{
                required:true,
                maxlength:40
            },
            // sex:{
            //     required:true,
            //     maxlength:2
            // },
            // nation:{
            //     required:true,
            //     maxlength:10
            // },
            // birthday:{
            //     required:true,
            //     birthday:true
            // },
            // politics:{
            //     required:true
            // },
            // country:{
            //     required:true
            // },
            // certificatetype:{
            //     required:true
            // },
            // certificatenum:{
            //     required:true,
            //     maxlength:18
            // },
            // address:{
            //     maxlength:100
            // },
            // postcode:{
            //     postcode: true
            // },
            // mobilephone:{
            //     required:true,
            //     maxlength:100
            // },
            // telephone:{
            //     required:true,
            //     maxlength:13
            // },
            // email:{
            //     required:true,
            //     email:true,
            //     maxlength:50
            // },
            // college:{
            //     maxlength:40
            // },
            // major:{
            //     maxlength:40
            // },
            // duty:{
            //     required:true,
            //     maxlength:50
            // },
            // serve:{
            //     required:true,
            //     maxlength:5
            // },
            // workunit:{
            //     required:true,
            //     maxlength:50
            // },
            // recommend:{
            //     required:true,
            //     maxlength:50
            // },
            // experience:{
            //     required:true,
            //     maxlength:400
            // },
            // achievementname:{
            //     required:true,
            //     maxlength:100
            // },
            // year:{
            //     required:true,
            //     year:true
            // },
            // projectname:{
            //     required:true,
            //     maxlength:100
            // },
        },
        messages:{
            candidatename:{
                required:"必须填写候选专家的姓名",
                maxlength:40
            },
        }
    });
    $.validator.addMethod("postcode", function(value, ele, params){
        var postcode = /^[0-9]{6}$/;
        return this.optional(ele) || (postcode.test(value));
    }, "请填写正确的邮编！");
    $.validator.addMethod("birthday", function(value, ele, params){
        var birthday = /^[1-2]\\d{3}(0?[1-9]||1[0-2])(0?[1-9]||[1-2][1-9]||3[0-1])$/;
        return this.optional(ele) || (birthday.test(value));
    }, "请填写正确的出生日期");
    $.validator.addMethod("year", function(value, ele, params){
        var year = /^[1-2]\\d{3}$/;
        return this.optional(ele) || (year.test(value));
    }, "请填写正确年份");
    var sex = [
        {value:1,text:"男"},
        {value:2,text:"女"}
    ];
    var certificate = [
        {value:1,text:"中华人民共和国居民身份证"},
        {value:2,text:"港澳居民来往内地通行证"},
        {value:3,text:"台湾居民来往大陆通行证"},
        {value:4,text:"护照"}
    ];
    var politics = [
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
    var serve = [
        {value:"openclass", text:"开课"},
        {value:"lecture", text:"讲座"},
        {value:"juge", text:"担任评委"},
        {value:"direct", text:"指导帮扶"},
        {value:"other", text:"其他"},
    ];
    var prizegrade = [
        {value:"country", text:"国家"},
        {value:"province", text:"省级"},
    ];
    var electgrade = [
        {value:"country", text:"国家"},
        {value:"province", text:"省级"},
        {value:"college", text:"校级"}
    ];
    var vue = new Vue({
        el: ".content",
        data: {
            sexSelected: 1,
            sex: sex,
            certificateSelected: 1,
            certificate:certificate,
            time: "20161124",
            politicsSelected: 1,
            politics: politics,
            serve:serve,
            servechecked: [],
            prizegrade: prizegrade,
            prizepicked1: "country",
            prizepicked2: "country",
            prizepicked3: "country",
            electgrade:electgrade,
            electpicked1: "country",
            electpicked2: "country",
            electpicked3: "country"
        }
    });

});
