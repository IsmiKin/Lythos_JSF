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
@Table(name = "modelofijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelofijo.findAll", query = "SELECT m FROM Modelofijo m"),
    @NamedQuery(name = "Modelofijo.findByIdModeloFijo", query = "SELECT m FROM Modelofijo m WHERE m.idModeloFijo = :idModeloFijo"),
    @NamedQuery(name = "Modelofijo.findByMarca", query = "SELECT m FROM Modelofijo m WHERE m.marca = :marca"),
    @NamedQuery(name = "Modelofijo.findByModelo", query = "SELECT m FROM Modelofijo m WHERE m.modelo = :modelo"),
    @NamedQuery(name = "Modelofijo.findByLanzamiento", query = "SELECT m FROM Modelofijo m WHERE m.lanzamiento = :lanzamiento"),
    @NamedQuery(name = "Modelofijo.findByImagen", query = "SELECT m FROM Modelofijo m WHERE m.imagen = :imagen"),
    @NamedQuery(name = "Modelofijo.findByDetalles", query = "SELECT m FROM Modelofijo m WHERE m.detalles = :detalles")})
public class Modelofijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModeloFijo")
    private Integer idModeloFijo;
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
    @OneToMany(mappedBy = "modeloFijoidModeloFijo")
    private Collection<Terminalfijo> terminalfijoCollection;

    public Modelofijo() {
    }

    public Modelofijo(Integer idModeloFijo) {
        this.idModeloFijo = idModeloFijo;
    }

    public Modelofijo(Integer idModeloFijo, String marca, String modelo) {
        this.idModeloFijo = idModeloFijo;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Integer getIdModeloFijo() {
        return idModeloFijo;
    }

    public void setIdModeloFijo(Integer idModeloFijo) {
        this.idModeloFijo = idModeloFijo;
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
    public Collection<Terminalfijo> getTerminalfijoCollection() {
        return terminalfijoCollection;
    }

    public void setTerminalfijoCollection(Collection<Terminalfijo> terminalfijoCollection) {
        this.terminalfijoCollection = terminalfijoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModeloFijo != null ? idModeloFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelofijo)) {
            return false;
        }
        Modelofijo other = (Modelofijo) object;
        if ((this.idModeloFijo == null && other.idModeloFijo != null) || (this.idModeloFijo != null && !this.idModeloFijo.equals(other.idModeloFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Modelofijo[ idModeloFijo=" + idModeloFijo + " ]";
    }
    
}
