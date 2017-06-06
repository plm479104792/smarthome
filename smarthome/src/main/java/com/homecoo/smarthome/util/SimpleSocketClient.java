package com.homecoo.smarthome.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 应该服务器发送报文到netty 工具类
 * */
public class SimpleSocketClient {
	private String host;
	private int port;
	private boolean isconnect;
	public Socket socket;
	
	private int TIME_OUT = 3000;
	
	private static final String DEFAULT_HOST= "localhost";
	private static final int DEFAULT_PORT = 8089;
	
	public SimpleSocketClient(){
		this(DEFAULT_HOST,DEFAULT_PORT);
	}
	
	public SimpleSocketClient(String host){
		this(host,DEFAULT_PORT);
	}
	public SimpleSocketClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public boolean isConnect(){
		return socket != null && socket.isConnected();
	}
	
	public void tryConnect() throws UnknownHostException, IOException{
		if(!isConnect()){
			socket = new Socket(host, port);
			socket.setSoTimeout(TIME_OUT);
			isconnect = socket.isConnected();
		}
	}
	
	public void sendMessage(byte[] datas) throws UnknownHostException, IOException{
		if(!this.isconnect){
			tryConnect();
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.write(datas);
			//等待消息的返回
//			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//			//解析回来的报文
//			//头部肯定只有30个字节
//			ByteBuffer byteBuffer = ByteBuffer.allocate(30);
//			byteBuffer.putInt(inputStream.readInt());
//			byteBuffer.putInt(inputStream.readInt());
//			byteBuffer.putLong(inputStream.readLong());
//			byteBuffer.putLong(inputStream.readLong());
//			byteBuffer.putShort(inputStream.readShort());
//			byteBuffer.putShort(inputStream.readShort());
//			byteBuffer.putShort(inputStream.readShort());
//			
//			byteBuffer.position(4+4+8+8+2+2);
//			short dataLength = byteBuffer.getShort();
//			byte[] responBody = new byte[dataLength];
//			inputStream.read(responBody);
//			
//			String str= new String(responBody);
//			System.out.println(str);
			
		}
	}
	
	public void close(){
		if(socket != null){
			try{
				socket.close();
			}catch(IOException e){
				
			}
		}
	}
}
