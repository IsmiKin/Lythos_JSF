/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package municipios;

import dao.MunicipioFacade;
import entidades.Municipio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pablo
 */
@ManagedBean
@ApplicationScoped
public class adminMunicipios {
    
    @EJB
    MunicipioFacade MunicipioFacade;
    
    private List<Municipio> todos;
    private Municipio mun;
    private Municipio aEditar;
    private Municipio aInsertar;
    private List<Municipio> filteredMunicipios;
    
    
    /**
     * Creates a new instance of adminMunicipios
     */
    public adminMunicipios() {
        this.mun = new Municipio();
        this.aEditar = new Municipio();
    }
    
   @PostConstruct
   public void adminMunicipios(){
       todos = (List<Municipio>) MunicipioFacade.findAll();
       
    } 
   
   public List<Municipio> getMunicipios(){
       return todos;
   }
   
   public Municipio getMunicipio(){
       return mun;
   }
   
   public void setMunicipio(Municipio mun){
       this.mun = mun;
   }
   
   public void borrarMunicipio(Municipio mun){
       MunicipioFacade.remove(mun);
       todos = (List<Municipio>) MunicipioFacade.findAll();
   }

    public Municipio getaEditar() {
        return aEditar;
    }

    public void setaEditar(Municipio aEditar) {
        this.aEditar = aEditar;
    }

    public Municipio getaInsertar() {
        return aInsertar;
    }

    public void setaInsertar(Municipio aInsertar) {
        this.aInsertar = aInsertar;
    }
   
   public String goToEditar() throws IOException{
       return "editarMunicipio.jsf";
       
       /*
       ExternalContext oContext = FacesContext.getCurrentInstance().getExternalContext();

        oContext.redirect(oContext.getRequestContextPath() + "/editarMunicipio.jsf");
        */
   }
   
    public void actualizarMunicipio() throws IOException
    {
        aEditar = mun;
        MunicipioFacade.edit(aEditar);
        todos = (List<Municipio>) MunicipioFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminMunicipios.jsf");
        
    }
    
    public List<Municipio> getFilteredMunicipios() {  
        return filteredMunicipios;  
    }  
    
    public void insertando() throws IOException{
        
        
        MunicipioFacade.create(mun);
        mun = new Municipio();
        todos = (List<Municipio>) MunicipioFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminMunicipios.jsf");
        
    }
  
    /**
     *
     * @param filteredMunicipios
     */
    public void setfilteredMunicipios(List<Municipio> filteredMunicipios) {  
        this.filteredMunicipios = filteredMunicipios;  
    } 
    
}
