package parser;

import static org.junit.Assert.*;
import org.junit.*;

public class ConnectorTest {

	@Test(expected = NullPointerException.class) 
	public void testConnectorBuildFailsOnNull() { 
	    Connector.build(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConnectorBuildFailsOnVariable() {
		Connector.build(TerminalSymbol.VARIABLE);
	}

	// @Test
	// public void testConnectorBuildNotCached() {
	// 	assertEquals("TerminalSymbol is not ADD", Connector.build(TerminalSymbol.ADD));
	// }

	// @Test
	// public void testConnectorBuildCached() {
	// 	Connector.build(TerminalSymbol.ADD);
	// }

	@Test
	public void testConnectorGetType() {
		Connector c = Connector.build(TerminalSymbol.PLUS);
		assertEquals("TerminalSymbol getType does not return correct type", TerminalSymbol.PLUS, c.getType());
	}

	@Test
	public void testConnectorToString() {
		Connector c = Connector.build(TerminalSymbol.PLUS);
		assertEquals("+", c.toString());
	}
}