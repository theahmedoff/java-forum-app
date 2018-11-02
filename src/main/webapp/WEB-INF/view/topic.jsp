<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Forum :: Topic</title>

       <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/imports.jsp"/>


    <script>
        $(function () {
            var topicID = $('#topic-id').val();
           $.ajax({
               url: '/cs?action=get-comments',
               dataType: 'HTML',
               method: 'GET',
               data: 'id=' + topicID,
               success: function (data) {
                   console.log("Error");
                    $('#fragment-comment').html(data);
               }
           })
        });
    </script>

    </head>
    <body class="topic">

        <div class="container-fluid">



          <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/header.jsp"/>


            <section class="content">
               <br><br>


                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-8">

                            <!-- MAIN POST -->
                            <div class="post beforepagination panel-primary">
                                <div class="topwrap">
                                    <div class="userinfo pull-left">
                                        <div class="avatar">
                                            <img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="" />
                                        </div>

                                    </div>
                                    <div class="posttext pull-left">
                                        <h2>${topic.title}</h2>
                                        <p>${topic.desc}</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="postinfobot">

                                    <div class="posted pull-left"><i class="fa fa-clock-o"></i> ${topic.shareDate}</div>
                                    <div class="posted pull-left"> Posted by : ${topic.user.firstName} ${topic.user.lastName}</div>
                                    <div class="posted pull-left"><i class="fa fa-trash-o"></i></div>

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            <!-- MAIN POST -->

                            <div id="fragment-comment">

                            </div>

                            <br><br>



                                 <!-- REPLY -->
                                 <div class="post">

                                         <div class="topwrap">
                                             <div class="userinfo pull-left">
                                                 <div class="avatar">
                                                     <img src="images/avatar4.jpg" alt="" />
                                                 </div>
                                                 <input type="hidden" name="id" id="topic-id" value="${param.id}">

                                             </div>
                                             <div class="posttext pull-left">
                                                 <div class="textwraper">
                                                     <div class="postreply">Post a Reply</div>
                                                     <textarea name="reply" id="reply" placeholder="Type your message here"></textarea>
                                                 </div>
                                             </div>
                                             <div class="clearfix"></div>
                                         </div>
                                         <div class="postinfobot">


                                             <div class="pull-right postreply">
                                                 <div class="pull-left"><button type="submit" class="btn btn-primary">Post Reply</button></div>
                                                 <div class="clearfix"></div>
                                             </div>


                                             <div class="clearfix"></div>
                                         </div>
                                 </div>
                                 <!-- REPLY -->


                        </div>
                        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/right-menu.jsp"/>
                    </div>
                </div>

                <br><br>


            </section>

            <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/footer.jsp"/>

        </div>

        <script type="text/javascript">
            $(function () {
                <c:if test="${mes ne null}">
                alert('${mes}');
                </c:if>
            });
        </script>
    </body>

<!-- Mirrored from forum.azyrusthemes.com/topic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 16 Oct 2018 05:39:38 GMT -->
</html>