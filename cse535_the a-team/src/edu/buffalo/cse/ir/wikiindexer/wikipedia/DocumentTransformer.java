/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.wikipedia;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import edu.buffalo.cse.ir.wikiindexer.indexer.INDEXFIELD;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.Tokenizer;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaDocument.Section;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaDocument;

/**
 * A Callable document transformer that converts the given WikipediaDocument object
 * into an IndexableDocument object using the given Tokenizer
 * @author nikhillo
 *
 */
public class DocumentTransformer implements Callable<IndexableDocument> {
	
	Map<INDEXFIELD, Tokenizer> tm;
	WikipediaDocument d;
	/**
	 * Default constructor, DO NOT change
	 * @param tknizerMap: A map mapping a fully initialized tokenizer to a given field type
	 * @param doc: The WikipediaDocument to be processed
	 */
	public DocumentTransformer(Map<INDEXFIELD, Tokenizer> tknizerMap, WikipediaDocument doc) {
		//TODO: Implement this method
		this.tm=tknizerMap;
		this.d=doc;
	}
	
	/**
	 * Method to trigger the transformation
	 * @throws TokenizerException Inc ase any tokenization error occurs
	 */
	public IndexableDocument call() throws TokenizerException {
		// TODO Implement this method
		
		List<Section>sect=this.d.getSections();
		String s="";
		for(int i=0;i<sect.size();i++)
		{
			Section x=sect.get(i);
			s=s+x.getTitle()+" "+x.getText()+" ";
		}
		
		TokenStream t1=new TokenStream(s);
		
		TokenStream t2=new TokenStream(this.d.getAuthor());
		TokenStream t3=new TokenStream(this.d.getLinks());
		TokenStream t4=new TokenStream(this.d.getCategories());
		
		for(Entry<INDEXFIELD, Tokenizer> e:this.tm.entrySet())
		{
			if(e.getKey()==INDEXFIELD.TERM)
			{
				
				e.getValue().tokenize(t1);
			}
		if(e.getKey()==INDEXFIELD.AUTHOR)
			e.getValue().tokenize(t2);
		if(e.getKey()==INDEXFIELD.LINK)
			e.getValue().tokenize(t3);
		if(e.getKey()==INDEXFIELD.CATEGORY)
			e.getValue().tokenize(t4);
		}
		
		IndexableDocument id=new IndexableDocument();
		id.addField(INDEXFIELD.TERM, t1);
		id.addField(INDEXFIELD.AUTHOR, t2);
		id.addField(INDEXFIELD.LINK, t3);
		id.addField(INDEXFIELD.CATEGORY, t4);
		id.setDocumentIdentifier(d.getTitle());
		return id;
	}
	
}
