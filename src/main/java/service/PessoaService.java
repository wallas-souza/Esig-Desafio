package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import entity.Pessoa;
import entity.PessoaSalario;
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
	
	public List<Pessoa> getAllPessoas() {
		return pessoas.todas();
	}
	
	public String construirConsulta(String termoPesquisa,String termoCidade,String termoCargo, String termoEmail, String termoUsuario) {
	    String jpql = "SELECT p FROM Pessoa p WHERE 1 = 1";

	    if (termoPesquisa != null && !termoPesquisa.isEmpty()) {
	        jpql += " AND lower(p.nome) like lower(:nome)";
	    }

	    if (termoCidade != null && !termoCidade.isEmpty()) {
	        jpql += " AND lower(p.cidade) like lower(:cidade)";
	    }

	    if (termoCargo != null && !termoCargo.isEmpty()) {
	        jpql += " AND lower(p.cargoId.nome) like lower(:nomeCargo)";
	    }
	    
	    if (termoEmail != null && !termoEmail.isEmpty()) {
	        jpql += " AND lower(p.email) like lower(:email)";
	    }
	    
	    if (termoUsuario != null && !termoUsuario.isEmpty()) {
	        jpql += " AND lower(p.usuario) like lower(:usuario)";
	    }

	    jpql += " ORDER BY p.id ASC";

	    return jpql;
	}
	
	public List<Pessoa> consultar(String jpql,String termoPesquisa,String termoCidade,String termoCargo, String termoEmail, String termoUsuario) {
		return pessoas.executarConsulta(jpql, termoPesquisa, termoCidade, termoCargo, termoEmail, termoUsuario);
	}

}
