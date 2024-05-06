<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>
        
    <style>
            .col-sm-8{
                border: 1px black solid;
                padding: 5px;
                width: 65%;
                text-align: center;
                height: 500px;
                background-color: white;
                margin-top: 20px;
            }
            body{
                background-color: rgba(101, 167, 188, 1);
            }
            .btn{
                margin: 5px;
            }
            .table-content{
                border: 1px black solid;
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-4" >
                <div class="d-grid gap-2 col-6 mx-auto" style="margin-top: 200px;">
                    <a class="btn btn-primary" href="Controlador?menu=RegisCliente" role="button">Registrar</a>
                    <a class="btn btn-primary" href="Controlador?menu=ModifCliente" role="button">Modificar</a>
                    <a class="btn btn-primary" href="Controlador?menu=ElimCliente" role="button">Eliminar</a>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <head>
                        <tr>
                            <th class="table-content">ID</th>
                            <th class="table-content">Nombre</th>
                            <th class="table-content">Apellido P.</th>
                            <th class="table-content">Apellido M.</th>
                            <th class="table-content">Teléfono</th>
                            <th class="table-content">DNI</th>                        
                        </tr>

                    </head>
                    <tbody>
                    <c:forEach var="cl" items="${clientes}">
                        
                    
                        <tr>
                            <td class="table-content">${cl.getId()}</td>  
                            <td class="table-content">${cl.getNom()}</td>  
                            <td class="table-content">${cl.getApep()}</td>  
                            <td class="table-content">${cl.getApem()}</td>  
                            <td class="table-content">${cl.getTel()}</td>  
                            <td class="table-content">${cl.getDni()}</td>  

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>            
        </div>
        
        
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        
        
    </body>
</html>
