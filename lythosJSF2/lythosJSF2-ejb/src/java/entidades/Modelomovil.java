/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IsmiKinPorti
 */
@Entity
@Table(name = "modelomovil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelomovil.findAll", query = "SELECT m FROM Modelomovil m"),
    @NamedQuery(name = "Modelomovil.findByIdModeloMovil", query = "SELECT m FROM Modelomovil m WHERE m.idModeloMovil = :idModeloMovil"),
    @NamedQuery(name = "Modelomovil.findByMarca", query = "SELECT m FROM Modelomovil m WHERE m.marca = :marca"),
    @NamedQuery(name = "Modelomovil.findByModelo", query = "SELECT m FROM Modelomovil m WHERE m.modelo = :modelo"),
    @NamedQuery(name = "Modelomovil.findByLanzamiento", query = "SELECT m FROM Modelomovil m WHERE m.lanzamiento = :lanzamiento"),
    @NamedQuery(name = "Modelomovil.findByImagen", query = "SELECT m FROM Modelomovil m WHERE m.imagen = :imagen"),
    @NamedQuery(name = "Modelomovil.findByDetalles", query = "SELECT m FROM Modelomovil m WHERE m.detalles = :detalles")})
public class Modelomovil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModeloMovil")
    private Integer idModeloMovil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Modelo")
    private String modelo;
    @Column(name = "Lanzamiento")
    @Temporal(TemporalType.DATE)
    private Date lanzamiento;
    @Size(max = 200)
    @Column(name = "Imagen")
    private String imagen;
    @Size(max = 400)
    @Column(name = "Detalles")
    private String detalles;
    @OneToMany(mappedBy = "modeloMovilidModeloMovil")
    private Collection<Terminalmovil> terminalmovilCollection;

    public Modelomovil() {
    }

    public Modelomovil(Integer idModeloMovil) {
        this.idModeloMovil = idModeloMovil;
    }

    public Modelomovil(Integer idModeloMovil, String marca, String modelo) {
        this.idModeloMovil = idModeloMovil;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Integer getIdModeloMovil() {
        return idModeloMovil;
    }

    public void setIdModeloMovil(Integer idModeloMovil) {
        this.idModeloMovil = idModeloMovil;
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

    public Date getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Date lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @XmlTransient
    public Collection<Terminalmovil> getTerminalmovilCollection() {
        return terminalmovilCollection;
    }

    public void setTerminalmovilCollection(Collection<Terminalmovil> terminalmovilCollection) {
        this.terminalmovilCollection = terminalmovilCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModeloMovil != null ? idModeloMovil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelomovil)) {
            return false;
        }
        Modelomovil other = (Modelomovil) object;
        if ((this.idModeloMovil == null && other.idModeloMovil != null) || (this.idModeloMovil != null && !this.idModeloMovil.equals(other.idModeloMovil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Modelomovil[ idModeloMovil=" + idModeloMovil + " ]";
    }
    
}
