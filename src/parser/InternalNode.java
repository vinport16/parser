package parser;

import java.util.*;
import java.util.stream.Collectors;

public final class InternalNode implements Node{
  
  private List<Node> children;
  private String computedString;
  private List<Token> computedTokenList;
  
  private InternalNode(List<Node> childs){
    // childs are copied before being put in children
    List<Node> mutableChildren = new ArrayList<Node>();
    for(Node child : childs){
      mutableChildren.add(child.copy());
    }
    children = Collections.unmodifiableList(mutableChildren);
  }
  
  public Node copy(){
  	return new InternalNode(getChildren());
  }

  public static InternalNode build(List<Node> childs){
    if(childs == null){
      throw new NullPointerException("Cannot build InternalNode with null children");
    }
    return new InternalNode(childs);
  }

  public List<Node> getChildren(){
    // return copy of children
    List<Node> list = new ArrayList<Node>();
    for(Node child : children){
      list.add(child.copy());
    }
    return list;
  }

  public final List<Token> toList(){
    // if the token list has not yet been computed, compute and store it
    if(computedTokenList == null){
      List<Token> list = new ArrayList<Token>();
      for(Node child : children){
        list.addAll(child.toList());
      }
      computedTokenList = list;
    }
    return computedTokenList;
  }

  public String toString(){
    // if the string representation has not yet been computed, compute and store it
    if(computedString == null){
      computedString = "[" + children.stream().map(c -> c.toString()).collect(Collectors.joining(",")) + "]";
    }
    return computedString;
  }
  
  public boolean isFruitful() {
  	return this.getChildren().size() > 0;
  }
  
  public static class Builder {
	  private List<Node> children = new ArrayList<Node>();
	  
	  // Adds child to children array if not null
	  public boolean addChild(Node node) {
		  if (node == null) {
			  return false;
		  }
		  children.add(node);
		  return true;
	  }
	  
	  // Removes all childless nodes from the children list and
	  // if children list only contains single InternalNode, replace it with its children
	  public Builder simplify() {
		  children = children.stream().filter(child -> child.isFruitful()).collect(Collectors.toList());
		  if (children.size() == 1 && children.get(0).getChildren() != null) {
			  children = children.get(0).getChildren();
		  }
		  return this;
	  }
	  
	  // Returns new InternalNode with simplified children list
	  public Node build() {
		  return InternalNode.build(children);
	  }
  }

}