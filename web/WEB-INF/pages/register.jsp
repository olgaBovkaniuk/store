<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Register page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/dispatcher?command=register_user" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail">Email address*</label>
                    <input type="email" name="email" value="${email}" class="form-control" id="exampleInputEmail"
                           placeholder="input email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword">Password*</label>
                    <input type="password" name="password" value="${password}" class="form-control" id="exampleInputPassword"
                           placeholder="input password">
                </div>
                <div class="form-group">
                    <label for="exampleInputFirstName">First name*</label>
                    <input type="text" name="first_name" value="${first_name}" class="form-control" id="exampleInputFirstName"
                           placeholder="input first name">
                </div>
                <div class="form-group">
                    <label for="exampleInputLastName">Last name*</label>
                    <input type="text" name="last_name" value="${last_name}" class="form-control" id="exampleInputLastName"
                           placeholder="input last name">
                </div>
                <div class="form-group">
                    <label for="exampleInputMobileNumber">Mobile number</label>
                    <input type="text" name="mobile_number" value="${mobile_number}" class="form-control" id="exampleInputMobileNumber"
                           placeholder="input mobile number">
                </div>
                <div class="form-group">
                    <label for="exampleInputAdditionalInfo">Additional info</label>
                    <input type="text" name="additional_info" value="${additional_info}" class="form-control" id="exampleInputAdditionalInfo"
                           placeholder="input additional info">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>