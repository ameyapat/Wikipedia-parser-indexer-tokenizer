package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.ApostropheRule;

public class MyApostropheRuleTest {

	private ApostropheRule rule;
	@Test
	public void test() {
		rule=new ApostropheRule();
		try {
			
		/*		//basic rules
				assertArrayEquals(new Object[]{"Finland"}, runtest("Finland's"));
				assertArrayEquals(new Object[]{"Gladys house"}, runtest("Gladys' house"));
	
				//contractions
				assertArrayEquals(new Object[]{"is","not"}, runtest("isn't"));
				assertArrayEquals(new Object[]{"do","not"}, runtest("don't"));
				assertArrayEquals(new Object[]{"will","not"}, runtest("won't"));
				assertArrayEquals(new Object[]{"shall","not"}, runtest("shan't"));
				assertArrayEquals(new Object[]{"let","us"}, runtest("let's"));
				assertArrayEquals(new Object[]{"I","am"}, runtest("I'm"));
				assertArrayEquals(new Object[]{"we","are"}, runtest("we're"));
				assertArrayEquals(new Object[]{"they","are"}, runtest("they're"));
				assertArrayEquals(new Object[]{"I","have"}, runtest("I've"));
				assertArrayEquals(new Object[]{"Should","have"}, runtest("Should've"));
				assertArrayEquals(new Object[]{"They","would"}, runtest("They'd"));
				assertArrayEquals(new Object[]{"She","will"}, runtest("She'll"));
				assertArrayEquals(new Object[]{"Put","them"}, runtest("Put 'em"));
				
				//as single quotes
				assertArrayEquals(new Object[]{"quote","test"}, runtest("'quote","test'"));
				assertArrayEquals(new Object[]{"f(x)","=","df/dx"},runtest("f'(x)","=","df/dx"));
				assertArrayEquals(new Object[]{"f(x)","=","df/dx" }, runtest("f''(x)","=","df'/dx"));
			*/
				//basic rules
				assertArrayEquals(new Object[]{"Finland"}, runtest("Finland's"));
				assertArrayEquals(new Object[]{"Gladys house"}, runtest("Gladys' house"));
	
				//contractions
				assertArrayEquals(new Object[]{"is","not"}, runtest("isn't"));
				assertArrayEquals(new Object[]{"do","not"}, runtest("don't"));
				assertArrayEquals(new Object[]{"will","not"}, runtest("won't"));
				assertArrayEquals(new Object[]{"shall","not"}, runtest("shan't"));
				assertArrayEquals(new Object[]{"let","us"}, runtest("let's"));
				assertArrayEquals(new Object[]{"I","am"}, runtest("I'm"));
				assertArrayEquals(new Object[]{"we","are"}, runtest("we're"));
				assertArrayEquals(new Object[]{"they","are"}, runtest("they're"));
				assertArrayEquals(new Object[]{"I","have"}, runtest("I've"));
				assertArrayEquals(new Object[]{"Should","have"}, runtest("Should've"));
				assertArrayEquals(new Object[]{"They","would"}, runtest("They'd"));
				assertArrayEquals(new Object[]{"She","will"}, runtest("She'll"));
				assertArrayEquals(new Object[]{"Put","them"}, runtest("Put","'em"));
				
				//as single quotes
				assertArrayEquals(new Object[]{"quote","test"}, runtest("'quote","test'"));
				assertArrayEquals(new Object[]{"f(x)","=","df/dx"},runtest("f'(x)","=","df/dx"));
				assertArrayEquals(new Object[]{"f(x)","=","df/dx" }, runtest("f''(x)","=","df'/dx"));
			
			
			
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
