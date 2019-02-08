package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol{
	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;
	private static Map<NonTerminalSymbol, List<SymbolSequence>> productions;
	
	static {
		productions = new HashMap();
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
	
	static final Optional<Node> parseInput(List<Token> input){
		System.out.println("AAAA");
		return Optional.of(productions.get(EXPRESSION).get(0).match(input).getNode());
	}
	
	public ParseState parse(List<Token> input){
		for(SymbolSequence s : productions.get(this)){
			ParseState ps = s.match(input);
			if(ps.getSuccess()){
				return ps;
			}
		}
		return ParseState.FAILURE;
	}
}
