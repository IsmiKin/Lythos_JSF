/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IsmiKinPorti
 */
@Entity
@Table(name = "terminalfijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminalfijo.findAll", query = "SELECT t FROM Terminalfijo t"),
    @NamedQuery(name = "Terminalfijo.findByIdTerminalFijo", query = "SELECT t FROM Terminalfijo t WHERE t.idTerminalFijo = :idTerminalFijo"),
    @NamedQuery(name = "Terminalfijo.findByCodigo", query = "SELECT t FROM Terminalfijo t WHERE t.codigo = :codigo")})
public class Terminalfijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTerminalFijo")
    private Integer idTerminalFijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Codigo")
    private String codigo;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuarioidUsuario;
    @JoinColumn(name = "ModeloFijo_idModeloFijo", referencedColumnName = "idModeloFijo")
    @ManyToOne
    private Modelofijo modeloFijoidModeloFijo;
    @JoinColumn(name = "Linea_idLinea", referencedColumnName = "idLinea")
    @ManyToOne
    private Linea lineaidLinea;

    public Terminalfijo() {
    }

    public Terminalfijo(Integer idTerminalFijo) {
        this.idTerminalFijo = idTerminalFijo;
    }

    public Terminalfijo(Integer idTerminalFijo, String codigo) {
        this.idTerminalFijo = idTerminalFijo;
        this.codigo = codigo;
    }

    public Integer getIdTerminalFijo() {
        return idTerminalFijo;
    }

    public void setIdTerminalFijo(Integer idTerminalFijo) {
        this.idTerminalFijo = idTerminalFijo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public Modelofijo getModeloFijoidModeloFijo() {
        return modeloFijoidModeloFijo;
    }

    public void setModeloFijoidModeloFijo(Modelofijo modeloFijoidModeloFijo) {
        this.modeloFijoidModeloFijo = modeloFijoidModeloFijo;
    }

    public Linea getLineaidLinea() {
        return lineaidLinea;
    }

    public void setLineaidLinea(Linea lineaidLinea) {
        this.lineaidLinea = lineaidLinea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTerminalFijo != null ? idTerminalFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminalfijo)) {
            return false;
        }
        Terminalfijo other = (Terminalfijo) object;
        if ((this.idTerminalFijo == null && other.idTerminalFijo != null) || (this.idTerminalFijo != null && !this.idTerminalFijo.equals(other.idTerminalFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Terminalfijo[ idTerminalFijo=" + idTerminalFijo + " ]";
    }
    
}
