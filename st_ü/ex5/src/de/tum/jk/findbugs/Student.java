package de.tum.jk.findbugs;

public class Student {
	private String firstName;
	private String lastName;

	public Student(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName=firstName.replaceAll("[^A-Za-z]", "");
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName=lastName.replaceAll("[^A-Za-z]", "");
	}
}
