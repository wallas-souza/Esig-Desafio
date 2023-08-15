package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Cargo;

public class Cargos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cargos() {

	}

	public Cargos(EntityManager manager) {
		this.manager = manager;
	}

	public Cargo porId(Long id) {
		return manager.find(Cargo.class, id);
	}

	public List<Cargo> pesquisar(String nome) {
		String jpql = "from Cargo where nome like :nome";
		
		TypedQuery<Cargo> query = manager
				.createQuery(jpql, Cargo.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}

	public Cargo guardar(Cargo cargo) {
		return manager.merge(cargo);
	}

	public void remover(Cargo cargo) {
		cargo = porId(cargo.getId());
		manager.remove(cargo);
	}
}
