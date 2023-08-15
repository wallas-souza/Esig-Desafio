package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.PessoaSalario;
import repository.PessoaSalarios;

@Named
@ViewScoped
public class GestaoPessoaSalarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaSalarios pessoaSalarios;
	
	private PessoaSalario pessoaSalario;
	
	private List<PessoaSalario> list;
	
	public void todasPessoasSalarios() {
		list = pessoaSalarios.todas();
	}
	
	public List<PessoaSalario> getListPessoasSalarios(){
		return list;
	}
	
	public PessoaSalario getPessoaSalario() {
		return pessoaSalario;
	}
	
	public void setPessoaSalario(PessoaSalario pessoaSalario) {
	    this.pessoaSalario = pessoaSalario;
	}

}
