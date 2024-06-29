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
public class ClienteDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
    
    public List listar(){
        String sql = "select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cl= new Cliente();
                cl.setId(rs.getInt(1)); 
                cl.setNom(rs.getString(2));
                cl.setApep(rs.getString(3));
                cl.setApem(rs.getString(4));
                cl.setTel(rs.getString(5));
                cl.setDni(rs.getString(6));
                lista.add(cl);
            }
        }catch(Exception e){
            
        }
        return lista;
    }
    public int agregar(Cliente cl){
        String sql="insert into cliente(Nombres,ApellidoPaterno,ApellidoMaterno,Telefono,Dni,Estado)values(?,?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            
            ps.setString(1, cl.getNom());
            ps.setString(2, cl.getApep());
            ps.setString(3, cl.getApem());
            ps.setString(4, cl.getTel());
            ps.setString(5, cl.getDni());
            ps.setString(6, cl.getEstado());
            ps.executeUpdate(); 
        } catch (Exception e) {
            System.out.println("error: aqui"+e.getMessage());
        }
        return r;
    }
    public Cliente listarId(String dni){
        Cliente cl=new Cliente();
        String sql="select * from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cl.setId(rs.getInt(1));
                cl.setNom(rs.getString(2));
                cl.setApep(rs.getString(3));
                cl.setApem(rs.getString(4));
                cl.setTel(rs.getString(5));
                cl.setDni(rs.getString(6));
                cl.setEstado(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return cl;
    }
    public int actualizar(Cliente cl){
        String sql="update cliente set Nombres=?,ApellidoPaterno=?, ApellidoMaterno=?, Telefono=?, Dni=? where Dni=? ";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getNom());
            ps.setString(2, cl.getApep());
            ps.setString(3, cl.getApem());
            ps.setString(4, cl.getTel());
            ps.setString(5, cl.getDni());
            ps.setString(6, cl.getDni());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return r;
    }
    
    public void delete(String dni){
        String sql="delete from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public Cliente buscar(String dni){
        Cliente cl=new Cliente();
        String sql="Select * from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cl.setId(rs.getInt(1));
                cl.setNom(rs.getString(2));
                cl.setApep(rs.getString(3));
                cl.setApem(rs.getString(4));
                cl.setTel(rs.getString(5));
                cl.setDni(rs.getString(6));
                cl.setEstado(rs.getString(7));
            }
        } catch (Exception e) {
        }
        return cl;
    }
    public boolean codigoExiste(String dni) {
        boolean existe = false;
        try {
            con = cn.Conexion(); // Asegúrate de usar el método de conexión correcto
            String sql = "SELECT COUNT(*) FROM cliente WHERE Dni = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }
}
