package bt_day2;

public class Student {
	private String id;
	private String name;
	private Float average;

	public Student() {
	}

	public Student(String id, String name, Float average) {
		this.id = id;
		this.name = name;
		this.average = average;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAverage() {
		return average;
	}

	public void setAverage(Float average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return id + "-" + name + "-" + average;
	}

}
