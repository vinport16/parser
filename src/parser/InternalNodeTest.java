package parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class InternalNodeTest {

	@Test(expected = NullPointerException.class) 
	public void testInternalNodeFailsOnNull() { 
	     InternalNode.build(null);
	}

	@Test
	public void testInternalNodesToString() {
		List<Node> children = new ArrayList<>();
		children.add(LeafNode.build(Variable.build("a")));
		children.add(LeafNode.build(Connector.build(TerminalSymbol.TIMES)));
		children.add(LeafNode.build(Variable.build("b")));

		InternalNode aTimesB = InternalNode.build(children);
		
		assertTrue("InternalNode toString doesn't work", aTimesB.toString().equals("[a,*,b]"));

		children = new ArrayList<>();
		children.add(aTimesB);
		children.add(LeafNode.build(Connector.build(TerminalSymbol.PLUS)));
		children.add(LeafNode.build(Variable.build("c")));

		InternalNode expression = InternalNode.build(children);

		assertTrue("InternalNode toString doesn't work", expression.toString().equals("[[a,*,b],+,c]"));
	}

	@Test
	public void testInternalNodesCopiesChildren() {
		List<Node> children = new ArrayList<>();
		children.add(LeafNode.build(Variable.build("a")));
		InternalNode aParent = InternalNode.build(children);

		assertTrue("InternalNode does not create copies of children when instantiting", aParent.getChildren().get(0) != children.get(0));
	}
	
}
