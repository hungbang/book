<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 4/23/2017
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Book</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>


        <button type="button" class="btn btn-primary btn-add">Add More</button>


        <table id="example" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th></th>
                <th></th>
            </tr>

            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>

            </thead>
            <tfoot>

            </tfoot>
        </table>




        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <form:form id="book-form" action="saveBook" method="POST" modelAttribute="book">
                            <input id="id" type="hidden" name="id" value="" />
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label for="author">Author:</label>
                                <input type="text" class="form-control" id="author" name="author" placeholder="Enter Author">
                            </div>
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <textarea class="form-control" rows="5" id="description" name="description" placeholder="Enter description"></textarea>
                            </div>
                            <button type="submit" class="btn btn-default">Save</button>
                        </form:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

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
                {
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<a href="#" onclick="return editBook('+data+');">Edit</a>';
                    }
                },
                {"data": "id",
                    "render": function (data, type, full, meta) {
                        return '<a href="#" onclick="return deleteBook('+data+');">Delete</a>';
                    }
                }
            ]
        });




    });
    function deleteBook(id){
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: 'deleteBook',
            type: 'POST',
            data: {id: id},
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            }
        })
            .done(function(data) {
                location.reload();
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });

    };


    function editBook(id){
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $("#book-form").attr('action', 'saveBook');
        $.ajax({
            url: 'getBookById',
            type: 'POST',
            data: {id: id},
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            }
        })
            .done(function(data) {
                console.log("success");
                $("#title").val(data.title);
                $("#author").val(data.author);
                $("#description").val(data.description);
                $("#id").val(data.id);
                $("#myModal").modal('show');
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });
    };

    $(".btn-add").click(function(e){
        $("#book-form").attr('action', 'addBook');
        $("#myModal").modal('show');
    });
</script>

</html>
