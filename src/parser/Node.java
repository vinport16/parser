package parser;

import java.util.*;

public interface Node {
	public Node copy();
	public List<Token> toList();
	public List<Node> getChildren();
	public boolean isFruitful();
}
