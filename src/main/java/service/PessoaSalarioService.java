package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.Pessoa;
import model.PessoaSalario;
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
	public void salvar(PessoaSalario pessoaSalario) {
		pessoaSalarios.guardar(pessoaSalario);
	}
}
