package name.filejunkie.CrazyCalculator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class CrazyCalculator {

	public static void main(String[] args) {
		Stack<HashSet<Z9Number>> stack = new Stack<HashSet<Z9Number>>();
		
		System.out.println("Enter RPN expression");
		Scanner in = new Scanner(System.in);

		while(true){
			String input = in.nextLine();
			String[] array = input.split(" ");
			for(String token: array){
				if(token.equals("+")){
					HashSet<Z9Number> a = stack.pop();
					HashSet<Z9Number> b = stack.pop();
					HashSet<Z9Number> res = new HashSet<Z9Number>();

					for(Z9Number i: a){
						for(Z9Number j: b){
							res.addAll(i.add(j));
						}
					}
					stack.push(res);
				}
				else if(token.equals("-")){
					HashSet<Z9Number> b = stack.pop();
					HashSet<Z9Number> a = stack.pop();
					HashSet<Z9Number> res = new HashSet<Z9Number>();

					for(Z9Number i: a){
						for(Z9Number j: b){
							res.addAll(i.sub(j));
						}
					}
					stack.push(res);
				} else if(token.equals("*")){
					HashSet<Z9Number> b = stack.pop();
					HashSet<Z9Number> a = stack.pop();
					HashSet<Z9Number> res = new HashSet<Z9Number>();

					for(Z9Number i: a){
						for(Z9Number j: b){
							res.addAll(i.mul(j));
						}
					}
					stack.push(res);
				}/*
				else if(token.equals("/")){
					HashSet<Z9Number> b = stack.pop();
					HashSet<Z9Number> a = stack.pop();
					HashSet<Z9Number> res = new HashSet<Z9Number>();

					for(Z9Number i: a){
						for(Z9Number j: b){
							res.addAll(i.div(j));
						}
					}
					stack.push(res);
				}*/
				else if(token.equals("inv")){
					HashSet<Z9Number> a = stack.pop();
					HashSet<Z9Number> res = new HashSet<Z9Number>();
					
					for(Z9Number i: a){
						res.addAll(i.inv());
					}
					stack.push(res);
				}
				else{
					int val = Integer.parseInt(token);
					HashSet<Z9Number> tmp = new HashSet<Z9Number>();
					tmp.add(new Z9Number(val));
					stack.push(tmp);
				}
			}
			for(HashSet<Z9Number> i: stack){
				System.out.println(i);
			}
		}
	}

}
