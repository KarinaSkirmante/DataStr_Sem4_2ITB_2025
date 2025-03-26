package datastr;

public class MyBST<Ttype> {
	private MyBSTNode<Ttype> root = null;
	// private MyBSTNode<Ttype> lastLeaf = null;//ja nepieciešams
	private int counter = 0;

	// tiks izmantots Object bezargument konstruktors

	// ļoti iespejams nekad neizpildīsies, jo OS bodrišinās RAM pārvaldību
	private boolean isFull() {
		try {
			new MyBSTNode<Integer>(45); // meginām RAM atmiņa ielikt mezglu
			return false;// ja tas izdodas, tad saraksts nav pilns
		} catch (OutOfMemoryError e) {
			return true;// bet ja mezglu nevar vairs RAM atmiņa ielikt, tad saraksts ir pilns
		}
	}

	private boolean isEmpty() {
		return (counter == 0);
	}

	public int lenght() {
		return counter;
	}

	public void add(Ttype element) throws Exception {
		if (element == null) {
			throw new Exception("Element can not be null");
		}

		if (isEmpty()) {
			MyBSTNode<Ttype> newNode = new MyBSTNode(element);
			root = newNode;
			counter++;
		} else {
			if (isFull())// iespējams, nekad neizpildīsies, jo OS pati nodrosina, ka RAM ir pietiekoši
							// vietas
			{
				throw new Exception("BST is full");
			}

			addHelper(element, root);
		}

	}

	private void addHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		// pābraudam, pa kuru pusi elements tiks pievienots

		// ja elements ir lielāks par currentNode elementu, tad jāpavirzas uz labo pusi
		if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
			// ja pa neksiste labais bērns, tad var veidot jauno mezglu un pielipināt ka
			// labo bērnu
			if (currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setRightCh(newNode);
				newNode.setParent(currentNode);
				counter++;

			}
			// ja eksistē
			else {
				addHelper(element, currentNode.getRightCh());
			}

		}

		// ja elements ir mazaks par currentNode elementu, tad jāpavirzas uz kreiso pusi
		else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
			// neeksiste kreisais bērns, tad izveidojam jaunu mezglu un pievienjam ka kreiso
			if (currentNode.getLeftCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setLeftCh(newNode);
				newNode.setParent(currentNode);
				counter++;
			} else// ja tomēr jau eksiste kreisais bērns
			{
				addHelper(element, currentNode.getLeftCh());
			}
		}

	}

	// TODO
	// search
	public boolean search(Ttype element) throws Exception {
		if (element == null) {
			throw new Exception("Element can not be null");
		}

		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to search element");
		}

		boolean result = searchHelper(element, root);
		return result;

	}

	private boolean searchHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		if (element.equals(currentNode.getElement())) {
			return true;
		}

		else {
			// ja meklētais elements ir lielāks par currentNode elementu, tad jāpavirzas uz
			// labo pusi
			if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
				// pārbaudām, vai eksiste mezgls labājā pusē. Ja neeksistē, tad varam drošī
				// pateikt, ka elements nav atrasts, jo esam jau nokļuvisi pie vietas, kur tam
				// būtu jābūt, bet nav
				if (currentNode.getRightCh() == null) {
					return false;
				} else// eksistē bērna mezgls labajā pusē
				{
					return searchHelper(element, currentNode.getRightCh());
				}
			} // ja meklētais elements ir mazāks par currentNode elementu
			else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
				// pārbaudām, vai eksiste mezgls kreisais pusē. Ja neeksistē, tad varam drošī
				// pateikt, ka elements nav atrasts, jo esam jau nokļuvisi pie vietas, kur tam
				// būtu jābūt, bet nav
				if (currentNode.getLeftCh() == null) {
					return false;
				} else// eksistē bērna mezgls kreisajā pusē
				{
					return searchHelper(element, currentNode.getLeftCh());
				}
			}
		}

		return false;

	}

	// print
	public void print() throws Exception {
		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to print elements");
		}

		printHelper(root);
	}

	// TODO uztaisīt funkcijas arī postfiksās un gala apstaigāšanas gadījumiem
	private void printHelper(MyBSTNode<Ttype> currentNode) {
		// PREFIX apstaigāsana Sakne-kreisais bēŗns - labais bērns
		System.out.println("P -> " + currentNode.getElement());

		// ja eksistē kreisais bērns
		if (currentNode.getLeftCh() != null) {
			System.out.println(
					"Left ch -> " + currentNode.getLeftCh().getElement() + " (" + currentNode.getElement() + ")");
			printHelper(currentNode.getLeftCh());
		}

		// ja eksistē labais bērns
		if (currentNode.getRightCh() != null) {
			System.out.println(
					"Right ch -> " + currentNode.getRightCh().getElement() + " (" + currentNode.getElement() + ")");
			printHelper(currentNode.getRightCh());
		}

	}
	// TODO

	// makeEmpty (skatīt piemēru no Sem2)
	public void makeEmpty() {
		if (!isEmpty()) {
			root = null;
			counter = 0;
			System.gc();
		}
	}

	// delete

	public void delete(Ttype element) throws Exception {
		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to delete element");
		}

		if (!search(element)) // ja nebūs atrasts elements, ko grib dzest
		{
			throw new Exception("Element doesn't exists in BST and it is not possible to delete it");
		}

		deleteHelper(element, root);

	}

	private void deleteHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		// TODO samazinār coutner, pie atrastā elementa
		// ja sakritīs, tad dzēsīsim
		if (element.equals(currentNode.getElement())) {
			// ja currentNode mezgls ir kā lapa
			if (currentNode.getLeftCh() == null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();

				// jānoņem saite uz kreiso bērnu
				if (parentOfCurrentNode.getLeftCh().getElement().equals(element)) {
					parentOfCurrentNode.setLeftCh(null);
				} else if (parentOfCurrentNode.getRightCh().getElement().equals(element)) {
					parentOfCurrentNode.setRightCh(null);
				}

			}

			// ja currentNode mezglam ir tikai viens bērns
			// gadījums, kad ir tikai labais bērns
			else if (currentNode.getLeftCh() == null && currentNode.getRightCh() != null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> rightChildOCurrentNode = currentNode.getRightCh();

				parentOfCurrentNode.setRightCh(rightChildOCurrentNode);
				rightChildOCurrentNode.setParent(parentOfCurrentNode);
			}
			// ir tikai kreisais bērns
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> leftChildOCurrentNode = currentNode.getLeftCh();

				parentOfCurrentNode.setRightCh(leftChildOCurrentNode);
				leftChildOCurrentNode.setParent(parentOfCurrentNode);
			}
			// ja currentNode mezglam ir abi bērni
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() != null) {
				// -----------------------------SĀKAS PIEVIENOTAIS KODA FRAGMENTS--------------------------

				// Caur labo pusi, meklēs "kreisāko bērnu"
				MyBSTNode<Ttype> temp = root.getRightCh();
				System.out.println(temp.getElement());
				// ja labajam bērnam nav neviens kreisajā pusē piesaistīts, tad pašu labo bērnu
				// jāieliek dzēšamajā vietā
				if (temp.getLeftCh() == null) {
					currentNode.setElement(temp.getElement());
					currentNode.setRightCh(temp.getRightCh());
					temp.getRightCh().setParent(currentNode);
				} else // ja labajam bērnam ir piesaistīts kreisais bērns, tad tiek meklēts arī kreisā
						// bērna kreisais bērns utt
				{
					while (temp.getLeftCh() != null) {
						temp = temp.getLeftCh();
					}
					// temp2 - būs ar to vērtību, kas ir jāieliek ieks tempNode
					currentNode.setElement(temp.getElement());

					// var gadīties, ka mezglu, kuru vērtību pārvietos uz dzēšamo mezglu, ir
					// piesaistīts labais bērns, tad šo labo bērnu vecākma jāuztaisa kā kreiso
					if (temp.getRightCh() != null) {
						MyBSTNode<Ttype> parent = temp.getParent();
						MyBSTNode<Ttype> rightCh = temp.getRightCh();
						parent.setLeftCh(rightCh);
						rightCh.setParent(parent);
					} else // ja mezgls ir kā lapa, tad var uz to vienkārsi noņemt sasaisti no vecāka puses
					{
						// noņemam sasaisti vecākam uz to mezglu, kura vērtību uzlika dzēšamajā vietā
						MyBSTNode<Ttype> parent = temp.getParent();
						if (parent.getLeftCh().equals(temp)) {
							parent.setLeftCh(null);
						} else if (parent.getRightCh().equals(temp)) {
							parent.setRightCh(null);
						}
					}
				}
				// -----------------------------BEIDZAS PIEVIENOTAIS KODA FRAGMENTS--------------------------

			}
		} else {
			// ja elements ir lielāks par currentNode elementu
			if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
				// izsaucam rekursiju, tikai tad, ja labajā pusē ir piesiastīts bērns
				if (currentNode.getRightCh() != null) {
					deleteHelper(element, currentNode.getRightCh());
				}
			} else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
				// izsaucam rekursiju, tikai tad, ja kreisajā pusē ir piesiastīts bērns
				if (currentNode.getLeftCh() != null) {
					deleteHelper(element, currentNode.getLeftCh());
				}
			}

		}
	}

}
