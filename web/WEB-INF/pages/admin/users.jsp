<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../util/head.jsp"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <%--<form action="${pageContext.request.contextPath}/dispatcher?command=deleteUser" method="post">--%>
            <%--<div class="row">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=addUser" class="btn btn-primary"
                       aria-pressed="true" role="button">Add</a>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>--%>
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">FirstName</th>
                            <th scope="col">LastName</th>
                            <th scope="col">Email</th>
                            <th scope="col">MobileNumber</th>
                            <th scope="col">AdditionalInfo</th>
                            <th scope="col">Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${user.mobileNumber}</td>
                                <td>${user.additionalInfo}</td>
                                <td>${user.role}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=admin_products"
                       class="btn btn-primary" aria-pressed="true" role="button">View products list</a>
                </div>
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=admin_orders"
                       class="btn btn-primary" aria-pressed="true" role="button">View order list</a>
                </div>
                <div class="col-md-3"></div>
                <div class="col-md-3"></div>
            </div>
            <%--</form>--%>
        </div>
        <div class="col-md-1">
            <div class="row">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/pages/login.jsp"
                       class="btn btn-primary" aria-pressed="true" role="button">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../util/js.jsp"/>
</body>
</html>
