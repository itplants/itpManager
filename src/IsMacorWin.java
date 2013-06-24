import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


public class IsMacorWin {
	
	public static boolean isMacOrWin()
	{
		// システムプロパティをすべて取得する。
				Properties properties = System.getProperties();
/*
				// すべてのシステムプロパティのキーと値を表示する。
				for ( Object key: properties.keySet() ) {
					Object value = properties.get( key );
					
					System.out.println( key + ": " + value );
				}
 */
		String s=(String)properties.get("os.name");
	if(s.contains("Mac")==true){
		// MacOSX
		return true;
		} else
		return false;

//		return true;// Mac
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		JLabel gjp=new JLabel("Mac: true else: false "+String.valueOf(isMacOrWin()));
		
		frame.getContentPane().add(gjp);
		
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println(IsMacorWin.isMacOrWin());

	}
	
}
