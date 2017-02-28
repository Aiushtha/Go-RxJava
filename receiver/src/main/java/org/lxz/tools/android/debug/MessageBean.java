package org.lxz.tools.android.debug;

import java.io.Serializable;

/**
 * Created by Lin on 16/4/14.
 */
public class MessageBean implements Serializable {
    public final static String TYPE_XML="xml";
    public final static String TYPE_JSON="json";
    public final static String TYPE_URL="url";
    public final static String TYPE_HTTP="http";
    public final static String TYPE_TOAST="toast";
    public final static String TYPE_PUSH="push";
    public final static String TYPE_MSG="msg";

    public final static String[] TYPES={
            TYPE_XML,TYPE_JSON,TYPE_URL,TYPE_HTTP,
            TYPE_TOAST,TYPE_PUSH,TYPE_MSG
    };

    public final static String LEVEL_VERBOSE="Verbose";
    public final static String LEVEL_DEBUG="Debug";
    public final static String LEVEL_INFO="Info";
    public final static String LEVEL_WARN="Warn";
    public final static String LEVEL_ERROR="Error";
    public final static String LEVEL_ASSERT="Assert";

    public final static String[] LEVELS={
            LEVEL_VERBOSE,LEVEL_DEBUG,LEVEL_INFO,LEVEL_WARN,
            LEVEL_ERROR,LEVEL_ASSERT
    };

    public static String applicationIdVar;
    public static long index=0;

    public String applicationId=applicationIdVar;
    public long createTime=System.currentTimeMillis();
    public String id=String.valueOf(index++);
    public String level=LEVEL_INFO;
    public String type=LEVEL_ASSERT;
    public String src="";
    public String subject="";
    public String content="";
    public String url="";
    public String tag="";


    public String getId() {
        return id;
    }

    public MessageBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public MessageBean setSrc(String src) {
        this.src = src;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MessageBean setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getType() {
        return type;
    }

    public MessageBean setType(String type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageBean setContent(String content) {
        this.content = content;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public MessageBean setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }


    public String getLevel() {
        return level;
    }

    public MessageBean setLevel(String level) {
        this.level = level;
        return this;
    }

    public long getCreateTime() {
        return createTime;
    }

    public MessageBean setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public MessageBean setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MessageBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
