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


public class mojClass extends JFrame {
	
	//String url = "jdbc:mysql://localhost:3306/zegarki_magchas?autoReconnect=true&useSSL=false";
	//String user ="root";
	//String pass = "1029384756qaz";
	//private static final String url = "jdbc:mysql://31.220.105.211:3306/needtest_1";
	//private static final String user ="needtest_1";
	//private static final String pass = "trenerovochka";
	
	//"jdbc:mysql://localhost:3306/zegarki_magchas?autoReconnect=true&useSSL=false","root","1029384756qaz"
	
	int wybranyIndex = 0;
	
	Connection con;
	Statement requete;
	ResultSet rs;
	
	public boolean connectBool = false;
	
	public String status = "false";
	public String urll = "";
	
	ArrayList<String> daneProfile = new ArrayList<String>();
	public String downUSER="";
	public String downPASS="";
	public String downURL="";
	public String wybranaKonfig = "";
	
	public String numerKonfig="";
	
	public boolean zmiennaNiema=false; 
	
	
	///// WSZYSTKIE ELEMENTY /////
	JFrame frame;
	JComboBox jComboBox1 =new JComboBox();
	JButton wybierz = new JButton() ;
	JButton wyczysc = new JButton() ;
	JButton dodajNowa = new JButton();
	JButton connect = new JButton();
	//Object wybranaBaza;	
	ArrayList<String> mojList = new ArrayList();
	ArrayList mojList2 = new ArrayList();
	JScrollPane scroller = new JScrollPane();
	
	
	boolean aaa = true;
    boolean bbb= true;

    JButton button1,button2,button3,button4, button5,button6,button7,button8,button9;
    JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7;
    JTextField jtf52;
    
    JTextField jtfURL =new JTextField();
    JTextField jtfUSER =new JTextField();
    JTextField jtfPASS =new JTextField();

    JLabel st;
    
    JLabel userr;
    JLabel passs;
    JLabel urlll;
    JLabel ex;
    JTextField ex2;
    JComboBox<String> jComboBox2 =new JComboBox<String>();
    JButton coffanie =new JButton();
    JButton dellete =new JButton();
    
    Color myColor = Color.decode("#1e8bc3"); // FRAME
    Color myColor3 = Color.decode("#d24d57"); // COMBOBOX
    Color myColor2 = Color.decode("#e8e6ff"); // BUTTONS

    
    
  //String path = "./BookCatalog.xml";
    //java.io.InputStream path = mojClass.class.getResourceAsStream("/BookCatalog.xml");
    static String path2 = "src/BookCatalog.xml";
    
    boolean niema = false;
    
    boolean thr = false;
    
    
    /*
     * 1 - uwidocznienie mojClass
     * 2 - zaladowanie na okienko przyciskow i inne
     * 3 - zaladowanie do comboBox baz danych
     * 4 - dodanie dzialan do przyciska 1,2
     * 
     * 
     * Rozpoczecie programu
     */
    
   // COFNIJ PO DODANIU DZIALA - tylko trzeba klikać na XML
   //---------------------------------------------------------
    
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
	button.setBackground(myColor2);
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
		frame.getContentPane().setBackground(myColor);
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
		
		
		jComboBox1.setBounds(20,110,120,50);
		frame.getContentPane().add(jComboBox1);
		//jComboBox1.setBackground(myColor3);
		jComboBox1.setVisible(false);
		
		jComboBox2.setBounds(50,140,120,50); // Contains profiles
		frame.getContentPane().add(jComboBox2);
		//jComboBox2.setBackground(myColor3);
		
		sprawdzenieProfile();
		//jComboBox2.addItem("NOWA");
				
		wybierz.setText("WYBIERZ");
		wybierz.setBounds(260,30,120,50);
		frame.getContentPane().add(wybierz);
		//wybierz.setVisible(false);
		buttt(wybierz);
		
		dodajNowa.setText("DODAJ");
		dodajNowa.setBounds(460,30,120,50);
		frame.getContentPane().add(dodajNowa);
		//dodajNowa.setVisible(false);
		buttt(dodajNowa);
		
		wyczysc.setText("USUN");
		wyczysc.setBounds(460,150,120,50);
		frame.getContentPane().add(wyczysc);
		//wyczysc.setVisible(false);
		buttt(wyczysc);
		
		
		connect.setText("CONNECT");
		connect.setBounds(460,200,120,50);
		frame.getContentPane().add(connect);
		buttt(connect);
		
		userr = new JLabel();
	    userr.setBounds(10,50,50,20);
	    frame.getContentPane().add(userr);
	    userr.setVisible(false);
	    userr.setText("USER");
	    userr.setForeground(myColor2);
	    
	    passs = new JLabel();
	    passs.setBounds(10,70,50,20);
	    frame.getContentPane().add(passs);
	    passs.setVisible(false);
	    passs.setText("PASS");
	    passs.setForeground(myColor2);
	    
	    urlll = new JLabel();
	    urlll.setBounds(10,90,50,20);
	    frame.getContentPane().add(urlll);
	    urlll.setVisible(false);
	    urlll.setText("URL");
	    urlll.setForeground(myColor2);
	    
	    ex = new JLabel();
	    ex.setBounds(10,110,30,20);
	    frame.getContentPane().add(ex);
	    ex.setVisible(false);
	    ex.setText("EX :");
	    ex.setForeground(myColor2);
	    
	    ex2 = new JTextField();
	    ex2.setBounds(50,110,500,20);
	    frame.getContentPane().add(ex2);
	    ex2.setVisible(false);
	    ex2.setEditable(false);
	    ex2.setText("jdbc:mysql://localhost:3306/zegarki_magazyn?autoReconnect=true&amp;useSSL=false");
	    ex2.setForeground(myColor2);
	    ex2.setBorder(null);
	    ex2.setBackground(myColor);
	    Font font1 = new Font("Calibri", Font.BOLD, 13);
	    ex2.setFont(font1);
	    
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
		
	    st = new JLabel();
	    st.setBounds(420,300,200,20);
	    frame.getContentPane().add(st);
	    st.setText("STATUS : " + "DISCONNECTED");
	    st.setForeground(myColor2);
	    
	    coffanie.setText("COFNIJ");
		coffanie.setBounds(20,30,120,50);
		frame.getContentPane().add(coffanie);
		coffanie.setVisible(false);
		buttt(coffanie);
		
		dellete.setText("DELETE");
		dellete.setBounds(50,250,120,50);
		frame.getContentPane().add(dellete);
		dellete.setVisible(true);
		buttt(dellete);
		
		
	    
	    sprawdzenie(); // Czy <Connect>true</Connect> czy FALSE
	    
	    
	    
	    if(status.contains("true"))
	    {
	    	connect2();
	    	wybierz.setVisible(true);
	    	dodajNowa.setVisible(true);
	    	wyczysc.setVisible(true);
	    	jComboBox1.setVisible(true);
	    	jComboBox2.setVisible(false);
	    	
	    	jtfURL.setVisible(false);
		    jtfUSER.setVisible(false);
		    jtfPASS.setVisible(false);
		    connect.setVisible(false);
		    coffanie.setVisible(true);
	    	//getItems();
	    }
	    else if(status.contains("false"))
	    {
	    
	    
	    wybierz.setVisible(false);
    	dodajNowa.setVisible(false);
    	wyczysc.setVisible(false);
	    
	    }
	    
	    zaladowanie();
	    	
	}
	
	void connect() // Opis w srodku
	{
		niema=false;
		
		
		wybranaKonfig = jComboBox2.getSelectedItem().toString(); // STRING SAVE1
		wybranyIndex=jComboBox2.getSelectedIndex();
		
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
			
		//if(jComboBox1.getItemCount()!=0 )
		if(!niema )
		{
			if(jComboBox1.getItemCount()>0){
			wybierz.setVisible(true);
			wyczysc.setVisible(true);}
			
			dodajNowa.setVisible(true);
			
			connect.setVisible(false);
			jtfURL.setVisible(false);
			jtfUSER.setVisible(false);
			jtfPASS.setVisible(false);
			
			userr.setVisible(false);
			passs.setVisible(false);
			urlll.setVisible(false);
			ex.setVisible(false);
			ex2.setVisible(false);
			st.setText("STATUS : "+"CONNECTED");
			
			dellete.setVisible(false);
		}
		
		//zmiana();
		
		
		
	}
	
	void getItems() // Ladowanie do jComboBox1 z bazy danych
	{
		if(jComboBox2.getItemCount()!=0)
		{if(jComboBox2.getSelectedItem().toString().contains("NOWA"))
		{
			downURL = jtfURL.getText();
			downUSER = jtfUSER.getText();
			downPASS = jtfPASS.getText();
		}
		}
		
		try
		{
			
			con = DriverManager.getConnection(downURL,downUSER,downPASS);
			
			requete = con.createStatement();
			
			//ResultSet myRs = myStmt.executeQuery("select * from mojebaby");
			
			DatabaseMetaData md = con.getMetaData();
			
			rs =md.getTables(null,null,null,null);					
			
			while(rs.next())
			{
			    jComboBox1.addItem(rs.getString(3));
			 
			}
			con.close();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
			niema=true;
			
		}
		
	}
	
	
		
	void connectMetoda() // Miesci connect
	{
		connect.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{	
				Thread t1 = new Thread("New Thread") {public void run(){
					      
					    
							//setConnect2();
							connect(); 
				      }};
				      
				      Thread t2 = new Thread("New Thread2") {public void run(){
					      
				    	  	

				      }};
				      
				      t1.start();
				      try
				      {
				    	 t1.join();
				      }catch(Exception ee)
				      {
				    	  
				      
				      }
				 
					
					System.out.println(thr);
					
				    
				
				
				if(!st.getText().toString().contains("DISCONNECTED"))
				{
				jComboBox2.setVisible(false);
				jComboBox1.setVisible(true);
				
				coffanie.setVisible(true);
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
                    //daneProfile.add(urrl);
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
    		
    		System.out.println(downUSER);
    		System.out.println(downPASS);
    		System.out.println(downURL);
    	
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
            	   //st.setText("STATUS : false");
               }
               else if(status.contains("true"))
               {
            	   //st.setText("STATUS : true");
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
		    	    userr.setVisible(true);
		    	    passs.setVisible(true);
		    	    urlll.setVisible(true);
		    	    ex.setVisible(true);
		    	    ex2.setVisible(true);
		    	    dellete.setVisible(false);
		        }else 
		        {
		        	jtfURL.setVisible(false);
		    	    jtfUSER.setVisible(false);
		    	    jtfPASS.setVisible(false);
		    	    
		    	    userr.setVisible(false);
		    	    passs.setVisible(false);
		    	    urlll.setVisible(false);
		    	    ex.setVisible(false);
		    	    ex2.setVisible(false);
		    	    
		    	    // POJAWIENIE PRZYCISKA DELETE
		    	    if(jComboBox2.getItemCount()>2)
		    	    dellete.setVisible(true);
		    	    
		        }
		    }
		});
	}
	
	void coffanie() // Powrot do poczatku miesci sprawdzenieProfile
	{
		coffanie.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e)
			{	
				
				sprawdzenieProfile();
				
				jComboBox1.setVisible(false);
				jComboBox2.setVisible(true);
				connect.setVisible(true);
				wybierz.setVisible(false);
		    	dodajNowa.setVisible(false);
		    	wyczysc.setVisible(false);
		    	coffanie.setVisible(false);
		    	st.setText("STATUS : DISCONNECTED");
		    	jComboBox1.removeAllItems();
				
				
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
	
	
	void addNewBooo() // Czytanie z jtf i przekazanie do addNewBook
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
        //System.out.println(numerKonfig);   
        //System.out.print("ZAPIS "+ wybranaKonfig+"do XML");
        
        
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
       
        
        //if(wybranyIndex!=0)
       // { //////////////
        // Zapis SAVE1 do XML po kliknieciu DODAJ
       int uuu = wybranyIndex+1;
       String aaa=String.valueOf(uuu);// 0,1,2 --> 1,2,3
       String kkk = wybranaKonfig+aaa;
       System.out.println(kkk);
        name.setNodeValue(kkk); //ZAPIS SAVE1 do XML
        //name.setNodeValue(wybranaKonfig);
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
		
		button1=new JButton("ADD");;
	    button1.setBounds(440,90,150,40);//Polozenie na formie
	    button1.setVisible(false);
	    frame.getContentPane().add(button1);
	    buttt(button1);
	    
	    button2 = new JButton("DEL");
	    button2.setBounds(440,130,150,40);//Polozenie na formie
	    button2.setVisible(false);
	    frame.getContentPane().add(button2);
	    buttt(button2);
	    
	    //button3 = new JButton("GET");
	    //button3.setBounds(210,10,100,40);//Polozenie na formie
	    //button3.setVisible(false);
	   // frame.getContentPane().add(button3);
	    
	    button3 = new JButton("SEARCH");
	    button3.setBounds(310,10,100,40);//Polozenie na formie
	    button3.setVisible(false);
	    frame.getContentPane().add(button3);
	    buttt(button3);
	    
	    button4 = new JButton("COFNIJ");
	    button4.setBounds(20,10,120,50);//Polozenie na formie
	    button4.setVisible(false);
	    frame.getContentPane().add(button4);
	    buttt(button4);
	    
	    button5 = new JButton("TRYB DODANIA");
	    button5.setBounds(440,90,150,40);//Polozenie na formie
	    button5.setVisible(false);
	    frame.getContentPane().add(button5);
	    buttt(button5);
	    
	    button6 = new JButton("TRYB USUWANIA");
	    button6.setBounds(440,130,150,40);//Polozenie na formie
	    button6.setVisible(false);
	    frame.getContentPane().add(button6);
	    buttt(button6);
	    
	    button7 = new JButton("TRYB SZUKANIA");
	    button7.setBounds(440,170,150,40);//Polozenie na formie
	    button7.setVisible(false);
	    frame.getContentPane().add(button7);
	    buttt(button7);
	    
	    button8 = new JButton("WYSZYSC");
	    button8.setBounds(460,150,120,50);//Polozenie na formie
	    button8.setVisible(false);
	    frame.getContentPane().add(button8);
	    buttt(button8);
	    //460,150,120,50
	    button9 = new JButton("COFNIJ");
	    button9.setBounds(20,30,120,50);//Polozenie na formie
	    button9.setVisible(false);
	    frame.getContentPane().add(button9);
	    buttt(button9);
	    
	   
	    jtf = new JTextField();
	    jtf.setBounds(440,70,150,20);
	    frame.getContentPane().add(jtf);
	    jtf.setVisible(false);
	    
	    jtf2 = new JTextField();
	    jtf2.setBounds(40,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf2);
	    jtf2.setVisible(false);
	    
	    jtf3 = new JTextField();
	    jtf3.setBounds(140,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf3);
	    jtf3.setVisible(false);
	    
	    jtf4 = new JTextField();
	    jtf4.setBounds(240,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf4);
	    jtf4.setVisible(false);
	    
	    jtf5 = new JTextField();
	    jtf5.setBounds(340,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf5);
	    jtf5.setVisible(false);
	    
	    
	    jtf52 = new JTextField();
	    jtf52.setBounds(440,60,100,20);//Polozenie na formie
	    frame.getContentPane().add(jtf52);
	    jtf52.setVisible(false);
	    
	    
	    jtf6 = new JTextField();
	    jtf6.setBounds(10,60,30,20);//Polozenie na formie
	    frame.getContentPane().add(jtf6);
	    jtf6.setVisible(false);
	   
	    jtf7 = new JTextField();
	    jtf7.setBounds(440,70,150,20);//Polozenie na formie
	    frame.getContentPane().add(jtf7);
	    jtf7.setVisible(false);
	   // 440,90,150,40
		
		
	}

	void zaladowanie2() // ZALADOWANIE TABELI id,sklep,model 
	{
		mojList2.clear();
		
		try{
	    	
  		
	  con = DriverManager.getConnection(downURL,downUSER,downPASS);
      requete = con.createStatement();
      rs = requete.executeQuery("select * from "+mojList.get(0).toString());
      
      //System.out.println(rs.toString());
      ResultSetMetaData md = rs.getMetaData();
      int columnCount = md.getColumnCount();
      
      //System.out.println(columnCount);
    
      Vector columns = new Vector(columnCount);
      
      //store column names
      for(int i=1; i<=columnCount; i++)
          {columns.add(md.getColumnName(i));
          // Dodanie nazw tabeli do ArrayList 2 <!!!> WAZNE
          mojList2.add(md.getColumnName(i));
          }
    System.out.println(mojList2);
     
      Vector data = new Vector();
      Vector row;

      //store row data
      while(rs.next())
      {
          row = new Vector(columnCount);
          
                        
          	for(int t=1; t<=columnCount; t++) // nie to
          	{
              row.add(rs.getString(t));
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
      
       
       jtf.getDocument().addDocumentListener(new DocumentListener(){

           @Override
           public void insertUpdate(DocumentEvent e) {
           	
               String text = jtf.getText();

               if (text.trim().length() == 0) {
                   rowSorter.setRowFilter(null);
               } else {
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
           	
           	
               String text = jtf.getText();

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
	
		
		wybierz.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				//zaladowanie();
				coffanie.setVisible(false);
				
				mojList.add(jComboBox1.getSelectedItem().toString());
			
				jComboBox1.setVisible(false);
				wybierz.setVisible(false);
				dodajNowa.setVisible(false);
				wyczysc.setVisible(false);
				
				//System.out.println(jComboBox1.getSelectedItem());
				if(jComboBox1.getSelectedItem().equals("Sprzedane"))
				{
					
				//button6.setVisible(true);
				
				}else
				{
					button5.setVisible(true);
					button6.setVisible(true);
				}
				button7.setVisible(true);
				button4.setVisible(true);
				
			
				zaladowanie2(); // dodanie tabeli do okienka i td
				
			
			}});}
	
		
	void dodajMetoda() // Przejscie do okienka miesci zmiana2
	{
			dodajNowa.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
					if(wybranyIndex!=0)
					{
						zmiana2(); // wybranaKonfig juz jest tam umieszczona wybranaKonfig to zczytane z ComboBox
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
		wyczysc.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				//jComboBox1.setVisible(false);
				wybierz.setVisible(false);
				dodajNowa.setVisible(false);
				wyczysc.setVisible(false);
				
				coffanie.setVisible(false);
				button8.setVisible(true);
				button9.setVisible(true);
				
				
			}
		});
	}

	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
	void connect2() // Laduje numer z XML SAVE0 --> 0 i pobiera z bazy do jComboBox1
	{
		czytajNumer2();
		
		getItems(); // połączenie z url z SAVE0 lub pobranie z jTextField
			
		if(jComboBox1.getItemCount()!=0)
		{
			wybierz.setVisible(true);
			dodajNowa.setVisible(true);
			wyczysc.setVisible(true);
			connect.setVisible(false);
			
			jtfURL.setVisible(false);
			jtfUSER.setVisible(false);
			jtfPASS.setVisible(false);
			ex.setVisible(false);
			st.setText("STATUS : "+"CONNECTED");
			
			dellete.setVisible(false);
			
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
	    	 System.out.print(numerKonfig);
	    	
	    	String del = numerKonfig;
	    	del=del.substring(4, 5);
	    	//System.out.println(del);
	    	int del2=Integer.valueOf(del);
	    	
	    	//if(numerKonfig=="NOWA")
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
                 downUSER= element.getElementsByTagName("User").item(0).getTextContent(); 
                 downPASS= element.getElementsByTagName("Password").item(0).getTextContent(); 
                 downURL = element.getElementsByTagName("Url").item(0).getTextContent(); 
            }*/
    	   node.getParentNode().removeChild(node);
           writeDocument(doc);
            
        //}
    } 
	
	//------------------------------------------------------------
	// NOWE METODY
	//------------------------------------------------------------
	
	// Realizowac WYSZYSC 
	
	void przycisk8() // WYCZYSC
	{
		button8.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				jComboBox1.setVisible(false);
				button8.setVisible(false);
				
				try
				{
					
					con = DriverManager.getConnection(downURL,downUSER,downPASS);
					
					requete = con.createStatement();
					
					String myTableName ="DROP TABLE "+jComboBox1.getSelectedItem().toString();
					
					System.out.print(jComboBox1.getSelectedItem().toString());
					
					requete.executeUpdate(myTableName);
					
					//System.out.print("Tabela zostala usunieta");
					st.setText("STATUS : DEL COMPLETE");
					
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
		button9.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				st.setText("STATUS : CONNECTED");
				coffanie.setVisible(true);
				button8.setVisible(false);
				button9.setVisible(false);
				
				jComboBox1.removeAllItems();
				getItems();
				
				if(jComboBox1.getItemCount()==0)
					{
					wybierz.setVisible(false);
					wyczysc.setVisible(false);
					}else
					{
						wybierz.setVisible(true);
						wyczysc.setVisible(true);
					}
				
				jComboBox1.setVisible(true);
				dodajNowa.setVisible(true);
				
				
			}
});
	}
    
	void przycisk5() // TRYB DODANIA
	{
		// TRYB DODANIA
		
		button5.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(bbb==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			bbb=false;
	    			//button5.setText("GOTOWE");
	    			button4.setVisible(false);
	    			button5.setVisible(false);
	    			button6.setVisible(false);
	    			button7.setVisible(false);
	    			//button5.setVisible(false);
	    			
	    			//button9.setVisible(false);
	    			
	    			button1.setVisible(true);
	    			
	    			 jtf2.setVisible(true);
	    			 jtf3.setVisible(true);
	    			 jtf4.setVisible(true);
	    			 jtf5.setVisible(true);
	    			 jtf6.setVisible(true);
	    			 
	    			 jtf52.setVisible(true);
	    			 
	    			 scroller.setBounds(000, 90, 400, 280);
	    			
	    			 //Dodanie do JDBC
	    			 
	    			 
	    			 
	    		}
	    		else // powinny sie zniknac przyciski i zmienic polozenie tabela
	    		
	    		{	
	    			
	    			bbb=true;
	    			button5.setText("TRYB DODANIA");
	    			
	    			//button5.setVisible(true);
	    			
	    			button1.setVisible(false);
	    			
	    		 jtf2.setVisible(false);
	   			 jtf3.setVisible(false);
	   			 jtf4.setVisible(false);
	   			 jtf5.setVisible(false);
	   			 jtf6.setVisible(false);
	   			 
	   			 jtf52.setVisible(false);
	   			
	   			 scroller.setBounds(000, 70, 400, 300);
	    			
	   			
	    			
	    			
	    			
	    		}
			}
		}
		
	);
	}
	void przycisk1() // PRZYCISK - ADD
	{
		// DODAJ
		button1.addActionListener(new ActionListener() 
		{
			@Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	 button1.setVisible(false);
    			 jtf6.setVisible(false);
            	 jtf2.setVisible(false);
	   			 jtf3.setVisible(false);
	   			 jtf4.setVisible(false);
	   			 jtf5.setVisible(false);
	   			 jtf52.setVisible(false);
	   			
	   			 scroller.setBounds(000, 70, 400, 300);
            	 button5.setText("TRYB DODANIA");
            	
            	
            	
            	
            	String pobieranie=mojList.get(0).toString();
        		String razem ="select * from "+pobieranie;
            	String razem2 = "insert into "+pobieranie;
            	
            	String sql ="insert into "+mojList.get(0).toString() //zmienic to co w nawiasach
						//+"(id,brand,model,cena,sklep)"
						+"("+mojList2.get(0).toString()+','+mojList2.get(1).toString()+','+mojList2.get(2).toString()+','+mojList2.get(3).toString()+','+mojList2.get(4).toString()+','+mojList2.get(5).toString()+")"
						//+aaa
						+"values ('"+jtf6.getText()+"','"+jtf2.getText()+"','"+jtf3.getText()+"','"+jtf4.getText().toString()+"','"+jtf5.getText()+"','"+jtf52.getText()+"')"; //dodanie numeru
            	
            	//System.out.println("("+mojList2.get(0).toString()+','+mojList2.get(1).toString()+','+mojList2.get(2).toString()+','+mojList2.get(3).toString()+','+mojList2.get(4).toString()+")");
        		
            	///System.out.println("values ('"+jtf6.getText().toString()+"','"+jtf2.getText().toString()+"','"+jtf3.getText().toString()+"','"+jtf4.getText().toString()+"','"+jtf5.getText().toString()+"')"); //dodanie numeru
            	
            	if(bbb==false)
            	{
            		bbb= true;
            		button5.setVisible(true);
            		button4.setVisible(true);
            		button6.setVisible(true);
            		button7.setVisible(true);
            		
            	if(jtf6.getText().length()!=0){
            
            
            try{
            
               con = DriverManager.getConnection(downURL,downUSER,downPASS);
               requete = con.createStatement();
               requete.executeQuery(razem);// zmienic to co w nawiasach
               requete.executeUpdate(sql);
            		
            		
            	}
            	catch (Exception exc)
            	{
            		exc.printStackTrace();
            	}
            	
            	 //koniec if
            	
            }	
            	//--------------------------------------------
            	//System.out.println("ADD complete");
            	jtf2.setText("");
        		jtf3.setText("");
        		jtf4.setText("");
        		jtf5.setText("");
        		jtf6.setText("");
        		jtf52.setText("");
        		
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
		
		button4.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
	    		
	    
		// Unvisible
		button4.setVisible(false);
		button5.setVisible(false);
		button6.setVisible(false);
		button7.setVisible(false);
		scroller.setVisible(false);
		
		// Visible
		jComboBox1.setVisible(true);
		wybierz.setVisible(true);
		dodajNowa.setVisible(true);
		wyczysc.setVisible(true);
		coffanie.setVisible(true);
		
		
		mojList.remove(0); // WYCZYSZCZENIE z listy, nazwy tabeli z JDBC - new1
		
		
		
		//for(int i=0;i<mojList.size();i++)
		//{
		//	mojList.remove(i);
		//}
			
		
		
		
			}});
		
			
	}
	
	void przycisk6() // TRYB DELETE
	{
		//TRYB DELETE
		
		button6.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(bbb==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			bbb=false;
	    			//button6.setText("GOTOWE");
	    			button4.setVisible(false);
	    			
	    			button5.setVisible(false);
	    			button6.setVisible(false);
	    			button7.setVisible(false);
	    			
	    			jtf7.setVisible(true);
	    			
	    			button2.setVisible(true);
	    			
	    			
	    			jtf7.setVisible(true);
	    			 
	    			
	    		}
	    		else
	    		{
	    		
	    		bbb=true;
	    		jtf7.setVisible(false);
	    		
				button6.setText("TRYB USUWANIA");
				
				button2.setVisible(false);
				
				
			}
	    		
	    		
			}
			
		 });
	}

	void przycisk2() // DEL
	{
		//DELETE
		
		button2.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        
	        	
	        	String pobieranie=mojList.get(0).toString();
	        	
	        	if(bbb==false)
	        	{
	        		bbb=true;
                   
	        		button5.setVisible(true);
              		button4.setVisible(true);
            		button6.setVisible(true);
            		button7.setVisible(true);
	        	try
	    		{
	        		//c.remove(scroller);
	    			//Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?autoReconnect=true&useSSL=false","root","1029384756qaz");
	    			
	    			//Statement myStmt = myConn.createStatement();
	        		//Connection con ;
         		     //Statement requete ;
         		     //ResultSet rs ;
      		
      		con = DriverManager.getConnection(downURL,downUSER,downPASS);
             		
             				
             requete = con.createStatement();
	    			
	    			String sql = "delete from "+pobieranie+" where id=' "+jtf7.getText()+" '"; // zmienic 
	    			
	    			requete.executeUpdate(sql);
	    						
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
	        	
	        	button2.setVisible(false);
	        	jtf7.setVisible(false);
	        	button6.setText("TRYB USUWANIA");
	        	jtf7.setText("");
	        	
	        }
	    });
	}

	void przycisk7() // TRYB SZUKAJ
	{
		//TRYB SZUKAJ
		
			button7.addActionListener(new ActionListener(){
			
	    	@Override
			public void actionPerformed(ActionEvent e)
			{
				
	    		if(bbb==true) // powinny sie pojawic przyciski i zmienic polozenie tabela
	    		{
	    			
	    			button4.setVisible(false);
	    			button5.setVisible(false);
	    			button6.setVisible(false);
	    			//button7.setVisible(false);
	    			
	    			bbb=false;
	    			button7.setText("GOTOWE");
	    			jtf.setVisible(true);
	    			
	    			//button4.setVisible(true);
	    			
	    			
	    			jtf.setVisible(true);
	    			
	    			     
	    		}
	    		else
	    		{
	    		
	    		bbb=true;
	    		jtf.setVisible(false);
	    		button7.setText("TRYB SZUKANIA");
				
	    		
	    		if(jComboBox1.getSelectedItem().equals("sprzedane"))
	    		{
	    			
	    		}else
	    		{
	    		button5.setVisible(true);
	    		button6.setVisible(true);
	    		
	    		}
	    		
	    		button4.setVisible(true);
				jtf.setText("");
				
			}
	    		
	    		
	    	
			}
			
		 });
	}

	void przyciskMainDel()
	{
			dellete.addActionListener(new ActionListener(){
			
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
		
		wybierzMetoda(); // WYBIERZ - przejscie
		wyczyscMetoda(); // WYCZYSC - przejscie
		dodajMetoda();  // DODAJ - przejscie
		
		przycisk8(); // Wyczysc - usuwa z JDBC 
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
