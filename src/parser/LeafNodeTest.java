package parser;

import static org.junit.Assert.*;

import org.junit.*;

public class LeafNodeTest {

  @Test
  public void testLeafNodesMethods() {
    LeafNode a = LeafNode.build(Variable.build("a"));
    LeafNode times = LeafNode.build(Connector.build(TerminalSymbol.TIMES));
    LeafNode a2 = LeafNode.build(Variable.build("a"));
    
    assertTrue("two 'a' tokens in two different LeafNodes have different representations", ((Variable)a.getToken()).getRepresentation() == ((Variable)a2.getToken()).getRepresentation());
    assertTrue("the 'a' token in a LeafNode does produce a string of 'a'", a.getToken().toString().equals("a"));
    assertTrue("LeafNode toString doesn't work", times.toString().equals("*"));
  }
  
}
