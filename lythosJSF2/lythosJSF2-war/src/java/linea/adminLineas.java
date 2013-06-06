/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linea;

import dao.LineaFacade;
import dao.TarifaFacade;
import entidades.Linea;
import entidades.Tarifa;
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

/**
 *
 * @author Rocio
 */
@ManagedBean(name="adminLineas")
@ApplicationScoped
public class adminLineas {
    @EJB
    private TarifaFacade tarifaFacade;
    @EJB
    private LineaFacade lineaFacade;

    private Linea lineaSeleccionada; 
    private List<Linea> todasLineas;
    private List<Tarifa> todasTarifas;
    
    private List<SelectItem> conjTarifas;
    private Linea ACrear;
    private Linea AEditar;
    private String FK_Tarifa;
    
    
    public adminLineas() {
        
        this.ACrear = new Linea();
        this.AEditar = new Linea();
        conjTarifas = new ArrayList<SelectItem>();
        
    }

    public Linea getAEditar() {
        return AEditar;
    }

    public void setAEditar(Linea AEditar) {
        this.AEditar = AEditar;
    }

    public TarifaFacade getTarifaFacade() {
        return tarifaFacade;
    }

    public String getFK_Tarifa() {
        return FK_Tarifa;
    }

    public void setFK_Tarifa(String FK_Tarifa) {
        this.FK_Tarifa = FK_Tarifa;
    }

   

    public List<SelectItem> getConjTarifas() {
        return conjTarifas;
    }

    public void setConjTarifas(List<SelectItem> conjTarifas) {
        this.conjTarifas = conjTarifas;
    }

    public void setTarifaFacade(TarifaFacade tarifaFacade) {
        this.tarifaFacade = tarifaFacade;
    }

    public LineaFacade getLineaFacade() {
        return lineaFacade;
    }

    public void setLineaFacade(LineaFacade lineaFacade) {
        this.lineaFacade = lineaFacade;
    }

    public Linea getACrear() {
        return ACrear;
    }

    public void setACrear(Linea ACrear) {
        this.ACrear = ACrear;
    }

   

    public Linea getLineaSeleccionada() {
        return lineaSeleccionada;
    }

    public void setLineaSeleccionada(Linea lineaSeleccionada) {
        this.lineaSeleccionada = lineaSeleccionada;
    }

    public List<Linea> getTodasLineas() {
        return todasLineas;
    }

    public void setTodasLineas(List<Linea> todasLineas) {
        this.todasLineas = todasLineas;
    }

    public List<Tarifa> getTodasTarifas() {
        return todasTarifas;
    }

    public void setTodasTarifas(List<Tarifa> todasTarifas) {
        this.todasTarifas = todasTarifas;
    }
  

    
     @PostConstruct
     public void Init(){
         todasLineas = this.lineaFacade.findAll();
         todasTarifas = this.tarifaFacade.findAll();
         
         Iterator<Tarifa> it = this.todasTarifas.iterator();
         this.conjTarifas.add(new SelectItem("null",""));
         while(it.hasNext()){
             Tarifa tarifita = it.next();
             SelectItem opciones = new SelectItem(tarifita.getIdTarifa(),tarifita.getNombre());
             conjTarifas.add(opciones);
         }
         
     }
     
     public void crearLinea() throws IOException{
         if(!this.FK_Tarifa.equalsIgnoreCase("null")){
             ACrear.setTarifaidTarifa(tarifaFacade.find(Integer.parseInt(FK_Tarifa)));
         }else{
             ACrear.setTarifaidTarifa(null);
         }             
         
         lineaFacade.create(ACrear);
         this.ACrear=new Linea();
         this.todasLineas = this.lineaFacade.findAll();
         FacesContext.getCurrentInstance().getExternalContext().redirect("adminLineas.jsf");
         
     }
     
     
     public String editarLinea(){
         return "editarLinea.jsf";
     }
    public void actualizarLinea() throws IOException{
        this.AEditar = this.lineaSeleccionada;
        
        if(!this.FK_Tarifa.equalsIgnoreCase("null")){
            AEditar.setTarifaidTarifa(this.tarifaFacade.find(Integer.parseInt(FK_Tarifa)));
        }else{
            AEditar.setTarifaidTarifa(null);
        }
        
        this.lineaFacade.edit(AEditar);
//        this.todasLineas = this.lineaFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminLineas.jsf");
        
    }
    
    public void eliminarLinea(){
        this.lineaFacade.remove(lineaSeleccionada);
        this.todasLineas = this.lineaFacade.findAll();
        
    }

   
}
