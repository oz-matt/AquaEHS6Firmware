import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.Vector;

import javax.microedition.io.*;

import com.cinterion.io.InPort;
import com.cinterion.io.OutPort;

public class AquaEHS6Firmware extends MIDlet {

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
		  
		  try {
	    	  String strCOM = "comm:COM1;baudrate=9600;bitsperchar=8;stopbits=1;parity=none;blocking=off;autocts=off;autorts=off";
	          commConn = (CommConnection)Connector.open(strCOM);
	          //commConn.setDSR(true);
	          inStream  = commConn.openDataInputStream();
	          outStream = commConn.openOutputStream();
	          
	          while(true) {
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
		}
	}

}
