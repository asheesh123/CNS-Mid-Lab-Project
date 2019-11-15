import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Register extends Canvas implements ActionListener {
    static int count;
    String uname;
    int x[] = new int[5];
    int y[] = new int[5];
    String pass[] = new String[5];
    String iname;
    int index = 0;
    Connection c=null;
    JFrame f;
    Image i;
    JPanel jp;
    JButton bfinish;
    public Register (){}
    public Register(String username) {
        Font font = new Font("Serif", Font.BOLD, 15);
        iname="Favorites/im9.jpg";
        Toolkit t = Toolkit.getDefaultToolkit();
        i = t.getImage(iname);
        count = 0;
        c=getConnection();
        f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setTitle("REGISTRATION FORM");
        bfinish = new JButton("FINISH");
        bfinish.setBounds(1400,755,100,40);
        bfinish.setBackground(Color.cyan);
        bfinish.addActionListener(this);
        bfinish.setFont(font);
        jp=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(i,  0, 0,null);
            }
        };
        jp.addMouseListener(new MouseAdapter() {
            private Color background;
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String password = getPass();
                x[index] = e.getX();
                y[index] = e.getY();
                pass[index++] = password;
                count++;
                if (count == 5) {
                    uname=username;
                    for (int i = 0; i < 5; i++) {
                        System.out.println(x[i] + " " + y[i] + " " + pass[i]);
                    }
                    if(insert(c)) {
                        index=0;
                        count=1;
                        JOptionPane.showMessageDialog(null, "registration successful");

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"please try again");
                        index=0;
                    }
                }
            }
        });
        jp.setBounds(600, 200, 720, 600);
        jp.setLayout(null);
        jp.setBackground(Color.CYAN);
        f.add(bfinish);
        f.add(jp);

        f.add(this);
        this.setBackground(Color.yellow);
        f.setVisible(true);
    }
    public boolean insert(Connection con){
        try{
            Statement stmt=con.createStatement();
            String s="INSERT INTO `users`(`username`, `x1`, `y1`, `p1`, `x2`, `y2`, `p2`, `x3`, `y3`, `p3`, `x4`, `y4`, `p4`, `x5`, `y5`, `p5`, `iname`) VALUES ('"+uname+"','"+x[0]+"','"+y[0]+"','"+pass[0]+"','"+x[1]+"','"+y[1]+"','"+pass[1]+"','"+x[2]+"','"+y[2]+"','"+pass[2]+"','"+x[3]+"','"+y[3]+"','"+pass[3]+"','"+x[4]+"','"+y[4]+"','"+pass[4]+"','"+iname+"')";
            stmt.executeUpdate(s);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
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
    public String getPass() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, panel, "Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (option == 0) {
            char[] password = pass.getPassword();
            return new String(password);
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==bfinish){
            System.exit(0);
        }
    }
}