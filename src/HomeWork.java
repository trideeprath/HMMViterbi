import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomeWork {
	public static ArrayList<String> stringArrayList = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> allStatesObservationArrayList = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> allStatesArrayList = new ArrayList<String>();
	public static ArrayList<String> allObservationArrayList =  new ArrayList<String>();
	public static HashMap<String,Integer> stateCountHashMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> stateStateCountHashMap = new HashMap<String, Integer>();
	public static HashMap<String,Double> stateProbablityHashMap = new HashMap<String, Double>();
	public static HashMap<String, Double> stateStateProbabilityHashMap = new HashMap<String, Double>();
	public static HashMap<String, Double> aijHashMap = new HashMap<String, Double>();
	public static HashMap<String, Integer> observationStateCountHashMap = new HashMap<String, Integer>();
	public static HashMap<String, Double> bijHashMap = new HashMap<String, Double>();
	public static Set<String> statesSet = new HashSet<String>();
	public static ArrayList<ArrayList<String>> allObservationArrayListList = new ArrayList<ArrayList<String>>();
	
	
	public static ArrayList<String> testStringArrayList = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> testAllStatesObservationArrayList = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> testAllObservationArrayListList = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> testAllStatesArrayListList = new ArrayList<ArrayList<String>>();

	
	public static void main(String[] args) throws IOException {
		
		//Reading file from fileLocation
		String trainString = readFile("C:\\Users\\Trideep\\javaworkspace\\HomeWorkNLP\\src\\train.txt");
		String testString = readFile("C:\\Users\\Trideep\\javaworkspace\\HomeWorkNLP\\src\\train.txt");

		//String trainString = readFile("C:\\Trideep\\Classes\\NLP\\small.txt");
		
		
		
		
		System.out.println("================Training Started================");
		
		//Save in the file in ArrayList<String>
		String[] trainStringArray = trainString.split("###/###");
		for(String string: trainStringArray){
			stringArrayList.add(string);
		}
		
		//printArrayList(stringArrayList);
		
		for(String str: stringArrayList){
			String[] stringArray = str.split("\n");
			for(String str2 : stringArray){
				//System.out.print(str2 + " ");
				if(observationStateCountHashMap.containsKey(String.valueOf(str2))){
						if(String.valueOf(str2).contains("/")){
							String[] obsState = str2.split("/");
							str2 = obsState[0].toLowerCase() +"/"+ obsState[1];
							int countAtState = observationStateCountHashMap.get(String.valueOf(str2));
							observationStateCountHashMap.put(String.valueOf(str2),++countAtState);
						}
						
				}
				else{
					if(String.valueOf(str2).contains("/")){
						String[] obsState = str2.split("/");
						str2 = obsState[0].toLowerCase() + "/"+obsState[1];
						observationStateCountHashMap.put(String.valueOf(str2),1);
					}
				}
			}
			
			
			//System.out.println("");
		}
		
		//System.out.println(observationStateCountHashMap.size());
		//printMap(observationStateCountHashMap);
		
		
		
		//System.out.println(observationStateCountHashMap.size());
		
		//Set allStatesObservationArrayList having sentences in the form of [ [o1/q1,o2,q2 ..] , [o1/q1, o2/q2] , ...]
		for(String sentence : stringArrayList){
			String[] sentenceArray = sentence.split("\n");
			ArrayList<String> sentenceArrayList = new ArrayList<String>();
			for (int i = 0; i < sentenceArray.length; i++) {
				sentenceArrayList.add(sentenceArray[i]);
			}
			allStatesObservationArrayList.add(sentenceArrayList);
			
		}
		
		
		
		
		//Set allStatesArrayList 
		for(ArrayList<String> sentenceWithObservationAndStates : allStatesObservationArrayList){
			allStatesArrayList.add(getStates(sentenceWithObservationAndStates));
		}
		
		//Set allObservationArrayList
		for(ArrayList<String> sentenceWithObservationAndStates : allStatesObservationArrayList){
			allObservationArrayList.add(getObservation(sentenceWithObservationAndStates));
		}

		//Set allObservationArrayListList
		for(ArrayList<String> sentenceWithObservationAndStates : allStatesObservationArrayList){
				allObservationArrayListList.add(getObservationArrayList(sentenceWithObservationAndStates));
		}
			
		
		/*
		for(ArrayList<String> arr :allObservationArrayListList){
			
			printArrayList(arr);
		}
		*/
		
		
		/***This is for the test string 
		 * This generates all the data required for processing of 
		 * test String 
		 */
		
		String[] testStringArray = testString.split("###/###");
		for(String string: trainStringArray){
			testStringArrayList.add(string);
		}
		
		for(String sentence : testStringArrayList){
			String[] sentenceArray = sentence.split("\n");
			ArrayList<String> sentenceArrayList = new ArrayList<String>();
			for (int i = 0; i < sentenceArray.length; i++) {
				sentenceArrayList.add(sentenceArray[i]);
			}
			testAllStatesObservationArrayList.add(sentenceArrayList);
			
		}
		
		//Set allObservationArrayListList
		for(ArrayList<String> testSentenceWithObservationAndStates : testAllStatesObservationArrayList){
			testAllObservationArrayListList.add(getObservationArrayList(testSentenceWithObservationAndStates));
		}
		
		for(ArrayList<String> testSentenceWithObservationAndStates : testAllStatesObservationArrayList){
			testAllStatesArrayListList.add(getStateArrayList(testSentenceWithObservationAndStates));
		}
		
		
		
		
		
		
				
//		/printArrayList(allStatesArrayList);
		
		//set all unique states 
		for(String sentence : allStatesArrayList){
			for(char state : sentence.toCharArray()){
				statesSet.add(String.valueOf(state));
			}
		}
		
		/*
		System.out.println(statesSet.size());
		
		Iterator ite = statesSet.iterator();
		int count=0;
		while(ite.hasNext()){
			String state = ite.next().toString();
			System.out.println(++count + " " + state );
			 
		}
		
		*/
		
		//printArrayList(allObservationArrayList);
		
		//System.out.println(allStatesArrayList.size());
		
		/*
		for(int i =0 ; i< allStatesArrayList.size() ; i++){
			System.out.println(allStatesArrayList.get(i).length()+ " "+  allStatesArrayList.get(i));
			System.out.println(allObservationArrayList.get(i).length() + " " + allObservationArrayList.get(i));
		}
		*/
		
		// Set the count of all the states in stateCountHashMap
		for(String statesString : allStatesArrayList ){
			for(char state: statesString.toCharArray()){
				if(stateCountHashMap.containsKey(String.valueOf(state))){
					int countAtState = stateCountHashMap.get(String.valueOf(state));
					stateCountHashMap.put(String.valueOf(state),++countAtState);
				}
				else{
					stateCountHashMap.put(String.valueOf(state),1);
				}
			}
		}
		
		//printMap(stateCountHashMap);
		
		
		for(String statesString : allStatesArrayList ){
			int j;
			for(int i =0 ; i<statesString.length() -1 ; i++){
				j = i+1;
				String first = String.valueOf(statesString.charAt(i));
				String second = String.valueOf(statesString.charAt(j));
				String key = first+second;
				if(stateStateCountHashMap.containsKey(key)){
					int countAtState = stateStateCountHashMap.get(key);
					stateStateCountHashMap.put(key,++countAtState);
				}
				else{
					stateStateCountHashMap.put(key,1);
				}
			}
		}
		
		//System.out.println(stateStateCountHashMap.size());
		//printMap(stateStateCountHashMap);
		
		Iterator it = stateCountHashMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        stateProbablityHashMap.put(pair.getKey().toString(), 	
	        		calculateProbability(pair.getKey().toString(), stateCountHashMap));
	    }
	    
	    
	    //printMap(stateProbablityHashMap);
	    //printMapSum(stateProbablityHashMap);
	    //printMapSumInt(stateCountHashMap);
	    //printMapSumInt(stateStateCountHashMap);
	    //System.out.println(stateStateCountHashMap.size());
	    
	    /*
	    Iterator it1 = stateStateCountHashMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it1.next();
	        stateStateProbabilityHashMap.put(pair.getKey().toString(), 	
	        		jointProbability(pair.getKey().toString(), stateStateCountHashMap));
	    }
	    */
	    
	    
	    Iterator iterator = stateStateCountHashMap.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry pair = (Map.Entry)iterator.next();
	        String key = pair.getKey().toString();
	        StringBuilder sb = new StringBuilder(key);
	        String reverseKey = sb.reverse().toString(); 
	        //System.out.println(key);
	        if(stateStateCountHashMap.containsKey(reverseKey)){
	        	double countOfStateState = Double.valueOf(stateStateCountHashMap.get(reverseKey));
	        	//double countOfStateState = Double.valueOf(pair.getValue().toString());
	        	double countOfState = Double.valueOf(stateCountHashMap.get(String.valueOf(key.charAt(1))));
	        	if(countOfState < countOfStateState){
	        		System.out.println("Error");
	        	}
	        	//System.out.println(key + " : " + countOfStateState + " " + countOfState + " " + countOfStateState/countOfState  );     
	        	aijHashMap.put(key, countOfStateState/countOfState);
	        }
	        else{
	        	aijHashMap.put(key, 0d);
	        }
	    }
	    
	    //printMap(aijHashMap);
	    //System.out.println(aijHashMap.size());
	    
	    
	    

		//Calculate Bij we have observationStateCountHashMap for calculating P(ON) , we have stateCountHashMap for calculation P(N) . 
		// We would put this data into bijHashMap
		Iterator iteratorOnObsState = observationStateCountHashMap.entrySet().iterator();
		while(iteratorOnObsState.hasNext()){
			Map.Entry pair = (Map.Entry) iteratorOnObsState.next();
			String key = pair.getKey().toString();
			String state = key.split("/")[1];
			double observationStateCount = Double.valueOf(pair.getValue().toString());
			double stateCount = Double.valueOf(stateCountHashMap.get(state));
			double observationStateProbability  = observationStateCount/stateCount;
			bijHashMap.put(key, observationStateProbability);
					
		}
		
		System.out.println("==============Training Completed ====================");
		System.out.println("==============Model generated for HMM=================");
		
		
		System.out.println("=====Aij====");
		//printMap(aijHashMap);
		
		System.out.println("=====Bjk====");
		//printMap(bijHashMap);
		System.out.println(bijHashMap.size());
		
		
		statesSet.add("<startState>");
		statesSet.add("<endState>");
		//String input = "bank will close soon";
		String input = "As a result , the insurance market plans new measures to restrict the ability of syndicate officials to leave years open .";
		//String input = "When such claims and litigation extend beyond the period , the syndicates can extend their accounting deadlines .";
		//printArrayList(allObservationArrayList);
	
		/*
		for(int i =1 ; i<allObservationArrayListList.size() ; i++){
			ArrayList<String> mostProbStateSeq = viterbi(input, allObservationArrayListList.get(i));
		}
		*/
		
		System.out.println("============ Testing Started =============");
		int count=0;
		int accuracyCount=0;
		int errorCount=0;
		for(int i =1 ; i<testAllObservationArrayListList.size()-1 ; i++){
			//printArrayList(testAllStatesObservationArrayList.get(i));
			System.out.print("Observation   :");
			printArrayList(testAllStatesArrayListList.get(i));
			ArrayList<String> mostProbStateSeq = viterbi(input, testAllObservationArrayListList.get(i));
			System.out.print("Prediction   :");
			printArrayList(mostProbStateSeq);
			System.out.println("===============================================================");
			ArrayList<String> actual = testAllStatesArrayListList.get(i);
			ArrayList<String> predicted = mostProbStateSeq;
			
			if(actual.size() == predicted.size()){
				for(int j =0 ; j < predicted.size() ; j++){
					count++;
					if(actual.get(j).toLowerCase().equals(predicted.get(j).toLowerCase())){
						accuracyCount++;
					}
					else{
						errorCount++;
					}
				}
			}
			//System.out.println("AC : " + accuracyCount + " C : " + count + " accuracy " + accuracyCount/count);
			//System.out.println("==================================");
		}
		
		System.out.println("================Result================");
		System.out.println("Accuracy Rate " + (Double.valueOf(accuracyCount))/Double.valueOf(count));
		System.out.println("Error Rate " + (Double.valueOf(errorCount)/Double.valueOf(count)));
		System.out.println("Accuracy Percentage " + (Double.valueOf(accuracyCount)) *100/Double.valueOf(count));
		System.out.println("Error Percentage " + (Double.valueOf(errorCount) * 100/Double.valueOf(count)));

	}
	
	
	

	private static ArrayList<String> viterbi(String sentence , ArrayList<String> observationArrayListTemp) {
	
		
		// TODO Auto-generated method stub
		ArrayList<String> mostProbStateSeq = new ArrayList<String>();
		//int T = sentence.split(" ").length; 
		//int N = statesSet.size();
		
		
		//Creating observation List from input sentence
		sentence = "<start> " + sentence; 				//add start to the observation
		String[] observationArray = sentence.split(" ");
		ArrayList<String> observationArrayList = new ArrayList<String>();
		for(String str : observationArray){
			observationArrayList.add(str);
		}
		
		
		// for debugging
		observationArrayList.clear();
		//observationArrayList = allObservationArrayListList.get(1);
		observationArrayList = observationArrayListTemp;
		observationArrayList.add(0, "<start>");
		int T = observationArrayList.size()-1;
		int N = statesSet.size();
		//printArrayList(observationArrayList);
		
		
		//creating stateArrayList 
		ArrayList<String> stateArrayList = setStateArrayList(statesSet);
		//printArrayList(stateArrayList);
		
		//Tables for SeqStore and BackTrack
		Double[][] seqStore = new Double[N][T+1];
		Integer[][] backTrack = new Integer[N][T+1];
		
		//Initializing the SeqStore and BackTrack 
		seqStore[0][0] = 1d;
		backTrack[0][0] = 0;
		for(int i =1 ; i < N ; i++){
			seqStore[i][0] = 0d;
		}
		
		
		
		
		//Iteration 
		for(int t =1 ; t < T+1 ; t++){
			for(int s = 0 ; s < N ; s++){
				ArrayList<Double> allProb = new ArrayList<Double>();
				for(int j =0 ; j < N ; j++){
					
					double oldvalue = seqStore[j][t-1];
					
					double obsProbability =0.000003d; // default value taken small
					String obskey = observationArrayList.get(t)+"/"+ stateArrayList.get(s);
					if(bijHashMap.containsKey(obskey)){
						obsProbability = bijHashMap.get(obskey);
					}
					
					double stateStateProbability = 0.00000000001d;
					String stateStateKey = stateArrayList.get(s) + stateArrayList.get(j);
					
					if(aijHashMap.containsKey(stateStateKey)){
						stateStateProbability = aijHashMap.get(stateStateKey);
					}
					
					double probScore = oldvalue * obsProbability * stateStateProbability;
					allProb.add(probScore);
				}
				
				//double max = Double.valueOf(Collections.max(allProb));
				int maxIndex = findMaxIndex(allProb);
				//seqStore[s][t] = max;
				//System.out.println(max + " : " + allProb.get(maxIndex) );
				seqStore[s][t] = allProb.get(maxIndex);
				backTrack[s][t] = maxIndex;
				allProb.clear();
				
				
			}
		}
		
		//printArrayList(stateArrayList);
		//System.out.println(Arrays.deepToString(seqStore));
		//System.out.println(Arrays.deepToString(backTrack));
		
		//printMap(bijHashMap);
		//printMap(aijHashMap);
		
		
		
		int[] c = new int[T+1];
		
		for(int i = T-1 ; i> 0 ; i--){
			c[i] = backTrack[c[i+1]][i+1];
		}
		
		//printArrayList(mostProbStateSeq);
		
		
		for(int i = 1 ; i < c.length -1  ; i++){
			//System.out.println(c[i] + " : " + stateArrayList.get(c[i]));
			mostProbStateSeq.add(stateArrayList.get(c[i]));
		}
		
		if(mostProbStateSeq.size()!=0){
			mostProbStateSeq.add(".");
		}

		
		//printArrayList(stateArrayList);
						
		
		
		return mostProbStateSeq;
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	









	private static int findMaxIndex(ArrayList<Double> allProb) {
		// TODO Auto-generated method stub
		double max = allProb.get(0);
		int maxIndex = 0;
		for(int i = 0; i < allProb.size(); i++) {
		      if(allProb.get(i) > max) {
		         maxIndex = i;
		         max = allProb.get(maxIndex);
		      }
		}
		return maxIndex;
	}






	private static ArrayList<String> setStateArrayList(Set<String> statesSet2) {
		Set<String> set = new HashSet<String>();
		set = statesSet2;
		ArrayList<String> arrayList = new ArrayList<String>();
		Iterator it = set.iterator();
		while(it.hasNext()){
			String state = it.next().toString();
			arrayList.add(state);
		}
		
		
		return arrayList;
	}






	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

	public static void printArrayList(ArrayList<String> stringArrayList){
		for(String str: stringArrayList){
			System.out.print(str+ " ");
		}
		System.out.println("");
	}

	public static void printObservation(ArrayList<String> stateObservationArrayList){
		StringBuilder observation = new StringBuilder("");
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			observation.append(stateObservationArrayList.get(i).split("/")[0] +" ");
		}
		System.out.println(observation);
	}
	
	public static void printStates(ArrayList<String> stateObservationArrayList){
		StringBuilder states = new StringBuilder("");
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			if(stateObservationArrayList.get(i).split("/").length >1){
				states.append(stateObservationArrayList.get(i).split("/")[1] +" ");
			}
		}
		
		System.out.println(states);
	}
	
	public static String getStates(ArrayList<String> stateObservationArrayList){
		StringBuilder states = new StringBuilder("");
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			if(stateObservationArrayList.get(i).split("/").length >1){
				states.append(stateObservationArrayList.get(i).split("/")[1]);
			}	
		}
		return states.toString();
	}

	public static String getObservation(ArrayList<String> stateObservationArrayList){
		StringBuilder observation = new StringBuilder("");
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			if(stateObservationArrayList.get(i).split("/").length >1){
				observation.append(stateObservationArrayList.get(i).split("/")[0].toLowerCase());
			}	
		}
		return observation.toString();
	}
	
	public static ArrayList<String> getObservationArrayList(ArrayList<String> stateObservationArrayList){
		ArrayList<String> observation = new ArrayList<String>();
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			if(stateObservationArrayList.get(i).split("/").length >1){
				observation.add(stateObservationArrayList.get(i).split("/")[0].toLowerCase());
			}	
		}
		return observation;
	}


	public static ArrayList<String> getStateArrayList(ArrayList<String> stateObservationArrayList){
		ArrayList<String> observation = new ArrayList<String>();
		for(int i =0 ; i < stateObservationArrayList.size() ; i++ ){
			if(stateObservationArrayList.get(i).split("/").length >1){
				observation.add(stateObservationArrayList.get(i).split("/")[1].toLowerCase());
			}	
		}
		return observation;
	}
	
	
	public static void printMap(Map mp) {
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + "=" + pair.getValue());
	        //System.out.println(pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	public static void printMapSum(Map<String,Double> mp) {
	    Iterator it = mp.entrySet().iterator();
	    double sum =0;
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        sum = sum +Double.valueOf(pair.getValue().toString());
	        //System.out.println(pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    System.out.println(sum);
	}
	
	public static void printMapSumInt(Map<String,Integer> mp) {
	    Iterator it = mp.entrySet().iterator();
	    int sum =0;
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        sum = sum +Integer.valueOf(pair.getValue().toString());
	        //System.out.println(pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    System.out.println(sum);
	}
	
	public static Double jointProbability(String key, Map<String,Integer> tempHashMap){
	
		int sum = 0;
		Iterator it = tempHashMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        sum = sum + Integer.valueOf(pair.getValue().toString());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    return Double.valueOf((tempHashMap.get(key))/Double.valueOf(sum));
	    
	    
	}
	
	public static Double calculateProbability(String key,Map<String, Integer> tempHashMap){
		int sum = 0;
		Iterator it = tempHashMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        sum = sum + Integer.valueOf(pair.getValue().toString());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    return Double.valueOf(tempHashMap.get(key))/Double.valueOf(sum);
	    
	}
	
	public static void printSize(){
		System.out.println(stateStateCountHashMap.size());
	}

	
}
