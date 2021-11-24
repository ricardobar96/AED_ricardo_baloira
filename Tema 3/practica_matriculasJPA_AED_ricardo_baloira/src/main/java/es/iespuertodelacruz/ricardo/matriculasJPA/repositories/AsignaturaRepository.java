package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Asignatura;

public class AsignaturaRepository implements JPACRUD<Asignatura,String>{

	private EntityManagerFactory emf;
	
	public AsignaturaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Asignatura> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Asignatura> lista = em.createNamedQuery("Asignatura.findAll", Asignatura.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Asignatura findById(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Asignatura asignatura = em.find(Asignatura.class, id);
		em.getTransaction().commit();
		em.close();
		return asignatura;
	}

	@Override
	public Asignatura save(Asignatura obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignatura update(Asignatura obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
