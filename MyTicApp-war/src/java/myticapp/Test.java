/*
 *MyTicApp
 */
package myticapp;

import messageboard.resources.MyTicAppCon;

/**
 *
 * @author Brian
 * This class is the gateway between the front-end and the back-end.
 */
public class Test
{
    private static final int HOURS = 3;
    private static final int DAYS = 21;
    private final MyTicAppCon tic;
    
    public Test()
    {
      tic = new MyTicAppCon();  
    }
    
    public int TotalTime()
    {
        int time = (HOURS*DAYS);
        return time;
    }
    
    public boolean Message(int mId, String mMessage, String mUser)
    {
        tic.TicMessage(mId, mMessage, mUser);
        return true;
    }
    
    public void NewAcc(String cName,String eMail,String address,String phone,String mobile,String fax,String userName,String password)
    {
        tic.NewAcc(cName, eMail, address, phone, mobile, fax, userName, password);
    }
    
    public String FeedBack()
    {
        return tic.FeedBack();
    }
    
    public int Ticket()
    {
        return tic.Ticket();    
    }
    
    public String Tracker(int x)
    {
        return tic.Tracker(x);
    }

}
