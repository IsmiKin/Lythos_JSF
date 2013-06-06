package modelosFijos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ModelofijoFacade;
import entidades.Modelofijo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ROOT
 */
@ManagedBean(name="adminModelosFijosBean")
@RequestScoped
public class adminModelosFijosBean {

    /**
     * Creates a new instance of adminModelosFijosBean
     */
    
    @EJB
    ModelofijoFacade modelofijofacade;
        
      
    private int idModelosFijos;
    private String detalles;
    private String imagen;
    private Date lanzamiento;
    private String marca;
    private String modelo;
    
    List<Modelofijo> todosmodelos;
    
    private Modelofijo modfijo;
    private Modelofijo aEditar;
    private Modelofijo selectedmodelofijo;
    
    private List<Modelofijo> filteredModeloFijo;
    
    @PostConstruct
    public void adminModelosFijosBean(){
       todosmodelos=(List<Modelofijo>) modelofijofacade.findAll();
       selectedmodelofijo  = new Modelofijo();
    }
    
    public List<Modelofijo> getModelos(){
       return todosmodelos;
    }
    
    public adminModelosFijosBean() {
        

    }

    public int getidModelosFijos() {
        return idModelosFijos;
    }

    public void setdModelosFijos(int idModelosFijos) {
        this.idModelosFijos = idModelosFijos;
    }
    
    
    
    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Date lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Modelofijo getSelectedmodelofijo() {
        return selectedmodelofijo;
    }

    public void setSelectedmodelofijo(Modelofijo selectedmodelofijo) {
        this.selectedmodelofijo = selectedmodelofijo;
    }
    
    public String goToEditar() throws IOException{
       return "editarModelosFijos.jsf";
       
      
   }

    public String editando(){
//        Modelofijo modelofijo = new Modelofijo();
//        modelofijo.setDetalles(detalles);
//        modelofijo.setImagen(imagen);
//        //modfijo.setLanzamiento(lanzamiento);
//        modelofijo.setMarca(marca);
//        modelofijo.setModelo(modelo);
        modelofijofacade.edit(this.selectedmodelofijo);
        todosmodelos=(List<Modelofijo>) modelofijofacade.findAll();
        return "adminModelosFijos.jsf";
        
               
    }
    
    
     public String editandoForm(){
       /*  this.setdModelosFijos(this.selectedmodelofijo.getIdModeloFijo());
         this.setDetalles(this.selectedmodelofijo.getDetalles());
         this.setImagen(this.selectedmodelofijo.getImagen());
         this.setMarca(this.selectedmodelofijo.getMarca());
         this.setModelo(this.selectedmodelofijo.getModelo());*/
         return "editarModelosFijos.jsf";
     }
     
      public List<Modelofijo> getFilteredModeloFijo() {  
        return filteredModeloFijo;  
    }  
  
    /**
     *
     * @param filteredMunicipios
     */
    public void setfilteredModeloFijo(List<Modelofijo> filteredModeloFijo) {  
        this.filteredModeloFijo = filteredModeloFijo;  
    } 
    
     
    
    public void insertando() throws IOException{
        Modelofijo modelofijo = new Modelofijo();
        modelofijo.setDetalles(detalles);
        modelofijo.setImagen(imagen);
        //modfijo.setLanzamiento(lanzamiento);
        modelofijo.setMarca(marca);
        modelofijo.setModelo(modelo);
        modelofijofacade.create(modelofijo);
        todosmodelos=(List<Modelofijo>) modelofijofacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminModelosFijos.jsf");
    }


    
    public void Delete(Modelofijo modelofijo){
        modelofijofacade.remove(modelofijo);
        todosmodelos=(List<Modelofijo>) modelofijofacade.findAll();
    }
}
