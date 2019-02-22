package parser;

import java.util.*;

public final class LeafNode implements Node{
  
  private final Token token;
  
  private LeafNode(Token t){
    token = t;
  }

  public static LeafNode build(Token t){
    if(t == null){
      throw new NullPointerException("Cannot build LeafNode with null token");
    }
    return new LeafNode(t);
  }

  public Token getToken(){
    return token;
  }

  public String toString(){
    return token.toString();
  }
  
  public Node copy(){
  	return new LeafNode(token);
  }

  public List<Token> toList(){
    return Arrays.asList(token);
  }
  
  public List<Node> getChildren(){
  	return null;
  }
  
  public boolean isFruitful() {
  	return true;
  }
  
  // Returns true if the node is a leaf corresponding to an operator, and false otherwise
  public boolean isOperator() {
  	return token.isOperator();
  }
  
  // Returns true if the node’s first child is an operator, and false otherwise
  public boolean isStartedByOperator(){
	return false;
  }
  
  //  Returns the first child of this node, or empty if the node is either a leaf or unfruitful.
  public Optional<Node> firstChild() {
	return Optional.empty();
  }
  
  // Returns true if this node’s only child is a leaf, and false otherwise.
  public boolean isSingleLeafParent() {
	return false;
  }

}