
package edu.buffalo.cse.ir.wikiindexer.wikipedia;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author nikhillo
 * This class implements Wikipedia markup processing.
 * Wikipedia markup details are presented here: http://en.wikipedia.org/wiki/Help:Wiki_markup
 * It is expected that all methods marked "todo" will be implemented by students.
 * All methods are static as the class is not expected to maintain any state.
 */
public class WikipediaParser {

	static WikipediaDocument wd;
	static int isCategory=0;
    public WikipediaDocument getValues(String title, String timestamp, int id, String author, String text) throws ParseException{
	wd= new WikipediaDocument(id, timestamp, author, title);
	String section_name;
	String x;
	String[] s=text.split("(==)(.+)(==)"); //splitting sections
	int i;
	Pattern p=Pattern.compile("(^==[^=].+==)|(^===.+===$)", Pattern.MULTILINE);//detecting section heading or subsection heading
	Matcher m=p.matcher(text);
	if(text.charAt(0)!='=')
		{
			section_name="Default";
			x=parseListItem(s[0]);
				 x=parseTextFormatting(x);
				 x=parseTagFormatting(x);
				 x=parseTemplates(x);
				 x=findLink(x);
				 wd.addSection(section_name, x);
		
			i=1;
			while(m.find())
			{	if(m.group(1)==null)
				{section_name=parseSectionTitle(m.group(2).toString());
				 x=parseListItem(s[i]);
				 x=parseTextFormatting(x);
				 x=parseTagFormatting(x);
				 x=parseTemplates(x);
				 x=findLink(x);
				 wd.addSection(section_name, x);
				}
				if(m.group(2)==null){
				section_name=parseSectionTitle(m.group(1).toString());
				 x=parseListItem(s[i]);
				 x=parseTextFormatting(x);
				 x=parseTagFormatting(x);
				 x=parseTemplates(x);
				 x=findLink(x);
				 wd.addSection(section_name, x);
				}
			i++;	
			}
		} 
		else
		{
			i=0;
			while(m.find())
			{	if(m.group(1)==null)
				{section_name=parseSectionTitle(m.group(2).toString());
				 x=parseListItem(s[i]);
				 x=parseTextFormatting(x);
				 x=parseTagFormatting(x);
				 x=parseTemplates(x);
				 x=findLink(x);
				 wd.addSection(section_name, x);
				 
				 }
				if(m.group(2)==null){
				section_name=parseSectionTitle(m.group(1).toString());
				x=parseListItem(s[i]);
				x=parseTextFormatting(x);
				x=parseTagFormatting(x);
				x=parseTemplates(x);
				x=findLink(x);
				 wd.addSection(section_name, x);
					}
			i++;	
			}
		}
	return wd;
	}
	/* TODO */
	/**
	 * Method to parse section titles or headings.
	 * Refer: http://en.wikipedia.org/wiki/Help:Wiki_markup#Sections
	 * @param titleStr: The string to be parsed
	 * @return The parsed string with the markup removed
	 */
	public static String parseSectionTitle(String titleStr) {
	if(titleStr!=null&&titleStr!=""){
	String x=titleStr.replaceAll("\\s?=+\\s?","");
	
		return x;}
		else if(titleStr=="")
		return "";
		else return null;
			}
	
	/* TODO */
	/**
	 * Method to parse list items (ordered, unordered and definition lists).
	 * Refer: http://en.wikipedia.org/wiki/Help:Wiki_markup#Lists
	 * @param itemText: The string to be parsed
	 * @return The parsed string with markup removed
	 */
	public static String parseListItem(String itemText) {
	if(itemText!=""&&itemText!=null){
		itemText=itemText.replaceAll("(?m)^(;.+):(.+)","$1$2");
		itemText = itemText.replaceAll("(?m)^\\*+\\s?", "");
		itemText = itemText.replaceAll("(?m)^#+\\s?", "");
		itemText = itemText.replaceAll("(?m)^:\\s?", "");
		itemText = itemText.replaceAll("(?m)^;+\\s?", "");
		
		return itemText;}
		else if(itemText=="")
			return "";
		else return null;
	}
		
	/* TODO */
	/**
	 * Method to parse text formatting: bold and italics.
	 * Refer: http://en.wikipedia.org/wiki/Help:Wiki_markup#Text_formatting first point
	 * @param text: The text to be parsed
	 * @return The parsed text with the markup removed
	 */
	public static String parseTextFormatting(String text) {
		if(text!=null&&text!=""){
		String a1=text.replace("'''''",""); //removing bold-italic markup
		a1=a1.replace("'''",""); //removing bold only markup
		a1=a1.replace("''","");	//removing italic only markup
		return a1;}
		else if(text=="")
		return "";
		else return null;
	}
	
	/* TODO */
	/**
	 * Method to parse *any* HTML style tags like: <xyz ...> </xyz>
	 * For most cases, simply removing the tags should work.
	 * @param text: The text to be parsed
	 * @return The parsed text with the markup removed.
	 */
	public static String parseTagFormatting(String text) {
		if(text!=null&&text!="")	
		{
		text=text.replaceAll("(\\s</?[^>]*>)","");
		text=text.replaceAll("(</?[^>]*>\\s)","");
		text=text.replaceAll("(</?[^>]*>)","");
		text=text.replaceAll("&lt;.*&gt;(.*)&lt;.*&gt;","$1");
		return text;
		}
			else if(text=="")
			return "";
			else return null;
		}

	
	/* TODO */
	/**
	 * Method to parse wikipedia templates. These are *any* {{xyz}} tags
	 * For most cases, simply removing the tags should work.
	 * @param text: The text to be parsed
	 * @return The parsed text with the markup removed
	 */
	public static String parseTemplates(String text) {
	if(text!=""&&text!=null){
	text=text.replaceAll("(?s)(?m)^\\{\\{.*\\}\\}$","");
	text=text.replaceAll("\\{\\{.*[^\\{]*\\}\\}","");
	if(text.contains("{|"))
	text=text.replaceAll("(?s)\\{\\|.*\\|\\}","");
		return text;}
		else if(text=="")
		return "";
		else return null;
	}
	
public static String findLink(String text){
		
		Pattern p2=Pattern.compile("([^\\n]*?\\[\\[?[^\\[]*\\]?\\])");
		Matcher m2=p2.matcher(text);
		String[]x1=new String[2];
		StringBuilder sb=new StringBuilder(100);
		Collection<String> links=new ArrayList<String>();
		Collection<String> cat=new ArrayList<String>();
		while(m2.find())
		{	
			x1=parseLinks(m2.group(1).toString());
			sb.append(x1[0]+" ");
			if(isCategory==1)
			{ //System.out.println("print:"+x1[0]);
			cat.add(x1[0]);
			isCategory=0;
			}
			if(x1[1]!=""&&x1[1]!=null)
			links.add(x1[1]);
		}
		wd.addCategories(cat);
		wd.addLInks(links);
		return sb.toString();
	}

	
	
	
	/* TODO */
	/**
	 * Method to parse links and URLs.
	 * Refer: http://en.wikipedia.org/wiki/Help:Wiki_markup#Links_and_URLs
	 * @param text: The text to be parsed
	 * @return An array containing two elements as follows - 
	 *  The 0th element is the parsed text as visible to the user on the page
	 *  The 1st element is the link url
	 */
	public static String[] parseLinks(String text) {
		String[] x=new String[2];
	if(text!=null&text!=""){
		
	
		if(text.contains("[in]"))
		{
		x[0]=text.replace("[in]", "");
		x[1]="";
		return x;
		}
		
		String a=text;
		
		
		Matcher mx4=Pattern.compile("[^\\[]?(\\[http([^\\[]*)\\])").matcher(text);     //external links
		while(mx4.find())
		{	//System.out.println("Found"+mx4.group(1));
			//System.out.println("external");
			//System.out.println("link:" +mx4.group(1));
			x[1]="";
			if(mx4.group(2).contains(" "))
			{
				if(mx4.group(2).matches("(?m)^\\s+$"))
						{
					x[0]=a.replace(mx4.group(1), "");
					return x;
						}
				else{
				String y[]=mx4.group(2).split(" ");
			String xy=mx4.group(2).replace(y[0]+" ","");
			x[0]=a.replace(mx4.group(1), xy);
			//System.out.println("X[0]--->"+x[0]);
			return x;}}
		else {//System.out.println("in else");
			x[0]="";
			x[1]="";
			return x;}
		}
		
		
		if(text.matches("[^\\[]*\\[[^\\[]+\\].*"))
		{	//System.out.println("yay!");
			x[0]=text.replaceAll("\\[(.*)\\]","$1");
			x[1]="";
			//System.out.println(x[0]);
			return x;
		}
		
		
		
		
			if(!a.contains("|")) //[[Texas]] or [[wikipedia:Hello]] or [[wikipedia:br:bonjour]]
			{	
				
				boolean flag_colon=false;
			
			
			
			
			Matcher mx1=Pattern.compile(".*?(\\[\\[Category:(.*?)\\]\\].*\\n?)").matcher(text); //categories1
			while(mx1.find())
			{	
				x[1]="";
				x[0]=a.replace(mx1.group(1), mx1.group(2));
				//System.out.println("x[0]"+x[0]);
				isCategory=1;
				return x;
			}
			
			Matcher mx2=Pattern.compile(".*(\\[\\[:(Category:.*?)\\]\\]).*?").matcher(text); //categories 2
			while(mx2.find())
			{
				x[1]="";
				x[0]=a.replace(mx2.group(1), mx2.group(2));
				return x;
			}
			
			Matcher mx3=Pattern.compile(".*(\\[\\[(\\w{2}:.*?)\\]\\]).*?").matcher(text); //Language links
			while(mx3.find())
			{
				x[1]="";
				x[0]=a.replace(mx3.group(1), mx3.group(2));
				return x;
			}
			
			Matcher mx=Pattern.compile(".*(\\[\\[(.*?)\\]\\])([^\\s].*?)").matcher(text); //blending
			while(mx.find())
			{	
				x[0]=a.replace("[[","");
				x[0]=x[0].replace("]]","");
				x[1]=mx.group(2).replace(" ", "_");
				char[] c=x[1].toCharArray();
				c[0] = Character.toUpperCase(c[0]);
				x[1]=new String(c);
				x[0]=x[0].replaceAll("<.*>", "");
				return x;
			}
			
			
				Matcher m=Pattern.compile(".*(\\[\\[(.*?)\\]\\])\\s?.*?").matcher(text);
				while(m.find())
				{	
					if(m.group(2).contains(":"))
					flag_colon=true;
					if(flag_colon&&!m.group(2).contains(".")) //[[wikipedia:hello]]
					{	
						x[1]="";
						x[0]=a.replace(m.group(1),m.group(2));
						return x;
					}
					if(flag_colon&&m.group(2).contains("."))  //file.png
					{	
						x[0]="";
						x[1]="";
						return x; 
					}
					else{										//[[texas]]
						x[0]=a.replace(m.group(1),m.group(2));
						x[1]=m.group(2).replace(" ", "_");
						char[] c=x[1].toCharArray();
						c[0] = Character.toUpperCase(c[0]);
						x[1]=new String(c);
						if(m.group(2).contains("#"))
						{
							x[1]="";
						}
						
						return x;
						}
					}
				}
				
			if(!a.contains("|]")&&a.contains("|")) //[[Texas|Lone Star State]]
			{	//System.out.println("bernhard!!!");
				Matcher m=Pattern.compile(".*(\\[\\[(.*\\|.*)\\]\\])\\s?.*?").matcher(text);
				while(m.find())
				{	
					//System.out.println(m.group(2));
					String[] s=m.group(2).split("\\|");
					if(!s[0].contains(":"))
					{
						//System.out.println("len->"+s.length);
					x[0]=a.replace(m.group(1),s[1]);
					x[1]=s[0];
					x[1]=x[1].replace(" ", "_");
					char[] c=x[1].toCharArray();
					c[0] = Character.toUpperCase(c[0]);
					x[1]=new String(c);
					}
					if(s[0].contains(":"))
					{
						x[1]="";
						x[0]=a.replace(m.group(1),s[s.length-1]);
					}
					if(m.group(2).contains("#"))
						x[1]="";
					return x;
					 
				}
			}
			if(a.contains("|]")) 
			{	
			
				Matcher m=Pattern.compile(".*(\\[\\[(.*)\\|\\]\\])\\s?.*?").matcher(text);
				while(m.find())
					{	
					
					if(m.group(2).contains(":")&&!m.group(2).contains("(")) //[[wikipedia:Hello|]]
					{	x[1]="";
						String[] y=m.group(2).split(":");
						if(y.length>2&&!m.group(2).contains("#"))					//  [[Wiktionary:fr:bonjour|]]
						{ String xy=m.group(2).replace(y[0]+":","");
						x[0]=a.replace(m.group(1), xy);
						}
						if(y.length==2&&!m.group(2).contains("#")) x[0]=a.replace(m.group(1), y[1]);
						if(m.group(2).contains("#"))
						x[0]=a.replace(m.group(1), m.group(2));
							
						return x;
					}
					if(m.group(2).contains("(")&&!m.group(2).contains(",")&&m.group(2).contains(":"))
					{
						x[1]="";
						String[]y=m.group(2).split(":");
						y[1]=y[1].replaceAll("\\s?\\(.*\\)","");
						x[0]=a.replace(m.group(1), y[1]);
						return x;
					}
					
					
					if(m.group(2).contains("(")&&!m.group(2).contains(",")&&!m.group(2).contains(":")) //[[kingdom (biology)|]]
					{	
						String y=m.group(2).replaceAll("\\s?\\(.*\\)","");
						x[0]=a.replace(m.group(1),y);
						x[1]=m.group(2).replace(" ","_");
						char[] c=x[1].toCharArray();
						c[0] = Character.toUpperCase(c[0]);
						x[1]=new String(c);
						return x;
					}
					if(m.group(1).contains(",")&&!m.group(1).contains("("))
					{
						String[] y1=m.group(2).split(", ");
						x[0]=a.replace(m.group(1),y1[0]);
						x[1]=m.group(2).replace(" ", "_");
						char[] c=x[1].toCharArray();
						c[0] = Character.toUpperCase(c[0]);
						x[1]=new String(c);
						return x;
					}
					if(m.group(1).contains(",")&&m.group(1).contains("("))
					{	
						String a1=m.group(2).replaceAll("\\s*\\(.*\\)", "");
						x[0]=a.replace(m.group(1),a1);
						x[1]=m.group(2).replace(" ","_");
						char[] c=x[1].toCharArray();
						c[0] = Character.toUpperCase(c[0]);
						x[1]=new String(c);
						return x;
						
					}
				}
			}
			x[0]=text;
			x[1]="";
			return x;
			
		}

	else {
		x[0]="";
		x[1]="";
		return x;
	}
		
	}
}
