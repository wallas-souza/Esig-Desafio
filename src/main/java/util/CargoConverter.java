package util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.Cargo;

public class CargoConverter implements Converter {

	private List<Cargo> listCargos;
	
	public CargoConverter(List<Cargo> listCargos) {
		this.listCargos = listCargos;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) {
			return null;
		}
		Long id = Long.valueOf(value); 
		
		for (Cargo cargo: listCargos ) {
			if(id.equals(cargo.getId())) {
				return cargo;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}
		Cargo cargo = (Cargo) value;
		
		return cargo.getId().toString();
	}

}
