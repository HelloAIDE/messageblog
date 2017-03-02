<%@page import="com.blog.util.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript"
	src="<%=Config.BASE_URL%>js/ajaxfileupload.js"></script>
</head>
<body>
	<div>
		<!--只有将表单的enctype设置为multipart/form-data，才能实现文件上传；表单enctype的默认值为：application/x-www-form-urlencoded-->
		<!--只有POST方法才会被解析为文件上传请求    -->
		<form action="<%=Config.BASE_URL%>user/update.do" method="post"
			enctype="multipart/form-data">
			<!-- 不知道为啥，type="file"这一标签必须同时添加name属性，否则在处理上传时，无法检测到上传文件 -->
			<input type="file" name="fileField" id="avatarfile" style="display:none"/> 
			<div>
				<img id="avatar" />
			</div>
			<div><a href="javascript:this;" id="select">点我选择头像</a></div>
		</form>
		<p>
			<font color="red">上传文件大小限制在1M之内</font>
		</p>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var avatarfile = $("#avatarfile");
		var avatar = $("#avatar");
		var sel = $("#select");
		
		sel.click(function(){
			avatarfile.click();
		});
		avatarfile.on("change",function() {
			updatefile();
		});
	
	function updatefile() {

			var fileFlag = false;
			if (avatarfile.val() != "") {
				fileFlag = true;
				var data = {
					fileField : avatarfile.val()
				};
				var url = BASE_URL + "user/update.do";
				$.ajaxFileUpload({
					url : url, //用于文件上传的服务器端请求地址
					secureuri : false, //一般设置为false
					fileElementId : $("input#avatarfile").attr("id"), //文件上传控件的id属性  <input type="file" id="file" name="file" /> 注意，这里一定要有name值   
					//$("form").serialize(),表单序列化。指把所有元素的ID，NAME 等全部发过去
					dataType : 'json',//返回值类型 一般设置为json
					complete : function() {//只要完成即执行，最后执行

					},
					success : function(result) //服务器成功响应处理函数
					{
						console.log(result);
						if (result.state == SUCCESS) {
							avatar.attr("src", result.message);
							sel.html("更换头像");
						} else {
							alert("error:" + result.message);
						}
						/*                
						 *    这里就是做一些其他操作，比如把图片显示到某控件中去之类的。               
						 */

					},
					error : function(data, status, e)//服务器响应失败处理函数
					{
						alert(e);
					}
				})

			}
			if (!fileFlag) {
				alert("请选择好文件!");
				return;
			}
			//二次失效重新绑定事件
			avatarfile = $("#avatarfile");
			avatarfile.on("change", function() {
				updatefile();
			});
		}
	})
</script>
</html>