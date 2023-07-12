package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Pessoa;
import repository.Pessoas;

@Named
@ViewScoped
public class GestaoPessoasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	private List<Pessoa> listaPessoas;
	
	public void todasPessoas() {
		listaPessoas = pessoas.todas();
	}
	
	public List<Pessoa> getListPessoas(){
		return listaPessoas;
	}

}
