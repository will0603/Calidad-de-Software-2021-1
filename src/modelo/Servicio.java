/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Cjuro
 */
public class Servicio {
    private String codigo;
    private Celular celular;
    private float precio;
    private String descripcion;

    public Servicio(String codigo, Celular celular, float precio, String descripcion) {
        this.codigo = codigo;
        this.celular = celular;
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    
    
}
