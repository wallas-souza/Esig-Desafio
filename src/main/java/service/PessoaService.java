package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.Pessoa;
import repository.Pessoas;
import util.Transacional;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	public List<Pessoa> getAll() {
		return pessoas.findAll();
	}
	
	@Transacional
	public Long quantidade() {
		return pessoas.contar();
	}
	
	@Transacional
	public void salvar(Pessoa pessoa) {
		Long id = quantidade();
		pessoa.setId(id);
		pessoas.guardar(pessoa);
	}
	
	@Transacional
	public void excluir(Pessoa pessoa) {
		pessoas.remover(pessoa);
	}

}
