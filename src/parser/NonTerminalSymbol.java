package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol{
	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;
	//private static Map<NonTerminalSymbol, List<SymbolSequence>> productions;
	private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions;
	
	static {
		// Create and populate productions
		productions = new HashMap();
		Map<TerminalSymbol, SymbolSequence> produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.OPEN, SymbolSequence.build(TERM, EXPRESSION_TAIL));
		produced.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TERM, EXPRESSION_TAIL));
		produced.put(TerminalSymbol.MINUS, SymbolSequence.build(TERM, EXPRESSION_TAIL));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(EXPRESSION, produced);
		
		produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.PLUS, SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
		produced.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(EXPRESSION_TAIL, produced);
		
		produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.VARIABLE, SymbolSequence.build(UNARY, TERM_TAIL));
		produced.put(TerminalSymbol.OPEN, SymbolSequence.build(UNARY, TERM_TAIL));
		produced.put(TerminalSymbol.MINUS, SymbolSequence.build(UNARY, TERM_TAIL));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(TERM, produced);
		
		produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.TIMES, SymbolSequence.build(TerminalSymbol.TIMES, TERM, EXPRESSION_TAIL));
		produced.put(TerminalSymbol.DIVIDE, SymbolSequence.build(TerminalSymbol.DIVIDE, TERM, EXPRESSION_TAIL));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(TERM_TAIL, produced);
		
		produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
		produced.put(TerminalSymbol.VARIABLE, SymbolSequence.build(FACTOR));
		produced.put(TerminalSymbol.OPEN, SymbolSequence.build(FACTOR));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(UNARY, produced);
		
		produced = new HashMap<TerminalSymbol, SymbolSequence>();
		produced.put(TerminalSymbol.OPEN, SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
		produced.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TerminalSymbol.VARIABLE));
		produced.put(null, SymbolSequence.EPSILON);
		productions.put(FACTOR, produced);
	}
	
	// Parses a list of tokens into an expression tree
	// Returns Optional ParseState with node as the root of the tree
	static final Optional<Node> parseInput(List<Token> input){
		return Optional.ofNullable(EXPRESSION.parse(Objects.requireNonNull(input)).getNode());
	}
	
	// Parses a list of tokens into one of its own symbol lists
	// Returns ParseState if successful, ParseState.FAILURE otherwise
	public ParseState parse(List<Token> input){
		for(SymbolSequence s : productions.get(this)){
			ParseState ps = s.match(Objects.requireNonNull(input));
			if(ps.getSuccess()){
				return ps;
			}
		}
		return ParseState.FAILURE;
	}
}
