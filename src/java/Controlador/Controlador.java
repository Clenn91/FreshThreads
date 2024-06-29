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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


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
                    if (pdao.codigoExiste(cod)) {
                        // El código ya existe, mostrar mensaje de error o redirigir
                        request.setAttribute("errorMensaje", "El código del producto ya existe.");
                        request.getRequestDispatcher("NuevoProducto.jsp").forward(request, response);
                    } else {
                        // El código no existe, agregar el producto
                        pr.setNom(nom);
                        pr.setCodigo(cod);
                        pr.setPrecio(precio);
                        pr.setStock(stock);
                        pr.setEstado(est);
                        pdao.agregar(pr);
                        request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    }
                    break;
                case "Buscar":
                    idp = request.getParameter("txtCodigo");
                    if (pdao.codigoExiste(idp)) {
                        Producto p = pdao.listarId(idp);
                        request.setAttribute("producto", p);
                        request.getRequestDispatcher("Controlador?menu=ModificarProducto").forward(request, response);
                    } else {
                        request.setAttribute("errorMensaje", "El código del producto no existe.");
                        request.getRequestDispatcher("Controlador?menu=ModificarProducto").forward(request, response);
                    }
                    break;
                  //  d
                case "Modificar":
                    String cod1 = request.getParameter("txtCodigo");
                    if (cod1 == null || cod1.isEmpty()) {
                        // Mostrar mensaje de error si el código está vacío
                        request.setAttribute("errorMensaje", "Ingrese un código válido para modificar el producto.");
                        request.getRequestDispatcher("Controlador?menu=ModificarProducto").forward(request, response);
                    } else {
                        // Continuar con la modificación si el código no está vacío
                        String nom1 = request.getParameter("txtNombres");
                        double precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock1 = Integer.parseInt(request.getParameter("txtStock"));

                        pr.setNom(nom1);
                        pr.setPrecio(precio1);
                        pr.setStock(stock1);
                        pr.setCodigo(cod1);

                        pdao.actualizar(pr);
                        request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    }
                    break;

                case "Buscar ":
                    
                    idp2 = request.getParameter("txtCodigo");
                    if (pdao.codigoExiste(idp2)) {
                        Producto p1 = pdao.listarId(idp2);
                        request.setAttribute("producto", p1);
                        request.getRequestDispatcher("Controlador?menu=EliminarProducto").forward(request, response);
                    } else {
                        request.setAttribute("errorMensaje", "El código del producto no existe.");
                        request.getRequestDispatcher("Controlador?menu=EliminarProducto").forward(request, response);
                    }
                    
                    break;
                case "Eliminar":
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
                    if (cl.getId() == 0) { // El cliente no está registrado
                        request.setAttribute("errorMensaje", "El cliente no está registrado.");
                        
                        request.getRequestDispatcher("RegisCliente.jsp").forward(request, response);
                    } else { // El cliente está registrado
                        request.setAttribute("c", cl);
                    }
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProducto":
                    String codi=request.getParameter("codigoproducto");
                    if (pdao.codigoExiste(codi)) {
                        pr=pdao.listarId(codi);
                        request.setAttribute("c", cl);
                        request.setAttribute("producto", pr);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalpagar", totalPagar);
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        request.setAttribute("errorMensaje", "El código del producto no existe.");
                        request.setAttribute("c", cl);
                    }
                    
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
                     if (cant <= pr.getStock()) {
                        subtotal = precio * cant;
                        v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setCodigoProducto(codiProducto);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        lista.add(v);

                        for (int i = 0; i < lista.size(); i++) {
                            totalPagar += lista.get(i).getSubtotal();
                        }
                        pdao.actualizarstock(cod, pr.getStock()-cant);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalpagar", totalPagar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    break;
                case "GenerarVenta":
                    // Actualización del stock
          /*          for (int i = 0; i < lista.size(); i++) {
                        Producto p = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        p = aO.buscar(idproducto);
                        int sac = p.getStock() - cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }*/
//Nota: El stock se actualiza cuando se agrega a la lista
                    // Guardar Venta
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(1);
                    v.setNumserie(numeroserie);
                    v.setFecha("2022-06-14");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);

                    // Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    request.getRequestDispatcher("Controlador?menu=Exportar").forward(request, response);
                    

        //            lista.clear();
       //             totalPagar = 0;
                    break;

                case "Cancelar":
                    for (int i = 0; i < lista.size(); i++) {
                        Producto p = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        p = aO.buscar(idproducto);
                        int sac = p.getStock() + cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }
                    lista.clear();
                    totalPagar=0;
                    response.getWriter().write("Stock restaurado correctamente");
                    break;
                case "Delete":
                    request.setAttribute("c", cl);
                    String id = request.getParameter("id");
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getCodigoProducto().equals(id)) {
                            Producto p = new Producto();
                            int cantidad = lista.get(i).getCantidad();
                            int idproducto = lista.get(i).getIdproducto();
                            ProductoDAO aO = new ProductoDAO();
                            p = aO.buscar(idproducto);
                            int sac = p.getStock() + cantidad;
                            aO.actualizarstock(idproducto, sac);
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
                        if (cdao.codigoExiste(dni)) {
                        // El código ya existe, mostrar mensaje de error o redirigir
                        request.setAttribute("errorMensaje", "El cliente ya existe.");
                        request.getRequestDispatcher("RegisCliente.jsp").forward(request, response);
                        } else {
                            cl.setNom(nom);
                            cl.setApep(apep);
                            cl.setApem(apem);
                            cl.setDni(dni);
                            cl.setTel(tel);
                            cl.setEstado(est);
                            cdao.agregar(cl);
                            request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response); 
                        }
                        
                        break;
                case "Buscar":
                    idc = request.getParameter("txtDni");
                    if (cdao.codigoExiste(idc)) {
                            Cliente c = cdao.listarId(idc);
                        request.setAttribute("cliente", c);
                        request.getRequestDispatcher("Controlador?menu=ModifCliente").forward(request, response);
                        } else {
                            request.setAttribute("errorMensaje", "El cliente no existe.");
                            request.getRequestDispatcher("RegisCliente.jsp").forward(request, response);
                        }
                    break;
                case "Modificar":
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
                case "Eliminar":
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
        if(menu.equals("Exportar")){
            
            switch (accion) {
                case "ExportarPdf":
                    try {
                        // Crear un nombre único para el archivo PDF con la fecha y hora actual
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                        LocalDateTime now = LocalDateTime.now();
                        String fileName = "voucher_" + dtf.format(now) + ".pdf";
                        String carpetaDescargas = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
                        String filePath = carpetaDescargas  + fileName;

                        // Iniciar la generación del PDF
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(filePath));
                        document.open();
                        document.add(new Paragraph("Voucher de Venta"));
                        document.add(new Paragraph("Número de Serie: " + numeroserie));
                        document.add(new Paragraph("Cliente: " + cl.getNom()));

                        PdfPTable table = new PdfPTable(5);
                        table.addCell("Item");
                        table.addCell("Código");
                        table.addCell("Descripción");
                        table.addCell("Cantidad");
                        table.addCell("Precio");

                        double totalSinIGV = 0;

                        for (Venta venta : lista) {
                            table.addCell(String.valueOf(venta.getItem()));
                            table.addCell(venta.getCodigoProducto());
                            table.addCell(venta.getDescripcionP());
                            table.addCell(String.valueOf(venta.getCantidad()));
                            table.addCell(String.valueOf(venta.getPrecio()));

                            totalSinIGV += venta.getPrecio();
                        }

                        // Calcular el IGV (18%)
                        double igv = totalSinIGV * 0.18;

                        // Agregar el IGV antes del total
                        PdfPCell igvCell = new PdfPCell(new Phrase("IGV (18%)"));
                        igvCell.setColspan(4);
                        igvCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(igvCell);
                        table.addCell(String.valueOf(igv));

                        PdfPCell totalCell = new PdfPCell(new Phrase("Total a Pagar "));
                        totalCell.setColspan(4);
                        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        table.addCell(totalCell);
                        table.addCell(String.valueOf(totalSinIGV));

                        document.add(table);
                        document.close();
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }
                    lista.clear();
                    totalPagar = 0;
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);
                    break;
                case "ExportarWord":
                    try {
                        // Crear un nombre único para el archivo Word con la fecha y hora actual
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                        LocalDateTime now = LocalDateTime.now();
                        String fileName = "voucher_" + dtf.format(now) + ".docx";
                        String carpetaDescargas = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
                        String filePath = carpetaDescargas + fileName;

                        // Crear un nuevo documento Word
                        XWPFDocument document = new XWPFDocument();

                        // Agregar contenido al documento Word
                        XWPFParagraph title = document.createParagraph();
                        title.setAlignment(ParagraphAlignment.CENTER);
                        XWPFRun titleRun = title.createRun();
                        titleRun.setText("Voucher de Venta");
                        titleRun.setBold(true);
                        titleRun.setFontSize(16);

                        XWPFParagraph seriePara = document.createParagraph();
                        XWPFRun serieRun = seriePara.createRun();
                        serieRun.setText("Número de Serie: " + numeroserie);

                        XWPFParagraph clientePara = document.createParagraph();
                        XWPFRun clienteRun = clientePara.createRun();
                        clienteRun.setText("Cliente: " + cl.getNom());

                        // Crear una tabla para los detalles de la venta
                        XWPFTable table = document.createTable();
                        XWPFTableRow tableRowOne = table.getRow(0);
                        tableRowOne.getCell(0).setText("Item");
                        tableRowOne.addNewTableCell().setText("Código");
                        tableRowOne.addNewTableCell().setText("Descripción");
                        tableRowOne.addNewTableCell().setText("Cantidad");
                        tableRowOne.addNewTableCell().setText("Precio");

                        double totalSinIGV = 0;

                        for (Venta venta : lista) {
                            XWPFTableRow tableRow = table.createRow();
                            tableRow.getCell(0).setText(String.valueOf(venta.getItem()));
                            tableRow.getCell(1).setText(venta.getCodigoProducto());
                            tableRow.getCell(2).setText(venta.getDescripcionP());
                            tableRow.getCell(3).setText(String.valueOf(venta.getCantidad()));
                            tableRow.getCell(4).setText(String.valueOf(venta.getPrecio()));

                            totalSinIGV += venta.getPrecio();
                        }

                        // Calcular el IGV (18%)
                        double igv = totalSinIGV * 0.18;

                        // Agregar el IGV antes del total
                        XWPFTableRow igvRow = table.createRow();
                        igvRow.getCell(0).setText("IGV (18%)");
                        igvRow.getCell(0).getCTTc().addNewTcPr().addNewGridSpan().setVal(BigInteger.valueOf(4));
                        igvRow.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
                        igvRow.getCell(4).setText(String.valueOf(igv));

                        XWPFTableRow totalRow = table.createRow();
                        totalRow.getCell(0).setText("Total a Pagar ");
                        totalRow.getCell(0).getCTTc().addNewTcPr().addNewGridSpan().setVal(BigInteger.valueOf(4));
                        totalRow.getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
                        totalRow.getCell(4).setText(String.valueOf(totalSinIGV));

                        // Guardar el documento Word
                        FileOutputStream out = new FileOutputStream(filePath);
                        document.write(out);
                        out.close();
                        document.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lista.clear();
                    totalPagar = 0;
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);
                    break;
                default:
        
                    break;      
            }
            request.getRequestDispatcher("Exportar.jsp").forward(request, response);
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
