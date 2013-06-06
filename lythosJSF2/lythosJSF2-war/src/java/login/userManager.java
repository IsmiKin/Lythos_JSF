/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dao.AutorizacionFacade;
import dao.UsuarioFacade;
import entidades.Autorizacion;
import entidades.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author IsmiKinPorti
 */
@ManagedBean(name = "apiLogin")
@ApplicationScoped
public class userManager {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private AutorizacionFacade autorizacionFacade;

    private Usuario usuarioLogueado;
    private Autorizacion authLogueado;
    
    private String nickname="";
    private String password="";
    private String levelAccess="";
    
    /**
     * Creates a new instance of userManager
     */
    public userManager() {
    }
    
    // GETTER AND SETTERS
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Autorizacion getAuthLogueado() {
        return authLogueado;
    }

    public void setAuthLogueado(Autorizacion authLogueado) {
        this.authLogueado = authLogueado;
    }

    public String getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(String levelAccess) {
        this.levelAccess = levelAccess;
    }
    
    
    // METODOS PROPIOS
    
    public void checkExistUsername() throws IOException{
        
       
    }
    
    public void loginUser() throws IOException{
     
        
        if(autorizacionFacade.checkLogin(nickname, password))
            authLogueado = autorizacionFacade.getByNickname(nickname);
        else
            FacesContext.getCurrentInstance().getExternalContext().redirect("errorLogin.jsf");
            
        
         if(authLogueado!=null){
             usuarioLogueado = usuarioFacade.getUserByAuto(authLogueado);
             if(usuarioLogueado.getRol().equals("Administrador")) this.levelAccess = "0";
             else if(usuarioLogueado.getRol().equals("Controlador")) this.levelAccess = "1";
             else if(usuarioLogueado.getRol().equals("JefeServicio")) this.levelAccess = "2";
             else if(usuarioLogueado.getRol().equals("Usuario")) this.levelAccess = "3";             
         }
            
        
         FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
    }
    
    // HAY QUE USAR SESSION.. ESTO ES UN PARCHE PASAJERO (ISMIKIN)
    public void logoutUser() throws IOException{
        this.nickname ="";
        this.password="";
        this.authLogueado = null;
        this.usuarioLogueado=null;
         FacesContext.getCurrentInstance().getExternalContext().redirect("../home/index2.jsf");
    }
}
