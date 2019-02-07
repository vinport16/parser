package parser;

import java.util.*;
import java.util.stream.*;

final class ParseState {
	
	private final boolean success;
	private final Node node;
	private final List<Token> remainder;
	final static ParseState FAILURE = new ParseState(false,null,null);
	
	
	private ParseState(boolean success, Node node, List<Token> remainder){
		this.success = success;
		this.node = node;
		this.remainder = remainder;
	}
	
	public static ParseState build(Node node, List<Token> remainder){
		if(node == null || remainder == null) {
			throw new NullPointerException("Cannot build ParseState with null parameters");
		}else {
			return new ParseState(true, node, remainder.stream().collect(Collectors.toList()));
		}
	}
	
	public boolean getSuccess(){
		return success;
	}
	
	public Node getNode(){
		return node.copy();
	}
	
	public List<Token> getRemainder(){
		return remainder.stream().collect(Collectors.toList());
	}
	
}
