package bt_day1;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ex02 {
	public static void main(String[] args) {
		Random rd = new Random();
		String root = "images";
		String[] extension = { ".jpg", ".png" };
		File folder = new File(root);
		boolean isDir = createNewDirectory(root);
		if (isDir) {
			for (int i = 0; i < 30; i++) {
				String fileName = System.currentTimeMillis() + extension[rd.nextInt(extension.length)];
				String name = root + File.separator + fileName;
				createNewfile(name);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
		File[] dir = folder.listFiles(p -> p.getAbsolutePath().endsWith(".jpg"));
		int i = 1;
		for (File file2 : dir) {
			String newName = folder.getAbsolutePath() + File.separator + i + ".jpg";
			File newFile = new File(newName);
			file2.renameTo(newFile);
			i++;
		}
		File[] dirN = folder.listFiles(p -> p.getAbsolutePath().endsWith(".png"));
		int j = 1;
		for (File file2 : dirN) {
			String newName = folder.getAbsolutePath() + File.separator + j + ".png";
			File newFile = new File(newName);
			file2.renameTo(newFile);
			j++;
		}
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

	private static boolean createNewDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			return dir.mkdirs();
		}

		return false;
	}
}
