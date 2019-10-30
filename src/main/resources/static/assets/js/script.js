var correct_ans = null;

function getQuestion() {
	var xmlHttp = new XMLHttpRequest();
	var url = "/getOneQuiz";
	xmlHttp.open("POST",url,true);
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4) {
			var quiz = JSON.parse(xmlHttp.responseText);
			$("#choose1").val(quiz.options[0]);
			$("#text1").html(quiz.options[0]);
			$("#choose2").val(quiz.options[1]);
			$("#text2").html(quiz.options[1]);
			$("#choose3").val(quiz.options[2]);
			$("#text3").html(quiz.options[2]);
			$("#choose4").val(quiz.options[3]);
			$("#text4").html(quiz.options[3]);
			
			$("#quiz_title").html(quiz.title);
			correct_ans = quiz.answer;
		}
	}
		  
	xmlHttp.send();
}

function judgeAns() {
	var chooses = document.getElementsByName("choose");
	for(var i=0;i<chooses.length;i++) {
		if(chooses[i].checked) {
			if(correct_ans==chooses[i].value) {
				ansCorrect();
			} else {
				ansWrong();
			}
		}
	}
}

function ansWrong() {
	$("#result_img").attr("src","../static/assets/img/wrong.png");
	setTimeout(function() {
	 	$("#result_img").attr("src","../static/assets/img/check.png");
	 	getQuestion();
	},1000);
	
	var xmlHttp = new XMLHttpRequest();
	var url = "/ansWrong";
	xmlHttp.open("POST",url,true);
	xmlHttp.send();
} 

function ansCorrect() {
	$("#result_img").attr("src","../static/assets/img/correct.png");
	setTimeout(function() {
	 	$("#result_img").attr("src","../static/assets/img/check.png");
	 	getQuestion();
	},1000);
	
	var xmlHttp = new XMLHttpRequest();
	var url = "/ansCorrect";
	xmlHttp.open("POST",url,true);
	xmlHttp.send();
}

function changePassword() {
	var password1 = $("#newPassword").val();
	var password2 = $("#confirmPassword").val();
	if(password1!=password2) {
		$("#newPassword").val("");
		$("#confirmPassword").val("");
		alert("两次密码输入不一致！");
	} else {
		var xmlHttp = new XMLHttpRequest();
		var url = "/changePassword?&password="+password1;
		xmlHttp.open("POST",url,true);
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				var data = JSON.parse(xmlHttp.responseText);
				if(data.isSuccess) {
					$("#newPassword").val("");
					$("#confirmPassword").val("");
					alert("密码修改成功!");
				} else {
					alert("修改失败，请稍后重试！");
				}
			}
		}
		xmlHttp.send();
	}
}


function changeWord(word) {
	$("#english").html(word.innerHTML.toString().split(" ")[0]);
	$("#chinese").html(word.innerHTML.toString().split(" ")[1]);
	$("#m_aud").attr("src","http://media.shanbay.com/audio/us/"+ word.innerHTML.toString().split(" ")[0] +".mp3");
	pre_selected=current_selected;
	current_selected=word;
	current_selected.className="list-group-item selected-word";
	pre_selected.className="list-group-item";
	
}

function nextWord() {
	pre_selected=current_selected;
	current_selected=current_selected.nextElementSibling;
	current_selected.className="list-group-item selected-word";
	pre_selected.className="list-group-item";
	
	$("#english").html(current_selected.innerHTML.toString().split(" ")[0]);
	$("#chinese").html(current_selected.innerHTML.toString().split(" ")[1]);
	$("#m_aud").attr("src","http://media.shanbay.com/audio/us/"+ current_selected.innerHTML.toString().split(" ")[0] +".mp3")
}

function preWord() {
	pre_selected=current_selected;
	current_selected=current_selected.previousElementSibling;
	current_selected.className="list-group-item selected-word";
	pre_selected.className="list-group-item";
	
	$("#english").html(current_selected.innerHTML.toString().split(" ")[0]);
	$("#chinese").html(current_selected.innerHTML.toString().split(" ")[1]);
	$("#m_aud").attr("src","http://media.shanbay.com/audio/us/"+ current_selected.innerHTML.toString().split(" ")[0] +".mp3");
}

function openMotai() {
	var key = $("#input-word").val();
	$("#key-word").html(key);
	var result;
	
	var pattern = /<|>|{|}/;
	
	if(key.search(pattern)>=0) {
		alert("不能使用* / %等特殊符号");
		return;
	}
	
	var xmlHttp = new XMLHttpRequest();
	var url = "/translate"+"?key="+key;
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4) {
			result = JSON.parse(xmlHttp.responseText).trans_result[0].dst;
			$("#translate-result").html(result);
			
			var pattern = /[a-zA-Z_]{1,}/;
			if(key.search(pattern) < 0) {
				key = result.toLowerCase();
			}
			
			$("#aud").attr("src","http://media.shanbay.com/audio/us/"+key+".mp3");
			$("#myModal").modal('show');
		}
	}
	xmlHttp.send();
}

function createTopic() {
	var theme = $("#theme").val();
	var content = $("#content").val();
	
	var pattern = /<|>|{|}/;
	
	if(theme.search(pattern)>=0 || content.search(pattern)>=0) {
		alert("不能使用* / %等特殊符号");
		return;
	}
	
	var xmlHttp = new XMLHttpRequest();
	var url = "/createTopic?"+"theme="+theme+"&content="+content;
	xmlHttp.open("POST",url,true);
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4) {
			window.location.href = "/resource";
		}
	}
	xmlHttp.send();
}

function newDiscuss() {
	var topic_id = $("#topic_id").val();
	var content = $("#content").val();
	
	var pattern = /<|>|{|}/;
	
	if(content.search(pattern)>=0) {
		alert("不能使用* / %等特殊符号");
		return;
	}
	
	var xmlHttp = new XMLHttpRequest();
	var url = "/submitDiscuss?"+"topic_id="+topic_id+"&content="+content;
	xmlHttp.open("POST",url,true);
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4) {
			window.location.href = "/discuss?"+"topic_id="+topic_id;
		}
	}
	xmlHttp.send();
}