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
  	return (this.children != null && this.getChildren().size() > 0);
  }
  
  public static class Builder {
	  private List<Node> children = new ArrayList<Node>();
	  
	  // Adds child to children array if not null
	  public boolean addChild(Node node) {
		  return children.add(Objects.requireNonNull(node));
	  }
	  
	  
	  private void replaceWithChildren() {
		  for (int i = 0; i < children.size() - 1; i++) {
			  if (!children.get(i).isOperator() && children.get(i+1).isStartedByOperator()) {
				  Node nodeIsStarted = children.get(i+1);
				  children.remove(i+1);
				  children.addAll(i+1, nodeIsStarted.getChildren());
			  }
		  }
	  }
	  
	  private void replaceWithGrandchild() {
		  for (int i = 0; i < children.size(); i++) {
			  Node child = children.get(i);
			  if (child.isSingleLeafParent()) {
				 children.set(i, child.firstChild().get());
			  }
		  }
	  }
	  
	  // Removes all childless nodes from the children list and
	  // if children list only contains single InternalNode, replace it with its children
	  public Builder simplify() {
		  children = children.stream().filter(child -> child.isFruitful()).collect(Collectors.toList());
		  if (children.size() == 1 && children.get(0).getChildren() != null) {
			  children = children.get(0).getChildren();
		  }
		  this.replaceWithChildren();
		  this.replaceWithGrandchild();
		  return this;
	  }
	  
	  // Returns new InternalNode with simplified children list
	  public Node build() {
	  	this.simplify();
		return InternalNode.build(children);
	  }
  }
  
  public boolean isOperator(){
  	return false;
  }
  
  public boolean isStartedByOperator(){
	return children.isEmpty() ? false : children.get(0).isOperator();
  }
  
  public Optional<Node> firstChild() {
	return (this.isFruitful() || children.size() > 0) ? Optional.of(children.get(0)) : Optional.empty();
  }
  
  public boolean isSingleLeafParent() {
	return (children.size() == 1 && children.get(0).getChildren() == null);
  }

}