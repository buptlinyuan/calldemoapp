package com.peak.calldemoapp.util.webutil;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by ly on 10/12/2015.
 */
public class WebUtil {

    private static XMPPConnection connection = null;
    private static String SERVER="123.57.22.77";

    private String HOST="localhost.localdomain";



    public static XMPPConnection getConnection() {
        if (connection == null) {
            conServer(new WebCallbackListener() {
                @Override
                public void onFinish(String response) {


                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
        return connection;
    }



    /**
     * 连接服务器
     */
    public static void conServer(final WebCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ConnectionConfiguration connConfig = new ConnectionConfiguration(SERVER,
                            5222);
                    connection = new XMPPConnection(connConfig);
                    connection.connect();
                    if(listener!=null){
                        //回调onFinish()方法
                        listener.onFinish("CONNECT_SUCCESS");
                    }
                } catch (XMPPException xe) {
                    xe.printStackTrace();
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(xe);
                    }
                }
            }
        }).start();


    }

    /**
     * 与服务器断开连接
     */
    public static void disconnectServer(final WebCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if (connection!=null){
                        connection.disconnect();
                        connection=null;
                    }
                    if(listener!=null){
                        //回调onFinish()方法
                        listener.onFinish("CONNECT_SUCCESS");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }
            }
        }).start();


    }

    /**
     * 登录
     */
    public static void login(final String user,final String password,final WebCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if(connection!=null){
                        connection.login(user, password);
                        Presence presence=new Presence(Presence.Type.available);
                        connection.sendPacket(presence);
                    }
                    if(listener!=null){
                        //回调onFinish()方法
                        listener.onFinish("LOGIN_SUCCESS");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    /**
     * 发送信息
     */
    public static void sendMSG(final String target,final String message,final WebCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if(connection!=null){
                        //获取聊天窗口管理器
                        ChatManager cm=connection.getChatManager();

                        //获取与好友target的聊天窗口
                        final Chat newchat=cm.createChat(target+"@"+XmppTool.getConnection().getServiceName(),null);
                        //发送消息
                        newchat.sendMessage(message);
                    }
                    if(listener!=null){
                        //回调onFinish()方法
                        listener.onFinish("SEND_SUCCESS");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    /**
     * 用户添加监听消息
     */
    public static void listenMSG(final WebCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if(connection!=null){
                        //添加发给该用户的监听
                        ChatManagerListener chatManagerListener=new ChatManagerListener();
                        connection.getChatManager().addChatListener( chatManagerListener);
                    }
                    if(listener!=null){
                        //回调onFinish()方法
                        listener.onFinish("SEND_SUCCESS");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(listener!=null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

}
