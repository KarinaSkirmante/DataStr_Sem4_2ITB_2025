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
	
	public void add(Ttype element) throws Exception {
		if(element == null)
		{
			throw new Exception("Element can not be null");
		}
		
		if(isEmpty())
		{
			MyBSTNode<Ttype> newNode = new MyBSTNode(element);
			root = newNode;
			counter++;
		}
		else
		{
			if(isFull())//iespējams, nekad neizpildīsies, jo OS pati nodrosina, ka RAM ir pietiekoši vietas
			{
				throw new Exception("BST is full");
			}
			
			addHelper(element, root);
		}

		
	}
	
	private void addHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		//pābraudam, pa kuru pusi elements tiks pievienots
		
		//ja elements ir lielāks par currentNode elementu, tad jāpavirzas uz labo pusi
		if(    ((Comparable)element).compareTo(currentNode.getElement()) == 1)
		{
			//ja pa neksiste labais bērns, tad var veidot jauno mezglu un pielipināt ka labo bērnu
			if(currentNode.getRightCh()==null)
			{
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setRightCh(newNode);
				newNode.setParent(currentNode);
				counter++;
				
			}
			//ja eksistē
			else
			{
				addHelper(element, currentNode.getRightCh());
			}
			
		}
		
		//ja elements ir mazaks par currentNode elementu, tad jāpavirzas uz kreiso pusi
		else if( ((Comparable)element).compareTo(currentNode.getElement()) == -1)
		{
			//neeksiste kreisais bērns, tad izveidojam jaunu mezglu un pievienjam ka kreiso
			if(currentNode.getLeftCh() == null)
			{
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setLeftCh(newNode);
				newNode.setParent(currentNode);
				counter++;
			}
			else//ja tomēr jau eksiste kreisais bērns
			{
				addHelper(element, currentNode.getLeftCh());
			}
		}
		
	}
	

}
