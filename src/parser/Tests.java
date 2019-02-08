package parser;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class Tests {

    public static void main(String[] args){
      
      Result result = JUnitCore.runClasses(InternalNodeTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(LeafNodeTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(CacheTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(VariableTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(ConnectorTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(NonTerminalSymbolTest.class);
      for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
      result = JUnitCore.runClasses(TerminalSymbolTest.class);
      for (Failure failure : result.getFailures()) {
          System.out.println(failure.toString());
        }
    }
}