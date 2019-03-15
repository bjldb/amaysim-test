package com.amaysim.shoppingcart.implem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
	
	public interface ILineProcessor {
		Object processLine(String line);
	}
	
	public static List<Object> processLines(String filePath, ILineProcessor processor, boolean skipOnError) throws IOException {
		List<Object> result = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while (true) {
				try {
					String line = br.readLine();
					if(line != null) {
						result.add(processor.processLine(line));
					} else {
						break;
					}
				} catch (IOException ex) {
					if(skipOnError) {
						throw ex;
					}
				}
		    } 
		} 
		return result;
	}
}
