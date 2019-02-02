package parser;

import java.util.*;
import java.util.stream.Collectors;

public final class InternalNode implements Node{
  
  private final List<Node> children;
  private String computedString;
  private List<Token> computedTokenList;

  private InternalNode(List<Node> childs){
    // childs are copied before being put in children
    children = new ArrayList<Node>();
    for(Node child : childs){
      if(child instanceof InternalNode){
        children.add(InternalNode.build(((InternalNode)child).getChildren()));
      }else{
        // child is a leaf node
        children.add(LeafNode.build(((LeafNode)child).getToken()));
      }
    }
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
      if(child instanceof InternalNode){
        list.add(InternalNode.build(((InternalNode)child).getChildren()));
      }else{
        // child is a leaf node
        list.add(LeafNode.build(((LeafNode)child).getToken()));
      }
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

}