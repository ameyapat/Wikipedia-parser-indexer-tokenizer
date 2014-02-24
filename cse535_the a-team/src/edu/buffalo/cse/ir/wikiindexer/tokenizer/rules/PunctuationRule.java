package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.PUNCTUATION)
public class PunctuationRule implements TokenizerRule {
 
public void apply(TokenStream stream) throws TokenizerException {
if(stream!=null)
{
String token;
while(stream.hasNext())
{
token=stream.next();
if(token!=null)
{
Pattern rep = Pattern.compile("(\\w+)(\\.|!|\\?)+(\\s+)"); 
   Matcher mat = rep.matcher(token);
  if(mat.find())
  { 
   token=mat.replaceAll("$1$3");
   stream.previous();
   stream.set(token);
  }
  
   Pattern rep1 = Pattern.compile("(\\.|\\?|!)+$");
   Matcher mat1 = rep1.matcher(token);
  if(mat1.find())
  {
   token=mat1.replaceAll("");
  stream.previous();
  stream.set(token);
  }
}
}
stream.reset();
}
}
}