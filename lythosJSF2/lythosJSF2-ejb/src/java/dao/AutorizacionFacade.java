/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Autorizacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author IsmiKinPorti
 */
@Stateless
public class AutorizacionFacade extends AbstractFacade<Autorizacion> {
    @PersistenceContext(unitName = "lythosJSF2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorizacionFacade() {
        super(Autorizacion.class);
    }
    
     // Metodos propios
    
    public boolean checkLogin(String nickname, String pass){
        Query consulta = em.createQuery("SELECT a FROM Autorizacion a WHERE a.nickname = :Nickname AND a.password = :Pass").setParameter("Nickname", nickname).setParameter("Pass", pass);
        //SELECT s from Computers s where s.id.column1 = :column1").setParameter("column1", "SONY LAPTOPS");
        int checking = consulta.getResultList().size();     // Comprobamos si concuerda password y nickname, si es 0 es que casca
        return checking==1;
    }
    
    public int getIdByNickname(String nickname){
        Query consulta = em.createQuery("SELECT a FROM Autorizacion a WHERE a.nickname = :nickname ").setParameter("nickname", nickname);
        Autorizacion resultado = (Autorizacion)consulta.getSingleResult();
        int salida = -1;
        if(resultado!=null)
            salida = resultado.getIdAutorizacion();
        return salida;
    }
    
    public Autorizacion getByNickname(String nickname){
        Autorizacion salida = (Autorizacion) em.createNamedQuery("Autorizacion.findByNickname").setParameter("nickname", nickname).getSingleResult();
        return salida;
    }
    
}
