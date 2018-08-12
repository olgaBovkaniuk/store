<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Login page index</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/dispatcher?command=login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail">Email address</label>
                    <input type="email" name="email" value="${email}" class="form-control" id="exampleInputEmail"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword">Password</label>
                    <input type="password" name="password" value="${password}" class="form-control"
                           id="exampleInputPassword"
                           placeholder="input password">
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-8 align-content-center">
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <a href="${pageContext.request.contextPath}/pages/register.jsp" class="btn btn-primary"
                           aria-pressed="true" role="button">Register</a>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>