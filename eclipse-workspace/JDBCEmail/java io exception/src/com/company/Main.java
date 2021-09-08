package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
	String a= "hi";
   String b="hello";
   
   try {
	FileInputStream inputStream = new FileInputStream("C:/Users/abdul.ghani/Desktop/javas.xlsx");
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
  System.out.println(a+b);
   
   
   

	}

}
