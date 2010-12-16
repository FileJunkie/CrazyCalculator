package name.filejunkie.CrazyCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Z9Digit {
	private static ArrayList<ArrayList<LinkedList<Integer>>> addAnswers = null;
	private static ArrayList<ArrayList<LinkedList<Integer>>> mulAnswers = null;
	
	static {
		if(addAnswers == null){
			addAnswers = new ArrayList<ArrayList<LinkedList<Integer>>>();
			for(int i = 0; i < 9; i++){
				addAnswers.add(new ArrayList<LinkedList<Integer>>());
				for(int j = 0; j < 9; j++){
					addAnswers.get(i).add(new LinkedList<Integer>());
				}
			}
			
			for(int i = 0; i < 9; i++){
				addAnswers.get(0).get(i).add(i);	
			}
			
			addAnswers.get(1).get(1).add(5);
			addAnswers.get(1).get(2).add(7);
			addAnswers.get(1).get(3).add(2);
			addAnswers.get(1).get(4).add(0);
			addAnswers.get(1).get(5).add(4);
			addAnswers.get(1).get(6).add(6);
			addAnswers.get(1).get(7).add(3);
			addAnswers.get(1).get(8).add(8);
			
			addAnswers.get(2).get(2).add(3); addAnswers.get(2).get(2).add(7);
			addAnswers.get(2).get(3).add(7);
			addAnswers.get(2).get(4).add(3);
			addAnswers.get(2).get(5).add(3);
			addAnswers.get(2).get(6).add(6); addAnswers.get(2).get(6).add(8);
			addAnswers.get(2).get(7).add(3);
			addAnswers.get(2).get(8).add(6); addAnswers.get(2).get(8).add(8);
			
			addAnswers.get(3).get(3).add(2); addAnswers.get(3).get(3).add(7);
			addAnswers.get(3).get(4).add(7);
			addAnswers.get(3).get(5).add(7); 
			addAnswers.get(3).get(6).add(6); addAnswers.get(3).get(6).add(8); 
			addAnswers.get(3).get(7).add(2);  
			addAnswers.get(3).get(8).add(6); addAnswers.get(3).get(8).add(8);
			
			addAnswers.get(4).get(4).add(5);
			addAnswers.get(4).get(5).add(1);
			addAnswers.get(4).get(6).add(6);
			addAnswers.get(4).get(7).add(2);
			addAnswers.get(4).get(8).add(8);
			
			addAnswers.get(5).get(5).add(0);
			addAnswers.get(5).get(6).add(6);
			addAnswers.get(5).get(7).add(2);
			addAnswers.get(5).get(8).add(8);
			
			addAnswers.get(6).get(6).add(6); addAnswers.get(6).get(6).add(8);
			addAnswers.get(6).get(7).add(6); addAnswers.get(6).get(7).add(8);
			addAnswers.get(6).get(8).add(6); addAnswers.get(6).get(8).add(8);
			
			addAnswers.get(7).get(7).add(2); addAnswers.get(7).get(7).add(3);
			addAnswers.get(7).get(8).add(6); addAnswers.get(7).get(8).add(8);
			
			addAnswers.get(8).get(8).add(6); addAnswers.get(8).get(8).add(8);
			
			mulAnswers = new ArrayList<ArrayList<LinkedList<Integer>>>();
			for(int i = 0; i < 9; i++){
				mulAnswers.add(new ArrayList<LinkedList<Integer>>());
				for(int j = 0; j < 9; j++){
					mulAnswers.get(i).add(new LinkedList<Integer>());
				}
			}

			for(int i = 0; i < 9; i++){
				mulAnswers.get(0).get(i).add(0);
			}
			
			for(int i = 1; i < 9; i++){
				mulAnswers.get(1).get(i).add(i);
			}
			
			for(int i = 2; i < 9; i++){
				if(i != 6 && i != 8){
					mulAnswers.get(2).get(i).add(3);
					mulAnswers.get(2).get(i).add(7);
				}
				else{
					mulAnswers.get(2).get(i).add(6);
					mulAnswers.get(2).get(i).add(8);
				}
			}
			
			for(int i = 3; i < 9; i++){
				if(i != 6 && i != 8){
					mulAnswers.get(3).get(i).add(2);
					mulAnswers.get(3).get(i).add(7);
				}
				else{
					mulAnswers.get(3).get(i).add(6);
					mulAnswers.get(3).get(i).add(8);
				}
			}

			mulAnswers.get(4).get(4).add(1);
			mulAnswers.get(4).get(5).add(5);
			mulAnswers.get(4).get(6).add(6); mulAnswers.get(4).get(6).add(8);
			mulAnswers.get(4).get(7).add(2); mulAnswers.get(4).get(7).add(3);   
			mulAnswers.get(4).get(8).add(6); mulAnswers.get(4).get(8).add(8);
			
			mulAnswers.get(5).get(5).add(0);
			mulAnswers.get(5).get(6).add(6); mulAnswers.get(5).get(6).add(8);
			mulAnswers.get(5).get(7).add(2); mulAnswers.get(5).get(7).add(3);   
			mulAnswers.get(5).get(8).add(6); mulAnswers.get(5).get(8).add(8);
			
			for(int i = 6; i < 9; i++){
				for(int j = i; j < 9; j++){
					mulAnswers.get(i).get(j).add(6);
					mulAnswers.get(i).get(j).add(8);
				}
			}
			
			for(int i = 1; i < 9; i++){
				for(int j = 0; j < i; j++){
					addAnswers.get(i).get(j).addAll(addAnswers.get(j).get(i));
					mulAnswers.get(i).get(j).addAll(mulAnswers.get(j).get(i));
				}
			}
		}
		
	}

	public static Set<Integer> add(int a, int b) {
		if(a < 0 || a > 8 || b < 0 || b > 8){
			throw new IllegalArgumentException();
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i: addAnswers.get(a).get(b)){
			set.add(i);
		}
		
		return set;
	}
	
	public static Set<Integer> sub(int a, int b){
		if(a < 0 || a > 8 || b < 0 || b > 8){
			throw new IllegalArgumentException();
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < 9; i++){
			if(addAnswers.get(i).get(b).contains(a)){
				set.add(i);
			}
		}
		
		if(set.isEmpty()){
			throw new IllegalArgumentException("Cannot substract " + b + " from " + a);
		}
		
		return set;
	}

	public static Set<Integer> mul(int a, int b){
		if(a < 0 || a > 8 || b < 0 || b > 8){
			throw new IllegalArgumentException();
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i : mulAnswers.get(a).get(b)){
			set.add(i);
		}
		
		return set;
	}
	
	public static Set<Integer> div(int a, int b){
		if(a < 0 || a > 8 || b < 0 || b > 8){
			throw new IllegalArgumentException();
		}
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < 9; i++){
			if(mulAnswers.get(i).get(b).contains(a)){
				set.add(i);
			}
		}
		
		if(set.isEmpty()){
			throw new IllegalArgumentException("Cannot divide " + a + " by " + b);
		}
		
		return set;
	}
	

}
