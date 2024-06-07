/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tintaya
 */
public class Controlador extends HttpServlet {
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto pr = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    String idc,idc2;
    String idp,idp2;
    //Necesario para ventas
    Venta v=new Venta();
    List<Venta>lista=new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    String numeroserie;
    VentaDAO vdao=new VentaDAO();
    String codiProducto;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("accion");
        String menu=request.getParameter("menu");
        if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if(menu.equals("Productos")){
            switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("Productos.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    String cod = request.getParameter("txtCodigo");
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtEstado");
                    pr.setNom(nom);
                    pr.setCodigo(cod);
                    pr.setPrecio(precio);
                    pr.setStock(stock);
                    pr.setEstado(est);
                    pdao.agregar(pr);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    idp = request.getParameter("txtCodigo");
                    Producto p = pdao.listarId(idp);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=ModificarProducto").forward(request, response);
                    break;
                case "Actualizar":
                    String nom1 = request.getParameter("txtNombres");
                    double precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String cod1 = request.getParameter("txtCodigo");
                    pr.setNom(nom1);
                    pr.setPrecio(precio1);
                    pr.setStock(stock1);
                    pr.setCodigo(cod1);
                    
                    pdao.actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;
                case "Buscar ":
                    idp2 = request.getParameter("txtCodigo");
                    Producto p1 = pdao.listarId(idp2);
                    request.setAttribute("producto", p1);
                    request.getRequestDispatcher("Controlador?menu=EliminarProducto").forward(request, response);
                    break;
                case "Delete":
                    idp = request.getParameter("txtCodigo");
                    pdao.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;
                default:
   /*                 numeroserie=vdao.GenerarSerie();
                    if(numeroserie==null){
                        numeroserie="00000001";
                        request.setAttribute("nserie", numeroserie);
                    }else{
                        int incrementar=Integer.parseInt(numeroserie);
                        GenerarSerie gs=new GenerarSerie();
                        numeroserie=gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);}*/
                }
            }
        if(menu.equals("Ventas")){
            
                switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    cl.setDni(dni);
                    cl = cdao.buscar(dni);
                    request.setAttribute("c", cl);
                    break;
                case "BuscarProducto":
                    String codi=request.getParameter("codigoproducto");
                    pr=pdao.listarId(codi);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "Agregar":
                    request.setAttribute("c", cl);
                    totalPagar=0.0;
                    item = item+1;
                    cod=pr.getId();
                    codiProducto=pr.getCodigo();
                    descripcion=request.getParameter("nomproducto");
                    precio=Double.parseDouble(request.getParameter("precio"));
                    cant=Integer.parseInt(request.getParameter("cant"));
                    subtotal=precio*cant;
                    v=new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setCodigoProducto(codiProducto);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);                                        
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar=totalPagar+lista.get(i).getSubtotal();
                    }
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
     //               request.setAttribute("nserie", numeroserie);
                    break;
                case "GenerarVenta":
                    //Actualizacion del Stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto p=new Producto();
                        int cantidad=lista.get(i).getCantidad();
                        int idproducto=lista.get(i).getIdproducto();
                        ProductoDAO aO=new ProductoDAO();
                        p=aO.buscar(idproducto);
                        int sac=p.getStock()-cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }
                    //Guardar Venta
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(1);
                    v.setNumserie(numeroserie);
                    v.setFecha("2022-06-14");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv=Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v=new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    lista.clear();
                    totalPagar=0;
                    break;
                case "Cancelar":
                    lista.clear();
                    totalPagar=0;
                    break;
                case "Delete":
                    request.setAttribute("c", cl);
                    String id = request.getParameter("id");
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getCodigoProducto().equals(id)) {
                            lista.remove(i);
                            break;
                        }
                    }
                    totalPagar = 0.0;
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    break;
                default:
                    numeroserie=vdao.GenerarSerie();
                    if(numeroserie==null){
                        numeroserie="00000001";
                        request.setAttribute("nserie", numeroserie);
                    }else{
                        int incrementar=Integer.parseInt(numeroserie);
                        GenerarSerie gs=new GenerarSerie();
                        numeroserie=gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);
            
            }
                request.getRequestDispatcher("Ventas.jsp").forward(request, response);
            
            
        
        }
        if(menu.equals("Clientes")){
            
            switch(accion){
                case "Listar":
                    List lista =cdao.listar();
                    request.setAttribute("clientes", lista);
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                    break;
                case "Registrar":
                        String nom = request.getParameter("txtNombre");
                        String apep = request.getParameter("txtApep");
                        String apem = request.getParameter("txtApem");
                        String dni = request.getParameter("txtDni");
                        String tel = request.getParameter("txtTel");
                        String est = "A";
                        cl.setNom(nom);
                        cl.setApep(apep);
                        cl.setApem(apem);
                        cl.setDni(dni);
                        cl.setTel(tel);
                        cl.setEstado(est);
                        cdao.agregar(cl);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                        break;
                case "Buscar":
                    idc = request.getParameter("txtDni");
                    Cliente c = cdao.listarId(idc);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=ModifCliente").forward(request, response);
                    break;
                case "Actualizar":
                    String nom1 = request.getParameter("txtNombres");
                    String apep1 = request.getParameter("txtApep");
                    String apem1 = request.getParameter("txtApem");
                    String dni1 = request.getParameter("txtDni");
                    String tel1 = request.getParameter("txtTel");
                    cl.setNom(nom1);
                    cl.setApep(apep1);
                    cl.setApem(apem1);
                    cl.setDni(dni1);
                    cl.setTel(tel1);
                    
                    cdao.actualizar(cl);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Buscar ":
                    idc2 = request.getParameter("txtDni");
                    Cliente cl1 = cdao.listarId(idc2);
                    request.setAttribute("cliente", cl1);
                    request.getRequestDispatcher("Controlador?menu=ElimCliente").forward(request, response);
                    break;
                case "Delete":
                    idc = request.getParameter("txtDni");
                    cdao.delete(idc);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            
        }
        if(menu.equals("RegisCliente")){
            request.getRequestDispatcher("RegisCliente.jsp").forward(request, response);
        }
        if(menu.equals("ModifCliente")){
            request.getRequestDispatcher("ModifCliente.jsp").forward(request, response);
        }
        if(menu.equals("ElimCliente")){
            request.getRequestDispatcher("ElimCliente.jsp").forward(request, response);
        }
        if(menu.equals("NuevoProducto")){
            request.getRequestDispatcher("NuevoProducto.jsp").forward(request, response);
        }
        if(menu.equals("ModificarProducto")){
            request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
        }
        if(menu.equals("EliminarProducto")){
            request.getRequestDispatcher("EliminarProducto.jsp").forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
