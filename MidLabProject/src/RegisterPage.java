import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegisterPage extends Canvas implements ActionListener {
    String uname;
    Connection c=null;
    JTextField tf;
    JFrame f;
    JLabel l;
    JButton bfinish;
    public RegisterPage() {
        Font font = new Font("Serif", Font.BOLD, 15);
        c=getConnection();
        f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setTitle("REGISTRATION FORM");
        l = new JLabel("Username : ");
        l.setBounds(720, 50, 100, 40);
        l.setBackground(null);
        l.setFont(font);
        tf=new JTextField();
        tf.setFont(font);
        tf.addActionListener(this);
        tf.setBounds(850, 50, 200, 40);
        bfinish = new JButton("FINISH");
        bfinish.setBounds(1400,755,100,40);
        bfinish.setBackground(Color.cyan);
        bfinish.addActionListener(this);
        bfinish.setFont(font);
        f.add(tf);f.add(l);f.add(bfinish);

        f.add(this);
        this.setBackground(Color.yellow);
        f.setVisible(true);
    }

    User isRegistered(String uname){
        try{
            Statement stmt=c.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where username = '"+uname+"'");
            if(rs.next()) {
                User u=new User();
                u.username=rs.getString(1);
                u.x1=rs.getInt(2);u.y1=rs.getInt(3);u.p1=rs.getString(4);
                u.x2=rs.getInt(5);u.y2=rs.getInt(6);u.p2=rs.getString(7);
                u.x3=rs.getInt(8);u.y3=rs.getInt(9);u.p3=rs.getString(10);
                u.x4=rs.getInt(11);u.y4=rs.getInt(12);u.p4=rs.getString(13);
                u.x5=rs.getInt(14);u.y5=rs.getInt(15);u.p5=rs.getString(16);
                u.iname=rs.getString(17);
                return u;
            }
        }
        catch(Exception e){}
        return null;
    }
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==bfinish){
            System.exit(0);
        }
        if(actionEvent.getSource()==tf){
            User u=isRegistered(tf.getText().trim());
            if(u==null){
                Register ob=new Register(tf.getText().trim());
            }
            else{
                JOptionPane.showMessageDialog(null,"username already exists, please choose another one");
            }
        }
    }
}