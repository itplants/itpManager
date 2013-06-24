public class Version {
	private static  int mejar=2;
	private static  int miner=1;
	private static  int update=130418;// version 
	
	static public void setMejar(int mejar) {
		Version.mejar = mejar;
	}
	static public int getMejar() {
		return mejar;
	}
	static public void setMiner(int miner) {
		Version.miner = miner;
	}
	static public int getMiner() {
		return miner;
	}
	static public int getUpdate() {
		return update;
	}
	static public String getRevision(){
//		return "Pro";
		return "Standard";
//		return "Education";
	}
}
