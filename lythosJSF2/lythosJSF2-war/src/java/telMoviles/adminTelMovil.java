package telMoviles;


import dao.LineaFacade;
import dao.ModelofijoFacade;
import dao.ModelomovilFacade;
import dao.TerminalmovilFacade;
import dao.UsuarioFacade;
import entidades.Linea;
import entidades.Modelomovil;
import entidades.Terminalmovil;
import entidades.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name ="todosTelMovil")
@ApplicationScoped
public class adminTelMovil {
    @EJB
    private ModelomovilFacade modelomovilFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private LineaFacade lineaFacade;
    @EJB
    private TerminalmovilFacade movilfacade;
    
    private List<Terminalmovil> todosMoviles;    
    private Terminalmovil  Movilseleccionado;
    private Terminalmovil Crear;
    private Terminalmovil aEditar;

    
    private String FK_Linea;
    private String FK_Usuario;
    private String FK_ModeloMovil;

    private List<SelectItem> conjLineas;
    private List<SelectItem> conjUsuarios;
    private List<SelectItem> conjModeloMovil;
    
    private List<Linea> todasLineas;
    private List<Usuario> todosUsuarios;
    private List<Modelomovil> todosModelosMovil;
    
    
    public adminTelMovil() {
        
        this.Crear = new Terminalmovil();
        this.aEditar=new Terminalmovil();
        conjLineas = new ArrayList<SelectItem>();
        conjUsuarios = new ArrayList<SelectItem>();
        conjModeloMovil = new ArrayList<SelectItem>();
        
    }
    
    
    public Terminalmovil getAEditar() {
        return aEditar;
    }

    public void setAEditar(Terminalmovil aEditar) {
        this.aEditar = aEditar;
    }

    public TerminalmovilFacade getMovilfacade() {
        return movilfacade;
    }

    public void setMovilfacade(TerminalmovilFacade movilfacade) {
        this.movilfacade = movilfacade;
    }

    public List<Terminalmovil> getTodosMoviles() {
        return todosMoviles;
    }

    public void setTodosMoviles(List<Terminalmovil> todosMoviles) {
        this.todosMoviles = todosMoviles;
    }

    public Terminalmovil getMovilseleccionado() {
        return Movilseleccionado;
    }

    public void setMovilseleccionado(Terminalmovil Movilseleccionado) {
        this.Movilseleccionado = Movilseleccionado;
    }


    public Terminalmovil getCrear() {
        return Crear;
    }

    public void setCrear(Terminalmovil Crear) {
        this.Crear = Crear;
    }


    public List<SelectItem> getConjLineas() {
        return conjLineas;
    }

    public void setConjLineas(List<SelectItem> conjLineas) {
        this.conjLineas = conjLineas;
    }

    public String getFK_Linea() {
        return FK_Linea;
    }

    public void setFK_Linea(String FK_Linea) {
        this.FK_Linea = FK_Linea;
    }

    public List<SelectItem> getConjUsuarios() {
        return conjUsuarios;
    }

    public void setConjUsuarios(List<SelectItem> conjUsuarios) {
        this.conjUsuarios = conjUsuarios;
    }

    public String getFK_Usuario() {
        return FK_Usuario;
    }

    public void setFK_Usuario(String FK_Usuario) {
        this.FK_Usuario = FK_Usuario;
    }

    public List<SelectItem> getConjModeloMovil() {
        return conjModeloMovil;
    }

    public void setConjModeloMovil(List<SelectItem> conjModeloMovil) {
        this.conjModeloMovil = conjModeloMovil;
    }

    public String getFK_ModeloMovil() {
        return FK_ModeloMovil;
    }

    public void setFK_ModeloMovil(String FK_ModeloMovil) {
        this.FK_ModeloMovil = FK_ModeloMovil;
    }

  

   
    @PostConstruct
    
   public void Inic(){
        todosMoviles = movilfacade.findAll();
        todasLineas = lineaFacade.findAll();
        Iterator<Linea> it = todasLineas.iterator();
        conjLineas.add(new SelectItem("null",""));
        while(it.hasNext()) {
            Linea temp = it.next();
        SelectItem opciones = new SelectItem(temp.getIdLinea(),temp.getNumero());
        conjLineas.add(opciones);
        }
        
        todosUsuarios = usuarioFacade.findAll();
        Iterator<Usuario> it2 = todosUsuarios.iterator();
        conjUsuarios.add(new SelectItem("null",""));
        while(it2.hasNext()){
            Usuario temp2 = it2.next();
            SelectItem opciones = new SelectItem(temp2.getIdUsuario(),temp2.getNombre());
            conjUsuarios.add(opciones);
        }
        
        todosModelosMovil = this.modelomovilFacade.findAll();
        Iterator<Modelomovil> it3 = todosModelosMovil.iterator();
        conjModeloMovil.add(new SelectItem("null",""));
        while(it3.hasNext()){
            Modelomovil temp3 = it3.next();
            SelectItem opciones = new SelectItem(temp3.getIdModeloMovil(),temp3.getModelo());
            this.conjModeloMovil.add(opciones);
        }
        
    }
   public void crearTerminalmovil() throws IOException{  
        if(!FK_Linea.equalsIgnoreCase("null"))
            Crear.setLineaidLinea(lineaFacade.find(Integer.parseInt(FK_Linea)));
        if(!FK_Usuario.equals("null"))
             Crear.setUsuarioidUsuario(usuarioFacade.find(Integer.parseInt(FK_Usuario)));
        if(!FK_ModeloMovil.equals("null"))
            Crear.setModeloMovilidModeloMovil(modelomovilFacade.find(Integer.parseInt(FK_ModeloMovil)));
        //Crear.setIdTerminalMovil(Integer.getInteger("654"));
        movilfacade.create(Crear); 
        this.Crear = new Terminalmovil();
        todosMoviles = this.movilfacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminTelMovil.jsf");
        
    
   }
   
   
    
    public void deleteMoviles(){
       movilfacade.remove(Movilseleccionado);
       this.todosMoviles = this.movilfacade.findAll();
    }
    
    public String editar(){
//          if(!this.Movilseleccionado.getLineaidLinea().equals("null"))
//             
//          this.conjLineas.get(1);
//        if(!this.Movilseleccionado.getUsuarioidUsuario().getNombre().equals("null"))
//        FK_Usuario = this.Movilseleccionado.getUsuarioidUsuario().getNombre();
//        if(!this.Movilseleccionado.getUsuarioidUsuario().getNombre().equals("null"))
//        FK_ModeloMovil = this.Movilseleccionado.getUsuarioidUsuario().getNombre();
//         FacesContext.getCurrentInstance().getExternalContext().redirect("editarTelMovil.jsf");        
        return "editarTelMovil.jsf";
    }
    
    public void actualizar() throws IOException{
        aEditar = this.Movilseleccionado;
        if(!FK_Linea.equalsIgnoreCase("null")){
             aEditar.setLineaidLinea(lineaFacade.find(Integer.parseInt(FK_Linea)));
        }else{
            aEditar.setLineaidLinea(null);
        }
        if(!FK_Usuario.equals("null")){
             aEditar.setUsuarioidUsuario(usuarioFacade.find(Integer.parseInt(FK_Usuario)));
        }else{
            aEditar.setUsuarioidUsuario(null);
        }
        if(!FK_ModeloMovil.equals("null")){
            aEditar.setModeloMovilidModeloMovil(modelomovilFacade.find(Integer.parseInt(FK_ModeloMovil)));
        }else{
            aEditar.setModeloMovilidModeloMovil(null);
        }
        //Crear.setIdTerminalMovil(Integer.getInteger("654"));
        
        this.movilfacade.edit(aEditar);
        todosMoviles = this.movilfacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminTelMovil.jsf");
    }
    
    
    
}
