
//import classes for file input - scanner etc.
import java.io.*;
import java.util.Scanner;
import java.util.*;

//import implementing set (eg. TreeSet)




public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		StringBuilder sb = new StringBuilder();
		
		int count =0;
		for(E e:inputSet) {
			sb.append(e.toString()+", ");
			count++;
			
			if(count%5==0) {
				sb.append("%n");
			}
		}
		
			
		return(String.format(sb.toString()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		//create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<String>();
		//create a set of type CountedElement<String> called countedWordSet 
		Set<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();
		//for each input file (assume 3 arguments, each the name of a file)
		//  for each word w
		//     if wordset doesnt contain w:
		//        add w to wordset
	    //        add new element to countedWordSet
		//     else
		//        increment numeric part of element in countedWordSet containing w
		for(String name:args) {
			File file = new File(name);
			//System.out.println(file.canRead());
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner lineScan = new Scanner(line);
				while(lineScan.hasNext()) {
					String word = lineScan.next().trim();
					System.out.println(word);
					
					//Else if the word is not in the wordset already add it
					if(!wordSet.contains(word)){
						countedWordSet.add(new CountedElement<String>(word));
						wordSet.add(word);
						
						System.out.println("not in wordset word "+word);
					}
					if(!countedWordSet.toString().contains(word)) {
						countedWordSet.add(new CountedElement<String>(word));
					}
					//Otherwise increase the counted element
					else {
						CountedElement element = null;
						for(CountedElement c: countedWordSet) {
							System.out.println(c.getElement().toString());
							if(c.getElement().toString().equals(word)) {
								element=c;
							}
						}
						element.incrementCount();
					}
				}
				
			}
		}
	System.out.println("countedword set");
	System.out.println(displaySet(countedWordSet));
	System.out.println("word set");
	System.out.println(displaySet(wordSet));
	}
}
