package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;

public class AlumnoRepository implements JPACRUD<Alumno,String>{
	
	private EntityManagerFactory emf;
	
	public AlumnoRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Alumno> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Alumno> lista = em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Alumno findById(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Alumno alumno = em.find(Alumno.class, id);
		em.getTransaction().commit();
		em.close();
		return alumno;
	}
	/*
	public List<Matricula> findbyDni(String dni){
		List<Matricula> matriculas = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		String query = "SELECT a FROM Alumno a RIGHT JOIN Matricula m  on m.alumno.dni = :dni";
		matriculas = em.createQuery(query, Matricula.class)
				.setParameter("dni", dni)
				.getResultList();
		tr.commit();
		em.close();
		return matriculas;
	}
	*/
	@Override
	public Alumno save(Alumno obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno update(Alumno obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
