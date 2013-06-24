//
//  communicate with itplanter by using USB
//	itplants.ltd.	2011
//	Yosiyuki SAKAGUCHI
//
//
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class sendCom {
	final static String sCurrentDir = new File(".").getAbsoluteFile().getParent();  //  
	final static String SendComcommandMac=new String(sCurrentDir+"/com"+"/Mac"+"/sendcom");  // MacOSX
	final static String SendComcommandWin=new String("\""+sCurrentDir+"\\com\\Win\\sendcom.exe"+"\"");  //Win
	
	 public static String SendComcommand()
	 {
		 
	 	if(IsMacorWin.isMacOrWin()==true ){
	 		// MacOSX
	 		return SendComcommandMac;
	 	} else {
	 		// Windows
	 		return SendComcommandWin;
	 	}
	 	
	 }

	 private static String sendcom_return = null;
//	 private static int sendcom_num = 0;
	 
	 
	// 外部アプリのsendcom をThreadで動かす。
	   public class SendCom implements Runnable {
	    	private String com;
	    	// Constructor
	    	public SendCom(String c){
	    		com=c;
	    	}
			@Override
			public void run() {
				//if(ITPlanterClass.getState()==false) new ITPlanterClass();
				//if(ITPlanterClass.getCurrentPlanterClass().getInformation().getPlanterName().equals("DUMMY_PLANTER")==true) return;
	            String mycommand=null;
				
				mycommand = SendComcommand()+" "+com;
				
				//System.out.println("SendCom:"+mycommand);
				
				Runtime runtime = Runtime.getRuntime();
				
				try {
					Process process = runtime.exec(mycommand);
					if(process==null){
						System.out.println("SendComが動作しません。");
					}
//					System.out.println("Command "+mycommand); //	
					try {
						process.waitFor();
						} catch (InterruptedException e1) {
						//e1.printStackTrace();
						}
					InputStream in = process.getInputStream(); 
					byte[] b = new byte[512];// 何故256でなければダメなんだろう？
					in.read(b);
					sendcom_return=new String(b,"UTF-8"); 
				  	} catch (IOException e1) {
				  		System.out.println("SendComが動作しません。");
				  		//e1.printStackTrace();
					}
	          //  System.out.println("RunnableTask is Done.");
	        }
	    }
	    
	//	private static int sendcom_num=0;
	// thread sendcom
	    public static  String sendcom(String com) {
//
//	    	String s="";
//			int st_sec = Calendar.getInstance().get(Calendar.SECOND);	
/*			
		 // version check
		for(int n=1;n<=ITPlanterClass.getSystemPlanterSize();n++){
		String comZ=SendComcommand()+" "+n+" -e Z";
	  	  s = sendcom_thread(comZ);  		
		  while(true){		  
				  // for debug
				  if(s.length()>=64){
					     break;// アイティプランターから応答があった
				  }
					int nw_sec = Calendar.getInstance().get(Calendar.SECOND);
					int passage=nw_sec-st_sec; //
					if(passage<0) passage+=60;
					  if(passage > 3){// time out = 3sec
						  System.out.println("sendcomがタイムアウトしました。");
						  s="error";
						  break;// Time out
					  }
						  try {
							Thread.sleep(10);
						  } catch (InterruptedException e) {
							e.printStackTrace();
					}
		  } 
		  // remove null strings
		  String[] a=s.split(System.getProperty("line.separator"),0);
		  String ssa="";
		  for(int i=0;i<a.length;i++){
			  if(a[i].length()<64) ssa += a[i]+"\r\n";
			  else
			  if(a[i].substring(0).compareTo(" ")==0) a[i].replaceFirst(" ","");
		  }
		  if(Integer.valueOf(ssa)==ITPlanterClass.getCurrentPlanterClass().getSerial()){
			  // OK
		  }
*/	  
	//  
		  
//itp_Logger.logger.info(com);
	  		String s = sendcom_thread(com);  		
/*
		  while(true){		  
				  // for debug
				  if(s.length()>=64){
					     break;// アイティプランターから応答があった
				  }
					int nw_sec = Calendar.getInstance().get(Calendar.SECOND);
					int passage=nw_sec-st_sec; //
					if(passage<0) passage+=60;
					  if(passage > 3){// time out = 3sec
						  System.out.println("sendcomがタイムアウトしました。");
						  s="error";
						  // アイティプランターが応答しなくなったので、PLTを再送信する。
						  // PLT_processingでは、つながっているプランターのみを選抜すること。
						  //PLT_processing();
						  break;// Time out
					  }
						  try {
							Thread.sleep(100);
						  } catch (InterruptedException e) {
							e.printStackTrace();
						  }
		  }
*/		  
//itp_Logger.logger.info(s);
	  	
		  // remove null strings
		  String[] b=s.split(System.getProperty("line.separator"),0);
		  String ss="";
		  for(int i=0;i<b.length;i++){
			  if(b[i].length()<64) ss += b[i]+"\r\n";
			  else
			  if(b[i].substring(0).compareTo(" ")==0) b[i].replaceFirst(" ","");// remove ' Water' space	  
		  }
//	    itp_Logger.logger.info(ss);
	    	return ss;
	    }
	    
	    //
	    public static  String sendcom_thread(String com) {
	        ExecutorService e = Executors.newSingleThreadExecutor();
	        sendCom sc=new sendCom();
	        Future<?> future = e.submit(sc.new SendCom(com));
	        
//	        System.out.println("RuunableTask is Called");
//	        System.out.println("Future#isDone(): " + future.isDone());
	 
	        try {
	        	try {
					future.get(30,TimeUnit.SECONDS);// TIME OUT 10 SEC
				} catch (TimeoutException e1) {
					e1.printStackTrace();
					
					//System.out.println("future.get time out");
					itp_Logger.logger.info("future.get time out");
					future.cancel(true);// cancel sendcom processing
					sendcom_return="error";
		            future.isDone();
					e.shutdown();
			        return sendcom_return;
				}
				
	            future.isDone();
	  //         System.out.println("Future#isDone(): " + sendcom_return);
	        } catch (ExecutionException ex) {
	            ex.printStackTrace();
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	        
	        e.shutdown();
	        return sendcom_return;
	    }
	    
	    
	    public  void setSendcom_return(String sendcom_return0) {
	    	sendcom_return = sendcom_return0;
	    }
	    
	    public  String getSendcom_return() {
	    	return sendcom_return;
	    }
	    
	    static int currentPlanterNo=0;
	    public void setCurrentPlanterNo(int i)
	    {
	    	currentPlanterNo=i;
	    }
	    
	    public String Lamp(boolean b){
			String result="";
			result = sendCom.sendcom(" "+currentPlanterNo+" -e L"); // sendcom
			return result;
		}	

	    public String Pump(boolean b){
			String result="";
			result = sendCom.sendcom(" "+currentPlanterNo+" -e P"); // sendcom
			return result;
		}	
	    
}
