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
			
			
			//-----------------------------SĀKAS PIEVIENOTAIS KODA FRAGMENTS--------------------------
			System.out.println("----------------DELETE------------------");
			bstForInteger.delete(7);//izdzēšot 7, ta vietā vajadzētu ielikt 10
			bstForInteger.print();
			
			//-----------------------------BEIDZAS PIEVIENOTAIS KODA FRAGMENTS--------------------------
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
