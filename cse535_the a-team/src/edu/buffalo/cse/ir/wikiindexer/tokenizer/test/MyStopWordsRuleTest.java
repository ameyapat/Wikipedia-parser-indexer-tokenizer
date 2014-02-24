package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.StopWordsRule;

public class MyStopWordsRuleTest {
	private StopWordsRule rule;
	@Test
	public void test() {
			rule=new StopWordsRule();
			try {

//					assertArrayEquals(new Object[]{"test"}, 
//							runtest("this is a test"));			//this is a
//					assertArrayEquals(new Object[]{}, 
//							runtest("do not do this"));			//do not
//					assertArrayEquals(new Object[]{"ace spades"}, //of
//							runtest("ace of spades"));
//					assertArrayEquals(new Object[]{"valid sentence"}, 
//							runtest("valid sentence"));
					assertArrayEquals(new Object[]{"test"}, 
							runtest("this","is","a","test"));
					assertArrayEquals(new Object[]{}, 
						runtest("do","not","do","this"));
					assertArrayEquals(new Object[]{"ace","spades"}, 
							runtest("ace","of","spades"));
					assertArrayEquals(new Object[]{"valid","sentence"}, 
							runtest("valid","sentence"));
					

				
			} catch (TokenizerException e) {
				
			}
		
	}
	protected Object[] runtest(String... input) throws TokenizerException {
		TokenStream stream = new TokenStream(input[0]);
		if (input.length > 1) {
			stream.append(Arrays.copyOfRange(input, 1, input.length));
		}
		
		rule.apply(stream);
		Collection<String> strtokens = stream.getAllTokens();
		return (strtokens != null) ? strtokens.toArray() : null;
	}
}
