package es.iespuertodelacruz.ricardo.matriculasJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Usuario;

public class UsuarioRepository implements JPACRUD<Usuario, Integer>{

	private EntityManagerFactory emf;

	public UsuarioRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Usuario> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Usuario> lista = em.createNamedQuery("Usuario.findAll", Usuario.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Usuario findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		em.getTransaction().commit();
		em.close();
		return usuario;
	}


	public Usuario findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuario = em.createNamedQuery("Usuario.findByName", Usuario.class)
			.setParameter("name", nombre)
			.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return usuario;
	}	
	
	
	@Override
	public Usuario save(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
