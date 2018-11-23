package de.tum.jk.findbugs;

import java.util.List;
import java.util.ArrayList;

public class Course {
	private String name;
	public List<Student> participants;

	public Course(String name) {
		this.name = name;
		this.participants=new ArrayList<Student>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfParticipants() {
		return this.participants.size();
	}

	public void addParticipant(Student student) {
		participants.add(student);
	}
}
