import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class PlanterSelecter extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		PlanterSelecter gjp=new PlanterSelecter();
		
		frame.getContentPane().add(gjp);
		
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * This is the default constructor
	 */
	public PlanterSelecter() {
		super();
		initialize();
	}
	
	ITPlanterClass itpClass=null;  //  @jve:decl-index=0:
	ArrayList<PlanterClass> planterList=null;  //  @jve:decl-index=0:
	JPanel base=null;
	static JPanel baseS=null;
	JButton selectButton=null;
	
	// Observerを追加する
	void addObserver(Observer o)
	{
		//observableMan.deleteObserver(defaultO);// 前に設定されたObserverを削除する。
		//observableMan.deleteObservers();
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
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.equals(selectButton)==false) return;
	      JButton btn = (JButton)arg0.getSource();
	      
	      if(btn.equals(selectButton)){
//	    	  System.out.println("addTimeButton");  	  
	      } 
	      
	      message ="私はPlanterSelecter classです。"+btn.getText()+"が押されました。";
	      	observableMan.setMessage(message);
			// 観察者全員に通知
			observableMan.notifyObservers();
			
	}
	
	private static ObservableMan observableMan=null;  //  @jve:decl-index=0:
	private static String message="";
	private static Observer defaultO=null;  //  @jve:decl-index=0:
	
	/**
	* 観察者を観察する人A。
	*
	*/
	static class ObserverA implements Observer {
	/* (非 Javadoc)
	* @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	*/
	public void update(Observable o, Object arg) {
	String str = (String) arg;
	//
	
	System.out.println("私はPlanterSelecter classです。観察対象の通知を検知したよ。" + str);
	// CameraSelecter class からのCamNoの値を反映する
	
	// プランターの名前が変更された
//System.out.println("PlanterSelecterClass : str="+str);
	if(str.contains("CameraSelect")== true){
		
		System.out.println("CameraSelect="+str);		
	// プランター選択からカメラが選択された。
	// Menuのカメラの名前のチェック印を変更する
		String[] para=str.split(" ");
		if(para.length<5) return;
		// 		    String message="PlanterSelect "+"planter "+pointPlanter+" camera "+s;
		String curPlanterName=para[2];
		String curCameraName =para[4];
		int thisplanterNo=0;
		//
		// search planter by name
		for(int i=0;i<ITPlanterClass.getPlanterList().size();i++){
		PlanterClass p = ITPlanterClass.getPlanterList().get(i);
		if(p.getInformation().getPlanterName().equals(curPlanterName.replace(checkMark, ""))==true) thisplanterNo=i;
		}
System.out.println("CameraSelecterClass: PlanterNo="+thisplanterNo);		
	//
	// curCameraName のメニューを得る
	// メニューの項目の curPlanterName を変更する
// PlanterListの中のoPlanterをnPlanterに入れ替える
		if(pupUps.size()<=0) return;
			for(int j=0;j<pupUps.size();j++){
				popup = pupUps.get(j);//popup = pupUps.get(0);
				for(int i=0;i<popup.getComponentCount();i++){
					JMenuItem menu = (JMenuItem) popup.getComponent(i);
					String s=menu.getText();
					if(curPlanterName.contains(checkMark)==true) curCameraName=checkMark+curCameraName.replace(checkMark, "");
System.out.println("PlanterSelecterClass: menuText="+s+" curCameraName "+curCameraName);
					if(s.contains(curCameraName.replace(checkMark, ""))==true){
							menu.setText(curCameraName);
					}
			}
		}
	} else
	// TODO
	// スペースがある名前にも対応すること
	//
	if(str.contains("CameraNameChange")==true){
		
		
		str=str.replace("CameraNameChange ", "");
//System.out.println("str "+str);
		String[] nCn=str.split(",");
		int mn=Integer.parseInt(nCn[0]);// menu item number
		String nCamera=nCn[1];// new Camera Name
		
//System.out.println("Cam   No:"+nCn[0]+" "+popup.getComponentCount()+" mn="+mn);// Cam No
//System.out.println("Cam Name:"+nCn[1]);// Cam Name
		
		
		//
		if(pupUps==null) return;
		if(pupUps.size()==0) return;
		//
		popup = pupUps.get(0);
		if(popup.getComponentCount()<mn) return;
		//
		String oCamera=((JMenuItem) popup.getComponent(mn)).getText();
		oCamera=oCamera.replace(checkMark, "");
//		System.out.println("menu.getText "+oCamera);
		
//System.out.println("PlanterSelecterClass: Camera Name change from "+oCamera+" to "+nCamera);
	ITPlanterClass.getCurrentPlanterClass().getInformation().removeCameraName(oCamera);			
	ITPlanterClass.getCurrentPlanterClass().getInformation().setCameraName(nCamera);			

//	System.out.println("pupUps.size() "+pupUps.size());

		if(pupUps!=null && pupUps.size()>0){
		for(int j=0;j<pupUps.size();j++){
				popup = pupUps.get(j);// プランターの数だけ処理する
				
			JMenuItem menu = (JMenuItem) popup.getComponent(mn);

			if(menu.getText().contains(oCamera)==true){
//				System.out.println("menu.getText1 "+menu.getText());
				String s=menu.getText();
				s=s.replace(oCamera, nCamera);
//				System.out.println("menu.getText2 "+menu.getText());
				menu.setText(s);	// 新しいカメラの名前をメニューに登録			
				//	
				PlanterClass p1 = ITPlanterClass.getPlanterList().get(pointPlanter);			
					p1.getInformation().removeCameraName(oCamera);
//					p1.getInformation().setCameraName(nCamera);
					}
				}
			}
		//
	} else 
		if(str.contains("CameraNameList")==true){
			// カメラの名前のリストが届いたので、メニュにあるカメラのリストを作り直す。
			str=str.replace("CameraNameList ", "");// remove keyword
			String[] cameraList=str.split(",");
			
			int cameraListSize=cameraList.length;// "私はCameraSelecterClassです。観察対象の通知を検知したよ。 CameraNameList "
System.out.println("PlanterSelecterClass1:  camera no="+cameraListSize);
ITPlanterClass.getCurrentPlanterClass().getInformation().setCameraNumberMax(cameraListSize);
			// set camera number
//			ITPlanterClass.getCurrentPlanterClass().getInformation().setCurrentCameraNumber(0); // default cam no is 0
System.out.println("PlanterSelecterClass: pupUps.size="+pupUps.size());// プランターの数の２倍になっている
			
			if(pupUps!=null && pupUps.size()>0){
				//  プランターが１台しかつながっていないのに、pupUps.size()が２になっている。
				for(int planterNo=0;planterNo < pupUps.size()/2;planterNo++){ // 
System.out.println("PlanterSelecterClass: j="+planterNo+" pupUps.size() "+pupUps.size());
				popup = pupUps.get(planterNo);// プランターの数だけ処理する
				//
//				JMenuItem menuChName = (JMenuItem) popup.getComponent(popup.getComponentCount()-1);
				popup.removeAll();				
				//
				for(int i=0;i<cameraListSize;i++){
System.out.println("PlanterSelecterClass: cameraList"+"["+i+"]="+cameraList[i]);
				JMenuItem menu = new JMenuItem("CAM-"+i);
				// すでにmenuに登録されていたら、二重に登録しない				
				//
					menu.setName("PopUp"+i);
					String s=checkMark+cameraList[i];			
					menu.setText(s);//
					menu.addActionListener(new MyAction());
					popup.add(menu);
					
					System.out.println("PlanterSelecterClass: i="+i+" cameraList.length "+cameraList.length+" planterNo "+planterNo);
					//if(ipn<=planterNo)
					//	ITPlanterClass.getPlanterList().add(new PlanterClass("ITPLANTER-"+planterNo));
					ITPlanterClass.getPlanterList().get(planterNo).getInformation().setCameraName(i,cameraList[i]);
														
					//　このカメラを登録する
//					PlanterClass pc = ITPlanterClass.getCurrentPlanterClass();
//					pc.getInformation().setCameraName(s.replace(checkMark, ""));
System.out.println("PlanterSelecterClass: "+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraName(i)+"が登録されました。"
		+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber());						
					}

				
				
				
//					popup.add(menuChName);
				}// next j
				
				cameraListSize=ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber();
				System.out.println("PlanterSelecterClass2:  camera no="+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber());
				for(int i=0;i<cameraListSize;i++){
					System.out.println("["+i+"]"+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraName(i));	
				}
	
			}
		}// CameraNameList
	
	//System.out.println("pupUps.size()="+pupUps.size());
	if(pupUps.size()<=0) return;
/*	
	//
	// プランターの名称変更タグ
	boolean sw=false;
	for(int i=0;i<popup.getComponentCount();i++){
	JMenuItem menu = (JMenuItem) popup.getComponent(i);
	if(menu==null) break;
	System.out.println("menuText |"+menu.getText()+"|");
	if(menu.getText().contains("プランターの名称変更")==true) sw=true;
	}
	
	//
	if(sw == false ){
	JMenuItem menu = new JMenuItem("プランターの名称変更");
	popup.add(menu);
	if(ITPlanterClass.getState()==false) new ITPlanterClass();
	int i=ITPlanterClass.getCurrentPlanterNo();// Planter No
	menu.setName("PopUpPlanterNameChange"+String.valueOf(i));// Planter's No
	menu.addActionListener(new MyNameChangeAction2());
	pupUps.add(popup);
	}
*/
	
	}
	
	class MyAction2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	//メニューが選択されると、このメソッドが呼ばれる
			JMenuItem mi=(JMenuItem)e.getSource();
			String s=mi.getText();
		
			PlanterClass p = ITPlanterClass.getPlanterList().get(pointPlanter);
			
			if(s.contains(checkMark)){
				s=s.replace(checkMark, "");
				//　このカメラを抹消する
				p.getInformation().removeCameraName(s);
//				p.getInformation().setCameraName("--remove--"+s);
	System.out.println("PlanterSelecterClass1: "+p.getInformation().getPlanterName()+"の"+s+"が抹消されました。");
			}
			else{
				//　このカメラを登録する
				p.getInformation().setCameraName(s.replace(checkMark, ""));
				s=checkMark+s;
	System.out.println("PlanterSelecterClass1: "+p.getInformation().getPlanterName()+"の"+s.replace(checkMark, "")+"が登録されました。");
			}
			mi.setText(s);
		}
	}
	
	class MyNameChangeAction2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	//プランターの名称変更が選択されると、このメソッドが呼ばれる
			//
//			System.out.println("プランターの名称変更が選択2");
			JMenuItem mi=(JMenuItem)e.getSource();
			String s=mi.getName();
//			System.out.println("メニュー　"+s);
			//int i=Integer.parseInt(s);
			int i = Integer.parseInt(s.replaceAll("[^0-9]",""));
			
//			System.out.println("PlanterSelecter:プランターの番号　"+i);
			String oPlanterName=ITPlanterClass.getPlanterList().get(i).getInformation().getPlanterName();
//		    System.out.println("PlanterSelecter01:修正前のプランターの名前　"+oPlanterName);

		    String nPlanterName = JOptionPane.showInputDialog("アイティプランターの名前を入力", oPlanterName);
		    System.out.println("PlanterSelecter|"+nPlanterName);
		    // Cancel then null
		    if(nPlanterName.equals("")==false)
		    	ITPlanterClass.getPlanterList().get(i).getInformation().replacePlanterName(oPlanterName, nPlanterName);
//		    System.out.println("PlanterSelecter02:修正後のプランターの名前　"+ITPlanterClass.getPlanterList().get(i).getInformation().getPlanterName());
		    
		    JPanel pc=(JPanel) baseS.getComponent(i);
		    PlanterIcon planterIcon=(PlanterIcon) pc.getComponent(i);
			// Planter Iconの名前を変更する
		    planterIcon.setPlanterName(nPlanterName);
		    //revalidate();
		    		
		    // Save planter name to file
		   String serial=ITPlanterClass.getCurrentPlanterClass().getSerial();
		   Files.setPlanterName(nPlanterName,serial);// set Planter Name to file
		   Files.savePlantersSettings();// save settings
		}
	}

	}
	
	public void reListPlanter()
	{
		baseS.removeAll();
		//ITPlanterClass.reMakePlanterList();
		planterList=ITPlanterClass.getPlanterList();
			for(int i=0;i<planterList.size();i++){
					baseS.add( planterCase(i) );
			}
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		GridBagLayout gridbag = new GridBagLayout();
		base=new JPanel(gridbag);
		
		//base.setPreferredSize(new Dimension(640,400));
		base.setBackground(new Color(250,251,245));

		baseS=new JPanel();
		baseS.setPreferredSize(new Dimension(640,150));
		baseS.setBackground(new Color(250,251,245));
		baseS.setName("PlanterSelecter");
		baseS.addMouseListener(this);

		
		JScrollPane scrollPane = new JScrollPane(baseS);
		// 縦スクロールバーを表示しない。
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//scrollPane.setPreferredSize(new Dimension(200, 160));
		scrollPane.setBorder(null);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;	
		constraints.gridy = 0;	
		constraints.gridwidth= 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(scrollPane, constraints);
		
		// ここが全ての始まりになる。
		if(ITPlanterClass.getState()==false){
			new ITPlanterClass();// 一度だけコンストラクターを呼ぶこと。
		}
		
		planterList=ITPlanterClass.getPlanterList();
		//
		if(planterList!=null)
			for(int i=0;i<planterList.size();i++){
					baseS.add( planterCase(i) );
			}
		base.add(scrollPane);
		
		this.add(base);
		this.setBackground(new Color(250,251,245));
		
		// 観察される人を生成
		observableMan = new ObservableMan();

		// デフォルトの観察者を追加
		defaultO=new PlanterSetting.ObserverA();
		observableMan.addObserver(defaultO);
		
		defaultO=new Selecters.ObserverA();
		observableMan.addObserver(defaultO);
		
		defaultO=new CameraSelecter.ObserverA();
		observableMan.addObserver(defaultO);
		
	}
	
	private static ArrayList<JPopupMenu> pupUps=new ArrayList<JPopupMenu>();  //  @jve:decl-index=0:
	// menu
	private static JPopupMenu popup =null;
	
	
	public JPanel planterCase(int i)
	{
		PlanterIcon planterIcon=ITPlanterClass.getPlanterList().get(i).getActiveIcon();
		planterIcon.setOpaque(false);
		
		GridBagLayout gridbag = new GridBagLayout();
		JPanel planterCase=new JPanel(gridbag);
		planterCase.addMouseListener(this);
		planterCase.setBackground(new Color(250,251,245));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;	
		constraints.gridy = 0;	
		constraints.gridwidth= 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(planterCase, constraints);
		planterCase.add(planterIcon);
		planterCase.setName("planterCase"+String.valueOf(i));
		
		// PopUp Menue
		popup = new JPopupMenu();

		// 全てのカメラを登録する
		for(int c=0;c<ITPlanterClass.getSystemCameraSize();c++){
		JMenuItem menu = new JMenuItem("CAM-"+c);
		menu.setName("PopUp"+c);
		menu.addActionListener(new MyAction());
		popup.add(menu);
		}

		// プランターの名称変更タグ
//
//	プランターの名前変更をすると、いろいろ面倒でうまくいかないので、当面、デフォルト名を使う。
//
//		JMenuItem menu = new JMenuItem("プランターの名称変更");
//		//menu.setName("PopUp"+ITPlanterClass.getSystemCameraSize()+1);
//		menu.addActionListener(new MyNameChangeAction());
//		menu.setName(String.valueOf(i));// Planter's No
//		popup.add(menu);	// menuに入れない
		pupUps.add(popup);
		

		return planterCase;
	}

	private static String checkMark="✓";
	private static int pointPlanter=0;

	class MyNameChangeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	//プランターの名称変更が選択されると、このメソッドが呼ばれる
			//
//			System.out.println("プランターの名称変更が選択");
			JMenuItem mi=(JMenuItem)e.getSource();
			String oPlanterName=mi.getText();
//System.out.println("メニュー　"+oPlanterName);// プランターの名前を変更になっている
	//		int i=Integer.valueOf(s);
//System.out.println("mi.getName()　"+mi.getName());
//			int mNo=Integer.valueOf(mi.getName());
			int mNo=Integer.parseInt(mi.getName().replaceAll("[^0-9]",""));//


			// Planter Iconの名前を得る
			JPanel pc=(JPanel) baseS.getComponent(0);
			PlanterIcon planterIcon=(PlanterIcon) pc.getComponent(0);// 一つしかないので０を入れること
			oPlanterName=planterIcon.getPlanterName();
//System.out.println("planterIcon.getName　"+oPlanterName);// プランターの名前を変更になっている

			
	//		String pname=ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName();
//System.out.println("PlanterSelecter11:修正前のプランターの名前　"+oPlanterName);
		    String nPlanterName = JOptionPane.showInputDialog("アイティプランターの名前を入力", oPlanterName);
		    if(nPlanterName==null) return;
//System.out.println("PlanterSelecter12:修正後のプランターの名前　"+nPlanterName);
//  変更したプランターの名前を登録する
		    ITPlanterClass.getCurrentPlanterClass().getInformation().replacePlanterName(oPlanterName,nPlanterName);// Save current Planter Name
//System.out.println("PlanterSelecter13:修正後のプランターの名前　"+ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName());
		    // Save planter name to file
//		   Files.setPlanterName(nPlanterName);// set Planter Name to file
			// Planter Iconの名前を変更する
			planterIcon.setPlanterName(nPlanterName);
			revalidate();

			//
			//
			//
			String serial=ITPlanterClass.getCurrentPlanterClass().getSerial();
			Files.setPlanterName(nPlanterName,serial);// set Planter Name to file
			Files.savePlantersSettings();// save settings
			   
		    message="PlanterNameChange "+mNo+","+nPlanterName;
			// 観察者全員に通知
			observableMan.notifyObservers();
		    
		    
		}
	}
	
	static class MyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	//メニューが選択されると、このメソッドが呼ばれる
			JMenuItem mi=(JMenuItem)e.getSource();
			int camNo=0;
			String k=mi.getName().replaceAll("[^0-9]","");//PupUp0
			if(k.length()>0) camNo=Integer.parseInt(k);//   PupUp0 ->  0
			//
//System.out.println("no="+mi.getName());
			//
			String s=mi.getText();// CAM-0		
//			PlanterClass p = ITPlanterClass.getPlanterList().get(pointPlanter); // PlanterList がおかしいのか？
			PlanterClass p = ITPlanterClass.getCurrentPlanterClass();
//System.out.println("PlanterSelecterClass camno0="+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber());// ???

			
System.out.println("PlanterSelecterClass pointPlanter　"+pointPlanter);
System.out.println("PlanterSelecterClass camno1="+p.getInformation().getCameraNumber());// ???

			if(s.contains(checkMark)){
				s=s.replace(checkMark, "");
				//　このカメラを抹消する
				p.getInformation().removeCameraName(s);
		System.out.println("PlanterSelecterClass1: "+p.getInformation().getPlanterName()+"の"+s+"が抹消されました。");
		System.out.println("PlanterSelecterClass1: camNo="+p.getInformation().getCameraNumber());
		p.getInformation().printCameras();
			}
			else{
				s=checkMark+s;
				//　このカメラを登録する
				p.getInformation().setCameraName(s.replace(checkMark, ""));
				s=p.getInformation().getCameraName(camNo);
				s=checkMark+s;
		System.out.println("PlanterSelecterClass1: "+p.getInformation().getPlanterName()+"の"+s.replace(checkMark, "")+"["+camNo+"]"+"が登録されました。");
		p.getInformation().printCameras();
			}
			//
			// カメラの選択結果を知らせる
		    String message="PlanterSelect "+"planter "+p.getInformation().getPlanterName()+" camera "+s;
		    System.out.println("PlanterSelecterClass message　"+message);
	System.out.println("PlanterSelecterClass camno2="+p.getInformation().getCameraNumber());
		    // 観察者全員に通知
		    observableMan.setMessage(message);
		    observableMan.notifyObservers();
		    
		    //
			mi.setText(s);
		}
	}
	

	
private int selectedNo=-1;//  現在、選択されているプランター番号
private JPanel selectedPanel=null;//  現在、選択されているプランターのパネル

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println("Clicked");
		if(arg0.getButton()==MouseEvent.BUTTON3) return;// 右ボタンはポップアップの処理に使うので。
		JPanel p=(JPanel)arg0.getSource();
		
		if(p.getName().contains("PlanterSelecter")){
			System.out.println("Re-Search ITPLANTER...");
			ITPlanterClass.reSearchPlanter();
			ITPlanterClass.getSystemPlanterNumber();
		//	ITPlanterClass.getSystemPlanterSize();
		}

		
			if(selectedPanel!=null) selectedPanel.setBackground(Color.WHITE);

			selectedPanel=(JPanel)arg0.getSource();
			//System.out.println("!!!!!!"+selectedPanel.getName());
			if(selectedPanel.getName().contains("planterCase")){
			//p.setBackground(Color.gray);
			
			
			String g=selectedPanel.getName().replaceAll("[^0-9]","");
			if(g.length()>0){
			selectedNo=Integer.parseInt(g);
			} else selectedNo=0;
			
			message ="私はPlanterSelecter classです。"+"このプランターを選択する"+"　が押されました。 CurrentPlanterNo "+selectedNo;
			
			//
			// ITPlanterClass.setCurrentPlanterClass(ITPlanterClass.getPlanterList().get(selectedNo));// set current planter
			//
/*			
			System.out.println("PlanterSelecterClass: selectNo1="+selectedNo);
			System.out.println("PlanterSelecterClass: entry cam number="+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber());
			for(int i=0;i<ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraNumber();i++){
			System.out.println("PlanterSelecterClass: entry cam number="+ITPlanterClass.getCurrentPlanterClass().getInformation().getCameraName(i));
			}
*/
			//			selectedNo=selectedPanel.getComponentCount()-1;//
//			System.out.println("selectNo2="+selectedNo);
			
			selectedPanel.setBackground(Color.GRAY);
			//
			// カレントプランターを設定
			ITPlanterClass.setCurrentPlanterNo(selectedNo);
			ITPlanterClass.setCurrentPlanterClass(ITPlanterClass.getPlanterList().get(selectedNo));
			// センサーの補正値読込
			ITPlanterClass.getCurrentPlanterClass().getSensor().setComp();
			
			// カレントプランターのシリアル番号の設定
				selectedNo++;// USBは１から始まるので１を加える 2013/4/18
				String wr = sendCom.sendcom(selectedNo+" -e Z"); // sendcom
				itp_Logger.logger.info(wr);
				String[]  arr=wr.split(System.getProperty("line.separator"),0);
				// SendComが受けたコマンドが正しいかどうかの検証
				if( arr[0].contains("Command: Z") == false){
				// 正しくない！
				itp_Logger.logger.info("アイティプランターが応答しません。");
				}
				System.out.println("selectedNo="+selectedNo+" Sereal="+arr[1]);
				ITPlanterClass.getPlanterList().get(ITPlanterClass.getCurrentPlanterNo()).setSerial(arr[1]);// serial number
			//
			//
		
	      	observableMan.setMessage(message);
			// 観察者全員に通知
			observableMan.notifyObservers();
			}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JPanel p=(JPanel)arg0.getSource();
		p.setBorder(new BevelBorder(BevelBorder.RAISED));
		// 現在、マウスがあるプランター番号
		//pointPlanter=Integer.parseInt(p.getName().replaceAll("[^0-9]",""));
		pointPlanter=p.getComponentCount()-1;
//		System.out.println("p.getComponentCount() "+pointPlanter);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JPanel p=(JPanel)arg0.getSource();
		p.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Lucida Grande", Font.PLAIN, 13), Color.black));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Windows Only
		
		if(arg0.getButton()==MouseEvent.BUTTON1 && IsMacorWin.isMacOrWin()==false){
			JPanel p=(JPanel)arg0.getSource();			
			//
			if(p.getName().contains("PlanterSelecter")){
				System.out.println("Re-Search ITPLANTER...!!");
				ITPlanterClass.reSearchPlanter();
				ITPlanterClass.getSystemPlanterNumber();
				// remake planterList
				
				// ここでプランター表示を書き換えること
				
				// redraw planterlistpanel
				p.setBackground(new Color(250,251,245));
				return;
			}
			
			if(p.getName().contains("planterCase")){
			//p.setBackground(Color.gray);
			message ="私はPlanterSelecter classです。"+"このプランターを選択する"+"　が押されました。";
			int i=Integer.parseInt(p.getName().replaceAll("[^0-9]",""));
			//
			// カレントプランターを設定
			ITPlanterClass.setCurrentPlanterNo(i);
			ITPlanterClass.setCurrentPlanterClass(ITPlanterClass.getPlanterList().get(i));
			// センサーの補正値読込
			// TODO
			// 各プランター毎に補正値を持たなければならない
			ITPlanterClass.getCurrentPlanterClass().getSensor().setComp();
			//
		
	      	observableMan.setMessage(message);
			// 観察者全員に通知
			observableMan.notifyObservers();
			}
			return;// Clicked
		}
		//  Windows Only End
		
		boolean trigger=arg0.isPopupTrigger();
		if(IsMacorWin.isMacOrWin()==false){
			trigger=!trigger;
		}		
		if (trigger) {
			JPanel p=(JPanel)arg0.getSource();
			int i=Integer.parseInt(p.getName().replaceAll("[^0-9]",""));
			JPopupMenu pop=pupUps.get(i);
			pop.show(arg0.getComponent(), arg0.getX(), arg0.getY());
		}
		if(arg0.getButton()==MouseEvent.BUTTON3) return;// 右ボタンはポップアップの処理に使うので。
		JPanel p=(JPanel)arg0.getSource();
		p.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getButton()==MouseEvent.BUTTON3) return;// 右ボタンはポップアップの処理に使うので。
		JPanel p=(JPanel)arg0.getSource();
		p.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
}
