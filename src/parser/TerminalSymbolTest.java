package parser;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class TerminalSymbolTest {
	
	@Test
	public void TestParseEmptyTokenList() {
		List<Token> tokens = new ArrayList<Token>();
		assertEquals(ParseState.FAILURE, TerminalSymbol.PLUS.parse(tokens));
	}
	
	@Test
	public void TestParseNullTokenList() {
		assertEquals(ParseState.FAILURE, TerminalSymbol.PLUS.parse(null));
	}
	
	@Test
	public void TestParse() {
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.TIMES));
		tokens.add(Variable.build("b"));
		tokens.add(Connector.build(TerminalSymbol.DIVIDE));
		tokens.add(Variable.build("c"));
		System.out.println(TerminalSymbol.PLUS.parse(tokens).getNode());
		System.out.println(TerminalSymbol.PLUS.parse(tokens).getRemainder());
	}
}
