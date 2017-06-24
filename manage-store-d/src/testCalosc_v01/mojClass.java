package testCalosc_v01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


@SuppressWarnings("serial")
public class mojClass extends JFrame {
	
	int selectIndex = 0;
	
	Connection connection;
	Statement statement;
	ResultSet resultset;
	
	public boolean connectBool = false;	
	public String status = "false";		
	ArrayList<String> dataProfile = new ArrayList<String>();
	public String USERNAME="";
	public String PASSWORD="";
	public String URL="";
	public String selectConfig = "";	
	public String numberOfConfig="";	
	public boolean zmiennaNiema=false; 
	
	
	JFrame frame;
	JComboBox<String> jSelectTable =new JComboBox<String>();
	JButton select = new JButton() ;
	JButton remove = new JButton() ;
	JButton addNew = new JButton();
	JButton connect = new JButton();
	
	ArrayList<String> selectedTable = new ArrayList<String>();
	ArrayList<String> columnNames = new ArrayList<String>();
	JScrollPane scroller = new JScrollPane();
	
	
	boolean varCheckOne = true;
    boolean varCheckTwo= true;

    JButton butId1,butId2,butId3,butId4, butId5,butId6,butId7,butId8,butId9;
    JTextField jtfSearch, jtf_ID2,jtf_ID3,jtf_ID4,jtf_ID5,jtf_ID1,jtfDelete;
    JTextField jtf_ID6;
    
    JTextField jtfURL =new JTextField();
    JTextField jtfUSER =new JTextField();
    JTextField jtfPASS =new JTextField();

    JLabel statusLabel;
    
    JLabel userLabel;
    JLabel passLabel;
    JLabel urlLabel;
    JLabel exLabel;
    JTextField exampleLabel;
    JComboBox<String> jSelectConfig =new JComboBox<String>();
    JButton butBackward =new JButton();
    JButton butDelete =new JButton();
    
    Color colorOfFrame = Color.decode("#1e8bc3"); 
    Color colorOfCombo = Color.decode("#d24d57"); 
    Color colorOfBut = Color.decode("#e8e6ff"); 

    
    
  //String path = "./BookCatalog.xml";
    //java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    final static String PATH = "src/BookCatalog.xml";
    
    boolean statusOfTables = false;    
    boolean thr = false;
    
    
    
	static void init() 
	{
		EventQueue.invokeLater(new Runnable()
		{
	public void run ()
	{
		try
		{
			mojClass okienkoGlowne1 = new mojClass();
			okienkoGlowne1.frame.setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});
	}

	public static void main(String[] args)
	{
		init();
	}
	
	
	
	void styleOfBut(JButton button){
		
	button.setBackground(colorOfBut);
	button.setBorder(new LineBorder(Color.GRAY));   
	}
	
	void init2() throws IOException{
	
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
		
		
		jSelectTable.setBounds(20,110,120,50);
		frame.getContentPane().add(jSelectTable);
		jSelectTable.setVisible(false);
		
		jSelectConfig.setBounds(50,140,120,50);
		frame.getContentPane().add(jSelectConfig);
			
		checkProfile();
					
		select.setText("WYBIERZ");
		select.setBounds(260,30,120,50);
		frame.getContentPane().add(select);
		styleOfBut(select);
		
		addNew.setText("DODAJ");
		addNew.setBounds(460,30,120,50);
		frame.getContentPane().add(addNew);
		styleOfBut(addNew);
		
		remove.setText("USUN");
		remove.setBounds(460,150,120,50);
		frame.getContentPane().add(remove);
		styleOfBut(remove);
		
		
		connect.setText("CONNECT");
		connect.setBounds(460,200,120,50);
		frame.getContentPane().add(connect);
		styleOfBut(connect);
		
		userLabel = new JLabel();
	    userLabel.setBounds(10,50,50,20);
	    frame.getContentPane().add(userLabel);
	    userLabel.setVisible(false);
	    userLabel.setText("USER");
	    userLabel.setForeground(colorOfBut);
	    
	    passLabel = new JLabel();
	    passLabel.setBounds(10,70,50,20);
	    frame.getContentPane().add(passLabel);
	    passLabel.setVisible(false);
	    passLabel.setText("PASS");
	    passLabel.setForeground(colorOfBut);
	    
	    urlLabel = new JLabel();
	    urlLabel.setBounds(10,90,50,20);
	    frame.getContentPane().add(urlLabel);
	    urlLabel.setVisible(false);
	    urlLabel.setText("URL");
	    urlLabel.setForeground(colorOfBut);
	    
	    exLabel = new JLabel();
	    exLabel.setBounds(10,110,30,20);
	    frame.getContentPane().add(exLabel);
	    exLabel.setVisible(false);
	    exLabel.setText("EX :");
	    exLabel.setForeground(colorOfBut);
	    
	    exampleLabel = new JTextField();
	    exampleLabel.setBounds(50,110,500,20);
	    frame.getContentPane().add(exampleLabel);
	    exampleLabel.setVisible(false);
	    exampleLabel.setEditable(false);
	    exampleLabel.setText("jdbc:mysql://localhost:3306/zegarki_magazyn?autoReconnect=true&amp;useSSL=false");
	    exampleLabel.setForeground(colorOfBut);
	    exampleLabel.setBorder(null);
	    exampleLabel.setBackground(colorOfFrame);
	    Font font1 = new Font("Calibri", Font.BOLD, 13);
	    exampleLabel.setFont(font1);
	    
	    jtfUSER = new JTextField();
	    jtfUSER.setBounds(50,50,100,20);
	    frame.getContentPane().add(jtfUSER);
	    jtfUSER.setVisible(false);
	    
	    jtfPASS = new JTextField();
	    jtfPASS.setBounds(50,70,100,20);
	    frame.getContentPane().add(jtfPASS);
	    jtfPASS.setVisible(false);
	    
	    jtfURL = new JTextField();
		jtfURL.setBounds(50,90,500,20);
	    frame.getContentPane().add(jtfURL);
	    jtfURL.setVisible(false);
		
	    statusLabel = new JLabel();
	    statusLabel.setBounds(420,300,200,20);
	    frame.getContentPane().add(statusLabel);
	    statusLabel.setText("STATUS : " + "DISCONNECTED");
	    statusLabel.setForeground(colorOfBut);
	    
	    butBackward.setText("COFNIJ");
		butBackward.setBounds(20,30,120,50);
		frame.getContentPane().add(butBackward);
		butBackward.setVisible(false);
		styleOfBut(butBackward);
		
		butDelete.setText("DELETE");
		butDelete.setBounds(50,250,120,50);
		frame.getContentPane().add(butDelete);
		butDelete.setVisible(true);
		styleOfBut(butDelete);
		
		
	    /* Check if <Connect>true</Connect> or FALSE*/
	    loadValue();
	    
	    
	    
	    if(status.contains("true"))
	    {
	    	connect2();
	    	select.setVisible(true);
	    	addNew.setVisible(true);
	    	remove.setVisible(true);
	    	jSelectTable.setVisible(true);
	    	jSelectConfig.setVisible(false);
	    	
	    	jtfURL.setVisible(false);
		    jtfUSER.setVisible(false);
		    jtfPASS.setVisible(false);
		    connect.setVisible(false);
		    butBackward.setVisible(true);
	    	
	    }
	    else if(status.contains("false"))
	    {
	    
	    select.setVisible(false);
    	addNew.setVisible(false);
    	remove.setVisible(false);
	    
	    }
	    
	    loading();
	    	
	}
	
	void connect()
	{
		statusOfTables=false;
		
		selectConfig = jSelectConfig.getSelectedItem().toString();
		selectIndex=jSelectConfig.getSelectedIndex();
		
		if(jSelectConfig.getSelectedItem().toString().contains("NOWA"))
		{
				addNewConfig();
		
		}
		
		if(!jSelectConfig.getSelectedItem().toString().contains("NOWA"))
		{
			setConnect2();
			selectSave();
		}
		
		getItems();
			
		if(!statusOfTables )
		{
			if(jSelectTable.getItemCount()>0){
			select.setVisible(true);
			remove.setVisible(true);}
			
			addNew.setVisible(true);
			
			connect.setVisible(false);
			jtfURL.setVisible(false);
			jtfUSER.setVisible(false);
			jtfPASS.setVisible(false);
			
			userLabel.setVisible(false);
			passLabel.setVisible(false);
			urlLabel.setVisible(false);
			exLabel.setVisible(false);
			exampleLabel.setVisible(false);
			statusLabel.setText("STATUS : "+"CONNECTED");
			
			butDelete.setVisible(false);
		}
		
	}
	
	/*Load to jSelectConfig items*/
	void getItems()
	{
		if(jSelectConfig.getItemCount()!=0)
		{if(jSelectConfig.getSelectedItem().toString().contains("NOWA"))
		{
			URL = jtfURL.getText();
			USERNAME = jtfUSER.getText();
			PASSWORD = jtfPASS.getText();
		}
		}
		
		try
		{
			
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			statement = connection.createStatement();
			
			DatabaseMetaData md = connection.getMetaData();
			
			resultset =md.getTables(null,null,null,null);					
			
			while(resultset.next())
			{
			    jSelectTable.addItem(resultset.getString(3));
			 
			}
			connection.close();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
			statusOfTables=true;
			
		}
		
	}
	
	
		
	/*Contains connect*/
	void connectMethod()
	{
		connect.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{	
				Thread t1 = new Thread("New Thread") {public void run(){
					
					    connect();
					    
				      }};
				     				      
				      t1.start();
				      try
				      {
				    	 t1.join();
				      }catch(Exception ee)
				      {
				      }
				
				if(!statusLabel.getText().toString().contains("DISCONNECTED"))
				{
				jSelectConfig.setVisible(false);
				jSelectTable.setVisible(true);
				
				butBackward.setVisible(true);
				}
				
			}});
	}
	/*Load size of SAVE's*/
	void checkProfile()
    {
		
		jSelectConfig.removeAllItems();
		
    	//ClassLoader cl = getClass().getClassLoader();
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    	
    	try {
            
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            NodeList nodeList = document.getElementsByTagName("Charakterystyki");
           for(int i=0;i<nodeList.getLength();i++)
            {
        	   Node node = nodeList.item(i);
               
                
                if (Node.ELEMENT_NODE == node.getNodeType())
                {
                    int l =i+1;
                	jSelectConfig.addItem("SAVE"+l);
                }
         
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
    	jSelectConfig.addItem("NOWA");
    }

	/*Get USER,PASS,URL from jSelectConfig*/
	void selectSave()
    {
    	int select = jSelectConfig.getSelectedIndex();
    		
    		System.out.println(select);
    		
    		getUserPassUrl(select);
    		
    		System.out.println(USERNAME);
    		System.out.println(PASSWORD);
    		System.out.println(URL);
    	
    }
	
	void getUserPassUrl(int numberOut)
    {
    		int number = numberOut;
        	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
        	try {
              
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(path);
                NodeList nodeList = document.getElementsByTagName("Charakterystyki");
              
            	Node node = nodeList.item(number);
                    
                    if (Node.ELEMENT_NODE == node.getNodeType())
                    {
                        Element element = (Element) node;
                         USERNAME= element.getElementsByTagName("User").item(0).getTextContent(); 
                         PASSWORD= element.getElementsByTagName("Password").item(0).getTextContent(); 
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
	
	void loadValue()
    {
    	
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    	//String path = "./BookCatalog.xml";
    	
    	try {
        
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        
            Document document = documentBuilder.parse(path);
                    
            NodeList nodeList = document.getElementsByTagName("Sprawdzenie");
            
            Node node = nodeList.item(0);
               
                
                if (Node.ELEMENT_NODE == node.getNodeType())
                {
                    Element element = (Element) node;
                    status= element.getElementsByTagName("Connect").item(0).getTextContent();
              }
           
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
    }
	
	void jSelectListener()
	{
		jSelectConfig.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	            
	     
		        if(jSelectConfig.getSelectedItem()=="NOWA")
		        {
		        	jtfURL.setVisible(true);
		    	    jtfUSER.setVisible(true);
		    	    jtfPASS.setVisible(true);
		    	    userLabel.setVisible(true);
		    	    passLabel.setVisible(true);
		    	    urlLabel.setVisible(true);
		    	    exLabel.setVisible(true);
		    	    exampleLabel.setVisible(true);
		    	    butDelete.setVisible(false);
		        }else 
		        {
		        	jtfURL.setVisible(false);
		    	    jtfUSER.setVisible(false);
		    	    jtfPASS.setVisible(false);
		    	    
		    	    userLabel.setVisible(false);
		    	    passLabel.setVisible(false);
		    	    urlLabel.setVisible(false);
		    	    exLabel.setVisible(false);
		    	    exampleLabel.setVisible(false);
		    	    
		    	    /*Set visible Delete button*/
		    	    if(jSelectConfig.getItemCount()>2)
		    	    butDelete.setVisible(true);
		    	    
		        }
		    }
		});
	}
	
	void backDisconn()
	{
		butBackward.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{	
				
				checkProfile();
				
				jSelectTable.setVisible(false);
				jSelectConfig.setVisible(true);
				connect.setVisible(true);
				select.setVisible(false);
		    	addNew.setVisible(false);
		    	remove.setVisible(false);
		    	butBackward.setVisible(false);
		    	statusLabel.setText("STATUS : DISCONNECTED");
		    	jSelectTable.removeAllItems();
				
				
			}});
	}
	
	

	private static void writeDocument 
	 	(Document document) 
		throws TransformerFactoryConfigurationError 
	    {
	        try {
	            
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(PATH);
	            tr.transform(source, result);
	            
	        } catch (TransformerException e) 
	        {
	            e.printStackTrace(System.out);
	        }
	    	
	    }
	
	
	void addNewConfig()
	 {
		 	
		    String a = jtfURL.getText();
			String b = jtfUSER.getText();
			String c = jtfPASS.getText();
		 
		 //java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
			java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
     	
     	try {
             
             DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
             Document document = documentBuilder.parse(path);
            
               NodeList languages2 = document.getElementsByTagName("Sprawdzenie");
 	           Element lang2 = (Element) languages2.item(0);
	           Node name = lang2.getElementsByTagName("Connect").item(0).getFirstChild();
	           name.setNodeValue("true");
             
             
             
             Node root = document.getDocumentElement();

 	        
 	        Element book = document.createElement("Charakterystyki");
 	         	        
 	        Element USER = document.createElement("User");
 	        USER.setTextContent(b);
 	        
 	        Element PASS = document.createElement("Password");
 	        PASS.setTextContent(c);
 	        
 	        Element URL = document.createElement("Url");
 	        URL.setTextContent(a);
 	        
 	        book.appendChild(USER);
 	        book.appendChild(PASS);
 	        book.appendChild(URL);
 	       
 	        root.appendChild(book);
	       

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(PATH);
            tr.transform(source, result);
            
            
     	}
     	catch(Exception e)
     	{
     		
     	}
	
	 }
	 
	
/*Change SAVE0 to SAVE2*/
	void checkpoint()
	{    java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//String path = "./BookCatalog.xml";
   		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
   		try {
            
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
 
            updateElementValueCheckp(document);
            
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
	}
	
	/*Change SAVE0 to SAVE2 */
	private  void updateElementValueCheckp(Document doc)
    {
        NodeList languages = doc.getElementsByTagName("Sprawdzenie");
        Element lang = null;
        
        lang = (Element) languages.item(0);
        Node name = lang.getElementsByTagName("Numer").item(0).getFirstChild();
        
        
        /*SAVE - "SAVE1" to XML after DODAJ*/
       int selected = selectIndex+1;
       /*0,1,2 -> 1,2,3 */
       String varCheckOne=String.valueOf(selected);
       String newValue = selectConfig+varCheckOne;
       System.out.println(newValue);
       /*Write SAVE1 to XML */
       name.setNodeValue(newValue);
        
        writeDocument(doc);
        
    } 

	/* Change value to false (in ".XML"), after close program*/
	void changeExit() 
	{	java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//String path = "./BookCatalog.xml";
   		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
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
	/*Change to false on exit*/
	private  void updateElementValue(Document doc)
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
                StreamResult result = new StreamResult(PATH);
                tr.transform(source, result);
                
            }catch(Exception e)
            {
            	
            }
    } 

	
	void loading()
	{
		
		butId1=new JButton("ADD");;
	    butId1.setBounds(440,90,150,40);//Polozenie na formie
	    butId1.setVisible(false);
	    frame.getContentPane().add(butId1);
	    styleOfBut(butId1);
	    
	    butId2 = new JButton("DEL");
	    butId2.setBounds(440,130,150,40);//Polozenie na formie
	    butId2.setVisible(false);
	    frame.getContentPane().add(butId2);
	    styleOfBut(butId2);
	    
	    butId3 = new JButton("SEARCH");
	    butId3.setBounds(310,10,100,40);//Polozenie na formie
	    butId3.setVisible(false);
	    frame.getContentPane().add(butId3);
	    styleOfBut(butId3);
	    
	    butId4 = new JButton("COFNIJ");
	    butId4.setBounds(20,10,120,50);//Polozenie na formie
	    butId4.setVisible(false);
	    frame.getContentPane().add(butId4);
	    styleOfBut(butId4);
	    
	    butId5 = new JButton("TRYB DODANIA");
	    butId5.setBounds(440,90,150,40);//Polozenie na formie
	    butId5.setVisible(false);
	    frame.getContentPane().add(butId5);
	    styleOfBut(butId5);
	    
	    butId6 = new JButton("TRYB USUWANIA");
	    butId6.setBounds(440,130,150,40);//Polozenie na formie
	    butId6.setVisible(false);
	    frame.getContentPane().add(butId6);
	    styleOfBut(butId6);
	    
	    butId7 = new JButton("TRYB SZUKANIA");
	    butId7.setBounds(440,170,150,40);//Polozenie na formie
	    butId7.setVisible(false);
	    frame.getContentPane().add(butId7);
	    styleOfBut(butId7);
	    
	    butId8 = new JButton("WYSZYSC");
	    butId8.setBounds(460,150,120,50);//Polozenie na formie
	    butId8.setVisible(false);
	    frame.getContentPane().add(butId8);
	    styleOfBut(butId8);
	    butId9 = new JButton("COFNIJ");
	    butId9.setBounds(20,30,120,50);//Polozenie na formie
	    butId9.setVisible(false);
	    frame.getContentPane().add(butId9);
	    styleOfBut(butId9);
	    
	   
	    jtfSearch = new JTextField();
	    jtfSearch.setBounds(440,70,150,20);
	    frame.getContentPane().add(jtfSearch);
	    jtfSearch.setVisible(false);
	    
	    jtf_ID2 = new JTextField();
	    jtf_ID2.setBounds(40,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID2);
	    jtf_ID2.setVisible(false);
	    
	    jtf_ID3 = new JTextField();
	    jtf_ID3.setBounds(140,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID3);
	    jtf_ID3.setVisible(false);
	    
	    jtf_ID4 = new JTextField();
	    jtf_ID4.setBounds(240,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID4);
	    jtf_ID4.setVisible(false);
	    
	    jtf_ID5 = new JTextField();
	    jtf_ID5.setBounds(340,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID5);
	    jtf_ID5.setVisible(false);
	    
	    
	    jtf_ID6 = new JTextField();
	    jtf_ID6.setBounds(440,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID6);
	    jtf_ID6.setVisible(false);
	    
	    
	    jtf_ID1 = new JTextField();
	    jtf_ID1.setBounds(10,60,30,20);//Polozenie na formie
	    frame.getContentPane().add(jtf_ID1);
	    jtf_ID1.setVisible(false);
	   
	    jtfDelete = new JTextField();
	    jtfDelete.setBounds(440,70,150,20);//Polozenie na formie
	    frame.getContentPane().add(jtfDelete);
	    jtfDelete.setVisible(false);
	   
		
		
	}

	void loadingTable() 
	{
		columnNames.clear();
		
		try{
	    	
  		
	  connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      statement = connection.createStatement();
      resultset = statement.executeQuery("select * from "+selectedTable.get(0).toString());
      
      ResultSetMetaData md = resultset.getMetaData();
      int columnCount = md.getColumnCount();
          
      Vector<String> columns = new Vector<String>(columnCount);
      
      /*store column names*/
      for(int i=1; i<=columnCount; i++)
          {columns.add(md.getColumnName(i));
          /* Add names of table to ArrayList 2 <!!!>*/
          columnNames.add(md.getColumnName(i));
          }
     
      Vector<Vector<String>> data = new Vector<Vector<String>>();
      Vector<String> row;

      /*store row data*/
      while(resultset.next())
      {
          row = new Vector<String>(columnCount);
          
                        
          	for(int t=1; t<=columnCount; t++)
          	{
              row.add(resultset.getString(t));
              
            }
          	
              data.add(row);
      }
          
    
    JTable jTable = new JTable(data, columns);
     
    for(int i=0;i<jTable.getColumnCount();i++)
    { jTable.getColumnModel().getColumn(i).setResizable(false);}
   
    jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
   	jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
   	jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
   	jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
   	jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
   	jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
   	
   	/*Disable swape*/
   	jTable.getTableHeader().setReorderingAllowed(false);
    /*Disable sorted up-down on click*/
   	jTable.getTableHeader().setEnabled(false); 			
      	
      
       TableRowSorter<TableModel> rowSorter
              = new TableRowSorter<>(jTable.getModel());

       jTable.setRowSorter(rowSorter);
      
       
       jtfSearch.getDocument().addDocumentListener(new DocumentListener(){

           @Override
           public void insertUpdate(DocumentEvent e) {
           	
               String text = jtfSearch.getText();

               if (text.trim().length() == 0) {
                   rowSorter.setRowFilter(null);
               } else {
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
           	
           	
               String text = jtfSearch.getText();

               if (text.trim().length() == 0) {
                   rowSorter.setRowFilter(null);
               } else {
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
           }

           @Override
           public void changedUpdate(DocumentEvent e) {
        	 /*To change body of generated methods, choose Tools | Templates.*/
        	   throw new UnsupportedOperationException("Not supported yet."); 
           }});
      
       
     /*Disable click*/
      jTable.setEnabled(false);
           
      
      scroller = new JScrollPane(jTable);
           
      frame.getContentPane().add(scroller);
      scroller.setBounds(000, 70, 400, 300);
      
		}
		catch(Exception exc)
		{
			
		}
	}
	
	
	
	void selectMethod()
	{
		select.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				butBackward.setVisible(false);
				selectedTable.add(jSelectTable.getSelectedItem().toString());
			
				jSelectTable.setVisible(false);
				select.setVisible(false);
				addNew.setVisible(false);
				remove.setVisible(false);
				
				if(jSelectTable.getSelectedItem().equals("Sprzedane"))
				{
				
				}else
				{
					butId5.setVisible(true);
					butId6.setVisible(true);
				}
				butId7.setVisible(true);
				butId4.setVisible(true);
				
				loadingTable();
				
			
			}});}
	
		
	void addMethod()
	{
			addNew.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
					if(selectIndex!=0)
					{
						checkpoint();
					}
				frame.setVisible(false);
				try {
					mojDodaj mojDodajOkienko = new mojDodaj();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
								
			}
		}
		
	);
	}
	
	void removeMethod()
	{
		remove.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				select.setVisible(false);
				addNew.setVisible(false);
				remove.setVisible(false);
				
				butBackward.setVisible(false);
				butId8.setVisible(true);
				butId9.setVisible(true);
				
				
			}
		});
	}

	/*Load number of XML SAVE0 --> 0 ang get from database to jSelectTable*/
	void connect2() 
	{
		readNumberMain();
		
		/*Connect to URL from SAVE0*/
		getItems();
			
		if(jSelectTable.getItemCount()!=0)
		{
			select.setVisible(true);
			addNew.setVisible(true);
			remove.setVisible(true);
			connect.setVisible(false);
			
			jtfURL.setVisible(false);
			jtfUSER.setVisible(false);
			jtfPASS.setVisible(false);
			exLabel.setVisible(false);
			statusLabel.setText("STATUS : "+"CONNECTED");
			
			butDelete.setVisible(false);
			
		}		
	}
	
	/*Method without input*/
	void readNumberMain()
	{
		getSettings(readNumber());
	}

	/*GET URL,USER,PASS from 0*/
	void getSettings(int numberOut)
	{
		
	    		int number = numberOut;
	        	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	    		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	        	try {
	              
	                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	                Document document = documentBuilder.parse(path);
	                NodeList nodeList = document.getElementsByTagName("Charakterystyki");
	              
	            	Node node = nodeList.item(number);
	                    
	                    if (Node.ELEMENT_NODE == node.getNodeType())
	                    {
	                        Element element = (Element) node; 
	                         USERNAME= element.getElementsByTagName("User").item(0).getTextContent(); 
	                         PASSWORD= element.getElementsByTagName("Password").item(0).getTextContent(); 
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
			java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
	    	//String path = "./BookCatalog.xml";
	    	try {
	            
	           DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	           Document document = documentBuilder.parse(path);
	            
	            NodeList nodeList = document.getElementsByTagName("Sprawdzenie");
	            Node node = nodeList.item(0);
	               
	              if (Node.ELEMENT_NODE == node.getNodeType())
	                {
	                    Element element = (Element) node;
	                    numberOfConfig= element.getElementsByTagName("Numer").item(0).getTextContent();
	                }
	               
	        
	        } catch (ParserConfigurationException ex) {
	            ex.printStackTrace(System.out);
	        } catch (SAXException ex) {
	            ex.printStackTrace(System.out);
	        } catch (IOException ex) {
	            ex.printStackTrace(System.out);
	        }
	    	 System.out.print(numberOfConfig);
	    	
	    	String del = numberOfConfig;
	    	del=del.substring(4, 5);
	    	
	    	int del2=Integer.valueOf(del);
	    	
	    	
	    	int del3=del2-1;	
	    	System.out.println("SAVE1 + "+del3);
	    	return del3;
	    	
	}
	
		
	 void setConnect2() // Zmienia w XML wartosc na true
	{
		
		        
		    
		 java.io.InputStream path4 = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		try {
            
            DocumentBuilder documentBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document2 = documentBuilder2.parse(path4);
           
            NodeList languages2 = document2.getElementsByTagName("Sprawdzenie");
	        
	        
	        Element lang2 = (Element) languages2.item(0);
	        Node name = lang2.getElementsByTagName("Connect").item(0).getFirstChild();
	           
	        name.setNodeValue("true");
	           
	        Transformer tr2 = TransformerFactory.newInstance().newTransformer();
	        DOMSource source2 = new DOMSource(document2);
	        StreamResult result2 = new StreamResult(PATH);
	        tr2.transform(source2, result2);	        	
	   }
    	catch(Exception e)
    	{
    	}
		
		   
		
		
	}
		          
	
	/*Delete SAVE0 from XML*/
	 void deleteConfigMain(int x)
	{java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//String path = "./BookCatalog.xml";
   		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
   		try {
            
   			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            deleteConfig(document,x);
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
	}
	
	private  void deleteConfig(Document doc,int index)
    {
		NodeList nodeList = doc.getElementsByTagName("Charakterystyki");
        Node node = nodeList.item(index);
            
        node.getParentNode().removeChild(node);
        writeDocument(doc);
              
    } 
	
	
	void butRemove()
	{
		butId8.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				jSelectTable.setVisible(false);
				butId8.setVisible(false);
				
				try
				{
					
					connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
					
					statement = connection.createStatement();
					
					String myTableName ="DROP TABLE "+jSelectTable.getSelectedItem().toString();
					
					System.out.print(jSelectTable.getSelectedItem().toString());
					
					statement.executeUpdate(myTableName);
					
					statusLabel.setText("STATUS : DEL COMPLETE");
					
				}
				catch (Exception exc)
				{
					exc.printStackTrace();
				}
			
			
			}
		});
	}
	void butRemoveBack()
	{
		butId9.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				statusLabel.setText("STATUS : CONNECTED");
				butBackward.setVisible(true);
				butId8.setVisible(false);
				butId9.setVisible(false);
				
				jSelectTable.removeAllItems();
				getItems();
				
				if(jSelectTable.getItemCount()==0)
					{
					select.setVisible(false);
					remove.setVisible(false);
					}else
					{
						select.setVisible(true);
						remove.setVisible(true);
					}
				
				jSelectTable.setVisible(true);
				addNew.setVisible(true);
			}
});
	}
    
	void butAddMode()
	{
		butId5.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true)
	    		{
	    			varCheckTwo=false;
	    			
	    			butId4.setVisible(false);
	    			butId5.setVisible(false);
	    			butId6.setVisible(false);
	    			butId7.setVisible(false);
	    			
	    			butId1.setVisible(true);
	    			
	    			 jtf_ID2.setVisible(true);
	    			 jtf_ID3.setVisible(true);
	    			 jtf_ID4.setVisible(true);
	    			 jtf_ID5.setVisible(true);
	    			 jtf_ID1.setVisible(true);
	    			 
	    			 jtf_ID6.setVisible(true);
	    			 
	    			 scroller.setBounds(000, 90, 400, 280);
	    			 
	    		}
	    		else {
	    			
	    			varCheckTwo=true;
	    			butId5.setText("TRYB DODANIA");
	    			butId1.setVisible(false);
	    			
	    		 jtf_ID2.setVisible(false);
	   			 jtf_ID3.setVisible(false);
	   			 jtf_ID4.setVisible(false);
	   			 jtf_ID5.setVisible(false);
	   			 jtf_ID1.setVisible(false);
	   			 
	   			 jtf_ID6.setVisible(false);
	   			
	   			 scroller.setBounds(000, 70, 400, 300);
	    			
	    		}
			}
		}
		
	);
	}
	void butAdd()
	{
		
		butId1.addActionListener(new ActionListener() 
		{
			@Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	 butId1.setVisible(false);
    			 jtf_ID1.setVisible(false);
            	 jtf_ID2.setVisible(false);
	   			 jtf_ID3.setVisible(false);
	   			 jtf_ID4.setVisible(false);
	   			 jtf_ID5.setVisible(false);
	   			 jtf_ID6.setVisible(false);
	   			
	   			 scroller.setBounds(000, 70, 400, 300);
            	 butId5.setText("TRYB DODANIA");
            	
            	
            	String getTable=selectedTable.get(0).toString();
        		String querySelect ="select * from "+getTable;
            	            	
            	String sql ="insert into "+selectedTable.get(0).toString()
				
						+"("+columnNames.get(0).toString()+','+columnNames.get(1).toString()+','+columnNames.get(2).toString()+','+columnNames.get(3).toString()+','+columnNames.get(4).toString()+','+columnNames.get(5).toString()+")"
				
						+"values ('"+jtf_ID1.getText()+"','"+jtf_ID2.getText()+"','"+jtf_ID3.getText()+"','"+jtf_ID4.getText().toString()+"','"+jtf_ID5.getText()+"','"+jtf_ID6.getText()+"')"; //dodanie numeru
            	
            	
            	
            	if(varCheckTwo==false)
            	{
            		varCheckTwo= true;
            		butId5.setVisible(true);
            		butId4.setVisible(true);
            		butId6.setVisible(true);
            		butId7.setVisible(true);
            		
            	if(jtf_ID1.getText().length()!=0){
            
            
            try{
            
               connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
               statement = connection.createStatement();
               statement.executeQuery(querySelect);
               statement.executeUpdate(sql);
            		
            		
            	}
            	catch (Exception exc)
            	{
            		exc.printStackTrace();
            	}
            	
            	 /* END loop if */ 
            	
            }	
            	
            	jtf_ID2.setText("");
        		jtf_ID3.setText("");
        		jtf_ID4.setText("");
        		jtf_ID5.setText("");
        		jtf_ID1.setText("");
        		jtf_ID6.setText("");
        		
        		frame.getContentPane().remove(scroller);
        		frame.repaint();
        		loadingTable();
            
            	}	
            }
        });

	}
	void butBackMenu()
	{
		butId4.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
	    		
	    
		/*Unvisible*/
		butId4.setVisible(false);
		butId5.setVisible(false);
		butId6.setVisible(false);
		butId7.setVisible(false);
		scroller.setVisible(false);
		
		/*Visible*/
		jSelectTable.setVisible(true);
		select.setVisible(true);
		addNew.setVisible(true);
		remove.setVisible(true);
		butBackward.setVisible(true);
		
		selectedTable.remove(0);
				
	}});
		
			
	}
	
	void butDelMode()
	{
		butId6.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true)
	    		{
	    			varCheckTwo=false;
	    			butId4.setVisible(false);
	    			
	    			butId5.setVisible(false);
	    			butId6.setVisible(false);
	    			butId7.setVisible(false);
	    			
	    			jtfDelete.setVisible(true);
	    			butId2.setVisible(true);
	    			jtfDelete.setVisible(true);
	    			 
	    			
	    		}
	    		else
	    		{
	    		
	    		varCheckTwo=true;
	    		jtfDelete.setVisible(false);
	    		
				butId6.setText("TRYB USUWANIA");				
				butId2.setVisible(false);
				
			}	
			}
			
		 });
	}

	void butDel()
	{
		butId2.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        
	        	
	        	String getTable=selectedTable.get(0).toString();
	        	
	        	if(varCheckTwo==false)
	        	{
	        		varCheckTwo=true;
                   
	        		butId5.setVisible(true);
              		butId4.setVisible(true);
            		butId6.setVisible(true);
            		butId7.setVisible(true);
	        	try
	    		{
	        		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);           		
                    statement = connection.createStatement();	    			
	    			String sql = "delete from "+getTable+" where id=' "+jtfDelete.getText()+" '";
	    			statement.executeUpdate(sql);
	    			frame.remove(scroller);
	            	frame.repaint();
	            	loadingTable();
	    			
	    		}
	    		catch (Exception exc)
	    		{
	    			exc.printStackTrace();
	    		}
	        	}
	        	
	        	butId2.setVisible(false);
	        	jtfDelete.setVisible(false);
	        	butId6.setText("TRYB USUWANIA");
	        	jtfDelete.setText("");
	        }
	    });
	}

	void butSearchMode()
	{
		
			butId7.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true)
	    		{
	    			butId4.setVisible(false);
	    			butId5.setVisible(false);
	    			butId6.setVisible(false);
	    		
	    			varCheckTwo=false;
	    			butId7.setText("GOTOWE");
	    			jtfSearch.setVisible(true);
	    			jtfSearch.setVisible(true);
	    			     
	    		}
	    		else
	    		{
	    		varCheckTwo=true;
	    		jtfSearch.setVisible(false);
	    		butId7.setText("TRYB SZUKANIA");
	    		
	    		if(jSelectTable.getSelectedItem().equals("sprzedane"))
	    		{
	    			
	    		}else
	    		{
	    		butId5.setVisible(true);
	    		butId6.setVisible(true);
	    		}	    		
	    		butId4.setVisible(true);
				jtfSearch.setText("");				
			}
			}
			
		 });
	}

	void przyciskMainDel()
	{
			butDelete.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
	    		if(jSelectConfig.getItemCount()>2)
	    		{int index = jSelectConfig.getSelectedIndex();
	    		deleteConfigMain(index);
	    		jSelectConfig.removeItemAt(index);
	    		}
	    		
			}
			});
	}
	
	
	public mojClass() throws IOException
	{
		/*Chek which window will be open*/
		init2();
		
		connectMethod();
		
		backDisconn();
		
		jSelectListener();
				
		selectMethod();
		
		removeMethod();
		
		addMethod();
		
		butRemove(); 
		
		butRemoveBack();
		
		butAddMode();
		
		butAdd();
		
		butBackMenu();
		 
		butDelMode();
		
		butDel();
		
		butSearchMode();
		
		przyciskMainDel();
		
	}


}
