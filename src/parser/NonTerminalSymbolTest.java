package parser;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class NonTerminalSymbolTest{

	@Test
	public void test(){
		
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		assertEquals(NonTerminalSymbol.parseInput(tokens).toString(), "Optional[[[[[a]],[]],[]]]");
		/*                 [EXPRESSION]
		 *         [[TERM]            [EXPRESSION_TAIL]]
		 *   [[[UNARY]  [TERM_TAIL]]  [[EPSILON]]]
		 *  [[[[FACTOR]    [EPSILON]] [EPSILON]]]
		 * [[[[[VARIABLE]] [EPSILON]] [EPSILON]]]
		 * [[[[[   a    ]],[       ]],[       ]]] = [[[[[a]],[]],[]]]
		 */
		
		tokens = new ArrayList<Token>();
		tokens.add(Variable.build("a"));
		tokens.add(Connector.build(TerminalSymbol.PLUS));
		tokens.add(Variable.build("b"));
		assertEquals(NonTerminalSymbol.parseInput(tokens).toString(), "Optional[[[[[a]],[]],[+,[[[b]],[]],[]]]]");
	
	}

}
