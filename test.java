import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Object;


public class test {
	
	public static ArrayList<ArrayList<Integer>> combine(ArrayList<ArrayList<Integer>> fst, ArrayList<ArrayList<Integer>> snd) {
		ArrayList<ArrayList<Integer>> cons = new ArrayList<ArrayList<Integer>>();
		if(fst.get(fst.size() - 1).get(fst.get(fst.size() - 1).size() - 1) <= snd.get(0).get(0)) {
			ArrayList<Integer> inner = new ArrayList<Integer>();
			inner.addAll(fst.get(fst.size() - 1));
			inner.addAll(snd.get(0));
			fst.remove(fst.get(fst.size() - 1));
			snd.remove(0);
			cons.addAll(fst);
			cons.add(inner);
			cons.addAll(snd);
		} else {
			cons.addAll(fst);
			cons.addAll(snd);
		}
		return cons;
	}
	
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> input) {
		if(input.size() > 1) {
			ArrayList<ArrayList<Integer>> fst = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> snd = new ArrayList<ArrayList<Integer>>();
			fst.addAll(input.subList(0, input.size()/2));
			snd.addAll(input.subList(input.size()/2, input.size()));
			ArrayList<ArrayList<Integer>> res1 = solve(fst);
			ArrayList<ArrayList<Integer>> res2 = solve(snd);
			ArrayList<ArrayList<Integer>> answer = combine(res1, res2);
			for(int i=0; i < answer.size(); i++) {
				System.out.println("[");
				for(int j=0; j < answer.get(i).size(); j++) {
					System.out.println(answer.get(i).get(j));
				}
				System.out.println("]");
			}
			System.out.println("-----------------------");
			return answer;
		} else {
			return input;
		}
	}
	
	public static void main(String args[]) {
		
		String inputTxt = args[0];
		String outputTxt = args[1];
		String line = null;
		
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		
		try {
            FileReader reader = new FileReader(inputTxt);
            BufferedReader bufferedReader = new BufferedReader(reader);

            bufferedReader.readLine();
            
            while((line = bufferedReader.readLine()) != null) {
                ArrayList<Integer> in = new ArrayList<Integer>();
                Integer store = Integer.parseInt(line);
                in.add(store);
                input.add(in);
            }
            bufferedReader.close();
            ArrayList<ArrayList<Integer>> result = solve(input);
            ArrayList<Integer> sol = new ArrayList<Integer>();
    		int maxLength = 0;
    		int count = 1;
    		int day = 1;
    		for (ArrayList<Integer> ar : result) {
    			if(ar.size() > maxLength) {
    				sol = ar;
    				maxLength = ar.size();
    				day = count;
    			}
    			count += ar.size();
    		}

    		PrintWriter writer = new PrintWriter(outputTxt, "UTF-8");
    		writer.println(maxLength);
    		writer.println(day);
    		for(int k : sol) {
        		writer.println(k);
    		}
            writer.close();         
        }
        catch(Exception e) {
        	e.printStackTrace();                             
        }

	}
	
}
