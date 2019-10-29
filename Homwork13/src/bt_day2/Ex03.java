package bt_day2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex03 {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String path = "courses\\Java06\\student.txt";
		File file = new File(path);
		List<Student> student = new ArrayList<Student>();
		List<Student> std = readFile(file, student);
		System.out.println("Quản Lý Sinh Viên");
		int chosse;
		do {
			System.out.println("============================================================");
			System.out.println(
					"1.Xem danh sách sinh viên\n2.Thêm sinh viên\n3.Xóa sinh viên\n4.Cập nhật sinh viên\5.In file\n6.Thoát");
			System.out.print("Mời bạn chọn yêu cầu: ");
			chosse = Integer.parseInt(input.nextLine());
			switch (chosse) {
			case 1:
				std.forEach(System.out::println);
				break;
			case 2:
				addStudent(std);
				break;
			case 3:
				removeStudent(std);
				break;
			case 4:
				upDateStudent(std);
				break;
			case 5:
				writeFile(file, std);
				System.out.println("In thành công");
				break;
			case 6:
				System.exit(0);
				break;
			default:
				break;
			}
		} while (chosse < 7);
	}

	private static List<Student> addStudent(List<Student> student) {
		String id = "";
		System.out.println("Moi ban nhap id: ");
		do {
			id = input.nextLine();
			if (isValid(id, student)) {
				System.out.println("Id đã tồn tại mời bạn nhập lại: ");
			}
		} while (isValid(id, student));
		System.out.println("Nhap ten: ");
		String name = input.nextLine();
		System.out.println("Nhap DTB: ");
		float avg = Float.parseFloat(input.nextLine());
		student.add(new Student(id, name, avg));
		System.out.println("Them thanh cong");
		return student;
	}

	private static void removeStudent(List<Student> student) {
		String id = "";
		System.out.println("Moi ban nhap id: ");
		do {
			id = input.nextLine();
			if (isValid(id, student) == false) {
				System.out.println("Id không tồn tại: ");
			}
		} while (isValid(id, student) == false);
		for (int i = 0; i < student.size(); i++) {
			if (id.equals(student.get(i).getId())) {
				student.remove(i);
			}
		}
		System.out.println("Xóa id thành công");
	}

	private static List<Student> upDateStudent(List<Student> student) {
		String id = "";
		System.out.println("Moi ban nhap id: ");
		do {
			id = input.nextLine();
			if (isValid(id, student) == false) {
				System.out.println("Id đã tồn tại mời bạn nhập lại: ");
			}
		} while (isValid(id, student) == false);
		System.out.println("Nhap ten: ");
		String name = input.nextLine();
		System.out.println("Nhap DTB: ");
		float avg = Float.parseFloat(input.nextLine());
		for (int i = 0; i < student.size(); i++) {
			if (id.equals(student.get(i).getId())) {
				student.get(i).setName(name);
				student.get(i).setAverage(avg);
			}
		}
		System.out.println("Cập nhật thành công");
		return student;

	}

	private static Boolean isValid(String id, List<Student> student) {
		for (Student std : student) {
			if (id.equals(std.getId())) {
				return true;
			}
		}
		return false;
	}

	private static List<Student> readFile(File file, List<Student> student) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] arr = line.split("-");
				if (arr.length == 3) {
					student.add(new Student(arr[0], arr[1], Float.parseFloat(arr[2])));
				}
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
		return student;
	}

	private static void writeFile(File file, List<Student> list) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (Student student2 : list) {
				bw.write(student2.toString());
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
