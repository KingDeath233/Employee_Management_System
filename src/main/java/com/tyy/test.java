package com.tyy;

import java.util.Arrays;

public class test {
	
	public static void main (String args[]) {
		String s = String.format("%08d",(int)(Math.random()*100000000));
		System.out.println(s);
	}
}
