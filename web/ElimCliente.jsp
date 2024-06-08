<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>
        <style>
            body{
                background-color: rgba(101, 167, 188, 1);
            }
        </style>
    </head>
    <body>
<div class="d-flex justify-content-center" style="padding-top:100px">
    <div class="col-sm-4">
        <div class="card shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
                <form action="Controlador?menu=Clientes" method="POST">
                    <div class="form-group mb-3 ">
                    <label>ELIMINAR CLIENTE</label>
                    </div>
                    <div class="form-group mb-3 d-flex" >
                        <div class="col-sm-6">
                            <input type="text" name="txtDni" value="${cliente.getDni()}" class="form-control" placeholder="DNI">

                        </div>
                        <div class="col-sm-4">
                            <input type="submit" name="accion" value="Buscar " class="btn btn-primary">
                        </div>

                    </div>
                    <div class="form-group d-flex mb-3">
                        <div class="col-sm-6 mr-3">
                            <input type="text" name="txtNombres" value="${cliente.getNom()}" class="form-control" placeholder="Nombres">
                        </div>
                        <div class="col-sm-6">
                            <input type="text" name="txtApep" value="${cliente.getApep()}" class="form-control" placeholder="Apellido Paterno">
                        </div>                      
                    </div>
                    <div class="form-group d-flex mb-3">
                        <div class="col-sm-6 mr-3">
                            <input type="text" name="txtApem" value="${cliente.getApem()}" class="form-control" placeholder="Apellido Materno">
                        </div>
                        <div class="col-sm-6">
                            <input type="text" name="txtTel" value="${cliente.getTel()}" class="form-control" placeholder="Teléfono">
                        </div>                             
                    </div>
                    <div class="form-group d-flex justify-content-between">
                        <div class="col-sm-6">
                            <input type="submit" name="accion" value="Delete" class="btn btn-primary">
                        </div> 
                        <div class="col-sm-6">
                            <a class="btn btn-danger" href="Controlador?menu=Clientes&accion=Listar" role="button" >Cancelar</a>
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
