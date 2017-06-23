package testCalosc_v01;

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



public class mojDodaj extends JFrame
{
	Connection myConn;
	Statement myStmt;
	

JFrame frame;
JComboBox jComboBox1 = new JComboBox();
JButton button1,button2;
JTextField jtf,jtf2,jtf3,jtf4,jtf5,jtf52,jtf6;
JLabel label,label2,label3,label4;

public String numerKonfig="";

public String downUSER="";
public String downPASS="";
public String downURL="";

//String path = "./BookCatalog.xml";
//java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
String path2 = "src/BookCatalog.xml";
Color myColor = Color.decode("#1e8bc3"); // FRAME
Color myColor2 = Color.decode("#e8e6ff"); // BUTTONS

void buttt(JButton button)
{
//Border emptyBorder = BorderFactory.createEmptyBorder();
//button.setBorder(emptyBorder);
	
	//button.setContentAreaFilled(false);
button.setBackground(myColor2);
button.setBorder(new LineBorder(Color.GRAY));


}

void init () throws IOException
{
	frame = new JFrame();
	//frame.setTitle("OKIENKO GLOWNE");
	frame.setTitle("MyDatabasePC v1.0");
	frame.setBounds(5, 5, 400, 100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.getContentPane().setBackground(myColor);
	frame.setIconImage(ImageIO.read(new File("src/znaczek.png")));
	
	frame.addWindowListener(new WindowAdapter() 
	{
		  public void windowClosing(WindowEvent we) 
		  {
			zmiana();
			System.out.println("Close");
		    System.exit(0);
		  }
		});
	
	frame.setSize(600,400);
	frame.setResizable(false);
	frame.setVisible(true);
    
	jComboBox1.setBounds(460,100,120,50);
	frame.getContentPane().add(jComboBox1);
	
	zaladowanie();
}

void zmiana() // Zmiana na false przy WYJSCIU
{	java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	//String path = "./BookCatalog.xml";
		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		try {
        // ĐˇĐľĐ•Đ´Đ°ĐµŃ‚ŃŃŹ ĐżĐľŃŃ‚Ń€ĐľĐ¸Ń‚ĐµĐ»ŃŚ Đ´ĐľĐşŃĐĽĐµĐ˝Ń‚Đ°
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // ĐˇĐľĐ•Đ´Đ°ĐµŃ‚ŃŃŹ Đ´ĐµŃ€ĐµĐ˛Đľ DOM Đ´ĐľĐşŃĐĽĐµĐ˝Ń‚Đ° Đ¸Đ• Ń„Đ°ĐąĐ»Đ°
        Document document = documentBuilder.parse(path);

        updateElementValue(document);
        
    } catch (ParserConfigurationException ex) {
        ex.printStackTrace(System.out);
    } catch (SAXException ex) {
        ex.printStackTrace(System.out);
    } catch (IOException ex) {
        ex.printStackTrace(System.out);
    }
	//return status;
}

private  void updateElementValue(Document doc) // Zmiana na false przy WYJSCIU
{
    NodeList languages = doc.getElementsByTagName("Sprawdzenie");
    Element lang = null;
   
       lang = (Element) languages.item(0);
        Node name = lang.getElementsByTagName("Connect").item(0).getFirstChild();
       
    
      name.setNodeValue("false");
    	
       
      // String path = "./BookCatalog.xml";
        
       try
        {
        	Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path2);
            tr.transform(source, result);
            
        }catch(Exception e)
        {
        	
        }
        
    //}
} 

void zaladowanie()
{
	button1=new JButton("WYBIERZ");;
    button1.setBounds(460,30,120,50);//Polozenie na formie
    button1.setVisible(true);
    frame.getContentPane().add(button1);
    buttt(button1);
    
    button2=new JButton("COFNIJ");;
    button2.setBounds(20,30,120,50);//Polozenie na formie
    button2.setVisible(true);
    frame.getContentPane().add(button2);
    buttt(button2);
    
    jtf = new JTextField();
    jtf.setBounds(80,220,30,20);
    frame.getContentPane().add(jtf);
    jtf.setVisible(false);
    jtf.setBackground(myColor2);
    
    jtf2 = new JTextField();
    jtf2.setBounds(110,220,100,20);//Polozenie na formie
    frame.getContentPane().add(jtf2);
    jtf2.setVisible(false);
    jtf2.setBackground(myColor2);
    
    
    jtf3 = new JTextField();
    jtf3.setBounds(210,220,100,20);//Polozenie na formie
    frame.getContentPane().add(jtf3);
    jtf3.setVisible(false);
    jtf3.setBackground(myColor2);
    
    jtf4 = new JTextField();
    jtf4.setBounds(310,220,100,20);//Polozenie na formie
    frame.getContentPane().add(jtf4);
    jtf4.setVisible(false);
    jtf4.setBackground(myColor2);
    
    jtf5 = new JTextField();
    jtf5.setBounds(410,220,80,20);//Polozenie na formie
    frame.getContentPane().add(jtf5);
    jtf5.setVisible(false);
    jtf5.setBackground(myColor2);
    
    jtf52 = new JTextField();
    jtf52.setBounds(490,220,40,20);//Polozenie na formie
    frame.getContentPane().add(jtf52);
    jtf52.setVisible(false);
    jtf52.setBackground(myColor2);
    
    jtf6 = new JTextField();
    jtf6.setBounds(80,170,130,20);//Polozenie na formie
    frame.getContentPane().add(jtf6);
    jtf6.setVisible(false);
    jtf6.setBackground(myColor2);
    
    label = new JLabel();
    label.setBounds(80,150,130,20);//Polozenie na formie
    frame.getContentPane().add(label);
    label.setVisible(true);
    label.setText("WPISZ NAZWE TABELI");
    label.setForeground(myColor2);
    
    label2 = new JLabel();
    label2.setBounds(40,200,500,20);//Polozenie na formie
    frame.getContentPane().add(label2);
    label2.setVisible(false);
    //label2.setText("id----------brand-----------------model-----------------cena_do---------cena_po-------sklep");
    label2.setText("EX :         "+"id"+"              "+"brand"
    		+"                       "+"model"
    		+"                    "+"cena_buy"
    		+"            "+"cena_cell"
    		+"    "+"sklep");
    label2.setForeground(myColor2);
    
    
    label3 = new JLabel();
    label3.setBounds(420,300,200,20);//Polozenie na formie
    frame.getContentPane().add(label3);
    label3.setVisible(true);
    label3.setText("STATUS : " + "CONNECTED");
    label3.setForeground(myColor2);
    
       
    
   /* label4 = new JLabel();
    label4.setBounds(480,300,100,20);//Polozenie na formie
    frame.getContentPane().add(label4);
    label4.setVisible(true);
    label4.setText("CONNECTED");
    label4.setForeground(myColor2);
    */
    
}

void dodanieCombo() // Listener of ComboBox
{
	jComboBox1.addItem("SZABLON");
	jComboBox1.addItem("SWOJE");
	
	jtf6.setVisible(true);
	
	jComboBox1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			
			
			if(jComboBox1.getSelectedItem().toString()=="SZABLON")
			{
				label2.setVisible(false);
				jtf.setVisible(false);
            	jtf2.setVisible(false);
            	jtf3.setVisible(false);
            	jtf4.setVisible(false);
            	jtf5.setVisible(false);
            	jtf52.setVisible(false);
            	
			}
			else if(jComboBox1.getSelectedItem().toString()=="SWOJE")
			{
				label2.setVisible(true);
				jtf.setVisible(true);
	        	jtf2.setVisible(true);
	        	jtf3.setVisible(true);
	        	jtf4.setVisible(true);
	        	jtf5.setVisible(true);
	        	jtf52.setVisible(true);
	        	
	        	
	        	
			}
			
			
		}
	});
	
	
	
	
	
	
}

//----------------------------------------------------------

void wybierzMetoda()
{
	button1.addActionListener(new ActionListener() 
	{

        @Override
        public void actionPerformed(ActionEvent e) 
        {
        	czytajNumer2();
        	//label4.setText(downUSER.toString());
        	jtf6.setVisible(false);
        	
        	button1.setVisible(false);
        	label.setVisible(false);
        	jComboBox1.setVisible(false);
        	
        	
        	if(jComboBox1.getSelectedItem().toString()=="SZABLON")
        	{
        		zaladowanieNowejTabeli2();
        		
        		jtf6.setText("");
        		
        	}
        	else
        	{
        		zala();
        		
        		jtf.setText("");
            	jtf2.setText("");
            	jtf3.setText("");
            	jtf4.setText("");
            	jtf5.setText("");
            	jtf52.setText("");
            	jtf6.setText("");
            	
            	jtf.setVisible(false);
            	jtf2.setVisible(false);
            	jtf3.setVisible(false);
            	jtf4.setVisible(false);
            	jtf5.setVisible(false);
            	jtf52.setVisible(false);
            	
            	
            	jComboBox1.setSelectedItem("SZABLON");
        	}
	
        }
	});
}




void zala()
{
	
	
	
	/*String myTableName = "CREATE TABLE "+jtf6.getText().toString()+"("
            + "id INT NOT NULL,"  
            + "brand VARCHAR(45) NOT NULL," 
            + "model VARCHAR(45) NOT NULL,"  
            + "cena_do VARCHAR(45) NOT NULL,"
            + "cena_po VARCHAR(45) NOT NULL,"
            + "sklep VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY(id))"; 
	
	*/
	String a =jtf.getText().toString();
	String b =jtf2.getText().toString();
	String c =jtf3.getText().toString();
	String d =jtf4.getText().toString();
	String e =jtf5.getText().toString();
	String f =jtf52.getText().toString();
	
	String myTable= "CREATE TABLE "+jtf6.getText().toString()+"("
	 + a +" "+"INT NOT NULL,"
	 + b +" "+"VARCHAR(45) NOT NULL," 
     + c +" "+"VARCHAR(45) NOT NULL,"  
     + d +" "+"VARCHAR(45) NOT NULL,"
     + e +" "+"VARCHAR(45) NOT NULL,"
     + f +" "+"VARCHAR(45) NOT NULL,"
     +"PRIMARY KEY("+a+"))"; 
	
	//label3.setText(jtf2.getText().toString());
	
	try
	{
		
		Connection cone = (Connection) DriverManager.getConnection(downURL,downUSER,downPASS);
		
		Statement iii = (Statement) cone.createStatement();
		
		iii.executeUpdate(myTable);
		 
		 
		//label4.setText("Tabela dodana !!!");
		 System.out.print("Tabela dodana !");
		 //label3.setText("STATUS : " + "ADD COMPLETE");
		 label3.setText("STATUS : " + "ADD COMPLETE");
	}
	catch (Exception exc)
	{
		exc.printStackTrace();
		//label3.setText("STATUS : " + "ERROR. PLEASE REPEAT");
		//label3.setText("STATUS : " + "ADD COMPLETE");
	}
	
	
	
	
}

void zaladowanieNowejTabeli2()

{
	//String n1= "CREATE TABLE "+jtf.getText().toString()+"(";
	String myTableName = "CREATE TABLE "+jtf6.getText().toString()+"("
            + "id INT NOT NULL,"  
            + "brand VARCHAR(45) NOT NULL," 
            + "model VARCHAR(45) NOT NULL,"  
            + "cena_do VARCHAR(45) NOT NULL,"
            + "cena_po VARCHAR(45) NOT NULL,"
            + "sklep VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY(id))"; 
	
	/*String n1= "CREATE TABLE "+jtf.getText().toString()+"(";
	String n2= jtf2.getText().toString()+" INT NOT NULL,";
	String n3= jtf3.getText().toString()+" VARCHAR(45) NOT NULL,";
	String n4= jtf4.getText().toString()+" VARCHAR(45) NOT NULL,";
	String n5= jtf5.getText().toString()+" DOUBLE NOT NULL,";
	String n6= "PRIMARY KEY("+jtf2.getText().toString() +"))"; 
	
	String myTableName =n1+n2+n3+n4+n5+n6;*/
	
	try
	{
		
		Connection myConn = (Connection) DriverManager.getConnection(downURL,downUSER,downPASS);
		
		Statement myStmt = (Statement) myConn.createStatement();
		
		 myStmt.executeUpdate(myTableName);
		 
		// label4.setText("Tabela dodana !!!");
		// System.out.print("Tabela dodana !");
		 label3.setText("STATUS : " + "ADD COMPLETE");
		 
	}
	catch (Exception exc)
	{
		exc.printStackTrace();
		//label3.setText("STATUS : " + "ERROR. PLEASE REPEAT");
		
	}
}



void comeBack()
{
button2.addActionListener(new ActionListener(){
		
    	@Override
		public void actionPerformed(ActionEvent e)
		{
			

    		frame.setVisible(false);
			try {
				mojClass mojClassOkienko = new mojClass();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
);
}

//----------------------------------------------------------
void czytajNumer2() // Metoda bez input
{
	pobierzCharakt(czytajNumer());
}

void pobierzCharakt(int numer2) // get URL,USER,PASS from 0
{
	
    		int numer = numer2;
        	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
        	try {
              
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(path);
                NodeList nodeList = document.getElementsByTagName("Charakterystyki"); // Gdzie to będzie szukane
              
            	Node node = nodeList.item(numer); // Pierwszy półka
                    
                    if (Node.ELEMENT_NODE == node.getNodeType()) // Jesli to jest półka to
                    {
                        Element element = (Element) node; // Element półki
                         downUSER= element.getElementsByTagName("User").item(0).getTextContent(); 
                         downPASS= element.getElementsByTagName("Password").item(0).getTextContent(); 
                         downURL = element.getElementsByTagName("Url").item(0).getTextContent(); 
                    }
          
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace(System.out);
            } catch (SAXException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        	
   
}

int czytajNumer()// Laduje numer z XML SAVE0 --> 0
{
	 	//ClassLoader cl = getClass().getClassLoader();
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    	//String path = "./BookCatalog.xml";
    	try {
            // Создается построитель документа
           DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
           Document document = documentBuilder.parse(path);
            
            NodeList nodeList = document.getElementsByTagName("Sprawdzenie"); // Gdzie to będzie szukane
            Node node = nodeList.item(0); // Pierwszy półka
               
              
                if (Node.ELEMENT_NODE == node.getNodeType()) // Jesli to jest półka to
                {
                    Element element = (Element) node; // Element półki
                    numerKonfig= element.getElementsByTagName("Numer").item(0).getTextContent();
                }
        
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
    	String del = numerKonfig;
    	del=del.substring(4, 5);
    	//System.out.println(del);
    	int del2=Integer.valueOf(del);
    	
    	//if(numerKonfig=="NOWA")
    	//{
    	//	System.out.println("NOWA + "+del2);
    	//   return del2;
    	
    	///}else
    	//{	
    	int delll=del2-1;	
    	System.out.println("SAVE numer + "+delll);
    	return delll;
    	//}
}



//----------------------------------------------------------
public mojDodaj() throws IOException
{
	init();
	wybierzMetoda();
	dodanieCombo();
	comeBack();
}

}
