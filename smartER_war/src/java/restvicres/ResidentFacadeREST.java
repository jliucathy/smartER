/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restvicres;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author ljx
 */
@Stateless
@Path("restvicres.resident")
public class ResidentFacadeREST extends AbstractFacade<Resident> {

    @PersistenceContext(unitName = "smartERPU")
    private EntityManager em;

    public ResidentFacadeREST() {
        super(Resident.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Resident entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Resident entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resident find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("findByFirstname/{firstname}")
    @Produces({"application/json"})
    public List<Resident> findByFirstname(@PathParam("firstname") String firstname) { 
        Query query=em.createNamedQuery("Resident.findByFirstname");
        query.setParameter("firstname",firstname);
        return query.getResultList();    
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces({"application/json"})
    public List<Resident> findBySurname(@PathParam("surname") String surname) { 
        Query query=em.createNamedQuery("Resident.findBySurname");
        query.setParameter("surname",surname);
        return query.getResultList();  
    }
    
    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
    public List<Resident> findByDob(@PathParam("dob") String dobstr) throws Exception {
        List<Resident> result = new ArrayList<Resident>();
        try {
        Date dob=new SimpleDateFormat("yyyy-mm-dd").parse(dobstr);   
        Query query=em.createNamedQuery("Resident.findByDob");
        query.setParameter("dob",dob);
        result.addAll(query.getResultList());
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Resident> findByAddress(@PathParam("address") String address){  
        Query query=em.createNamedQuery("Resident.findByAddress");
        query.setParameter("address",address);
        return query.getResultList();  
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Resident> findByPostcode(@PathParam("postcode") String postcode){  
        Query query=em.createNamedQuery("Resident.findByPostcode");
        query.setParameter("postcode",postcode);
        return query.getResultList(); 
    }
    
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<Resident> findByEmail(@PathParam("email") String email){  
        Query query=em.createNamedQuery("Resident.findByEmail");
        query.setParameter("email",email);
        return query.getResultList(); 
    }
   
    @GET
    @Path("findByMobile/{mobile}")
    @Produces({"application/json"})
    public List<Resident> findByMobile(@PathParam("mobile") String mobile){  
        Query query=em.createNamedQuery("Resident.findByMobile");
        query.setParameter("mobile",mobile);
        return query.getResultList(); 
    }
    
    @GET
    @Path("findByNoofresidents/{noofresidents}")
    @Produces({"application/json"})
    public List<Resident> findByNoofresidents(@PathParam("noofresidents") int noofresidents){  
        Query query=em.createNamedQuery("Resident.findByNoofresidents");
        query.setParameter("noofresidents",noofresidents);
        return query.getResultList(); 
    }
    
    @GET
    @Path("findByEnergyprovider/{energyprovider}")
    @Produces({"application/json"})
    public List<Resident> findByEnergyprovider(@PathParam("energyprovider") String energyprovider){  
        Query query=em.createNamedQuery("Resident.findByEnergyprovider");
        query.setParameter("energyprovider",energyprovider);
        return query.getResultList(); 
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("findByName/{firstname}/{surname}") 
    @Produces({"application/json"})
    public List<Resident> findByName(@PathParam("firstname") String firstname, 
            @PathParam("surname") String surname) 
    {
        TypedQuery<Resident> qu = em.createQuery
        ("SELECT r FROM Resident r WHERE r.firstname = :firstname and r.surname=:surname", Resident.class);
        qu.setParameter("firstname", firstname); 
        qu.setParameter("surname", surname);
        return qu.getResultList();
}
    
    
    
}
