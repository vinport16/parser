package parser;

import java.util.*;

public enum TerminalSymbol implements Symbol{
	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;
	
	// TerminalSymbolList to check if token passed is of type TerminalSymbol
	static ArrayList<TerminalSymbol> tsl = new ArrayList<TerminalSymbol>();
	
	// Parses the first token of the input to return a ParseState with a Leaf Node of
	// the first token and the remainder of the list.
	@Override
	public ParseState parse(List<Token> input) {
		if (Objects.requireNonNull(input).isEmpty()) {
			return ParseState.FAILURE;
		}
		Token first = input.get(0);
		if (first.matches(this)) {
			return ParseState.build(LeafNode.build(first), input.subList(1, input.size()));
		} else {
			return ParseState.FAILURE;
		}
	}
}