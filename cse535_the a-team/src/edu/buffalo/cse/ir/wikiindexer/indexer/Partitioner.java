/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.indexer;

/**
 * @author nikhillo
 * THis class is responsible for assigning a partition to a given term.
 * The static methods imply that all instances of this class should 
 * behave exactly the same. Given a term, irrespective of what instance
 * is called, the same partition number should be assigned to it.
 */
public class Partitioner {
	/**
	 * Method to get the total number of partitions
	 * THis is a pure design choice on how many partitions you need
	 * and also how they are assigned.
	 * @return: Total number of partitions
	 */
	public static int getNumPartitions() {
		//TODO: Implement this method
		return 14;
	}
	
	/**
	 * Method to fetch the partition number for the given term.
	 * The partition numbers should be assigned from 0 to N-1
	 * where N is the total number of partitions.
	 * @param term: The term to be looked up
	 * @return The assigned partition number for the given term
	 */
	public static int getPartitionNumber (String term) {
		//TDOD: Implement this method
		if(term!=null&&term!="")
		{
			switch(term.toLowerCase().charAt(0))
			{
			case 'a':
			case 'b':
				return 0;
			case 'c':
			case 'd':
				return 1;
			case 'e':
			case 'f':
				return 2;
			case 'g':
			case 'h':
				return 3;
			case 'i':
			case 'j':
				return 4;
			case 'k':
			case 'l':
				return 5;
			case 'm':
			case 'n':
				return 6;
			case 'o':
			case 'p':
				return 7;
			case 'q':
			case 'r':
				return 8;
			case 's':
			case 't':
				return 9;
			case 'u':
			case 'v':
				return 10;
			case 'w':
			case 'x':
				return 11;
			case 'y':
			case 'z':
				return 12;
			default: return 13;
			}
		}
		else return -1;
	}
}
