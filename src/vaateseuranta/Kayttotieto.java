/**
 * TIEP111, harjoitustyö
 * @author Ville Korhonen <ville.p.korhonen@jyu.fi>
 * @version x.y
 * 
 * @todo eroon finglishistä (ts. käytä muuttujienkin nimissä englantia)
 * @todo ostopaikalle parempi käännös (vrt. seller)
 */
package vaateseuranta;

import java.util.regex.*;
import java.io.PrintStream;

//#STATICIMPORT
/**
 * Käyttötieto, joka kuvaa vaatteen siihen liittyvän käyttökokemuksen
 * 
 */
public class Kayttotieto implements Cloneable {
	private static int nextID = 1;
	private int id;
    private Vaate vaate;
    private String aika;
    private String paikka;
    private String kunto;
    private int puhtaus;
    private int arvosana;
	private String lisatiedot;
	
	// ks. suunnitelma.t2t
    // id | vaate | aika             | paikka           | kunto     | puhtausaste jälkeen | arvosana | lisätiedot
    
	private static final int KENTTIA = 8;
	private static final int KENTTA_ID = 0;
	private static final int KENTTA_VAATE = 1;
	private static final int KENTTA_AIKA = 2;
	private static final int KENTTA_PAIKKA = 3;
	private static final int KENTTA_KUNTO = 4;
	private static final int KENTTA_PUHTAUS = 5;
	private static final int KENTTA_ARVOSANA = 6;
	private static final int KENTTA_LISATIEDOT = 7;

	private static final String EROTIN_STR = "|"; // asetetaan erotinmerkki ihan stringin avulla => helpompi muuttaa vrt. merkin ascii-koodin etsiminen
	private static final char EROTIN = EROTIN_STR.charAt(0); // pystyviiva (|) ascii-koodina (haetaan EROTIN_STR:stä)
	private static final long PIENIN_TUNNISTE = 1000000000;
	private static final long SUURIN_TUNNISTE = 2000000000;

	private static final String PARSE_REGEXP = "^([0-9]{1,})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "]([0-9]{10,10})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "]$";
	
	/** 
	 * Parsii annetusta rivistä käyttötieto-olion tiedot
	 *
	 * @param rivi rivi, joka tahdotaan lukea
	 * @return palauttaa true, jos tiedot olivat sopivassa muodossa & ne saatiin luettua, false mikäli tapahtui jokin virhe
	 *
	 * @todo onko regexp tarpeen? onko raskas? jotain muuta jännää pohdiskelua....
	 */
	public boolean parse(String rivi) {
		Pattern p = Pattern.compile(PARSE_REGEXP, Pattern.CANON_EQ);
		Matcher m = p.matcher(rivi);
		
		if (!m.matches() || (m.groupCount() != KENTTIA)) {
			return false; // TODO: tässä vois failata ihan kunnolla
		}
		
        // TODO: vois olla oma datavarastotyyppi mallia Kentta @ Jasen.java, jolloin tiedot sais taltioitua for-loopilla tai vastaavalla...
        // setID(m.group(KENTTA_ID + 1)); // TODO: tsekkaa, ettei id ole käytössä
        // setType(Integer.parseInt(m.group(KENTTA_TYYPPI + 1)));
        // setRFID(Long.parseLong(m.group(KENTTA_RFID + 1)));
        // setMaterial(Integer.parseInt(m.group(KENTTA_MATERIAALI + 1)));
        // setColor(Integer.parseInt(m.group(KENTTA_VARI + 1)));
        // setSize(m.group(KENTTA_KOKO + 1));
        // setSeller(m.group(KENTTA_OSTOPAIKKA + 1));
        // setManufacturer(m.group(KENTTA_VALMISTAJA + 1));
        // setAdditionalInfo(m.group(KENTTA_LISATIEDOT + 1));
        return true;
	}
	
	public void createID() {
		this.id = nextID++;
	}
	
	/**
	 * Asettaa vaatteen id:n
	 * @param i
	 */
	public void setID(int i) {
	    this.id = i;
	}

	/**
	 * Asettaa vaatteen id:n
	 * @param s
	 */
	public void setID(String s) {
		setID(Integer.parseInt(s));
	}
	

	
	/**
	 * Asettaa vaatteen lisätiedot
	 * @param s
	 */
	public void setAdditionalInfo(String s) {
	    this.lisatiedot = s;
	}
	
	/**
	 * Palauttaa vaatteen RFID-tunnisteen
	 * @return Palauttaa vaatteen RFID-tunnisteen
	 */
	public long getRFID() {
		return this.rfid;
	}
	
	/**
	 * Palauttaa vaatteen ID-numeron
	 * @return palauttaa vaatteen ID-numeron
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Palauttaa lisätiedot
	 * @return lisätiedot
	 */
	public String getAdditionalInfo() {
		return this.lisatiedot;
	}
	
	/**
	 * Palauttaa koon
	 * @return palauttaa koon
	 */
	public String getSize() {
		return this.koko;
	}
	
	public Vaate() {
		this.id = 0;
	}
	
	/**
	 * Muodostaja, joka parsii syötteenä annetusta rivistä vaatteen tiedot
	 * @param rivi
	 */
	public Kayttotieto(String rivi) {
		parse(rivi);
	}
	
	
	
	public String toString() {
	    // TODO: vaiheessa
		return "" + this.getID() + EROTIN;
	}
	
	
	
	public void print(PrintStream o) {
		o.println(this.toString());
	}
	
	/**
	 * Luo demotusta varten sisältöä
	 * 
	 * @example
	 * <pre name="test">
	 *  Kayttotieto k = Kayttotieto();
	 *  v.demoContent();
	 *  ...
	 * </pre>
	 */
	public void demoContent() {
        // TODO: ...
		lisatiedot = "Tää oli rikki jo ostettaessa, mutta näytti kivalta ni pidin.";
	}
	
	/**
	 * Testiohjelma...
	 * @param args
	 */
	public static void main(String[] args) {
        String testi1 = "1|1|2010-01-23 15:15|juhlat @ jossain|uusi|7|9|vaate oli ihku";
        String testi2 = "3|5|2010-01-23 15:15|juhlat @ jossain|reikäinen|5|4|tän vois heittää roskiin";
        String testi3 = "4|5|2010-01-23 16:00|kylpyhuone|rikki|6|5|osta uusi!";
        
        
        
	}

}
