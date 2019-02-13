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
		/*                 [EXPRESSION]
		 *         [[TERM]            [EXPRESSION_TAIL]]
		 *   [[[UNARY]  [TERM_TAIL]]  [EPSILON]]]
		 *  [[[[FACTOR]    [EPSILON]] [       ]]]
		 * [[[[[VARIABLE]] [       ]] [       ]]]
		 * [[[[[   a    ]],[       ]],[       ]]] = [[[[[a]],[]],[]]]
		 */
		
		tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("b"));
		assertEquals("Optional[[[a],[+,[b]]]]", NonTerminalSymbol.parseInput(tokens).toString());
	
	}

}
