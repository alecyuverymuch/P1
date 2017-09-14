
public class P1 {

	// Sym to test operations on
	private static Sym sym;
	// SymTable to test operations on
	private static SymTable tbl;
	// Count of passing tests
	private static int passCount;
	// Total number of tests
	private static final int TEST_COUNT = 15;
	
	/**
	 * Runs a series of tests on the Sym and SymTable objects
	 * @param args
	 */
	public static void main(String[] args) {
		passCount = 0;
		test_sym();
		test_addScopeAndRemoveScope();
		test_addDeclAndLookup();
		System.out.println();
		System.out.println("State of the SymTable after running tests:");
		tbl.print();
		System.out.println("Passed " + passCount + 
				" tests out of " + TEST_COUNT);
	}
	
	/**
	 * Runs tests on the Sym object's methods.
	 */
	public static void test_sym() {
		String testName = "test_sym - ";
		sym = new Sym("test");
		
		// Test: Check getType
		// Pass: If sym.getType() = "test"
		// Description: Checks if the string returned by the getType()
		// method is the same as the string given.
		String test0 = "Check getType";
		String result0 = sym.getType().equals("test") ? "Pass: " : "Fail: ";
		System.out.println(result0 + testName + test0);
		if (result0 == "Pass: ")
			passCount++;
		
		// Test: Check toSting
		// Pass: If sym.toString() = "test"
		// Description: Checks if the string returned by the toString()
		// method is the same as the string given.
		String test1 = "Check toSting";
		String result1 = sym.toString().equals("test") ? "Pass: " : "Fail: ";
		System.out.println(result1 + testName + test1);
		if (result1 == "Pass: ")
			passCount++;
	}
	
	/**
	 * Runs tests on the SymTable's methods involving adding and removing 
	 * scopes
	 */
	public static void test_addScopeAndRemoveScope() {
		String testName = "test_addScopeAndRemoveScope - ";
		tbl = new SymTable();
		
		// Test: Remove Constructor Initialized Scope
		// Pass: If successfully removed
		// Description: Checks to see that the scope added by the constructor
		// is in the table and then removes it. 
		String test0 = "Remove Constructor Initialized Scope";
		try {
			tbl.removeScope();
			System.out.println("Pass: " + testName + test0);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test0);
		}
		
		// Test: Remove Scope From Empty List
		// Pass: If EmptySymTableException is thrown
		// Description: Tries to remove the first scope from the empty list
		// and passes if an exception is thrown
		String test1 = "Remove Scope From Empty List";
		try {
			tbl.removeScope();
			System.out.println("Fail: " + testName + test1);
		} catch (EmptySymTableException e) {
			System.out.println("Pass: " + testName + test1);
			passCount++;
		}
		
		// Test: Add and Remove New Scope
		// Pass: Add and successfully remove new scope
		// Description: Add a new scope to the table and then check if it is
		// there by removing it
		String test2 = "Add and Remove New Scope";
		tbl.addScope();
		try {
			tbl.removeScope();
			System.out.println("Pass: " + testName + test2);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test2);
		}
		
		// Test: Check if Removed is First
		// Pass: If the scope removed is the first one
		// Description: Add a scope and add an entry to it. Then add another
		// scope. Then run removeScope() to see if only the first one is 
		// removed and the old one becomes the first. 
		String test3 = "Check if Removed is First";
		tbl.addScope();
		try {
			tbl.addDecl("test3_1", new Sym("test3_1"));
			tbl.addScope();
			tbl.addDecl("test3_2", new Sym("test3_2"));
			tbl.removeScope();
			Sym removedSym = tbl.lookupGlobal("test3_2");
			Sym currentSym = tbl.lookupLocal("test3_1");
			if (removedSym != null || currentSym == null)
				throw new Exception();
			System.out.println("Pass: " + testName + test3);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test3);
		}
		
		// Test: New Scope is First
		// Pass: If newly added scope is at the top
		// Details: Add a scope and add an entry. Then add a new scope and do
		// a local lookup to see if the value is at the bottom
		String test4 = "New Scope is First";
		try {
			tbl.addScope();
			tbl.addDecl("test4", new Sym("test4"));
			tbl.addScope();
			Sym resultL = tbl.lookupLocal("test4");
			String resultG = tbl.lookupGlobal("test4").getType();
			if (resultL != null || resultG != "test4")
				throw new Exception();
			System.out.println("Pass: " + testName + test4);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test4);
		}
	}
	
	/**
	 * Runs tests on the SymTable object involving adding entries and looking
	 * up entries
	 */
	public static void test_addDeclAndLookup() {
		String testName = "test_addDeclAndLookup - ";
		tbl = new SymTable();
		
		// Test: Lookup on an Empty Table
		// Pass: If EmptySymTableExceptions are thrown
		// Description: Remove the first scope and then try
		// try searching the scope to get exceptions
		String test0 = "Lookup on an Empty Table";
		try {
			tbl.removeScope();
			tbl.lookupLocal("test0");
			System.out.println("Fail: " + testName + test0);
		} catch (EmptySymTableException e) {
			try {
				tbl.lookupLocal("test0");
				System.out.println("Fail: " + testName + test0);
			} catch (EmptySymTableException e1) {
				System.out.println("Pass: " + testName + test0);
				passCount++;
			} catch (Exception e1) {
				System.out.println("Fail: " + testName + test0);
			}
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test0);
		}
		
		// Test: Adding Null Values
		// Pass: If NullPointerExceptions are thrown
		// Description: Try adding null values to the table and 
		// pass if a null pointer exception is thrown
		String test1 = "Adding Null Values";
		tbl.addScope();
		try {
			tbl.addDecl(null, null);
			System.out.println("Fail: " + testName + test1);
		} catch (NullPointerException e) {
			try {
				tbl.addDecl(null, new Sym("test1"));
				System.out.println("Fail: " + testName + test1);
			} catch (NullPointerException e1) {
				try {
					tbl.addDecl("test1", null);
					System.out.println("Fail: " + testName + test1);
				} catch (NullPointerException e2) {
					System.out.println("Pass: " + testName + test1);
					passCount++;
				} catch (Exception e2) {
					System.out.println("Fail: " + testName + test1);
				}
			} catch (Exception e1) {
				System.out.println("Fail: " + testName + test1);
			}
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test1);
		}
		
		// Test: Adding to an Empty List
		// Pass: An EmptySymTableException is thrown
		// Description: Remove the scope and try to add an entry into the 
		// empty table to get an empty table exception. 
		String test2 = "Adding to an Empty List";
		try {
			tbl.removeScope();
			tbl.addDecl("test2", new Sym("test2"));
			System.out.println("Fail: " + testName + test2);
			tbl.addScope();
		} catch (EmptySymTableException e) {
			System.out.println("Pass: " + testName + test2);
			passCount++;
			tbl.addScope();
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test2);
			tbl.addScope();
		}
		
		// Test: Adding a Valid Entry
		// Pass: The value is added and found by search
		// Description: Add a valid entry into the table and check that 
		// the lookup methods return the entry and return null for a 
		// non-existant entry
		String test3 = "Adding a Valid Entry";
		try {
			tbl.addDecl("test3", new Sym("test3"));
			String localSearch = tbl.lookupLocal("test3").getType(); 
			String globalSearch = tbl.lookupGlobal("test3").getType();
			Sym localSearchFail = tbl.lookupLocal("test3Fail"); 
			Sym globalSearchFail = tbl.lookupGlobal("test3Fail");
			if (localSearch == "test3" && 
				globalSearch == "test3" && 
				localSearchFail == null && 
				globalSearchFail == null) {
				System.out.println("Pass: " + testName + test3);
				passCount++;
			}
			else 
				System.out.println("Fail: " + testName + test3);
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test3);
		}

		// Test: Add Duplicate Entries
		// Pass: DuplicateSymException is thrown
		// Description: Add two duplicate entries to the table to get a 
		// duplicate sym exception
		String test4 = "Add Duplicate Entries";
		try { 
			tbl.addScope();
			tbl.addDecl("test4", new Sym("test4"));
			tbl.addDecl("test4", new Sym("test4"));
			System.out.println("Fail: " + testName + test4);
		} catch (DuplicateSymException e) {
			System.out.println("Pass: " + testName + test4);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test4);
		}

		// Test: Adding Multiple Entries
		// Pass: The values are added and found by search
		// Description: Add two different valid entries into the table and
		// check that the lookup methods return the entries and return null
		// for a non-existant entry
		String test5 = "Adding Multiple Entries";
		try {
			tbl.addScope();
			tbl.addDecl("test5_1", new Sym("test5_1"));
			tbl.addDecl("test5_2", new Sym("test5_2"));
			String localSearch1 = tbl.lookupLocal("test5_1").getType(); 
			String globalSearch1 = tbl.lookupGlobal("test5_1").getType();
			Sym localSearchFail1 = tbl.lookupLocal("test5_1Fail"); 
			Sym globalSearchFail1 = tbl.lookupGlobal("test5_1Fail");

			String localSearch2 = tbl.lookupLocal("test5_2").getType(); 
			String globalSearch2 = tbl.lookupGlobal("test5_2").getType();
			Sym localSearchFail2 = tbl.lookupLocal("test5_2Fail"); 
			Sym globalSearchFail2 = tbl.lookupGlobal("test5_2Fail");

			if (localSearch1 != "test5_1" || 
				globalSearch1 != "test5_1" || 
				localSearchFail1 != null || 
				globalSearchFail1 != null)
				throw new Exception();

			if (localSearch2 != "test5_2" || 
				globalSearch2 != "test5_2" || 
				localSearchFail2 != null || 
				globalSearchFail2 != null)
				throw new Exception();

			System.out.println("Pass: " + testName + test5);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test5);
		}

		// Test: Adding Multiple Values and Scopes
		// Pass: All values are added and found by search
		// Description: Add two scopes each with a valid entry into the table 
		// and check that the local lookup method can't find entries not in 
		// the top scope. Check that the global lookup return the entries 
		// being searched for and return null for a non-existant entry
		String test6 = "Adding Multiple Values and Scopes";
		try {
			tbl.addScope();
			tbl.addDecl("test6_1", new Sym("test6_1"));
			tbl.addScope();
			tbl.addDecl("test6_2", new Sym("test6_2"));
			Sym localSearch1 = tbl.lookupLocal("test6_1"); 
			String globalSearch1 = tbl.lookupGlobal("test6_1").getType();
			Sym localSearchFail1 = tbl.lookupLocal("test6_1Fail"); 
			Sym globalSearchFail1 = tbl.lookupGlobal("test6_1Fail");
			
			String localSearch2 = tbl.lookupLocal("test6_2").getType(); 
			String globalSearch2 = tbl.lookupGlobal("test6_2").getType();
			Sym localSearchFail2 = tbl.lookupLocal("test6_2Fail"); 
			Sym globalSearchFail2 = tbl.lookupGlobal("test6_2Fail");

			if (localSearch1 != null || 
				globalSearch1 != "test6_1" || 
				localSearchFail1 != null || 
				globalSearchFail1 != null)
				throw new Exception();

			if (localSearch2 != "test6_2" || 
				globalSearch2 != "test6_2" || 
				localSearchFail2 != null || 
				globalSearchFail2 != null)
				throw new Exception();

			System.out.println("Pass: " + testName + test6);
			passCount++;
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test6);
		}
		
		// Test: Check Search Returns First
		// Pass: The item returned by lookup is the first associated Sym
		// Description: Add two scopes each with a valid entry with the same 
		// key. Check to see that the global lookup returns the first one 
		// from the top.
		String test7 = "Check Search Returns First";
		try {
			tbl.addScope();
			tbl.addDecl("test7", new Sym("test7_bottom"));
			tbl.addScope();
			tbl.addDecl("test7", new Sym("test7_top"));
			String resultL = tbl.lookupLocal("test7").getType();
			String resultG = tbl.lookupGlobal("test7").getType();
			if (resultL != resultG)
				throw new Exception();
			System.out.println("Pass: " + testName + test7);
			passCount++;			
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test7);
		}
	}

}
