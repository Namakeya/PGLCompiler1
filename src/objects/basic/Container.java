package objects.basic;

public class Container {
	public PGBase pgb;
	public String name;

	public PGBase getPGBase() {
		if(pgb==null) {
			pgb=null;
		}
		return pgb;
	}
}
