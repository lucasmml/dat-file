package dat.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import dat.dto.ClienteDTO;
import dat.dto.ItemDTO;
import dat.dto.VendaDTO;
import dat.dto.VendedorCountDTO;
import dat.dto.VendedorDTO;

public class FileReader {

	private static Set<ClienteDTO> setClientes = new HashSet<ClienteDTO>();
	private static Set<VendedorDTO> setVendedores = new HashSet<VendedorDTO>();
	private static ArrayList<VendaDTO> listVendas = new ArrayList<VendaDTO>();

	public static void readDatFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] var = line.split(";");
			if (var[0].equals("001")) {
				addVendedor(var);
			} else if (var[0].equals("002")) {
				addCliente(var);
			} else if (var[0].equals("003")) {
				addVenda(var);
			}
			System.out.println(line);
		}
	}

	private static void addVendedor(String[] var) {
		VendedorDTO vendedor = new VendedorDTO();
		vendedor.setIdentificador(Integer.valueOf(var[0]));
		vendedor.setCpf(new BigInteger(var[1]));
		vendedor.setNome(var[2]);
		vendedor.setSalario(Double.valueOf(var[3]));

		setVendedores.add(vendedor);
	}

	private static void addCliente(String[] var) {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setIdentificador(Integer.valueOf(var[0]));
		cliente.setCnpj(new BigInteger(var[1]));
		cliente.setNome(var[2]);
		cliente.setRamoDeAtividade(var[3]);

		setClientes.add(cliente);
	}

	private static void addVenda(String[] var) {
		VendaDTO newVenda = new VendaDTO();
		newVenda.setIdentificador(Integer.valueOf(var[0]));
		newVenda.setIdVenda(Integer.valueOf(var[1]));
		newVenda.setNomeVendedor(var[5]);

		ItemDTO item = new ItemDTO();
		item.setIdItem(Integer.valueOf(var[2]));
		item.setQuantidadeItem(Integer.valueOf(var[3]));
		item.setPrecoItem(Double.valueOf(var[4]));
		Double valorItem = item.getQuantidadeItem() * item.getPrecoItem();

		if (listVendas.isEmpty()) {
			newVenda.getItens().add(item);
			newVenda.setValorTotal(valorItem);
			listVendas.add(newVenda);
			return;
		}

		boolean isNewVenda = true;
		for (VendaDTO venda : listVendas) {
			if (venda.getIdVenda().equals(newVenda.getIdVenda())) {
				venda.getItens().add(item);
				venda.setValorTotal(venda.getValorTotal() + valorItem);
				isNewVenda = false;
			}
		}

		if (isNewVenda) {
			newVenda.getItens().add(item);
			newVenda.setValorTotal(valorItem);
			listVendas.add(newVenda);
		}
	}

	public static void summarize() {
		Integer quantidadeClientes = setClientes.size();
		Integer quantidadeVendedores = setVendedores.size();
		Integer idVendaValorMaisAlto = getIdVendaValorMaisAlto();
		String nomeVendedorQueMenosVendeu = getVendedorQueMenosVendeu();

		createOutDatFile(quantidadeClientes, quantidadeVendedores, idVendaValorMaisAlto, nomeVendedorQueMenosVendeu);
	}

	private static void createOutDatFile(Integer quantidadeClientes, Integer quantidadeVendedores,
			Integer idVendaValorMaisAlto, String nomeVendedorQueMenosVendeu) {

		try {
			PrintWriter writer = new PrintWriter("dados/out/infosSumarizadas.dat.proc", "UTF-8");
			writer.println("1. Quantidade de Clientes: " + quantidadeClientes);
			writer.println("2. Quantidade de Vendedores: " + quantidadeVendedores);
			writer.println("3. ID da Venda de valor mais alto: " + idVendaValorMaisAlto);
			writer.println("4. Nome do Vendedor que menos vendeu: " + nomeVendedorQueMenosVendeu);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	private static Integer getIdVendaValorMaisAlto() {
		Integer idVendaValorMaisAlto = null;
		Double vendaValorMaisAlto = 0.0;
		for (VendaDTO venda : listVendas) {
			if (venda.getValorTotal() > vendaValorMaisAlto) {
				vendaValorMaisAlto = venda.getValorTotal();
				idVendaValorMaisAlto = venda.getIdVenda();
			}
		}
		return idVendaValorMaisAlto;
	}

	private static String getVendedorQueMenosVendeu() {
		Set<VendedorCountDTO> setVendedoresCount = new HashSet<VendedorCountDTO>();

		for (VendaDTO venda : listVendas) {
			VendedorCountDTO vendedorCountDTO = new VendedorCountDTO();
			vendedorCountDTO.setNome(venda.getNomeVendedor());
			setVendedoresCount.add(vendedorCountDTO);
		}

		for (VendaDTO venda : listVendas) {
			for (VendedorCountDTO vendedorCountDTO : setVendedoresCount) {
				if(vendedorCountDTO.getNome().equals(venda.getNomeVendedor())) {
					vendedorCountDTO.setValorTotalDeVendas(vendedorCountDTO.getValorTotalDeVendas() + venda.getValorTotal());
				}
		     }
		}
		
		Double menorValorDeVendas = Double.MAX_VALUE;
		String nomeVendedorComMenorNumeroDeVendas = null;
		
		for (VendedorCountDTO vendedorCountDTO : setVendedoresCount) {
			if(vendedorCountDTO.getValorTotalDeVendas() < menorValorDeVendas) {
				menorValorDeVendas = vendedorCountDTO.getValorTotalDeVendas();
				nomeVendedorComMenorNumeroDeVendas = vendedorCountDTO.getNome();
			}
	     }
		
		return nomeVendedorComMenorNumeroDeVendas;
	}
}