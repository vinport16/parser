package parser;

import static org.junit.Assert.*;
import org.junit.*;

public class VariableTest {

	@Test(expected = NullPointerException.class) 
	public void testVariableBuildFailsOnNull() { 
	    Variable.build(null);
	}

	// @Test
	// public void testVariableBuildNotCached() {
	// 	assertEquals("TerminalSymbol is not ADD", Connector.build(TerminalSymbol.ADD));
	// }

	// @Test
	// public void testVariableBuildCached() {
	// 	Variable.build("a");
	// }

	@Test
	public void testVariableGetType() {
		Variable v = Variable.build("a");
		assertEquals(TerminalSymbol.VARIABLE, v.getType());
	}

	@Test
	public void testVariableGetRepresentation() {
		Variable v = Variable.build("a");
		assertEquals("a", v.getRepresentation());
	}

	@Test
	public void testVariableToString() {
		Variable v = Variable.build("a");
		assertEquals("a", v.toString());		
	}
}