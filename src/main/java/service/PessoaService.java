package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.Pessoa;
import model.PessoaSalario;
import repository.Pessoas;
import util.Transacional;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private PessoaSalarioService pessoaSalarioService;
	
	private PessoaSalario pessoaSalario = new PessoaSalario();
	
	public List<Pessoa> getAll() {
		return pessoas.findAll();
	}
	
	@Transacional
	public int quantidade() {
		return pessoas.contar();
	}
	
	@Transacional
	public void salvar(Pessoa pessoa) {
		int id = quantidade();
		pessoa.setId(id);
		pessoas.guardar(pessoa);
		pessoaSalarioService.salvar(pessoaSalario,pessoa);
	}
	
	@Transacional
	public void atualizar(Pessoa pessoaSelecionada) {
		pessoas.guardar(pessoaSelecionada);
		pessoaSalarioService.salvar(pessoaSalario,pessoaSelecionada);
	}
	
	@Transacional
	public void excluir(Pessoa pessoa) {
		pessoaSalarioService.excluir(pessoaSalario,pessoa);
		pessoas.remover(pessoa);
	}

}
