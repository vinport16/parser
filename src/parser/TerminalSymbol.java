package parser;

import java.util.*;

public enum TerminalSymbol implements Symbol{
	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;
	
	static ArrayList<TerminalSymbol> tsl = new ArrayList<TerminalSymbol>();
	
	static {
		tsl.add(VARIABLE);
		tsl.add(PLUS);
		tsl.add(MINUS);
		tsl.add(TIMES);
		tsl.add(DIVIDE);
		tsl.add(OPEN);
		tsl.add(CLOSE);
	}
	
	@Override
	public ParseState parse(List<Token> input) {
		Token first = input.get(0);
		if (tsl.contains(first.getType())) {
			return ParseState.build(LeafNode.build(first), input.subList(1, input.size()));
		} else {
			return ParseState.FAILURE;
		}
	}
}