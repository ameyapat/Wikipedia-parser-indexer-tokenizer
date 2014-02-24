package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.CapitalizationRule;

public class MyCapitalizationRuleTest {

	private CapitalizationRule rule;
	
	@Test
	public void test() {
		rule=new CapitalizationRule();
		
		try {
			
			/*	assertArrayEquals(new Object[] { "this is a test." },
						runtest("This is a test."));
				assertArrayEquals(new Object[] { "san Francisco is in California." },
						runtest("San Francisco is in California."));
				assertArrayEquals(
						new Object[] { "some bodily fluids, such as saliva and tears, do not transmit HIV" },
						runtest("Some bodily fluids, such as saliva and tears, do not transmit HIV"));
				assertArrayEquals(
						new Object[] { "it runs Apple's iOS mobile operating system," },
						runtest("It runs Apple's iOS mobile operating system,"));
			
				*/
				assertArrayEquals(new Object[] { "this", "is", "a", "test." },
						runtest("This", "is", "a", "test."));
				assertArrayEquals(new Object[] { "san", "Francisco", "is",
						"in", "California." },
						runtest("San", "Francisco", "is", "in", "California."));
				assertArrayEquals(
						new Object[] { "some", "bodily", "fluids,", "such",
								"as", "saliva", "and", "tears,", "do", "not",
								"transmit", "HIV" },
						runtest("Some", "bodily", "fluids,", "such", "as",
								"saliva", "and", "tears,", "do", "not",
								"transmit", "HIV"));
				assertArrayEquals(
						new Object[] { "it", "runs", "Apple's", "iOS",
								"mobile", "operating", "system," },
						runtest("It", "runs", "Apple's", "iOS", "mobile",
								"operating", "system,"));
		
			

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
