package parser.bin;

import java.util.*;

final class SymbolSequence {
	
	private final List<Symbol> production;
	final static SymbolSequence EPSILON = new List<Symbol>();
	
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
	}

	static final SymbolSequence build(List<Symbol> production) {
		if (production == null) {
			throw new NullPointerException("production provided is null");
		}
		return SymbolSequence(production);
	}
	
	static final SymbolSequence build(Symbol... symbols) {
		
	}
	
	public ParseState match(List<Token> input)) {
		if (input == null) {
			throw new NullPointerException("input provided is null");
		}
		List<Token> remainder = input;
		List<Token> children = new List<Token>();
		
		
	}
	
	public toString() {
		return production.toString();
	}
}
