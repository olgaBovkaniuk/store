<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../util/head.jsp"/>
    <title>User orders page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-12">
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Order number</th>
                            <th scope="col">User id</th>
                            <th scope="col">User first name</th>
                            <th scope="col">User last name</th>
                            <th scope="col">Product</th>
                            <th scope="col">Total price</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.orderNumber}</td>
                                <td>${order.user.id}</td>
                                <td>${order.user.firstName}</td>
                                <td>${order.user.lastName}</td>
                                <td>${order.product.name}</td>
                                <td>${order.totalPrice}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/dispatcher?command=delete_admin_order&order_id=${order.id}"
                                       class="btn btn-primary" role="button">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=admin_products"
                       class="btn btn-primary" aria-pressed="true" role="button">View product list</a>
                </div>
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/dispatcher?command=users"
                       class="btn btn-primary" aria-pressed="true" role="button">View user list</a>
                </div>
                <div class="col-md-3"></div>
                <div class="col-md-3"></div>
            </div>
        </div>
        <div class="col-md-2">
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
