package service;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) {
		System.out.println("----------------BST (INTEGERS)------------------");
		MyBST<Integer> bstForInteger = new MyBST<Integer>();
	
		try {
			System.out.println("----------------ADD------------------");
			bstForInteger.add(7);
			bstForInteger.add(10);
			bstForInteger.add(4);
			bstForInteger.add(15);
			bstForInteger.add(6);
			bstForInteger.print();
			System.out.println("----------------SEARCH------------------");
			System.out.println("6? -> " + bstForInteger.search(6));//true
			System.out.println("15? -> " + bstForInteger.search(15));//true
			System.out.println("100? -> " + bstForInteger.search(100));//false
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
