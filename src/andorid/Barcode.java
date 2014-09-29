package com.equipnet.barcode;


import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject; 
//importazioni da Cordova 
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;

//importazioni da Android
import android.content.Context;
import android.hardware.barcode.Scanner;
import android.telephony.TelephonyManager;
import org.apache.cordova.CordovaPlugin;

import com.equipnet.basestorage.Basestorage;
import com.equipnet.storage.Storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import android.util.Log; 

public class Barcode extends CordovaPlugin { 

	public static final String READ = "read"; 
	public static final String OPENDB = "dbopen";
	public static final String ME = "logging"; 
	Context context = this.cordova.getActivity().getApplicationContext();
	Storage storage = new Basestorage(context, "udmDB.db", null, 1);


	public void initialize(CordovaInterface cordova, CordovaWebView webview) {
		super.initialize(cordova, webView);
		//Il plugin non ha accesso diretto al contesto dell'applicazione
		//e quindi dobbiamo arrivarci
		Scanner.InitSCA();
	}


	public boolean execute(String action, JSONArray data, CallbackContext callbackContext)
			throws JSONException{
		Log.d(ME, "execute " + action); 
		if (action.equalsIgnoreCase(READ)) {  
			callbackContext.success(Scanner.ReadSCAAuto());
			String acquired = Scanner.ReadSCAAuto();
			storage.acquire(acquired);
			Log.v(ME, "Barcode " + action);
			return true;
			//		}  else if (action.equalsIgnoreCase(OPENDB)) { 
			//			
			//			callbackContext.success(storage.read());	
			//			return true;
		}
		return false; 
	} 


	//         public  void acquire(String acquired) throws IOException { 
	//        	//creazione del file e storage
	//        	String FILENAME = "udmdata.txt";
	//        	FileOutputStream fos = this.cordova.getActivity().getApplicationContext().openFileOutput(FILENAME, Context.MODE_APPEND);
	//        	fos.write(acquired.getBytes());
	//            Log.v(ME, "scritturafile" + acquired.getBytes() );
	//        	fos.close();
	//        	
	//        } 
	//         
	//         public void read(){
	//             try{
	//                FileInputStream fin = this.cordova.getActivity().getApplicationContext().openFileInput("udmdata.txt");
	//                int c;
	//                String temp="";
	//                while( (c = fin.read()) != -1){
	//                   temp = temp + Character.toString((char)c);
	//                }
	//                Log.v(ME, "letturaFile" + temp );
	//                //string temp contiene tutti i dati del file.
	//                fin.close();
	//                
	//             }catch(Exception e){
	//
	//             }
	//          }


}
