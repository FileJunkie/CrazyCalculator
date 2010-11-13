package name.filejunkie.CrazyCalculator;

public class CrazyCalculator {

	public static void main(String[] args) {
		Z9Number n = new Z9Number(0);
		for(int i = 0; i < 9; i++){
			n.setValue(i);
			for(int j = 0; j < 9; j++){
				System.out.println(i + " + " + j + " = " + n.add(j));
			}
		}
	}

}
