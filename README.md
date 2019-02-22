# Assignment 5 - Vincent Portelli and Jason Shin

* To compile all the java files: javac *.java
* To run all of the tests: java Tests.java
* Vincent
	* Implemented isOperator() in Token and its derived classes
	* Implemented isOperator() and isStartedByOperator() methods in Node and its derived classes
	* Implemented command line interface to accept a string and converted it to a token list to pass in to NonTerminalSymbol.parseInput()
	* Came up with design to represent tree in the command line

* Jason
	* Implemented firstChild(), and isSingleLeafParent() methods in Node and its derived classes
	* Implemented the simplification to replace an InternalNode with its children whenever the InternalNode starts with an operator
	* Implemented the simplification to replace child with its grandchild when a node is a parent of an InternalNode whose only child is a leaf
	* Worked on displaying parse tree in command line
