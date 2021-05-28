package it.unibs.fp.mylib;

import java.util.ArrayList;

/*
Questa classe contiene dei metodi che consentono di fare delle particolari operazioni su degli array

*/
public class ManipolaArray {

	
	public static ArrayList<String> convertToArrayListString(String[] array){
		ArrayList<String> lista=new ArrayList<String>();
		
		for(int i=0;i<array.length;i++) {
			lista.add(new String(array[i]));
		}
		
		return lista;
	}
	
	
	public static ArrayList<Integer> convertToArrayListInteger(int[] array){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for(int i=0;i<array.length;i++) {
			lista.add(array[i]);
		}
		
		return lista;
	}
	
	
	
	public static ArrayList<Double> convertToArrayListDouble(double[] array){
		ArrayList<Double> lista=new ArrayList<Double>();
		
		for(int i=0;i<array.length;i++) {
			lista.add(array[i]);
		}
		
		return lista;
	}
	
	public static String[] convertToCommonArrayString(ArrayList<String> list){
		String[] array=new String[list.size()];
		
		for(int i=0;i<array.length;i++) {
			array[i]=new String(list.get(i));
		}
		
		return array;
	}
	
	public static int[] convertToCommonArrayInt(ArrayList<Integer> list){
		int[] array=new int[list.size()];
		
		for(int i=0;i<array.length;i++) {
			array[i]=list.get(i);
		}
		
		return array;
	}
	
	public static double[] convertToCommonArrayDouble(ArrayList<Double> list){
		double[] array=new double[list.size()];
		
		for(int i=0;i<array.length;i++) {
			array[i]=list.get(i);
		}
		
		return array;
	}
	
	public static void printArray(int[] array,int n) {
		for(int i=0;i<n&&i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
	}
}
