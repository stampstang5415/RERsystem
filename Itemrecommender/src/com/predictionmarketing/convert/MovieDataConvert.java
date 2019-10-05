package com.predictionmarketing.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieDataConvert {
	
	/**
	 * cat t.data | -f1,2,3 tr "\\t" ","
	 * @throws IOException 
	 * 
	 */

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("data/t.data"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/tables.csv"));
	
		String line;
		while((line = br.readLine()) != null) {
			String[] values = line.split("\\t", -1);
			bw.write(values[0] + "," + values[1] + "," + values[2] + "\n");	
		}
		
		br.close();
		bw.close();
	
	}

}
