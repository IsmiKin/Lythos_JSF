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
@Table(name = "gruporescate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruporescate.findAll", query = "SELECT g FROM Gruporescate g"),
    @NamedQuery(name = "Gruporescate.findByIdGrupoRescate", query = "SELECT g FROM Gruporescate g WHERE g.idGrupoRescate = :idGrupoRescate"),
    @NamedQuery(name = "Gruporescate.findByNombre", query = "SELECT g FROM Gruporescate g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "Gruporescate.findByDescripcion", query = "SELECT g FROM Gruporescate g WHERE g.descripcion = :descripcion")})
public class Gruporescate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoRescate")
    private Integer idGrupoRescate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "grupoRescateidGrupoRescate")
    private Collection<Usuario> usuarioCollection;

    public Gruporescate() {
    }

    public Gruporescate(Integer idGrupoRescate) {
        this.idGrupoRescate = idGrupoRescate;
    }

    public Gruporescate(Integer idGrupoRescate, String nombre) {
        this.idGrupoRescate = idGrupoRescate;
        this.nombre = nombre;
    }

    public Integer getIdGrupoRescate() {
        return idGrupoRescate;
    }

    public void setIdGrupoRescate(Integer idGrupoRescate) {
        this.idGrupoRescate = idGrupoRescate;
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

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoRescate != null ? idGrupoRescate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruporescate)) {
            return false;
        }
        Gruporescate other = (Gruporescate) object;
        if ((this.idGrupoRescate == null && other.idGrupoRescate != null) || (this.idGrupoRescate != null && !this.idGrupoRescate.equals(other.idGrupoRescate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Gruporescate[ idGrupoRescate=" + idGrupoRescate + " ]";
    }
    
}
