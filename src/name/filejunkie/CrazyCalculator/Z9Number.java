package name.filejunkie.CrazyCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Z9Number {
	private ArrayList<Integer> number = null;
	
	public Z9Number(int a){
		if(a < 0){
			throw new IllegalArgumentException();
		}
		number = new ArrayList<Integer>();
		
		int digit;
		while(a != 0){
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
		while(numb1.size() > numb2.size()){
			numb2.add(0);
		}
		while(numb1.size() < numb2.size()){
			numb1.add(0);
		}
	
		Set<ArrayList<Integer>> results = Z9Number.add(numb1, numb2);
		Set<Z9Number> result = new HashSet<Z9Number>();
		for(ArrayList<Integer> res: results){
			Z9Number r = new Z9Number(0);
			r.number = res;
			result.add(r);
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	private static Set<ArrayList<Integer>> add(ArrayList<Integer> number1, ArrayList<Integer> number2){		
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

	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < number.size(); i++){
			res = res + number.get(number.size() - i - 1);
		}
		return res;
	}
	
}
