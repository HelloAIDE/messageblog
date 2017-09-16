/**
 * 线报网api 作者：大牛哥 时间：2016.12.25 20：55
 */
var url = {
	"LOGIN_URL" : "checkuser.html",
	"REGISTER_URL" : "adduser.html",
	"ADDPOST_URL" : "addpost.html"
};
var SUCCESS = "0";
var ERROR = "1";
$.extend({
	isNull : function(str) {
		if (str == null || str == undefined || str == "") {
			return true
		} else {
			return false
		}
		;
	},
});
//初始化
$(function() {
	/** -----------用户相关信息--------------- */
	var nameinp = $("input[name='name']");
	var passinp = $("input[name='pass']");
	var phoneinp = $("input[name='phone']");
	var qqinp = $("input[name='qq']");
	var emailinp = $("input[name='email']");
	var addressinp = $("input[name='address']");

	/** -----------帖子相关信息--------------- */
	var titleinp = $("input[name='title']");
	var contentinp = $("input[name='content']");
	var cityinp = $("input[name='city']");
	var typeinp = $("select[name='type']");
	var resourcesinp = $("input[name='resources']");

	// 验证码
	var codeinp = $("input[name='code']");
	// 登陆页提交按钮
	var login_submit = $("button[name='login_submit']");
	// 注册页提交按钮
	var register_submit = $("button[name='register_submit']");
	// 发布帖子页提交按钮
	var send_post_submit = $("button[name='send_post_submit']");
	initClick();
	// 初始化按钮点击事件
	function initClick() {
		//登陆提交事件
		login_submit.click(function() {
			var name = nameinp.val()
			var pass = passinp.val();
			var code = codeinp.val();
			login(name, pass, code, Login_SUCCESS, Login_ERROR);
		});
		//注册提交事件
		register_submit.click(function(){
			var name = nameinp.val()
			var pass = passinp.val();
			var phone = phoneinp.val();
			var qq = qqinp.val();
			var email = emailinp.val();
			var address = addressinp.val();
			var code = codeinp.val();
			register(name, pass, phone, qq, email, address, code, Register_SUCCESS, Register_ERROR)
		});
		//发帖提交事件
		send_post_submit.click(function(){
			var title = titleinp.val();
			var content = contentinp.val();
			var city = cityinp.val();
			var type = typeinp.val();
			var resources = resourcesinp.val();
			var code = codeinp.val();
			sendPost(title, content, city, type, resources, code, SendPost_SUCCESS, SendPost_ERROR)
		});
	}
	// 登陆成功事件
	var Login_SUCCESS = function(result) {
		recode();
		alert("登陆成功");
		self.location = document.referrer;
	}
	// 登陆失败事件
	var Login_ERROR = function Login_ERROR(result) {
		recode();
		var returnMsg = "<p>" + result.message + "</p>";
		$.show(returnMsg, 3000);

	}
	var Register_SUCCESS = function(result){
		recode();
		alert("注册成功");
		self.location = document.referrer;
	}
	var Register_ERROR = function(result){
		recode();
		var returnMsg = "<p>" + result.message + "</p>";
		$.show(returnMsg, 3000);
	}
	var SendPost_SUCCESS = function(result){
		recode();
		alert("发布成功");
		self.location = "fourm/post-"+result.data.id+".html";
	}
	var SendPost_ERROR = function(result){
		recode();
		var returnMsg = "<p>" + result.message + "</p>";
		$.show(returnMsg, 3000);
	}
	
})
//用户登录方法
function login(name, pass, code, onSuccess, onError) {
	if ($.isNull(name)) {
		$.show("用户名不能为空", 3000);
		return false;
	}
	if ($.isNull(pass)) {
		$.show("密码不能为空", 3000);
		return false;
	}
	if ($.isNull(code)) {
		$.show("验证码不能为空", 3000);
		return false;
	}
	var data = {
		name : name,
		pass : pass,
		code : code
	};
	$.post(BASE_URL + url.LOGIN_URL, data, function(result) {
		if (result.state == SUCCESS) {
			onSuccess(result);
		} else {
			onError(result);
		}
	});
}
//用户注册方法
function register(name, pass, phone, qq, email, address, code ,onSuccess, onError) {
	if ($.isNull(name)) {
		$.show("用户名不能为空", 3000);
		return false;
	}
	if ($.isNull(pass)) {
		$.show("密码不能为空", 3000);
		return false;
	}
	if ($.isNull(phone)) {
		$.show("手机号不能为空", 3000);
		return false;
	}
	if ($.isNull(qq)) {
		$.show("qq不能为空", 3000);
		return false;
	}
	if ($.isNull(email)) {
		$.show("邮箱不能为空", 3000);
		return false;
	}
	if ($.isNull(address)) {
		$.show("地址不能为空", 3000);
		return false;
	}
	if ($.isNull(code)) {
		$.show("验证码不能为空", 3000);
		return false;
	}
	var data = {
		name : name,
		pass : pass,
		phone : phone,
		qq : qq,
		email : email,
		address : address,
		code : code
	};
	$.post(BASE_URL + url.REGISTER_URL, data, function(result) {
		if (result.state == SUCCESS) {
			onSuccess(result);
		} else {
			onError(result);
		}
	});
}
//发帖方法
function sendPost(title,content,city,type,resources,code,onSuccess,onError){
	if ($.isNull(title)) {
		$.show("标题不能为空", 3000);
		return false;
	}
	if ($.isNull(content)) {
		$.show("内容不能为空", 3000);
		return false;
	}
	if ($.isNull(city)) {
		$.show("城市不能为空", 3000);
		return false;
	}
	if ($.isNull(type)) {
		$.show("类型不能为空", 3000);
		return false;
	}
	if ($.isNull(code)) {
		$.show("验证码不能为空", 3000);
		return false;
	}
	var data = {
		title:title,
		content:content,
		city:city,
		type:type,
		resources:resources,
		code:code
	};
	$.post(BASE_URL + url.ADDPOST_URL, data, function(result) {
		if (result.state == SUCCESS) {
			onSuccess(result);
		} else {
			onError(result);
		}
	});
}
