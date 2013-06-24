//
// load setting data from local file
//	itplants.ltd.	2011
//	Yosiyuki SAKAGUCHI
//
//
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class Files {
	public static void main(String[] args) {
		if(ITPlanterClass.getState()==false) new ITPlanterClass();
/*		
			String nPlanterName=ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName();
			String serial=ITPlanterClass.getCurrentPlanterClass().getSerial();

		   String s1 = Files.readFile(getPlantersfile());
		   Files.getPlantersSettings(s1);
		   Files.setPlanterName(nPlanterName,serial);// set Planter Name to file
		   Files.savePlantersSettings();// save settings
 */	   			
		String s=Files.LoadCameraResolution();
		System.out.println("LoadCameraResolution="+s);		
		Files.SaveCameraResolution(1280,800);
		String t=Files.LoadCameraResolution();
		System.out.println("LoadCameraResolution="+t);		

		/*
		// お知らせメールを出す。
		String sr=Files.getRecordSensor();
		String[] sr2=sr.split(" ");
		if(sr2.length>1){
			String sr3=sr2[1];
			sr3=sr3.replace(System.getProperty("line.separator"), "");
			System.out.println("getRecordSensor="+sr3);		
		}
		
		System.out.println("getRecordfile():"+getRecordfile());
			String s1=Files.getAutoPicture();
			System.out.println("s1="+s1);
		   
		String b=Files.getPhotoHolder();
		System.out.println("holder1=|"+b+"|");
		b=b.replace("PhotoHolder ", "");
		System.out.println("holder2=|"+b+"|");
		
		String check=Files.getCheckSaveImage();
		System.out.println("holder check=|"+check+"|");
		
		if(check.contains("true")==true)
			Files.setCheckSaveImage("false");
		else
			Files.setCheckSaveImage("true");

		Files.savePlantersSettings();
		
		String holder=Files.getPhotoHolder();
		System.out.println("getPhotoHolder="+holder+"filename");
		System.out.println("getPhotoHolderlength="+holder.length());

		Files.setPhotoHolder("/Users/sakaguti/Desktop/");

		Files.savePlantersSettings();
		*/
		//holder = holder.substring(0,holder.length()-1);

		//System.out.println("getPhotoHolderlength="+holder.length());

		
		//System.out.println("holder=|"+holder+"|");
		
		   
		   
		/*
		System.out.println("getPhotoHolder="+Files.getPhotoHolder());
		System.out.println("AutoPicture="+Files.getAutoPicture());
		Files.setPhotoHolder("/User/sakaguti/Desktop");
		System.out.println("getPhotoHolder="+Files.getPhotoHolder());
		savePlantersSettings();
		*/
		/*
		// 栽培モードを保存する。
			String message="TempCorrect 1.0";
			String data=getFileContents( message );
			writeFile(getPlantersfile(), data);
			String keyword="PCAutoTime";
			System.out.println(readFile(getPlantersfile(),keyword));
		*/
		/*
		File[] files=ls(getDBPath());
		 for (int i = 0,j=0; i < files.length; i++) {
		        File file = files[i];
		        if(file.getName().contains(".xml")==true){
		        System.out.println((j++) + ":    " + file);
		        }
		 }
		 */
	}
	
	public static File[] ls(String path) {
	    //String path = "C:\\filelist";
	    File dir = new File(path);
	    File[] files = dir.listFiles();	    
		return files;
	}
	
	// ファイルを読む
	public static  String readFile(String fname)
	{
		// full path
		File f = new File(fname);
		byte[] b = new byte[(int) f.length()];
		String	s = "";
		try 
		{	
			FileInputStream fi = new FileInputStream(f);
			fi.read(b);	
			s = new String(b);	
			//System.out.println(s);
			fi.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "設定ファイルが読めません。");
			e.printStackTrace();
		}
		return s;
	}
	
	
	// ファイルを上書きする
	public static  void writeFile(String file, String txt)
	{
		// full path
		File f = new File(file);
		
		if (checkBeforeWritefile(f)){
	        try {
				FileWriter filewriter = new FileWriter(file, false);
				filewriter.write(txt);
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ファイルに追記する
	public static  void addFile(String file, String txt)
	{
		// full path
		File f = new File(file);
		
		if (checkBeforeWritefile(f)){
	        try {
				FileWriter filewriter = new FileWriter(file, true);
				filewriter.write(txt);
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	  private static boolean checkBeforeWritefile(File file){
		    if (file.exists()){
		      if (file.isFile() && file.canWrite()){
		        return true;
		      }
		    }

		    return false;
		  }
	
		// DB のパスを返す
		public static String getDBPath()
		{
			String sCurrentDir = new File(".").getAbsoluteFile().getParent();
				if(IsMacorWin.isMacOrWin()==true ){
//					System.out.println("Dir="+sCurrentDir+"/DB/");
					// MacOSX
					return(sCurrentDir+"/DB/");// MACOSX  
				} else {
					// Windows
//					System.out.println("Dir="+sCurrentDir+"\\DB\\");
					return(sCurrentDir+"\\DB\\");// WIndows
				}
		}
	
	// 設定ファイルの名前を返す
	public static String getSetupfile()
	{
		String sCurrentDir = new File(".").getAbsoluteFile().getParent();
			if(IsMacorWin.isMacOrWin()==true ){
				// MacOSX
				return(sCurrentDir+"/data/Mac/setup.txt");// MACOSX  
			} else {
				// Windows
				return(sCurrentDir+"\\data\\Win\\setup.txt");// WIndows
			}
	}
	
	

	// 設定ファイルの名前を返す
	public static String getRecordfile()
	{
		String sCurrentDir = new File(".").getAbsoluteFile().getParent();
		//
			if(IsMacorWin.isMacOrWin()==true ){
				// MacOSX
				return(sCurrentDir+"/record.txt");// MACOSX  
			} else {
				// Windows
				return(sCurrentDir+"\\record.txt");// WIndows
			}
	}
	
	// プランターの設定ファイルの名前を返す
	public static String getPlantersfile()
	{
		String sCurrentDir = new File(".").getAbsoluteFile().getParent();
			if(IsMacorWin.isMacOrWin()==true ){
				// MacOSX
				return(sCurrentDir+"/data/Mac/planters.txt");// MACOSX  
			} else {
				// Windows
				return(sCurrentDir+"\\data\\Win\\planters.txt");// WIndows
			}
	}
	
	// 設定ファイルの名前を返す
	public static String getImagefile(String f)
	{
		String sCurrentDir = new File(".").getAbsoluteFile().getParent();
			if(IsMacorWin.isMacOrWin()==true){
				// MacOSX
				return(sCurrentDir+"/"+f);// MACOSX  
			} else {
				// Windows
				return(sCurrentDir+"\\"+f);// WIndows
			}
	}

// 画像ファイルの削除
	public static void delete(String qname) {
		String imageFile=getImagefile(qname);
		File file=new File(imageFile);
		//System.out.println("imageFile="+imageFile);
		boolean flag=false;
		for(int i=0;i<10;i++){
		//System.out.println("file.exists "+file.exists());
		if(file.exists()) flag=file.delete(); 
		if(flag) break;
		}
	}

	public static String getupAlbumFile() {
			String SETUPFILE=null; 
			String sCurrentDir = new File(".").getAbsoluteFile().getParent();
			if(IsMacorWin.isMacOrWin()==true ){
				// MacOSX
				SETUPFILE=sCurrentDir+"/data/Mac/PicasaSetup.txt";// MACOSX  
			} else {
				// Windows
				SETUPFILE=sCurrentDir+"\\data\\Win\\PicasaSetup.txt";// WIndows
			}
			return SETUPFILE;
	}

	public static void SaveCameraResolution(int w, int h) {
		String message="Resolution "+w+"X"+h;
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}
	
	public static void SavePlanterName(String old_planterName) {
		// プランターの名前を保存する。　すでにある名前は書き換える
		// 現在のプランターの名前は、すでに　ITPlanterClass.getCurrentPlanterClass().getInformation().setPlanterName("newPlanterName"); で登録されている。
		String s1 = Files.readFile(getPlantersfile());
		String[] s2=s1.split(System.getProperty("line.separator"));
		// 現在のプランターの情報までスキップする
		String current_planterName=ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName();
		if( old_planterName.equals(current_planterName)) return;

		String add="";
		String res="";
		boolean sw=false;
		for(int i=0;i<s2.length;i++){
			if(s2[i].contains(old_planterName)==false ) continue;// skip
			if(s2[i].contains("Planter")==true){
				res="Planter "+current_planterName;
				s2[i]=res;
				sw=true;
				break;
			}
		}
		//
		if(sw==false){
		// New entry
			add="Planter "+current_planterName+System.getProperty("line.separator");
			System.out.println("add planter file"+add);
		}
		// output strings
		String s3="";
		for(int i=0;i<s2.length;i++){
			//System.out.println("s2:"+s2[i]);
			s3 += s2[i]+System.getProperty("line.separator");
		}
		if(add.equals("")==false) s3+=add;
		//System.out.println("planter file"+getPlantersfile());
		//System.out.println(s3);
		writeFile(getPlantersfile(), s3);
	}
	
	
	public static String LoadCameraResolution() {
		return loadKeywordData("Resolution");
	}
	
	public static String loadKeywordData(String keyword){
		// Planters.txtからキーワードの値を読み出す
		return Files.readKeywordDatafromFile(getPlantersfile(),keyword);
		/*
		String s="";
		String s1 = Files.readFile(getPlantersfile());
		String[] s2=s1.split(System.getProperty("line.separator"));
		
		for(int i=0;i<s2.length;i++){
			if(s2[i].contains(keyword)==true){
				String[] r=s2[i].split(" ");
				if(r.length>1)
							s=r[1];
			}
		}
		return s;
		*/
	}

	public static void SaveCameraInformation(int cn, String camname) {
		// プランターの名前を保存する。　すでにある名前は書き換える
		// 現在のプランターの名前は、すでに　ITPlanterClass.getCurrentPlanterClass().getInformation().setPlanterName("newPlanterName"); で登録されている。
		String s1 = Files.readFile(getPlantersfile());
		String[] s2=s1.split(System.getProperty("line.separator"));
		// 現在のプランターの情報までスキップする
		String old_planterName=ITPlanterClass.getCurrentPlanterClass().getInformation().getOldPlanterName();
		String current_planterName=ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName();
	//	if( old_planterName.equals(current_planterName)) return;
		String add="";
		String res="";
		boolean sw=false;
		for(int i=0;i<s2.length;i++){
			if(s2[i].contains(old_planterName)==false ) continue;// skip
			if(s2[i].contains("CamNo")==true){
				res="CamNo "+cn;
				s2[i]=res;
				sw=true;
			}
			if(s2[i].contains("CamName")==true){
				res="CamName "+camname;
				s2[i]=res;
				sw=true;
				break;
			}
		}
		//
		if(sw==false){
		// New entry
			add ="Planter "+current_planterName+System.getProperty("line.separator");// new planter name
			add+="Camera yes "+System.getProperty("line.separator");
			add+="CamNo "+cn+System.getProperty("line.separator");
			add += "CamName "+camname+System.getProperty("line.separator");
			add += "Resolution 640X480"+System.getProperty("line.separator");// default resolution
			System.out.println("add Camera"+add);
		}
		// output strings
		String s3="";
		for(int i=0;i<s2.length;i++){
			//System.out.println("s2:"+s2[i]);
			s3 += s2[i]+System.getProperty("line.separator");
		}
		if(add.equals("")==false) s3+=add;
		//System.out.println("planter file"+getPlantersfile());
		//System.out.println(s3);
		writeFile(getPlantersfile(), s3);
	}

	public static String getFileContents(String message )
	{
		// 現在のプランター設定業まで読み飛ばす
		// 現在のプランターの名前は、すでに　ITPlanterClass.getCurrentPlanterClass().getInformation().setPlanterName("newPlanterName"); で登録されている。
		String data="";
		String s1 = Files.readFile(getPlantersfile());
		String[] s2=s1.split(System.getProperty("line.separator"));
		if(ITPlanterClass.getState()==false) new ITPlanterClass();
		boolean sw1=false;
		boolean sw2=false;
		
		String[] keydata=message.split(" ");// keyword and data
		
		for(int i=0;i<s2.length;i++){
			//System.out.println("0  "+s2[i]+" " +keydata[0] + " "+current_planterName);
			
			// 現在のプランターの情報までスキップする
			if(sw1==false && s2[i].contains("Planter")==true 
					//&& s2[i].contains(current_planterName)==true
					){
				sw1=true;// 見つかった！
			//System.out.println("1 "+s2[i]+" " +keydata[0]);
				if(s2[i].length()>0){
				data += s2[i]+System.getProperty("line.separator");
				}
				continue;
			}
			
			//
			if(sw1==true){
			// キーワードを探す
			//System.out.println("1"+s2[i]+" " +keydata[0]);
				
			if(s2[i].contains(keydata[0])==true){
			// キーワードがあれば書き換える
			//System.out.println("333"+s2[i]+" " +keydata[0]);
				s2[i]=message;
				sw2=true;
			} else
			
			// キーワードがなければ追加する
			if(s2[i].contains("Planter")==true && sw2==false){
				// 別のプランター情報になってしまったので追加する。
			//System.out.println("444"+s2[i]+" " +keydata[0]);
					if(message.length()>0){
					data += message+System.getProperty("line.separator");
					}
				}
			}
				if(s2[i].length()>0){
				data += s2[i]+System.getProperty("line.separator");
				}
		}		
		return data;
	}
	

	
	// キーワードの値を読み出す
	private static String readKeywordDatafromFile(String plantersfile, String keyword) {
		String s=readFile(plantersfile);
		String[] p=s.split(System.getProperty("line.separator"));
		for(int i=0;i<p.length;i++){
			if(p[i].contains(keyword)== true){
				String[] q=p[i].split(" ");
				return q[1];
			}
		}
		return null;
	}

	// 栽培モードを保存する。
	public static void SaveCultivationMode(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}
	// 栽培モードを読み出す。
	public static String LoadCultivationMode() {
		String data="CultivationMode";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}
	
	// 温度補正値を保存する。
	public static void SaveTempCorrection(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}

	// 温度補正値を読み出す。
	public static String LoadTempCorrection() {
		String data="TempCorrect";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}

	// 照度補正値を保存する。
	public static void SaveIllumCorrection(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}
	// 照度補正値を読み出す。
	public static String LoadIllumCorrection() {
		String data="IllumCorrect";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}

	// Manual->Auto復帰時間を保存する。
	public static void SaveManualAutoTime(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}

	// Manual->Auto復帰時間を読み出す。
	public static String LoadManualAutoTime() {
		String data="ManualAutoTime";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}

	// PC->Auto復帰時間を保存する。
	public static void SavePCAutoTime(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);	
	}
	// PC->Auto復帰時間を読み出す。
	public static String LoadPCAutoTime() {
		String data="PCAutoTime";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}
	// ポンプブースト時間を保存する。
	public static void SaveBoostTime(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);
	}
	// ポンプブースト時間を読み出す。
	public static String LoadBoostTime() {
		String data="PumpBoost";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}
	// 白色LED点滅時間を保存する。
	public static void SaveWhiteLEDTime(String message) {
		String data=getFileContents( message );
		writeFile(getPlantersfile(), data);	
	}
	// 白色LED点滅時間を読み出す。
	public static String LoadWhiteLEDTime() {
		String data="WhiteLEDTime";
		return readKeywordDatafromFile(getPlantersfile(), data);
	}

	//
	private static ArrayList<String> plantersSettings=null;//new ArrayList<String>();
	public static ArrayList<String> getPlantersSettings()
	{
		return plantersSettings;
	}
	
	// keyword	dataString
	public static String getKeywordData(String key) {
		String s="";
		if(plantersSettings==null){
			String d=readFile(getPlantersfile());
	//	System.out.println("get "+d+"|");
			plantersSettings=getPlantersSettings(d);
		}

		if(ITPlanterClass.getState()==false) new ITPlanterClass();
		String pname=ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName();
		for(int i=0;i<plantersSettings.size();i++){
			if(plantersSettings.get(i).contains("Planter")==true && plantersSettings.get(i).contains(pname)==true){
				String[] p=plantersSettings.get(i).split(System.getProperty("line.separator"));
				//for(int t=0;t<p.length;t++)
				//  System.out.println(t+" "+plantersSettings.get(i).split(System.getProperty("line.separator"))[t]);
				
				for(int j=i;j<p.length;j++){
					if(p[j].contains(key)==true){
//System.out.println(i+" "+j+" "+pname+" "+j+" "+key+" |"+p[j]+"|");
						s=p[j];
						i++;
						break;
							}
						}// next j
					}
			}// next i
//System.out.println("getKeywordData001 |"+s+"|");
			s=s.replace("\t", " ");
//System.out.println("getKeywordData002 |"+s+"|");
			
			String rs = Files.removeKaigyo(s);// ??????
			
			//System.out.println("getKeywordData1 |"+s+"|");
			return rs;
		}//
	
	public static String removeKaigyo(String s)
	{
		return( s.replaceAll(System.getProperty("line.separator"),""));
//		return( s.replaceAll("\r\n",""));
	}

	// save all plantersSetting infor
	public static void savePlantersSettings(){
		writeFile(getPlantersfile(), ArrayListtoString(plantersSettings));		
	}

	private static String ArrayListtoString(ArrayList<String> ps) {
		String r="";
		for(int i=0;i<ps.size();i++){
			r += (ps.get(i)+System.getProperty("line.separator"));
		}
	//	System.out.println("save "+r+"|");
		return r;
	}

	private static void plantersSettingsReplace(String t) {
		String key="";
		t=t.replace("\t", " ");// tab to space
		String[] u=t.split(" ");// separate bye space
		key=u[0];
		//System.out.println("name="+ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName());
		if(plantersSettings==null) return;
		for(int i=0;i<plantersSettings.size();i++){
			String[] v=plantersSettings.get(i).split(System.getProperty("line.separator"));
			for(int j=0;j<v.length;j++){

				if(v[j].contains("Planter")==true){
					String[] n=v[j].split(" ");
					//System.out.println("n0="+n[0]+" "+"n1="+n[1]);
					if(n[0].equals("Planter")&&n[1].contains(ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName())==true){

					for(int l=j;l<v.length;l++){
					if(v[l].contains(key)==true){
//System.out.println("from i="+i+" l="+l+" "+v[l]+" keyword "+key);
						v[l]=t;
//System.out.println("to i="+i+" l="+l+" "+v[l]+" keyword "+key);
							plantersSettings.remove(i);
							String w="";
							for(int k=0;k<v.length;k++) w+=(v[k]+System.getProperty("line.separator"));
							plantersSettings.add(w);
							i++;
							}
						}
					}
				}
			}
		}
		
	}
	
	// getSeries
	// Reports	Water Temp Illum
	public static String getReports() {
		String t="Reports";
		return getKeywordData(t);
		}//


	// PhotoHolder	adress
	public static String getPhotoHolder() {
		String t="PhotoHolder";
		String r=getKeywordData(t);
		if(r.length()==0) return("");
//		System.out.println("getPhotoHolder0|"+r+"|");
//System.out.println("getPhotoHolder1|"+r+"|");	
		r=r.replaceAll(System.getProperty("line.separator"),"");
		//どうしても改行が取れないのは何故？
		int last=r.length()-1;
		r = r.substring(0,last);

//		System.out.println("last="+r.substring(last-1,last));
		
		if(r.substring(last-1,last).equals("/")==false && r.substring(last-1,last).equals("\\")==false) r=r+"/";
/*
		if(IsMacorWin.isMacOrWin()==true){
		if(r.substring(0,last).equals("/")==false) r=r+"/";
	} else {
		if(r.substring(0,last-1).equals("\\")==false) r=r+"\\";
	}
	*/	
//		System.out.println("getPhotoHolder2|"+r+"|");
/*		
		System.out.println("getPhotoHolder1|"+r+"|");
		if(r.substring(r.length()-1).equals("/")==false) r =r+"/";
		System.out.println("getPhotoHolder2|"+r+"|");
	*/	
		return(r);
		}//
	
	// CheckSaveImage	status
		public static String getCheckSaveImage() {
			String t="CheckSaveImage";
			return getKeywordData(t);
			}//

	// AutoPicture	ON or OFF
	public static String getAutoPicture() {
		String t="AutoPicture";
		return getKeywordData(t);
		}//
	
	// RecordSensor recordPeriodicTime[sec]
	public static String getRecordSensor() {
		String t="RecordSensor";
		return getKeywordData(t);
		}//
	
	
	// Serial serialNo
	public static String getSerial() {
		String t="Serial";
		return getKeywordData(t);
		}//
	//
	
	// setSeries
	// Reports	Water Temp Illum
	public static void setReports(String s) {
		String t="Reports"+" "+s;
		plantersSettingsReplace(t);
		}//
	
	// PhotoHolder	adress
	public static void setPhotoHolder(String s) {
		String t="PhotoHolder"+" "+s;
		plantersSettingsReplace(t);
		}//
	
	// CheckSaveImage
	public static void setCheckSaveImage(String s) {
		String t="CheckSaveImage"+" "+s;
		plantersSettingsReplace(t);
		}//

	// AutoPicture	ON or OFF
	public static void setAutoPicture(String s) {
		String t="AutoPicture"+" "+s;
		plantersSettingsReplace(t);
		}//
	
	// RecordSensor recordPeriodicTime[sec]
	public static void setRecordSensor(String s) {
		String t="RecordSensor"+" "+s;
		plantersSettingsReplace(t);
		}//
	
	// Serial serialNo
	public static void setSerial(String s) {
		String t="Serial"+" "+s;
		plantersSettingsReplace(t);
		}//

	
	private static ArrayList<String> getPlantersSettings(String s1) {
		ArrayList<String> result=new ArrayList<String>();
		String[] s2=s1.split(System.getProperty("line.separator"));
		for(int i=0;i<s2.length;i++){
			s2[i]=s2[i].replace("\t", " ");// tab to space
			if(s2[i].contains("Planter")==true){
				String s=new String();
				for(int j=i;j<s2.length;j++){
					if(s2[j].length()>0){
						s+=(s2[j]+System.getProperty("line.separator"));
					}
					if(j<s2.length-1){
					if(s2[j+1].contains("Planter")==true){
						i=j;
						break;
						}
					}
				}// next j
				result.add(s);
				//plantersSettings.add(s);
			}// Planter keyword
		}// next i
		return result;
	}

	/*
	// MailSetup.txt
	public static void saveSMTPPasswd(String sMTPPasswdString) {
		// TODO Auto-generated method stub
		
	}

	public static void saveToAddress(String toAddressString) {
		// TODO Auto-generated method stub
		
	}

	public static void saveMessage(String titleString) {
		// TODO Auto-generated method stub
		
	}

	public static void saveSMTPServer(String sMTPServerString) {
		// TODO Auto-generated method stub
		
	}
	
	public static void saveCheckSaveHolder(boolean selected) {
		// TODO Auto-generated method stub
		
	}
	*/


	// File save & load
	public static String getSetupMailFileName()
		{
			String SETUPFILE=null; 
			String sCurrentDir = new File(".").getAbsoluteFile().getParent();
			if(IsMacorWin.isMacOrWin()==true ){
				// MacOSX
				SETUPFILE=sCurrentDir+"/data/Mac/MailSetup.txt";// MACOSX  
			} else {
				// Windows
				SETUPFILE=sCurrentDir+"\\data\\Win\\MailSetup.txt";// WIndows
			}
			return SETUPFILE;
		}
	
	public static void saveMailSetup(String s) 
	{
		/*
		0  sMTPServerString+System.getProperty("line.separator");
		1  sMTPPasswdString+System.getProperty("line.separator");
		2   toAddressString+System.getProperty("line.separator");
		3 fromAddressString+System.getProperty("line.separator");
		4       titleString+System.getProperty("line.separator");
		5 messageAreaString+System.getProperty("line.separator");
		 */
		writeFile(getSetupMailFileName(), s);
	}

	public static void setPlanterName(String nPlanterName) {
		String t="Planter"+" "+nPlanterName;
		plantersSettingsReplace(t);
	}

	public static void setPlanterName(String nPlanterName, String serial) {
		// Planter planterName
		// Serial serial
		
		String t="Planter"+" "+nPlanterName;
		plantersSettingsReplace(t);
		
		String s="Serial"+" "+serial;
		plantersSettingsReplace(s);
	}

	public static void setCheckSaveHolder(boolean selected) {
		String t="CheckSaveHolder"+" "+selected;
		plantersSettingsReplace(t);
System.out.println("FilesClass: setCheckSaveHolder :"+t);
	}
	// CheckSavePicasa	status
	public static String getCheckSaveHolder() {
		String t="CheckSaveHolder";
		return getKeywordData(t);
		}//

	public static void setCheckSavePicasa(boolean selected) {
		String t="CheckSavePicasa"+" "+selected;
		plantersSettingsReplace(t);
System.out.println("FilesClass: setCheckSavePicasa :"+t);
	}
	
	// CheckSavePicasa	status
	public static String getCheckSavePicasa() {
		String t="CheckSavePicasa";
		return getKeywordData(t);
		}//
	
	// Files.IscheckSavePicasa()
	public static boolean IscheckSavePicasa()
	{
		if(getCheckSavePicasa().contains("true")) return true; else return false;
	}

	public static String getCamNo() {
		String t="CamNo";
		return getKeywordData(t);
	}

	public static void setCamNo(int camno) {
		String t="CamNo"+" "+camno;
		plantersSettingsReplace(t);
	}

	public static String getReportsPeriodicTime() {
		String[] s=getReports().split(" ");
		for(int i=1;i<s.length;i++){
			if(s[i].contains(":")==true){
				return s[i];
				}
			}
		return null;
		}
	
}
