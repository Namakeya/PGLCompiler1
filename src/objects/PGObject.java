package objects;

public class PGObject extends PGBase{
	private PGType type;

	public PGObject(String name_,PGType type) {
		super(name_);
		this.type=type;
	}

	public PGType getType() {
		return type;
	}

}
