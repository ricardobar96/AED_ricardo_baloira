package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
		int idBuscar = Integer.valueOf(id);
		Asignatura asignatura = em.find(Asignatura.class, idBuscar);
		em.getTransaction().commit();
		em.close();
		return asignatura;
	}
	/*
	public List<Asignatura> findByIdMatricula(String id) {
		List<Asignatura> asignaturas = null;
		int idmatricula = Integer.valueOf(id);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		String query = "SELECT a FROM Asignatura a WHERE a.idmatricula = idmatricula";
		//String query = "SELECT a FROM Asignatura a RIGHT JOIN Matricula m on a.idasignatura = :idmatricula";
		//String query = "SELECT a FROM Asignatura a WHERE a.idasignatura = :idmatricula";
		asignaturas = em.createQuery(query, Asignatura.class)
				.setParameter("idmatricula", idmatricula)
				.getResultList();
		tr.commit();
		em.close();
		return asignaturas;
	}
	*/
	@Override
	public Asignatura save(Asignatura obj) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		em.persist(obj);
		tr.begin();
		tr.commit();
		em.close();
		return obj;
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
