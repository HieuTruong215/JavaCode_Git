package bt_day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Currency;
import java.util.Random;

//Bài toán chưa được tối ưu mong mọi người giúp đỡ
public class Ex01 {
	public static void main(String[] args) {
		Random rd = new Random();
		String[] file = new String[20];
		String root = "garbage";
		String[] extension = { ".png", ".jpg", ".jpeg", ".mp3", ".mp4", ".bat", ".java", ".txt" };
		File folder = new File(root);// tao folder garbage
		int lengthOFEnum = Directory.values().length;
		String[] pathFile = new String[lengthOFEnum];
		boolean isDir = createNewDirectory(root);
		if (isDir) {
			for (int i = 0; i < lengthOFEnum; i++) {// Tạo 4 folder từ Enum
				Directory dir = Directory.values()[i];
				pathFile[i] = root + File.separator + dir;
				createNewDirectory(pathFile[i]);
			}
			for (int j = 0; j < 20; j++) {// tạo 20 file random
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
//========================================================================================================================================//
		// Move .png, .jpg, jpeg vào thư mục image
		File[] dirI = folder.listFiles(p -> p.getAbsolutePath().endsWith(".png") || p.getAbsolutePath().endsWith(".jpg")
				|| p.getAbsolutePath().endsWith(".jpeg"));
		for (File files : dirI) {
			String pathFolder = folder.getAbsolutePath() + File.separator + "image" + File.separator + files.getName();
			move(files.getAbsolutePath(), pathFolder);
		}
//========================================================================================================================================//
		// Move .mp3, mp4 vào thư mục music
		File[] dirM = folder
				.listFiles(p -> p.getAbsolutePath().endsWith(".mp3") || p.getAbsolutePath().endsWith(".mp4"));
		for (File files : dirM) {
			String pathFolder = folder.getAbsolutePath() + File.separator + "music" + File.separator + files.getName();
			move(files.getAbsolutePath(), pathFolder);
		}
//========================================================================================================================================//
		// Move .bat vào thư mục System
		File[] dirS = folder.listFiles(p -> p.getAbsolutePath().endsWith(".bat"));
		for (File files : dirS) {
			String pathFolder = folder.getAbsolutePath() + File.separator + "system" + File.separator + files.getName();
			move(files.getAbsolutePath(), pathFolder);
		}
//========================================================================================================================================//
		// Copy .java vào thư mục coding
		File[] dirC = folder.listFiles(p -> p.getAbsolutePath().endsWith(".java"));
		for (File files : dirC) {
			String pathFolder = folder.getAbsolutePath() + File.separator + "coding" + File.separator + files.getName();
			copy(files.getAbsolutePath(), pathFolder);
		}
//======================================================================================================================================//
		// Delete .txt
		File[] dirD = folder.listFiles(p -> p.getAbsolutePath().endsWith(".txt"));
		for (File files : dirD) {
			String pathFolder = folder.getAbsolutePath() + File.separator + files.getName();
			File dirDelete = new File(pathFolder);
			boolean isValid = dirDelete.delete();
		}
	}

//======================================================================================================================================//
	private static boolean move(String pathFile, String pathFolder) {
		File source = new File(pathFile);
		if (source.exists()) {
			File target = new File(pathFolder + source.getName());
			try {
				Path path = Files.move(source.toPath(), target.toPath(), StandardCopyOption.ATOMIC_MOVE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private static boolean copy(String pathFile, String pathFolder) {
		File source = new File(pathFile);
		if (source.exists()) {
			File target = new File(pathFolder + source.getName());
			try {
				Path path = Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
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
