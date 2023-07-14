package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Pessoa;
import model.PessoaSalario;

public class PessoaSalarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public PessoaSalarios() {

	}

	public PessoaSalarios(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<PessoaSalario> findAll() {
		TypedQuery<PessoaSalario> query = manager.createQuery("SELECT p FROM PessoaSalario p ORDER BY p.id", PessoaSalario.class);
		return query.getResultList();
	}
	
	public List<PessoaSalario> todas() {
		return manager.createQuery("from PessoaSalario ORDER BY id", PessoaSalario.class).getResultList();
	}

	public PessoaSalario porId(int id) {
		return manager.find(PessoaSalario.class, id);
	}

	public PessoaSalario guardar(PessoaSalario pessoaSalario) {
		return manager.merge(pessoaSalario);
	}

	public void remover(PessoaSalario pessoaSalario) {
	    PessoaSalario pessoaParaRemover = porId(pessoaSalario.getId());
	    if (pessoaParaRemover != null) {
	        manager.remove(pessoaParaRemover);
	    }
	}
}	
