/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nikhillo
 * This class represents a subclass of a Dictionary class that is
 * local to a single thread. All methods in this class are
 * assumed thread safe for the same reason.
 */
public class LocalDictionary extends Dictionary {
	
	
	private static AtomicInteger nextIdforauthor = new AtomicInteger(0);
	private static AtomicInteger nextIdforcategory = new AtomicInteger(0);
	private static AtomicInteger nextIdforterm = new AtomicInteger(0);
	private static AtomicInteger nextIdforlink = new AtomicInteger(0);
	
	/**
	 * Public default constructor
	 * @param props: The properties file
	 * @param field: The field being indexed by this dictionary
	 */
	public LocalDictionary(Properties props, INDEXFIELD field) {
		super(props, field);
		
	}
	
	/**
	 * Method to lookup and possibly add a mapping for the given value
	 * in the dictionary. The class should first try and find the given
	 * value within its dictionary. If found, it should return its
	 * id (Or hash value). If not found, it should create an entry and
	 * return the newly created id.
	 * @param value: The value to be looked up
	 * @return The id as explained above.
	 */
	public int lookup(String value) {
		//TODO Implement this method
		if(this.m.containsKey(value))
			return this.m.get(value);
		else{
		int x=getNextId();
		m.put(value, x);
		return x;}
		}
	
	private int getNextId() {
		if(this.fld==INDEXFIELD.AUTHOR)
			return nextIdforauthor.incrementAndGet();
		if(this.fld==INDEXFIELD.CATEGORY)
		    return nextIdforcategory.incrementAndGet();
		if(this.fld==INDEXFIELD.TERM)
		    return nextIdforterm.incrementAndGet();
		if(this.fld==INDEXFIELD.LINK)
		    return nextIdforlink.incrementAndGet();
		return -1;
		}
}
