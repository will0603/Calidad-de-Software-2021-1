
package modelo;

import interfaces.IColecciones;

public class UsuarioArreglo implements IColecciones{
    private Usuario[] usuarios;
    private int indice;

    public UsuarioArreglo() {
        this.usuarios = new Usuario[10];
        this.indice = 0;
    }
    
    public Usuario ingresar(String username, String password){
        Usuario result = null;
        for(int i=0; i< this.indice ; i++){
            if( this.usuarios[i].ingresar(username, password)
                    ){
                result = this.usuarios[i];
                break;
            }
        }
        return result;
    }
    
    public boolean isLleno(){
        boolean result = false;
            if(indice == usuarios.length){
                result = Boolean.TRUE;
            }
        return result;
    }
     
    public Usuario getUsuarioxEmail( String email){
        Usuario result = null;
        for(int i=0; i < this.indice; i++){
            if(this.usuarios[i].getEmail().equalsIgnoreCase(email)){
                result =  this.usuarios[i] ;
                break;
            }    
        }
        return result;
    }
    
    public Usuario getUsuarioxNombre( String nombre){
        Usuario result = null;
        for(int i=0; i < this.indice; i++){
            if(this.usuarios[i].getLogin().equalsIgnoreCase(nombre)){
                result =  this.usuarios[i] ;
                break;
            }    
        }
        return result;
    }
    
    public void aumentarTamanho(){
            Usuario[] temp = new Usuario[indice];
            for(int i=0; i< indice; i++){
                temp[i] = usuarios[i];
            }
            usuarios = new Usuario[indice+10];
            for(int i=0; i< indice; i++){
                usuarios[i] = temp[i];
            }
    }
    
    @Override
    public String toString() {
        String result = "ArregloUsuario\n";
        for(int i=0;i< this.indice ;i++){
            result += this.usuarios[i] + "\n";
        }
        return result;
    }

    @Override
    public boolean add(Object elemento) {
        boolean result = false;
        if(!isLleno()){
            this.usuarios[this.indice] = (Usuario) elemento;
            this.indice++;
            result = true;
        }else{
            aumentarTamanho();
            this.usuarios[this.indice] = (Usuario) elemento;
            this.indice++;
            result = true;
        }
        return result;    
    }

    @Override
    public int find(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[][] getDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getCabecera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moverElementos(int posicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isVacio() {
        return this.indice==0;
    }
}
