<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Fresh Threads</title>
    
    <style>
        body{
            background-color: rgba(101, 167, 188, 1);
        }
        
        .centered-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }
        
        .text1{
            text-align: center;
            font-family: 'Inria Serif', serif;
            font-size: 70px;
            color: #fff;
            padding-top:50px;
        }
        
        .container-custom {
            display: flex;
        }
        
        .left-banner, .right-banner {
            flex: 1;
            text-align: center;
            padding: 100px;
            align-self: center; /* Añadido para centrar verticalmente las imágenes */
        }
        
        .left-banner img, .right-banner img {
            width: 200px;
            cursor: pointer; /* Añadido para mostrar que las imágenes son clicables */
        }
    </style>
</head>
<body>
    
        <div class="text1">
        Escoja el formato para exportar
    </div>
    <div class="centered-container">
        
        <div class="container-custom">
            <div class="left-banner">
                <a href="Controlador?menu=Exportar&accion=ExportarWord">
                    <img src="img/word.png" alt="Word" width="200"/>
                </a>
            </div>
            <div class="right-banner">
                <a href="Controlador?menu=Exportar&accion=ExportarPdf">
                    <img src="img/pdf.png" alt="PDF" width="200"/>
                </a>
            </div>
        </div>
    </div>
    
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
