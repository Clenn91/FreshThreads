<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inria+Serif&display=swap');
        
        .centered-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container-custom {
            width: 771px;
            height: 541px;
            background-color: rgba(101, 167, 188, 1);
            display: flex;
            align-items: center;
            padding: 20px;
        }
        .left-banner {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            
            font-family: 'Inria Serif', serif;
            font-size: 50px;
            color: #fff;
        }
        .form-container {
            background-color: rgba(154, 223, 238, 1);
            width: 289px;
            height: 479px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .form-group {
            text-align: center;
            margin-bottom: 25px;
        }
        .form-control {
            margin: auto;
        }
    </style>
    <title>Fresh Threads</title>
</head>
<body>
    <div class="centered-container">
        <div class="container-custom">
            <div class="left-banner">
                Fresh Treads
            </div>
            <div class="form-container ">
                <form class="form-sign " action="Validar" method="POST">
                    <div class="form-group">
                        <label style="font-family: 'Inria Serif', serif; font-size: 42px;">Bienvenidos al Sistema</label>
                    </div>
                    <div class="form-group">
                        <input placeholder="Usuario" type="text" name="txtuser" class="form-control" style="color: rgba(119, 112, 112, 0.9); width: 220px;">
                    </div>
                    <div class="form-group">
                        <input placeholder="Contraseña" type="password" name="txtpass" class="form-control" style="color: rgba(119, 112, 112, 0.9); width: 220px;">
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" style="background-color:rgba(101, 167, 188, 1)" type="submit" name="accion" value="Ingresar">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

