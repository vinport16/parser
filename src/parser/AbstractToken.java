package parser;

public abstract class AbstractToken implements Token {

	private TerminalSymbol type;

	// Getter method for type
	public TerminalSymbol getType() {
		return type;
	}

	// Checks to see if type matches the input type
	public final boolean matches(TerminalSymbol type) {
		return (this.type == type);
	}
}