package de.tum.jk.findbugs;

public class FindBugsMain {

	public static void main(String[] args) {
		Student student1 = new Student("Samuel", "Singh");
		Student student2 = new Student("Max", "Mustermann");

		Course course = new Course("EIST");
		course.addParticipant(student1);
		course.addParticipant(student2);

		TUMOnline tumOnline = new TUMOnline();
		tumOnline.enrolStudent(student1);
		tumOnline.enrolStudent(student2);
		tumOnline.registerCourse(course);

		System.out.println(tumOnline.findStudentByName("Samuel", "Singh"));
	}
}
