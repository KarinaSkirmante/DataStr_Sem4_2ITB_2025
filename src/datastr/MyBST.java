package datastr;

public class MyBST <Ttype> {
	private MyBSTNode<Ttype> root = null;
	//private MyBSTNode<Ttype> lastLeaf = null;//ja nepieciešams
	private int counter = 0;
	
	//tiks izmantots Object bezargument konstruktors
	
	//ļoti iespejams nekad neizpildīsies, jo OS bodrišinās RAM pārvaldību
	private boolean isFull() {
		try {
			new MyBSTNode<Integer>(45); //meginām RAM atmiņa ielikt mezglu
			return false;//ja tas izdodas, tad saraksts nav pilns
		}
		catch (OutOfMemoryError e) {
			return true;//bet ja mezglu nevar vairs RAM atmiņa ielikt, tad saraksts ir pilns
		}
	}
	
	
	private boolean isEmpty()
	{
		return (counter == 0);
	}
	
	public int lenght()
	{
		return counter;
	}
	

}
