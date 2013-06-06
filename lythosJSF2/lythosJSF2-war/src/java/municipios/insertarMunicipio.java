/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package municipios;

import dao.MunicipioFacade;
import entidades.Municipio;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pablo
 */
@ManagedBean
@RequestScoped
public class insertarMunicipio {
    @EJB
    private MunicipioFacade MunicipioFacade;
    /**
     * Creates a new instance of insertarMunicipio
     */
    private String nombre;
    private String fax;
    private String numero;
    private String CP;
    Municipio mun;
    
    
    public String getNombre(){
        return nombre;
    }
    
    public String getFax(){
        return fax;
    }
    public String getNumero(){
        return numero;
    }
    public String getCP(){
        return CP;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
     public void setNumero(String numero){
        this.numero = numero;
    }
    public void setFax(String fax){
        this.fax = fax;
    }
    public void setCP(String CP){
        this.CP = CP;
    }
    
    
    public insertarMunicipio() {
        mun = new Municipio();
    }
    
   
    public void insertando() throws IOException{
        mun.setNombre(nombre);
        mun.setFax(fax);
        mun.setNumero(numero);
        mun.setCp(CP);
        
        MunicipioFacade.create(mun);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminMunicipios.jsf");
        
    }
}
