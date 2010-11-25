package name.filejunkie.CrazyCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Z9Number {
	private ArrayList<Integer> number = null;
	final private int width = 3;
	
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
		
	}
	
	@SuppressWarnings("unchecked")
	public Set<Z9Number> add(Z9Number b){
		ArrayList<Integer> numb1 = (ArrayList<Integer>)this.number.clone();
		ArrayList<Integer> numb2 = (ArrayList<Integer>)b.number.clone();
			
		Set<ArrayList<Integer>> results = Z9Number.add(numb1, numb2);
		Set<Z9Number> result = new HashSet<Z9Number>();
		for(ArrayList<Integer> a: results){
			for(int i = a.size() - 1; a.get(i) == 0 && i != 0; i--){
				a.remove(i);
			}
		}
		for(ArrayList<Integer> res: results){
			Z9Number r = new Z9Number(0);
			while(res.size() > width){
				res.remove(res.size() - 1);
			}
			r.number = res;
			result.add(r);
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Z9Number> sub(Z9Number b){
		ArrayList<Integer> numb1 = (ArrayList<Integer>)this.number.clone();
		ArrayList<Integer> numb2 = (ArrayList<Integer>)b.number.clone();
			
		Set<ArrayList<Integer>> results = Z9Number.sub(numb1, numb2);
		for(ArrayList<Integer> a: results){
			for(int i = a.size() - 1; a.get(i) == 0 && i != 0; i--){
				a.remove(i);
			}
		}
		Set<Z9Number> result = new HashSet<Z9Number>();
		for(ArrayList<Integer> res: results){
			Z9Number r = new Z9Number(0);
			while(res.size() > width){
				res.remove(res.size() - 1);
			}
			r.number = res;
			result.add(r);
		}
		
		return result;
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
					a.number.add(0);
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

	private static Set<Z9Number> mul(ArrayList<Integer> numb1, Integer integer) {
		Set<Z9Number> result = new HashSet<Z9Number>();
		ArrayList<Set<Z9Number>> parts = new ArrayList<Set<Z9Number>>();
		for(int i = 0; i < numb1.size(); i++){
			parts.add(new HashSet<Z9Number>());
			for(int r: Z9Digit.mul(numb1.get(i), integer)){
				if(r < numb1.get(i) * integer){
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
			overflow = (resDigit < digit1 + digit2) || (digit1 != 0 && digit2 != 0 && resDigit == digit1 + digit2);
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

	@SuppressWarnings("unchecked")
	private static Set<ArrayList<Integer>> sub(ArrayList<Integer> number1, ArrayList<Integer> number2){
		while(number1.size() > number2.size()){
			number2.add(0);
		}
		while(number1.size() < number2.size()){
			number1.add(0);
		}
		
		Integer digit1 = number1.get(0);
		Integer digit2 = number2.get(0);
		Set<Integer> resDigits = Z9Digit.sub(digit1, digit2);
		Set<ArrayList<Integer>> resNumbers = new HashSet<ArrayList<Integer>>();
		boolean overflow = false;
		
		for(int resDigit: resDigits){
			overflow = (resDigit > digit1 - digit2) || (digit1 != 0 && digit2 != 0 && resDigit == digit1 + digit2);
			ArrayList<Integer> numb1 = (ArrayList<Integer>)number1.clone();
			ArrayList<Integer> numb2 = (ArrayList<Integer>)number2.clone();
			numb1.remove(0);
			numb2.remove(0);
			if(numb1.size() != 0){
				Set<ArrayList<Integer>> resultsHigh = Z9Number.sub(numb1, numb2);
				if(overflow){
					Set<ArrayList<Integer>> tmpSet = resultsHigh;
					resultsHigh = new HashSet<ArrayList<Integer>>();
					ArrayList<Integer> tmpArr = new ArrayList<Integer>();
					tmpArr.add(1);
					for(int i = 1; i < numb1.size(); i++){
						tmpArr.add(0);
					}
					for(ArrayList<Integer> keyArr: tmpSet){
						resultsHigh.addAll(Z9Number.sub(keyArr, tmpArr));
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
	
	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < number.size(); i++){
			res = res + number.get(number.size() - i - 1);
		}
		return res;
	}
	
}
