package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.RuleClass;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.WHITESPACE)
public class WhitespaceRule implements TokenizerRule {
 
public void apply(TokenStream stream) throws TokenizerException 
{
if(stream!=null)
{
String token1;
while(stream.hasNext())
{
token1=stream.next();
if(token1!=null)
{
Pattern p=Pattern.compile("\\s+");
       	Matcher m=p.matcher(token1);
       	String tok[]=null;
       	if(m.find())
       	  {
       	token1=token1.trim();
       	tok=token1.split("\\s+");
       	
       	
       	stream.previous();
       	stream.set(tok);
           
           
       }
     }
     } stream.reset();
      }

	
}
}
