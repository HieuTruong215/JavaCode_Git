package bt_day2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex04 {
	public static void main(String[] args) {
		String pathInput = "courses\\Java06\\input.txt";
		String pathOutput = "courses\\Java06\\output.txt";
		List<Integer> digits = new ArrayList<>();

		File fileInput = new File(pathInput);
		File fileOutput = new File(pathOutput);
		digits = readFile(fileInput, digits);
		sortednNumber(digits, fileOutput);
		writeFile(fileOutput, digits);

	}

	private static List<Integer> sortednNumber(List<Integer> list, File file) {
		list.stream().sorted((d1, d2) -> d1 - d2).forEach(System.out::println);
		return list;
	}

	private static List<Integer> split(List<Integer> list, String digit) {
		String[] s = digit.split("[a-z]+");
		for (String dig : s) {
			if (!dig.equals("")) {
				list.add(Integer.parseInt(dig));
			}
		}
		return list;
	}

	private static List<Integer> readFile(File file, List<Integer> list) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				split(list, line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private static void writeFile(File file, List<Integer> list) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (Integer digit : list) {
				bw.write(digit.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
