package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.text.Normalizer;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.ACCENTS)
public class AccentRule implements TokenizerRule{



public void apply(TokenStream stream) {
	// TODO Auto-generated method stub
	if(stream!=null)
	{
	String token;
	while(stream.hasNext())
	{
	token=stream.next();
	if(token!=null)
	{
	token = Normalizer.normalize(token, Normalizer.Form.NFD);	
	token = token.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	stream.previous();
	stream.set(token);
	stream.next();
	}
	}stream.reset();
	}
}
	
}
