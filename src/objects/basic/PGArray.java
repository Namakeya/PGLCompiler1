package objects.basic;

import java.util.ArrayList;
import java.util.List;

import objects.function.FunctionEquals;

public class PGArray extends PGBase{
	
	public List<FunctionEquals> list=new ArrayList<FunctionEquals>();

	@Override
	public PGBase clone() {
		return null;
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		return false;
	}

}
