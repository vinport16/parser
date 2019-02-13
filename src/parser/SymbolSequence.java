package parser;

import java.util.*;

final class SymbolSequence {
	
	private final List<Symbol> production;
	final static SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());
	
	// Constructor
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
	}

	// Returns instance of SymbolSequence with given List<Symbol> production
	static final SymbolSequence build(List<Symbol> production) {
		if (production == null) {
			throw new NullPointerException("production provided is null");
		}
		return new SymbolSequence(production);
	}
	
	// Returns instance of SymbolSequence with variable number of symbols
	static final SymbolSequence build(Symbol... symbols) {
		List<Symbol> production = new ArrayList<Symbol>();
		for (Symbol symbol : symbols) {
			production.add(symbol);
		}
		return new SymbolSequence(production);
	}
	
	// Returns a ParseState with node containing all non-empty parsed tokens
	// and the remainder
	public ParseState match(List<Token> input) {
		List<Token> remainder = Objects.requireNonNull(input);
		List<Node> children = new ArrayList<Node>();
		ParseState result;
		for (Symbol symbol : production) {
			result = symbol.parse(remainder);
			if (!result.getSuccess()) {
				return ParseState.FAILURE;
			} else {
				children.add(result.getNode());
				remainder = result.getRemainder();
			}
		}
		return ParseState.build((InternalNode.build(children)), remainder);		
	}
	
	public String toString() {
		return production.toString();
	}
}
