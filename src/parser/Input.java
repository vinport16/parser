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
	
	private static String repeatString(String s, int times) {
		StringBuilder repeatedString = new StringBuilder();
		for (int i = 0; i < times; i++) {
			repeatedString.append(s);
		}
		return repeatedString.toString();
	}
	
	private static void printParseTree(Node n) {
		System.out.println("|");
		printParseTree(n, 0);
	}
		
	private static void printParseTree(Node n, int level) {
		String frontSpace = repeatString(" ", level * 2);
		for (Node child : n.getChildren()) {
			if (child instanceof LeafNode) {
				System.out.println(frontSpace + "|-" + child.toString());
			} else {
				System.out.println(frontSpace + "|");
				System.out.println(frontSpace + "|-|");
				printParseTree(child, level+1);
			}
		}
		System.out.println(frontSpace + "|");
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
				printParseTree(exp.get());
				//System.out.println(exp.get().toString());
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
