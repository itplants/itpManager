import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class ReportPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		ReportPanel spn=new ReportPanel();
		
		frame.getContentPane().add(spn);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public ReportPanel() {
		super();
		initialize();
	}

	
	public  static JButton acceptBtn=null;
	public  static GJCheck tempDisp=null;
	public  static GJCheck waterDisp=null;
	public  static GJCheck illumDisp=null;
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		String status=Files.getReports();
		
		Container c = this;
		JPanel jp=new JPanel();
		jp.setBackground(new Color(250,251,245));
		// default size
		jp.setPreferredSize(new Dimension(640,320));
		
		// panel
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints1 = new GridBagConstraints();
		
		String s0 = Path.getPath()+"/images/program_h6_bg_temperature.gif";
		if(IsMacorWin.isMacOrWin()==false) s0 = Path.getPath()+"images\\program_h6_bg_temperature.gif";

		tempDisp=new GJCheck(s0);
		//"/Users/sakaguti/Documents/workspace2/GJCheck/src/images/program_h6_bg_temperature.gif");
//		tmpDisp.setAction(this.acceptEnableAction());// Action when checked
		
		constraints1.gridx = 0;
		constraints1.gridy = 0;
		constraints1.gridwidth= 3;
		constraints1.gridheight = 1;
		constraints1.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(tempDisp, constraints1);
		jp.add(tempDisp);
		if(status.contains("Temp")==true) tempDisp.setSelected(true);

		// separator
		JSeparator vspr=new JSeparator(SwingConstants.VERTICAL);
		vspr.setPreferredSize(new Dimension(30, 100));
		jp.add(vspr);
		
		String s1 = Path.getPath()+"/images/program_h6_bg_water.gif";
		if(IsMacorWin.isMacOrWin()==false) s1 = Path.getPath()+"images\\program_h6_bg_water.gif";

		waterDisp=new GJCheck(s1);
		//"/Users/sakaguti/Documents/workspace2/GJCheck/src/images/program_h6_bg_water.gif");
		waterDisp.setIconText("水位");
		waterDisp.setName("水位");
		if(status.contains("Water")==true) waterDisp.setSelected(true);
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.gridwidth= 3;
		constraints1.gridheight = 1;
		constraints1.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(waterDisp, constraints1);
		jp.add(waterDisp);
		
		// separator
		JSeparator vspr2=new JSeparator(SwingConstants.VERTICAL);
		vspr2.setPreferredSize(new Dimension(30, 100));
		jp.add(vspr2);
		
		String s2 = Path.getPath()+"/images/program_h6_bg_illuminance.gif";
		if(IsMacorWin.isMacOrWin()==false) s2 = Path.getPath()+"images\\program_h6_bg_illuminance.gif";

		illumDisp=new GJCheck(s2);
		//"/Users/sakaguti/Documents/workspace2/GJCheck/src/images/program_h6_bg_illuminance.gif");
		illumDisp.setIconText("照度");
		illumDisp.setName("照度");
		if(status.contains("Illum")==true) illumDisp.setSelected(true);
		constraints1.gridx = 2;
		constraints1.gridy = 0;
		constraints1.gridwidth= 3;
		constraints1.gridheight = 1;
		constraints1.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(illumDisp, constraints1);
		jp.add(illumDisp);
		
		//	horizontal line
		constraints1.gridx = 0;
		constraints1.gridy = 1;
		constraints1.gridwidth= 1;
//		constraints1.gridheight = 1;
		constraints1.insets = new Insets(0, 0, 0, 0);
		JSeparator jspr=new JSeparator();
		jspr.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jspr.setPreferredSize(new Dimension(480, 10));
		jspr.setSize(200, 10);
		gridbag.setConstraints(jspr, constraints1);
		jp.add(jspr);	// 水平線
		

		// accept button
		if(acceptBtn == null)
			acceptBtn=new JButton("この設定を適用する");
		acceptBtn.setName("この設定を適用する");	
		acceptBtn.setPreferredSize(new Dimension(180,60));
		acceptBtn.setEnabled(true);// いつもアクティブ
		// set ActionListner
		acceptBtn.addActionListener(this);
		
		constraints1.gridx = 0;
		constraints1.gridy = 5;
		constraints1.gridwidth= 1;
		constraints1.gridheight = 1;
		constraints1.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(acceptBtn, constraints1);
		jp.add(acceptBtn);
	
	
 // ToDo
		JLabel glabel=new JLabel();
		glabel.setText("メール送信時間");
		GridBagConstraints constraintsTLabel = new GridBagConstraints();
		constraintsTLabel.gridx = 1;
		constraintsTLabel.gridy = 4;
		gridbag.setConstraints(glabel, constraintsTLabel);
		jp.add(glabel);
		
		
		DelDoubleComb gjp=new DelDoubleComb();
		gjp.setEnabled(true);
		gjp.setButtonEnabled(false);
		System.out.println("ReportPanelClass |"+Files.getReportsPeriodicTime()+"|");
		String[] hm=Files.getReportsPeriodicTime().split(":");
		System.out.println("ReportPanelClass h|"+hm[0]+"|");
		System.out.println("ReportPanelClass m|"+hm[1]+"|");
		//
		//
		hm[0]=hm[0].replace("00", "0");
		hm[0]=hm[0].replace("01", "1");
		hm[0]=hm[0].replace("02", "2");
		hm[0]=hm[0].replace("03", "3");
		hm[0]=hm[0].replace("04", "4");
		hm[0]=hm[0].replace("05", "5");
		hm[0]=hm[0].replace("06", "6");
		hm[0]=hm[0].replace("07", "7");
		hm[0]=hm[0].replace("08", "8");
		hm[0]=hm[0].replace("09", "9");
		int h=Integer.parseInt(hm[0].replaceAll("[^0-9]",""));//

		hm[1]=hm[1].replace("00", "0");
		hm[1]=hm[1].replace("01", "1");
		hm[1]=hm[1].replace("02", "2");
		hm[1]=hm[1].replace("03", "3");
		hm[1]=hm[1].replace("04", "4");
		hm[1]=hm[1].replace("05", "5");
		hm[1]=hm[1].replace("06", "6");
		hm[1]=hm[1].replace("07", "7");
		hm[1]=hm[1].replace("08", "8");
		hm[1]=hm[1].replace("09", "9");
		int m=Integer.parseInt(hm[1].replaceAll("[^0-9]",""));//
		
		gjp.setHour(h);
		gjp.setMin(m);
		gjp.addObserver((java.util.Observer)new ReportPanel.ObserverA());
		
		GridBagConstraints constraintsT = new GridBagConstraints();
		constraintsT.gridx = 2;
		constraintsT.gridy = 4;
		gridbag.setConstraints(gjp, constraintsT);
		jp.add(gjp);
	
		
		//
		c.setLayout(new BorderLayout());
		c.add( jp, BorderLayout.CENTER );
		
		// 観察される人を生成
		observableMan = new ObservableMan();

		// デフォルトの観察者を追加
		defaultO=new ObserverA();
		setObserver(defaultO);
	}
	
	//必要なメッソドを追加
	// Observerを追加する
	void addObserver(Observer o)
	{
		observableMan.addObserver(o);
	}

	// Observerを設定する
	// 以前に追加されたObserverは全て破棄される
	void setObserver(Observer o)
	{
		observableMan.deleteObservers();
		observableMan.addObserver(o);
	}

	// Observerを削除
	void deleteObserver(Observer o)
	{
		observableMan.deleteObserver(o);
	}

	//
	private ObservableMan observableMan=null;  //  @jve:decl-index=0:
	private String message="";  //  @jve:decl-index=0:
	private Observer defaultO=null;  //  @jve:decl-index=0:

	static public boolean temp=false;
	static public boolean water=false;
	static public boolean illum=false;
	
	static public int rHour=0;
	static public int rMin=0;
	
	//各クラス固有

	/**
	* 観察者を観察する人A。
	*
	*/
	static class ObserverA implements Observer {
		//
	@Override
	public void update(Observable arg0, Object arg1) {

		
		String message=(String)arg1;
		
		if(message.contains("温度")) temp  = tempDisp.isSelected();
		if(message.contains("水位")) water = waterDisp.isSelected();
		if(message.contains("照度")) illum = illumDisp.isSelected();
		//acceptBtn.setEnabled(temp|water|illum);
		
		String str = (String) arg1;
		System.out.println("私はReportPanel classです。観察対象の通知を検知したよ。" + str);
		if(str.contains("HourComb")==true){
			String[] s=str.split(" ");
			rHour=Integer.parseInt(s[s.length-1]);
		}
		if(str.contains("minComb")==true){
			String[] s=str.split(" ");
			rMin=Integer.parseInt(s[s.length-1]);
		}
			
	}
	}

	
	public static void  acceptEnable(boolean b)
	{
		if(acceptBtn!=null)
			acceptBtn.setEnabled(b);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//
		JButton cb=(JButton)arg0.getSource();
		temp = tempDisp.isSelected();
		water = waterDisp.isSelected();
		illum = illumDisp.isSelected();

		message = "私はReportPanel classです。"+cb.getName()+" ボタンが押されました。temp "+temp+" water "+water+" illum "+illum +" time "+rHour+":"+rMin;
		String s="";
		if(temp==true ) s+="Temp ";
		if(water==true) s+="Water ";
		if(illum==true) s+="Illum ";
		s+=rHour+":"+rMin;//+System.getProperty("line.separator");// report mail send time at one day
		Files.setReports(s);
		Files.savePlantersSettings();
		//
		stopReportTimer();
		startReportTimer();
		//
		// Do processing using temperature value
		observableMan.setMessage(message);
		// 観察者全員に通知
		observableMan.notifyObservers();
		//
	}

	public void setTemp(boolean b) {
		tempDisp.setSelected(b);
	}
	public void setWater(boolean b) {
		waterDisp.setSelected(b);
	}
	public void setIllum(boolean b) {
		illumDisp.setSelected(b);
	}

	public void setTime(String s) {
		String[] hm=s.split(":");
		int ih=Integer.parseInt(hm[0].replaceAll("[^0-9]",""));//
		int im=Integer.parseInt(hm[1].replaceAll("[^0-9]",""));//
		rHour=Integer.valueOf(ih);
		rMin=Integer.valueOf(im);
//		rHour=Integer.valueOf(s.split(":")[0]);
//		rMin=Integer.valueOf(s.split(":")[1]);
	}
	public Timer longTimer =null;
	public void startReportTimer(){
		longTimer = new Timer(true);
		Calendar calendar=Calendar.getInstance();
		int h=calendar.get(Calendar.HOUR_OF_DAY);
		int m=calendar.get(Calendar.MINUTE);
		
		if( longTimer != null){
			longTimer.cancel();
			longTimer.purge();
		}
		longTimer=new Timer(true);
		//
		long startTime=60*(60*(rHour-h)+(rMin-m))*1000;// msec
		//
		if(startTime<0) startTime += 24*60*60*1000;
		//
System.out.println("ReportPanelClass: rHour "+rHour+" h "+h+" rMin "+rMin+" m "+m);
System.out.println("ReportPanelClass: startTime="+startTime);
		//
		longTimer.schedule(new TaskReportLong(), startTime, (24*60*60*1000)); // タスクの実行間隔は24時間に１度

	}
	
	public void stopReportTimer()
	{
		System.out.println("PlanterSettingClass: Stop Long Sensor Timer");

		if( longTimer != null){
			longTimer.cancel();
			longTimer.purge();
		//	longTimer = new Timer();
		}
	}

	public void purgeReportTimer()
	{
		if( longTimer != null){
			longTimer.purge();
		//	longTimer=new Timer();
		}
	}
	
	class TaskReportLong extends TimerTask {
	    @Override
		public void run() {
	    	
	    	for(int i=0;i<ITPlanterClass.getPlanterList().size();i++){
	    	String s=ITPlanterClass.getPlanterList().get(i).getSensor().getSensors();
	    	System.out.println("SensorPanelClass: TaskSensorLong "+s+" "+i);

	    	TimeDate td=new TimeDate();
	    		String pn="Report from "+ITPlanterClass.getPlanterList().get(i).getInformation().getPlanterName()+",";
	    		String recordData=pn+" SensorRecord: "+td.getYear()+":"+td.getMonth()+":"+td.getDay()+" "+td.getHour()+":"+td.getMin()+" Temp "+temp+" Water "+water+" Illum "+illum;
	 			
				if(Sensor.temperatureReport==true||Sensor.illuminationReport==true||Sensor.waterlevelReport==true){
				sendMail.setMessageText(recordData);
	    		// お知らせメールを出す。
				/*
				if(Sensor.temperature > Sensor.getTemperatureWarningLevel()){
					Sensor.temperatureReport=true;
					sendMail.addMessageText("Temp "+String.valueOf(Sensor.temperature));
				} else Sensor.temperatureReport=false;
				if(Sensor.illumination > Sensor.getIlluminationWarningLevel()){
					Sensor.illuminationReport=true;
					sendMail.addMessageText("Illum "+String.valueOf(Sensor.illumination));
				} else Sensor.illuminationReport=false;
				if(Sensor.waterlevel > Sensor.getWaterWarningLevel()){
					Sensor.waterlevelReport=true;
					sendMail.addMessageText("Water "+String.valueOf(Sensor.waterlevel));// Sensor.getWaterLevel();
				} else Sensor.waterlevelReport=false;
				*/
				sendMail.addMessageText("Temp "+String.valueOf(Sensor.temperature));
				sendMail.addMessageText("Illum "+String.valueOf(Sensor.illumination));
				sendMail.addMessageText("Water "+String.valueOf(Sensor.waterlevel));// Sensor.getWaterLevel();
					//
					Settings.readMailSetup();// read from file
					//PlanterSetting.setOptions() ;// read from panel
					sendMail.send();// おしらせメール
					System.out.println("SensorPanelClass: recordData2="+recordData);
				}		
	    	}
	    }
	}

}


