/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.parsers;

import java.text.ParseException;
import java.util.Collection;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.buffalo.cse.ir.wikiindexer.FileUtil;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaDocument;
import edu.buffalo.cse.ir.wikiindexer.wikipedia.WikipediaParser;

/**
 * @author nikhillo
 *
 */
public class Parser {
	/* */
	private final Properties props;
	private Collection<WikipediaDocument> d;
	/**
	 * 
	 * @param idxConfig
	 * @param parser
	 */
	public Parser(Properties idxProps) {
		props = idxProps;
	}
	
	/* TODO: Implement this method */
	/**
	 * 
	 * @param filename
	 * @param docs
	 */
	public void parse(String filename, Collection<WikipediaDocument> docs) {
		
		d=docs;
		if(filename!=null&&filename!=""&&filename.contains(FileUtil.getRootFilesFolder(props)))
		{
			try {
				 
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
			 
				DefaultHandler handler = new DefaultHandler() {
			 
					boolean flag_title = false;
					boolean flag_author = false;
					boolean flag_timestamp = false;
					boolean flag_id = false;
					boolean flag_text=false;
					boolean flag_page=false;
					private StringBuilder characters = new StringBuilder(100);
					private StringBuilder ichar=new StringBuilder(25);
					private StringBuilder tchar=new StringBuilder(25);
					private StringBuilder timechar=new StringBuilder(25);
					private StringBuilder achar;
					String text;
					String title;
					String id;
					String timestamp;
					String author;
					boolean flag_revision=false;
					
				
					public void startElement(String uri, String localName,String qName, 
			                Attributes attributes) throws SAXException {
		
						if(qName.equalsIgnoreCase("page"))
						flag_page= true;
						
						if(qName.equalsIgnoreCase("username"))
						flag_author= true;
						
						if(qName.equalsIgnoreCase("ip"))
						flag_author = true;

						if(qName.equalsIgnoreCase("title"))
						flag_title=true;
						
						if(qName.equalsIgnoreCase("id")&&!flag_revision){
						achar=new StringBuilder(25);
						flag_id= true;}

						if(qName.equalsIgnoreCase("timestamp"))
						flag_timestamp = true;
						
						if(qName.equalsIgnoreCase("text"))
						flag_text = true;
						
						if(qName.equalsIgnoreCase("revision")){
						id=new String(ichar);
						ichar.setLength(0);
							flag_revision=true;}
			 
				}
				
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					
					if(qName.equalsIgnoreCase("revision")){
						flag_revision = false;}
					
						if(qName.equalsIgnoreCase("page"))
						{ 	WikipediaParser wp=new WikipediaParser();
							try {
								WikipediaDocument wd=wp.getValues(title,timestamp,Integer.parseInt(id),author,text);
								//System.out.println(wd.getAuthor());
								add(wd,d);
							} catch (NumberFormatException e) {
								System.out.println("number format exception");
								e.printStackTrace();
							} catch (ParseException e) {
								System.out.println("parse exception");
								e.printStackTrace();
							}
							
							flag_page=false;
						}	
						
						if(qName.equalsIgnoreCase("title")){
						title=tchar.toString();
						tchar.setLength(0);
							flag_title=false;}
						
						
						if(qName.equalsIgnoreCase("id")){
							flag_id= false;}

						if(qName.equalsIgnoreCase("timestamp")){
						timestamp=new String(timechar);
						timechar.setLength(0);
							flag_timestamp = false;}

						if(qName.equalsIgnoreCase("ip"))
						{
							author=new String(achar);
							achar.setLength(0);
							flag_author = false;
						}
						
						if(qName.equalsIgnoreCase("username"))
						{
							author=new String(achar);
							achar.setLength(0);
							flag_author = false;
						}
					
						if(qName.equalsIgnoreCase("text"))
						{
							text = characters.toString();
							characters.setLength(0);
							flag_text = false;
						}
						
					}
			 
				public void characters(char ch[], int start, int length) throws SAXException {

					if(flag_title)
					tchar.append(ch,start,length);
					
					if(flag_id)
					ichar.append(ch,start,length);
					
					if(flag_timestamp)
					timechar.append(ch,start,length);

					if(flag_author)
					achar.append(ch,start,length);
					
					if(flag_text)
					characters.append(new String(ch,start,length));
					
				}
			 
			     };
			 
			       saxParser.parse(filename, handler);
			     //saxParser.parse("C:\\five_entries.xml", handler);
			     } catch (Exception e) {
			       e.printStackTrace();
			     }
			 
			   }

			    }
		
		/**
		 * Method to add the given document to the collection.
		 * PLEASE USE THIS METHOD TO POPULATE THE COLLECTION AS YOU PARSE DOCUMENTS
		 * For better performance, add the document to the collection only after
		 * you have completely populated it, i.e., parsing is complete for that document.
		 * @param doc: The WikipediaDocument to be added
		 * @param documents: The collection of WikipediaDocuments to be added to
		 */
		private synchronized void add(WikipediaDocument doc, Collection<WikipediaDocument> documents) {
			documents.add(doc);
		}
}
