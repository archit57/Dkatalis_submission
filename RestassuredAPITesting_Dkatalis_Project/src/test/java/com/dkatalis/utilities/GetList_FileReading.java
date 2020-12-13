package com.dkatalis.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GetList_FileReading {

	public static List<String> filereader(String fileloc) throws IOException {
		BufferedReader br = null;
		List<String> apiurl = new ArrayList<String>();
		String line;

		try {
			br = Files.newBufferedReader(Paths.get(fileloc));
			// read line by line

			while ((line = br.readLine()) != null) {
				apiurl.add(line);
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return apiurl;

	}
}