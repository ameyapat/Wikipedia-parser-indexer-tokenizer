package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.AccentRule;

public class MyAccentRuleTest {
	
	private AccentRule rule;

	@Test
	public void test() {
		rule=new AccentRule();
		
		try {
			
				assertArrayEquals(new Object[]{"The urban counterpart of chateau is palais"},
						runtest("The urban counterpart of château is palais"));
				assertArrayEquals(new Object[]{"The expression hotel particulier is used for an urban 'private house'"}, 
						runtest("The expression hôtel particulier is used for an urban 'private house'"));
				assertArrayEquals(new Object[]{"Resumes can be used for a variety of reasons"}, 
						runtest("Résumés can be used for a variety of reasons"));
				assertArrayEquals(new Object[]{"nара", "('steam/vapour')", "and", "nара", "('cent/penny,", "money')"},

						runtest("nа̀ра", "('steam/vapour')", "and", "nара̀", "('cent/penny,", "money')"));
 
//				assertArrayEquals(new Object[]{"for example vis-a-vis piece de resistance and creme brulee"}, 
//						runtest("for example vis-а̀-vis pièce de résistance and crème brûlée"));
				assertArrayEquals(new Object[]{"Spanish pinguino French aigue or aigue"}, 
						runtest("Spanish pingüino French aiguë or aigüe"));

				assertArrayEquals(new Object[]{"The", "urban", "counterpart", "of", "chateau", "is", "palais"}, 
				runtest("The", "urban", "counterpart", "of", "château", "is", "palais"));
				assertArrayEquals(new Object[]{"The", "expression", "hotel", "particulier", "is", "used", "for", "an", "urban", "'private", "house'"}, 
						runtest("The", "expression", "hôtel", "particulier", "is", "used", "for", "an", "urban", "'private", "house'"));
				assertArrayEquals(new Object[]{"Resumes", "can", "be", "used", "for", "a", "variety", "of", "reasons"}, 
						runtest("Résumés", "can", "be", "used", "for", "a", "variety", "of", "reasons"));
			//	assertArrayEquals(new Object[]{"пaра", "('steam/vapour')", "and", "napa", "('cent/penny,", "money')"},
			//			runtest("па̀ра", "('steam/vapour')", "and", "пара̀", "('cent/penny,", "money')"));
				assertArrayEquals(new Object[]{"for", "example", "vis-a-vis", "piece", "de", "resistance", "and", "creme", "brulee"}, 
						runtest("for", "example", "vis-à-vis", "pièce", "de", "résistance", "and", "crème", "brûlée"));
				assertArrayEquals(new Object[]{"Spanish", "pinguino", "French", "aigue", "or", "aigue"}, 
						runtest("Spanish", "pingüino", "French", "aiguë", "or", "aigüe"));

		} catch(TokenizerException e) {
	
			}}
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
