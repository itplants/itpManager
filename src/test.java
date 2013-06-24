
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s="abcd"+System.getProperty("line.separator")+System.getProperty("line.separator");
		System.out.println("getKeywordData0 |"+s+"|");
		s=Files.removeKaigyo(s);// ??????
		System.out.println("getKeywordData1 |"+s+"|");
	}
	
	

}
