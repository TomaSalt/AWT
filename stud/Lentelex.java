	/**
	* stud - Programavimo Java mokymuisi skirtu pagalbiniu klasiu rinkinys
	*/
	package stud;
	/**
	* Klase skirta pavaizduoti lentele 
	* versija 0.0.1
	*/
	public class Lentelex {
	
		protected String[] pav_lent_grafu;		 									// lenteles grafos stulpeliu pavadinimai, su tarpais ir stulpeliu skyrimo simboliais, pilnu plociu, pvz.: { "| laikas |", " atstumas |" }
		
		protected String viskas = "";
	
		protected int[] len_iki_stulp = { 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0 };				// atstumu iki konkretaus stulpelio (simboliais) seka (masyvas)
		protected int total_len = 0;		
	
		/**
		* bool2Str statinis metodas grazina eilute "T" arba "N" atitinkancia logines argumento val reiksmes true arba false
		* @param boolean val
		*/
		public static String bool2Str ( boolean val ) {
			
			String i_ret = "T";													// i_ret - "as grazinu" - pradine reiksme true
			
			if ( ! val ) {
			
				i_ret = "N";
			}
			return i_ret;														// eilutes grazinimas
		}

		/**
		* skPoz - metodas nustatantis vidiniu klases savybiu - atstumu (simboliais) iki stulpeliu masyvo len_iki_stulp ir visos lenteles plocio (simboliais) total_sum reiksmes
		*/
		protected void skPoz() {
			
			int i = 0;
			
			for ( ; i < pav_lent_grafu.length; i++ ) {									// "prabegam" visu stulpeliu pavadinimu masyva
			
				len_iki_stulp [ i ] = total_len;										// i-tajam stulpeliui priskiriam iki tol suskaiciuota lenteles plocio ( simboliais ) total_len reiksme
				total_len += pav_lent_grafu [ i ].length();								// skaiciuojam nauja total_len_reiksme
			}
			len_iki_stulp [ i ] = total_len;											// jei burtu norodytas vaizduoti duomenu elementas neesantis (neatpazintas) lenteleje, ji rodytume papildomo stulpelio pozicijoje
		}

		/**
		* horizEil - metodas "nubreziantis" lenteles plocio horizontale eilute
		*/
		public String horizEil() {
			String hor_eilute = "\n";
			for ( int i = 0; i < total_len; i++ ) {										// brezimas tai vieno "-" simbolio rasymas tiek kartu, kokio plocio (simboliais) yra lentele
			
				hor_eilute += "-";
			}
			hor_eilute += "\n";
			return hor_eilute;
		}

		/**
		* antraste - metodas "israsantis" lenteles antrase
		*/
		public void antraste() {
			
			for (int i = 0; i <  pav_lent_grafu.length; i++ ) {							// ima po viena stulpeli ..

				viskas += pav_lent_grafu [ i ];								// .. ir ji "uzraso" - isveda 
			}
			viskas += "\n";
		}

		/**
		* Lentele - specialus metodas - klases konstruktorius - turi vadintis taip pat kaip klase
		*/
		public Lentelex () {
			
		}

		/**
		* Lentele - specialus metodas - klases konstruktorius - turi vadintis taip pat kaip klase
		* @param String[] duomenu_kitimo_lentele -  lenteles grafos - stulpeliu pavadinimai, rekomenduojama su tarpais ir stulpeliu skyrimo simboliais, pilnu plociu, pvz.: { "| laikas |", " atstumas |" }
		*/
		public Lentelex ( String[] pavad_lent_grafu ) {

			pav_lent_grafu = pavad_lent_grafu;										// isimenamos lenteles grafos
			skPoz();															// skaiciuojamos atstumu (simboliais) iki stulpeliu masyvo len_iki_stulp ir visos lenteles plocio (simboliais) total_sum reiksmes
		}
		
		/**
		 * iLentele - metodas atvaizduojantis viena laukeli vienoje eiluteje
		* @param String[] reiksmes -pateikiamu reiksmiu seka
		*/
		public void iLentele ( String[] reiksmes ) {
			
			String i_lentele = "";													// suformuota lenteles eilutes eilute

			for ( int i = 0; i < pav_lent_grafu.length; i++ ) {
				
				if ( i == 0 ) {													// pirmas stulpelis formuojamas kiek kitaip
				
					i_lentele += String.format ( "| %" + ( pav_lent_grafu [ i ].length() - 4 ) + "s | ", reiksmes [ i ] );
					
				} else {
					
					i_lentele += String.format ( "%" + ( pav_lent_grafu [ i ].length() - 3 ) + "s | ", reiksmes [ i ] );
				}
			}
			
			viskas += i_lentele + horizEil();											// uzrasoma suformuota eilute
																				// nubreziama horizontali linija
		}
		
		public String atiduok() {
			
			return viskas;
		}
	}		

		