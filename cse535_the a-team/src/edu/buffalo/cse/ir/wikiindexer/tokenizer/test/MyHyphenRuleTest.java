package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.HyphenRule;

public class MyHyphenRuleTest{
	
	private HyphenRule rule;

	@Test
	public void test() {
		rule=new HyphenRule();
		try {
			//whitespace padded hyphens
			assertArrayEquals(new Object[]{"hyphen", "test"}, runtest("hyphen"," - ", "test"));
			assertArrayEquals(new Object[]{"hyphen", "test"}, runtest("hyphen"," -- ", "test"));
			
			//alphanumeric
			assertArrayEquals(new Object[]{"B-52"}, runtest("B-52"));
			assertArrayEquals(new Object[]{"12-B"}, runtest("12-B"));
			assertArrayEquals(new Object[]{"6-6"}, runtest("6-6"));
			assertArrayEquals(new Object[]{"D-BB3"}, runtest("D-BB3"));
			assertArrayEquals(new Object[]{"week day"}, runtest("week-day"));
			
			//code style
			assertArrayEquals(new Object[]{"c"}, runtest("c--"));
			assertArrayEquals(new Object[]{"c"}, runtest("--c"));
			
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


