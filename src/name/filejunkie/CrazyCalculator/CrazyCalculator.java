package name.filejunkie.CrazyCalculator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class CrazyCalculator {

	public static void main(String[] args) {
		Stack<HashSet<Integer>> stack = new Stack<HashSet<Integer>>();

		System.out.println("Enter RPN expression");
		Scanner in = new Scanner(System.in);

		while(true){
			String input = in.nextLine();
			String[] array = input.split(" ");
			for(String token: array){
				if(token.equals("+")){
					HashSet<Integer> a = stack.pop();
					HashSet<Integer> b = stack.pop();
					HashSet<Integer> res = new HashSet<Integer>();

					for(int i: a){
						for(int j: b){
							res.addAll(new Z9Number(i).add(j));
						}
					}
					stack.push(res);
				}
				else if(token.equals("-")){
					HashSet<Integer> b = stack.pop();
					HashSet<Integer> a = stack.pop();
					HashSet<Integer> res = new HashSet<Integer>();

					for(int i: a){
						for(int j: b){
							res.addAll(new Z9Number(i).sub(j));
						}
					}
					stack.push(res);
				} else if(token.equals("*")){
					HashSet<Integer> b = stack.pop();
					HashSet<Integer> a = stack.pop();
					HashSet<Integer> res = new HashSet<Integer>();

					for(int i: a){
						for(int j: b){
							res.addAll(new Z9Number(i).mul(j));
						}
					}
					stack.push(res);
				}
				else if(token.equals("/")){
					HashSet<Integer> b = stack.pop();
					HashSet<Integer> a = stack.pop();
					HashSet<Integer> res = new HashSet<Integer>();

					for(int i: a){
						for(int j: b){
							res.addAll(new Z9Number(i).div(j));
						}
					}
					stack.push(res);
				}
				else{
					int val = Integer.parseInt(token);
					if(val < 0 || val > 8){
						throw new IllegalArgumentException();
					}
					HashSet<Integer> tmp = new HashSet<Integer>();
					tmp.add(val);
					stack.push(tmp);
				}
			}
			for(HashSet<Integer> i: stack){
				System.out.println(i);
			}
		}
	}

}
