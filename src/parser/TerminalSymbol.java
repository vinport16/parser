package parser;

import java.util.*;

public enum TerminalSymbol implements Symbol{
	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;
	
	@Override
	public ParseState parse(List<Token> input) {
		if (input.get(0) ==  ) {
			return ParseState.build(LeafNode.build(input.get(0)))
		} else {
			return ParseState.FAILURE;
		}
	}
}