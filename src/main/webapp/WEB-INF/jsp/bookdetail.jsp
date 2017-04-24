<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 4/23/2017
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<div class="container">
    <button type="button" onclick="location.href='/booklist'" class="btn btn-primary btn-list">Book List</button>

    <h2>Book Detail</h2>
    <table class="table">

        <tbody>
        <tr>
            <td>Title</td>
            <td>${book.title}</td>
        </tr>
        <tr>
            <td>Author</td>
            <td>${book.author}</td>
        </tr>
        <tr>
            <td>Create Date</td>
            <td>${book.dateCreate}</td>
        </tr>
        <tr>
            <td>Update Date</td>
            <td>${book.dateUpdate}</td>
        </tr>
        <tr>
            <td>Description</td>
            <td>${book.description}</td>
        </tr>

        </tbody>
    </table>
</div>

<script>


</script>