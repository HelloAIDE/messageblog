/**
 * 线报网api 作者：大牛哥 时间：2016.12.25 20：55
 */
var url = {
	"LOGIN_URL" : "checkuser.html",
	"REGISTER_URL" : "register.html"
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
$(function() {
	/**-----------用户相关信息---------------*/
	var nameinp = $("input[name='name']");
	var passinp = $("input[name='pass']");
	var phone = $("input[name='phone']");
	var qq = $("input[name='qq']");
	var email = $("input[name='email']");
	var address = $("input[name='address']");
	
	/**-----------帖子相关信息---------------*/
	var title = $("input[name='title']");
	var content = $("input[name='content']");
	var city = $("input[name='city']");
	var type = $("input[name='type']");
	var resources = $("input[name='resources']");
	
	
	
	//验证码
	var codeinp = $("input[name='code']");
	// 登陆页提交按钮
	var login_submit = $("button[name='login_submit']");
	initClick();
	// 初始化按钮点击事件
	function initClick() {
		login_submit.click(function() {
			var name = nameinp.val()
			var pass = passinp.val();
			var code = codeinp.val();
			login(name, pass, code, Login_SUCCESS, Login_ERROR);
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
})
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