import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends Canvas implements ActionListener {
    static int count;
    String uname;
    int x[] = new int[2];
    int y[] = new int[2];
    String pass[] = new String[2];
    String iname;
    int index = 0;
    JFrame f;
    JLabel l;
    Image i;
    JPanel jp;
    JButton bfinish;
    public Login(){}
    public Login(User u) {
        Font font = new Font("Serif", Font.BOLD, 15);
        iname=u.iname;
        Toolkit t = Toolkit.getDefaultToolkit();
        i = t.getImage(iname);
        count = 0;
        f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setTitle("LOGIN FORM");
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
        uname=u.username;

        jp.addMouseListener(new MouseAdapter() {
            private Color background;
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String password = getPass();
                x[index] = e.getX();
                y[index] = e.getY();
                pass[index++] = password;
                count++;
                int vc=validateClick(u,e.getX(),e.getY());
                if(vc!=-1){
                    if(getStoredPassword(u,vc).equals(password)){
                        if (count==2) {
                            JOptionPane.showMessageDialog(null, "login successful");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "go for next");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "try again");
                        index-=1;
                        count-=1;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "click on one of the valid location on image");
                    index-=1;
                    count-=1;
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
    int validateClick(User u,int x,int y){
        Rectangle r=new Rectangle(x-4,y-4,8,8);
        if(r.contains(u.x1,u.y1)){
            return 1;
        }
        else if(r.contains(u.x2,u.y2)){
            return 2;
        }
        else if(r.contains(u.x3,u.y3)){
            return 3;
        }
        else if(r.contains(u.x4,u.y4)){
            return 4;
        }
        else if(r.contains(u.x5,u.y5)){
            return 5;
        }
        return -1;
    }

    String getStoredPassword(User u,int click){
        if(click==1)
            return u.p1;
        else if(click==2)
            return u.p2;
        else if(click==3)
            return u.p3;
        else if(click==4)
            return u.p4;
        else if(click==5)
            return u.p5;
        return "";
    }
    boolean validatePassword(User u,int click,String pass){
        String ori_pass=getStoredPassword(u,click);
        if(pass==ori_pass)
            return true;
        return false;
    }
    public String getPass() {

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        pass.setFocusable(true);
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

    public static void main(String[] args) {
        Login m = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==bfinish){
            System.exit(0);
        }
    }
}