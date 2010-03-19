/**
 * TIEP111, harjoitustyö
 *
 * @author Ville Korhonen <ville.p.korhonen@jyu.fi>
 */
package vaateseuranta;

/**
 * Vaateseuranta
 */
class Vaateseuranta {
    private Vaatteet vaatteet = new Vaatteet();
    private Kayttotiedot kayttotiedot = new Kayttotiedot();
    
    public void lisaaVaate(Vaate vaate) {
        vaatteet.add(vaate);
    }
    
    public void poistaVaate(int i) {
        vaatteet.remove(i);
    }
    
    public void lisaaKayttotieto(Kayttotieto kt) {
    	kayttotiedot.add(kt);
    }
    
    public void poistaKayttotieto(int i) {
    	kayttotiedot.remove(i);
    }
    
    public static void main(String[] args) {
        
    }
}