<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
        $.ajax({
            url: '/ts?action=get-popular-topics',
            type: 'GET',
            dataType: 'JSON',
            success: function (topicList) {
                $('#idPopularTopic').empty();
                topicList.forEach(function (topic) {
                    $('#idPopularTopic').append('<li><a href="/ns?action=topic&id=' + topic.id + '">' + topic.title + ' <span class="badge pull-right">' + topic.commentCount + '</span></a></li>');
                })
            },
            error: function () {
                $('#idPopularTopic').empty();
                topicList.forEach(function (topic) {
                    $('#idPopularTopic').append('<p>Error</p>');
                })
            }
        })
    })
</script>
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
            <ul id="idPopularTopic" class="cats">
                <p>Loading popular topics</p>
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
