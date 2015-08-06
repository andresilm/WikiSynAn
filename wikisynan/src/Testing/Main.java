package Testing;

import Pattern.DepPattern;
import Pattern.Dependency;

public class Main {
	public static void main(String[] args) {
		DepPattern depp1 = new DepPattern("nsubj(<N>,N)");
		
		
		DepPattern depp2 = new DepPattern("cop(<N>,was/V)");
		
		
		Dependency dep1 = new Dependency("nsubj(astronaut/NN,Armstrong/NP)");
		Dependency dep2 = new Dependency("cop(astronaut/NN,was/VBD)");
		
		System.out.println(dep1);
		System.out.println(dep2);
		
		System.out.println(depp1);
		System.out.println(depp2);
		
		System.out.println(depp1.matchesWithDependency(dep1));
		System.out.println(depp2.matchesWithDependency(dep2));
		System.out.println(depp2.matchesWithDependency(dep1));
		System.out.println(depp1.matchesWithDependency(dep2));
	}
}
