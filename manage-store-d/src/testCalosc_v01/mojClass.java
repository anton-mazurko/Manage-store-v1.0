package testCalosc_v01;

import java.awt.Color;
import java.awt.Container;
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
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
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



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


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
	JComboBox<String> jSelectConfig =new JComboBox<String>();
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
    JComboBox<String> jComboBox2 =new JComboBox<String>();
    JButton butBackward =new JButton();
    JButton butDelete =new JButton();
    
    Color colorOfFrame = Color.decode("#1e8bc3"); // FRAME
    Color colorOfCombo = Color.decode("#d24d57"); // COMBOBOX
    Color colorOfBut = Color.decode("#e8e6ff"); // BUTTONS

    
    
  //String path = "./BookCatalog.xml";
    //java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    static String path2 = "src/BookCatalog.xml";
    
    boolean statusOfTables = false;    
    boolean thr = false;
    
    
    
	static void init()  // Poczatkwa metoda
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

	public static void main(String[] args) // Poczatkowa metoda
	{
		init();
	}
	
	
	
	void buttt(JButton button)
	{
	//Border emptyBorder = BorderFactory.createEmptyBorder();
	//button.setBorder(emptyBorder);
		
		//button.setContentAreaFilled(false);
	button.setBackground(colorOfBut);
	button.setBorder(new LineBorder(Color.GRAY));
	
   
	}
	
	void init2() throws IOException // UZYTE sprawdzenieProfile - dodane SAVE0,SAVE1 ,
				 // sprawdzenie - true false , connect2 - warunek przejscie do drugiego okienka
	{
		frame = new JFrame();
		//frame.setTitle("OKIENKO GLOWNE");
		frame.setTitle("MyDatabasePC v1.0");
		frame.setBounds(5, 5, 400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(colorOfFrame);
		//URL iconURL = getClass().getResource("/some/package/favicon.png");
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
		
		
		jSelectConfig.setBounds(20,110,120,50);
		frame.getContentPane().add(jSelectConfig);
		//jSelectConfig.setBackground(colorOfCombo);
		jSelectConfig.setVisible(false);
		
		jComboBox2.setBounds(50,140,120,50); // Contains profiles
		frame.getContentPane().add(jComboBox2);
		//jComboBox2.setBackground(colorOfCombo);
		
		sprawdzenieProfile();
		//jComboBox2.addItem("NOWA");
				
		select.setText("WYBIERZ");
		select.setBounds(260,30,120,50);
		frame.getContentPane().add(select);
		//select.setVisible(false);
		buttt(select);
		
		addNew.setText("DODAJ");
		addNew.setBounds(460,30,120,50);
		frame.getContentPane().add(addNew);
		//addNew.setVisible(false);
		buttt(addNew);
		
		remove.setText("USUN");
		remove.setBounds(460,150,120,50);
		frame.getContentPane().add(remove);
		//remove.setVisible(false);
		buttt(remove);
		
		
		connect.setText("CONNECT");
		connect.setBounds(460,200,120,50);
		frame.getContentPane().add(connect);
		buttt(connect);
		
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
	    jtfUSER.setBounds(50,50,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtfUSER);
	    jtfUSER.setVisible(false);
	    
	    jtfPASS = new JTextField();
	    jtfPASS.setBounds(50,70,100,20);//Polozenie na formie
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
		buttt(butBackward);
		
		butDelete.setText("DELETE");
		butDelete.setBounds(50,250,120,50);
		frame.getContentPane().add(butDelete);
		butDelete.setVisible(true);
		buttt(butDelete);
		
		
	    
	    sprawdzenie(); // Czy <Connect>true</Connect> czy FALSE
	    
	    
	    
	    if(status.contains("true"))
	    {
	    	connect2();
	    	select.setVisible(true);
	    	addNew.setVisible(true);
	    	remove.setVisible(true);
	    	jSelectConfig.setVisible(true);
	    	jComboBox2.setVisible(false);
	    	
	    	jtfURL.setVisible(false);
		    jtfUSER.setVisible(false);
		    jtfPASS.setVisible(false);
		    connect.setVisible(false);
		    butBackward.setVisible(true);
	    	//getItems();
	    }
	    else if(status.contains("false"))
	    {
	    
	    
	    select.setVisible(false);
    	addNew.setVisible(false);
    	remove.setVisible(false);
	    
	    }
	    
	    zaladowanie();
	    	
	}
	
	void connect() // Opis w srodku
	{
		statusOfTables=false;
		
		
		selectConfig = jComboBox2.getSelectedItem().toString(); // STRING SAVE1
		selectIndex=jComboBox2.getSelectedIndex();
		
		if(jComboBox2.getSelectedItem().toString().contains("NOWA"))
		{
				addNewBooo(); // Dodanie danych z NOWA do XML
		
		}
		////
		if(!jComboBox2.getSelectedItem().toString().contains("NOWA"))
		{
			setConnect2();
			selectSave(); // pobranie SAVE0 z pliku XML
		}
		
		getItems(); // połączenie z url z SAVE0 lub pobranie z jTextField
			
		//if(jSelectConfig.getItemCount()!=0 )
		if(!statusOfTables )
		{
			if(jSelectConfig.getItemCount()>0){
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
		
		//zmiana();
		
		
		
	}
	
	void getItems() // Ladowanie do jSelectConfig z bazy danych
	{
		if(jComboBox2.getItemCount()!=0)
		{if(jComboBox2.getSelectedItem().toString().contains("NOWA"))
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
			
			//ResultSet myRs = myStmt.executeQuery("select * from mojebaby");
			
			DatabaseMetaData md = connection.getMetaData();
			
			resultset =md.getTables(null,null,null,null);					
			
			while(resultset.next())
			{
			    jSelectConfig.addItem(resultset.getString(3));
			 
			}
			connection.close();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
			statusOfTables=true;
			
		}
		
	}
	
	
		
	void connectMetoda() // Miesci connect
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
				jComboBox2.setVisible(false);
				jSelectConfig.setVisible(true);
				
				butBackward.setVisible(true);
				}
				
			
				
			}});
	}
	
	void sprawdzenieProfile() // Laduje ilosc SAVE ow
    {
		
		jComboBox2.removeAllItems();
		
    	//ClassLoader cl = getClass().getClassLoader();
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    	//System.out.println(in);
    	try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            NodeList nodeList = document.getElementsByTagName("Charakterystyki"); // Gdzie to będzie szukane
           for(int i=0;i<nodeList.getLength();i++)
            {
        	   Node node = nodeList.item(i); // Pierwszy półka
               
                
                if (Node.ELEMENT_NODE == node.getNodeType()) // Jesli to jest półka to
                {
                    //Element element = (Element) node; // Element półki
                    //String urrl= element.getElementsByTagName("Url").item(0).getTextContent(); 
                    //dataProfile.add(urrl);
                    int l =i+1;
                	jComboBox2.addItem("SAVE"+l);
                    
                }
         
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	
    	/*for(int i=0;i<jComboBox2.getItemCount();i++)
    	if(!jComboBox2.getItemAt(i).toString().equals("NOWA"))
    	{
    		jComboBox2.addItem("NOWA");
    	}
    	
    	*/
    	jComboBox2.addItem("NOWA");
    }

	void selectSave() // Sciaganie URL,USER,PASS z jComboBox2
    {
    	int wybor = jComboBox2.getSelectedIndex(); // 0,1,2 ...
    		
    		System.out.println(wybor);
    		
    		pobierzUserPassUrl(wybor); // sciaganie danych z wybranego trybu
    		
    		System.out.println(USERNAME);
    		System.out.println(PASSWORD);
    		System.out.println(URL);
    	
    }
	
	void pobierzUserPassUrl(int numer2) // Jest w selectSave
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
	
	void sprawdzenie() // Laduje true czy false z XML
    {
    	//ClassLoader cl = getClass().getClassLoader();
    	//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    	//String path = "./BookCatalog.xml";
    	
    	//System.out.println(in);
    	try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
           Document document = documentBuilder.parse(path);
            //Document document = documentBuilder.parse(in);
            // Получаем корневой элемент
            //Node root = document.getDocumentElement();
            
            //System.out.println("List of books:");
            //System.out.println();
            
            // Просматриваем все подэлементы корневого - т.е. книги
            //NodeList books = root.getChildNodes();
            
            NodeList nodeList = document.getElementsByTagName("Sprawdzenie"); // Gdzie to będzie szukane
            
            Node node = nodeList.item(0); // Pierwszy półka
               
                //System.out.println("Gdzie : " + node.getNodeName());// Nazwa półki
                if (Node.ELEMENT_NODE == node.getNodeType()) // Jesli to jest półka to
                {
                    Element element = (Element) node; // Element półki
                    status= element.getElementsByTagName("Connect").item(0).getTextContent(); 
                    																// pobranie elementa półki
                    //System.out.println("Status : "+status);
              }
           //System.out.println("Status : "+status);
               if(status.contains("false"))
               {
            	   //statusLabel.setText("STATUS : false");
               }
               else if(status.contains("true"))
               {
            	   //statusLabel.setText("STATUS : true");
               }
            
           // updateElementValue(document);
            
            
            
            
            //addNewBook(document);
           //
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	//return status;
    }
	
	void savesListe() //jComboBoxLIstener
	{
		jComboBox2.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	            //Do Something
	     
		        if(jComboBox2.getSelectedItem()=="NOWA")
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
		    	    
		    	    // POJAWIENIE PRZYCISKA DELETE
		    	    if(jComboBox2.getItemCount()>2)
		    	    butDelete.setVisible(true);
		    	    
		        }
		    }
		});
	}
	
	void coffanie() // Powrot do poczatku miesci sprawdzenieProfile
	{
		butBackward.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{	
				
				sprawdzenieProfile();
				
				jSelectConfig.setVisible(false);
				jComboBox2.setVisible(true);
				connect.setVisible(true);
				select.setVisible(false);
		    	addNew.setVisible(false);
		    	remove.setVisible(false);
		    	butBackward.setVisible(false);
		    	statusLabel.setText("STATUS : DISCONNECTED");
		    	jSelectConfig.removeAllItems();
				
				
			}});
	}
	
	/*private static void addNewBook // Dodanie NOWA do XML
	 	(Document document,String url,String user,String pass) 
		throws TransformerFactoryConfigurationError, DOMException 
	 {
		 	
		 
	        // Получаем корневой элемент
	        Node root = document.getDocumentElement();

	        // Создаем новую книгу по элементам
	        // Сама книга <Book>
	        Element book = document.createElement("Charakterystyki");
	       
	        
	        Element USER = document.createElement("User");
	        USER.setTextContent(user);
	        
	        Element PASS = document.createElement("Password");
	        PASS.setTextContent(pass);
	        
	        Element URL = document.createElement("Url");
	        URL.setTextContent(url);
	        // <Author>
	      
	        
	        // Добавляем внутренние элементы книги в элемент <Book>
	        book.appendChild(USER);
	        book.appendChild(PASS);
	        book.appendChild(URL);
	       
	        // Добавляем книгу в корневой элемент
	        root.appendChild(book);
	       
	        // Записываем XML в файл
	        writeDocument(document);
	       
	    }*/

	private static void writeDocument // Zapisanie do XML
	 	(Document document) 
		throws TransformerFactoryConfigurationError 
	    {
	        try {
	            
	        	//TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            //Transformer transformer = transformerFactory.newTransformer();
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(path2);
	            tr.transform(source, result);
	            
	            
	                        
	        } catch (TransformerException e) 
	        {
	            e.printStackTrace(System.out);
	        }
	    	
	    }
	
	
	void addNewBooo() // Czytanie z jtfSearch i przekazanie do addNewBook
	 {
		 	
		//Thread thread3 = new Thread("New Thread") {
		 ///     public void run()
		 //     {
		
		    String a = jtfURL.getText();
			String b = jtfUSER.getText();
			String c = jtfPASS.getText();
		 
		 //java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
			java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
     	//System.out.println(in);
     	try {
             // Создается построитель документа
             DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
             Document document = documentBuilder.parse(path);
            
             //addNewBook(document,a,b,c);
            
 	       
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
 	       
 	     //TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Transformer transformer = transformerFactory.newTransformer();
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(path2);
            tr.transform(source, result);
            
            
            ;
            
            
            
            
         
     	}
     	catch(Exception e)
     	{
     		
     	}
		
	//}};
		//      thread3.start();
	 }
	 
	
	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------

	void zmiana2() // Zmiana SAVE0 na SAVE2
	{    java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//String path = "./BookCatalog.xml";
   		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
   		try {
            
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
 
            updateElementValue2(document);
            
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    	//return status;
	}
	
	private  void updateElementValue2(Document doc)  // Zmiana SAVE0 na SAVE2
    {
        NodeList languages = doc.getElementsByTagName("Sprawdzenie");
        Element lang = null;
        
        lang = (Element) languages.item(0);
        Node name = lang.getElementsByTagName("Numer").item(0).getFirstChild();
        //System.out.println(numberOfConfig);   
        //System.out.print("ZAPIS "+ selectConfig+"do XML");
        
        
        //NodeList nodeList = doc.getElementsByTagName("Sprawdzenie"); // Gdzie to będzie szukane
       // Node node = nodeList.item(0); // Pierwszy półka
           
         /* String mokk="";
            if (Node.ELEMENT_NODE == name.getNodeType()) // Jesli to jest półka to
            {
                Element element = (Element) name; // Element półki
                mokk= element.getElementsByTagName("Numer").item(0).getTextContent();
                System.out.print("Zczytana z XML "+mokk);
            }
        */
       
        
        //if(selectIndex!=0)
       // { //////////////
        // Zapis SAVE1 do XML po kliknieciu DODAJ
       int uuu = selectIndex+1;
       String varCheckOne=String.valueOf(uuu);// 0,1,2 --> 1,2,3
       String kkk = selectConfig+varCheckOne;
       System.out.println(kkk);
        name.setNodeValue(kkk); //ZAPIS SAVE1 do XML
        //name.setNodeValue(selectConfig);
       /// }else
      //  {
        	
       // }
        
        
        
        ////////////////
        writeDocument(doc);
            
        //}
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

	
	void zaladowanie() // ZALADOWANIE ADD,COFNIJ
	{
		
		butId1=new JButton("ADD");;
	    butId1.setBounds(440,90,150,40);//Polozenie na formie
	    butId1.setVisible(false);
	    frame.getContentPane().add(butId1);
	    buttt(butId1);
	    
	    butId2 = new JButton("DEL");
	    butId2.setBounds(440,130,150,40);//Polozenie na formie
	    butId2.setVisible(false);
	    frame.getContentPane().add(butId2);
	    buttt(butId2);
	    
	    //butId3 = new JButton("GET");
	    //butId3.setBounds(210,10,100,40);//Polozenie na formie
	    //butId3.setVisible(false);
	   // frame.getContentPane().add(butId3);
	    
	    butId3 = new JButton("SEARCH");
	    butId3.setBounds(310,10,100,40);//Polozenie na formie
	    butId3.setVisible(false);
	    frame.getContentPane().add(butId3);
	    buttt(butId3);
	    
	    butId4 = new JButton("COFNIJ");
	    butId4.setBounds(20,10,120,50);//Polozenie na formie
	    butId4.setVisible(false);
	    frame.getContentPane().add(butId4);
	    buttt(butId4);
	    
	    butId5 = new JButton("TRYB DODANIA");
	    butId5.setBounds(440,90,150,40);//Polozenie na formie
	    butId5.setVisible(false);
	    frame.getContentPane().add(butId5);
	    buttt(butId5);
	    
	    butId6 = new JButton("TRYB USUWANIA");
	    butId6.setBounds(440,130,150,40);//Polozenie na formie
	    butId6.setVisible(false);
	    frame.getContentPane().add(butId6);
	    buttt(butId6);
	    
	    butId7 = new JButton("TRYB SZUKANIA");
	    butId7.setBounds(440,170,150,40);//Polozenie na formie
	    butId7.setVisible(false);
	    frame.getContentPane().add(butId7);
	    buttt(butId7);
	    
	    butId8 = new JButton("WYSZYSC");
	    butId8.setBounds(460,150,120,50);//Polozenie na formie
	    butId8.setVisible(false);
	    frame.getContentPane().add(butId8);
	    buttt(butId8);
	    //460,150,120,50
	    butId9 = new JButton("COFNIJ");
	    butId9.setBounds(20,30,120,50);//Polozenie na formie
	    butId9.setVisible(false);
	    frame.getContentPane().add(butId9);
	    buttt(butId9);
	    
	   
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
	   // 440,90,150,40
		
		
	}

	void zaladowanie2() // ZALADOWANIE TABELI id,sklep,model 
	{
		columnNames.clear();
		
		try{
	    	
  		
	  connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      statement = connection.createStatement();
      resultset = statement.executeQuery("select * from "+selectedTable.get(0).toString());
      
      //System.out.println(resultset.toString());
      ResultSetMetaData md = resultset.getMetaData();
      int columnCount = md.getColumnCount();
      
      //System.out.println(columnCount);
    
      Vector columns = new Vector(columnCount);
      
      //store column names
      for(int i=1; i<=columnCount; i++)
          {columns.add(md.getColumnName(i));
          // Dodanie nazw tabeli do ArrayList 2 <!!!> WAZNE
          columnNames.add(md.getColumnName(i));
          }
    System.out.println(columnNames);
     
      Vector data = new Vector();
      Vector row;

      //store row data
      while(resultset.next())
      {
          row = new Vector(columnCount);
          
                        
          	for(int t=1; t<=columnCount; t++) // nie to
          	{
              row.add(resultset.getString(t));
              //System.out.println(row);
             
             }
          	 //System.out.println(row);
              data.add(row);
         
      }
          
    
    JTable jTable = new JTable(data, columns);
     
    for(int i=0;i<jTable.getColumnCount();i++)
    { jTable.getColumnModel().getColumn(i).setResizable(false);} // NIE ZMIENIA ROZMIAROW
   
    jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
   	jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
   	jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
   	jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
   	jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
   	jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
   	
   	//jTable.setAutoResizeMode(getWidth());
   	// ??? Czy nie trzeba dodac cos 
   	
   	jTable.getTableHeader().setReorderingAllowed(false); // PRZESUNIENIE FALSE
    jTable.getTableHeader().setEnabled(false); 			// NIE SORTUJE PO KLIKNIECIU
      	
      
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
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }

       });
      
       
     
      jTable.setEnabled(false); // NIE MOZNA KLIKAC
      //jTable.setRowSelectionAllowed(false);
      //jTable.setColumnSelectionAllowed(false);
      //jTable.setCellSelectionEnabled(false);
      //jTable.setDropTarget(null);
      
      
      scroller = new JScrollPane(jTable);
           
      frame.getContentPane().add(scroller);
      scroller.setBounds(000, 70, 400, 300);
      
		}
		catch(Exception exc)
		{
			
		}
		
		
		}
	
	
	
	void wybierzMetoda() // Zaladowanie buttonow i tabeli
	{
	
		
		select.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				//zaladowanie();
				butBackward.setVisible(false);
				
				selectedTable.add(jSelectConfig.getSelectedItem().toString());
			
				jSelectConfig.setVisible(false);
				select.setVisible(false);
				addNew.setVisible(false);
				remove.setVisible(false);
				
				//System.out.println(jSelectConfig.getSelectedItem());
				if(jSelectConfig.getSelectedItem().equals("Sprzedane"))
				{
					
				//butId6.setVisible(true);
				
				}else
				{
					butId5.setVisible(true);
					butId6.setVisible(true);
				}
				butId7.setVisible(true);
				butId4.setVisible(true);
				
			
				zaladowanie2(); // dodanie tabeli do okienka i td
				
			
			}});}
	
		
	void dodajMetoda() // Przejscie do okienka miesci zmiana2
	{
			addNew.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
					if(selectIndex!=0)
					{
						zmiana2(); // selectConfig juz jest tam umieszczona selectConfig to zczytane z ComboBox
					}
				frame.setVisible(false);
				try {
					mojDodaj mojDodajOkienko = new mojDodaj();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
								
			}
		}
		
	);
	}
	
	void wyczyscMetoda()
	{
		remove.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				//jSelectConfig.setVisible(false);
				select.setVisible(false);
				addNew.setVisible(false);
				remove.setVisible(false);
				
				butBackward.setVisible(false);
				butId8.setVisible(true);
				butId9.setVisible(true);
				
				
			}
		});
	}

	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
	void connect2() // Laduje numer z XML SAVE0 --> 0 i pobiera z bazy do jSelectConfig
	{
		czytajNumer2();
		
		getItems(); // połączenie z url z SAVE0 lub pobranie z jTextField
			
		if(jSelectConfig.getItemCount()!=0)
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
		
		//zmiana();
		
	}
	
	
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
	    	//System.out.println(del);
	    	int del2=Integer.valueOf(del);
	    	
	    	//if(numberOfConfig=="NOWA")
	    	//{
	    	//	System.out.println("NOWA + "+del2);
	    	//   return del2;
	    	
	    	//}else
	    	//{	
	    	int delll=del2-1;	
	    	System.out.println("SAVE1 + "+delll);
	    	return delll;
	    	//}
	}
	
	/*void setConnect(Document doc) // Zmienia w XML wartosc na true
	{
		 
	}*/
	
	 void setConnect2() // Zmienia w XML wartosc na true
	{
		
		        
		    
		 java.io.InputStream path4 = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		try {
            // Создается построитель документа
            DocumentBuilder documentBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document2 = documentBuilder2.parse(path4);
           
           ////////////////////////////////////////
            NodeList languages2 = document2.getElementsByTagName("Sprawdzenie");
	        
	        
	           Element lang2 = (Element) languages2.item(0);
	           Node name = lang2.getElementsByTagName("Connect").item(0).getFirstChild();
	           
	           name.setNodeValue("true");
	           
	           Transformer tr2 = TransformerFactory.newInstance().newTransformer();
	            DOMSource source2 = new DOMSource(document2);
	            StreamResult result2 = new StreamResult(path2);
	            tr2.transform(source2, result2);
	            
	           
	            
	        	
	   }
    	catch(Exception e)
    	{
    	}
		
		   
		
		
	}
		          
	
	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
	
	void zmianaSAVE(int x) // USUNIECIE SAVE0 z XML
	{java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
		//String path = "./BookCatalog.xml";
   		//java.io.InputStream in = mojClass.class.getResourceAsStream("/BookCatalog.xml");
   		try {
            
   			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            zmianaSAVE2(document,x);
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
   		
    	
	}
	
	private  void zmianaSAVE2(Document doc,int i) // USUNIECIE SAVE0 z XML
    {
		NodeList nodeList = doc.getElementsByTagName("Charakterystyki"); // Gdzie to będzie szukane
        
    	Node node = nodeList.item(i); // Pierwszy półka
            
           /* if (Node.ELEMENT_NODE == node.getNodeType()) // Jesli to jest półka to
            {
                Element element = (Element) node; // Element półki
                 USERNAME= element.getElementsByTagName("User").item(0).getTextContent(); 
                 PASSWORD= element.getElementsByTagName("Password").item(0).getTextContent(); 
                 URL = element.getElementsByTagName("Url").item(0).getTextContent(); 
            }*/
    	   node.getParentNode().removeChild(node);
           writeDocument(doc);
            
        //}
    } 
	
	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
	
	// Realizowac WYSZYSC 
	
	void przycisk8() // remove
	{
		butId8.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				jSelectConfig.setVisible(false);
				butId8.setVisible(false);
				
				try
				{
					
					connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
					
					statement = connection.createStatement();
					
					String myTableName ="DROP TABLE "+jSelectConfig.getSelectedItem().toString();
					
					System.out.print(jSelectConfig.getSelectedItem().toString());
					
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
	void przycisk9() // COFNIJ - po wyczyszczeniu
	{
		butId9.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				statusLabel.setText("STATUS : CONNECTED");
				butBackward.setVisible(true);
				butId8.setVisible(false);
				butId9.setVisible(false);
				
				jSelectConfig.removeAllItems();
				getItems();
				
				if(jSelectConfig.getItemCount()==0)
					{
					select.setVisible(false);
					remove.setVisible(false);
					}else
					{
						select.setVisible(true);
						remove.setVisible(true);
					}
				
				jSelectConfig.setVisible(true);
				addNew.setVisible(true);
				
				
			}
});
	}
    
	void przycisk5() // TRYB DODANIA
	{
		// TRYB DODANIA
		
		butId5.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			varCheckTwo=false;
	    			//butId5.setText("GOTOWE");
	    			butId4.setVisible(false);
	    			butId5.setVisible(false);
	    			butId6.setVisible(false);
	    			butId7.setVisible(false);
	    			//butId5.setVisible(false);
	    			
	    			//butId9.setVisible(false);
	    			
	    			butId1.setVisible(true);
	    			
	    			 jtf_ID2.setVisible(true);
	    			 jtf_ID3.setVisible(true);
	    			 jtf_ID4.setVisible(true);
	    			 jtf_ID5.setVisible(true);
	    			 jtf_ID1.setVisible(true);
	    			 
	    			 jtf_ID6.setVisible(true);
	    			 
	    			 scroller.setBounds(000, 90, 400, 280);
	    			
	    			 //Dodanie do JDBC
	    			 
	    			 
	    			 
	    		}
	    		else // powinny sie zniknac przyciski i zmienic polozenie tabela
	    		
	    		{	
	    			
	    			varCheckTwo=true;
	    			butId5.setText("TRYB DODANIA");
	    			
	    			//butId5.setVisible(true);
	    			
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
	void przycisk1() // PRZYCISK - ADD
	{
		// DODAJ
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
        		String razem ="select * from "+getTable;
            	String razem2 = "insert into "+getTable;
            	
            	String sql ="insert into "+selectedTable.get(0).toString() //zmienic to co w nawiasach
						//+"(id,brand,model,cena,sklep)"
						+"("+columnNames.get(0).toString()+','+columnNames.get(1).toString()+','+columnNames.get(2).toString()+','+columnNames.get(3).toString()+','+columnNames.get(4).toString()+','+columnNames.get(5).toString()+")"
						//+varCheckOne
						+"values ('"+jtf_ID1.getText()+"','"+jtf_ID2.getText()+"','"+jtf_ID3.getText()+"','"+jtf_ID4.getText().toString()+"','"+jtf_ID5.getText()+"','"+jtf_ID6.getText()+"')"; //dodanie numeru
            	
            	//System.out.println("("+columnNames.get(0).toString()+','+columnNames.get(1).toString()+','+columnNames.get(2).toString()+','+columnNames.get(3).toString()+','+columnNames.get(4).toString()+")");
        		
            	///System.out.println("values ('"+jtf_ID1.getText().toString()+"','"+jtf_ID2.getText().toString()+"','"+jtf_ID3.getText().toString()+"','"+jtf_ID4.getText().toString()+"','"+jtf_ID5.getText().toString()+"')"); //dodanie numeru
            	
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
               statement.executeQuery(razem);// zmienic to co w nawiasach
               statement.executeUpdate(sql);
            		
            		
            	}
            	catch (Exception exc)
            	{
            		exc.printStackTrace();
            	}
            	
            	 //koniec if
            	
            }	
            	//--------------------------------------------
            	//System.out.println("ADD complete");
            	jtf_ID2.setText("");
        		jtf_ID3.setText("");
        		jtf_ID4.setText("");
        		jtf_ID5.setText("");
        		jtf_ID1.setText("");
        		jtf_ID6.setText("");
        		
        		frame.getContentPane().remove(scroller);
        		frame.repaint();
        		zaladowanie2();
            
            	}	
            }
        });

	}
	void przycisk4() // COFNIJ - do menu
	{
		
		// COFNIJ
		
		butId4.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
	    		
	    
		// Unvisible
		butId4.setVisible(false);
		butId5.setVisible(false);
		butId6.setVisible(false);
		butId7.setVisible(false);
		scroller.setVisible(false);
		
		// Visible
		jSelectConfig.setVisible(true);
		select.setVisible(true);
		addNew.setVisible(true);
		remove.setVisible(true);
		butBackward.setVisible(true);
		
		
		selectedTable.remove(0); // WYCZYSZCZENIE z listy, nazwy tabeli z JDBC - new1
		
		
		
		//for(int i=0;i<selectedTable.size();i++)
		//{
		//	selectedTable.remove(i);
		//}
			
		
		
		
			}});
		
			
	}
	
	void przycisk6() // TRYB DELETE
	{
		//TRYB DELETE
		
		butId6.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			varCheckTwo=false;
	    			//butId6.setText("GOTOWE");
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

	void przycisk2() // DEL
	{
		//DELETE
		
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
	        		//c.remove(scroller);
	    			//Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false","root","1029384756qaz");
	    			
	    			//Statement myStmt = myConn.createStatement();
	        		//Connection connection ;
         		     //Statement statement ;
         		     //ResultSet resultset ;
      		
      		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
             		
             				
             statement = connection.createStatement();
	    			
	    			String sql = "delete from "+getTable+" where id=' "+jtfDelete.getText()+" '"; // zmienic 
	    			
	    			statement.executeUpdate(sql);
	    						
	    			System.out.println("DELETE complete");
	    			
	    			
	    			
	    			frame.remove(scroller);
	            	frame.repaint();
	            	zaladowanie2();
	    			
	    			
	    			
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

	void przycisk7() // TRYB SZUKAJ
	{
		//TRYB SZUKAJ
		
			butId7.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(varCheckTwo==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			
	    			butId4.setVisible(false);
	    			butId5.setVisible(false);
	    			butId6.setVisible(false);
	    			//butId7.setVisible(false);
	    			
	    			varCheckTwo=false;
	    			butId7.setText("GOTOWE");
	    			jtfSearch.setVisible(true);
	    			
	    			//butId4.setVisible(true);
	    			
	    			
	    			jtfSearch.setVisible(true);
	    			
	    			     
	    		}
	    		else
	    		{
	    		
	    		varCheckTwo=true;
	    		jtfSearch.setVisible(false);
	    		butId7.setText("TRYB SZUKANIA");
				
	    		
	    		if(jSelectConfig.getSelectedItem().equals("sprzedane"))
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
	    		if(jComboBox2.getItemCount()>2)
	    		{int rr = jComboBox2.getSelectedIndex(); // SAVE0 --> 0
	    		zmianaSAVE(rr); // USUWA SAVE0 z XML
	    		//sprawdzenieProfile(); // RESET ComboBox
	    		jComboBox2.removeItemAt(rr);
	    		}
	    		
			}
			});
	}
	
	//------------------------------------------------------------
	// PRZED KLASA
	//------------------------------------------------------------
	public mojClass() throws IOException
	{
		init2(); // inicjalizacja i sprawdzenie jakie okienko otworzyc
		connectMetoda(); // przycisk Connect
		coffanie(); // przycisk cofanie
		savesListe(); // Combobox listener
		
	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
		
		wybierzMetoda(); // select - przejscie
		wyczyscMetoda(); // remove - przejscie
		dodajMetoda();  // DODAJ - przejscie
		
		przycisk8(); // remove - usuwa z JDBC 
		przycisk9(); // COFNIJ - po wyczyszczeniu
		
		przycisk5(); // DODAJ
		przycisk1(); // Przycisk - ADD
		
		przycisk4(); // COFNIJ - powrow do menu
		 
		przycisk6(); // TRYB DELETE
		przycisk2(); // DEL
		
		przycisk7(); // TRYB SZUKAJ
		
		przyciskMainDel();
		
	}


}
