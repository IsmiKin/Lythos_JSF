/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Autorizacion;
import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author IsmiKinPorti
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "lythosJSF2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
      // Metodos propios
    
    public Usuario getUserByAuto(Autorizacion autori){
        
        Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.autorizacionidAutorizacion = :idUser ").setParameter("idUser", autori);
        Usuario resultado = (Usuario) consulta.getSingleResult();        
        return resultado;
       
    }
    
}
