package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;


@RuleClass(className = RULENAMES.ACCENTS)
public class DelimRule implements TokenizerRule{
	String x;
	public DelimRule(String x)
	{
		x=x;
	}
	
	public void apply(TokenStream stream) {
	
	if(stream!=null)
	{
	String token;
	while(stream.hasNext())
	{
	token=stream.next();
	if(token!=null)
	{
		String arr[]=token.split(x);
        stream.previous();
        stream.set(arr);
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
