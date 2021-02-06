package com.app.highPeak;
import java.io.*;
import java.util.*;
class Item {
	String name;
	int price;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String toString() { 
		return this.name + ": " + this.price;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		// Change the file path according to the folder while Execution
		FileInputStream fileInputStream=new FileInputStream("input.txt");       
		Scanner scanner=new Scanner(fileInputStream);
		int number_of_employees = Integer.parseInt(scanner.nextLine().split(": ")[1]);
		scanner.nextLine(); scanner.nextLine(); scanner.nextLine();

		ArrayList<Item> goodies_items = new ArrayList<Item>();

		while(scanner.hasNextLine())  
		{
			String current[] = scanner.nextLine().split(": ");
			goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
		}
		scanner.close();

		Collections.sort(goodies_items, new Comparator<Item>(){
			public int compare(Item a, Item b) { 
				return a.price - b.price; 
			} 
		});

		int min_diff = goodies_items.get(goodies_items.size()-1).price;
		int min_index = 0;
		for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
			int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

			if(diff<=min_diff) {
				min_diff = diff;
				min_index = i;
			}
		}

		// Change the file path according to the folder while Execution
		FileWriter fileWriter = new FileWriter("output.txt");
		fileWriter.write("The goodies selected for distribution are:\n\n");
		for(int i=min_index;i<min_index + number_of_employees; i++) {
			fileWriter.write(goodies_items.get(i).toString() + "\n");
		}

		fileWriter.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
		fileWriter.close();
	}
}
