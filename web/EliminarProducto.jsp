<%-- 
    Document   : EliminarProducto
    Created on : 14 abr. 2024, 12:51:59
    Author     : tintaya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>
        <style>
            .content{
                margin-bottom: 10px;
                padding-left: 8px;
                margin-right: 5px;
            }
            .content_des{
                padding-bottom: 40px;
            }
            
        </style>
    </head>
    <body>
        <div class="d-flex justify-content-center">
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <form action="Controlador?menu=Productos" method="POST">
                            <div class="form-group mb-3 ">
                            <label>ELIMINAR PRODUCTO</label>
                            </div>
                            <div class="form-group mb-3 d-flex" >
                                <div class="col-sm-6">
                                    <input  type="text" name="txtCodigo" value="${producto.getCodigo()}" class="form-control " placeholder="Código">

                                </div>
                                <div class="col-sm-4">
                                    <input type="submit" name="accion" value="Buscar " class="btn btn-primary">

                                </div>

                            </div>
                            <div class="d-flex">
                                <div class="form-group mb-3">
                                    <div class="col-sm-10 mr-3 ">
                                        <input type="text" name="txtPrecio" value="${producto.getPrecio()}" class="form-control " placeholder="Precio">
                                    </div>
                                    <div class="col-sm-10 ">
                                        <input  type="text" name="txtStock" value="${producto.getStock()}" class="form-control" placeholder="Cantidad">
                                    </div>                      
                                </div>
                                <div class="form-group mb-8">
                                    <div class="col-sm-12 mr-8 ">
                                        <input type="text" name="txtNombres" value="${producto.getNom()}" class="form-control" placeholder="Descripción">
                                    </div>                             
                                </div>                            
                            </div>

                            <div class="form-group d-flex justify-content-between">
                                <div class="col-sm-6">
                                    <input type="submit" name="accion" value="Delete" class="btn btn-primary">
                                </div> 
                                <div class="col-sm-6">
                                    <a class="btn btn-danger" href="Controlador?menu=Productos&accion=Listar" role="button" >Cancelar</a>
                                </div>                     

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    </body>
    
</html>