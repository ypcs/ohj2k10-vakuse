/**
 * Virtuaalinen vaatekasa Vaateseuranta -ohjelmaan
 * Vaateseuranta -ohjelman 
 *
 * @author Ville Korhonen <ville.p.korhonen@jyu.fi>
 * @version 2010-03-19
 */
package vaateseuranta;
import java.util.ArrayList;
import java.util.Iterator;

class Vaatteet implements Iterable<Vaate> {
	private boolean dataChanged = false;
	
	private static final int VAATTEITA_MAX = 200;
	
	private final ArrayList<Vaate> vaatteet = new ArrayList<Vaate>();
	
	public Vaatteet() {
		// TODO: ...
	}
	
	/**
	 * Lukee vaatedatan tiedostosta
	 * 
	 * @param filename tiedosto, josta luetaan
	 */
	public void readFile(String filename) {
		// TODO: ...
	}
	
	/**
	 * Kirjoittaa vaatedatan tiedostoon
	 * 
	 * @param filename
	 */
	public void writeFile(String filename) {
		// TODO
	}
	
	/**
	 * Palauttaa i:nnen vaate-alkion
	 * 
	 * @param i
	 * @return palauttaa vaatteen
	 */
	public Vaate get(int i) {
		return vaatteet.get(i);
	}
	
	/**
	 * Poistaa i:nnen vaate-alkion
	 * @param i poistettava vaate
	 */
	public void remove(int i) {
		vaatteet.remove(i);
	}
	
	/**
	 * Palauttaa vaate-alkioiden määrän
	 * @return vaateiden määrä
	 */
	public int getSize() {
		return vaatteet.size();
	}
	
	/**
	 * Lisää vaatteen
	 * @param vaate
	 */
	public void add(Vaate vaate) {
		vaatteet.add(vaate);
	}
	
	public class Iter implements Iterator<Vaate> {
		private Iterator<Vaate> i = vaatteet.iterator();
		Vaate v;
		int pos;
		
		/**
		 * 
		 */
		public Iter() {
			pos = 0;
		}
		
		/**
		 * Kertoo, löytyykö taulusta vielä alkioita
		 * @return palauttaa true, jos seuraava alkio olemassa; muuten false
		 */
		public boolean hasNext() {
			if (pos < vaatteet.size()) {
				return true;
			} else {
				return false;
			}
		}
		
		public Vaate next() {
			pos++;
			return i.next();
		}
		
		/**
		 * Poistaa edellisen iteroidun alkion 
		 */
		public void remove() {
			// TODO: liekö tarpeen, toteutetaanko?
		}
	}
	
	/**
	 * Palauttaa iteraattorin
	 * @return palauttaa uuden iteraattorin tälle kokoelmalle
	 */
	public Iterator<Vaate> iterator() {
		return new Iter();
	}
	
	/**
	 * Testiohjelma vaatekatalogin demottamiseen
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Vaatteet kaappi = new Vaatteet();
		Vaate vaate1 = new Vaate("59|2|1031209393|7|2|47|XL|sukkatehdays oy|huonoous|");
		vaate1.createID(); // resetoidaan id parsimalla saadusta
		
		
		Vaate vaate2 = new Vaate("5|6|1539988283|9|7|47||nokian|hai-saappaat rokkaa!|");
		vaate2.createID();
		
		kaappi.add(vaate1);
		kaappi.add(vaate2);
		
		// vaatteita kaapissa: kaappi.size();
		Iterator<Vaate> it= kaappi.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}