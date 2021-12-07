package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Asignatura;
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
	
	public List<Matricula> findByDni(String dni) {
		List<Matricula> matriculas = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		String query = "SELECT m FROM Matricula m WHERE m.alumno.dni = :dni";
		matriculas = em.createQuery(query, Matricula.class)
				.setParameter("dni", dni)
				.getResultList();
		tr.commit();
		em.close();
		return matriculas;
	}

	public List<Matricula> findbyJoin(String dni){
		List<Matricula> matriculas = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		String query = "SELECT DISTINCT m FROM Matricula m JOIN Alumno a on m.alumno.dni = :dni";
		matriculas = em.createQuery(query, Matricula.class)
				.setParameter("dni", dni)
				.getResultList();
		tr.commit();
		em.close();
		return matriculas;
	}
	
	@Override
	public Matricula save(Matricula obj) {
		AsignaturaRepository asignaturaRepository = new AsignaturaRepository(emf);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		for(Asignatura asignatura: obj.getAsignaturas()) {
			asignatura.getMatriculas().add(obj);
			}	
		
		em.merge(obj);
		tr.commit();
		em.close();
		return obj;
	}

	@Override
	public Matricula update(Matricula obj) {
		EntityManager em = emf.createEntityManager();
		MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
		Matricula existente = matriculaRepository.findById(String.valueOf(obj.getIdmatricula()));
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		for(Asignatura asignaturaE: existente.getAsignaturas()) {
			asignaturaE.getMatriculas().remove(existente);
			em.merge(asignaturaE);
			}
		
		for(Asignatura asignatura: obj.getAsignaturas()) {
			asignatura.getMatriculas().add(obj);
			}	
		
		em.merge(obj);

		tr.commit();
		em.close();
		
		return obj;
	}

	@Override
	public boolean delete(String id) {
		EntityManager em = emf.createEntityManager();
		MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
		Matricula borrar = matriculaRepository.findById(id);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		em.remove(em.merge(borrar));
		
		for(Asignatura asignatura: borrar.getAsignaturas()) {
			asignatura.getMatriculas().remove(borrar);
			em.merge(asignatura);
			//em.remove(em.merge(asignatura));
			}	

		tr.commit();
		em.close();		
		return false;
	}

}