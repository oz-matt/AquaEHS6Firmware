import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import java.io.*;
import java.util.Random;
import java.util.Vector;

import javax.microedition.io.*;

import com.cinterion.io.ATCommand;
import com.cinterion.io.ATCommandFailedException;
import com.cinterion.io.ATCommandListener;
import com.cinterion.io.ATCommandResponseListener;
import com.cinterion.io.InPort;

import org.json.me.*;

public class AquaEHS6Firmware extends MIDlet {
	
	InPort inport = null;
	
	static String destHost = "198.61.169.55";
	static String destPort = "8081";
	static String connProfile = "bearer_type=gprs;access_point=data641003";
	
	public AquaEHS6Firmware() {
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	        try {
	            m_Cmd.release();
	        } catch (IOException e) {
	            System.out.println(e);
	        }

	        notifyDestroyed();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}
	private ATCommand m_Cmd;
	
	/*private String replaceinstr( String str, String pattern, String replace ) 
	{
	    int s = 0;
	    int e = 0;
	    StringBuffer result = new StringBuffer();

	    while ( (e = str.indexOf( pattern, s ) ) >= 0 ) 
	    {
	        result.append(str.substring( s, e ) );
	        result.append( replace );
	        s = e+pattern.length();
	    }
	    result.append( str.substring( s ) );
	    return result.toString();
	}   
	*/
	protected void startApp() throws MIDletStateChangeException {
		
		int coor = 100;
		Random r = new Random();
		int rcoor = r.nextInt(900) + 100;
		try {
            m_Cmd = new ATCommand(false);
        } catch (Exception e) {
            System.out.println(e);
        }
		  //CommConnection  		commConn;
		 while(true) { 
	      HttpConnection		hc = null;
		  OutputStream			os = null;
		  
		  //DataInputStream     	inStream = null;
		  //OutputStream    		outStream = null;
		  
		  //Vector flowin = new Vector();
		  //flowin.addElement("GPIO12");
		  System.out.println("Starting");
		  
		  
		  try {
			  String CarrierInfo = m_Cmd.send("at^sind?\r");
	          
			  String CarrierInfoFormatted = CarrierInfo.replace('\r', ' ');
	          CarrierInfoFormatted = CarrierInfoFormatted.replace('\n', ' ');
	          CarrierInfoFormatted = CarrierInfoFormatted.replace('\"', ' ');
	          //CarrierInfoFormatted = replaceinstr(CarrierInfoFormatted, "\"", "\\\"");
	          
	          System.out.println(CarrierInfoFormatted);// + aqsenObj.toString());
			  
	          
			  String openParm = "http://" + destHost + ":" + destPort + ";" + connProfile;
			  
			  hc = (HttpConnection) Connector.open(openParm);
			  
			  hc.setRequestMethod(HttpConnection.POST);
			  hc.setRequestProperty("Content-Type", "application/json");
			  
			  //is = hc.openInputStream();
			  os = hc.openOutputStream();
			  
			  
			  //inport = new InPort(flowin);
			  //String strCOM = "comm:COM1;baudrate=9600;bitsperchar=8;stopbits=1;parity=none;blocking=off;autocts=off;autorts=off";
			  //commConn = (CommConnection)Connector.open(strCOM);
	          //commConn.setDSR(true);
			  //inStream  = commConn.openDataInputStream();
			  //outStream = commConn.openOutputStream();
	          
			  /*aqsenObj = new JSONObject("{reqtype:'aqsen', aquakey:'D4F8C385DE09BF63', iid:'123.121.89.23'}");
			  innerAqsenObj = new JSONObject("{datetime:'2017-09-28T14:24:21.966Z', uuid:'45674567-4567-4567-6789-456745672345', custom:{}, gpsextended:{}, incoming_ip:'68.56.45.34', install_id:'abcdefghijklmnop'}");
			  gpsObj = new JSONObject("{time:'2017-09-28T14:24:21.966Z', numsat:'6', lon:'-44.56756', lat:'49.67867', height:'35.4564', gspeed:'0.0066', direction:'354.4567'}");
			  sensorsObj = new JSONObject("{pct_battery:'66', accelerometer:'0,33,11', temperature:'54', humidity:'7', pressure:'5', update_rate:'1'}");
			  
			  devSeenArr = new JSONArray("['aa:bb:cc:dd:ee:ff', '00:11:22:33:44:55']");
			  bleObj = new JSONObject("{}");
			  
			  bleObj.put("devices_seen", devSeenArr);
			  
			  innerAqsenObj.put("gpsminimum", gpsObj);
			  innerAqsenObj.put("sensors", sensorsObj);
			  innerAqsenObj.put("ble", bleObj);
			  
			  aqsenObj.put("aqsen", innerAqsenObj);
			  */
			  
			  Thread.sleep(100);
			  
			  coor++;
			  int ircoor = rcoor + coor;
			  if (ircoor >= 1000) ircoor  = ircoor - 900;
			  
			  System.out.println("aqobj: ");// + aqsenObj.toString());
			  
			  String omg = "{\"reqtype\":\"aqsen\",\"aquakey\":\"D4F8C385DE09BF63\",\"iid\":\"50.176.32.147\",\"aqsen\":{\"datetime\":\"2017-09-30T14:24:21.966Z\",\"uuid\":\"108af48b-7071-419e-812f-8cb06d763db5\",\"gpsminimum\":{\"time\":\"2017-02-17T09:44:00.280Z\",\"numsat\":\"6\",\"lon\":\"99.4" + coor + "\",\"lat\":\"35." + ircoor + "\",\"height\":\"35.5\",\"gspeed\":\"0.00543\",\"direction\":\"354.6796\"},\"gpsextended\": {\"carrierdata\": \"" + CarrierInfoFormatted + "\"},\"sensors\":{\"ptc_battery\":\"85\",\"accelerometer\":\"0,34,5\",\"temperature\":\"54\",\"humidity\":\"10\",\"pressure\":\"5\",\"update_rate\":\"1\"},\"ble\":{\"devices_seen\":[\"AA:BB:CC:DD:EE:FF\",\"11:22:33:44:55:66\",\"77:88:99:00:AA:BB\",\"00:11:22:33:44:55\",\"AA:BB:CC:DD:EE:EE\"]},\"custom\":{},\"incoming_ip\":\"68.15.55.192\",\"install_id\":\"abcdefghijklmnop\"}}";
			  os.write(omg.getBytes());
			  os.flush();
			  //os.write(aqsenObj.toString().getBytes());
			  System.out.println("done!");// + aqsenObj.toString());
			  
			  //String yo = "yooyyo";
			  //os.write(yo.getBytes());
			  
			  /*StringBuffer sb = new StringBuffer();
			  int ch;
			  while ((ch = is.read()) != -1) {
				  sb.append((char)ch);
			  }
			  System.out.println("got: " + sb);*/
			  
			  Thread.sleep(100);
			  
			  //is.close();
			  os.close();
			  hc.close();
			  Thread.sleep(900000);
			  
			  //k = new JSONObject("{data : 'we have data!'}");
	          //j.put("OtherJson", k);
	          //System.out.println(j.toString(2));
	          
			  //while(true) {
			  //  int inchr = inStream.read();
			  //  if (inchr != 0)System.out.println("got char: " + inchr);
			  //}
	          
	          /*while(inport.getValue() != 1);
	          System.out.println("got port high!");
	          int chr;
	          while((chr = inStream.read()) != -1) sb.append((char) chr);
	          
	          System.out.println("Exited loop");
	          System.out.println("in str: " + sb.toString());
	          */
		  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		  } //catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (JSONException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
//}
 //} catch (JSONException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
} catch (IllegalStateException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IllegalArgumentException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ATCommandFailedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}}
		 
	}

}
