import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import java.io.*;
import java.util.Vector;

import javax.microedition.io.*;

import com.cinterion.io.InPort;

import org.json.me.*;

public class AquaEHS6Firmware extends MIDlet {
	InPort inport = null;
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
		CommConnection  commConn;
		  DataInputStream     inStream;
		  OutputStream    outStream;
		  JSONObject j, k;
		  
		  Vector flowin = new Vector();
		  flowin.addElement("GPIO12");
		  
		  
		  try {
			  inport = new InPort(flowin);
	    	  String strCOM = "comm:COM1;baudrate=9600;bitsperchar=8;stopbits=1;parity=none;blocking=off;autocts=off;autorts=off";
	          commConn = (CommConnection)Connector.open(strCOM);
	          //commConn.setDSR(true);
	          inStream  = commConn.openDataInputStream();
	          outStream = commConn.openOutputStream();
	          j = new JSONObject("{reqtype:'auth'}");
	          k = new JSONObject("{data : 'we have data!'}");
	          j.put("OtherJson", k);
	          System.out.println(j.toString(2));
	          while(true) {
	        	  System.out.println("Port val: " + inport.getValue());
	    		  outStream.write('W');
	    		  outStream.flush();
	    		  Thread.sleep(1000);
	    	  }
	          
		  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		  } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
