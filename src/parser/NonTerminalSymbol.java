package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol{
	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;
	private static Map<NonTerminalSymbol, List<SymbolSequence>> productions;
	
	static {
		List<SymbolSequence> produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(TERM, EXPRESSION_TAIL));
		productions.put(EXPRESSION, produced);
		
		produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
		produced.add(SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
		produced.add(SymbolSequence.EPSILON);
		productions.put(EXPRESSION_TAIL, produced);
		
		produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(UNARY, TERM_TAIL));
		productions.put(TERM, produced);
		
		produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(TerminalSymbol.TIMES, TERM, EXPRESSION_TAIL));
		produced.add(SymbolSequence.build(TerminalSymbol.DIVIDE, TERM, EXPRESSION_TAIL));
		produced.add(SymbolSequence.EPSILON);
		productions.put(TERM_TAIL, produced);
		
		produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
		produced.add(SymbolSequence.build(FACTOR));
		productions.put(UNARY, produced);
		
		produced = new ArrayList<SymbolSequence>();
		produced.add(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
		produced.add(SymbolSequence.build(TerminalSymbol.VARIABLE));
		productions.put(FACTOR, produced);
	}
	
	private ParseState parseSegment(NonTerminalSymbol find, List<Token> input){
		if(productions.get(find).)
		return ParseState.FAILURE;
	}
	
	public ParseState parse(List<Token> input){
		
		return ParseState.FAILURE;
	}
}
