package parser;

import java.util.*;

final class SymbolSequence {
	
	private final List<Symbol> production;
	final static SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());
	
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
	}

	static final SymbolSequence build(List<Symbol> production) {
		if (production == null) {
			throw new NullPointerException("production provided is null");
		}
		return new SymbolSequence(production);
	}
	
	static final SymbolSequence build(Symbol... symbols) {
		List<Symbol> production = new ArrayList<Symbol>();
		for (Symbol symbol : symbols) {
			production.add(symbol);
		}
		return new SymbolSequence(production);
	}
	
	public ParseState match(List<Token> input) {
		if (input == null) {
			throw new NullPointerException("input provided is null");
		}
		List<Token> remainder = input;
		List<Node> children = new ArrayList<Node>();
		ParseState result;
		for (Symbol symbol : production) {
			result = symbol.parse(remainder);
			if (!result.getSuccess()) {
				return ParseState.FAILURE;
			} else if(result.getNode().toList().size() != 0) {
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
