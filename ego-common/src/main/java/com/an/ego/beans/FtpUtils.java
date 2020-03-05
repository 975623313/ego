package com.an.ego.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUtils {

	
	public static void main(String[] args) throws FileNotFoundException {
		
		/***
		 * 完成图片的上传，通过ftp将图片上传到图片服务器
		 * **/
		
		String hostname="192.168.142.6";
		int port=21;
		String username="ftpuser";
		String password="ftpuser";
		String pathname="/home/ftpuser/jd";
		String remote="qweqweeqwq.jpg";
		InputStream local=new FileInputStream("D:/pic/123.jpg");
		uploadFile(hostname, port, username, password, pathname, remote,local);
	}
    public static boolean uploadFile(String hostname,
                                     int port, String username,
                                     String password, String pathname,
                                     String remote,InputStream local) {

        boolean flag=false;

        try{
            //创建FtpClient对象
            FTPClient client=new FTPClient();
            //建立和ftp服务器的链接
            client.connect(hostname, port);
            //登陆ftp服务器
            client.login(username, password);
            //设置上传的文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //切换工作目录，文件上传后保存到那个目录
            if(!client.changeWorkingDirectory(pathname)){
                if(client.makeDirectory(pathname)){
                    client.changeWorkingDirectory(pathname);
                }
            }

//            local=new FileInputStream("D:/pic/1.jpg");
            //实现文件上传
            client.storeFile(remote, local);

            local.close();

            client.logout();
            client.disconnect();
            flag=true;

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return flag;


    }}
//    public static boolean uploadFile(String hostname,
//                                     int port, String username,
//                                     String password, String pathname,
//                                     String remote,InputStream local) {
//
//        boolean flag=false;
//
//        try{
//            //创建FtpClient对象
//            FTPClient client=new FTPClient();
//            //建立和ftp服务器的链接
//
//            client.connect("192.168.142.6", port);
//            //登陆ftp服务器
//            client.login(username, password);
//            //设置上传的文件的类型
//            client.setFileType(FTP.BINARY_FILE_TYPE);
//            client.enterLocalPassiveMode();
//            //切换工作目录，文件上传后保存到那个目录
//            String status = client.getReplyString();
//            System.out.println(status);
//            System.out.println("11111111111111111");
//            if(!client.changeWorkingDirectory(pathname)){
//                if(client.makeDirectory(pathname)){
//                    client.changeWorkingDirectory(pathname);
//                }
//            }
//
//            local=new FileInputStream("D:/pic/11.txt");
//            //实现文件上传
//            client.storeFile(remote, local);
//
//            local.close();
//
//            client.logout();
//            client.disconnect();
//            flag=true;
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//        return flag;
//
//
//    }
//}

//	public static boolean uploadFile(String hostname,
//			int port, String username,
//			String password, String pathname,
//			String remote,InputStream local) {
//
//		boolean flag=false;
//
//		try{
//			System.out.println(hostname+"--"+port+"--"+username+"--"+password+"--"+pathname+"--"+remote+"--"+local);
//			//创建FtpClient对象
//			FTPClient client=new FTPClient();
//			//建立和ftp服务器的链接
//
//			client.connect(hostname,port);
//			//登陆ftp服务器
//			client.login(username, password);
//			//设置上传的文件的类型
//			client.setFileType(FTP.BINARY_FILE_TYPE);
////			//切换工作目录，文件上传后保存到那个目录
//			if(!client.changeWorkingDirectory(pathname)){
//				System.out.println(pathname+"11111111111111");
//				if(client.makeDirectory(pathname)){
//					System.out.println(pathname+"22222222222222222222");
//					boolean flag2 =client.changeWorkingDirectory(pathname);
//					System.out.println("3333333333333333333333");
//				}
//			}
//
//			//local=new FileInputStream("D:/pic/1.jpg");
//			//实现文件上传
//			System.out.println(remote+"-----"+local);
//		client.storeFile(remote, local);
//
//			local.close();
//
//			client.logout();
//			client.disconnect();
//			flag=true;
//
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return flag;
//
//
//	}
//}
