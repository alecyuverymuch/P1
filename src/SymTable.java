import java.util.*;

public class SymTable {

	// Symbol Table
	private List<HashMap<String,Sym>> symTable;
	
	/**
	 * Constructor initialize the SymTable's List field to contain a single, 
	 * empty HashMap.
	 */
	public SymTable() {
		symTable = new LinkedList<HashMap<String, Sym>>();
		addScope();
	}
	
	/**
	 * Adds a given name and sym to the first hashmap of the list. Throws 
	 * NullPointerException if either the name or sym are null, 
	 * EmptySymTableException if the list is empty, DuplicateSymException if
	 * the name being added is already in the hashmap
	 * @param name - key of the sym being added
	 * @param sym - value containing the sym to be added
	 * @throws DuplicateSymException - thrown when two of the same key
	 * @throws EmptySymTableException - thrown if operations are done on an
	 * empty table
	 */
	public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException {
		if (name == null || sym == null)
			throw new NullPointerException();
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		if (symTable.get(0).containsKey(name))
			throw new DuplicateSymException();
		symTable.get(0).put(name, sym);
	}
	
	/**
	 * Add a new, empty HashMap to the front of the list.
	 */
	public void addScope() {
		symTable.add(0, new HashMap<String,Sym>());
	}
	
	/**
	 * If the first hashmap contains name as a key, return the associated Sym.
	 * If not found, return null. Throws EmptySymTableException if the list is
	 * empty.
	 * @param name - a key to search for in the hashmap
	 * @return a Sym with the associated name as a key or null
	 * @throws EmptySymTableException - thrown if operations are done on an
	 * empty table
	 */
	public Sym lookupLocal(String name) throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		return symTable.get(0).get(name);
	}
	
	/**
	 * Search all hashmaps in the list for a key of name and return the 
	 * associated Sym, if not found return null. Throws an 
	 * EmptySymTableException if the table is empty
	 * @param name - a key to search for in the hashmap
	 * @return a Sym with the associated name as a key or null
	 * @throws EmptySymTableException - thrown if operations are done on an
	 * empty table
	 */
	public Sym lookupGlobal(String name) throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		for (HashMap<String, Sym> m : symTable) {
			if (m.containsKey(name))
				return m.get(name);
		}
		return null;
	}
	
	/**
	 * Removes the first hashmap from the list throws EmptySymTableException
	 * if the list is empty.
	 * @throws EmptySymTableException - thrown if operations are done on an
	 * empty table
	 */
	public void removeScope() throws EmptySymTableException {
		if (symTable.isEmpty())
			throw new EmptySymTableException();
		symTable.remove(0);
	}
	
	/**
	 * Prints the contents of the SymTable
	 */
	public void print() {
		System.out.print("\nSym Table\n");
		symTable.forEach(m -> System.out.println(m.toString()));
		System.out.println();
	}
}
