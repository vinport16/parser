# Assignment 4 - Vincent Portelli and Jason Shin

* To compile all the java files: javac *.java
* To run all of the tests: java Tests.java
* Vincent
	* Implemented getChildren() and isFruitful() methods to Node and their implementations
	* Finished populating new productions Map
	* Modified the parse() method in NonTerminalSymbol to work with the new productions Map.

* Jason
	* Created the Builder static nested class and implemented addChild(), simplify(), and build() methods
	* Replaced productoins to `Map<NonTerminalSymbol,Map<TerminalSymbol, SymbolSequence>>`
	* Began populating the new productions Map
