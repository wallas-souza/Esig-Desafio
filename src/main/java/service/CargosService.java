package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import entity.Cargo;
import repository.Cargos;

public class CargosService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cargos cargos;
	
	
	public List<Cargo> pesquisar (String termo){
		return cargos.pesquisar(termo);
	}
	
}
