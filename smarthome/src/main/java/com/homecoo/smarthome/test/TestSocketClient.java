package com.homecoo.smarthome.test;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import com.homecoo.smarthome.util.SimpleSocketClient;


public class TestSocketClient {

	/**
	 * @param args
	 * @Desciption:测试smarthome发送报文到netty
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleSocketClient simpleSocketClient = new SimpleSocketClient();
		//这么写是为了让你更清楚报文的格式
		ByteBuffer byteBuffer = ByteBuffer.allocate(4+4+8+8+2+2+2+2);
		byteBuffer.putInt(1111);
		byteBuffer.putInt(0);
		byteBuffer.putLong(123456);
		byteBuffer.putLong(8);
		byteBuffer.putShort((short)2);
		byteBuffer.putShort((short)1011);
		byteBuffer.putShort((short)2);
		byteBuffer.put(new byte[]{125,-100});
		try {
			simpleSocketClient.sendMessage(byteBuffer.array());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	

}
