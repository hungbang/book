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

<script type="text/javascript">
    //Plugin fetch paging data
    jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
    {
        return {
            "iStart":         oSettings._iDisplayStart,
            "iEnd":           oSettings.fnDisplayEnd(),
            "iLength":        oSettings._iDisplayLength,
            "iTotal":         oSettings.fnRecordsTotal(),
            "iFilteredTotal": oSettings.fnRecordsDisplay(),
            "iPage":          oSettings._iDisplayLength === -1 ?
                    0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
            "iTotalPages":    oSettings._iDisplayLength === -1 ?
                    0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
        };
    };
    $(document).ready(function() {
        var url = "data/booklists";
        $('#example').dataTable({
            "bProcessing": true,
            "bServerSide": true,
            "sort": "position",
            "bStateSave": false,
            "iDisplayLength": 10,
            "iDisplayStart": 0,
            "fnDrawCallback": function(){
                //alert('Current page number: '+ this.fnPagingInfo().iPage);
            },
            "sAjaxSource": url,
            "columns": [
                {"data": "title"},
                {"data":"author"},
                {"data": "id"},
                {"data": "id"}
            ]
        });
    });
</script>

</html>
