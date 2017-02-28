package org.lxz.tools.android.debug;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Lin on 16/4/18.
 */
public class MessageCache {
    /**关键字*/
    private static String currentKeyWord;
    /**type类型*/
    private static String currentType;
    /**level*/
    private static String currentLevel;

    private static int maxCount=100;

    private final static LinkedList<MessageBean> listMessages = new LinkedList<>();
    private static LinkedList<MessageBean> filterMessages = new LinkedList<>();

    public static synchronized void addMessage(MessageBean bean) {
        listMessages.add(bean);
        boolean isKeyWord=true;
        boolean isType=true;
        boolean isLevel=true;
        if(currentKeyWord!=null)
        {
            isKeyWord=currentKeyWord.equals(bean.tag);
        }
        if(currentType!=null)
        {
            isType=currentType.equals(bean.type);
        }
        if(currentLevel!=null)
        {
            isLevel=currentLevel.equals(bean.level);
        }
        if(isKeyWord&&isType&&isLevel)
        {
            filterMessages.add(bean);
        }
        if (listMessages.size() > maxCount) {
            listMessages.removeLast();
        }
        if (filterMessages.size() > maxCount) {
            filterMessages.removeLast();
        }


    }

    public static void setCurrentLevel(String str){
        currentLevel=str;
    }

    public static void setCurrentkeyWord(String str){
        currentKeyWord=str;
    }

    public static void setCurrentType(String str){
        currentType=str;
    }

    public static void clearCurrentLevel(){
        currentLevel=null;
    }
    public static void clearCurrentType(){
        currentType=null;
    }

    public static void clearCurrentkeyWord(){
        currentKeyWord=null;
    }

    public static void buildSearch(){
        MessageCache.filterMessages.clear();
        MessageCache.filterMessages=new LinkedList<MessageBean>(MessageCache.listMessages);
        if(currentKeyWord!=null)
        {
            for(Iterator<MessageBean> it=MessageCache.filterMessages.iterator();it.hasNext();){
                MessageBean bean=it.next();
                if(!currentKeyWord.equals(String.valueOf(bean.tag)))
                {
                    it.remove();
                }
            }
        }
        if(currentType!=null)
        {
            for(Iterator<MessageBean> it=MessageCache.filterMessages.iterator();it.hasNext();){
                MessageBean bean=it.next();
                if(!currentType.equals(String.valueOf(bean.type)))
                {

                    it.remove();
                }
            }
        }
        if(currentLevel!=null)
        {
            for(Iterator<MessageBean> it=MessageCache.filterMessages.iterator();it.hasNext();){
                MessageBean bean=it.next();
                if(!currentLevel.equals(String.valueOf(bean.level)))
                {

                    it.remove();
                }
            }
        }
    }
    public static LinkedList<MessageBean> getList(){
        if(currentKeyWord!=null||currentType!=null||currentLevel!=null)
        {
            return filterMessages;
        }
        return listMessages;
    }


    public static void clearAll(){
        listMessages.clear();
        filterMessages.clear();
    }
}
