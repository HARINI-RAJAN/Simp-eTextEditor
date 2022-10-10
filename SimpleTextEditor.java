import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class SimpleTextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    // Constructor
    SimpleTextEditor()
    {
        //setting the initial frame
        frame=new JFrame("Simple Text Editor");
        textArea=new JTextArea();
        frame.setSize(800,800);
        frame.add(textArea);
        frame.setVisible(true);
        //MenuBar, Menu and MenuItems
        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu=new JMenu("File Menu");
        JMenu editMenu=new JMenu("Edit Menu");
        //adding menus to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        JMenuItem New=new JMenuItem("New File");
        JMenuItem Open=new JMenuItem("Open File");
        JMenuItem Save=new JMenuItem("Save File");
        JMenuItem Print=new JMenuItem("Print File");
        JMenuItem Cut=new JMenuItem("Cut");
        JMenuItem Copy=new JMenuItem("Copy");
        JMenuItem Paste=new JMenuItem("Paste");
        //adding actionListeners for menuItems
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        //adding menuItems to Filemenu
        fileMenu.add(New);
        fileMenu.add(Open);
        fileMenu.add(Save);
        fileMenu.add(Print);
        //adding menuItems to EditMenu
        editMenu.add(Cut);
        editMenu.add(Copy);
        editMenu.add(Paste);
        //adding actionListerners to editMenu Items
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        //adding menubar to the frame
        frame.setJMenuBar(menuBar);
        frame.show();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args)
    {
        //invoking constructor by creating object for SimpleTextEditor
        SimpleTextEditor editor=new SimpleTextEditor();
    }

    //for when an actionEvent is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s=="New File")
        {
            textArea.setText("");
        }
        else if(s=="Open File")
        {
            //creating an object of JFileChooser class
            JFileChooser fileChooser=new JFileChooser("Desktop");
            //Invoking the showOpenDialog function to show the open dialog
            int value=fileChooser.showOpenDialog(null);
            if(value==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String s1="",s2="";
                    // File reader
                    BufferedReader reader=new BufferedReader(new FileReader(file));
                    s2=reader.readLine();
                    //to read the file line by line and assign to s1
                    while((s1= reader.readLine())!=null)
                    {
                        //concat s1 to finalString s2
                        s2+=s1+"\n";
                    }
                    //print finalString s2
                    textArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if (s=="Save File")
        {
            //creating an object of JFileChooser class
            JFileChooser fileChooser=new JFileChooser("f:");
            //Invoking the showsSaveDialog function to show the save dialog
            int value=fileChooser.showSaveDialog(null);
            if(value==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    // Creating a file writer
                    FileWriter wr=new FileWriter(file,false);
                    BufferedWriter writer=new BufferedWriter(wr);
                    writer.write(textArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if (s=="Print File")
        {
            try
            {
                // print the file
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (s=="Cut")
        {
            textArea.cut();
        }
        else if (s=="Copy")
        {
            textArea.copy();
        }
        else if (s=="Paste")
        {
            textArea.paste();
        }

    }
}
