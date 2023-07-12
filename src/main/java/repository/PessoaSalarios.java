package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		TypedQuery<PessoaSalario> query = manager.createQuery("SELECT p FROM Pessoa p ORDER BY p.id", PessoaSalario.class);
		return query.getResultList();
	}

	public PessoaSalario porId(int id) {
		return manager.find(PessoaSalario.class, id);
	}

	public List<PessoaSalario> pesquisar(String nome) {
		String jpql = "from pessoa_salario where nome like :nome";
		
		TypedQuery<PessoaSalario> query = manager
				.createQuery(jpql, PessoaSalario.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}

	public PessoaSalario guardar(PessoaSalario pessoaSalario) {
		return manager.merge(pessoaSalario);
	}

	public void remover(PessoaSalario pessoaSalario) {
		pessoaSalario = porId(pessoaSalario.getId());
		manager.remove(pessoaSalario);
	}
}	
