package dat.dto;

public class DadosOutDTO {

	private Integer quantidadeClientes;
	private Integer quantidadeVendedores;
	private Integer idVendaValorMaisAlto;
	private String nomeVendedorQueMenosVendeu;

	public Integer getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(Integer quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public Integer getQuantidadeVendedores() {
		return quantidadeVendedores;
	}

	public void setQuantidadeVendedores(Integer quantidadeVendedores) {
		this.quantidadeVendedores = quantidadeVendedores;
	}

	public Integer getIdVendaValorMaisAlto() {
		return idVendaValorMaisAlto;
	}

	public void setIdVendaValorMaisAlto(Integer idVendaValorMaisAlto) {
		this.idVendaValorMaisAlto = idVendaValorMaisAlto;
	}

	public String getNomeVendedorQueMenosVendeu() {
		return nomeVendedorQueMenosVendeu;
	}

	public void setNomeVendedorQueMenosVendeu(String nomeVendedorQueMenosVendeu) {
		this.nomeVendedorQueMenosVendeu = nomeVendedorQueMenosVendeu;
	}

}
