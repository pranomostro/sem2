package edu.tum.cs.i1.eist2018.tutorial;

public class App {
	private String name = "ArTEMiS";

	public String getName() {
		return name;
	}
	
	public App() {
		
	}
	
	public void print() {
		System.out.println("Hello " + getName());
	}

	public static void main(String[] args)
	{
		App app = new App();
		app.print();
	}
}
