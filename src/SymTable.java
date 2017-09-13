import java.util.*;

public class SymTable {

	// Symbol Table
	private List<HashMap<String,Sym>> symTable;
	
	// This is the constructor; it should initialize the SymTable's List field 
	// to contain a single, empty HashMap.
	public SymTable() {
		symTable = new LinkedList<HashMap<String, Sym>>();
		addScope();
	}
	
	// If this SymTable's list is empty, throw an EmptySymTableException. If 
	// either name or sym (or both) is null, throw a NullPointerException. If
	// the first HashMap in the list already contains the given name as a key,
	// throw a DuplicateSymException. Otherwise, add the given name and sym to
	// the first HashMap in the list.
	public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException {
		if (name == null || sym == null)
			throw new NullPointerException();
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		if (symTable.get(0).containsKey(name))
			throw new DuplicateSymException();
		symTable.get(0).put(name, sym);
	}
	
	// Add a new, empty HashMap to the front of the list.
	public void addScope() {
		symTable.add(0, new HashMap<String,Sym>());
	}
	
	// If this SymTable's list is empty, throw an EmptySymTableException.
	// Otherwise, if the first HashMap in the list contains name as a key, 
	// return the associated Sym; otherwise, return null.
	public Sym lookupLocal(String name) throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		return symTable.get(0).get(name);
	}
	
	// If this SymTable's list is empty, throw an EmptySymTableException. If
	// any HashMap in the list contains name as a key, return the first 
	// associated Sym (i.e., the one from the HashMap that is closest to the
	// front of the list); otherwise, return null.
	public Sym lookupGlobal(String name) throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		for (HashMap<String, Sym> m : symTable) {
			if (m.containsKey(name))
				return m.get(name);
		}
		return null;
	}
	
	// If this SymTable's list is empty, throw an EmptySymTableException;
	// otherwise, remove the HashMap from the front of the list. To clarify,
	// throw an exception only if before attempting to remove, the list is 
	// empty (i.e. there are no HashMaps to remove).
	public void removeScope() throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		symTable.remove(0);
	}
	
	// This method is for debugging. First, print “\nSym Table\n”. Then, for
	// each HashMap M in the list, print M.toString() followed by a newline. 
	// Finally, print one more newline. All output should go to System.out.
	public void print() {
		System.out.print("\nSym Table\n");
		symTable.forEach(m -> System.out.println(m.toString()));
	}
}
