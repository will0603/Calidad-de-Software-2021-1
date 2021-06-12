  package modelo;

import excepciones.InvalidPasswordException;
import excepciones.InvalidEmailException;
import excepciones.InvalidLoginException;
import general.Sistema;
import java.util.Date;
import utils.Email;

public class Usuario {
    private String nombres;
    private String apellidos;
    private String login;
    private String contraseña;
    private String email;
    private int edad;
    private String pais;
    private String genero;
    private Boolean conectado;
    
    public Usuario(String nombres, String apellidos, String login, String contraseña, 
            String email, String pais, Date fechaNacimiento, String genero) throws InvalidPasswordException, InvalidEmailException, InvalidLoginException{
        this.nombres = nombres;
        this.apellidos = apellidos;
        setLogin(login);
        setContraseña(contraseña);
        setEmail(email);
        this.pais = pais;
        this.edad = fechaNacimiento.getYear();
        this.genero = genero;
        this.conectado = Boolean.FALSE;
    }
    
    public boolean ingresar(String username, String password){
        boolean result = false;
        if( this.login.equalsIgnoreCase(username) &&
                this.contraseña.equals(password) &&
                !this.isConectado() ){
            result = true;
            this.conectado = Boolean.TRUE;
        }
        return result;
    }

    public Boolean isConectado() {
        return conectado;
    }
    
    public String getLogin(){
        return login;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        
        if((email.endsWith("@hotmail.com")||email.endsWith("@gmail.com")||email.endsWith("@unmsm.edu.pe"))
                            && Sistema.usuarios.getUsuarioxEmail(email)== null){
            this.email = email;
        }else{
            throw new InvalidEmailException(email);
        }
    }
    
    public void setContraseña(String password) throws InvalidPasswordException {
        if(password.length()>=6){
            this.contraseña=password;
        }else{
            throw new InvalidPasswordException(password);
        }
    }

    public void setLogin(String login) throws InvalidLoginException{
        if(Sistema.usuarios.getUsuarioxNombre(login)==null ){
            this.login = login;
        }else{
            throw new InvalidLoginException(login);
        }
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    
    @Override
    public String toString() {
        return  "login=" + login + ", contrase\u00f1a=" + contraseña;
    }
    
}
