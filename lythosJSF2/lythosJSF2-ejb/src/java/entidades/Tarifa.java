/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IsmiKinPorti
 */
@Entity
@Table(name = "tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifa.findAll", query = "SELECT t FROM Tarifa t"),
    @NamedQuery(name = "Tarifa.findByIdTarifa", query = "SELECT t FROM Tarifa t WHERE t.idTarifa = :idTarifa"),
    @NamedQuery(name = "Tarifa.findByNombre", query = "SELECT t FROM Tarifa t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tarifa.findByDescripcion", query = "SELECT t FROM Tarifa t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tarifa.findByTarifaDatos", query = "SELECT t FROM Tarifa t WHERE t.tarifaDatos = :tarifaDatos"),
    @NamedQuery(name = "Tarifa.findByLlamadaOperadora", query = "SELECT t FROM Tarifa t WHERE t.llamadaOperadora = :llamadaOperadora"),
    @NamedQuery(name = "Tarifa.findByLlamadaFuera", query = "SELECT t FROM Tarifa t WHERE t.llamadaFuera = :llamadaFuera"),
    @NamedQuery(name = "Tarifa.findByTDCapacidad", query = "SELECT t FROM Tarifa t WHERE t.tDCapacidad = :tDCapacidad"),
    @NamedQuery(name = "Tarifa.findBySms", query = "SELECT t FROM Tarifa t WHERE t.sms = :sms")})
public class Tarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private Integer idTarifa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TarifaDatos")
    private String tarifaDatos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LlamadaOperadora")
    private String llamadaOperadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LlamadaFuera")
    private String llamadaFuera;
    @Size(max = 45)
    @Column(name = "TDCapacidad")
    private String tDCapacidad;
    @Size(max = 45)
    @Column(name = "SMS")
    private String sms;
    @OneToMany(mappedBy = "tarifaidTarifa")
    private Collection<Linea> lineaCollection;
    @JoinColumn(name = "Operador_idOperador", referencedColumnName = "idOperador")
    @ManyToOne
    private Operador operadoridOperador;

    public Tarifa() {
    }

    public Tarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Tarifa(Integer idTarifa, String nombre, String descripcion, String tarifaDatos, String llamadaOperadora, String llamadaFuera) {
        this.idTarifa = idTarifa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaDatos = tarifaDatos;
        this.llamadaOperadora = llamadaOperadora;
        this.llamadaFuera = llamadaFuera;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTarifaDatos() {
        return tarifaDatos;
    }

    public void setTarifaDatos(String tarifaDatos) {
        this.tarifaDatos = tarifaDatos;
    }

    public String getLlamadaOperadora() {
        return llamadaOperadora;
    }

    public void setLlamadaOperadora(String llamadaOperadora) {
        this.llamadaOperadora = llamadaOperadora;
    }

    public String getLlamadaFuera() {
        return llamadaFuera;
    }

    public void setLlamadaFuera(String llamadaFuera) {
        this.llamadaFuera = llamadaFuera;
    }

    public String getTDCapacidad() {
        return tDCapacidad;
    }

    public void setTDCapacidad(String tDCapacidad) {
        this.tDCapacidad = tDCapacidad;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection() {
        return lineaCollection;
    }

    public void setLineaCollection(Collection<Linea> lineaCollection) {
        this.lineaCollection = lineaCollection;
    }

    public Operador getOperadoridOperador() {
        return operadoridOperador;
    }

    public void setOperadoridOperador(Operador operadoridOperador) {
        this.operadoridOperador = operadoridOperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarifa != null ? idTarifa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifa)) {
            return false;
        }
        Tarifa other = (Tarifa) object;
        if ((this.idTarifa == null && other.idTarifa != null) || (this.idTarifa != null && !this.idTarifa.equals(other.idTarifa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tarifa[ idTarifa=" + idTarifa + " ]";
    }
    
}
