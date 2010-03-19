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
 * Vaateluokka, joka kuvaa vaatteen ja sisältää sen ominaisuudet
 * 
 */
public class Vaate implements Cloneable {
	private static int nextID = 1;
	private int id;
	private long rfid;
	private int tyyppi;
	private int materiaali;
	private int vari;
	private String koko;
	private String ostopaikka;
	private String valmistaja;
	private String lisatiedot;
	
	// ks. suunnitelma.t2t
	// kentät: id, tyyppi, rfid, materiaali, väri, koko, ostopaikka, valmistaja, lisätiedot
    //         0    1       2     3           4     5     6            7          8
    
	private static final int KENTTIA = 9;
	private static final int KENTTA_ID = 0;
	private static final int KENTTA_TYYPPI = 1;
	private static final int KENTTA_RFID = 2;
	private static final int KENTTA_MATERIAALI = 3;
	private static final int KENTTA_VARI = 4;
	private static final int KENTTA_KOKO = 5;
	private static final int KENTTA_OSTOPAIKKA = 6;
	private static final int KENTTA_VALMISTAJA = 7;
	private static final int KENTTA_LISATIEDOT = 8;

	private static final String EROTIN_STR = "|"; // asetetaan erotinmerkki ihan stringin avulla => helpompi muuttaa vrt. merkin ascii-koodin etsiminen
	private static final char EROTIN = EROTIN_STR.charAt(0); // pystyviiva (|) ascii-koodina (haetaan EROTIN_STR:stä)
	private static final long PIENIN_TUNNISTE = 1000000000;
	private static final long SUURIN_TUNNISTE = 2000000000;

	private static final String PARSE_REGEXP = "^([0-9]{1,})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "]([0-9]{10,10})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "]([0-9]{1,})[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "](.*)[" + EROTIN + "]$";
	
	/** 
	 * Parsii annetusta rivistä vaate-olion tiedot
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
		setID(m.group(KENTTA_ID + 1)); // TODO: tsekkaa, ettei id ole käytössä
		setType(Integer.parseInt(m.group(KENTTA_TYYPPI + 1)));
		setRFID(Long.parseLong(m.group(KENTTA_RFID + 1)));
		setMaterial(Integer.parseInt(m.group(KENTTA_MATERIAALI + 1)));
		setColor(Integer.parseInt(m.group(KENTTA_VARI + 1)));
		setSize(m.group(KENTTA_KOKO + 1));
		setSeller(m.group(KENTTA_OSTOPAIKKA + 1));
		setManufacturer(m.group(KENTTA_VALMISTAJA + 1));
		setAdditionalInfo(m.group(KENTTA_LISATIEDOT + 1));
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
	 * Asettaa vaatteen tyypin
	 * @param t
	 */
	public void setType(int t) {
	    this.tyyppi = t; // TODO
	}
	
	/**
	 * Asettaa vaatteen RFID -tunnisteen
	 * @param r
	 */
	public void setRFID(long r) {
	    this.rfid = r;
	}
	
	/**
	 * Asettaa vaatteen materiaalin
	 * @param s
	 */
	public void setMaterial(int i) {
	    this.materiaali = i; // TODO
	}
	
	/**
	 * Asettaa vaatteen värin
	 * 
	 * @param s
	 */
	public void setColor(int i) {
	    this.vari = i; // TODO
	}
	
	/**
	 * Asettaa vaatteen koon
	 * @param s
	 */
	public void setSize(String s) {
	    this.koko = s; // TODO
	}
	
	/**
	 * Asettaa vaateen ostopaikan
	 * @param s
	 */
	public void setSeller(String s) {
	    this.ostopaikka = s;
	}
	
	/**
	 * Asettaa vaatteeen valmistajan
	 * @param s
	 */
	public void setManufacturer(String s) {
	    this.valmistaja = s;
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
	 * Palauttaa vaatetyypin
	 * @return vaatetyyppi
	 */
	public int getType() {
		return this.tyyppi;
	}
	
	/**
	 * Palauttaa värin
	 * @return vaatteen väri
	 */
	public int getColor() {
		return this.vari;
	}
	
	
	/**
	 * Palauttaa materiaalin
	 * @return palauttaa materiaalin
	 */
	public int getMaterial() {
		return this.materiaali;
	}
	
	/**
	 * Palauttaa ostopaikan
	 * @return ostopaikka
	 */
	public String getSeller() {
		return this.ostopaikka;
	}
	
	/**
	 * Palauttaa valmistajan
	 * @return valmistaja
	 */
	public String getManufacturer() {
		return this.valmistaja;
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
	public Vaate(String rivi) {
		parse(rivi);
	}
	
	
	
	public String toString() {
		return "" + this.getID() + EROTIN + this.getType() + EROTIN + this.getRFID() + EROTIN + this.getMaterial() + EROTIN + this.getColor() + EROTIN + this.getSize() + EROTIN + this.getSeller() + EROTIN + this.getManufacturer() + EROTIN + this.getAdditionalInfo() + EROTIN;
		//return "#" + this.getID() + " [vaatetyyppi], [vari: " + this.getColor() + "]/[koko], RFID: " + this.getRFID();
	}
	
	/**
	 * Luo näennäisesti satunnaisen tunnisteen, eli "rfid-koodin",
	 * jonka avulla vaate yksilöidään
	 * 
	 * @author Ville Korhonen <ville.p.korhonen@jyu.fi>
	 *
	 */
	private void createRFID() {
		this.rfid = (long)rand(PIENIN_TUNNISTE, SUURIN_TUNNISTE);
	}
	
	/**
	 * Palauttaa satunnaisen luvun väliltä min-max
	 * 
	 * 
	 * @param min pienin luku
	 * @param max suurin luku
	 * @return palauttaa satunnaisen luvun annetulta väliltä
	 */
	public static long rand(long min, long max) {
		double n = (max - min) * Math.random() + min;
		return (long)Math.round(n);
	}
	
	public void print(PrintStream o) {
		o.println(this.toString());
	}
	
	/**
	 * Luo demotusta varten sisältöä
	 * 
	 * @example
	 * <pre name="test">
	 *  Vaate v = Vaate();
	 *  v.demoContent();
	 *  v.getRFID() === 1992912345;
	 *  v.getSize() === "M";
	 * </pre>
	 */
	public void demoContent() {
		tyyppi = 5;
		vari = 4;
		koko = "M";
		rfid = 1992912345;
		ostopaikka = "Valintatalo";
		valmistaja = "Oy Lapsityövoima Ab";
		lisatiedot = "Tää oli rikki jo ostettaessa, mutta näytti kivalta ni pidin.";
	}
	
	/**
	 * Testiohjelma...
	 * @param args
	 */
	public static void main(String[] args) {
        String testirivi = "5|6|1539988283|9|7|47||nokian|hai-saappaat rokkaa!|";
        
        Vaate testivaate = new Vaate();
        testivaate.parse(testirivi);
        testivaate.print(System.out);
        testivaate.createID();
        testivaate.print(System.out);
        
        Vaate tokavaate = new Vaate();
        tokavaate.createRFID();
        tokavaate.createID();
        tokavaate.setAdditionalInfo("Huonous");
        tokavaate.setManufacturer("Piraatti Oy");
        tokavaate.setSeller("Halpahalli Ab");
        tokavaate.setSize("XXL");
        tokavaate.setMaterial(5);
        tokavaate.setType(2);
        tokavaate.print(System.out);
        
        Vaate kolmas = new Vaate();
        kolmas.demoContent();
        kolmas.print(System.out);
        kolmas.createID();
        kolmas.print(System.out);
       
        String testirivi2 = "59|2|1031209393|7|2|47|XL|sukkatehdays oy|huonoous|";
        Vaate testivaateN = new Vaate(testirivi2);
        testivaateN.print(System.out);
        testivaateN.createID();
        testivaateN.print(System.out);
	}

}
