import java.sql.*;
import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces
import java.io.*;
import stud.Lentelex;

// An AWT GUI program inherits the top-level container java.awt.Frame
public class balansoLentele extends Frame
	implements KeyListener, ActionListener, WindowListener {
	// This class acts as listener for ActionEvent and WindowEvent
	// A Java class can extend only one superclass, but it can implement multiple interfaces.
																																										// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/prekyba";
	private TextField tfInput;
	private TextArea taDisplayTekstas;  // Declare a TextField component
	static String isvedami_duomenys = "";
	private Button btnNuskaityti;    // Declare a Button component																																									//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	private int ta_simboliu = 0;

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		new balansoLentele();
		try{

																																										//STEP 2: Register JDBC driver
			// Class.forName( "com.mysql.jdbc.Driver" );																				 // .newInstance();

																																										//STEP 3: Open a connection
			System.out.println( "Connecting to database..." );
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
																																//STEP 4: Execute a query
			System.out.println( "Creating statement..." );
			stmt = conn.createStatement();
			String sql;
			sql = 
				"SELECT "
					+ "`id`"
					+ ", `menuo`"
					+ ", `id_prekes`"
					+ ", `id_prekiu_grupes`"
					+ ", `kiekis_gauta`"
					+ ", `suma_gauta` "
					+ ", `kiekis_parduota` "
					+ ", `suma_parduota` "
				+ "FROM " 
					+ "`balanso_lentele`"
					;
			ResultSet rs = stmt.executeQuery(sql);
			String[] proceso_antr = { "|    id    |", "   menuo  |", " id_prekes  |", " id_prekiu_grupes  |", " kiekis_gauta  |", " suma_gauta   |", " kiekis_parduota  |", " suma_parduota  |" }; 
			String[] reiksmes = { "", "", "", "", "", "", "", "" };
			Lentelex lent = new Lentelex ( proceso_antr );
				
			lent.horizEil();
			lent.antraste();
			lent.horizEil();
																																													//STEP 5: Extract data from result set
			while ( rs.next() ) {
				
																																										//Retrieve by column name
				int id  = rs.getInt("id");
				int menuo = rs.getInt ( "menuo" );
				int id_prekes = rs.getInt( "id_prekes" );
				int id_prekiu_grupes = rs.getInt ( "id_prekiu_grupes" );
				int kiekis_gauta = rs.getInt ( "kiekis_gauta" );
				double suma_gauta = rs.getDouble ( "suma_gauta" );
				int kiekis_parduota = rs.getInt ( "kiekis_parduota" );
				double suma_parduota = rs.getDouble ( "suma_parduota" );				
																																									//Display values
				reiksmes [0] = String.valueOf(id);
				reiksmes [1] = String.valueOf(menuo);
				reiksmes [2] = String.valueOf(id_prekes);
				reiksmes [3] = String.valueOf(id_prekiu_grupes);
				reiksmes [4] = String.valueOf(kiekis_gauta);
				reiksmes [5] = String.valueOf(suma_gauta);
				reiksmes [6] = String.valueOf(kiekis_parduota);
				reiksmes [7] = String.valueOf(suma_parduota);

				lent.iLentele (reiksmes);
				
			}
			isvedami_duomenys += lent.atiduok();																																							//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch ( SQLException se ) {
																																										//Handle errors for JDBC
			se.printStackTrace();
		} catch ( Exception e ) {
																																										//Handle errors for Class.forName
			e.printStackTrace();

		} finally {
																																										//finally block used to close resources
			try{
					if (stmt != null) {

						stmt.close();
					}

			} catch ( SQLException se2 ){

			}																																							// nothing we can do
			
			try{

				if ( conn!=null ) {

					conn.close();
				}

			} catch ( SQLException se ) {

				se.printStackTrace();
			}																																							//end finally try
		}																																								//end try
		System.out.println( "Goodbye!" );
	}					
		
	public balansoLentele() {
		
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout
		add(new Label("Iveskite barkoda: "));
		tfInput = new TextField(12);
		add(tfInput);
		btnNuskaityti = new Button("Nuskaityti duomenu baze");  // Construct the Button
		add(btnNuskaityti);                   // "super" Frame adds Button
		taDisplayTekstas = new TextArea(50, 80); // Construct the TextField
		taDisplayTekstas.setEditable(false);       // read-only
		add(taDisplayTekstas);                     // "super" Frame adds TextField
		tfInput.addKeyListener(this);
		// tfInput TextField (source) fires KeyEvent.
		// tfInput adds "this" object as a KeyEvent listener.
		
		btnNuskaityti.addActionListener(this);
		// btnCount (source object) fires ActionEvent upon clicking
		// btnCount adds "this" object as an ActionEvent listener

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.kvailai
	 
		setTitle("Balanso lentele"); // "super" Frame sets title
		setSize(700, 750);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
	}	
	//end main
	
	/** KeyEvent handlers */
	@Override public void keyTyped(KeyEvent evt) {
		taDisplayTekstas.append("" +evt.getKeyChar());
		ta_simboliu += 1;
	}
	
	// Not Used, but need to provide an empty body for compilation
	@Override public void keyPressed(KeyEvent evt) { }
	@Override public void keyReleased(KeyEvent evt) { }
	
	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getActionCommand().equals("Nuskaityti duomenu baze")){
			
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");
			// Dimension dim = taDisplayTekstas.getPreferredSize();
			taDisplayTekstas.replaceRange(isvedami_duomenys, 0, ta_simboliu );
			ta_simboliu = isvedami_duomenys.length();			
			
		}
	}

	@Override public void windowClosing(WindowEvent evt) {
		System.exit(0);  // Terminate the program
	}
 
	// Not Used, BUT need to provide an empty body to compile.
	@Override public void windowOpened(WindowEvent evt) { }
	@Override public void windowClosed(WindowEvent evt) { }
	// For Debugging
	@Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
	@Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
	@Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
	@Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }

}	//end program