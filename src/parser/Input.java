package parser;

import java.util.*;

public class Input {
	
	private static Map<Character, TerminalSymbol> connectors = new HashMap<Character, TerminalSymbol>();
	
	static {
		connectors.put('+', TerminalSymbol.PLUS);
		connectors.put('-', TerminalSymbol.MINUS);
		connectors.put('*', TerminalSymbol.TIMES);
		connectors.put('/', TerminalSymbol.DIVIDE);
		connectors.put('(', TerminalSymbol.OPEN);
		connectors.put(')', TerminalSymbol.CLOSE);
	}
	
	private static List<Token> stringToTokenList(String expression){
		List<Token> tokens = new ArrayList<Token>();
		
		for(char c : expression.toCharArray()) {
			if(connectors.containsKey(c)) {
				tokens.add(Connector.build(connectors.get(c)));
			}else{
				tokens.add(Variable.build(String.valueOf(c)));
			}
		}
		return tokens;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line;
		Optional<Node> exp;
		
		loop:
		while(true){
			line = in.nextLine();
			
			if(line.contentEquals("stop")) {
				break loop;
			}
			
			exp = NonTerminalSymbol.parseInput(stringToTokenList(line));
			
			if(exp.isPresent()) {
				System.out.println(exp.get().toString());
			}else {
				System.out.println("error parsing expression");
			}
		}
		
		in.close();
		
	}
}

/* 
 * For [a,+,b,-,[c,*,[[(,[g,/,b,+,e],)],/,a,-,h]]] :
 * 
 * |
 * |-{a
 * |-{+
 * |-{b
 * |-{-
 * |
 * |-|
 *   |-{c
 *   |-{*
 *   |
 *   |-|
 *     |-|
 *     | |-{(
 *     | |
 *     | |-|
 *     | | |-{g
 *     | | |-{/
 *     | | |-{b
 *     | | |-{+
 *     | | |-{e
 *     | |
 *     | |-{)
 *     | 
 *     |-|
 *       |-{/
 *       |-{a
 *       |-{-
 *       |-{h
 * 
 * */
