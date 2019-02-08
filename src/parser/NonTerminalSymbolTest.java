package parser;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class NonTerminalSymbolTest{

	@Test
	public void test(){
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("b"));
		
		System.out.println(tokens);
		// NonTerminalSymbol.parseInput(tokens);
		System.out.println(NonTerminalSymbol.parseInput(tokens));
	}

}
