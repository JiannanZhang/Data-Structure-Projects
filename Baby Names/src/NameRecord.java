import java.util.ArrayList;
import java.util.Scanner;


public class NameRecord implements Comparable<NameRecord>{
	
	String name;
	ArrayList<Integer> ranks;
	int baseYear;
	private int size = ranks.size();
	int interval;
	
	public NameRecord(Scanner ls, int baseYear, int numOfRanks, int interval){
		name = ls.next();
		ranks = new ArrayList<Integer>(numOfRanks);
		while (ls.hasNext()){
			ranks.add(ls.nextInt());
		}
	}
	
	public String getName (){
		return name;
	}
	
	public int getBaseDecade(){
		return baseYear;
	}
	
	public int getRankForDecade(int y){
		return ranks.get(y);
	}
	
	public int bestRankDecade(){
		int bestIdx = size - 1;
		for (int i = bestIdx - 1; i >= 0; i--){
			if (ranks.get(i) < ranks.get(bestIdx))
				bestIdx =i;
		}
		return baseYear + bestIdx * 10;
	}
	
	public int numDecOfTop1000(){
		int total = 0;
		for (int i = 0; i < size; i++) {
			if (ranks.get(i) <= 1000)
				total++;
		}
		return total;
	}
	// a method that returns true if this name has been ranked in the top 1000 in only one decade
	public boolean isRanked1000Once(){
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (ranks.get(i) <= 1000)
				count++;
		}
		if (count == 1)
			return true;
		return false;
	}
	
	//a method that returns true if this name has been getting more popular every decade
	
//	public boolean isMorePop(){
//		int count = 0;
//		for (int i : ranks)
//			if (i == 0)
//				count++;
//		if (count == size)
//			return false; 
//		for (int i = 0; i < size; i++) {
//			if (ranks.get(i) == 0)
//				ranks.set(i,1001);
//		}
//		for (int i = 0; i < size - 1; i++) {
//			if (ranks.get(i) < ranks.get(i + 1))
//				return false;
//		}	
//		return true;
//	}
	
	// another clever way to do above
	public boolean isMorePop() {
		int startIdx = (ranks.get(0) == 0) ? 1 : 0;
		for (int i = startIdx; i < size - 1; i++) {
			if (ranks.get(i) < ranks.get(i + 1) || ranks.get(i) == 0)
				return false;
		}	
		return true;
	}
	
	//a method that returns true if this name has been getting less popular
	
	public boolean isLessPop() {
		int startIdx = (ranks.get(size - 1) == 0) ? (size - 2) : (size - 1);
		for (int i = startIdx; i >= 1; i--) {
			if (ranks.get(i) < ranks.get(i - 1) || ranks.get(i) == 0)
				return false;
		}	
		return true;
	}

	
	public String toString() {
		String ret = name + " ";
		for (int i = 0; i < size; i++) {
			ret += ranks.get(i) + " ";
		}
		return ret;
	}

	@Override
	public int compareTo(NameRecord record) {
		// TODO Auto-generated method stub
		if (record == null)
			throw new IllegalArgumentException("worng para");
		return this.name.compareTo(record.name); the order????
	}
	
}
		



