package objects.basic;

import main.Main;

public class PGSymbol extends PGDouble{

	public PGSymbol(String name_) {
		super(name_);
	}

	@Override
	public double getValue() {
		PGBase pgb=PGBase.getPGFromFullpath(this.getFullName());
		if(pgb instanceof PGDouble) {
			return ((PGDouble) pgb).getValue();
		}else if (pgb==null){
			Main.logger.severe(this.getFullName()+" is Null!");
			throw new NullPointerException();
		}else {
			Main.logger.severe(pgb+" is "+pgb.getTypeName()+" instead of number!");
			throw new ClassCastException();
		}
	}

	@Override
	public PGBase clone() {
		return new PGSymbol(this.getFullName());
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGSymbol) {
			return ((PGSymbol)obj).getFullName().equals(this.getFullName());
		}
		return false;
	}

}
