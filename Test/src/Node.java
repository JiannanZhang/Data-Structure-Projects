
// singly node
public class Node {
	String data;
	Node next;
	public Node (String data, Node next){
		this.data = data;
		this.next = next;
		
	}
	Node list = new Node("A",new Node("B",new Node("C",null)));
	
	public String getData() { return data; }
	public Node getNext() {return next; }
	public void setData(String s) { data = s;}
	public void setNext(Node n) {next = n;}
	
	public static String getThird(Node list){
		return list.getNext().getNext().getData();
	}
	
	public static void insertSecond(Node list, String s){
		Node newNode = new Node(s,list.getNext());
		list.setNext(newNode);
	}

}

// doubly node



//public class Node<E> {
//    private E myData;
//private Node myNext;
//public Node()
//{ myData = null; myNext = null; }
//public Node(E data, Node<E> next)
//{ myData = data; myNext = next; }
//public E getData()
//{ return myData; }
//public Node<E> getNext()
//{ return myNext; }
//public void setData(E data) { myData = data; }
//public void setNext(Node<E> next) { myNext = next; }
//}