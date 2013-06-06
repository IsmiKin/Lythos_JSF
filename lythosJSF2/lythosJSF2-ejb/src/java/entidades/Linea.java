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
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findByIdLinea", query = "SELECT l FROM Linea l WHERE l.idLinea = :idLinea"),
    @NamedQuery(name = "Linea.findByNumero", query = "SELECT l FROM Linea l WHERE l.numero = :numero"),
    @NamedQuery(name = "Linea.findByTipo", query = "SELECT l FROM Linea l WHERE l.tipo = :tipo"),
    @NamedQuery(name = "Linea.findByVoIP", query = "SELECT l FROM Linea l WHERE l.voIP = :voIP"),
    @NamedQuery(name = "Linea.findByPais", query = "SELECT l FROM Linea l WHERE l.pais = :pais")})
public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLinea")
    private Integer idLinea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "VoIP")
    private String voIP;
    @Size(max = 45)
    @Column(name = "Pais")
    private String pais;
    @OneToMany(mappedBy = "lineaidLinea")
    private Collection<Terminalmovil> terminalmovilCollection;
    @JoinColumn(name = "Tarifa_idTarifa", referencedColumnName = "idTarifa")
    @ManyToOne
    private Tarifa tarifaidTarifa;
    @OneToMany(mappedBy = "lineaidLinea")
    private Collection<Terminalfijo> terminalfijoCollection;

    public Linea() {
    }

    public Linea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public Linea(Integer idLinea, String numero, String tipo) {
        this.idLinea = idLinea;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVoIP() {
        return voIP;
    }

    public void setVoIP(String voIP) {
        this.voIP = voIP;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @XmlTransient
    public Collection<Terminalmovil> getTerminalmovilCollection() {
        return terminalmovilCollection;
    }

    public void setTerminalmovilCollection(Collection<Terminalmovil> terminalmovilCollection) {
        this.terminalmovilCollection = terminalmovilCollection;
    }

    public Tarifa getTarifaidTarifa() {
        return tarifaidTarifa;
    }

    public void setTarifaidTarifa(Tarifa tarifaidTarifa) {
        this.tarifaidTarifa = tarifaidTarifa;
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
        hash += (idLinea != null ? idLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.idLinea == null && other.idLinea != null) || (this.idLinea != null && !this.idLinea.equals(other.idLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Linea[ idLinea=" + idLinea + " ]";
    }
    
}
