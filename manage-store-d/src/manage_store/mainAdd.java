package manage_store;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



@SuppressWarnings("serial")
public class mainAdd extends JFrame

{
	Connection connection;
	Statement statement;
	

JFrame frame;
JComboBox<String> jSelectType = new JComboBox<String>();
JButton butId1,butId2;
JTextField jtf_ID1,jtf_ID2,jtf_ID3,jtf_ID4,jtf_ID5,jtf_ID6,jtf_NameOfTable;
JLabel labNameOfTable,labExample,labStatus;

public String numberConfig="";

public String USER="";
public String PASS="";
public String URL="";

//String path = "./dataConfig.xml";
//java.io.InputStream path = mojClass.class.getResourceAsStream("/dataConfig.xml");
final static String path2 = "src/dataConfig.xml";
Color colorOfFrame = Color.decode("#1e8bc3");
Color colorOfBut = Color.decode("#e8e6ff");

void styleOfBut(JButton button)
{
button.setBackground(colorOfBut);
button.setBorder(new LineBorder(Color.GRAY));
}

void init () throws IOException
{
	frame = new JFrame();
	frame.setTitle("MyDatabasePC v1.0");
	frame.setBounds(5, 5, 400, 100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.getContentPane().setBackground(colorOfFrame);
	frame.setIconImage(ImageIO.read(new File("src/znaczek.png")));
	
	frame.addWindowListener(new WindowAdapter() 
	{
		  public void windowClosing(WindowEvent we) 
		  {
			changeExit();
			System.out.println("Close");
		    System.exit(0);
		  }
		});
	
	frame.setSize(600,400);
	frame.setResizable(false);
	frame.setVisible(true);
    
	jSelectType.setBounds(460,100,120,50);
	frame.getContentPane().add(jSelectType);
	
	loading();
}

void changeExit()
{	java.io.InputStream path = mainSpec.class.getResourceAsStream("/dataConfig.xml");
	//String path = "./dataConfig.xml";
		//java.io.InputStream in = mojClass.class.getResourceAsStream("/dataConfig.xml");
		try {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(path);

        updateElementValue(document);
    } catch (ParserConfigurationException ex) {
        ex.printStackTrace(System.out);
    } catch (SAXException ex) {
        ex.printStackTrace(System.out);
    } catch (IOException ex) {
        ex.printStackTrace(System.out);
    }
	
}

private  void updateElementValue(Document doc)
{
    NodeList languages = doc.getElementsByTagName("Sprawdzenie");
    Element lang = null;
   
       lang = (Element) languages.item(0);
       Node name = lang.getElementsByTagName("Connect").item(0).getFirstChild();
       
       name.setNodeValue("false");
    	
       
      // String path = "./dataConfig.xml";
        
       try
        {
        	Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path2);
            tr.transform(source, result);
            
        }catch(Exception e)
        {
        	
        }
} 

void loading()
{
	butId1=new JButton("WYBIERZ");;
    butId1.setBounds(460,30,120,50);
    butId1.setVisible(true);
    frame.getContentPane().add(butId1);
    styleOfBut(butId1);
    
    butId2=new JButton("COFNIJ");;
    butId2.setBounds(20,30,120,50);
    butId2.setVisible(true);
    frame.getContentPane().add(butId2);
    styleOfBut(butId2);
    
    jtf_ID1 = new JTextField();
    jtf_ID1.setBounds(80,220,30,20);
    frame.getContentPane().add(jtf_ID1);
    jtf_ID1.setVisible(false);
    jtf_ID1.setBackground(colorOfBut);
    
    jtf_ID2 = new JTextField();
    jtf_ID2.setBounds(110,220,100,20);
    frame.getContentPane().add(jtf_ID2);
    jtf_ID2.setVisible(false);
    jtf_ID2.setBackground(colorOfBut);
    
    
    jtf_ID3 = new JTextField();
    jtf_ID3.setBounds(210,220,100,20);
    frame.getContentPane().add(jtf_ID3);
    jtf_ID3.setVisible(false);
    jtf_ID3.setBackground(colorOfBut);
    
    jtf_ID4 = new JTextField();
    jtf_ID4.setBounds(310,220,100,20);
    frame.getContentPane().add(jtf_ID4);
    jtf_ID4.setVisible(false);
    jtf_ID4.setBackground(colorOfBut);
    
    jtf_ID5 = new JTextField();
    jtf_ID5.setBounds(410,220,80,20);
    frame.getContentPane().add(jtf_ID5);
    jtf_ID5.setVisible(false);
    jtf_ID5.setBackground(colorOfBut);
    
    jtf_ID6 = new JTextField();
    jtf_ID6.setBounds(490,220,40,20);
    frame.getContentPane().add(jtf_ID6);
    jtf_ID6.setVisible(false);
    jtf_ID6.setBackground(colorOfBut);
    
    jtf_NameOfTable = new JTextField();
    jtf_NameOfTable.setBounds(80,170,130,20);
    frame.getContentPane().add(jtf_NameOfTable);
    jtf_NameOfTable.setVisible(false);
    jtf_NameOfTable.setBackground(colorOfBut);
    
    labNameOfTable = new JLabel();
    labNameOfTable.setBounds(80,150,130,20);
    frame.getContentPane().add(labNameOfTable);
    labNameOfTable.setVisible(true);
    labNameOfTable.setText("WPISZ NAZWE TABELI");
    labNameOfTable.setForeground(colorOfBut);
    
    labExample = new JLabel();
    labExample.setBounds(40,200,500,20);
    frame.getContentPane().add(labExample);
    labExample.setVisible(false);
    //label2.setText("id----------brand-----------------model-----------------cena_do---------cena_po-------sklep");
    labExample.setText("EX :         "+"id"+"              "+"brand"
    		+"                       "+"model"
    		+"                    "+"cena_buy"
    		+"            "+"cena_cell"
    		+"    "+"sklep");
    labExample.setForeground(colorOfBut);
    
    
    labStatus = new JLabel();
    labStatus.setBounds(420,300,200,20);
    frame.getContentPane().add(labStatus);
    labStatus.setVisible(true);
    labStatus.setText("STATUS : " + "CONNECTED");
    labStatus.setForeground(colorOfBut);
        
}

void jSelectListener()
{
	jSelectType.addItem("SZABLON");
	jSelectType.addItem("SWOJE");
	
	jtf_NameOfTable.setVisible(true);
	
	jSelectType.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
						
			if(jSelectType.getSelectedItem().toString()=="SZABLON")
			{
				labExample.setVisible(false);
				jtf_ID1.setVisible(false);
            	jtf_ID2.setVisible(false);
            	jtf_ID3.setVisible(false);
            	jtf_ID4.setVisible(false);
            	jtf_ID5.setVisible(false);
            	jtf_ID6.setVisible(false);
            	
			}
			else if(jSelectType.getSelectedItem().toString()=="SWOJE")
			{
				labExample.setVisible(true);
				jtf_ID1.setVisible(true);
	        	jtf_ID2.setVisible(true);
	        	jtf_ID3.setVisible(true);
	        	jtf_ID4.setVisible(true);
	        	jtf_ID5.setVisible(true);
	        	jtf_ID6.setVisible(true);
	        	        	
			}
		}
	});	
}

void selectMethod()
{
	butId1.addActionListener(new ActionListener() 
	{

        @Override
        public void actionPerformed(ActionEvent e) 
        {
        	readNumberMain();
        	jtf_NameOfTable.setVisible(false);
        	
        	butId1.setVisible(false);
        	labNameOfTable.setVisible(false);
        	jSelectType.setVisible(false);
        	
        	
        	if(jSelectType.getSelectedItem().toString()=="SZABLON")
        	{
        		zaladowanieNowejTabeli2();        		
        		jtf_NameOfTable.setText("");        		
        	}
        	else
        	{
        		addMethod();
        		
        		jtf_ID1.setText("");
            	jtf_ID2.setText("");
            	jtf_ID3.setText("");
            	jtf_ID4.setText("");
            	jtf_ID5.setText("");
            	jtf_ID6.setText("");
            	jtf_NameOfTable.setText("");
            	
            	jtf_ID1.setVisible(false);
            	jtf_ID2.setVisible(false);
            	jtf_ID3.setVisible(false);
            	jtf_ID4.setVisible(false);
            	jtf_ID5.setVisible(false);
            	jtf_ID6.setVisible(false);
            	
            	
            	jSelectType.setSelectedItem("SZABLON");
        	}
	
        }
	});
}

void addMethod()
{
	String a =jtf_ID1.getText().toString();
	String b =jtf_ID2.getText().toString();
	String c =jtf_ID3.getText().toString();
	String d =jtf_ID4.getText().toString();
	String e =jtf_ID5.getText().toString();
	String f =jtf_ID6.getText().toString();
	
	String myTable= "CREATE TABLE "+jtf_NameOfTable.getText().toString()+"("
	 + a +" "+"INT NOT NULL,"
	 + b +" "+"VARCHAR(45) NOT NULL," 
     + c +" "+"VARCHAR(45) NOT NULL,"  
     + d +" "+"VARCHAR(45) NOT NULL,"
     + e +" "+"VARCHAR(45) NOT NULL,"
     + f +" "+"VARCHAR(45) NOT NULL,"
     +"PRIMARY KEY("+a+"))"; 
	
	try
	{
		Connection connection = (Connection) DriverManager.getConnection(URL,USER,PASS);
		
		Statement statement = (Statement) connection.createStatement();
		
		statement.executeUpdate(myTable);
		 
		 System.out.print("Tabela dodana !");
		 labStatus.setText("STATUS : " + "ADD COMPLETE");
	}
	catch (Exception exc)
	{
		exc.printStackTrace();		
	}
	
}

void zaladowanieNowejTabeli2()

{
	String myTableName = "CREATE TABLE "+jtf_NameOfTable.getText().toString()+"("
            + "id INT NOT NULL,"  
            + "brand VARCHAR(45) NOT NULL," 
            + "model VARCHAR(45) NOT NULL,"  
            + "cena_do VARCHAR(45) NOT NULL,"
            + "cena_po VARCHAR(45) NOT NULL,"
            + "sklep VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY(id))"; 
	
	try
	{
		
		Connection connection = (Connection) DriverManager.getConnection(URL,USER,PASS);
		
		Statement statement = (Statement) connection.createStatement();
		
		statement.executeUpdate(myTableName);
		 
		labStatus.setText("STATUS : " + "ADD COMPLETE");
		 
	}
	catch (Exception exc)
	{
		exc.printStackTrace();		
	}
}



void comeBack()
{
butId2.addActionListener(new ActionListener(){
		
    	@Override
		public void actionPerformed(ActionEvent e)
		{
			frame.setVisible(false);
			try {
				mainSpec startWindow = new mainSpec();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}			
		}
	}
	
);
}


void readNumberMain()
{
	pobierzCharakt(readNumber());
}

void pobierzCharakt(int numberOut)
{
	
    		int number = numberOut;
        	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    		java.io.InputStream path = mainSpec.class.getResourceAsStream("/BookCatalog.xml");
        	try {
              
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(path);
                NodeList nodeList = document.getElementsByTagName("Charakterystyki");
              
            	Node node = nodeList.item(number);
                    
                    if (Node.ELEMENT_NODE == node.getNodeType())
                    {
                        Element element = (Element) node;
                         USER= element.getElementsByTagName("User").item(0).getTextContent(); 
                         PASS= element.getElementsByTagName("Password").item(0).getTextContent(); 
                         URL = element.getElementsByTagName("Url").item(0).getTextContent(); 
                    }
          
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace(System.out);
            } catch (SAXException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
}

int readNumber()
{
	 	//ClassLoader cl = getClass().getClassLoader();
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	java.io.InputStream path = mainSpec.class.getResourceAsStream("/BookCatalog.xml");
    	//String path = "./BookCatalog.xml";
    	try {
            
           DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            
           Document document = documentBuilder.parse(path);
            
           NodeList nodeList = document.getElementsByTagName("Sprawdzenie");
           Node node = nodeList.item(0);
               
              
                if (Node.ELEMENT_NODE == node.getNodeType())
                {
                    Element element = (Element) node;
                    numberConfig= element.getElementsByTagName("Numer").item(0).getTextContent();
                }
        
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
    	String del = numberConfig;
    	del=del.substring(4, 5);
    	
    	int del2=Integer.valueOf(del);
    	    	
    	int del3=del2-1;	
    	System.out.println("SAVE numer + "+del3);
    	return del3;    	
}




public mainAdd() throws IOException
{
	init();
	selectMethod();
	jSelectListener();
	comeBack();
}

}
