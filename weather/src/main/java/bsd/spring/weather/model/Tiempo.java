package bsd.spring.weather.model;

import java.io.Serializable;

public class Tiempo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String ciudad, region, pais, temperatura;

	public Tiempo(String ciudad, String region, String pais, String temperatura) {
		this.ciudad = ciudad;
		this.region = region;
		this.pais = pais;
		this.temperatura = temperatura;
	}

	public String getCiudad() {
		return ciudad;
	}

	@Override
	public String toString() {
		return "Tiempo [ciudad=" + ciudad + ", region=" + region + ", pais=" + pais + ", temperatura=" + temperatura
				+ "]";
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
}
