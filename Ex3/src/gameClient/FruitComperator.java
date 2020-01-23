package gameClient;

import java.util.Comparator;


/**
 * this class represent the comparator for fruits, meaning it compares their values
 */
public class FruitComperator implements Comparator<Fruit>{

	@Override
	public int compare(Fruit a, Fruit b)
	{
		double res = 0;
		res = a.getValue() - b.getValue();
		if(res<0)
			return 1; //b>a
		if(res>0)
			return -1; // a>b
		return 0; // a=b
	}
	
	
	public int compareObjects(Object o1, Object o2) 
	{
		Fruit f1,f2;
		if(o1 instanceof Fruit)
			f1 = (Fruit)o1;
		else
			throw new ArithmeticException();
		if(o2 instanceof Fruit)
			f2 = (Fruit)o2;
		else
			throw new ArithmeticException();
		
		return compare(f1,f2);//If o1 and o2 are from type Fruit, then return the same result from the original compare.
		
	}
	

}