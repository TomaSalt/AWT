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
	private String [] tekstas_po_simb;
	private String [] sveiku_sk_masyvas;
	private String nuskaitytasTekstas;
	private String sveiku_sk_sarasas;
	private String [] realiu_sk_masyvas;
	private String realiu_sk_sarasas;
	private String Tekstas;
	private String [] su_prasme_sk_masyvas;
	private String su_prasme_sk_sarasas;
	private int n = 0;
	private int b = 0;
	private int t = 0;
	private int c = 0;	
	private int d = 0;
	private int e = 0;
	private int kiekis_skaitmenu = 0;
	private int ta_simboliu = 0;
	


	// Constructor to setup the GUI components and event handlers
	public SkaiciaiTekste() {
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout
			
		//add(new Label("Maks., sumos, kiekio, vid. skaiciavimas"));   // "super" Frame adds an anonymous Label
		btnNuskaityti = new Button("Nuskaityti teksta");  // Construct the Button
		btnSkaitmenys = new Button("Skaitmenys");
		btnSveiki = new Button("Sveiki skaiciai");
		btnRealus = new Button("Realus skaiciai");
		btnSuPrasme = new Button("Skaiciai su ju prasme");
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
		
			if ( (simbolis != null) && simbolis.equals ( skaitmenys [ i ] ) ) {
			
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
			
			String simb = "";
			nuskaitytasTekstas = "Pradedam darba\n\n";
			ss = new String[ 2000 ];
			tekstas_po_simb = new String[ 2000 ];
			n = 0;
			t = 0;
			while ( ( s = frx.readLine() ) != null ) {
				
				nuskaitytasTekstas += s + "\n";
				
				for ( int i = 0; i < s.length()-1; i++ ) {
					
					simb = s.substring( i, i+1 );
					
					if ( simb != null ) {
					
						tekstas_po_simb[n] = simb;
						System.out.print ( tekstas_po_simb[n] );
						n++;
					
						if ( yraSkaitmuo ( simb ) ) {
						
							kiekis_skaitmenu++;
							ss [t] = simb;
							t++;
						}
					}
					
				}
			}
			fr.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	
	}
	
	public void showNumbersFromFile (){
		  
		Tekstas = "Skaitmenys tekste: \n\n";
		for( int i = 0; i < t; i++){
			  
			Tekstas += ss[i] + " ";
		}
	}
	
	public void sveikiSkaiciai () {
		
		c =0;
		sveiku_sk_masyvas = new String[ 2000 ];
		sveiku_sk_sarasas = "Sveikų skaičių sąrašas:\n ";
		boolean buvo_skaicius = false;
				
		for (int j = 0; j <= n; j++) {
			
			sveiku_sk_masyvas[c] = "";
			
			while ( yraSkaitmuo ( tekstas_po_simb[ j ] ) ) {
				
				sveiku_sk_masyvas[c] += tekstas_po_simb[j];
				sveiku_sk_sarasas += tekstas_po_simb[ j ];
				j++;
				buvo_skaicius = true;
				
			}
			
			if (buvo_skaicius){
				
				j--;
				sveiku_sk_sarasas += " ";
				System.out.print ( " " );
				System.out.print ( sveiku_sk_masyvas[c] );
				c++;
				
			}
			
			buvo_skaicius = false;
		
		}
		
	}
	public void realusSkaiciai () {
		
		d = 0;
		realiu_sk_masyvas = new String[ 2000 ];
		realiu_sk_sarasas = "Realių skaičių sąrašas:\n ";
		boolean rastas_kablelis = false;
		String skaicius = "";
				
		for (int j = 0; j < n; j++) {
			
			skaicius = "";
			
			while ( yraSkaitmuo ( tekstas_po_simb[ j ] )) {
				
				skaicius += tekstas_po_simb[ j ];
				j++;
								
			}
			if (rastas_kablelis){
				
				realiu_sk_masyvas[d] += skaicius;
				realiu_sk_sarasas += skaicius + " ";
				d++;
				rastas_kablelis = false;
				
			}				
				
			if ( (tekstas_po_simb[ j ].equals ( "," ) ) && (yraSkaitmuo( tekstas_po_simb[ j + 1] ))){
				
				realiu_sk_masyvas[d] += skaicius + ",";
				realiu_sk_sarasas += skaicius + ",";
				rastas_kablelis = true;
			}
		
		}
		if (yraSkaitmuo( tekstas_po_simb[ n ] )){
			realiu_sk_masyvas[d] += tekstas_po_simb[ n ];
			realiu_sk_sarasas += tekstas_po_simb[ n ];
		}
	}
	
	public static boolean arRaide ( String simb ) {
	
		boolean ar_raide = false;
		char ch = 0;		
		
		ch = simb.charAt(0);
		ar_raide = Character.isLetter( ch ) || simb.equals( "%" ) || simb.equals ( "." ) ;
		
		return ar_raide;
	}
	
	public void skaiciaiSuPrasme() {
		
		e = 0;
		String s = "";

		su_prasme_sk_masyvas = new String[ 2000 ];
		su_prasme_sk_sarasas = "Su reikšme skaičių sąrašas:\n ";
		boolean rastas_kablelis = false;
		String skaicius = "";
		boolean ar_raide = false;
				
		for (int j = 1; j < n; j++) {
			
			skaicius = "";
			
			while ( yraSkaitmuo ( tekstas_po_simb[ j ] ) && j < n) {
				
				skaicius += tekstas_po_simb[ j ];
				j++;
			}
			
			if ( j < n ) {
			
				ar_raide = arRaide ( tekstas_po_simb[ j ] );

				if ( ar_raide && ( ( j - 1 ) < n ) && yraSkaitmuo ( tekstas_po_simb[ j - 1 ] ) ) {
				
					su_prasme_sk_masyvas [ e ] += skaicius;
					su_prasme_sk_sarasas += skaicius;
				
					while ( ar_raide && ( j < n ) ) {
					
						su_prasme_sk_masyvas [ e ] += tekstas_po_simb[ j ];
						su_prasme_sk_sarasas += tekstas_po_simb[ j ];
						j++;
					
						if ( j < n ){
						
							ar_raide = arRaide ( tekstas_po_simb[ j ] );
						
						} else {
						
							break;
						}
					
					}
					e++;
					su_prasme_sk_sarasas += "\n";
				}
				
				if ( rastas_kablelis ) {
				
					su_prasme_sk_masyvas [ e ] += skaicius;
					su_prasme_sk_sarasas += skaicius + "\n";
					e++;
					rastas_kablelis = false;
				}				
				
				if ( ( j < ( n - 1) ) && ( tekstas_po_simb[ j ].equals ( "," ) ) && ( yraSkaitmuo( tekstas_po_simb [ j + 1 ] ) ) ) {
				
					su_prasme_sk_masyvas[e] += skaicius + ",";
					su_prasme_sk_sarasas += skaicius + ",";
					rastas_kablelis = true;
				}
			}
		}
		
		if ( yraSkaitmuo( tekstas_po_simb[ n ] ) ) {
			
			realiu_sk_masyvas[e] += tekstas_po_simb[ n ];
			realiu_sk_sarasas += tekstas_po_simb[ n ];
		}
	}
		

	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		readFromFile();
		if (evt.getActionCommand().equals("Nuskaityti teksta")){
			
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");
			// Dimension dim = taDisplayTekstas.getPreferredSize();
			taDisplayTekstas.replaceRange(nuskaitytasTekstas, 0, ta_simboliu );
			ta_simboliu = nuskaitytasTekstas.length();			
			
			
		}
		if (evt.getActionCommand().equals("Skaitmenys")){
			
			showNumbersFromFile();
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");			
			taDisplayTekstas.replaceRange(Tekstas, 0, ta_simboliu );
			ta_simboliu = Tekstas.length();
			
		}
			
		if (evt.getActionCommand().equals("Sveiki skaiciai")){
			
			sveikiSkaiciai();
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");
			taDisplayTekstas.replaceRange(sveiku_sk_sarasas, 0, ta_simboliu);
			ta_simboliu = sveiku_sk_sarasas.length();
			
		}
		
		if (evt.getActionCommand().equals("Realus skaiciai")){
			
			realusSkaiciai();
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");
			taDisplayTekstas.replaceRange(realiu_sk_sarasas, 0, ta_simboliu);
			ta_simboliu = realiu_sk_sarasas.length();
			
		}
		
		if (evt.getActionCommand().equals("Skaiciai su ju prasme")){
			
			skaiciaiSuPrasme();
			ta_simboliu += "Belekas".length();
			taDisplayTekstas.append("Belekas");
			// ta_simboliu = su_prasme_sk_sarasas.length();
			
			taDisplayTekstas.replaceRange(su_prasme_sk_sarasas, 0, ta_simboliu);
			ta_simboliu = su_prasme_sk_sarasas.length();
			
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