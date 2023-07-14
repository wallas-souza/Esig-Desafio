package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cargo;
import model.Pessoa;
import model.PessoaSalario;
import repository.Cargos;
import repository.Pessoas;
import service.PessoaSalarioService;
import service.PessoaService;

@Named
@ViewScoped
public class GestaoPessoasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Cargos cargos;
	
	private Pessoa pessoa = new Pessoa();
	
	private Pessoa pessoaSelecionada = new Pessoa();
	
	private PessoaSalario pessoaSalario = new PessoaSalario();
	
	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private PessoaSalarioService pessoaSalarioService;
	
	private List<Pessoa> listaPessoas;
	
	private Converter cargoConverter;
	
	public void prepararNovaPessoa() {
		pessoa = new Pessoa();
	}
	
	public void prepararEditarPessoa() {
		pessoaSelecionada = new Pessoa();
		cargoConverter = new CargoConverter(Arrays.asList(pessoaSelecionada.getCargoId()));
	}
	
	public void salvar() {
		pessoaService.salvar(pessoa);
		pessoaSalarioService.salvar(pessoaSalario,pessoa);
		todasPessoas();
	}
	
	public void atualizar() {
		pessoaService.atualizar(pessoaSelecionada);
		pessoaSalarioService.salvar(pessoaSalario,pessoa);
		todasPessoas();
	}
	
	public void delete() {
		pessoaSalarioService.excluir(pessoaSalario,pessoa);
		pessoaService.excluir(pessoaSelecionada);
		todasPessoas();
	}
	
	public String url() {
		return "GestaoPessoaSalario?faces-redirect=true";
	}
	
	public void todasPessoas() {
		listaPessoas = pessoas.todas();
	}
	
	public List<Cargo> completarCargo(String termo){
		List<Cargo> listCargos = cargos.pesquisar(termo);
		
		cargoConverter = new CargoConverter(listCargos);
		
		return listCargos;
	}
	
	public List<Pessoa> getListPessoas(){
		return listaPessoas;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
	    this.pessoa = pessoa;
	}
	
	public Converter getCargoConverter() {
		return cargoConverter;
	}
	
	public Pessoa getpessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setpessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	

}
