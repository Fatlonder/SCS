/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISystem;

import gnu.io.*;
import java.io.*;

public class ListPortClass {

    public static void main(String[] s) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Port in use!");
            } else {
                System.out.println(portIdentifier.getName());

                SerialPort serialPort = (SerialPort) portIdentifier.open("ListPortClass", 300);
                int b = serialPort.getBaudRate();
                System.out.println(Integer.toString(b));
                serialPort.setSerialPortParams(300, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                OutputStream mOutputToPort = serialPort.getOutputStream();
                InputStream mInputFromPort = serialPort.getInputStream();
                String mValue = "AT\r";
                System.out.println("beginning to Write . \r\n");
                mOutputToPort.write(mValue.getBytes());
                System.out.println("AT Command Written to Port. \r\n");
                mOutputToPort.flush();
                System.out.println("Waiting for Reply \r\n");
                Thread.sleep(500);
                byte mBytesIn[] = new byte[20];
                mInputFromPort.read(mBytesIn);
                mInputFromPort.read(mBytesIn);
                String value = new String(mBytesIn);
                System.out.println("Response from Serial Device: " + value);
                mOutputToPort.close();
                mInputFromPort.close();
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
     

    }

}

