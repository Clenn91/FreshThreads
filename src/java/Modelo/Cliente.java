/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author tintaya
 */
public class Cliente {
    int id;
    String dni;
    String nom;
    String apep;
    String apem;
    String tel;
    String estado;

    public Cliente() {
    }

    public Cliente(int id, String dni, String nom, String apep, String apem, String tel, String estado) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.apep = apep;
        this.apem = apem;
        this.tel = tel;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApep() {
        return apep;
    }

    public void setApep(String apep) {
        this.apep = apep;
    }

    public String getApem() {
        return apem;
    }

    public void setApem(String apem) {
        this.apem = apem;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
