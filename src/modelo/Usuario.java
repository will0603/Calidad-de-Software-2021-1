package modelo;

import excepciones.InvalidPasswordException;
import excepciones.InvalidEmailException;
import excepciones.InvalidUsernameException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private int codigo;
    private String username;
    private String contraseña;
    private String email;
    private Boolean conectado;
    
    public Usuario(String username, String contraseña,String email) throws InvalidPasswordException, InvalidEmailException, InvalidUsernameException{
        setCodigo();
        setUsername(username);
        setContraseña(contraseña);
        setEmail(email);
        this.conectado = Boolean.FALSE;
    }

    public Usuario() {
    }
    
    public Usuario(int codigo) {
        this.codigo = codigo;
    }
    
    public Usuario(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public Usuario(int codigo, String username, String contraseña, String email, Boolean conectado) {
        this.codigo = codigo;
        this.username = username;
        this.contraseña = contraseña;
        this.email = email;
        this.conectado = conectado;
    }
    
    public String insertar(){
        Conexion conexion = new Conexion();
        String SQL = "insert into usuario (codigo,username,contraseña,email,conectado) values ('"+this.codigo+"','"+this.username+"','"+this.contraseña+"','"+this.email+"',"+this.conectado+")";
        return conexion.ejecutar(SQL);
    }
    /*
    private void setId() {
        Conexion conexion = new Conexion();
        String SQL = "select * from producto where nombre='"+this.nombre+"' and cantidad='"+this.cantidad+"' and precio='"+this.precio+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                this.id = resultado.getInt("id");
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    */
    public Usuario loguear(){
        Usuario result = null;
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where username='"+this.username+"' and contraseña='"+this.contraseña+"' and conectado="+false;
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                this.codigo=resultado.getInt("codigo");
                this.email=resultado.getString("email");
                this.conectado = Boolean.TRUE;
                actualizar();
                result = new Usuario(codigo, username, contraseña, email, conectado);
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public Usuario getUsuarioxEmail(String email){
        Usuario result = null;
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where email='"+email+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                this.codigo=resultado.getInt("codigo");
                this.username=resultado.getString("username");
                this.contraseña=resultado.getString("contraseña");
                this.email=resultado.getString("email");
                this.conectado = Boolean.FALSE;
                result = new Usuario(this.codigo, this.username,this.contraseña,this.email,this.conectado);
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public Usuario getUsuarioxUsername(String username){
        Usuario result = null;
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where username='"+username+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                this.codigo=resultado.getInt("codigo");
                this.username=resultado.getString("username");
                this.contraseña=resultado.getString("contraseña");
                this.email=resultado.getString("email");
                this.conectado = Boolean.FALSE;
                result = new Usuario(this.codigo, this.username,this.contraseña,this.email,this.conectado);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public void setCodigo() {
        Conexion conexion = new Conexion();
        String SQL = "select MAX(codigo) as codigo FROM usuario";
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if(resultado.next()){
                if(resultado.getString(1)!=null){
                    this.codigo=Integer.parseInt(resultado.getString(1))+1;
                }else{
                    this.codigo = 1;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean salir(){
        boolean result = false;
        if(isConectado()){
            result = true;
            this.conectado = Boolean.FALSE;
            actualizar();
        }
        return result;
    }
    
    public String eliminar() {
        Conexion conexion = new Conexion();
        String SQL = "delete from usuario where codigo='"+this.codigo+"'";
        return conexion.ejecutar(SQL);
    }
    
    public boolean consultar() {
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where codigo='"+this.codigo+"'";
        ResultSet resultado = conexion.consultar(SQL);
        try {
            if (resultado.next()){ 
            this.username=resultado.getString("username");
            this.contraseña=resultado.getString("contraseña");
            this.email=resultado.getString("email");
            this.conectado = resultado.getBoolean("conectado");
            return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public String actualizar() {
        Conexion conexion = new Conexion();
        String SQL = "update usuario set username='"+this.username
        +"', contraseña='"+this.contraseña+"', email='"+this.email+"', conectado="+this.conectado+"  where codigo='"+this.codigo+"'";
        return conexion.ejecutar(SQL);
    }
    
    public String[][] buscar(String filtro){
        Conexion con = new Conexion();
        String SQL = "select * from usuario where username like '"+filtro+"%'";   //Buscar nick's que empiezen con "filtro"
        ResultSet resultado=con.consultar(SQL);
        String[][] datos = null;
        try{
            resultado.last();
            datos= new String[resultado.getRow()][4];
            resultado.beforeFirst();
            int i=0;
            while(resultado.next()){
                datos[i][0]= resultado.getString("codigo");
                datos[i][1]= resultado.getString("username");
                datos[i][2]= resultado.getString("contraseña");
                datos[i][3]= resultado.getString("email");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return datos;
    
    }
    
    public Boolean isConectado() {
        return conectado;
    }
    
    public String getLogin(){
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where email='"+email+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
            
            if((email.endsWith("@hotmail.com")||email.endsWith("@gmail.com")||email.endsWith("@unmsm.edu.pe")) && !resultado.next()){
                this.email = email;
            }else{
                throw new InvalidEmailException(email); 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void setContraseña(String password) throws InvalidPasswordException {
        if(password.length()>=6){
            this.contraseña=password;
        }else{
            throw new InvalidPasswordException(password);
        }
    }

    public void setUsername(String username) throws InvalidUsernameException{
        Conexion conexion = new Conexion();
        String SQL = "select * from usuario where username='"+username+"'";
        
        try{
            ResultSet resultado = conexion.consultar(SQL);
             
            if(resultado.next()){
                throw new InvalidUsernameException(username);
            }else{
                this.username = username;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", username=" + username + ", contrase\u00f1a=" + contraseña + ", email=" + email + ", conectado=" + conectado + '}';
    }

}
