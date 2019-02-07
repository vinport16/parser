package parser;

import java.util.*;

interface Symbol {
	
	// Parse the input into a node, possibly leaving a remainder
	// ParseState's success will be true if parsing process was
	// successful and false otherwise.
	ParseState parse(List<Token> input);
}
