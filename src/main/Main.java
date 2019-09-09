package main;

import java.io.File;

import jframe.ReadImageUserInterface;

public class Main {
	

	public static void main(String[] args) {
		System.out.println("Hello,world!");

		File file=new File("resource/image.png");
		//System.out.println(file.getAbsolutePath());
		new ReadImageUserInterface().run();
		
	}

}
