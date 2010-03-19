/**
 * Virtuaalinen kayttotietokasa kayttotietoseuranta -ohjelmaan 
 * kayttotietoseuranta -ohjelman 
 *
 * @author Ville Korhonen <ville.p.korhonen@jyu.fi>
 * @version 2010-03-19
 * 
 * @todo iterable
 */
package vaateseuranta;

//import java.util.Iterator;

class Kayttotiedot {
	//private boolean dataChanged = false;
	private int lastIndex = 0;
	
	private static final int KAYTTOTIETOJA_MAX = 200;
	private Kayttotieto[] kayttotiedot = new Kayttotieto[KAYTTOTIETOJA_MAX];
	
	public Kayttotiedot() {
		// TODO: ...
	}
	
	/**
	 * Kasvatetaan käyttötietotaulun kokoa (*2); tuplataan samantien, koska tehokkaampaa
	 * tehdä kasvatus harvoin, ja kerralla sit enempi
	 * @param ka kasvatettava taulu
	 * @return palauttaa kasvatetun taulun
	 */
	public static Kayttotieto[] enlargeYourKayttotiedot(Kayttotieto[] ka) {
		Kayttotieto[] kt = new Kayttotieto[ka.length * 2];
		for	(int i=0; i<ka.length; i++) {
			kt[i] = ka[i];			
		}
		return ka;
	}
	
	/**
	 * Lukee kayttotietodatan tiedostosta
	 * 
	 * @param filename tiedosto, josta luetaan
	 */
	public void readFile(String filename) {
		// TODO: ...
	}
	
	/**
	 * Kirjoittaa kayttotietodatan tiedostoon
	 * 
	 * @param filename
	 */
	public void writeFile(String filename) {
		// TODO
	}
	
	/**
	 * Palauttaa i:nnen kayttotieto-alkion
	 * 
	 * @param i
	 * @return palauttaa vaatteen
	 */
	public Kayttotieto get(int i) {
		return kayttotiedot[i];
	}
	
	/**
	 * Poistaa i:nnen kayttotieto-alkion
	 * @param i poistettava kayttotieto
	 */
	public void remove(int i) {
		// TODO
	}
	
	/**
	 * Palauttaa kayttotieto-alkioiden määrän
	 * @return kayttotietoiden määrä
	 */
	public int getSize() {
		return kayttotiedot.length;
	}
	
	/**
	 * Lisää vaatteen
	 * @param kayttotieto
	 */
	public void add(Kayttotieto kayttotieto) {
		if (lastIndex >= kayttotiedot.length) {
			kayttotiedot = enlargeYourKayttotiedot(kayttotiedot);
		}
		kayttotiedot[lastIndex] = kayttotieto;
		lastIndex++;
	}

	

	/**
	 * Testiohjelma kayttotietokatalogin demottamiseen
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String testi1 = "1|1|2010-01-23 15:15|juhlat @ jossain|uusi|7|9|vaate oli ihku";
        String testi2 = "3|5|2010-01-23 15:15|juhlat @ jossain|reikäinen|5|4|tän vois heittää roskiin";
        String testi3 = "4|5|2010-01-23 16:00|kylpyhuone|rikki|6|5|osta uusi!";
        
		Kayttotiedot tiedot = new Kayttotiedot();
		Kayttotieto kayttotieto1 = new Kayttotieto(testi1);
		kayttotieto1.createID(); // resetoidaan id parsimalla saadusta
		
		
		Kayttotieto kayttotieto2 = new Kayttotieto(testi2);
		kayttotieto2.createID();
		
		Kayttotieto kayttotieto3 = new Kayttotieto(testi3);
		kayttotieto3.createID();
		
		tiedot.add(kayttotieto1);
		tiedot.add(kayttotieto2);
		tiedot.add(kayttotieto3);
		
		// vaatteita kaapissa: kaappi.size();
		for (int i=0; i<tiedot.getSize() - 1; i++) {
			tiedot.get(i).print(System.out);
		}
	}
}