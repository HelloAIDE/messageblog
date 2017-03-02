<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../include/header.jsp"%>
</head>
<body>
	<div id="cmd">
		<textarea id="sql" rows="10" cols="10">select * from user</textarea>
		<br />
		<button id="submit">执行</button>
	</div>
	<div id="result"></div>
</body>
<script type="text/javascript">
var model ={};
$(function(){
	$("#submit").click(function(e){
		
		var url="<%=Config.BASE_URL%>/admin/system/execute.do";
			var sql = $("#sql").val();
			var data = {
				sql : sql
			};
		
			$.post(url, data, function(result) {
				if (result.state == "0") {
					model.sqldata = result.data;
					paintTable();
				} else {
					alert("sql执行出错");
				}
			});
		});
		function paintTable() {
			var resultdiv = $("#result");
			resultdiv.empty();
			var data = model.sqldata;
			var table = $("<table border=\"1\">");
			table.appendTo(resultdiv);
			var field = [];
			jQuery.each(data[0], function(i, val) {
				var th = "<th>"+i+"</th>";
				field.push(i);
				table.append(th);
			});
			for (var i = 0; i < data.length; i++) {
				var tr = $("<tr></tr>");
				for (var int = 0; int < field.length; int++) {
					var v = data[i][field[int]];
					var td =$("<td>空</td>");
					if(v!==undefined){
						td = $("<td>"+v	+"</td>");
					}
					td.appendTo(tr);
				}
				tr.appendTo(table);
			}
		}
	});
</script>
</html>