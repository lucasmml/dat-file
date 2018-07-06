package dat.runner;

import java.io.File;
import java.io.FileNotFoundException;

import dat.dto.DadosOutDTO;
import dat.file.FileReader;
import dat.file.FileWriter;

public class Runner {

	public static void main(String args[]) throws FileNotFoundException {
		readFilesFromDadosIn(new File("dados/in"));
		DadosOutDTO dadosOutDTO = FileReader.summarize();
		FileWriter.createOutDatFile(dadosOutDTO);
	}

	public static void readFilesFromDadosIn(File directory) throws FileNotFoundException {
	    for (File file : directory.listFiles()) {
	    	if (file.isFile()) {
	    		Boolean isDatFile = file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("dat");
	    		if (isDatFile) {
	    			FileReader.readDatFile(file);
	    		}
	        } 
	    }
	}
}