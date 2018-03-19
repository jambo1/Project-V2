import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E> extends LinkedStack<CountedElement> implements Bag<CountedElement>, Iterable<CountedElement>{
	
	//Nested node class to contain each node of the tree
	private class Node{
		//Instance variables
		private CountedElement element;
		private Node left;
		private Node right;
		private Node parent;
		
		//Constructor
		public Node(CountedElement element2, Node parent) {
			this.element=element2;
			left=null;
			right=null;
			this.parent=parent;
		}
		
		public CountedElement getElement() {return element;}
		public Node getLeft() {return left;}
		public Node getRight() {return right;}
		public Node getParent() {return parent;}
		
		public void setLeft(CountedElement leftElem) {this.left=new Node(leftElem, this);}
		public void setLeft(Node leftNode) {this.left=leftNode;}
		public void setRight(CountedElement rightElem) {this.right=new Node(rightElem, this);}
		public void setRight(Node rightNode) {this.right=rightNode;}
		
		public void setParent(Node node) {this.parent=node;}
		
		public String toString() {return element.toString();}
		
		public void removeNode() {
			this.element=null;
			this.parent=null;
			this.left=null;
			this.right=null;
		}
	}
	
	//Root node, the beginning of the BST
	private Node root;
	//Other instance variables
	private int size;
	
	//Constructor for the BST, initially sets root node to null as tree is empty
	public BSTBag() {
		this.root=null;
		this.size=0;
	}
	

	@Override
	public boolean isEmpty() {
		if(root==null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean equals(Bag that) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		Node node = this.root;
		while(true) {
			while(node.getLeft()!=null) {
				node=node.getLeft();
			}
			if(node.getRight()!=null) {
				node=node.getRight();
			}
			if(node.getLeft()==null&&node.getRight()==null) {
				Node temp = node;
				node=node.getParent();
				temp.removeNode();
			}
		}
		
	}

	@Override
	public boolean contains(CountedElement element) {
		if(root==null) {
			return false;
		}
		else {
			int direction=0;
			Node curr = root;
			for(;;) {
				direction = new CountedElement(element).compareTo(new CountedElement(curr.getElement()));
				if(direction==0) {
					return true;
				}
				else if(direction==1) {
					if(curr.getRight()!=null) {
						curr=curr.getRight();
					}
					else {
						return false;
					}
				}
				else if(direction==-1) {
					if(curr.getLeft()!=null) {
						curr=curr.getLeft();
					}
					else {
						return false;
					}
				}
			}
		}
	}

	@Override
	public void add(CountedElement element) {
		if(root==null) {
			root=new Node(element, null);
		}
		else {
			int direction =0;
			boolean atEnd=false;
			Node node=root;
			while(atEnd==false) {
				direction = new CountedElement(element).compareTo(new CountedElement(node.getElement()));
				//If the element is larger than the node
				if(direction==1) {
					if(node.getRight()!=null) {
						node = node.getRight();
					}
					else {
						node.setRight(element);
						size++;
						atEnd=true;
					}
				}
				//If the element is smaller than the node
				else if(direction==-1) {
					if(node.getLeft()!=null) {
						node = node.getLeft();
					}
					else {
						node.setLeft(element);
						size++;
						atEnd=true;
					}
				}
				//The counted element is the same as the current one, so increment count
				else if(direction==0) {
					node.getElement().incrementCount();
					atEnd=true;
				}
			}
		}	
	}

	@Override
	public void remove(CountedElement element) {
		int direction =0;
		Node curr = root;
		
		for(;;) {
			if(curr==null) return;
		
			direction= element.compareTo(curr.getElement());
			if(direction ==0) {
				curr.getElement().decrementCount();
			}
			else if(direction==1) {
				curr=curr.getRight();
			}
			else if(direction==-1) {
				curr=curr.getLeft();
			}
		}
	}


	@Override
	public Iterator<CountedElement> iterator() {
		return new Iterator<CountedElement>() {
			Node cur = root.getLeft();
			boolean readFirst = false;
			
			public Node left(Node cur) {
				if(cur.getLeft()!=null) {
					cur=cur.getLeft();
					return left(cur);
				}
				else {
					return cur;
				}
			}
			public boolean hasNext() {
				if(cur.getRight()==null) {
					Node temp = cur;
					while(temp.getParent()!=null) {
						if(temp.getParent().getElement().compareTo(cur.getElement())>0) {
					
						return true;
					}
					else {
						temp=temp.getParent();
					}
					}
					return false;
			}else {
				return true;	
			}
			}
			
			public CountedElement next() {
				if(cur.getParent()==null && cur.getRight()==null) {
					throw new NoSuchElementException();
				}
				if(!readFirst) {
					readFirst = true;
					return cur.getElement();
				}
				else {
					if(cur.right!=null) {
						cur=left(cur.getRight());
					}
					else if(cur.getElement().compareTo(cur.getParent().getElement())<0) {
						Node temp = cur;
						while(temp!=null) {
							if(temp.getParent()==null) {
								throw new NoSuchElementException();
							}
							if(cur.getElement().compareTo(temp.getParent().getElement())<0) {
								cur = temp.getParent();
								break;
							}
							else {
								temp=temp.getParent();
							}
						}
					}else {
						cur=cur.getParent();
					}
				}
				return cur.getElement();
			}
		};
	}
}
			
			
			
//		class TreeIterator implements Iterator{
//			private Node next;
//			
//			public TreeIterator(Node root) {
//				next= root;
//				if(next==null) {
//					return;
//				}
//				while (next.getLeft()!=null) {
//					next=next.getLeft();
//				}
//			}
//			
//			
//			
//			public Node next() {
//				if(!hasNext()) throw new NoSuchElementException();
//				Node r = next;
//				
//				if(next.getRight()!=null) {
//					next=next.getRight();
//					while(next.getLeft()!=null) {
//						next=next.getLeft();
//					}
//					return r;
//				}
//				while(true) {
//					if(next.getParent()==null) {
//						next=null;
//						return r;
//					}
//					if(next.getParent().getLeft()==next) {
//						next=next.getParent();
//						return r;
//					}
//					next=next.getParent();
//				}
//			}
//		}
//		
//		Iterator<CountedElement> iterator;
//		return iterator;
//		
//	}


	
	
	

