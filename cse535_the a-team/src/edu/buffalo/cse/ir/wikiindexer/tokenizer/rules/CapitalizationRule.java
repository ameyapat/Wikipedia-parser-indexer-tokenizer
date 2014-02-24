package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;


import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.CAPITALIZATION)
public class CapitalizationRule implements TokenizerRule {
 
public void apply(TokenStream stream) throws TokenizerException {
if(stream!=null)
{
String token;
int i=0;
while(stream.hasNext())
{   
   i++;
token=stream.next();
if(token!=null)
{
String temp;
temp=token.toUpperCase();
if(token.equals(temp))
{
//ALL CAPS Do nothing
}
else
{
// check if 1st word of stream
if(i==1)
{token=token.toLowerCase();
   stream.previous();
   stream.set(token);
}
//checking first word of sentence
if(Character.isUpperCase(token.charAt(0)))
{


}
}
}
}
//stream.reset();
}
}

}