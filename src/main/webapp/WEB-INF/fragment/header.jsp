<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="headernav">
    <div class="container">
        <div class="row">
            <div class="col-lg-1 col-xs-3 col-sm-2 col-md-2 logo "><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt=""  /></a></div>
            <div class="col-lg-3 col-xs-9 col-sm-5 col-md-3 selecttopic">

            </div>
            <div class="col-lg-4 search hidden-xs hidden-sm col-md-3">
                <div class="wrap">
                    <form action="#" method="post" class="form">
                        <div class="pull-left txt"><input type="text" class="form-control" placeholder="Search Topics"></div>
                        <div class="pull-right"><button class="btn btn-default" type="button"><i class="fa fa-search"></i></button></div>
                        <div class="clearfix"></div>
                    </form>
                </div>
            </div>


            <c:choose>
                <c:when test="${sessionScope.user eq null}">
                    <div class="wrapper">
                        <div class="btn">
                            <form action="${pageContext.request.contextPath}/ns?action=login" method="post" class="form">
                            <button class="btn-design" id="loc1" type="submit"> Sign in </button>
                            </form>
                        </div>
                    </div>
                    <div class="wrapper">
                        <div class="btn">
                            <form action="${pageContext.request.contextPath}/ns?action=register" method="post" class="form">
                            <button class="btn-design" id="loc2" type="submit"> Sign up </button>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-4 col-xs-12 col-sm-5 col-md-4 avt">
                        <div class="stnt pull-left">
                            <form action="${pageContext.request.contextPath}/ns?action=new-topic" method="post" class="form">
                                <button class="btn btn-primary" type="submit">Start New Topic</button>
                            </form>
                        </div>

                        <div class="avatar pull-left dropdown">
                            <a data-toggle="dropdown" href="#"><img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="" /></a> <b class="caret"></b>
                            <ul class="dropdown-menu" role="menu">
                                <li role="presentation"><a role="menuitem" tabindex="-3" href="${pageContext.request.contextPath}/us?action=logout">Log Out</a></li>
                            </ul>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>