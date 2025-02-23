<html>
	<head>
		<title>Login</title>
		<script>
			function changeColor() {
				alert("function called");
				document.login.pass.value = "I am changed";
			}
		</script>
		<style><%@include file="../css/style.css"%></style>
	</head>
	
	<body>
		
		<form name="login" action="login">
			<div>
				<a style="float: right" href="registration"> New Register/Sign Up </a><br>
			</div>
			
			<h1>
				Login
			</h1>
			<h4><span style="color: red">${error}</span></h4>
						
			<div class="imgcontainer">
				<img class="avatar" src="images/img_avatar.png"> 
			</div>
			
			<div class="container">
				<label><b>Username</b></label><br>
				<input type="text" name="name" placeholder="Enter username"><br>
				
				<label><b>Password</b></label><br>
				<input type="password" name="pass" placeholder="Enter password"><br>
				
				<button type="submit">Login</button>
				<label><input type="checkbox">Remember me</label>
			</div>
			
			<div class="container">
				<button class="cancelbtn" type="button">Cancel</button>
				<span class="forgotpwd"><a href="https://www.facebook.com/login/">Forgot password?</a></span>
			</div>		
			
		</form>
	</body>
</html>