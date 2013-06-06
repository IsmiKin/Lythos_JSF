/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloTerminalMovil;

import dao.ModelomovilFacade;
import entidades.Modelomovil;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author IsmiKinPorti
 */
@ManagedBean(name = "administracionModelos")
@ApplicationScoped
public class AdminModeloMovilBean {
 @EJB
    private ModelomovilFacade modelomovilFacade;
    
    public List<Modelomovil> todosModelos;
    public Modelomovil model;
    public Modelomovil AEditar;
    public Modelomovil AAnadir;
    
    /**
     * Creates a new instance of AdminModeloMovilBean
     */
    public AdminModeloMovilBean() {
        this.AAnadir = new Modelomovil();
        this.AEditar = new Modelomovil();
    }

    public List<Modelomovil> getTodosModelos() {
        return todosModelos;
    }

    public void setTodosModelos(List<Modelomovil> todosModelos) {
        this.todosModelos = todosModelos;
    }

    public Modelomovil getModel() {
        return model;
    }

    public void setModel(Modelomovil model) {
        this.model = model;
    }

    public Modelomovil getAEditar() {
        return AEditar;
    }

    public void setAEditar(Modelomovil aEditar) {
        this.AEditar = aEditar;
    }

    public Modelomovil getAAnadir() {
        return AAnadir;
    }

    public void setAAnadir(Modelomovil AAnadir) {
        this.AAnadir = AAnadir;
    }
    
    @PostConstruct
    public void InicListaModelos()
    {
        this.todosModelos = modelomovilFacade.findAll();
        
    }
    
    public void eliminarModelo() throws IOException
    {
        modelomovilFacade.remove(this.model);
        InicListaModelos();
   //     FacesContext.getCurrentInstance().getExternalContext().redirect("adminModelos.jsf");
    }
    
    public String editarModelo()
    {
        return "editarModelos.jsf";
    } 
    
    public void actualizarModelo() throws IOException
    {
        AEditar = model;
        modelomovilFacade.edit(AEditar);
        AEditar = new Modelomovil();
        InicListaModelos();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminModelos.jsf");
    }
    
    public void crearModelo() throws IOException
    {
        modelomovilFacade.create(AAnadir);
        AAnadir = new Modelomovil();
        InicListaModelos();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminModelos.jsf");
    }
}
