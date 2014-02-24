/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;

/**
 * @author nikhillo
 * This class is used to introspect a given index
 * The expectation is the class should be able to read the index
 * and all associated dictionaries.
 */
public class IndexReader {
	
	Properties prop;
	INDEXFIELD fld;
	HashMap<String, HashMap<Integer,Integer>> index;
	HashMap<Integer, HashMap<Integer,Integer>> LinkIndex;
	Map<String, Integer> docDict;
	/**
	 * Constructor to create an instance 
	 * @param props: The properties file
	 * @param field: The index field whose index is to be read
	 * @throws Exception 
	 */
	public IndexReader(Properties props, INDEXFIELD field) throws Exception {
		//TODO: Implement this method
		this.prop=props;
		this.fld=field;
		this.docDict=new HashMap<String, Integer>();
		String filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"DocumentDictionary";
		FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.docDict= (Map<String, Integer>) ois.readObject();
        ois.close();
        
		if(fld==INDEXFIELD.AUTHOR)
		{	this.index=new HashMap<String,HashMap<Integer,Integer>>();
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"AuthorIndex";
			fis = new FileInputStream(filename);
	        ois = new ObjectInputStream(fis);
	        this.index=(HashMap<String, HashMap<Integer, Integer>>) ois.readObject();
	        ois.close();
		}
		if(fld==INDEXFIELD.CATEGORY)
		{	this.index=new HashMap<String,HashMap<Integer,Integer>>();
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"CategoryIndex";
			fis = new FileInputStream(filename);
	        ois = new ObjectInputStream(fis);
	        this.index= (HashMap<String, HashMap<Integer, Integer>>) ois.readObject();
	        ois.close();
		}
		if(fld==INDEXFIELD.LINK)
		{	
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"LinkIndex";
			fis = new FileInputStream(filename);
	        ois = new ObjectInputStream(fis);
	        this.LinkIndex= (HashMap<Integer, HashMap<Integer, Integer>>)ois.readObject();
	        ois.close();
		}
		if(fld==INDEXFIELD.TERM)
		{this.index=new HashMap<String,HashMap<Integer,Integer>>();
			for(int i=0;i<14;i++)
			{	
				
				filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"TermIndex"+i;
				fis = new FileInputStream(filename);
		        ois = new ObjectInputStream(fis);
		        HashMap<String, HashMap<Integer,Integer>> temp= (HashMap<String, HashMap<Integer, Integer>>)ois.readObject();
		        ois.close();
		        this.index.putAll(temp);
			}
		}
	}
	
	/**
	 * Method to get the total number of terms in the key dictionary
	 * @return The total number of terms as above
	 */
	public int getTotalKeyTerms() {
		//TODO: Implement this method
		if(this.fld==INDEXFIELD.LINK)
		return this.LinkIndex.size();
		else
		return this.index.size();
	}
	
	/**
	 * Method to get the total number of terms in the value dictionary
	 * @return The total number of terms as above
	 */
	public int getTotalValueTerms() {
		//TODO: Implement this method
		return this.docDict.size();
	}
	
	/**
	 * Method to retrieve the postings list for a given dictionary term
	 * @param key: The dictionary term to be queried
	 * @return The postings list with the value term as the key and the
	 * number of occurrences as value. An ordering is not expected on the map
	 */
	public Map<String, Integer> getPostings(String key) {
		//TODO: Implement this method
		if(this.fld==INDEXFIELD.LINK)
		{
			int id=getDocumentid(key);
			HashMap<String,Integer>hm=new HashMap<String, Integer>();
			for(Entry<Integer,Integer>e:this.LinkIndex.get(id).entrySet())
			{
				hm.put(getDocumentName(e.getKey()),e.getValue());
			}
			return hm;
		}
		else
		{
			HashMap<String,Integer>hm=new HashMap<String, Integer>();
			for(Entry<Integer,Integer>e:this.index.get(key).entrySet())
			{
				hm.put(getDocumentName(e.getKey()),e.getValue());
			}
			return hm;
		}
	}
	
	/**
	 * Method to get the top k key terms from the given index
	 * The top here refers to the largest size of postings.
	 * @param k: The number of postings list requested
	 * @return An ordered collection of dictionary terms that satisfy the requirement
	 * If k is more than the total size of the index, return the full index and don't 
	 * pad the collection. Return null in case of an error or invalid inputs
	 */
	public Collection<String> getTopK(int k) {
		//TODO: Implement this method
		
		if(k>0)
		{
			if(this.fld==INDEXFIELD.LINK)
			{	HashMap<Integer, String>mm=new HashMap<Integer, String>();
				Collection<Integer>c=new ArrayList<Integer>(this.LinkIndex.keySet());
				for (Iterator<Integer> iterator = c.iterator(); iterator.hasNext();)
				{
					int x=iterator.next();	
					mm.put(x,getDocumentName(x));
				}
				if(k>=this.LinkIndex.size())
				{
					Collection<String>cx=new ArrayList<String>(mm.values());
					return cx;
				}
				else
				{
					HashMap<String, Integer> sizeMap=new HashMap<String,Integer>();
					for(Entry<Integer,HashMap<Integer,Integer>>e:this.LinkIndex.entrySet())
					{
						sizeMap.put(getDocumentName(e.getKey()), e.getValue().size());
					}
					
					MyComparator bvc =  new MyComparator(sizeMap);
			        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
			        sorted_map.putAll(sizeMap);
			        
			        Collection<String>cxx=new ArrayList<String>(sorted_map.keySet());
			        Collection<String>c1xx=new ArrayList<String>();
			        Iterator<String> iter = cxx.iterator();
			        for(int i=0;i<k;i++)
			        c1xx.add(iter.next());
			        return c1xx;    
				}
			}
			else
			{
				if(k>=this.index.size())
				{
					Collection<String>c=new ArrayList<String>(this.index.keySet());
					return c;
				}
				else
				{
					HashMap<String, Integer> sizeMap=new HashMap<String,Integer>();
					for(Entry<String,HashMap<Integer,Integer>>e:this.index.entrySet())
					{
						sizeMap.put(e.getKey(), e.getValue().size());
					}
					MyComparator bvc =  new MyComparator(sizeMap);
			        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
			        sorted_map.putAll(sizeMap);
			        
			        Collection<String>c=new ArrayList<String>(sorted_map.keySet());
			        Collection<String>c1=new ArrayList<String>();
			        Iterator<String> iter = c.iterator();
			        for(int i=0;i<k;i++)
			        c1.add(iter.next());
			        return c1;
				}
			}
		}
		else return null;
	}
	
	/**
	 * Method to execute a boolean AND query on the index
	 * @param terms The terms to be queried on
	 * @return An ordered map containing the results of the query
	 * The key is the value field of the dictionary and the value
	 * is the sum of occurrences across the different postings.
	 * The value with the highest cumulative count should be the
	 * first entry in the map.
	 */
	public Map<String, Integer> query(String... terms) {
		//TODO: Implement this method (FOR A BONUS)
		return null;
	}
	
	public String getDocumentName(int id)
	{
		for (Entry<String,Integer> e : docDict.entrySet()) {
	        if (id==e.getValue()) {
	            return e.getKey();
	        }}
		return null;
	}
	
	public int getDocumentid(String name)
	{
		for (Entry<String,Integer> e : docDict.entrySet()) {
	        if (name==e.getKey()) {
	            return e.getValue();
	        }}
		return -1;
	}
	
	class MyComparator implements Comparator<String> {

		HashMap<String, Integer> base;
	    public MyComparator(HashMap<String, Integer> base) {
	        this.base = base;
	    }
	    
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }

}

}
