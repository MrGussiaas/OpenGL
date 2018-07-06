package com.gussiaas.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class FileUtils {
	public static Optional<String> readFile(String fileName){
		StringBuilder answer = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String nextLine;
				while((nextLine = reader.readLine()) != null){
					answer.append(nextLine).append(System.lineSeparator());
				}
				return Optional.of(answer.toString());
			}
			
		 catch (IOException e1) {
			e1.printStackTrace();
			return Optional.empty();
		}
	}
}
