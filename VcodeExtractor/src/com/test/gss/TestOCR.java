package com.test.gss;
 
import java.io.File; 
 
public class TestOCR {
	
	 public static void main(String[] args) {  
	        String path = "E://workspace//tesseract//second_test.png";       
	        System.out.println("ORC Test Begin......");  
	        try {       
	            String valCode = new OCR().recognizeText(new File(path), "png");       
	            System.out.println(valCode);       
	        } catch (Exception e) {    
	            e.printStackTrace();    
	        }         
	        System.out.println("ORC Test End......");  
	    }    
	  
	}  
