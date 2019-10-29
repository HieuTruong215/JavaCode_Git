package bt_day2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Ex05 {
	public static void main(String[] args) {
		Random rd = new Random();
		Scanner ip = new Scanner(System.in);
		String path = "courses\\Java06\\readme.txt";
		boolean isValid = createNewfile(path);
		File file = new File(path);
		int n, m;
		System.out.println("Nhap so hang: ");
		n = Integer.parseInt(ip.nextLine());
		System.out.println("Nhap so cot: ");
		m = Integer.parseInt(ip.nextLine());
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = rd.nextInt(10);
			}
		}

		writeFile(file, arr, n, m);
		readFile(file);
	}

	private static boolean createNewfile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				return file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static void readFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void writeFile(File file, int[][] arr, int n, int m) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("rows: " + n + "|| cols: " + m);
			bw.newLine();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					bw.write(arr[i][j] + "   ");
				}
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

	private static boolean createNewDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			return dir.mkdirs();
		}

		return false;
	}
}
