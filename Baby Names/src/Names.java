import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Names {
	
	private ArrayList<NameRecord> names = new ArrayList<NameRecord>();
	int baseYear;
	int numOfRanks;
	final int interval = 11;


	public Names(Scanner fileScanner) {
		baseYear = fileScanner.nextInt();
		numOfRanks = fileScanner.nextInt();
		String line;
		while(fileScanner.hasNextLine()){
		    line = fileScanner.nextLine();
		    NameRecord record = new NameRecord(new Scanner(line), baseYear, numOfRanks,11);
		    // check the validation for the record obj
		    ArrayList<Integer> pop = new ArrayList<Integer>();
		    Scanner linesc = new Scanner(line);
		    String name = linesc.next();
		    while (linesc.hasNext()){
		    	pop.add(linesc.nextInt());
		    }
		    if (pop.size() == numOfRanks)
		    	names.add(record);
		}
		
	
	}
	
    /**
     * Returns an ArrayList of NameRecord objects that contain a 
     * given substring, ignoring case.  The names must be in sorted order based 
     * on name.
     * @param partialName != null, partialName.length() > 0
     * @return an ArrayList of NameRecords whose names contains
     * partialName. If there are no NameRecords that meet this
     * criteria returns an empty list. 
     */
    
    public ArrayList<NameRecord> getMatches(String partialName) {
    	if (partialName == null || partialName.length() == 0)
    		throw new IllegalArgumentException("fail the precondition");
    	String lowerCaseName = partialName.toLowerCase();
		ArrayList<NameRecord> Names = new ArrayList<NameRecord>();
		
		for (int i = 0; i < names.size(); i++) {
			NameRecord record = names.get(i);
			String name = record.getName();
			if(name.toLowerCase().contains(lowerCaseName)) {	// contains the partial name ?
				Names.add(record);
			}
		}
		
		Collections.sort(Names);
		System.out.println(Names);
		return Names;
        
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings  must be in sorted 
     * order based on name. 
     * @return A list of the names that have been ranked in the top
     * 1000 or better in every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    
    public ArrayList<String> rankedEveryDecade() {
    	ArrayList<String> Names = new ArrayList<String>();
		
		for (int i = 0; i < names.size(); i++) {
			NameRecord record = names.get(i);
			// call the appearAlways() to check whether appear in every decade
			ArrayList<Integer> ranks = record.ranks;
			if(appearAlways(ranks)) {
				Names.add(record.getName());
			}
		}
		Collections.sort(Names);
		return Names;
        
    }
    
    private boolean appearAlways(ArrayList<Integer> ranks) {
    	for (int i = 0; i < ranks.size(); i++) {
    		if (ranks.get(i) == 0)
    			return false;
			
		}
    	return true;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the 
     * top 1000 or better in exactly one decade. The Strings must be in sorted 
     * order based on name. 
     * @return A list of the names that have been ranked in the top
     * 1000 or better in exactly one decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {
    	ArrayList<String> Names = new ArrayList<String>();
    	for (int i = 0; i < names.size(); i++) {
    		NameRecord record = names.get(i);
    		ArrayList<Integer> ranks = record.ranks;
    		if (appearOnce(ranks))
    			Names.add(record.name);
    		
		}
    	Collections.sort(Names);
    	return Names;
        
    }
    
    private boolean appearOnce(ArrayList<Integer> ranks) {
    	int count = 0;
    	for (int i = 0; i < ranks.size(); i++) {
    		if (ranks.get(i) != 0)
    			count++;
		}
    	if (count == 1)
    		return true;
    	return false;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting more
     * popular every decade. The Strings  must be in sorted order based on name.
     * @return A list of the names that have been getting more popular in
     * every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list. 
     */
    public ArrayList<String> alwaysMorePopular() {
    	ArrayList<String> Names = new ArrayList<String>();
    	for (int i = 0; i < names.size(); i++) {
    		NameRecord record = names.get(i);
    		if (record.isMorePop())
    			Names.add(record.name);
    		
		}
    	Collections.sort(Names);
    	return Names;
        
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting less
     * popular every decade. The Strings  must be in sorted order based on name.
     * @return A list of the names that have been getting less popular in
     * every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list. 
     */
    public ArrayList<String> alwaysLessPopular() {
    	ArrayList<String> Names = new ArrayList<String>();
    	for (int i = 0; i < names.size(); i++) {
    		NameRecord record = names.get(i);
    		if (record.isLessPop())
    			Names.add(record.name);
    		
		}
    	Collections.sort(Names);
    	return Names;
        
    }

}
