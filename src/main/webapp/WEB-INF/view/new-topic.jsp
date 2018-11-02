<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Forum :: New topic</title>

    <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/imports.jsp"/>

    </head>
    <body>

        <div class="container-fluid">

            <c:import url="${pageContext.request.request.contextPath}/WEB-INF/fragment/header.jsp"/>

            <section class="content">
                <br><br>


                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-8">



                            <!-- POST -->
                            <div class="post">
                                <form action="${pageContext.request.contextPath}/ts?action=new-topic" class="form newtopic" method="post">
                                    <div class="topwrap">
                                        <div class="userinfo pull-left">
                                            <div class="avatar">
                                                <img src="${pageContext.request.contextPath}/resources/images/avatar4.jpg" alt="" />
                                            </div>
                                        </div>
                                        <div class="posttext pull-left">

                                            <div>
                                                <input type="text" placeholder="Enter Topic Title" name="new-title" class="form-control" />
                                            </div>


                                            <div>
                                                <textarea name="new-desc" id="desc" placeholder="Description"  class="form-control" ></textarea>
                                            </div>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>                              
                                    <div class="postinfobot">

                                        <div class="pull-right postreply">
                                            <div class="pull-left"><button type="submit" class="btn btn-primary">Post</button></div>
                                            <div class="clearfix"></div>
                                        </div>


                                        <div class="clearfix"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- POST -->

                            <div class="row similarposts">
                                <div class="col-lg-10"><i class="fa fa-info-circle"></i> <p>Similar Posts according to your Topic Title.</p></div>
                            </div>

                            <!-- POST -->
                            <div class="post">
                                <div class="wrap-ut pull-left">
                                    <div class="userinfo pull-left">
                                        <div class="avatar">
                                            <img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="" />
                                        </div>
                                    </div>
                                    <div class="posttext pull-left">
                                        <h2><a href="topic.jsp">10 Kids Unaware of Their Halloween Costume</a></h2>
                                        <p>It's one thing to subject yourself to a Halloween costume mishap because, hey, that's your prerogative.</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="postinfo pull-left">
                                    <div class="comments">
                                        <div class="commentbg">
                                            560
                                            <div class="mark"></div>
                                        </div>

                                    </div>
                                    <div class="views"><i class="fa fa-eye"></i> 1,568</div>
                                    <div class="time"><i class="fa fa-clock-o"></i> 24 min</div>                                    
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <!-- POST -->

                            <!-- POST -->
                            <div class="post">
                                <div class="wrap-ut pull-left">
                                    <div class="userinfo pull-left">
                                        <div class="avatar">
                                            <img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="" />
                                        </div>
                                    </div>
                                    <div class="posttext pull-left">
                                        <h2><a href="topic.jsp">10 Kids Unaware of Their Halloween Costume</a></h2>
                                        <p>It's one thing to subject yourself to a Halloween costume mishap because, hey, that's your prerogative.</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="postinfo pull-left">
                                    <div class="comments">
                                        <div class="commentbg">
                                            560
                                            <div class="mark"></div>
                                        </div>

                                    </div>
                                    <div class="views"><i class="fa fa-eye"></i> 1,568</div>
                                    <div class="time"><i class="fa fa-clock-o"></i> 24 min</div>                                    
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <!-- POST -->




                        </div>
                        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/right-menu.jsp"/>
                    </div>
                </div>

            </section>

            <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/footer.jsp"/>

        </div>

        <script type="text/javascript">
            $(function () {
                <c:if test="${message ne null}">
                alert('${message}');
                </c:if>
            });
        </script>

    </body>

<!-- Mirrored from forum.azyrusthemes.com/new-topic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 16 Oct 2018 07:34:53 GMT -->
</html>