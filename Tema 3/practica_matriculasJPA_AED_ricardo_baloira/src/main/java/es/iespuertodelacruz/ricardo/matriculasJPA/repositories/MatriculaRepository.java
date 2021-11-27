package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;

public class MatriculaRepository implements JPACRUD<Matricula,String>{

	private EntityManagerFactory emf;
	
	public MatriculaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Matricula> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Matricula> lista = em.createNamedQuery("Matricula.findAll", Matricula.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Matricula findById(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		int idBuscar = Integer.valueOf(id);
		Matricula matricula = em.find(Matricula.class, idBuscar);
		em.getTransaction().commit();
		em.close();
		return matricula;
	}

	@Override
	public Matricula save(Matricula obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matricula update(Matricula obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
