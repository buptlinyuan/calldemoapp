package com.peak.calldemoapp.util.webutil;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ly on 9/21/2015.
 */
public class ChatManagerListener implements org.jivesoftware.smack.ChatManagerListener{

    public void chatCreated(Chat chat,boolean arg1){

        chat.addMessageListener(new MessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {

                String msg_string=(message!=null?message.getBody():"NUll");

                //接收消息解析
                try{
                    JSONObject msgJson = new JSONObject(msg_string);
                    String MSGTYPE= msgJson.getString("MSGTYPE");
                    if(MSGTYPE.equals("REQ")){
                        System.out.println("Received message and we can read it:"+msg_string);
                    }

                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.println("Received message:"+msg_string);
            }
        });
    }
}
