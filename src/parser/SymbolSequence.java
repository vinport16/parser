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
	
	// Helper method to check if input is null
	private void checkIfInputIsNull(List<Token> input) {
		if (input == null) {
			throw new NullPointerException("Input provided is null");
		}
	}
	
	// Returns a ParseState with node containing all non-empty parsed tokens
	// and the remainder
	public ParseState match(List<Token> input) {
		checkIfInputIsNull(input);
		List<Token> remainder = input;
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
		System.out.println(InternalNode.build(children).toString());
		return ParseState.build((InternalNode.build(children)), remainder);		
	}
	
	public String toString() {
		return production.toString();
	}
}
