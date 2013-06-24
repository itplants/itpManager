


public class PlanterClass {
private Information info=null;//  設定された情報
private Sensor 	sensor=null;// センサーの情報
private Control control=null;// 制御
//private PlanterInformation planterInformation=null;// アイティプランターの情報
private PlantProgram   plantProgram=null;// 栽培物の情報
private String serial=""; 		// プランターのシリアル番号
private Version version=null;// プランターOSのバージョン
private int usbNo=0; 		// USBで見つかった順番
private PlanterIcon inactiveGPanel=null;// Activeなアイコンパネル
private PlanterIcon activeGPanel=null;// Inactiveなアイコンパネル


// アクティブなプランターのアイコンパネルを返す
	public PlanterIcon getActiveIcon()
	{
		if(activeGPanel==null) activeGPanel=new PlanterIcon(info.getPlanterName());
		if(sensor.getActiveCamNo()==0)
			activeGPanel.setPlanterIcon("NoCamON");
		else
			activeGPanel.setPlanterIcon("CamON");
		return activeGPanel;
	}
	
// アクティブでないプランターのアイコンパネルを返す
	public PlanterIcon getInActiveIcon()
	{
		if(inactiveGPanel==null) inactiveGPanel=new PlanterIcon(info.getPlanterName());
		if(sensor.getActiveCamNo()==0)
			inactiveGPanel.setPlanterIcon("NoCamOFF");
		else
			inactiveGPanel.setPlanterIcon("NoCamOFF");
		return inactiveGPanel;
	}
	
	public PlanterClass(String s)
	{
		//this.setSystemCameraSize(ITPlanterClass.getSystemCameraSize());
		
		setInfo(new Information(this));
		info.setPlanterName(s);
		
		setSerial(serial);
		
		setSensor(new Sensor(this));
		
		setControl(new Control(this));
		
		// 栽培プログラムの指定ファイル名が必要
		// とりあえず、null　で新規作成しておく
		setPlantProgram(new PlantProgram("default.xml"));
		
		setVersion(new Version());
		// Add cam
		/*
		this.setCameraSize(3);
		this.addCameraName("CAM-0");
		this.addCameraNumber(0);
		this.addCameraName("CAM-1");
		this.addCameraNumber(1);
		this.addCameraName("CAM-2");
		this.addCameraNumber(2);
		*/
	}
	
	public Version getVersion()
	{
		if(version==null){
			setVersion(new Version());// Versionがなければ作る。
		}
		return version;
	}

	private void setVersion(Version version2) {
		// インスタンスを保持する
		version=version2;
		// アイティプランターのバージョンを調べる
		if(ITPlanterClass.getSystemPlanterNumber()==0) return;// USBにアイティプランターが存在しない
		String result="";
		int n=ITPlanterClass.getCurrentPlanterNo();
		if(n<=0){
			n=0;
			ITPlanterClass.setCurrentPlanterNo(n);
		}
		result = sendCom.sendcom(" "+(n+1)+" -e v"); // sendcom
		if(result=="")		return ;
		// Command: v		arrn[0]
		// Version 2.2		arrn[1]
		String[] arrn=result.split(System.getProperty("line.separator"),0);
		//
		if(arrn[0]==""){
			System.out.println("プランターが見つからないか応答がありません。\nプランターの電源を入れ直してみてください。");
			itp_Logger.logger.info("can not find planter");
			return ;
			}
		
		if(arrn[0].equals("Version")==true){
		// Version 2.2
		if(arrn.length>1){
		String[] s=arrn[1].split(" ");
		//System.out.println("s:"+s[1]);
		// split(".")はダメ。 split("\\.")にすること
		String[] mm=s[1].split("\\.");
		// ??
		//System.out.println(mm[0]);

		//
		Version.setMejar(Integer.parseInt(mm[0].replaceAll("[^0-9]","")));
		Version.setMiner(Integer.parseInt(mm[1].replaceAll("[^0-9]","")));
		itp_Logger.logger.info("Version "+Version.getMejar()+"."+Version.getMiner());
		} else {
//			System.out.println("バージョン不明");
			itp_Logger.logger.info("no version");		
			}
		}
	}
		
	
	public Information getInformation() {
		return this.info;
	}

	public void setInfo(Information information) {
		this.info = information;
	}

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Control getControl() {
		return this.control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public void setPlantProgram(PlantProgram p) {
		this.plantProgram = p;
	}
/*	
	public PlanterInformation getPlanterInfo() {
		return this.planterInformation;
	}
*/
	public void setPlantInfo(PlantProgram p) {
		this.plantProgram = p;
	}

	public PlantProgram getPlantInfo() {
		return this.plantProgram;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		if(serial.equals("")==true ){
			String result=sendCom.sendcom(" "+(ITPlanterClass.getCurrentPlanterNo()+1)+" -e Z");
			String[] r=result.split(System.getProperty("line.separator"));
			if(r.length>1){
/*
System.out.println("Serial="+result);
System.out.println("Serial="+r[0]);
System.out.println("Serial="+r[1]);
*/
				serial=r[1];
System.out.println("Serial="+serial);
			}
		}
		this.serial = serial;
	}

	public int getPlanterNo() {
		return usbNo;
	}

	public void setPlanterNo(int planterNo) {
		this.usbNo = planterNo;
	}
	
	public String getStatue() {
		// command z
		String result=sendCom.sendcom(" "+(ITPlanterClass.getCurrentPlanterNo()+1)+" -e z");
		return result;
	}

}
