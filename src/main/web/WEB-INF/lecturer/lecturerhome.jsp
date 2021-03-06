<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Foreleser</title>
		<style>
			body{padding-top:70px;padding-bottom:70px;}
			@media(max-width:767px){body{padding-top:0;padding-bottom:0px;}}
		</style>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Ny undersøkelse</h3>
				</div>
				<div class="panel-body">
					<a class="btn btn-primary" href="<c:url value="instantiatesurvey"/>" role="button">Opprett undersøkelse</a>
				</div>
			</div>

			<jsp:include page="listsurveys.jsp"/>
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/macdesk.jpg"/>");</script>
	</body>
</html>