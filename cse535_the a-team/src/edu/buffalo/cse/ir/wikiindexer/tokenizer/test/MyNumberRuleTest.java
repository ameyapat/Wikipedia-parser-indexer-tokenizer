package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.NumberRule;

public class MyNumberRuleTest {

	private NumberRule rule;
	
	@Test
	public void test() {
		rule=new NumberRule();
		
		try {
			
			/*	assertArrayEquals(
						new Object[] { "The App Store offered more than apps by Apple and third parties." },
						runtest("The App Store offered more than 775,000 apps by Apple and third parties."));
				assertArrayEquals(
						new Object[] { "The game received average review scores of % and / for the Xbox version" },
						runtest("The game received average review scores of 96.92% and 98/100 for the Xbox 360 version"));
				assertArrayEquals(
						new Object[] { "The number is the sixth prime number" },
						runtest("The number 13 is the sixth prime number"));
			*/
				assertArrayEquals(
						new Object[] { "The", "App", "Store", "offered",
								"more", "than", "apps", "by", "Apple",
								"and", "third", "parties." },
						runtest("The", "App", "Store", "offered", "more",
								"than", "775,000", "apps", "by", "Apple",
								"and", "third", "parties."));
				assertArrayEquals(
						new Object[] { "The", "game", "received",
								"average", "review", "scores", "of", "%",
								"and", "/", "for", "the", "Xbox", "version" },
						runtest("The", "game", "received", "average",
								"review", "scores", "of", "96.92%", "and",
								"98/100", "for", "the", "Xbox", "360",
								"version"));
				assertArrayEquals(
						new Object[] { "The", "number", "is", "the",
								"sixth", "prime", "number" },
						runtest("The", "number", "13", "is", "the",
								"sixth", "prime", "number"));
			

		} catch (TokenizerException e) {

		} }
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


