/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tarifa;

import dao.OperadorFacade;
import dao.TarifaFacade;
import entidades.Operador;
import entidades.Tarifa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author IsmiKinPorti
 */
@ManagedBean(name = "apiTarifa")
@ApplicationScoped
public class tarifaManager {

    @EJB
    private OperadorFacade operadorFacade;
    @EJB
    private TarifaFacade tarifaFacade;

    public List<Tarifa> tarifas ;
    public Tarifa seleccionada;
    public List<SelectItem> operadoras;
    public List<Operador> todasoperadoras;
    public Tarifa toAdd;
    public String FK_Operador;
    
    
    public Tarifa getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Tarifa seleccionada) {
        this.seleccionada = seleccionada;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public List<SelectItem> getOperadoras() {
        return operadoras;
    }

    public void setOperadoras(List<SelectItem> operadoras) {
        this.operadoras = operadoras;
    }

    public Tarifa getToAdd() {
        return toAdd;
    }

    public void setToAdd(Tarifa toAdd) {
        this.toAdd = toAdd;
    }

    public String getFK_Operador() {
        return FK_Operador;
    }

    public void setFK_Operador(String FK_Operador) {
        this.FK_Operador = FK_Operador;
    }

    public List<Operador> getTodasoperadoras() {
        return todasoperadoras;
    }

    public void setTodasoperadoras(List<Operador> todasoperadoras) {
        this.todasoperadoras = todasoperadoras;
    }
    
    /**
     * Creates a new instance of tarifaInfo
     */
    public tarifaManager() {
        operadoras = new ArrayList<SelectItem>();
        this.toAdd=new Tarifa();
       
    }
    
    @PostConstruct
    public void initializando(){
        tarifas = tarifaFacade.findAll();
        //FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         this.todasoperadoras= operadorFacade.findAll();
         Iterator<Operador> it = todasoperadoras.iterator();
         operadoras.add(new SelectItem("null",""));
         while(it.hasNext()){
                Operador op = it.next();
                SelectItem opcion = new SelectItem(op.getIdOperador(),op.getNombre());                      
                operadoras.add(opcion);
         }
         
    }
    
    public void insertarTarifa() throws IOException{
        
        Operador operito;
        if( (this.FK_Operador!=null) && (!this.FK_Operador.equals("null")) ){
            operito = operadorFacade.find(Integer.parseInt(this.FK_Operador));
            toAdd.setOperadoridOperador(operito);            
        }
        
        if(this.toAdd.getTDCapacidad()!=null || Integer.parseInt(this.toAdd.getTDCapacidad())!=0)
            this.toAdd.setTarifaDatos("true");
        else
            this.toAdd.setTarifaDatos("false");
        
        tarifaFacade.create(toAdd);
        
        this.FK_Operador = null;    // No deberia hacer falta ya que el scope es de request
        toAdd = new Tarifa();         // No deberia hacer falta
        
        tarifas = this.tarifaFacade.findAll();
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminTarifa.jsf");
        //return "adminTarifjas.jsf";
    }
    
    public void borrarTarifa() throws IOException{
        
         tarifaFacade.remove(this.seleccionada);        
         tarifas = tarifaFacade.findAll();
       //  FacesContext.getCurrentInstance().getExternalContext().redirect("adminTarifa.jsf");
    }
    
    public void aEditarVamos() throws IOException {
        
        FK_Operador = null;
        
        if(seleccionada.getOperadoridOperador()!=null){
                 operadoras.clear();
        
                this.todasoperadoras= operadorFacade.findAll();   // No deberia hacer falta.. o.O
                Iterator<Operador> it = this.todasoperadoras.iterator();
             
                // Añadimos el que ya tiene seleccionado la tarifa elegida
                operadoras.add(new SelectItem(this.seleccionada.getOperadoridOperador().getIdOperador(),this.seleccionada.getOperadoridOperador().getNombre()));
                operadoras.add(new SelectItem("null",""));

                 while(it.hasNext()){
                        Operador op = it.next();
                        if(!op.equals(this.seleccionada.getOperadoridOperador())){  // Comprobamos para no volver a meter el mismo
                            SelectItem opcion = new SelectItem(op.getIdOperador(),op.getNombre());                      
                            operadoras.add(opcion);
                        }                
                 }

        }
                
        FacesContext.getCurrentInstance().getExternalContext().redirect("editTarifa.jsf");
    }
    
    public void editTarifa() throws IOException{
         if(!this.FK_Operador.equals("null"))
            seleccionada.setOperadoridOperador(this.operadorFacade.find(Integer.parseInt(FK_Operador)));
         else
             seleccionada.setOperadoridOperador(null);
         
         tarifaFacade.edit(seleccionada);
         this.tarifas.clear();
         this.tarifas  = this.tarifaFacade.findAll();
         FacesContext.getCurrentInstance().getExternalContext().redirect("adminTarifa.jsf");
    }
}
