import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordProcessor2{
	public static void main(String args[]) throws IOException {
		BSTBag bag = new BSTBag();
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
					
					bag.add(new CountedElement(word));
					
				
			}
		}
		
		}
		Iterator iterator = bag.iterator();
		while(iterator.hasNext()) {
			System.out.println("Iterator :"+ iterator.next());
		}
		System.out.println(bag.size());
		System.out.println(bag.isEmpty());
		System.out.println(bag.contains(new CountedElement("zoo")));
		bag.remove(new CountedElement("brain"));
		bag.iterator();
		bag.clear();
		bag.isEmpty();
	}
}

