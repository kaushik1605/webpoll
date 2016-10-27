<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>Prosjekt WebPoll</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
			<div class="form-signin-outer">
				<form class="form-signin" method="post" action="login">
					<h2 class="form-signin-heading">Vennligst logg inn</h2>
					<label for="inputUsername" class="sr-only">Brukernavn</label>
					<input type="text" id="inputUsername" class="form-control" placeholder="${usrplaceholder}" required autofocus>
					<label for="inputPassword" class="sr-only">Passord</label>
					<input type="password" id="inputPassword" class="form-control" placeholder="${pswdplaceholder}" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Logg inn</button>
				</form>
			</div>
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/macdesk.jpg"/>");</script>
	</body>
</html>