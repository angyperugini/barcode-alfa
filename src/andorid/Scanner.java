package android.hardware.barcode;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Scanner {
	static public final int BARCODE_READ = 10;
	static public final int BARCODE_NOREAD = 12;
	static Boolean m_bASYC = false;
	static int m_nCommand = 0;
	static public Handler m_handler = null;
	
	//
	static public native String ReadSCAAuto();
	
	//
	static public native String ReadSCA(int nCode);
	
	//
	static public native String ReadSCAEx(int nCommand, int nCode);
	
	//
	static public native int ReadDataSCA(int nCommand, byte[] buf);
	
	//
	static public native byte[] ReadData(int nCommand);
	
	//
	static public native int InitSCA();
	
	//
	static public void Read() {
		if (m_bASYC) {
			return;
		} else {
			// m_nCommand=nCode;
			StartASYC();
		}
	}
	
	//
	static void StartASYC() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {

				if (m_handler != null) {

					String str = ReadSCAAuto();
					Message msg = new Message();
					msg.what = str.length() > 0 ? BARCODE_READ : BARCODE_NOREAD;
					msg.obj = str;

					m_handler.sendMessage(msg);

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}
	
	//
	static public void SendString(String str) {
		try {
			Runtime.getRuntime().exec("input keyevent 66");
			Runtime.getRuntime().exec("input text " + str);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("run", e.toString());
		}
	}

	static private void showToast(String str) {
		Toast.makeText(null, str, Toast.LENGTH_SHORT).show();
	}

	static {
		// System.loadLibrary("Cilico-Scan");
		System.loadLibrary("tiny-tools");
	}

}
