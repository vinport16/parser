package parser;

public abstract class AbstractToken implements Token {

	// Checks to see if type matches the input type
	public final boolean matches(TerminalSymbol type) {
		return (getType() == type);
	}
}