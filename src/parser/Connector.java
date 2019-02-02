package parser;

import java.util.HashMap;

public final class Connector extends AbstractToken {

	TerminalSymbol type;
	private static Cache<TerminalSymbol, Connector> cache = new Cache<TerminalSymbol, Connector>();
	private static HashMap<TerminalSymbol, String> map = new HashMap<TerminalSymbol, String>();

	// Private constructor to set type field
	private Connector(TerminalSymbol type) {
		this.type = type;
	}

	// Populates hashmap field with mapping from TerminalSymbol enum to String
	private void populateMap() {
		map.put(TerminalSymbol.PLUS, "+");
		map.put(TerminalSymbol.MINUS, "-");
		map.put(TerminalSymbol.TIMES, "*");
		map.put(TerminalSymbol.DIVIDE, "/");
		map.put(TerminalSymbol.OPEN, "(");
		map.put(TerminalSymbol.CLOSE, ")");
	}

	// Getter method for type
	public TerminalSymbol getType() {
		return type;
	}

	// Returns a connector of the given type
	// Should use a Cache to avoid building multiple connectors of the same type.
	// Throws a NullPointerException if type is null
	// Throws an illegalArgumentException if the type is invalid
	public static final Connector build(TerminalSymbol type) {
		if (type == null) {
			throw new NullPointerException("type provided is null");
		} else if (type == TerminalSymbol.VARIABLE) {
			throw new IllegalArgumentException("type is not one of PLUS, MINUS, TIMES, DIVIDE, OPEN, or CLOSE");
		} else if (cache.containsKey(type)) {
			return cache.get(type, t -> new Connector(t));
		} else {
			return new Connector(type);
		}
	}

	// Overrides toString method, returns string representation of TerminalSymbol
	public String toString() {
		if (map.isEmpty()) {
			populateMap();
		}
		return map.get(type);
	}
}