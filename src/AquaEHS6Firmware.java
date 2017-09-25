import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import java.io.*;
import java.util.Vector;

import javax.microedition.io.*;

import com.cinterion.io.InPort;

import org.json.me.*;

public class AquaEHS6Firmware extends MIDlet {
	
	InPort inport = null;
	
	static String destHost = "216.98.8.227";
	static String destPort = "8081";
	static String connProfile = "bearer_type=gprs;access_point=data641003;username=hihi;password=hihi";
	
	public AquaEHS6Firmware() {
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		notifyDestroyed();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		  
		  CommConnection  		commConn;
		  
		  SocketConnection		sc = null;
		  InputStream			is = null;
		  OutputStream			os = null;
		  
		  DataInputStream     	inStream = null;
		  OutputStream    		outStream = null;
		  
		  JSONObject j, k;
		  
		  Vector flowin = new Vector();
		  flowin.addElement("GPIO12");
		  
		  
		  try {
			  
			  String openParm = "socket://" + destHost + ":" + destPort + ";" + connProfile;
			  
			  sc = (SocketConnection) Connector.open(openParm);
			  System.out.println("OpenParm: " + openParm);
			  
			  is = sc.openInputStream();
			  os = sc.openOutputStream();
			  
			  String outTxt = "hi";
			  System.out.println("Sending: " + outTxt);
			  os.write(outTxt.getBytes());
    		  
			  inport = new InPort(flowin);
	    	  String strCOM = "comm:COM1;baudrate=9600;bitsperchar=8;stopbits=1;parity=none;blocking=off;autocts=off;autorts=off";
	          commConn = (CommConnection)Connector.open(strCOM);
	          //commConn.setDSR(true);
	          inStream  = commConn.openDataInputStream();
	          outStream = commConn.openOutputStream();
	          
	          //j = new JSONObject("{reqtype:'auth'}");
	          //k = new JSONObject("{data : 'we have data!'}");
	          //j.put("OtherJson", k);
	          //System.out.println(j.toString(2));
	          
	          
	          
	          
	          while(true) {
	        	  System.out.println("Port val: " + inport.getValue());
	    		  outStream.write('W');
	    		  outStream.flush();
	    		  Thread.sleep(5000);
	    	  }
	          
		  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		  } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
