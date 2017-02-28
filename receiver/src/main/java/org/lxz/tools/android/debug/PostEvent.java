package org.lxz.tools.android.debug;

import org.lxz.tools.android.debug.MessageBean;

public class PostEvent
{
  private static final String TAG = "PostEvent";
  public MessageBean msg;
  
  public PostEvent(MessageBean msg)
  {
    this.msg=msg;
  }
}
