$(function() {
	var model = {
		huifu2 : []
	};
	var div = $("#replys");
	var sid = div.attr("sid");
	function init() {
		GetHuifu(sid);
	}
	function GetHuifu(sid) {
		Log(sid);
		var data = {
			postid : sid
		};
		$.post("../getReply.do", data, function(result) {
			if (result.state == "0") {
				Log(result.data);
				model.huifu = result.data;
				painthuifu();

			}

		});
	}
	function painthuifu() {
		div.empty();
		var str = "";
		for (var i = 0; i < model.huifu.length; i++) {
			var reply = model.huifu[i];
			var id = reply.id;
			str += '<div class="item" rid="' + reply.id
					+ '"><div class="left"></div><div class="rigth"><p>'
					+ reply.userName + '</p><br><div class="content">'
					+ reply.content + '</div><div class="content_r">'
					+ '<p>回复时间：' + reply.createTime + '</p><br/>'
					+ '<a href="javascript:reply(' + reply.id + ',\''
					+ reply.userName + '\');">回复</a></div>' + '</div></div>';
			if (id !== undefined) {
				var data = {
					parentid : id
				};
				$.post("../getParentAllReply.do", data, function(result) {
					if (result.state == "0") {
						setTimeout(replacePaint(reply.id, result.data),1);
					}
				});
			}
		}
		div.append(str);

	}
	function replacePaint(id, result) {
		
		var str ='';
		var last="";
		for (var i = 0; i < result.length; i++) {
			var reply = result[i];
			Log("======================"+i+"=====================");
			Log(reply);
			if(i>0&&i!=result.length-1){
				var t  =(result.length-i-1)*10*2+650+"px";
				str+= reply.content+'<div class="item1"  style="width:'+t+';">' + '<div class="rigth">'
				+ '<p>引用'+reply.userName+'的回复：</p>' + '<br>'
				+ '<div class="content">';
			}else if(i==0){
				var t  =(result.length-1)*10*2+650+"px";
				str+= '<div class="item1"  style="width:'+t+';">' + '<div class="rigth" >'
				+ '<p>引用'+reply.userName+'的回复：</p>' + '<br>'
				+ '<div class="content">'+reply.content;
			
				
			}else if(i==result.length-1){
				str+= '<div class="item1">' + '<div class="rigth">'
				+ '<p>引用'+reply.userName+'的回复：</p>' + '<br>'
				+ '<div class="content">';
				last=reply.content;
			}
		}
		var str1='</div>' + '</div>' + '</div>';
		for (var i = 0; i < result.length; i++) {
			if(i==result.length-1){
				
				str+=str1;
				str+=last;
			}else{

				str+=str1;
	
			}			
		}
		Log(str);
	}
	init();
	function Log(str) {
		console.log(str);
	}

});