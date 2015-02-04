package dataStructure;

import java.util.Scanner;

import helperUtils.DrinkItem;

/**
 * This hashtable is used to store item object
 * @author Rhea
 *
 */
public class HashTable {
	
	private static final int tableSize=4;
	private DrinkItem[] table = new DrinkItem[tableSize];	
	
	public HashTable(){
		for(int i=0;i<tableSize;i++)
		{
			table[i] = new DrinkItem();
			table[i].name = "empty";
			table[i].drink = "empty";
			table[i].next = null;
		}
	}
	
	public int Hash(String key){
		int hash = 0;
		int index;
		
		for(int i=0;i<key.length();i++)
		{
			//hash = hash+Character.getNumericValue(key.charAt(i));
			hash = (hash+Character.getNumericValue(key.charAt(i))*17);
		}
		index = hash % tableSize;
		
		return index;
	}
	
	public void AddItem(String name, String drink)
	{
		int index = Hash(name);
		if(table[index].name == "empty")
		{
			table[index].name=name;
			table[index].drink = drink;
		}
		else
		{
			DrinkItem parentdi = table[index];
			DrinkItem newdi = new DrinkItem();
			newdi.name = name;
			newdi.drink = drink;
			newdi.next = null;
			while(parentdi.next!=null)
				parentdi = parentdi.next;
			parentdi.next = newdi;
		}
	}
	
	public int NumberOfItemsInIndex(int index)
	{
		int count = 0;
		if(table[index].name=="empty") return count;
		else
		{
			count++;
			DrinkItem ptr = table[index];
			while(ptr.next!=null)
			{
				count++;
				ptr = ptr.next;
			}
		}
		
		return count;
	}

	public void PrintTable()
	{
		int number;
		for(int i=0;i<tableSize;i++)
		{
			number = NumberOfItemsInIndex(i);
			System.out.println("------------------");
			System.out.println("index="+i);
			System.out.println(table[i].name);
			System.out.println(table[i].drink);
			System.out.println("# of items = "+number);
			System.out.println("------------------");
		}
	}
	
	public void PrintItemsInIndex(int index)
	{
		DrinkItem Ptr = table[index];
		if(Ptr.name == "empty")
			System.out.println("index="+index+" is empty");
		else
		{
			System.out.println("index "+index+" contains the following items");
			while(Ptr!=null)
			{
				System.out.println("------------------");
				System.out.println(Ptr.name);
				System.out.println(Ptr.drink);
				System.out.println("------------------");
				Ptr = Ptr.next;
			}
		}
	}
	
	public String FindDrink(String name)
	{
		int index = Hash(name);
		boolean foundName = false;
		String drink="";
		
		DrinkItem Ptr = table[index];
		while(Ptr != null)
		{
			if(Ptr.name.equals(name))
			{
				foundName = true;
				drink = Ptr.drink;
				return drink;
			}
			Ptr = Ptr.next;
		}
		return drink;
	}
	
	public void removeItem(String name)
	{
		int index = Hash(name);
		DrinkItem delPtr = new DrinkItem();
		DrinkItem P1 = new DrinkItem();
		DrinkItem P2 = new DrinkItem();
		
		//bucket is empty
		if(table[index].name.equals("empty")&&table[index].drink.equals("empty"))
		{
			System.out.println(name+" was not found in the Hash Table");
		}
		
		// only 1 item contained in bucket and that item has matching name
		else if(table[index].name.equals(name) && table[index].next == null)
		{
			table[index].name = "empty";
			table[index].drink = "empty";
			System.out.println(name+" was removed from the Hash Table");
		}
		
		// match is located in the first item in the bucket but there  are more
		// itmes in the bucket
		else if(table[index].name.equals(name))
		{
			table[index] = table[index].next;
			System.out.println(name+" was removed from the Hash Table");
		}
		
		//bucket contains itemts but first item is not a match
		else
		{
			P1 = table[index].next;
			P2 = table[index];
			while(P1 != null && !P1.name.equals(name))
			{
				P2 = P1;
				P1 = P1.next;
			}
			
			// no match
			if(P1 == null)
				System.out.println(name+ "was not found in the Hash Table");
			// match is found
			else
			{
				P1 = P1.next;
				P2.next = P1;
				System.out.println(name+" was removed from the Hash Table");
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		HashTable hashy = new HashTable();
		String name="Emma";
		
		hashy.AddItem("Paul", "Locha");
		hashy.AddItem("K", "Iced Mocha");
		hashy.AddItem("Emma", "Strawberry Smoothy");
		hashy.AddItem("Annie", "Hot Chocolate");
		hashy.AddItem("Pepper", "Caramel Mocha");
		hashy.AddItem("Sarah", "Passion Tea");
		hashy.AddItem("Mike", "Chai Tea");
		hashy.AddItem("Steve", "Apple Cider");
		hashy.AddItem("Bill", "Root Beer");
		hashy.AddItem("Susan", "Skinny Latte");
		hashy.AddItem("Joe", "Green Tea");
		hashy.AddItem("Marie", "Water");
	
		hashy.PrintTable();
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		hashy.PrintItemsInIndex(2);	
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		//System.out.println(name+"'s drink is "+hashy.FindDrink(name));
		//System.out.println("++++++++++++++++++++++++++++++++++++++");
		hashy.removeItem("Bill");
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		hashy.PrintTable();
		hashy.PrintItemsInIndex(2);	
		
	}
	

}
