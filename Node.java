
package MUSIC;



public class Node {

	String FilePath; 
       
	Node next , prev ;

	public Node (Node prev ,Node next , String filePath) {
		this.prev =prev;
		this.next =next;
		this.FilePath=filePath ;
	}

	public Node (String FilePath){
		this(null, null, FilePath);
	}
}