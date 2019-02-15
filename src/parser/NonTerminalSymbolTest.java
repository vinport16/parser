package parser;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class NonTerminalSymbolTest{

	@Test
	public void test(){
		
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		assertEquals( "Optional[[a]]", NonTerminalSymbol.parseInput(tokens).toString());
		
		tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("b"));
		assertEquals("Optional[[[a],[+,[b]]]]", NonTerminalSymbol.parseInput(tokens).toString());
		
		tokens = new ArrayList<Token>();
		tokens.add(Connector.build(TerminalSymbol.MINUS));
		tokens.add(Connector.build(TerminalSymbol.OPEN));
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.MINUS));
		tokens.add(Variable.build("b"));
		tokens.add(Connector.build(TerminalSymbol.CLOSE));
		tokens.add(Connector.build(TerminalSymbol.DIVIDE));
		tokens.add(Variable.build("c"));
		assertEquals("Optional[[[-,[(,[[a],[-,[b]]],)]],[/,[c]]]]", NonTerminalSymbol.parseInput(tokens).toString());
		
		tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("b"));
		tokens.add(Connector.build(TerminalSymbol.TIMES));
		tokens.add(Variable.build("c"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("d"));
		tokens.add(Connector.build(TerminalSymbol.TIMES));
		tokens.add(Connector.build(TerminalSymbol.OPEN));
		tokens.add(Variable.build("e"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("f"));
		tokens.add(Connector.build(TerminalSymbol.CLOSE));
		assertEquals("Optional[[[a],[+,[[b],[*,[c],[+,[[d],[*,[(,[[e],[+,[f]]],)]]]]]]]]]", NonTerminalSymbol.parseInput(tokens).toString());
	
	}

}
