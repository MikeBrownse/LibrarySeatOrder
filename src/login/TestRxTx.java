package login;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

public class TestRxTx implements Runnable {
	public static String currentCard = null;
	public synchronized void run() {
		CommPortIdentifier port = getSerialPort("COM4");
		SerialPortClient client = new SerialPortClient(port);
		try {
			client.initAndOpen();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
 
	// �г��˿�
	public static Enumeration<CommPortIdentifier> listAllPort() {
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		return portList;
	}
 
	public static List<CommPortIdentifier> listAllPort(int portType) {
		List<CommPortIdentifier> ret = new ArrayList<>();
		Enumeration<CommPortIdentifier> all = listAllPort();
		if (all == null)
			return ret;
		while (all.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) all.nextElement();
			if (portId.getPortType() == portType) {
				ret.add(portId);
			}
		}
		return ret;
	}
 
	public static List<CommPortIdentifier> listAllSerialPort() {
		return listAllPort(CommPortIdentifier.PORT_SERIAL);
	}
 
	// ��ȡ����
	public static CommPortIdentifier getSerialPort(String portName) {
		List<CommPortIdentifier> list = listAllSerialPort();
		if (list == null)
			return null;
		for (CommPortIdentifier p : list) {
			if (p.getName().equalsIgnoreCase(portName)) {
				return p;
			}
		}
		return null;
	}
 
	// ���ڲ���
	public static class SerialPortClient implements SerialPortEventListener {
		public final CommPortIdentifier port;
		public SerialPort serialPort;
		public InputStream is;
		public OutputStream os;
 
		public SerialPortClient(CommPortIdentifier port) {
			this.port = port;
		}
 
		public void initAndOpen() throws IOException, UnsupportedCommOperationException,TooManyListenersException, PortInUseException {
			serialPort = (SerialPort) port.open("SerialPort-Test", 2000);
 
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			//������9600������λ8��ֹͣλ1������żУ��
			serialPort.setSerialPortParams(9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
 
			os = serialPort.getOutputStream();
			is = serialPort.getInputStream();
		}

		protected void onReceive(SerialPortEvent event) {
			char[] c = new char[10];
			int cnt = 0, num = 0, sum = 0;
			int newData = 0;
			boolean isread = false;
			do {
				try {
					newData = is.read();
					if(Character.isDigit((char)newData)) {
						c[cnt] = (char)newData;
						cnt++;
						isread = true;
					}else {
						break;
					}
				} catch (IOException e) {
					return;
				}
			} while (newData != -1);
			//ת��Ϊint����string��currentCard��Ϊint
			for(; cnt >= 0; cnt--) {
				if(Character.isDigit((char) c[cnt])) {
					sum += (c[cnt] - 48) * Math.pow(10, num);
					num++;
				}
			}
			if(isread) {
				currentCard = String.valueOf(sum);
				isread = false;
				cnt = 0;
				num = 0;
				sum = 0;
			}
		}
 
		public void serialEvent(SerialPortEvent event) {
			if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				onReceive(event);
			}
		}
	}
}