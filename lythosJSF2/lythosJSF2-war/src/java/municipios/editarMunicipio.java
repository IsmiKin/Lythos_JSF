/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package municipios;

import dao.MunicipioFacade;
import entidades.Municipio;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlForm;

/**
 *
 * @author Pablo
 */
@ManagedBean
@RequestScoped
public class editarMunicipio {

    /**
     * Creates a new instance of editarMunicipio
     */
    @EJB
    MunicipioFacade MunicipioFacade;
    private String nombre;
    private String numero;
    private String Cp;
    private String fax;
    private Municipio mun;
    private Municipio aEditar;
    private Integer idMunicipio;
    
    
    
    public editarMunicipio() {
        mun = new Municipio();
        aEditar = new Municipio();
    }
    
    @PostConstruct
    public void editarMunicipio(){
       
       
    }
 
    
    public String getNombre(){
        return nombre;
    }
    
    public String getFax(){
        return fax;
    }
    public String getNumero(){
        return numero;
    }
    public String getCp(){
        return Cp;
    }
    
    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
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
    public void setCp(String Cp){
        this.Cp = Cp;
    }
    
    
    public void editando(){
        
        aEditar = mun;
        MunicipioFacade.edit(aEditar);
    }
    /*
    public String goToEditar() throws IOException{
       return "editarMunicipio.jsf";
    }
    * */
}
