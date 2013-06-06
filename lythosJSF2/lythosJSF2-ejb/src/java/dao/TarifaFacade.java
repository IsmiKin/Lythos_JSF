/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Tarifa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author IsmiKinPorti
 */
@Stateless
public class TarifaFacade extends AbstractFacade<Tarifa> {
    @PersistenceContext(unitName = "lythosJSF2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarifaFacade() {
        super(Tarifa.class);
    }
    
    public List<Tarifa> findLessSMS(){
        
        Query consulta = em.createQuery("SELECT t FROM Tarifa t WHERE t.operadoridOperador.idOperador < :numero").setParameter("numero", 5);
        List<Tarifa> resultado = consulta.getResultList();
        return resultado;
        
    }
    
}
