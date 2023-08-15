package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Pessoa;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pessoas() {

	}

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Pessoa porId(int id) {
		return manager.find(Pessoa.class, id);
	}
	
	public List<Pessoa> findAll() {
		return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> todas() {
		return manager.createQuery("from Pessoa ORDER BY id", Pessoa.class).getResultList();
	}

	public Pessoa guardar(Pessoa pessoa) {
		return manager.merge(pessoa);
	}

	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	public int contar() {
		String jpql = "SELECT e FROM Pessoa e ORDER BY e.id DESC";
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);
		query.setMaxResults(1);
		Pessoa ultimoObjeto = query.getSingleResult();
		int id = ultimoObjeto.getId() + 1;
		return id;
	}
	
	public List<Pessoa> executarConsulta(String jpql, String termoPesquisa, String termoCidade, String termoCargo, String termoEmail, String termoUsuario) {
		
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);
	    
	    if (termoPesquisa != null && !termoPesquisa.isEmpty()) {
	        query.setParameter("nome", "%" + termoPesquisa + "%");
	    }

	    if (termoCidade != null && !termoCidade.isEmpty()) {
	        query.setParameter("cidade", "%" + termoCidade + "%");
	    }

	    if (termoCargo != null && !termoCargo.isEmpty()) {
	        query.setParameter("nomeCargo", "%" + termoCargo + "%");
	    }
	    
	    if (termoEmail != null && !termoEmail.isEmpty()) {
	        query.setParameter("email", "%" + termoEmail + "%");
	    }
	    
	    if (termoUsuario != null && !termoUsuario.isEmpty()) {
	        query.setParameter("usuario", "%" + termoUsuario + "%");
	    }

	    return query.getResultList();
	}
}	
