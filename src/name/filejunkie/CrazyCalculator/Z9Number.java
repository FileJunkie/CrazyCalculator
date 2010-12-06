package name.filejunkie.CrazyCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Z9Number {
	private ArrayList<Integer> number = null;
	final private static int width = 3;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Z9Number other = (Z9Number) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	public Z9Number(int a){
		if(a < 0){
			throw new IllegalArgumentException();
		}
		number = new ArrayList<Integer>();
		
		int digit;
		for(int i = 0; i < width && a != 0; i++){
			digit = a % 10;
			
			if(digit > 8){
				throw new IllegalArgumentException();
			}
			
			number.add(digit);
			
			a /= 10;
		}
		if(number.isEmpty()){
			number.add(0);
		}
		
	}
	
	private Z9Number(ArrayList<Integer> numb) {
		for(int i = numb.size() - 1; numb.get(i) == 0 && i != 0; i--){
			numb.remove(i);
		}
		while(numb.size() > width){
			numb.remove(numb.size() - 1);
		}
		this.number = numb;
		
	}

	@SuppressWarnings("unchecked")
	public Set<Z9Number> add(Z9Number b){
		ArrayList<Integer> numb1 = (ArrayList<Integer>)this.number.clone();
		ArrayList<Integer> numb2 = (ArrayList<Integer>)b.number.clone();
			
		Set<ArrayList<Integer>> results = Z9Number.add(numb1, numb2);
		Set<Z9Number> result = new HashSet<Z9Number>();
		for(ArrayList<Integer> res: results){
			Z9Number r = new Z9Number(0);
			while(res.size() > width){
				res.remove(res.size() - 1);
			}
			r.number = res;
			result.add(r);
		}
		for(ArrayList<Integer> a: results){
			for(int i = a.size() - 1; a.get(i) == 0 && i != 0; i--){
				a.remove(i);
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Z9Number> sub(Z9Number b){
		Set<Z9Number> results = new HashSet<Z9Number>();
		
		ArrayList<Integer> numb = new ArrayList<Integer>();
		for(int i = 0; i < width; i++){
			numb.add(0);
		}
		do{
			Z9Number candidate = new Z9Number((ArrayList<Integer>) numb.clone());
			for(Z9Number res: b.add(candidate)){
				if(res.equals(this)){
					results.add(candidate);
				}
			}
		}while(inc(numb));
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Z9Number> mul(Z9Number b){
		ArrayList<Integer> numb1 = (ArrayList<Integer>)this.number.clone();
		ArrayList<Integer> numb2 = (ArrayList<Integer>)b.number.clone();
		
		ArrayList<Set<Z9Number>> parts = new ArrayList<Set<Z9Number>>();
		for(int i = 0; i < numb2.size(); i++){
			Set<Z9Number> part = mul(numb1, numb2.get(i));
			for(int j = 0; j < i; j++){
				for(Z9Number a: part){
					a.number.add(0, 0);
					a.getClass();
				}
			}
			parts.add(part);
		}
		
		Set<Z9Number> result = new HashSet<Z9Number>();
		Set<Z9Number> tmp = new HashSet<Z9Number>();
		result.addAll(parts.remove(0));
		while(!parts.isEmpty()){
			for(Z9Number c: parts.remove(0)){
				for(Z9Number a: result){
					Set<Z9Number> tmp1 = a.add(c);
					for(Z9Number d: tmp1){
						while(d.number.size() > width){
							d.number.remove(d.number.size() - 1);
						}
						for(int i = d.number.size() - 1; d.number.get(i) == 0 && i != 0; i--){
							d.number.remove(i);
						}
					}
					tmp.addAll(tmp1);
				}
				result.clear();
				result.addAll(tmp);
				tmp.clear();
			}
		}
		
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public Set<Z9Number> div(Z9Number b){
		Set<Z9Number> results = new HashSet<Z9Number>();
		
		ArrayList<Integer> numb = new ArrayList<Integer>();
		for(int i = 0; i < width; i++){
			numb.add(0);
		}
		do{
			Z9Number candidate = new Z9Number((ArrayList<Integer>) numb.clone());
			for(Z9Number res: b.mul(candidate)){
				if(res.equals(this)){
					results.add(candidate);
				}
			}
		}while(inc(numb));
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Z9Number> inv(){
		Set<Z9Number> results = new HashSet<Z9Number>();
		
		ArrayList<Integer> numb = new ArrayList<Integer>();
		for(int i = 0; i < width; i++){
			numb.add(0);
		}
		do{
			Z9Number candidate = new Z9Number((ArrayList<Integer>) numb.clone());
			for(Z9Number res: this.mul(candidate)){
				if(res.number.size() == 1 && res.number.get(0) == 1){
					results.add(candidate);
				}
			}
		}while(inc(numb));
		
		return results;
	}
	
	private static boolean inc(ArrayList<Integer> array){
		int pointer = 0;
		boolean ready = true;
		
		do{
			ready = true;
			array.set(pointer, array.get(pointer) + 1);
			if(array.get(pointer) == 9){
				array.set(pointer, 0);
				pointer++;
				ready = false;
			}
		}while(!ready && pointer < width);
		if(!ready && pointer >= width){
			return false;
		}
		else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static Set<ArrayList<Integer>> add(ArrayList<Integer> number1, ArrayList<Integer> number2){
		while(number1.size() > number2.size()){
			number2.add(0);
		}
		while(number1.size() < number2.size()){
			number1.add(0);
		}
		
		Integer digit1 = number1.get(0);
		Integer digit2 = number2.get(0);
		Set<Integer> resDigits = Z9Digit.add(digit1, digit2);
		Set<ArrayList<Integer>> resNumbers = new HashSet<ArrayList<Integer>>();
		boolean overflow = false;
		
		for(int resDigit: resDigits){
			overflow = addOverflow(digit1, digit2);
			ArrayList<Integer> numb1 = (ArrayList<Integer>)number1.clone();
			ArrayList<Integer> numb2 = (ArrayList<Integer>)number2.clone();
			numb1.remove(0);
			numb2.remove(0);
			if(numb1.size() != 0){
				Set<ArrayList<Integer>> resultsHigh = Z9Number.add(numb1, numb2);
				if(overflow){
					Set<ArrayList<Integer>> tmpSet = resultsHigh;
					resultsHigh = new HashSet<ArrayList<Integer>>();
					ArrayList<Integer> tmpArr = new ArrayList<Integer>();
					tmpArr.add(1);
					for(int i = 1; i < numb1.size(); i++){
						tmpArr.add(0);
					}
					for(ArrayList<Integer> keyArr: tmpSet){
						resultsHigh.addAll(Z9Number.add(keyArr, tmpArr));
					}
				}
				for(ArrayList<Integer> resHigh: resultsHigh){
					resHigh.add(0, resDigit);
					resNumbers.add(resHigh);
				}
			}
			else{
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(resDigit);
				if(overflow){
					tmp.add(1);
				}
				resNumbers.add(tmp);
			}
		}
		
		return resNumbers;
	}
	
	private static Set<Z9Number> mul(ArrayList<Integer> numb1, Integer integer) {
		Set<Z9Number> result = new HashSet<Z9Number>();
		ArrayList<Set<Z9Number>> parts = new ArrayList<Set<Z9Number>>();
		for(int i = 0; i < numb1.size(); i++){
			parts.add(new HashSet<Z9Number>());
			for(int r: Z9Digit.mul(numb1.get(i), integer)){
				if(mulOverflow(numb1.get(i), integer)){
					r *= 10;
				}
				for(int j = 0; j < i; j++){
					r *= 10;
				}
				parts.get(i).add(new Z9Number(r));
				
			}
		}
		result.addAll(parts.remove(0));
		Set<Z9Number> tmp = new HashSet<Z9Number>();
		while(!parts.isEmpty()){
			for(Z9Number c: parts.remove(0)){
				for(Z9Number a: result){
					tmp.addAll(a.add(c));
				}
				result.clear();
				result.addAll(tmp);
				tmp.clear();
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < number.size(); i++){
			res = res + number.get(number.size() - i - 1);
		}
		return res;
	}
	
	private static boolean addOverflow(int a, int b){
		if( (a == 0) || (b == 0))
			return false;
		
		if( (a == 6) || (b == 6))
			return true;
		if( (a == 8) || (b == 8))
			return true;
		if( (a == 4) || (b == 4))
			return true;
		if( (a == 7) || (b == 7))
			return true;
		
		return false;
	}
	private static boolean mulOverflow(int a, int b){
		if( (a == 0) || (b == 0))
			return false;
		if( (a == 1) || (b == 1))
			return false;
		
		if( (a == 6) || (b == 6))
			return true;
		if( (a == 8) || (b == 8))
			return true;
		if( (a == 4) || (b == 4))
			return true;
		if( (a == 7) || (b == 7))
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Z9Number> gcd(Z9Number a, Z9Number b){
		Set<Z9Number> results = new HashSet<Z9Number>();
		
		ArrayList<Integer> numb = new ArrayList<Integer>();
		for(int i = 0; i < width; i++){
			numb.add(0);
		}
		do{
			Z9Number candidate = new Z9Number((ArrayList<Integer>) numb.clone());
			if(a.div(candidate).size() != 0 && b.div(candidate).size() != 0){
				results.add(candidate);
			}
		}while(inc(numb));
		Set<Z9Number> res2 = new HashSet<Z9Number>();
		res2.addAll(results);
		
		boolean ok;
		Z9Number victim = null;
		for(Z9Number candidate: res2){
			ok = true;
			for(Z9Number candidate2: results){
				if(!candidate2.equals(candidate)){
					if(candidate2.div(candidate).size() != 0){
						ok = false;
						victim = candidate;
						break;
					}
				}
			}
			if(!ok){
				results.remove(victim);
			}
		}
		
		
		return results;
	}
}
