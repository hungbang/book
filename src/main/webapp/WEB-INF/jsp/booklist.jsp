<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 4/23/2017
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Book</title>
</head>
<body>
        <c:forEach items="${books}" var="b">
            <c:out value="${b.title}" />
            
            <c:out value="${b.author}" />
        </c:forEach>


        <table id="example" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Edit Button</th>
                <th>Delete Button</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Edit Button</th>
                <th>Delete Button</th>
            </tr>
            </tfoot>
        </table>

</body>

<script>

//    $(document).ready(function() {
//        var table = $('table#example').DataTable({
//            'ajax' : '/data/books',
//            'serverSide' : true,
//            columns : [
//                {
//                    data: 'title'
//                },
//                {
//                    data: 'author'
//                },
//                {
//                    data: 'id'
//                }
//            ]
//        });
//    });

</script>

</html>
