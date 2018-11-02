<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 10/21/2018
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div class="col-lg-4 col-md-4">

    <!-- -->
    <div class="sidebarblock">
        <h3 class="bg-primary">Popular Topics</h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <ul class="cats">
                <li><a href="#">Trading for Money <span class="badge pull-right">20</span></a></li>
                <li><a href="#">Vault Keys Giveway <span class="badge pull-right">10</span></a></li>
                <li><a href="#">Misc Guns Locations <span class="badge pull-right">50</span></a></li>
                <li><a href="#">Looking for Players <span class="badge pull-right">36</span></a></li>
                <li><a href="#">Stupid Bugs &amp; Solves <span class="badge pull-right">41</span></a>
                </li>
                <li><a href="#">Video &amp; Audio Drivers <span class="badge pull-right">11</span></a>
                </li>
                <li><a href="#">2K Official Forums <span class="badge pull-right">5</span></a></li>
            </ul>
        </div>
    </div>

    <!-- -->
    <c:choose>
        <c:when test="${sessionScope.user ne null}">
            <div class="sidebarblock">
                <h3 class="bg-primary">My Active Threads</h3>
                <c:forEach var="t" items="${topicList}">

                   <c:if test="${t.user.id == user.id}">
                       <div class="divline"></div>
                       <div class="blocktxt">
                           <a href="#">${t.title}</a>
                           <<br>
                       </div>
                   </c:if>

                </c:forEach>
            </div>
        </c:when>
    </c:choose>


</div>
</body>
</html>
