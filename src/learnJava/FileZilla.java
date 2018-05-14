package learnJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class FileZilla {
	public void getFile(String host, String uname, int port, String key, String src, String destn ) {
	JSch jsch = new JSch();
	Session session = null;
    try {
        session = jsch.getSession(uname, host, port);
        jsch.addIdentity("D:\\EverAuto\\barracuda_3rdparty_rsa.ppk");
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("");
        session.connect();
        
        Channel channel = session.openChannel("sftp");
        channel.connect();
       
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        
        // Get Files from Server
        sftpChannel.get(src, destn);
        
        sftpChannel.exit();
        session.disconnect();
    } catch (JSchException e1) {
        e1.printStackTrace();  
    } catch (SftpException e) {
        e.printStackTrace();
    }
	}
	
	public void putFile(String host, String uname, int port, String key, String src, String destn ) {
		JSch jsch = new JSch();
		Session session = null;
	    try {
	        session = jsch.getSession(uname, host, port);
	        jsch.addIdentity("D:\\EverAuto\\barracuda_3rdparty_rsa.ppk");
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword("");
	        session.connect();
	        
	        Channel channel = session.openChannel("sftp");
	        channel.connect();
	       
	        ChannelSftp sftpChannel = (ChannelSftp) channel;
	        
	        //Push files to Server
	        sftpChannel.put(src, destn);
	        
	        sftpChannel.exit();
	        session.disconnect();
	    } catch (JSchException e1) {
	        e1.printStackTrace();  
	    } catch (SftpException e) {
	        e.printStackTrace();
	    }
	}
	
	public String readFile(String host, String uname, int port, String key, String src) {
		JSch jsch = new JSch();
		String UEID = "";
		Session session = null;
	    try {
	        session = jsch.getSession(uname, host, port);
	        jsch.addIdentity("D:\\EverAuto\\barracuda_3rdparty_rsa.ppk");
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword("");
	        session.connect();
	        
	        Channel channel = session.openChannel("sftp");
	        channel.connect();
	       
	        ChannelSftp sftpChannel = (ChannelSftp) channel;
	        
	        //Read Logs Line by Line from Server - This Cannot read xml files
	        InputStream stream = sftpChannel.get(src);
	        
	            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
	            String line;
	            String lastline = "";
	            try {
					while ((line = br.readLine()) != null) {

						lastline = line;
						//System.out.println(line);
						}
					br.close();
					System.out.println(lastline);
					if (lastline.contains("-1")) {
						System.out.println("UEID is -1");
						UEID = "-1";
						return UEID;
					}
					else { 
						System.out.println("UEID is not -1");
						UEID = "1";
						return UEID;
					}
						
					    
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        sftpChannel.exit();
	        session.disconnect();
	    } catch (JSchException e1) {
	        e1.printStackTrace();  
	    } catch (SftpException e) {
	        e.printStackTrace();
	    }
		return UEID;
	}
}
