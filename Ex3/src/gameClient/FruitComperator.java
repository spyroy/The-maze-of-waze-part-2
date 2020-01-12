package gameClient;

import java.util.Comparator;

public class FruitComperator implements Comparator<Fruit> {

	@Override
	public int compare(Fruit a, Fruit b) {
		double result = 0;
		result = a.getValue() - b.getValue();
		if(result < 0)
			return 1; // b>a
		if(result > 0)
			return -1; // a>b
		return 0; // a=b
	}

}
