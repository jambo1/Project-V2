public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;
	
	//Constructor including count
	public CountedElement(E e, int count){
		element = e;
		this.count=count;
	}
	
	//Constructor with default count
	public CountedElement(E element2){
		element = element2;
		count++;
	}

	//add getters and setters
	public E getElement() {
			return element;
	}
	public int getCount() {return count;}
	public void incrementCount() {count++;}
	public void decrementCount() {count--;}
	
	//add toString() method
	public String toString() { return("("+element.toString()+","+count+")");}
	

	//Compares the element to order alphabetically, if completely even 0 is returned
	public int compareTo(CountedElement<E> sC1) {
		String elem = element.toString();
		String otherElem = sC1.getElement().toString();
		for(int i=0;i<element.toString().length();i++) {
			if(elem.charAt(i)<otherElem.charAt(i)) {
				return -1;
			}
			else if(elem.charAt(i)>otherElem.charAt(i)) {
				return 1;
			}
		}
		return 0;
		
	}

}
