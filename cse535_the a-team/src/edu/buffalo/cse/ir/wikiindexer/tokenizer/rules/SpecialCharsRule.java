package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.SPECIALCHARS)
public class SpecialCharsRule implements TokenizerRule {

public void apply(TokenStream stream) throws TokenizerException {

if(stream!=null)
{
String token;
while(stream.hasNext())
{
token=stream.next();
        if(token!=null)
        {
 token=token.replaceAll("(?m)^\\-",""); //not working!!
 if(token.contains("-"))
 token=token.replaceAll("(.*[^0-9][a-zA-z]*)\\-([a-zA-Z]*[^0-9].*)","$1#$2");  //a-b
 
Matcher m1=Pattern.compile("\\w+([^\\-\\w\\.])\\w+").matcher(token);  //any symbol except "." and "-" 
String[] tok=null;
if(m1.find())
{
tok=token.split("[^\\dA-Za-z\\.\\-]");
stream.previous();
stream.set(tok);
        }
        else
{
        Pattern p=Pattern.compile("[^\\dA-Za-z\\.\\- ]");
            Matcher m=p.matcher(token);
            if(m.find()){
            token=token.replaceAll("[^\\dA-Za-z\\.\\- ]", "");
            stream.previous();
                stream.set(token);
              
}
else{
//do nothing
                }

 }
}
}
stream.reset();
while(stream.hasNext())
   if(stream.next().isEmpty()){
   	stream.previous();
   	stream.remove();}
}
stream.reset();
}

} 