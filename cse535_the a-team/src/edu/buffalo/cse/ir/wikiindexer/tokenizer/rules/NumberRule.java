package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.NUMBERS)
public class NumberRule implements TokenizerRule {
 
public void apply(TokenStream stream) throws TokenizerException {
    if(stream!=null)
{
String token;
while(stream.hasNext())
{
token=stream.next();
            if(token!=null)
        {Matcher mx=Pattern.compile("(?m)^[0-9]{8}$").matcher(token);
        if(mx.find())
        {
        	//do nothing
        }
        else{
        Matcher m1=Pattern.compile("\\d+").matcher(token);
            if(m1.matches())
            {
            token=m1.replaceAll("");
            stream.previous();
            stream.set(token);
            }
            else
            {
        Pattern p=Pattern.compile("([0-9].*)([^0-9].*)");
             Matcher m=p.matcher(token);
             while(m.find())
             {
             token=m.replaceAll("$2");
             token=token.replaceAll("[0-9.,]","");
             stream.previous();
             stream.set(token);
             }
             
            }
     	
        }
        
}}
stream.reset();
while(stream.hasNext())
     	   if(stream.next().isEmpty()){
     	   	stream.previous();
     	   	stream.remove();}
stream.reset();
     	
}
}
}
