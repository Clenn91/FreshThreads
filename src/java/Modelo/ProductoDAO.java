/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tintaya
 */
public class ProductoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
        
    public Producto buscar(int id){
        Producto pr=new Producto();
        String sql="select * from producto where idproducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return pr;
    }
    
    public int actualizarstock(int id, int stock){
        String sql="update producto set Stock=? where idproducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return r;
    }
    
    public List listar(){
        String sql="select * from producto";
        List<Producto>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto pr=new Producto();
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setCodigo(rs.getString(3));
                pr.setPrecio(rs.getDouble(4));
                pr.setStock(rs.getInt(5));
                pr.setEstado(rs.getString(6));
                lista.add(pr);
            }
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Producto pr){
        String sql="insert into producto(Nombres,Codigo,Precio,Stock,Estado)values(?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pr.getNom());
            ps.setString(2, pr.getCodigo());
            ps.setDouble(3, pr.getPrecio());
            ps.setInt(4, pr.getStock());
            ps.setString(5, pr.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return r;
    }
    
    public Producto listarId(String codigo){
        Producto pr=new Producto();
        String sql="select * from producto where Codigo="+codigo;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setCodigo(rs.getString(3));
                pr.setPrecio(rs.getDouble(4));
                pr.setStock(rs.getInt(5));
                pr.setEstado(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return pr;
    }
    public int actualizar(Producto pr){
        String sql="update producto set Nombres=?,Codigo=?, Precio=?, Stock=? where Codigo=? ";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pr.getNom());
            ps.setString(2, pr.getCodigo());
            ps.setDouble(3, pr.getPrecio());
            ps.setInt(4, pr.getStock());
            ps.setString(5, pr.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return r;
    }
    
    public void delete(String codigo){
        String sql="delete from producto where Codigo="+codigo;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
