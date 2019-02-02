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

  public List<Token> toList(){
    return Arrays.asList(token);
  }

}