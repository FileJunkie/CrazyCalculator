package name.filejunkie.CrazyCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Z9Number {
	private int value;
	private static ArrayList<ArrayList<LinkedList<Integer>>> answers = null;
	
	public Z9Number(int value) {
		if(value < 0 || value > 8){
			throw new IllegalArgumentException();
		}
		
		if(answers == null){
			answers = new ArrayList<ArrayList<LinkedList<Integer>>>();
			for(int i = 0; i < 9; i++){
				answers.add(new ArrayList<LinkedList<Integer>>());
				for(int j = 0; j < 9; j++){
					answers.get(i).add(new LinkedList<Integer>());
				}
			}
			
			answers.get(0).get(0).add(0);
			answers.get(0).get(1).add(1);
			answers.get(0).get(2).add(2);
			answers.get(0).get(3).add(3);
			answers.get(0).get(4).add(4);
			answers.get(0).get(5).add(5);
			answers.get(0).get(6).add(6);
			answers.get(0).get(7).add(7);
			answers.get(0).get(8).add(8);
			
			answers.get(1).get(1).add(5);
			answers.get(1).get(2).add(7);
			answers.get(1).get(3).add(2);
			answers.get(1).get(4).add(0);
			answers.get(1).get(5).add(4);
			answers.get(1).get(6).add(6);
			answers.get(1).get(7).add(3);
			answers.get(1).get(8).add(8);
			
			answers.get(2).get(2).add(2); answers.get(2).get(2).add(3); answers.get(2).get(2).add(7);
			answers.get(2).get(3).add(3); answers.get(2).get(3).add(7); answers.get(2).get(3).add(2);
			answers.get(2).get(4).add(3);
			answers.get(2).get(5).add(3);
			answers.get(2).get(6).add(6); answers.get(2).get(6).add(8);
			answers.get(2).get(7).add(7); answers.get(2).get(7).add(2); answers.get(2).get(7).add(3);
			answers.get(2).get(8).add(6); answers.get(2).get(8).add(8);
			
			answers.get(3).get(3).add(7); answers.get(3).get(3).add(2); answers.get(3).get(3).add(3);
			answers.get(3).get(4).add(7);
			answers.get(3).get(5).add(7); 
			answers.get(3).get(6).add(6); answers.get(3).get(6).add(8); 
			answers.get(3).get(7).add(2); answers.get(3).get(7).add(3); answers.get(3).get(7).add(7);  
			answers.get(3).get(8).add(6); answers.get(3).get(8).add(8);
			
			answers.get(4).get(4).add(5);
			answers.get(4).get(5).add(1);
			answers.get(4).get(6).add(6);
			answers.get(4).get(7).add(2);
			answers.get(4).get(8).add(8);
			
			answers.get(5).get(5).add(0);
			answers.get(5).get(6).add(6);
			answers.get(5).get(7).add(2);
			answers.get(5).get(8).add(8);
			
			answers.get(6).get(6).add(6); answers.get(6).get(6).add(8);
			answers.get(6).get(7).add(6); answers.get(6).get(7).add(8);
			answers.get(6).get(8).add(6); answers.get(6).get(8).add(8);
			
			answers.get(7).get(7).add(3); answers.get(7).get(7).add(7); answers.get(7).get(7).add(2);
			answers.get(7).get(8).add(6); answers.get(7).get(8).add(8);
			
			answers.get(8).get(8).add(6); answers.get(8).get(8).add(8);
		}
		
		this.setValue(value);
		
		
	}

	public Set<Integer> add(Z9Number e){
		return this.add(e.getValue());
	}

	public Set<Integer> add(int b) {
		int a = this.getValue();
		
		Set<Integer> set = new HashSet<Integer>();
		
		if(a > b){
			int c = a;
			a = b;
			b = c;
		}
		
		for(int i: answers.get(a).get(b)){
			set.add(i);
		}
		
		return set;		
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String toString(){
		return String.valueOf(value);
	}

}
