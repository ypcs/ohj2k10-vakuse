/**
 * Käyttöliittymäluokka Vaateseuranta -ohjelmaan
 * Vaateseuranta -ohjelman 
 *
 * @author Ville Korhonen
 * @version 2010-02-05
 */


 class Vaatteet {
    
    public int testi() {
        return 0;
    }
    
    /**
     * Palauttaa vaatteen uniikin tunnisteen
     * @return vaatteen tunniste
     *
     * @example
     * <pre name="test">
     *  Vaatteet vaatteet = new Vaatteet();
     *  vaatteet.haeTunniste() === 0;
     */
    public int haeTunniste() {return tunniste;}
    
    /**
     * Palauttaa vaatteen tyypin selväkielisenä (tekstinä), pienillä kirjaimilla kirjoitettuna
     *
     * @return vaatteen tyyppi
     * @todo vaatetyyppien luku tiedostosta, nyt palauttaa staattisen arvon
     */
    public String haeTyyppi() {
        return "paita";
    }
 }