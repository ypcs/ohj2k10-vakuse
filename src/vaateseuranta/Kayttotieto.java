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

	private static final String PARSE_REGEXP = "^([0-9]{1,})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "]([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2})[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "]([0-9]{1})[" + EROTIN + "]([0-9]{1})[" + EROTIN + "](.*)[" + EROTIN + "]?$";
	
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
		setID(Integer.parseInt(m.group(KENTTA_ID + 1)));
		setVaate(Integer.parseInt(m.group(KENTTA_VAATE + 1)));
		setTimestamp(m.group(KENTTA_AIKA + 1));
		setPaikka(m.group(KENTTA_PAIKKA + 1));
		setKunto(m.group(KENTTA_KUNTO + 1));
		setPuhtaus(Integer.parseInt(m.group(KENTTA_PUHTAUS + 1)));
		setArvosana(Integer.parseInt(m.group(KENTTA_ARVOSANA + 1)));
		setAdditionalInfo(m.group(KENTTA_LISATIEDOT + 1));
		
        return true;
	}
	
	/**
	 * Asettaa paikan
	 * @param s
	 */
	public void setPaikka(String s) {
		this.paikka = s;
	}
	
	/**
	 * Palauttaa paikan
	 * @return palauttaa paikan
	 */
	public String getPaikka() {
		return this.paikka;
	}
	
	/**
	 * Asettaa vaatteen
	 * @param v vaate
	 */
	public void setVaate(Vaate v) {
		this.vaate = v;
	}

	public void setVaate(int i) {
		// TODO
		this.vaate = new Vaate();
		this.vaate.setID(i);
	}
	
	/**
	 * Palauttaa vaatteen
	 * @return palauttaa vaatteen
	 */
	public Vaate getVaate() {
		return this.vaate;
	}
	
	/**
	 * Palauttaa kunnon
	 * @return palauttaa kunnon
	 */
	public String getKunto() {
		return this.kunto;
	}
	
	/**
	 * Asettaa kunnon
	 * @param s kunto
	 */
	public void setKunto(String s) {
		this.kunto = s;
	}
	
	public void setPuhtaus(int i) {
		this.puhtaus = i;
	}
	
	public void setArvosana(int i) {
		this.arvosana = i;
	}
	
	public int getPuhtaus() {
		return this.puhtaus;
	}
	
	public int getArvosana() {
		return this.arvosana;
	}
	
	/**
	 * Asettaa aikaleiman
	 * @param s
	 */
	public void setTimestamp(String s) {
		this.aika = s;
	}
	
	/**
	 * Hakee aikaleiman
	 * @return palauttaa aikaleiman
	 */
	public String getTimestamp() {
		return this.aika;
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
	 * Asettaa käyttötiedon lisätiedot
	 * @param s
	 */
	public void setAdditionalInfo(String s) {
	    this.lisatiedot = s;
	}
	
	
	
	/**
	 * Palauttaa käyttötiedon ID-numeron
	 * @return palauttaa käyttötiedon ID-numeron
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
	
	
	
	public Kayttotieto() {
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
		return "" + this.getID() + EROTIN + this.getVaate().getID() + EROTIN + this.getTimestamp() + EROTIN + this.getPaikka() + EROTIN + this.getKunto() + EROTIN + this.getPuhtaus() + EROTIN + this.getArvosana() + EROTIN + this.getAdditionalInfo() + EROTIN;
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
        String testi1 = "1|1|2010-01-23 15:15|juhlat @ jossain|uusi|7|9|vaate oli ihku|";
        String testi2 = "3|5|2010-01-23 15:15|juhlat @ jossain|reikäinen|5|4|tän vois heittää roskiin|";
        String testi3 = "4|5|2010-01-23 16:00|kylpyhuone|rikki|6|5|osta uusi!|";
        
        System.out.println("Sisään:");
        System.out.println(testi1);
        System.out.println(testi2);
        System.out.println(testi3);
        
        
		Kayttotieto kayttotieto1 = new Kayttotieto(testi1);
		kayttotieto1.createID(); // resetoidaan id parsimalla saadusta
		
		
		Kayttotieto kayttotieto2 = new Kayttotieto(testi2);
		kayttotieto2.createID();
		
		Kayttotieto kayttotieto3 = new Kayttotieto(testi3);
		kayttotieto3.createID();
        
		System.out.println("Ulos:");
		kayttotieto1.print(System.out);
		kayttotieto2.print(System.out);
		kayttotieto3.print(System.out);
		
	}

}
