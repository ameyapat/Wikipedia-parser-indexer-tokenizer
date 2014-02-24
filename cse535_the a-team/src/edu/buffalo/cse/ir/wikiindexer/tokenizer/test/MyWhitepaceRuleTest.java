package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.WhitespaceRule;

public class MyWhitepaceRuleTest {
	private WhitespaceRule rule;
	@Test
	public void test() {
		rule=new WhitespaceRule();
		
		try {
			assertArrayEquals(new Object[]{"this","is","a","test" }, 
					runtest("this is a test"));
			assertArrayEquals(new Object[]{"this","is","a","test" }, 
					runtest("this    is     a      test"));
			assertArrayEquals(new Object[]{"this","is","a","test" }, 
					runtest("    this is a test       "));
			assertArrayEquals(new Object[]{"this","is","a","test" }, 
					runtest("this "
							+ "is \r"
							+ "a \n test"));
			assertArrayEquals(new Object[]{"thisisatest" }, 
					runtest("thisisatest"));
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

