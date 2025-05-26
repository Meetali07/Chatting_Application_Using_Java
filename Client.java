import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; // Importing JFrame for GUI
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.awt.*; // Importing Color for GUI background color
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Client extends JFrame implements ActionListener{
    JTextField text; // Declare a JTextField for user input globally
    static JPanel a1; // Declare a JPanel for the main content area globally
    static Box vertical=Box.createVerticalBox(); // Create a vertical box layout for the main content area for text messages
   
    static JFrame f=new JFrame(); // Create a new JFrame for the main window
    static DataOutputStream dout; // Declare a DataOutputStream for sending messages globally
    // Constructor for the Server class

    Client() {                                      // Constructor logic added here
        f.setLayout(null); 
        JPanel p1= new JPanel(); // Create a new JPanel
        p1.setBackground(new Color(218,112,214)); // Set the background color of the panel
        p1.setBounds(0, 0, 450, 70); // Set the position and size of the panel
        p1.setLayout(null); // Set the layout manager to null for absolute positioning
        f.add(p1); // Adding the panel to the frame

        ImageIcon i1 = new ImageIcon("Icons/3.png"); // Load the server icon
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Scale the icon to fit the label
        ImageIcon i3=new ImageIcon(i2); // Create a new ImageIcon with the scaled image
        JLabel back=new JLabel(i3); // Create a JLabel with the icon
        back.setBounds(5, 20, 25, 25); // Set the position and size of the label
        p1.add(back); // Add the label to the frame p1 is the panel on which imageto be added 
        
        back.addMouseListener(new MouseAdapter() { //MouseAdapter is a helper class that lets you override only the mouse event methods you care about.  
            public void mouseClicked(MouseEvent ae) { // Mouse click event
                System.exit(0); // Exit the application when the icon is clicked
            }

        });

        ImageIcon i4 = new ImageIcon("Icons/2.jpg"); // Load the server icon
        Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale the icon to fit the label
        ImageIcon i6=new ImageIcon(i5); // Create a new ImageIcon with the scaled image
        JLabel profile=new JLabel(i6); // Create a JLabel with the icon
        profile.setBounds(40, 10, 50, 50); // Set the position and size of the label
        p1.add(profile); // Add the label to the frame p1 is the panel on which imageto be added 
        
        ImageIcon i7 = new ImageIcon("Icons/video.png"); // Load the server icon
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the icon to fit the label
        ImageIcon i9=new ImageIcon(i8); // Create a new ImageIcon with the scaled image
        JLabel video=new JLabel(i9); // Create a JLabel with the icon
        video.setBounds(300, 20, 30, 30); // Set the position and size of the label
        p1.add(video); // Add the label to the frame p1 is the panel on which imageto be added 

        ImageIcon i10 = new ImageIcon("Icons/phone.png"); // Load the server icon
        Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH); // Scale the icon to fit the label
        ImageIcon i12=new ImageIcon(i11); // Create a new ImageIcon with the scaled image
        JLabel phone=new JLabel(i12); // Create a JLabel with the icon
        phone.setBounds(360, 20, 35, 30); // Set the position and size of the label
        p1.add(phone); // Add the label to the frame p1 is the panel on which imageto be added 

        ImageIcon i13 = new ImageIcon("Icons/3icon.png"); // Load the server icon
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_SMOOTH); // Scale the icon to fit the label
        ImageIcon i15=new ImageIcon(i14); // Create a new ImageIcon with the scaled image
        JLabel more=new JLabel(i15); // Create a JLabel with the icon
        more.setBounds(420, 20, 10, 25); // Set the position and size of the label
        p1.add(more); // Add the label to the frame p1 is the panel on which imageto be added 

        JLabel name=new JLabel("Customer Name");
        name.setBounds(110, 15, 100, 18); // Set the position and size of the label
        name.setForeground(Color.white);   // Set the text color to white
        name.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Set the font of the label    
        p1.add(name); // Add the label to the frame p1 is the panel on which imageto be added
        
        JLabel Status=new JLabel("Online");
        Status.setBounds(110, 37, 100, 15); // Set the position and size of the label
        Status.setForeground(Color.white);   // Set the text color to white
        Status.setFont(new Font("Times New Roman", Font.PLAIN, 14)); // Set the font of the label    
        p1.add(Status); // Add the label to the frame p1 is the panel on which imageto be added

        a1= new JPanel(); // Create a new JPanel
        a1.setBounds(5,75,440,475);
        f.add(a1);
        
        text=new JTextField(); // Create a new JTextField
        text.setBounds(5,555,310,40); // Set the position and size of the text field 
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16)); // Set the font of the text field       
        f.add(text);

        JButton send=new JButton("Send");
        send.setBounds(320,555,123,40);
        send.setBackground(new Color(7,94,84)); // Set the background color of the button
        send.setForeground(Color.WHITE); // Set the text color of the button
        send.addActionListener(this); //add action to button in the Actionperformed method
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);
        


        f.setSize(450, 600);          // Set the size of the window
                           
        f.setLocation(800,50);                    // Set the location of the window coordinates x and y axis 
        f.setUndecorated(true); // Remove the title bar and borders
        f.getContentPane().setBackground(new Color(135,31,120));     // Set the background color to black

        f.setVisible(true);   
    }

    public void actionPerformed(ActionEvent ae) { //you are overriding the method from the ActionListener interface. This means you are providing your own code that will run whenever an action event (like a button click) occurs and this listener is registered.
        // The ActionListener interface has one method void actionPerformed(ActionEvent e)
        try{
            String out=text.getText(); // Get the text from the text field
            
            JPanel p2 = formatLabel(out); // Create a new JPanel
        

            a1.setLayout(new BorderLayout());
            JPanel right=new JPanel(new BorderLayout());// Print the text to the console
            right.add(p2,BorderLayout.LINE_END); // Add the label to the right side of the panel
            vertical.add(right); // Add the panel to the vertical box layout
            vertical.add(Box.createVerticalStrut(15)); // Add a vertical strut to create space between messages
            a1.add(vertical,BorderLayout.PAGE_START); // Add the vertical box layout to the main panel

            dout.writeUTF(out); // Send the message to the server
            text.setText(""); // Clear the text field after sending the message

            f.repaint(); // Repaint the panel to update the display
            f.invalidate();
            f.validate(); // Validate the layout to ensure it is properly arranged
            //without these three lines, the new message will not appear on frame
        }catch(Exception e){
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }
    }
    

    public static JPanel formatLabel(String out) {
        JPanel panel=new JPanel(); // Create a new JPanel
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); // Set the layout manager to a vertical box layout
        JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>"); // Create a new JLabel with the text 
        output.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Set the font of the label
        output.setBackground(new Color(37,211,102)); // Set the background color of the label
        output.setOpaque(true); // Make the label opaque to show the background color
        output.setBorder(new EmptyBorder(15,15,15,50));
        

        panel.add(output); // Add the label to the panel

        Calendar cal=Calendar.getInstance(); // Get the current time
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm"); // Create a new SimpleDateFormat object to format the time
        JLabel time=new JLabel(); // Create a new JLabel for the time 
        time.setText(sdf.format(cal.getTime())); // Set the time dynamically
        panel.add(time); // Add the time label to the panel 

        return panel; // Return the panel with the formatted label
        
    }

    public static void main(String[] args) {
        // Create a new server instance
        new Client();

        try{
            Socket s=new Socket("127.0.0.1", 6001); // Create a new socket connection to the server
            DataInputStream din=new DataInputStream(s.getInputStream()); // Create a DataInputStream to read data from the socket to receive the messages
            dout=new DataOutputStream(s.getOutputStream());

            while (true){
                    a1.setLayout(new BorderLayout()); // Set the layout manager to a border layout  it is the space where we want our msg to be seen
                    String msg=din.readUTF(); // Read a message from the input stream
                    JPanel p2 = formatLabel(msg); // Create a new JPanel with the message formats the msg

                    JPanel left=new JPanel(new BorderLayout()); // Create a new JPanel for the left side
                    left.add(p2,BorderLayout.LINE_START); // Add the label to the left side of the panel
                    vertical.add(left); // Add the panel to the vertical box layout
                    
                    vertical.add(Box.createVerticalStrut(15)); // Add a vertical strut to create space between messages
                    a1.add(vertical,BorderLayout.PAGE_START); // Add the vertical box layout to the main panel
                    
                    f.validate();

                }    
        }catch(Exception e){
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }
        
    }


        // Additional server startup logic can be added here
    }

