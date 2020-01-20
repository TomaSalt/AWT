import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces
import java.io.*;
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class SkaiciaiTekste extends Frame
	implements ActionListener, WindowListener {
	// This class acts as listener for ActionEvent and WindowEvent
	// A Java class can extend only one superclass, but it can implement multiple interfaces.
 
	private TextArea taDisplayTekstas;  // Declare a TextField component
	private Button btnNuskaityti;    // Declare a Button component
	private Button btnSkaitmenys;
	private Button btnSveiki;
	private Button btnRealus;
	private Button btnSuPrasme;
	
	private String s;
	private String [] ss;
	private String nuskaitytasTekstas;
	private int b = 0;
	private int t = 0;
	private int kiekis_skaitmenu = 0;
	


	// Constructor to setup the GUI components and event handlers
	public SkaiciaiTekste() {
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout
			
		//add(new Label("Maks., sumos, kiekio, vid. skaiciavimas"));   // "super" Frame adds an anonymous Label
		btnNuskaityti = new Button("Nuskaityti teksta");  // Construct the Button
		btnSkaitmenys = new Button("Skaitmenys");
		btnSveiki = new Button("Sveiki skaičiai");
		btnRealus = new Button("Realus skaičiai");
		btnSuPrasme = new Button("Skaičiai su jų prasme");
		add(btnNuskaityti);                   // "super" Frame adds Button
		add(btnSkaitmenys);                   
		add(btnSveiki);       
		add(btnRealus);                   
		add(btnSuPrasme);                   		
		taDisplayTekstas = new TextArea(30, 50); // Construct the TextField
		taDisplayTekstas.setEditable(false);       // read-only
		add(taDisplayTekstas);                     // "super" Frame adds TextField
		btnNuskaityti.addActionListener(this);
		btnSkaitmenys.addActionListener(this);
		btnSveiki.addActionListener(this);
		btnRealus.addActionListener(this);
		btnSuPrasme.addActionListener(this);
		// btnCount (source object) fires ActionEvent upon clicking
		// btnCount adds "this" object as an ActionEvent listener

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.kvailai
	 
		setTitle("Teksto analizė"); // "super" Frame sets title
		setSize(700, 750);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
	}
	
	// The entry main() method
	public static void main(String[] args) {
		
		new SkaiciaiTekste();  // Let the construct do the job
		
	}
	public static boolean yraSkaitmuo ( String simbolis ) {
		
		String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		
		boolean yra_skaitmuo = false;
		
		for (int i = 0; i < 10; i++ ) {
		
			if ( simbolis.equals ( skaitmenys [ i ] ) ) {
			
				yra_skaitmuo = true;
			}
		}
		return yra_skaitmuo;
	}
   
	public void readFromFile () {
		
		String s = null;
		
		try{
			FileReader fr=new FileReader( "tekstas.txt" );
			BufferedReader frx = new BufferedReader ( fr );
			System.out.println ( "duomenų failo turinys:" );
			String simb;
			nuskaitytasTekstas = "";
			ss = new String[ 1000 ];
			while ( ( s = frx.readLine() ) != null ) {
				
				System.out.println( s );
				nuskaitytasTekstas += s + "\n";
				
				for ( int i = 0; i < s.length(); i++ ) {
					
					simb = s.substring( i, i+1 );
					if ( yraSkaitmuo ( simb ) ) {
						
						kiekis_skaitmenu++;
						ss [t] = simb;
						t++;
						
					}
				}
			}
			fr.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	
	}
  
	public String showNumbersFromFile (){
		  
		String Tekstas = "Skaitmenys tekste: \n\n";
		for( int i = 0; i < t; i++){
			  
			Tekstas += ss[i] + " ";
		}
		return Tekstas;
	}
  
	/*public void sudarytiSkaiciuMasyva (){
		

		int j = 0;
		String[] sss;
		skaiciu_masyvas = new double[ 1000 ];
		
		for (int y = 0; y < t; y++){
			
			int i;
			
			sss = ss [ y ].split ( "," );
				
			for ( i = 0; i < sss.length; i++) {
				
				skaiciu_masyvas[ j + i ] = Double.parseDouble ( sss [ i ] );
				nr_skaiciu++;
			}
			
			j += i;
		}
			
	}
	public double maksReiksmeSkaiciuMasyve () {
		
		double maks_reiksme_masyve = skaiciu_masyvas[0];
		
		for (int i=1; i < nr_skaiciu; i++){
			
			if (skaiciu_masyvas[i] > maks_reiksme_masyve) {
				
				maks_reiksme_masyve = skaiciu_masyvas[i];
			}
		}
		
		return maks_reiksme_masyve;
	}
	
	public double skaiciuMasyveSuma () {
		
		double suma = 0;
		
		for (int i=0; i < nr_skaiciu; i++){
			
			suma += skaiciu_masyvas[i];
		}
		
		return Math.round(suma);
	}
	
	public void masyvoVidurkis () {
		
		vidurkis = skaiciuMasyveSuma () / nr_skaiciu;
		
	}
	
	public void masyvoSkaiciaiDidesniUzVidurki () {
				
		int y = 0;
		
		didesni_uz_vidurki = new double[ 100 ];
		
		for (int i=0; i < nr_skaiciu; i++){
			
			if ( skaiciu_masyvas[i] > vidurkis) {
				
				didesni_uz_vidurki [y] = skaiciu_masyvas [i];
				didesniu_uz_vid_sarasas += didesni_uz_vidurki [y] + ", ";
				y++;
				
			} else {
				
				maz_uz_vid_suma += skaiciu_masyvas[i];
			}
		}
		didesniu_uz_vid_kiekis = y;
	}
	*/
	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getActionCommand().equals("Nuskaityti teksta")){
			
			taDisplayTekstas.append("Pradedam darba\n\n" );
			readFromFile();
			taDisplayTekstas.append(nuskaitytasTekstas);
			
		}
		if (evt.getActionCommand().equals("Skaitmenys")){
			
			taDisplayTekstas.append(showNumbersFromFile());
		}
			/*sudarytiSkaiciuMasyva();
			taDisplayTekstas.append("Nuskaityta " + (nr_skaiciu) + " skaiciu. \n\n");
			taDisplayTekstas.append("Maksimali reiksme skaiciu sekoje: " + maksReiksmeSkaiciuMasyve() + "\n");
			taDisplayTekstas.append("Skaiciu suma: " + skaiciuMasyveSuma() + "\n");
			masyvoVidurkis();
			taDisplayTekstas.append("Vidurkis: " + vidurkis + "\n\n");
			masyvoSkaiciaiDidesniUzVidurki();
			taDisplayTekstas.append("Skaiciai didesni uz vidurki:\n\n " + didesniu_uz_vid_sarasas + "\n");
			taDisplayTekstas.append("likusiu skaiciu suma: " + maz_uz_vid_suma);*/
			
		

	}
 
   /* WindowEvent handlers */
   // Called back upon clicking close-window button
   @Override
   public void windowClosing(WindowEvent evt) {
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
}