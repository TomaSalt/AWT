import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces
import java.io.*;
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class MinMaxAWT extends Frame
	implements ActionListener, WindowListener {
	// This class acts as listener for ActionEvent and WindowEvent
	// A Java class can extend only one superclass, but it can implement multiple interfaces.
 
	private TextArea taDisplayTekstas;  // Declare a TextField component
	private Button btnCount;    // Declare a Button component
	private int count = 0;      // Counter's value
	private String s;
	private String [] ss;
	private double [] skaiciu_masyvas;
	private int t = 0;
	private int max_eiluciu_sk_tekste = 50;
	private int nr_skaiciu = 0;

	// Constructor to setup the GUI components and event handlers
	public MinMaxAWT() {
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout
			
		add(new Label("Maks., sumos, kiekio, vid. skaiciavimas"));   // "super" Frame adds an anonymous Label
		btnCount = new Button("Skaiciuoti");  // Construct the Button
		add(btnCount);                   // "super" Frame adds Button
		 
		taDisplayTekstas = new TextArea(30, 50); // Construct the TextField
		taDisplayTekstas.setEditable(false);       // read-only
		add(taDisplayTekstas);                     // "super" Frame adds TextField
		btnCount.addActionListener(this);
		// btnCount (source object) fires ActionEvent upon clicking
		// btnCount adds "this" object as an ActionEvent listener

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.
	 
		setTitle("Maks., Suma, Kiekis, Vidurkis"); // "super" Frame sets title
		setSize(700, 750);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
	}
	
	// The entry main() method
	public static void main(String[] args) {
		
		new MinMaxAWT();  // Let the construct do the job
		
	}
   
	public void readFromFile (){
	   
		try{
			FileReader fr=new FileReader( "MinMaxDuomenys.txt" );
			BufferedReader frx = new BufferedReader ( fr );
			t = 0;
			ss = new String[  max_eiluciu_sk_tekste ];
			while ( ( s = frx.readLine() ) != null ) {
				ss [ t ] = s; //.split ( "," );
				t++;
			}
			fr.close();
		}
		catch(IOException ioe){
		}
	
	}
  
	public String showTextFromFile (){
		  
		String Tekstas = "duomenu failo turinys: \n\n";
		for( int i = 0; i < t; i++){
			  
			Tekstas += ss[i] + "\n";
		}
		return Tekstas;
	}
  
	public void sudarytiSkaiciuMasyva (){
		

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
	
 
	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getActionCommand().equals("Skaiciuoti")){
			
			taDisplayTekstas.append("Pradedam darba\n\n");
			readFromFile();
			taDisplayTekstas.append(showTextFromFile());
			sudarytiSkaiciuMasyva();
			taDisplayTekstas.append("Nuskaityta " + (nr_skaiciu) + " skaiciu. \n");
			
		}

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