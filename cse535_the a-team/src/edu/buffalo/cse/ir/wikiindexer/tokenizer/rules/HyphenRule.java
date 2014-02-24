/**
 * 
 */
package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RuleClass(className = RULENAMES.HYPHEN)
public class HyphenRule implements TokenizerRule {
	

	public void apply(TokenStream stream) throws TokenizerException{
		if(stream!=null){
		String token;
		while(stream.hasNext())
		{
		token=stream.next();	//read next token
		if(token!=null&&token.contains("-"))
		{
		Matcher m=Pattern.compile("^\\s+-+\\s+$",Pattern.MULTILINE).matcher(token);
		if(m.find()){
		stream.previous();
		stream.remove();}
		else
		{
		Matcher m1=Pattern.compile("^\\s*([a-zA-Z]+(-+)\\s?[a-zA-Z]+)\\s*$",Pattern.MULTILINE).matcher(token);
		Matcher m2=Pattern.compile("^([\\w\\d]+-+)$|^(-+[\\w\\d]+)$",Pattern.MULTILINE).matcher(token);
		if(m1.find())
		{
		token=token.replace(m1.group(2), " ");
		stream.previous();
		stream.set(token);
		}
		if(m2.find())
		{
		if(m2.group(1)!=null)
		{
		token=token.replaceAll("-+","");
		stream.previous();
		stream.set(token);
		}
		if(m2.group(2)!=null)
		{token=token.replaceAll("-+","");
		stream.previous();
		stream.set(token);
		}
		}
		}
		}
		}stream.reset();
		}}

}