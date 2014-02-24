/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;

/**
 * @author nikhillo
 * An abstract class that represents a dictionary object for a given index
 */
public abstract class Dictionary implements Writeable {
	
	Map<String, Integer> m;
	INDEXFIELD fld;
	Properties prop;
	
	public Dictionary (Properties props, INDEXFIELD field) {
		//TODO Implement this method
		this.m=new HashMap <String, Integer>();
		this.fld=field;
		this.prop=props;
	}
	
	/* (non-Javadoc)
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#writeToDisk()
	 */
	public void writeToDisk() throws IndexerException, IOException {
		// TODO Implement this method
		FileOutputStream fos= new FileOutputStream(prop.getProperty(IndexerConstants.TEMP_DIR)+"DocumentDictionary");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
        oos.writeObject(this.m);
        oos.close();
	}// NOT CREATING ANY LOCAL DICTIONARIES---> HENCE NO CODE FOR WRITING THEM TO DISK

	/* (non-Javadoc)
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#cleanUp()
	 */
	public void cleanUp() {
		// TODO Implement this method
		this.m=null;
	}
	
	/**
	 * Method to check if the given value exists in the dictionary or not
	 * Unlike the subclassed lookup methods, it only checks if the value exists
	 * and does not change the underlying data structure
	 * @param value: The value to be looked up
	 * @return true if found, false otherwise
	 */
	public boolean exists(String value) {
		//TODO Implement this method
		if(this.m.containsKey(value))
			return true;
		else return false;
	}
	
	/**
	 * MEthod to lookup a given string from the dictionary.
	 * The query string can be an exact match or have wild cards (* and ?)
	 * Must be implemented ONLY AS A BONUS
	 * @param queryStr: The query string to be searched
	 * @return A collection of ordered strings enumerating all matches if found
	 * null if no match is found
	 */
	public Collection<String> query(String queryStr) {
		//TODO: Implement this method (FOR A BONUS)
		Collection<String> c=new ArrayList<String>();
		if(!queryStr.contains("*")&&!queryStr.contains("?"))
		{
			if(this.m.containsKey(queryStr))
			{c.add(queryStr);
			return c;}
			else return null;
		}
		else if(queryStr.contains("*")||queryStr.contains("?"))
		{
			if(queryStr.contains("*")&&!queryStr.contains("?"))
			{	
				if(queryStr.matches("(?m)^(\\*).*"))
				{	String temp=null;
				Matcher mx=Pattern.compile("(?m)^(\\*)(.*)").matcher(queryStr);
				if(mx.find())
					temp=mx.group(2);
					for(Entry<String,Integer>e:m.entrySet())
					{
						if(e.getKey().matches("(?m).*"+temp+"$"))
							c.add(e.getKey());
					}
					return c;
				}
				if(queryStr.matches("(?m).*\\*$"))
				{	
					String temp=null;
					Matcher mx=Pattern.compile("(?m)(.*)\\*$").matcher(queryStr);
					if(mx.find())
						{temp=mx.group(1);}
					for(Entry<String,Integer>e:m.entrySet())
					{
						if(e.getKey().matches("(?m)^"+temp+".*"))
							c.add(e.getKey());
					}
					return c;
				}
				if(queryStr.matches(".+\\*.+"))
				{
					String temp1=null;
					String temp2=null;
					Matcher mx=Pattern.compile("(.+)\\*(.+)").matcher(queryStr);
					if(mx.find())
					{
						temp1=mx.group(1);
						temp2=mx.group(2);
					}
					for(Entry<String,Integer>e:m.entrySet())
					{
						if(e.getKey().matches("(?m)^"+temp1+".*"+temp2+"$"))
								c.add(e.getKey());
					}
					return c;
				}
			}
		else if(queryStr.contains("?")&&!queryStr.contains("*"))
		{
			if(queryStr.matches("(?m)^\\?.*"))
			{
				String temp=null;
				Matcher mx=Pattern.compile("(?m)^\\?(.*)").matcher(queryStr);
				if(mx.find())
					temp=mx.group(1);
				for(Entry<String,Integer>e:m.entrySet())
				{
					if(e.getKey().matches("(?m)^.{1}"+temp+"$"))
							c.add(e.getKey());
				}
				return c;
			}
			if(queryStr.matches("(?m).*\\?$"))
			{
				String temp=null;
				Matcher mx=Pattern.compile("(?m)(.*)\\?$").matcher(queryStr);
				if(mx.find())
					temp=mx.group(1);
				for(Entry<String,Integer>e:m.entrySet())
				{
					if(e.getKey().matches("(?m)^"+temp+".{1}"))
							c.add(e.getKey());
				}
				return c;
			}
			if(queryStr.matches(".+\\?.+"))
			{
				String temp1=null;
				String temp2=null;
				Matcher mx=Pattern.compile("(?m)^(.+)\\?(.+)$").matcher(queryStr);
				if(mx.find()){
					temp1=mx.group(1);
					temp2=mx.group(2);
				for(Entry<String,Integer>e:m.entrySet())
				{
					if(e.getKey().matches("(?m)^"+temp1+".{1}"+temp2+"$"))
							c.add(e.getKey());
				}
				return c;
			}
			}
		}
		
		}
		return null;
	}
	
	/**
	 * Method to get the total number of terms in the dictionary
	 * @return The size of the dictionary
	 */
	public int getTotalTerms() {
		//TODO: Implement this method
		return this.m.size();
	}
}
