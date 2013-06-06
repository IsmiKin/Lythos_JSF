/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Terminalmovil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IsmiKinPorti
 */
@Stateless
public class TerminalmovilFacade extends AbstractFacade<Terminalmovil> {
    @PersistenceContext(unitName = "lythosJSF2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerminalmovilFacade() {
        super(Terminalmovil.class);
    }
    
}
