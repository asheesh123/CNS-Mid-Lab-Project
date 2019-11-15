import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainProgram extends JFrame implements ActionListener {
    JButton b1,b2,b3;
    public MainProgram(){
        setLayout(null);
        b1=new JButton("REGISTER");
        b2=new JButton("LOGIN");
        b3=new JButton("CLOSE");
        b1.setBackground(Color.CYAN);
        b2.setBackground(Color.CYAN);
        b3.setBackground(Color.CYAN);
        setTitle("REGISTER / LOGIN");
        b1.setBounds(100,100,120,50);
        b2.setBounds(250,100,120,50);
        b3.setBounds(300,250,120,50);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470,400);
    }
    public static void main(String args[]){
        MainProgram mp=new MainProgram();
        mp.setBackground(Color.BLUE);
        mp.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==b1){
            RegisterPage ob=new RegisterPage();
        }
        if(actionEvent.getSource()==b2){
            LoginPage ob=new LoginPage();
        }
        if(actionEvent.getSource()==b3){
            System.exit(0);
        }
    }
}

