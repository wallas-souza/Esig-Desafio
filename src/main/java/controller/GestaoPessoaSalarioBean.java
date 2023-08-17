package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.PessoaSalario;
import service.PessoaSalarioService;

@Named
@ViewScoped
public class GestaoPessoaSalarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaSalarioService pessoaSalarioService;
	
	private PessoaSalario pessoaSalario;
	
	private List<PessoaSalario> list;
	
	private Integer termoPesquisa;

	private String termoNome;
	
	public void todasPessoasSalarios() {
		list = pessoaSalarioService.getAll();
	}
	
	public List<PessoaSalario> getListPessoasSalarios(){
		return list;
	}
	
	public void pesquisar() {
		String jpql = pessoaSalarioService.construirConsulta(termoPesquisa, termoNome);
		list = pessoaSalarioService.consultar(jpql, termoPesquisa, termoNome);
	}
	
	public PessoaSalario getPessoaSalario() {
		return pessoaSalario;
	}
	
	public void setPessoaSalario(PessoaSalario pessoaSalario) {
	    this.pessoaSalario = pessoaSalario;
	}
	
	public Integer getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(Integer termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public String getTermoNome() {
		return termoNome;
	}

	public void setTermoNome(String termoNome) {
		this.termoNome = termoNome;
	}

}
