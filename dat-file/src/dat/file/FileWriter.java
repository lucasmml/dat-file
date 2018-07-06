package dat.file;

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
import dat.dto.DadosOutDTO;
import dat.dto.ItemDTO;
import dat.dto.VendaDTO;
import dat.dto.VendedorCountDTO;
import dat.dto.VendedorDTO;

public class FileWriter {

	public static void createOutDatFile(DadosOutDTO dadosOutDTO) {
		try {
			new File("dados/out").mkdirs();
			PrintWriter writer = new PrintWriter("dados/out/infosSumarizadas.dat.proc", "UTF-8");
			writer.println("1. Quantidade de Clientes: " + dadosOutDTO.getQuantidadeClientes());
			writer.println("2. Quantidade de Vendedores: " + dadosOutDTO.getQuantidadeVendedores());
			writer.println("3. ID da Venda de valor mais alto: " + dadosOutDTO.getIdVendaValorMaisAlto());
			writer.println("4. Nome do Vendedor que menos vendeu: " + dadosOutDTO.getNomeVendedorQueMenosVendeu());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}