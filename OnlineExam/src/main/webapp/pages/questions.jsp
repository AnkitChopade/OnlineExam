
<%@page import="java.util.List"%>
<%@page import="com.jbk.dao.MyDao"%>
<%@page isELIgnored="false"%>

<html>
<head>
<title>Question Page</title>
<style><%@include file="../css/style.css"%></style>
<script>
	function submitAnswer() {
		xhttp = new XMLHttpRequest();

		var ans = document.myForm.opt.value;
		var oans = document.myForm.qans.value;

		var qno = document.myForm.qno.value;
		var qtxt = document.myForm.qtxt.value;

		//submitAns ? submittedAnswer=x & originalAnswer=x & qno=1 & questionText=what is java
		var data = "?submittedAnswer=" + ans + "&originalAnswer=" + oans
				+ "&qno=" + qno + "&questionText=" + qtxt;

		xhttp.open("GET", "submitAns" + data, true);

		xhttp.send();

	}
</script>

<style>
	h1 {
		color: red;
		font-size: 40px;
		text-align: center;
		margin: 25px;
	}
	.navbtn{
		background-color: #04AA6d;
		color: white;
		padding: 10px 15px;
		margin: auto;
		width: 15%;
		border: none;
	}

</style>

</head>
<body>
	${wel}
	<a style="float:right; font-size: 20px;" href="/"><b>Logout</b></a><br> <br>
	<h1>Welcome to Online Examination</h1>	

	<span style="color: red">${message}</span>
	<br>
	<br>

	<form name="myForm" class="">
		
			<div class="quecontainer">
				<p><b>${question.qno}. ${question.qtxt}</b></p>
				<input type="hidden" name="qno" value="${question.qno}">
				<input type="hidden" name="qtxt" value="${question.qtxt}">
				<br> 
				<input type="radio" name="opt" value="${question.opt1}" 
					onclick="submitAnswer()"> <span>${question.opt1}</span>
				<br>
				<input type="radio" name="opt" value="${question.opt2}"
					onclick="submitAnswer()"> <span>${question.opt2}</span>
				<br>
				<input type="radio" name="opt" value="${question.opt3}"
					onclick="submitAnswer()"> <span>${question.opt3}</span>
				<br>
				<br> 
				<br>
				<input type="hidden" name="qans" value="${question.qans}">				
				
				<button class="navbtn" type="submit" formaction="previous" >previous</button>
				<button class="navbtn" style="float:right" type="submit" formaction="next" >next</button><br>
				<br> <br> <br> <br> 
				<button class="cancelbtn" style="float:right" type="submit" formaction="endExam" >End Exam</button><br>
				<br> <br> 
			</div>
	</form>
</body>
</html>