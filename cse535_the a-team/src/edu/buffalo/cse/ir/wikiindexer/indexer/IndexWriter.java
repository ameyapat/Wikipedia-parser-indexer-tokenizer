/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import edu.buffalo.cse.ir.wikiindexer.FileUtil;
import edu.buffalo.cse.ir.wikiindexer.IndexerConstants;

/**
 * @author nikhillo
 * This class is used to write an index to the disk
 * 
 */
public class IndexWriter implements Writeable {
	
	HashMap<String, HashMap<Integer,Integer>> index;
	INDEXFIELD fld;
	Properties prop;
	boolean forward;
	HashMap<Integer, HashMap<Integer,Integer>> LinkIndex;
	int pnum;
	
	
	/**
	 * Constructor that assumes the underlying index is inverted
	 * Every index (inverted or forward), has a key field and the value field
	 * The key field is the field on which the postings are aggregated
	 * The value field is the field whose postings we are accumulating
	 * For term index for example:
	 * 	Key: Term (or term id) - referenced by TERM INDEXFIELD
	 * 	Value: Document (or document id) - referenced by LINK INDEXFIELD
	 * @param props: The Properties file
	 * @param keyField: The index field that is the key for this index
	 * @param valueField: The index field that is the value for this index
	 */
	public IndexWriter(Properties props, INDEXFIELD keyField, INDEXFIELD valueField) {
		this(props, keyField, valueField, false);
	}
	
	/**
	 * Overloaded constructor that allows specifying the index type as
	 * inverted or forward
	 * Every index (inverted or forward), has a key field and the value field
	 * The key field is the field on which the postings are aggregated
	 * The value field is the field whose postings we are accumulating
	 * For term index for example:
	 * 	Key: Term (or term id) - referenced by TERM INDEXFIELD
	 * 	Value: Document (or document id) - referenced by LINK INDEXFIELD
	 * @param props: The Properties file
	 * @param keyField: The index field that is the key for this index
	 * @param valueField: The index field that is the value for this index
	 * @param isForward: true if the index is a forward index, false if inverted
	 */
	public IndexWriter(Properties props, INDEXFIELD keyField, INDEXFIELD valueField, boolean isForward) {
		//TODO: Implement this method
		
		this.prop=props;
		this.fld=keyField;
		this.forward=isForward;
		if(this.fld==INDEXFIELD.LINK)
		LinkIndex=new HashMap<Integer, HashMap<Integer,Integer>>();
		else
		index=new HashMap<String, HashMap<Integer,Integer>>();
		
	}
	
	/**
	 * Method to make the writer self aware of the current partition it is handling
	 * Applicable only for distributed indexes.
	 * @param pnum: The partition number
	 */
	public void setPartitionNumber(int pnum) {
		//TODO: Optionally implement this method
		this.pnum=pnum;
	}
	
	/**
	 * Method to add a given key - value mapping to the index
	 * @param keyId: The id for the key field, pre-converted
	 * @param valueId: The id for the value field, pre-converted
	 * @param numOccurances: Number of times the value field is referenced
	 *  by the key field. Ignore if a forward index
	 * @throws IndexerException: If any exception occurs while indexing
	 */
	public void addToIndex(int keyId, int valueId, int numOccurances) throws IndexerException {
		//TODO: Implement this method
		if(this.LinkIndex.containsKey(keyId))
		{
			if(this.LinkIndex.get(keyId).containsKey(valueId))
			{
				//do nothing
			}
			else
				this.LinkIndex.get(keyId).put(valueId, numOccurances);
		}
		else
		{
			HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
			hm.put(valueId, numOccurances);
			this.LinkIndex.put(keyId, hm);
		}
		
		}
	
	/**
	 * Method to add a given key - value mapping to the index
	 * @param keyId: The id for the key field, pre-converted
	 * @param value: The value for the value field
	 * @param numOccurances: Number of times the value field is referenced
	 *  by the key field. Ignore if a forward index
	 * @throws IndexerException: If any exception occurs while indexing
	 */
	public void addToIndex(int keyId, String value, int numOccurances) throws IndexerException {
		//TODO: Implement this method
		
	}
	
	/**
	 * Method to add a given key - value mapping to the index
	 * @param key: The key for the key field
	 * @param valueId: The id for the value field, pre-converted
	 * @param numOccurances: Number of times the value field is referenced
	 *  	by the key field. Ignore if a forward index
	 * @throws IndexerException: If any exception occurs while indexing
	 */
	public void addToIndex(String key, int valueId, int numOccurances) throws IndexerException {
		//TODO: Implement this method
		
		if(this.index.containsKey(key))
		{
			if(this.index.get(key).containsKey(valueId))
			{
				//do nothing
			}
			else
				this.index.get(key).put(valueId, numOccurances);
		}
		else
		{
			HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
			hm.put(valueId, numOccurances);
			this.index.put(key, hm);
		}
		
	}
	
	/**
	 * Method to add a given key - value mapping to the index
	 * @param key: The key for the key field
	 * @param value: The value for the value field
	 * @param numOccurances: Number of times the value field is referenced
	 *  by the key field. Ignore if a forward index
	 * @throws IndexerException: If any exception occurs while indexing
	 */
	public void addToIndex(String key, String value, int numOccurances) throws IndexerException {
		//TODO: Implement this method
	}

	/* (non-Javadoc)
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#writeToDisk()
	 */
	public void writeToDisk() throws IndexerException, IOException {
		// TODO Implement this method
		String filename;
		if(this.fld==INDEXFIELD.AUTHOR)
		{	
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"AuthorIndex";
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
		    oos.writeObject(this.index);
		    oos.close();
		}
		if(this.fld==INDEXFIELD.CATEGORY)
		{	
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"CategoryIndex";
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
		    oos.writeObject(this.index);
		    oos.close();
		}
		if(this.fld==INDEXFIELD.TERM)
		{	
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"TermIndex"+pnum;
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
		    oos.writeObject(this.index);
		    oos.close();
		}
		if(this.fld==INDEXFIELD.LINK)
		{	
			filename=prop.getProperty(IndexerConstants.TEMP_DIR)+"LinkIndex";
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
		    oos.writeObject(this.LinkIndex);
		    oos.close();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see edu.buffalo.cse.ir.wikiindexer.indexer.Writeable#cleanUp()
	 */
	public void cleanUp() {
		this.index=null;
		// TODO Implement this method
		
	}

}
