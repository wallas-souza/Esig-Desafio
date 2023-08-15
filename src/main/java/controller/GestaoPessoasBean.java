package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Cargo;
import entity.Pessoa;
import service.CargosService;
import service.PessoaService;
import util.CargoConverter;

@Named
@ViewScoped
public class GestaoPessoasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CargosService cargosService;
	
	private Pessoa pessoa = new Pessoa();
	
	private Pessoa pessoaSelecionada = new Pessoa();
	
	@Inject
	private PessoaService pessoaService;
	
	private List<Pessoa> listaPessoas;
	
	private Converter cargoConverter;
	
	private String termoPesquisa;
	
	private String termoCidade;
	
	private String termoCargo;
	
	private String termoEmail;
	
	private String termoUsuario;
	
	public void prepararNovaPessoa() {
		pessoa = new Pessoa();
	}
	
	public void prepararEditarPessoa() {
		pessoaSelecionada = new Pessoa();
		cargoConverter = new CargoConverter(Arrays.asList(pessoaSelecionada.getCargoId()));
	}
	
	public void salvar() {
		pessoaService.salvar(pessoa);
		todasPessoas();
	}
	
	public void atualizar() {
		pessoaService.atualizar(pessoaSelecionada);
		todasPessoas();
	}
	
	public void delete() {
		pessoaService.excluir(pessoaSelecionada);
		todasPessoas();
	}
	
	public String url() {
		return "GestaoPessoaSalario?faces-redirect=true";
	}
	
	public void todasPessoas() {
		listaPessoas = pessoaService.getAllPessoas();
	}
	
	public List<Cargo> completarCargo(String termo){
		List<Cargo> listCargos = cargosService.pesquisar(termo);
		
		cargoConverter = new CargoConverter(listCargos);
		
		return listCargos;
	}
	
	public void pesquisar() {
	    String jpql = pessoaService.construirConsulta(termoPesquisa, termoCidade, termoCargo, termoEmail, termoUsuario);
	    listaPessoas = pessoaService.consultar(jpql,termoPesquisa, termoCidade, termoCargo, termoEmail, termoUsuario);
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
	
	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public String getTermoCidade() {
		return termoCidade;
	}
	
	public void setTermoCidade(String termoCidade) {
		this.termoCidade = termoCidade;
	}
	
	public String getTermoCargo() {
		return termoCargo;
	}
	
	public void setTermoCargo(String termoCargo) {
		this.termoCargo = termoCargo;
	}
	
	public String getTermoEmail() {
		return termoEmail;
	}
	
	public void setTermoEmail(String termoEmail) {
		this.termoEmail = termoEmail;
	}
	
	public String getTermoUsuario() {
		return termoUsuario;
	}
	
	public void setTermoUsuario(String termoUsuario) {
		this.termoUsuario = termoUsuario;
	}
	

}
