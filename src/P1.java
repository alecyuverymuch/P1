
public class P1 {

	private static Sym sym;
	private static SymTable tbl;
	
	public static void main(String[] args) {
		UnitTest_sym();
		UnitTest_addScopeAndRemoveScope();
		UnitTest_addDeclAndLookup();
	}
	
	public static void UnitTest_sym() {
		String testName = "UnitTest_sym - ";
		sym = new Sym("test");
		
		// Test: Get Type
		// Pass: If sym.getType() = "test"
		String test1 = "Get Type";
		String result1 = sym.getType().equals("test") ? "Pass: " : "Fail: ";
		System.out.println(result1 + testName + test1);
		
		// Test: Check toSting
		// Pass: If sym.toString() = "test"
		String test2 = "Check toSting";
		String result2 = sym.toString().equals("test") ? "Pass: " : "Fail: ";
		System.out.println(result2 + testName + test2);
	}
	
	public static void UnitTest_addScopeAndRemoveScope() {
		String testName = "UnitTest_addScopeAndRemoveScope - ";
		tbl = new SymTable();
		
		// Test: Remove Constructor Initialized Scope
		// Pass: If successfully removed
		String test1 = "Remove Constructor Initialized Scope";
		try {
			tbl.removeScope();
			System.out.println("Pass: " + testName + test1);
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test1);
		}
		
		// Test: Remove Scope From Empty List
		// Pass: If EmptySymTableException is thrown
		String test2 = "Remove Scope From Empty List";
		try {
			tbl.removeScope();
			System.out.println("Fail: " + testName + test2);
		} catch (EmptySymTableException e) {
			System.out.println("Pass: " + testName + test2);
		}
		
		// Test: Add and Remove New Scope
		// Pass: Add and successfully remove new scope
		String test3 = "Add and Remove New Scope";
		tbl.addScope();
		try {
			tbl.removeScope();
			System.out.println("Pass: " + testName + test3);
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test3);
		}
	}
	
	public static void UnitTest_addDeclAndLookup() {
		String testName = "UnitTest_addDeclAndLookup - ";
		tbl = new SymTable();
		
		// Test: Adding Null Values
		// Pass: If NullPointerExceptions are thrown
		String test1 = "Adding Null Values";
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
		String test2 = "Adding to an Empty List";
		try {
			tbl.removeScope();
			tbl.addDecl("test2", new Sym("test2"));
			System.out.println("Fail: " + testName + test2);
			tbl.addScope();
		} catch (EmptySymTableException e) {
			System.out.println("Pass: " + testName + test2);
			tbl.addScope();
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test2);
			tbl.addScope();
		}
		
		//Test: Adding a Valid Entry
		//Pass: The value is added and found by search
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
				globalSearchFail == null)
				System.out.println("Pass: " + testName + test3);
			else 
				System.out.println("Fail: " + testName + test3);
		} catch (Exception e) {
			System.out.println("Fail: " + testName + test3);
		}

		// Test: Add Duplicate Entries
		// Pass: DuplicateSymException is thrown
		String test4 = "Add Duplicate Entries";
		try { 
			tbl.addScope();
			tbl.addDecl("test4", new Sym("test4"));
			tbl.addDecl("test4", new Sym("test4"));
			System.out.println("Fail: " + testname + test4);
		} catch (DuplicateSymException e) {
			System.out.println("Pass: " + testname + test4);
		} catch (Exception e) {
			System.out.println("Fail: " + testname + test4);
		}

		// Test: Adding Multiple Entries
		// Pass: The values are added and found by search
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

			System.out.println("Pass: " + testname + test5);
		} catch (Exception e) {
			System.out.println("Fail: " + testname + test5);
		}

		// Test: Adding Multiple Values and Scopes
		// Pass: All values are added and found by search
		String test6 = "Adding Multiple Values and Scopes";
		try {
			tbl.addScope();
			tbl.addDecl("test6_1", new Sym("test6_1"));
			tbl.addScope();
			tbl.addDecl("text6_2", new Sym("test6_2"));
			String localSearch1 = tbl.lookupLocal("test6_1").getType(); 
			String globalSearch1 = tbl.lookupGlobal("test6_1").getType();
			Sym localSearchFail1 = tbl.lookupLocal("test6_1Fail"); 
			Sym globalSearchFail1 = tbl.lookupGlobal("test6_1Fail");

			String localSearch2 = tbl.lookupLocal("test6_2").getType(); 
			String globalSearch2 = tbl.lookupGlobal("test6_2").getType();
			Sym localSearchFail2 = tbl.lookupLocal("test6_2Fail"); 
			Sym globalSearchFail2 = tbl.lookupGlobal("test6_2Fail");

			if (localSearch1 != null || 
				globalSearch1 != "test5_1" || 
				localSearchFail1 != null || 
				globalSearchFail1 != null)
				throw new Exception();

			if (localSearch2 != "test5_2" || 
				globalSearch2 != "test5_2" || 
				localSearchFail2 != null || 
				globalSearchFail2 != null)
				throw new Exception();

			System.out.println("Pass: " + testname + test6);
		} catch (Exception e) {
			System.out.println("Fail: " + testname + test6);
		}
	}

}
