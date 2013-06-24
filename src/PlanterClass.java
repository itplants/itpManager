


public class PlanterClass {
private Information info=null;//  �ݒ肳�ꂽ���
private Sensor 	sensor=null;// �Z���T�[�̏��
private Control control=null;// ����
//private PlanterInformation planterInformation=null;// �A�C�e�B�v�����^�[�̏��
private PlantProgram   plantProgram=null;// �͔|���̏��
private String serial=""; 		// �v�����^�[�̃V���A���ԍ�
private Version version=null;// �v�����^�[OS�̃o�[�W����
private int usbNo=0; 		// USB�Ō�����������
private PlanterIcon inactiveGPanel=null;// Active�ȃA�C�R���p�l��
private PlanterIcon activeGPanel=null;// Inactive�ȃA�C�R���p�l��


// �A�N�e�B�u�ȃv�����^�[�̃A�C�R���p�l����Ԃ�
	public PlanterIcon getActiveIcon()
	{
		if(activeGPanel==null) activeGPanel=new PlanterIcon(info.getPlanterName());
		if(sensor.getActiveCamNo()==0)
			activeGPanel.setPlanterIcon("NoCamON");
		else
			activeGPanel.setPlanterIcon("CamON");
		return activeGPanel;
	}
	
// �A�N�e�B�u�łȂ��v�����^�[�̃A�C�R���p�l����Ԃ�
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
		
		// �͔|�v���O�����̎w��t�@�C�������K�v
		// �Ƃ肠�����Anull�@�ŐV�K�쐬���Ă���
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
			setVersion(new Version());// Version���Ȃ���΍��B
		}
		return version;
	}

	private void setVersion(Version version2) {
		// �C���X�^���X��ێ�����
		version=version2;
		// �A�C�e�B�v�����^�[�̃o�[�W�����𒲂ׂ�
		if(ITPlanterClass.getSystemPlanterNumber()==0) return;// USB�ɃA�C�e�B�v�����^�[�����݂��Ȃ�
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
			System.out.println("�v�����^�[��������Ȃ�������������܂���B\n�v�����^�[�̓d������꒼���Ă݂Ă��������B");
			itp_Logger.logger.info("can not find planter");
			return ;
			}
		
		if(arrn[0].equals("Version")==true){
		// Version 2.2
		if(arrn.length>1){
		String[] s=arrn[1].split(" ");
		//System.out.println("s:"+s[1]);
		// split(".")�̓_���B split("\\.")�ɂ��邱��
		String[] mm=s[1].split("\\.");
		// ??
		//System.out.println(mm[0]);

		//
		Version.setMejar(Integer.parseInt(mm[0].replaceAll("[^0-9]","")));
		Version.setMiner(Integer.parseInt(mm[1].replaceAll("[^0-9]","")));
		itp_Logger.logger.info("Version "+Version.getMejar()+"."+Version.getMiner());
		} else {
//			System.out.println("�o�[�W�����s��");
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
