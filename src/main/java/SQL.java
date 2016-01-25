import java.io.IOException;
import java.util.Properties;
import java.sql.*;
/**
 * Created by chroma on 16/01/19.
 */
public class SQL {
    private String url, username, password;
    public Properties properties;
    public SQL() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/sql.properties"));
            url = properties.getProperty("sql.url");
            username = properties.getProperty("sql.user");
            password = properties.getProperty("sql.pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection open() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return DriverManager.getConnection(url, username, password);
    }
    public boolean addGreetMessage(String Channelname,String message) {
        Connection con = null;
        try{
            con = open();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO GreetBot (ChannelName, Message)  VALUES (?,?)"
            );
            ps.setString(1,Channelname);
            ps.setString(2,message);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con);
        }
        return true;
    }
    public boolean setGreetMessage(String ChannelName,String Message) {
        Connection con = null;
        try {
            con = open();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE GreetBot SET Message = ? WHERE ChannelName = ?"
            );
            ps.setString(1,Message);
            ps.setString(2,ChannelName);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con);
        }
        return true;
    }
    public String getGreetMessage(String channelName) {
        Connection connection = null;
        String Greet = null;
        try {
            connection = open();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT Message FROM GreetBot WHERE ChannelName = ?"
            );
            ps.setString(1, channelName);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                Greet = rs.getString("msg");
        } catch (SQLException e) {
            e.printStackTrace();
            Greet = "Unknown";
        } finally {
            close(connection);
        }
        return Greet;
    }
}
