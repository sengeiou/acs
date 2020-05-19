package android_serialport_api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SerialPortUtils {

    Activity activity;
    SerialPortListener serialPortListener;

    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;


    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
					/*System.out.println(Arrays.toString(buffer));
					for(int i=0;i<buffer.length;i++){
					System.out.println("=====================****"+buffer[i]);
					}
					
					String a=bytesToHexString(buffer);
					System.out.println("=============****"+size+"*****========"+a);
					*/
                    if (size > 0) {
                        onDataReceived(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }


    public SerialPortUtils() {
    }

    public void startSerialPort(Activity activity, String path, String code, SerialPortListener serialPortListener) {

        this.serialPortListener = serialPortListener;
        this.activity = activity;
        try {
            mSerialPort = getSerialPort(path, code);
            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();

            /* Create a receiving thread */
            mReadThread = new ReadThread();
            mReadThread.start();
        } catch (SecurityException e) {
            DisplayError("You do not have read/write permission to the serial port.");
        } catch (IOException e) {
            DisplayError("The serial port can not be opened for an unknown reason");
        } catch (InvalidParameterException e) {
            DisplayError("Please configure your serial port first.");
        }


    }

    private void DisplayError(String resourceId) {
        AlertDialog.Builder b = new AlertDialog.Builder(activity);
        b.setTitle("Error");
        b.setMessage(resourceId);
        b.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        b.show();
    }

    public SerialPort getSerialPort(String path, String code) throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort == null) {
            /* Read serial port parameters */
//			SharedPreferences sp = context.getSharedPreferences("android_serialport_api.sample_preferences", Context.MODE_PRIVATE);
//			String path = sp.getString("DEVICE", "");
//			int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));
            int baudrate = Integer.decode(code);
            /* Check parameters */
            if ((path.length() == 0) || (baudrate == -1)) {
                throw new InvalidParameterException();
            }


//			 String[] entries = mSerialPortFinder.getAllDevices();
//		     String[] entryValues = mSerialPortFinder.getAllDevicesPath();
//
//		     Log.i("test",Arrays.toString(entries)+"---Arrays-"+Arrays.toString(entryValues));

            /* Open the serial port */
            mSerialPort = new SerialPort(new File(path), baudrate, 0);
        }
        return mSerialPort;
    }


    protected void onDataReceived(final byte[] buffer, final int size) {
        try {
            activity.runOnUiThread(new Runnable() {
                public void run() {

                    if (serialPortListener != null) {
                        String s=bytesToHexString(buffer, 6, 9);
                        int number=Integer.parseInt(s, 16);
                        StringBuffer data = new StringBuffer(String.valueOf(number));
                        if (data.length() < 10) {
                            data.insert(0, "0");
                        }
                        serialPortListener.CallbackCode(data.toString());
                    }
                }
            });

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }


    /**
     * ����ɸѡ0~9
     */
    public String selectNum(String str) {

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public String bytesToHexString(byte[] src, int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = end; i >= start; i--) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
