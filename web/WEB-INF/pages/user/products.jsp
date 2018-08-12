<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../util/head.jsp"/>
    <title>User products page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/dispatcher?command=deleteProduct" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Price</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>${product.price}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/dispatcher?command=add_order&product_id=${product.id}"
                                           class="btn btn-primary" role="button">Order</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/dispatcher?command=user_orders"
                           class="btn btn-primary" aria-pressed="true" role="button">View my orders</a>
                    </div>
                </div>
            </form>
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
