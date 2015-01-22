import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public class CollectionsValuesTest {

	public static void main(String[] args) {
		Map<String,Integer> map = new TreeMap<String,Integer>();
		map.put("1", 3);
		map.put("2", 1);
		//Collection<Integer> valuesArray = new ArrayList<Integer>();// is this polymopisem?? like Map
		// Map<> = TreeMap<>...

		Collections.sort(map.values());
	}

}
