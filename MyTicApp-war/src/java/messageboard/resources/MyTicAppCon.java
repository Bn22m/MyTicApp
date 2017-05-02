/*
 * MyTicApp
 */
package messageboard.resources;

/**
 *
 * @author Brian
 * This class is the backbone of the system.
 * It does all the hard work behind the scenes.
 */

import java.net.URI;
//import java.net.MalformedURLException;
import java.net.URISyntaxException;
//import java.net.URL;
import java.util.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MyTicAppCon 
{
    private static final String NAME = "postgres";
    private static final String PASSWORD = "";
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/myticapp";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String QUERY = "select * from tblmessage;";
    private static final String SQLMESSAGE  = "insert into tblmessage values (?,?,?,?,?,?,?,?,?);";
    private static final String SQLUSER = "insert into tbluser values (?,?,?,?,?,?,?,?);";
    private static final String SQLTICKET = "select ticket from tblvisitor;";
    private static final String SQLTICKETX = "insert into tblvisitor (ticket) values(?);";
    private static final String SQLTRACKER = "select date,message,processing,done,comment from tblmessage where id =?;";
    private PreparedStatement stmt;
    //private String userName;
    //private String passW;
    //private String company;
    //private String address;
    //private String phone;
    //private String mobile;
    //private String fax;
    //private String email;
    //
    private int id;
    private String date;
    private String message;
    private boolean processing;
    private boolean done;
    private String user;
    private String staff;
    private String comment;
    private String picture;
    
    //
    int option;
    private static final List<String> TAGLIST = new ArrayList<>();
    private final String LINESTR;
    private Connection connectDb;
    private String feedBack;//Hint "Field feedBack can be final" is incorrect!
    private static final String OK = "OK!";//OK Field is final, can be assigned to variable field feedBack.
    public MyTicAppCon()
    {
        connectDb = null;
        feedBack = OK;
        option = 3;
        LINESTR ="//\n#/////////////////////#\n// ";
    }
    
    public void NewAcc(String cName,String eMail,String address,String phone,String mobile,String fax,String userName,String nPassword)
    {
        boolean autoCommit = true;
        try
        {
            Class.forName(DRIVER);
            connectDb = DriverManager.getConnection(DATABASE,NAME,PASSWORD);
            try
            {
                autoCommit = connectDb.getAutoCommit ();
                connectDb.setAutoCommit(false);
                System.out.println("Connected to db."); 
                stmt = connectDb.prepareStatement (SQLUSER);
                stmt.setString(1,userName);
                stmt.setString(2,nPassword);
                stmt.setString(3,cName);
                stmt.setString(4,address);
                stmt.setString(5,phone);
                stmt.setString(6,mobile);
                stmt.setString(7,fax);
                stmt.setString(8,eMail);
                stmt.execute ();
                //
                stmt.close ();
                connectDb.commit();
                connectDb.setAutoCommit ( autoCommit );
                connectDb.close();
                System.out.println("//\n// db closed.");
            }
            catch(SQLException ex)
            {
                feedBack = ex.getMessage () ;
                System.out.println("//\n// db roll back.\n// " + feedBack );//Hint "Flip operands of the binary operator" is missleading!
                try
                {
                    connectDb.rollback ();
                    connectDb.setAutoCommit ( autoCommit );
                }
                catch(SQLException ex1)
                {
                    System.out.println("//\n// dbSQL error.\n// " + ex1.getMessage ());
                }    
            }
            
        }
        catch(ClassNotFoundException | SQLException e)
        {
            feedBack = e.getMessage () ;
            System.err.println("//\n// db error.\n// "+feedBack +LINESTR);
            // System.exit(0);
            System.out.println(LINESTR);
        }
        
    }

    public  void TicMessage(int mId,String mMessage, String mUser)
    {
        //Connection connectDb = null;
        boolean autoCommit = true;
        try
        {
            System.out.println(LINESTR);
            System.out.println("//\n// Database Connection\";");
            option = 4;
            Enumeration<Driver> d = DriverManager.getDrivers();
            System.out.println(d.getClass().getName());
            Class.forName(DRIVER);
            connectDb = DriverManager.getConnection(DATABASE,NAME,PASSWORD);
            try 
            {
                autoCommit = connectDb.getAutoCommit ();
                connectDb.setAutoCommit(false);
                System.out.println("Connected to db.");
                boolean insertM = Message(connectDb,mId,mMessage,mUser);
                System.out.println("// insert done : " + insertM);
                if(insertM)
                {    
                    stmt = connectDb.prepareStatement (QUERY);
                    ResultSet rs = stmt.executeQuery ();
                    while(rs.next())
                    {
                        id = rs.getInt("ID");
                        date = rs.getTimestamp("DATE").toString();
                        message = rs.getString("MESSAGE");
                        processing = rs.getBoolean("PROCESSING");
                        done = rs.getBoolean("DONE");
                        user = rs.getString("USER");
                        staff = rs.getString("STAFF");
                        comment = rs.getString("COMMENT");
                        picture = rs.getObject("PICTURE").toString();
                        if(option > 2)
                        {
                            TAGLIST.add(String.valueOf(id));
                            TAGLIST.add(date);
                            TAGLIST.add(message);
                            TAGLIST.add(String.valueOf(processing));
                            TAGLIST.add(String.valueOf(done));
                            TAGLIST.add(user);
                            TAGLIST.add(staff);
                            TAGLIST.add(comment);
                            TAGLIST.add(picture);
                         }
                    }
                }
                stmt.close ();
                connectDb.commit();
                connectDb.setAutoCommit ( autoCommit );
                connectDb.close();
                System.out.println("//\n// db closed.");
            }
            catch(SQLException ex)
            {
                feedBack = ex.getMessage () ;
                System.out.println("//\n// db roll back.\n// " + ex.getMessage ());
                try
                {
                    connectDb.rollback ();
                    connectDb.setAutoCommit ( autoCommit );
                    option = 0;
                }
                catch(SQLException ex1)
                {
                    System.out.println("//\n// dbSQL error.\n// " + ex1.getMessage ());
                    option = 1;
                }    
            }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            feedBack = e.getMessage () ;
            System.err.println("//\n// db error.\n// "+e.getMessage() +LINESTR);
            // System.exit(0);
            option = 2;
            System.out.println(LINESTR);
        }
        catch (NumberFormatException exc)
        {
            feedBack = exc.getMessage () ;
            System.err.println( "EXC SQL error : "+ exc.getMessage() );
        }
        if(option <= 2)
        {
            TAGLIST.add("mId : "+mId);
            TAGLIST.add(java.time.Instant.now().toString());
            TAGLIST.add(mMessage);
            TAGLIST.add("processing : "+option);
            TAGLIST.add("DONE");
            TAGLIST.add(mUser);
            //TAGLIST.add(staff);
            //TAGLIST.add(comment);
            //TAGLIST.add(picture);
        }
        System.out.println(LINESTR);
        System.out.println("//\n// connectDb.\n// " + connectDb +"\n// ");
        System.out.println(LINESTR + LocalDate.now ()+"\n// "+mUser);
        TAGLIST.forEach((String TAGLIST1) -> {
            System.out.println(TAGLIST1);
        });
        System.out.println(LINESTR);
    }
    
    public boolean Message(Connection connect, int mId, String mMessage, String mUser)
    {
        boolean mDone = false;
        try
        {
            URI pictureL = new URI("message.jsp?xpicture='img/site/"+mId+".png'");
            System.out.println(LINESTR);
            System.out.println("id :"+mId+" \nMessage: "+mMessage+" \nUser : "+mUser+"\n");
            stmt = connect.prepareStatement ( SQLMESSAGE );
            System.out.println("#"+mId+" : "+mUser);
            stmt.setInt(1,mId);
            stmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3,mMessage);
            stmt.setBoolean(4, true);
            stmt.setBoolean(5, false);
            stmt.setString(6,mUser);
            stmt.setString(7, "test");
            stmt.setString(8, "pending");
            stmt.setString(9,pictureL.toString());
            //stmt.setObject(9,"img/site");
            //stmt.setURL(9, pictureL);
            //stmt.setString(9,pictureL.getPath());
            stmt.execute();
            stmt.close();
            connect.commit();
            mDone = true;
        }
        catch (SQLException e)
        {
            feedBack = e.getMessage () ;
            System.err.println( "E SQL error : "+ e.getMessage() );
            //System.exit(0);
            return mDone ;
        }
        catch (URISyntaxException ex)
        {
            feedBack = ex.getMessage () ;
            System.err.println( "EX SQL error : "+ ex.getMessage() );
            //System.exit(0);
            return mDone ;
        }
        catch ( Exception exc )
        {
            feedBack = exc.getMessage () ;
            System.err.println( "EXC SQL error : "+ exc.getMessage() );
            //System.exit(0);
            return mDone ;
        }
        System.out.println(LINESTR);
        return mDone;
    }
    
    public String FeedBack()
    {
        return feedBack;
    }
    
    public int Ticket()
    {
        int x = 63;
        feedBack = OK;
        boolean autoCommit = true;
        try 
        {
            Class.forName(DRIVER);
            connectDb = DriverManager.getConnection(DATABASE,NAME,PASSWORD);
            System.out.println("\nConnected to : "+DATABASE);
            try
            {
                autoCommit = connectDb.getAutoCommit ();
                connectDb.setAutoCommit(false);//
                stmt = connectDb.prepareStatement (SQLTICKET);
                ResultSet rs = stmt.executeQuery ();
                while(rs.next())
                {
                   x = rs.getInt("ticket");
                }
                stmt.close();
                x++;
                TicketX(connectDb,x);
                connectDb.commit();
                connectDb.setAutoCommit ( autoCommit );
                connectDb.close();
                System.out.println("\nDB Closed : "+x);
                return x;
            }
            catch(SQLException ex)
            {
                System.out.println("//\n// db roll back.\n// " + ex.getMessage ());
                try
                {
                    connectDb.rollback ();
                    connectDb.setAutoCommit ( autoCommit );
                }
                catch(SQLException ex1)
                {
                    System.out.println("//\n// dbSQL error.\n// " + ex1.getMessage ());
                }    
            }
            
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            feedBack = ex.getMessage () ;
            System.err.println( "SQL error : "+ ex.getMessage() );
            //Logger.getLogger(MyTicAppCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    public void TicketX(Connection conn,int x)
    {
        feedBack = OK;
        boolean autoCommit = true;
        System.out.println("\nConnected to : "+conn);
        try
        {
            autoCommit = conn.getAutoCommit ();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement (SQLTICKETX);
            stmt.setInt(1, x);
            stmt.execute();
            stmt.close();
            conn.commit();
            conn.setAutoCommit ( autoCommit );
            //conn.close();
            System.out.println("\nDB stmt Closed : "+x);
        }
        catch(SQLException ex)
        {
            System.out.println("//\n// db roll back.\n// " + ex.getMessage ());
            try
            {
                connectDb.rollback ();
                connectDb.setAutoCommit ( autoCommit );
            }
            catch(SQLException ex1)
            {
                System.out.println("//\n// dbSQL error.\n// " + ex1.getMessage ());    
            }
        }
    }
    
    public String Tracker(int idT)
    {
        boolean autoCommit = true;
        String track = LINESTR;
        try
        {
            System.out.println(LINESTR);
            System.out.println("//\n// Database Connection\";");
            option = 4;
            Enumeration<Driver> d = DriverManager.getDrivers();
            System.out.println(d.getClass().getName());
            Class.forName(DRIVER);
            connectDb = DriverManager.getConnection(DATABASE,NAME,PASSWORD);
            try 
            {
                autoCommit = connectDb.getAutoCommit ();
                connectDb.setAutoCommit(false);
                System.out.println("Connected to db.");
                stmt = connectDb.prepareStatement (SQLTRACKER);
                stmt.setInt(1, idT);
                ResultSet rs = stmt.executeQuery ();
                while(rs.next())
                {
                    //id = rs.getString("ID");
                    date = rs.getDate("DATE").toString();
                    message = rs.getString("MESSAGE");
                    processing = rs.getBoolean("PROCESSING");
                    done = rs.getBoolean("DONE");
                    //user = rs.getString("USER");
                    //staff = rs.getString("STAFF");
                    comment = rs.getString("COMMENT");
                    //picture = rs.getURL("PICTURE").getPath();
                    if(option > 2)
                    {
                        track = "<br>Date: "+date+"\n<br>Message: "+message+"\n<br>Processing: "+processing+
                                "\n<br>Done: "+done+"\n<br>Comment: "+comment+"\n<br>";
                        //TAGLIST.add(id);
                        //TAGLIST.add(date);
                        //TAGLIST.add(message);
                        //TAGLIST.add("processing");
                        //TAGLIST.add("DONE");
                        //TAGLIST.add(user);
                        //TAGLIST.add(staff);
                        //TAGLIST.add(comment);
                        //TAGLIST.add(picture);
                    }
                }
                stmt.close ();
                connectDb.commit();
                connectDb.setAutoCommit ( autoCommit );
                connectDb.close();
                System.out.println("//\n// db closed.");
            }
            catch(SQLException ex)
            {
                feedBack = ex.getMessage () ;
                System.out.println("//\n// db roll back.\n// " + ex.getMessage ());
                try
                {
                    connectDb.rollback ();
                    connectDb.setAutoCommit ( autoCommit );
                    option = 0;
                }
                catch(SQLException ex1)
                {
                    System.out.println("//\n// dbSQL error.\n// " + ex1.getMessage ());
                    option = 1;
                }    
            }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            feedBack = e.getMessage () ;
            System.err.println("//\n// db error.\n// "+e.getMessage() +LINESTR);
            // System.exit(0);
            option = 2;
            System.out.println(LINESTR);
        }
        
        System.out.println(LINESTR);
        System.out.println("//\n// connectDb.\n// " + connectDb +"\n// ");
        System.out.println(LINESTR + LocalDate.now ()+"\n// "+idT);
        TAGLIST.forEach((TAGLIST1) -> {
            System.out.println(TAGLIST1);
        });
        System.out.println(LINESTR);
        
        return track;
    }
}