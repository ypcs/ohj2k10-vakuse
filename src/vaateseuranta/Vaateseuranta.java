package vaateseuranta;


import java.util.Scanner;

/**
 * Käyttöliittymäluokka Vaateseuranta -ohjelmaan
 * Vaateseuranta -ohjelman 
 *
 * @author Ville Korhonen
 * @version 2010-02-05
 */
public class Vaateseuranta {
	private static final String APP_NAME = "Vaateseuranta";
	private static final int APP_VER_MAJOR = 0;
	private static final int APP_VER_MINOR = 0;
	private static final int APP_VER_PATCH = 5;
	private static final String APP_VERSION = APP_VER_MAJOR + "." + APP_VER_MINOR + "." + APP_VER_PATCH;
	public static final String APP_INFO = APP_NAME + " v" + APP_VERSION;
	
  /**
   * Tulostaa virheilmoituksen
   */
  private static void error(String message) {
      System.err.println(message);
  }


  /**
   * Lukee käyttäjän syötteen
   * @param question käyttäjälle esitettävä kysymys
   * @param defaultValue oletusarvo, mikäli käyttäjä syöttää tyhjän
   * @return palauttaa käyttäjän syötteen
   */
  private static String readLine(String question, String defaultValue) {
      Scanner lukija = new Scanner(System.in);
      System.out.print(question + " > ");
      String rivi = lukija.next().trim();
      if (rivi.length() == 0) {return defaultValue;}
      return rivi;
  }


  /**
   * Lukee käyttäjän syöttämän merkin
   * @param question käyttäjälle esitettävä kysymys
   * @param defaultValue oletusarvo, mikäli käyttäjä syöttää tyhjän
   * @return palauttaa käyttäjän syötteen
   */
  private static char readChar(String question, char defaultValue) {
      String rivi = readLine(question, "" + defaultValue);
      return rivi.charAt(0);
  }


  /**
   * Lukee käyttäjän syötteen (valikkosiirtymisiä varten)
   * @param question käyttäjälle esitettävä kysymys
   * @param defaultValue oletusarvo, mikäli käyttäjä syöttää tyhjän
   * @return palauttaa käyttäjän syötteen
   */
  private static int readInteger(String question, int defaultValue) {
      String rivi = readLine(question, "" + defaultValue);
      try {
          return Integer.parseInt(rivi);
      } catch (NumberFormatException ex) {
          error("Virheellinen syöte");
          return defaultValue;
      }
  }

  /**
   * Tulostaa merkkijonon ja rivinvaihdon jos printlf on tosi
   * @param s tulostettava merkkijono
   * @param printlf tulostetaanko (true) rivinvaihto vai ei (false)
   */
  private static void print(String s, boolean printlf) {
    System.out.print(s);
    if ( printlf ) System.out.println();
  }

  
  /**
   * Tulostaa merkkijonon ja rivinvaihdon
   * @param s Tulostettava rivi
   */
  private static void print(String s) {
    print(s,true);
  }

  /**
   * Tulostaa hieman tyhjää väliä
   * @param kVaateseurantanka monta riviä tyhjää tulostetaan?
   */
  private static void printSpace(int howMany) {
      for (int i=0; i<howMany; i++) {
        print("", true);
      }
  }
  
  /**
   * Tulostaa kaksi tyhjää riviä
   */
  private static void printSpace() {
      printSpace(2);
  }
  
  /**
   * Tulostaa ilmoituksen siitä, ettei toimintoa ole vielä toteutettu
   */
  private static void notImplemented()  {
    print(" Hups, toimintoa ei toteutettu!");
    print("");
  }

  private static void logo() {
      print("*******************************************");
      print("* " + APP_INFO);
      print("*******************************************");
  }

  
  /**
   * Tulostaa ohjelman tiedot
   */
  private static void info() {
      print(APP_INFO);
      printSpace();
      print("Vaateseurantaohjelmisto henkilökohtaiseen vaateseurantaan");
      print("(c) 2010, VK <ville.p.korhonen@jyu.fi>");
      printSpace();
  }


  /**
   * Tallentaa vaatekatalogin tiedot levylle.
   * @return palauttaa true mikäli tallennus onnistVaateseuranta, muuten false
   * @todo tee varsinainen toiminnallisuus
   */
  public Boolean save() {
    info();

    printSpace();

    print("Nyt pitäisi tallentaa tavarat kovalevylle...");
    notImplemented();

    return false;
  }

  /**
   * Tulostaa ohjelman valikolle otsikon
   * @param title
   */
  private static void printTitle(String title) {
	  printSpace();
	  print("--------------------");
	  print(title);
	  print("--------------------");
	  print("");
  }
  
  /**
   * Tulostaa ohjelman "Vaatteet" -osion valikon ja kysytään käyttäjältä valinta
   * @param onlyShow tulostetaanko valikko ja poistutaan kysymättä valintaa?
   */
  private static void vaateMenu(boolean onlyShow) {
	  printTitle("Vaatteet");
	  print("1 = Lisää uusi vaate");
	  print("2 - Muokkaa vaatteita");
	  print("3 - Poista vaatteita");
	  print("4 - Selaa vaatteita");
	  
	  print("-");
	  print("0 = paluu edelliseen valikkoon");
	  printSpace();
	  if (onlyShow) {return;}
	  
	  int valinta;
	  do {
		  valinta = readInteger("Valinta", 99);
		  switch (valinta) {
		  	case 0: break;
		  	case 1: notImplemented(); break;
		  	case 2: notImplemented(); break;
		  	case 3: notImplemented(); break;
		  	case 4: notImplemented(); break;
		  	
		  }
	  } while (valinta != 0);
	  mainMenu(true);
  }
  
  /**
   * Tulostaa ohjelman päävalikon ja kysyy käyttäjältä suoritettavan toiminnon
   * jonka jälkeen kyseinen toiminto suoritetaan
   * @param onlyShow tulostetaanko valikko ja poistutaan kysymättä valintaa?
   */
  private static void mainMenu(boolean onlyShow) {
      printTitle("Päävalikko");
      print("1 = Vaatteet");
      print("2 = Rekisteröi käyttötietoja");
      print("3 = Tulosta lista likaisista vaatteista");
      print("4 = Luo satunnainen vaatekokonaisuus");
      print("-");
      print("0 = lopetus");
      printSpace();
      if (onlyShow) {return;}
      
      int valinta;
      do {
          valinta = readInteger("Valinta", 99);
          switch (valinta) {
          	case 0: break;
            case 1: vaateMenu(false); break;
            case 2: notImplemented(); break;
            case 3: notImplemented(); break;
            case 4: notImplemented(); break;
            default: error("Virheellinen valinta!"); break;
          }
      } while (valinta != 0);
      
  }

  /**
   * Ohjelman pääohjelma
   * @param args komentorivin parametrit, ei käytössä vielä
   */
  public static void main(String[] args) {
    Vaateseuranta vaatteet = new Vaateseuranta();

    try {
      Vaateseuranta vaateseuranta = new Vaateseuranta();
      // näytetään logo
      vaateseuranta.logo();
      
      // näytetään päävalikko
      vaateseuranta.mainMenu(false);
      
      // tallennetaan data
      vaateseuranta.save();

      // näytetään vielä sovelluksen tiedot
      vaateseuranta.info();
    }
    catch (Exception ex) {
      System.out.println("VIRHE: " + ex.getMessage());
    }
  }

}

