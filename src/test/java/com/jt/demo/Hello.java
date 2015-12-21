package com.jt.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-2/
 * since 2015/12/21.
 */
public class Hello {

    /**
\d  	A digit: [0-9]
\D  	A non-digit: [^0-9]
\s  	A whitespace character: [ \t\n\x0B\f\r]
\S  	A non-whitespace character: [^\s]
\w  	A word character: [a-zA-Z_0-9]
\W  	A non-word character: [^\w]

*      Match 0 or more times
+      Match 1 or more times
?      Match 1 or 0 times
{n}    Match exactly n times
{n,}   Match at least n times
{n,m}  Match at least n but not more than m times

\   	Escape the next meta-character (it becomes a normal/literal character)
^   	Match the beginning of the line
.   	Match any character (except newline)
$   	Match the end of the line (or before newline at the end)
|   	Alternation (‘or’ statement)
()  	Grouping
[]  	Custom character class
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
String pattern = "\\d \\D \\W \\w \\S \\s";
    }

    @Test
    public void test01() throws Exception {
        String s = "I lost my sablefish";
        System.out.println(s.matches("I lost my \\w+"));

    }

    //Matching/Validating
    @Test
    public void test02() throws Exception {
        List<String> input = new ArrayList<String>();
		input.add("123-45-6789");
		input.add("9876-5-4321");
		input.add("987-65-4321 (attack)");
		input.add("987-65-4321 ");
		input.add("192-83-7465");


		for (String ssn : input) {
			if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {
				System.out.println("Found good SSN: " + ssn);
			}
		}
    }

    // Extracting/Capturing
    @Test
    public void test03() throws Exception {
        String input = "I have a cat, but I like my dog better.";

		Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
		Matcher m = p.matcher(input);

		List<String> animals = new ArrayList<String>();
		while (m.find()) {
			System.out.println("Found a " + m.group() + ".");
			animals.add(m.group());
		}
    }

    // Modifying/Substitution
    @Test
    public void test04() throws Exception {
String input =
                  "User clientId=23421. Some more text clientId=33432. This clientNum=100";

		Pattern p = Pattern.compile("(clientId=)(\\d+)");
		Matcher m = p.matcher(input);

		StringBuffer result = new StringBuffer();
		while (m.find()) {
			System.out.println("Masking: " + m.group(2));
			m.appendReplacement(result, m.group(1) + "***masked***");
		}
		m.appendTail(result);
		System.out.println(result);
    }

    /**
(?:X) 			X, as a non-capturing group
(?=X) 			X, via zero-width positive look-ahead
(?!X) 			X, via zero-width negative look-ahead
(?<=X) 			X, via zero-width positive look-behind
(?<!X) 			X, via zero-width negative look-behind
(?<X) 			X, as an independent, non-capturing group
     * @throws Exception
     */
    //Look-ahead & Look-behind
    @Test
    public void test05() throws Exception {
List<String> input = new ArrayList<String>();
		input.add("password");
		input.add("p4ssword");
		input.add("p4ssw0rd");
		input.add("p45sword");

		for (String ssn : input) {
			if (ssn.matches("^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}$")) {
				System.out.println(ssn + ": matches");
			} else {
				System.out.println(ssn + ": does not match");
			}
		}
    }

    //配置

    /**
 Configuration flags
(?idmsux-idmsux)  	Turns match flags on - off for entire expression
(?idmsux-idmsux:X)   	X, as a non-capturing group with the given flags on – off
(?i)  			Toggle case insensitivity (default: off, (?-i)) for the text in this group only
(?d)  			Enables UNIX line mode (default: off, (?-d))
			In this mode, only the '\n' line terminator is recognized in the behavior of ., ^, and $
(?s)  			Toggle dot ‘.’ matches any character (default: off, (?-s))
			Normally, the dot character will match everything except newline characters.
(?u)  			Toggle Unicode standard case matching (default: off, (?-u)
			By default, case-insensitive matching assumes that only characters
			in the US-ASCII charset are being matched.
(?x)  			Allow comments in pattern (default: off, (?-x))
			In this mode, whitespace is ignored, and embedded comments starting with '#'
			are ignored until the end of a line.

(?si)			turn on case insensitivity and dotall modes
(?=.*Green.*) 		‘Green’ must be found somewhere to the right of this look-ahead
.*Blue.*		‘Blue’ must be found somewhere in the input
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        String s = "(?idx)^I\\s lost\\s my\\s .+     #this comment and all spaces will be ignored";
        String input = "My dog is Blue.\n" +
				"He is not red or green.";

		Boolean controlResult = input.matches("(?=.*Green.*).*Blue.*");
		Boolean caseInsensitiveResult = input.matches("(?i)(?=.*Green.*).*Blue.*");
		Boolean dotallResult = input.matches("(?s)(?=.*Green.*).*Blue.*");
		Boolean configuredResult = input.matches("(?si)(?=.*Green.*).*Blue.*");

		System.out.println("Control result was: " + controlResult);
		System.out.println("Case ins. result was: " + caseInsensitiveResult);
		System.out.println("Dot-all result was: " + dotallResult);
		System.out.println("Configured result was: " + configuredResult);
    }


}
