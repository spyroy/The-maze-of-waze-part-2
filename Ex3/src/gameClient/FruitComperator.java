package gameClient;

import java.util.Comparator;



public class FruitComperator implements Comparator<Fruit>{

	@Override
	public int compare(Fruit a, Fruit b) {
		double res = 0;
		res = a.getValue() - b.getValue();
		if(res<0)
			return 1; //b>a
		if(res>0)
			return -1; // a>b
		return 0; // a=b
	}

}