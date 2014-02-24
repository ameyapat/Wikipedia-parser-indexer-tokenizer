/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.tokenizer;
import java.util.*;

/**
 * This class represents a stream of tokens as the name suggests.
 * It wraps the token stream and provides utility methods to manipulate it
 * @author nikhillo
 *
 */
public class TokenStream implements Iterator<String> {
/**
* This class represents a stream of tokens as the name suggests.
* It wraps the token stream and provides utility methods to manipulate it
* @author nikhillo
*
*/
List<String> a=new ArrayList<String>();
ListIterator<String> x=a.listIterator();
/**
* Default constructor
* @param bldr: THe stringbuilder to seed the stream
*/
public TokenStream(StringBuilder bldr) {
	//TODO: Implement this method
	if(bldr.toString()!=null&&bldr.toString()!="")
	{a.add(bldr.toString());    //this
	this.x=a.listIterator();
	}}
/**
* Overloaded constructor
* @param bldr: THe stringbuilder to seed the stream
*/
public TokenStream(String string) {
	//TODO: Implement this method
	if(string!=null&&string!="")
	{a.add(string);
	this.x=a.listIterator();
	}
	if(string==null||string=="")
	this.a=null;
}


public TokenStream(Collection c) {
	if(c!=null)
	{a.addAll(c);    //this
	this.x=a.listIterator();
	}}
/**
* Method to append tokens to the stream
* @param tokens: The tokens to be appended
*/
public void append(String... tokens) {
//TODO: Implement this method
if(tokens==null)
{}
else{
for(int i=0;i<tokens.length;i++)
{if(tokens[i]!=""&&tokens[i]!=null)
a.add(tokens[i]);
}}
this.x=a.listIterator();
}
/**
* Method to retrieve a map of token to count mapping
* This map should contain the unique set of tokens as keys
* The values should be the number of occurrences of the token in the given stream
* @return The map as described above, no restrictions on ordering applicable
*/
public Map<String, Integer> getTokenMap() {
//TODO: Implement this method
if(this.a!=null){
if(this.a.size()!=0){
Iterator<String> x = this.a.iterator();
Map<String, Integer> m=new HashMap();
     while(x.hasNext())
     {	String s=x.next().toString();
   	 m.put(s, Collections.frequency(this.a,s));
     }
return m;
} else return null;
} else return null; }
/**
* Method to get the underlying token stream as a collection of tokens
* @return A collection containing the ordered tokens as wrapped by this stream
* Each token must be a separate element within the collection.
* Operations on the returned collection should NOT affect the token stream
*/
public Collection<String> getAllTokens() {
	//TODO: Implement this method
	if(this.a!=null){
	Collection<String> ab=new ArrayList<String>();
	if(this.a.size()!=0)
	return this.a;
	else return ab;
	}else return null;}
/**
* Method to query for the given token within the stream
* @param token: The token to be queried
* @return: THe number of times it occurs within the stream, 0 if not found
*/
public int query(String token) {
//TODO: Implement this method
if(this.a==null)
return 0;
else{
if(this.a.contains(token))
{
return Collections.frequency(this.a, token)	;	}
else return 0;
}}
/**
* Iterator method: Method to check if the stream has any more tokens
* @return true if a token exists to iterate over, false otherwise
*/
public boolean hasNext() {
	// TODO: Implement this method
	if(this.x.hasNext()){
	this.x.next();
	this.x.previous();
	return true;}
	else return false;
	}
/**
* Iterator method: Method to check if the stream has any more tokens
* @return true if a token exists to iterate over, false otherwise
*/
public boolean hasPrevious() {
	//TODO: Implement this method
	if(this.x.hasPrevious()){
	x.previous();
	x.next();
	return true;}
	else return false;
	}
/**
* Iterator method: Method to get the next token from the stream
* Callers must call the set method to modify the token, changing the value
* of the token returned by this method must not alter the stream
* @return The next token from the stream, null if at the end
*/
public String next() {
// TODO: Implement this method
if(this.x.hasNext())
{
return x.next().toString();
}
else return null;
}
/**
* Iterator method: Method to get the previous token from the stream
* Callers must call the set method to modify the token, changing the value
* of the token returned by this method must not alter the stream
* @return The next token from the stream, null if at the end
*/
public String previous() {
//TODO: Implement this method
if(this.x.hasPrevious())
{
return x.previous().toString();
}
else return null;
}
/**
* Iterator method: Method to remove the current token from the stream
*/
public void remove() {
if(this.a!=null&&this.x.hasNext())   //trying something
{
x.next();
x.remove();
}
}
/**
* Method to merge the current token with the next token, assumes whitespace
* separator between tokens when merged. The token iterator should now point
* to the newly merged token (i.e. the current one)
* @return true if the merge succeeded, false otherwise
*/
public boolean mergeWithNext() {
//TODO: Implement this method
if(this.hasNext()&&this.a.size()!=0)
{
String temp=this.next()+" ";  //+this.next();
if(this.hasNext()){
temp=temp+this.next();
this.previous();
this.remove();
this.previous();
this.remove();
x.add(temp);
this.previous();
return true;}
else return false;
}
else
return false;
}
/**
* Method to merge the current token with the previous token, assumes whitespace
* separator between tokens when merged. The token iterator should now point
* to the newly merged token (i.e. the previous one)
* @return true if the merge succeeded, false otherwise
*/
public boolean mergeWithPrevious() {
//TODO: Implement this method
	if(this.hasPrevious()&&this.hasNext())
	{
		String temp=this.next();  
		this.previous();
		temp=this.previous()+" "+temp;
		this.next();
		this.next();
		this.previous();
		this.remove();
		this.previous();
		this.remove();
		x.add(temp);
		this.previous();
	return true;
	}
	else
	return false;
	}
/**
* Method to replace the current token with the given tokens
* The stream should be manipulated accordingly based upon the number of tokens set
* It is expected that remove will be called to delete a token instead of passing
* null or an empty string here.
* The iterator should point to the last set token, i.e, last token in the passed array.
* @param newValue: The array of new values with every new token as a separate element within the array
*/
public void set(String... newValue) {
	//TODO: Implement this method
	if(this.a!=null){
	boolean flag=false;
	for(int j=0;j<newValue.length;j++)
	{
		if(newValue[j]==""||newValue[j]==null)
			flag=true;
	}
	if(this.a.size()!=0&&newValue!=null&&flag==false&&this.x.hasNext()){
	int i=0;
	this.x.next();
		
	this.x.remove();
	for(i=0;i<newValue.length;i++)
	{ this.x.add(newValue[i]);}
	this.x.previous();
	}
	}}
/**
* Iterator method: Method to reset the iterator to the start of the stream
* next must be called to get a token
*/
public void reset() {
//TODO: Implement this method
	if(this.a!=null)
	this.x=this.a.listIterator();
}
/**
* Iterator method: Method to set the iterator to beyond the last token in the stream
* previous must be called to get a token
*/
public void seekEnd() {
while(this.x.hasNext())
x.next();
}
/**
* Method to merge this stream with another stream
* @param other: The stream to be merged
*/
public void merge(TokenStream other) {
if(other!=null&&other.a!=null){
if(this.a==null)
a=new ArrayList<String>();
this.a.addAll(other.a);
this.x=this.a.listIterator();
}}

}
