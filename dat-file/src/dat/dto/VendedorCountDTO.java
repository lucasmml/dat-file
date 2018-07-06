package dat.dto;

import java.math.BigInteger;

public class VendedorCountDTO {

	public VendedorCountDTO() {
		this.valorTotalDeVendas = 0.0;
	}

	private String nome;
	private Double valorTotalDeVendas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorTotalDeVendas() {
		return valorTotalDeVendas;
	}

	public void setValorTotalDeVendas(Double valorTotalDeVendas) {
		this.valorTotalDeVendas = valorTotalDeVendas;
	}

}