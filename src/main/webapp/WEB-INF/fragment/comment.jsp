<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:forEach var="comment" items="${list}">
    <!-- COMMENT -->
    <div class="post">
        <div class="topwrap">
            <div class="userinfo pull-left">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/resources/images/avatar2.jpg" alt=""/>
                </div>

            </div>
            <div class="posttext pull-left">
                <p>${comment.desc}</p>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="postinfobot">

            <div class="posted pull-left"><i class="fa fa-clock-o"></i> Posted on : ${comment.writeDate}</div>
            <div class="posted pull-left"> ${comment.user.firstName} ${comment.user.lastName}</div>
            <div class="posted pull-left"><i class="fa fa-trash-o"></i></div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- COMMENT -->
</c:forEach>