package edu.buffalo.cse.ir.wikiindexer.tokenizer.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenStream;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.TokenizerException;
import edu.buffalo.cse.ir.wikiindexer.tokenizer.rules.TokenizerRule.RULENAMES;

@RuleClass(className = RULENAMES.DATES)
public class DateRule implements TokenizerRule {

	@Override
	public void apply(TokenStream stream) throws TokenizerException {
		if(stream!=null)
		{
		String token;
		while(stream.hasNext())
		{
		token=stream.next();
		if(token!=null)
		{	Matcher mx=Pattern.compile("(([0-9]{2}:[0-9]{2}:[0-9]{2})\\s[a-zA-Z\\s,]+([0-9]|[0-9]{2})\\s(January|February|March|April|May|June|July|August|September|October|November|December)\\s([0-9]{4}))").matcher(token);
		if(mx.find())
		{	
			switch(mx.group(4).toLowerCase())
			{
			case "january": 
			if(mx.group(3).toCharArray().length==2)
			token=token.replace(mx.group(1), mx.group(5)+"01"+mx.group(3)+" "+mx.group(2));
			else
				token=token.replace(mx.group(1), mx.group(5)+"010"+mx.group(3)+" "+mx.group(2));
			break;	
			case "february": 
				token=token.replace(mx.group(1), mx.group(4)+"02"+mx.group(2));
				break;	
			case "march": 
				token=token.replace(mx.group(1), mx.group(4)+"03"+mx.group(2));
				break;
			case "april": 
				token=token.replace(mx.group(1), mx.group(4)+"04"+mx.group(2));
				break;	
			case "may": 
				token=token.replace(mx.group(1), mx.group(4)+"05"+mx.group(2));
				break;	
			case "june": 
				token=token.replace(mx.group(1), mx.group(4)+"06"+mx.group(2));
				break;	
			case "july": 
				token=token.replace(mx.group(1), mx.group(4)+"07"+mx.group(2));
				break;
			case "august": 
				token=token.replace(mx.group(1), mx.group(4)+"08"+mx.group(2));
				break;
			case "september": 
				token=token.replace(mx.group(1), mx.group(4)+"09"+mx.group(2));
				break;	
			case "october": 
				token=token.replace(mx.group(1), mx.group(4)+"10"+mx.group(2));
				break;	
			case "november": 
				token=token.replace(mx.group(1), mx.group(4)+"11"+mx.group(2));
				break;	
			case "december": 
				if(mx.group(3).toCharArray().length==2)
					
					token=token.replace(mx.group(1), mx.group(5)+"12"+mx.group(3)+" "+mx.group(2));
					else
						token=token.replace(mx.group(1), mx.group(5)+"120"+mx.group(3)+" "+mx.group(2));
				break;
			}}

			Matcher m=Pattern.compile("(([0-9]|[0-9]{2})\\s(January|February|March|April|May|June|July|August|September|October|November|December)\\s([0-9]{4}))").matcher(token);	
			while(m.find())
			{
				switch(m.group(3).toLowerCase())
				{
					case "january": 
					if(m.group(2).toCharArray().length==2)
					token=token.replace(m.group(1), m.group(4)+"01"+m.group(2));
					else
						token=token.replace(m.group(1), m.group(4)+"010"+m.group(2));
					break;	
					case "february": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"02"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"020"+m.group(2));
						break;	
					case "march": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"03"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"030"+m.group(2));
						break;
					case "april": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"04"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"040"+m.group(2));
						break;	
					case "may": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"05"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"050"+m.group(2));
						break;	
					case "june": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"06"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"060"+m.group(2));
						break;	
					case "july": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"07"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"070"+m.group(2));
						break;
					case "august": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"08"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"080"+m.group(2));
						break;
					case "september": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"09"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"090"+m.group(2));
						break;	
					case "october": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"10"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"0100"+m.group(2));
						break;	
					case "november": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"11"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"0110"+m.group(2));
						break;	
					case "december": 
						if(m.group(2).toCharArray().length==2)
						token=token.replace(m.group(1), m.group(4)+"12"+m.group(2));
						else
							token=token.replace(m.group(1), m.group(4)+"0120"+m.group(2));
						break;	
				}
				
			}
			
			Matcher m1=Pattern.compile("(([0-9]|[0-9]{2}):([0-9]{2})\\s?(am|pm|AM|PM))").matcher(token);
			while(m1.find())
			{	
				int a; String b;
				if(m1.group(4).contains("pm")||m1.group(4).contains("PM"))
				{ 
					a=Integer.parseInt(m1.group(2));
				 a+=12;
				 b=Integer.toString(a);
				 token=token.replace(m1.group(1),b+":"+m1.group(3)+":00");
				}
				if(m1.group(4).contains("am")||m1.group(4).contains("AM"))
				{	String temp=m1.group(2)+":"+m1.group(3)+":00";
				
					token=token.replace(m1.group(1),temp);
				}
			}
			Matcher m2=Pattern.compile("((January|February|March|April|May|June|July|August|September|October|November|December)\\s([0-9]?[0-9]),\\s([0-9]{4}),?)").matcher(token);
			while(m2.find())
			{
				switch(m2.group(2).toLowerCase())
				{
				case "january": 
					if(m2.group(3).toCharArray().length==2)
					token=token.replace(m2.group(1), m2.group(4)+"01"+m2.group(3));
					else token=token.replace(m2.group(1), m2.group(4)+"010"+m2.group(3));
					break;	
					case "february": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"02"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"020"+m2.group(3));
						break;	
					case "march": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"03"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"030"+m2.group(3));
						break;
					case "april": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"04"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"040"+m2.group(3));
						break;	
					case "may": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"05"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"050"+m2.group(3));
						break;	
					case "june": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"06"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"060"+m2.group(3));
						break;	
					case "july": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"07"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"070"+m2.group(3));
						break;
					case "august": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"08"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"080"+m2.group(3));
						break;
					case "september": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"09"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"090"+m2.group(3));
						break;	
					case "october": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"10"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"100"+m2.group(3));
						break;	
					case "november": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"11"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"110"+m2.group(3));
						break;	
					case "december": 
						if(m2.group(3).toCharArray().length==2)
						token=token.replace(m2.group(1), m2.group(4)+"12"+m2.group(3));
						else token=token.replace(m2.group(1), m2.group(4)+"120"+m2.group(3));
						break;	
				}
			}
			Matcher m3=Pattern.compile("((January|February|March|April|May|June|July|August|September|October|November|December)\\s([0-9]?[0-9]{1}),?)").matcher(token);
			while(m3.find())
			{
				switch(m3.group(2).toLowerCase())
				{
				case "january": 
					if(m3.group(3).toCharArray().length==2)
					token=token.replace(m3.group(1), "190001"+m3.group(3));
					else
					token=token.replace(m3.group(1), "1900010"+m3.group(3));
					break;	
					case "february":
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190002"+m3.group(3));
						else
						token=token.replace(m3.group(1), "1900020"+m3.group(3));
						break;	
					case "march": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190003"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900030"+m3.group(3));
						break;
					case "april": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1),"190004"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900040"+m3.group(3));
						break;	
					case "may": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190005"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900050"+m3.group(3));
						break;	
					case "june": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190006"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900060"+m3.group(3));
						break;	
					case "july": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190007"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900070"+m3.group(3));
						break;
					case "august": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190008"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900080"+m3.group(3));
						break;
					case "september": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190009"+m3.group(3));
						else
							token=token.replace(m3.group(1), "1900090"+m3.group(3));
						break;	
					case "october": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190010"+m3.group(3));
						else
							token=token.replace(m3.group(1), "19000100"+m3.group(3));
						break;	
					case "november": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190011"+m3.group(3));
						else
							token=token.replace(m3.group(1), "19000110"+m3.group(3));
						break;	
					case "december": 
						if(m3.group(3).toCharArray().length==2)
						token=token.replace(m3.group(1), "190012"+m3.group(3));
						else
							token=token.replace(m3.group(1), "19000120"+m3.group(3));
						break;
			}
		}
			Matcher m4=Pattern.compile("(([0-9]+)\\s(AD|BC))").matcher(token);
			while(m4.find())
			{
				if(m4.group(3).contains("AD"))
				{
					char[] x=m4.group(2).toCharArray();
					switch(x.length)
					{
					case 1:
						token=token.replace(m4.group(1),"000"+m4.group(2)+"0101");
						break;
					case 2:
						token=token.replace(m4.group(1),"00"+m4.group(2)+"0101");
						break;
					case 3:
						token=token.replace(m4.group(1),"0"+m4.group(2)+"0101");
						break;
					case 4:
						token=token.replace(m4.group(1),m4.group(2)+"0101");
						break;
					}
				}
				if(m4.group(3).contains("BC"))
				{	
					char[] x=m4.group(2).toCharArray();
					switch(x.length)
					{
					case 1:
						token=token.replace(m4.group(1),"-000"+m4.group(2)+"0101");
						break;
					case 2:
						token=token.replace(m4.group(1),"-00"+m4.group(2)+"0101");
						break;
					case 3:
						token=token.replace(m4.group(1),"-0"+m4.group(2)+"0101");
						break;
					case 4:
						token=token.replace(m4.group(1),"-"+m4.group(2)+"0101");
						break;
				}
					
				}
			}
			Matcher m5=Pattern.compile("(([0-9]{4})([^\\w\\s])([0-9]{2}))").matcher(token);
			char[] temp;
			if(m5.find())
			{	temp=m5.group(2).toCharArray();

				token=token.replace(m5.group(1),m5.group(2)+"0101"+m5.group(3)+temp[0]+temp[1]+m5.group(4)+"0101");
				
			}
			Matcher m6=Pattern.compile("[^\\d]([0-9]{4})[^\\d]").matcher(token);
			if(m6.find())
			{
				token=token.replaceAll("([^\\d])[0-9]{4}([^\\d])","$1"+m6.group(1)+"0101$2");
			}
			
			
		}
		stream.previous();
		stream.set(token);
		stream.next();
		
		}
		stream.reset();
	}

}}
