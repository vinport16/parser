package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol{
	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;
	private static Map<NonTerminalSymbol, List<SymbolSequence> productions;
	
	static {

	}
}
