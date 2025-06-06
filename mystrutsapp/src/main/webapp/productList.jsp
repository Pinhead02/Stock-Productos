<%--
  Created by IntelliJ IDEA.
  User: alejandro.v
  Date: 04/06/2025
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Lista de Productos</h1>

<c:if test="${empty productList}">
    <p>No hay productos disponibles.</p>
</c:if>
<c:if test="${not empty productList}">
    <table>
        <thead>
        <tr>
            <th>ID Producto</th>
            <th>Nombre</th>
            <th>SKU</th>
            <th>UoM</th>
            <th>Costo</th>
            <th>Precio Venta</th>
            <th>Categoría</th>
            <th>Ubicación</th>
            <th>Activo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td><c:out value="${product.productId}"/></td>
                <td><c:out value="${product.productName}"/></td>
                <td><c:out value="${product.sku}"/></td>
                <td><c:out value="${product.unitOfMeasure}"/></td>
                <td><c:out value="${product.cost}"/></td>
                <td><c:out value="${product.salePrice}"/></td>
                <td><c:out value="${product.category}"/></td>
                <td><c:out value="${product.location}"/></td>
                <td><c:out value="${product.active ? 'Sí' : 'No'}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<p><a href="index.jsp">Volver al Inicio</a></p>
</body>
</html>
