package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import entity.Pessoa;
import entity.PessoaSalario;
import repository.PessoaSalarios;
import util.Transacional;

public class PessoaSalarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaSalarios pessoaSalarios;
	
	public List<PessoaSalario> getAll() {
		return pessoaSalarios.findAll();
	}
	
	@Transacional
	public void salvar(PessoaSalario pessoaSalario, Pessoa pessoa) {
		pessoaSalario.setId(pessoa.getId());
		pessoaSalario.setNome(pessoa.getNome());
		pessoaSalario.setSalario(pessoa.getCargoId().getSalario());
		pessoaSalarios.guardar(pessoaSalario);
	}
	
	@Transacional
	public void excluir(PessoaSalario pessoaSalario,Pessoa pessoa) {
		pessoaSalario.setId(pessoa.getId());
		pessoaSalarios.remover(pessoaSalario);
	}
	
	
}
