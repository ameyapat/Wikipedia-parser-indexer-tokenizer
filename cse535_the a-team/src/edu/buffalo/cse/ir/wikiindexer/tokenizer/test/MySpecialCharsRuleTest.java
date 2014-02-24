package edu.buffalo.cse.ir.wikiindexer.tokenizer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.SpecialCharsRule;

public class MySpecialCharsRuleTest {

	private SpecialCharsRule rule;
	@Test
	public void test() {
		rule=new SpecialCharsRule();
		try{
	/*
		assertArrayEquals(new Object[]{"destructor is method"}, 
				runtest("destructor is ~method()")); //tilda, brackets
		assertArrayEquals(new Object[]{"email is test buffalo.edu"}, 
				runtest("email is test@buffalo.edu")); //@
	//	assertArrayEquals(new Object[]{"call 555-5555"}, 
	//			runtest("call #555-5555")); //hash
		assertArrayEquals(new Object[]{"total is 5000.00"}, 
				runtest("total is $5000.00")); //dollar
		assertArrayEquals(new Object[]{"discounted at 15"}, 
				runtest("discounted at 15%")); //percentage
		assertArrayEquals(new Object[]{"x 2 x x"}, 
				runtest("x^2 = x*x")); //crows feet, asterisk and equal to
		assertArrayEquals(new Object[]{"proctor gamble"}, 
				runtest("proctor & gamble")); //&
	//	assertArrayEquals(new Object[]{"a b c"}, 
	//			runtest("a+b-c")); //+, -
		assertArrayEquals(new Object[]{"case x continue"}, 
				runtest("case x: continue;")); //: ;
	//	assertArrayEquals(new Object[]{"stdin cut f1 sort myfile"}, 
	//			runtest("stdin < cut -f1 | sort > myfile")); //< > |
		assertArrayEquals(new Object[]{"pray to"}, 
				runtest("pray to __/\\__"));
*/
		
		assertArrayEquals(new Object[]{"destructor","is","method"}, 
				runtest("destructor","is","~method()")); //tilda, brackets
		assertArrayEquals(new Object[]{"email","is","test","buffalo.edu"}, 
				runtest("email","is","test@buffalo.edu")); //@
		assertArrayEquals(new Object[]{"call","555-5555"}, 
				runtest("call","#555-5555")); //hash
		assertArrayEquals(new Object[]{"total","is","5000.00"}, 
				runtest("total","is","$5000.00")); //dollar
		assertArrayEquals(new Object[]{"discounted","at","15"}, 
				runtest("discounted","at","15%")); //percentage
		assertArrayEquals(new Object[]{"x","2","x","x"}, 
				runtest("x^2","=","x*x")); //crows feet, asterisk and equal to
		assertArrayEquals(new Object[]{"proctor","gamble"}, 
				runtest("proctor","&","gamble")); //&
		assertArrayEquals(new Object[]{"a","b","c"}, 
				runtest("a+b-c")); //+, -
		assertArrayEquals(new Object[]{"case","x","continue"}, 
				runtest("case","x:","continue;")); //: ;
		assertArrayEquals(new Object[]{"stdin","cut","f1","sort","myfile"}, 
			runtest("stdin","<","cut","-f1","|", "sort", ">", "myfile")); //< > |
		assertArrayEquals(new Object[]{"pray","to"}, 
				runtest("pray","to","__/\\__"));
		
	}catch (TokenizerException e){}
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
