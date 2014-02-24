package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.APOSTROPHE)
public class ApostropheRule implements TokenizerRule {
 
public void apply(TokenStream stream) throws TokenizerException {
    if(stream!=null)
{
String token;
while(stream.hasNext())
{
token=stream.next();
  
        if(token!=null)
        {
               	
        String arr[]={"isn't","don't","won't","shan't","let's","I'm","we're","they're","I've","Should've","They'd","She'll","'em"};
            String arr1[]={"is not","do not","will not","shall not","let us","I am","we are","they are","I have","Should have","They would","She will","them"}; 
            int i=0;
             String temp[];
             for(i=0;i<arr.length;i++)
             {
        if(token.equalsIgnoreCase(arr[i]))
        {
        token=arr1[i];
        temp=token.split("\\s");
        stream.previous();
        stream.set(temp);
        } 
                                     
             }
            
             if(token.endsWith("'s"))
         	{
         	token=token.replace("'s", "");
         	stream.previous();
         	stream.set(token);
         	}
         	if(token.contains("s' "))
         	{
         
         	token=token.replace("'", "");
         	
         	stream.previous();
         	stream.set(token);
         	}
             	
        Matcher m1=Pattern.compile("(\\w+[^s])*(')(\\w+[^s])*").matcher(token);
if(m1.find())
{
token=m1.replaceAll("$1 $3");
token=token.trim();
token=token.replaceAll("\\s+", "");
stream.previous();
stream.set(token);
 
}
             
 
          
}
}
}
stream.reset();
while(stream.hasNext())
   if(stream.next().isEmpty())
   {
   	stream.previous();
   	stream.remove();
   	}
stream.reset();
}
}



