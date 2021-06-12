
import controlador.ControladorLoginInicio;
import excepciones.InvalidEmailException;
import excepciones.InvalidLoginException;
import excepciones.InvalidPasswordException;
import general.Sistema;
import java.util.Date;
import modelo.Usuario;
import vista.frmIniciar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cjuro
 */
public class AppMain {

    public static void main(String[] args) {
        
        try{   
        Sistema.usuarios.add(new Usuario("Jimmy","Cjuro Apaza","JimmyCjuro", "secreta", "jimmy.cjuro@unmsm.edu.pe", "PERU", new Date(2000-1900, 0, 15), "hombre"));
        }catch(InvalidEmailException e1){
            System.out.println(e1);
        }catch(InvalidPasswordException e2){
            System.out.println(e2);
        }catch(InvalidLoginException e3){
            System.out.println(e3);
        }
        
        frmIniciar fInicio = new frmIniciar();
        ControladorLoginInicio controlador = new ControladorLoginInicio(Sistema.usuarios, fInicio);
        controlador.iniciar();
        
    }
}
