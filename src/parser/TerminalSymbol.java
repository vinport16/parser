package parser;

import java.util.*;

public enum TerminalSymbol implements Symbol{
	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE
	
	@Override
	public ParseState parse(List<Token> input) {
		if () {
			return ParseState.build( )
		} else {
			return ParseState.FAILURE;
		}
	}
}