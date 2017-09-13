
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
		//Pass: The value is added and the output matches
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
		
	}

}
