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
	  
	  // Replaces InternalNode with its children if the first of two adjacent Nodes
	  // is not an operator  and the next is an InternalNode that starts with an operator
	  private void replaceWithChildren() {
		  for (int i = 0; i < children.size() - 1; i++) {
			  if (!children.get(i).isOperator() && children.get(i+1).isStartedByOperator()) {
				  Node nodeIsStarted = children.get(i+1);
				  children.remove(i+1);
				  children.addAll(i+1, nodeIsStarted.getChildren());
			  }
		  }
	  }
	  
	  // If the Node is a parent of an internal node whose only child is a leaf, the child
	  // is replaced with the grandchild.
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
  
  // Returns true if the node is a leaf corresponding to an operator, and false otherwise
  public boolean isOperator(){
  	return false;
  }
  
  // Returns true if the node’s first child is an operator, and false otherwise
  public boolean isStartedByOperator(){
	return children.isEmpty() ? false : children.get(0).isOperator();
  }
  
  //  Returns the first child of this node, or empty if the node is either a leaf or unfruitful.
  public Optional<Node> firstChild() {
	return (this.isFruitful() || children.size() > 0) ? Optional.of(children.get(0)) : Optional.empty();
  }

  // Returns true if this node’s only child is a leaf, and false otherwise.
  public boolean isSingleLeafParent() {
	return (children.size() == 1 && children.get(0) instanceof LeafNode);
  }

}