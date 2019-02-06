//Piacente e Murano 4Ai 06-02-2019

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class CaricaImmagine extends Canvas { //sottoclasse di Canvas (Canvas e' una sottoclasse di Component)
    static BufferedImage img;
    /*
     * BufferedImage e' una sottoclasse della classe Image e descrive un'immagine Raster
     * 
     * Gerarchia:
     * 
     * java.lang.Object
          java.awt.Image
               java.awt.image.BufferedImage
    */
 
    public void paint(Graphics g) { //paint e' un metodo che dev'esserci sempre nelle classi che estendono Component (o Canvas dato che e' sottoclasse di Component)
        //e' invocato nel momento in cui si deve ridisegnare il contenuto, ad esempio quando si allarga o restringe la finestra
        Image newImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT); //questa riga permette di ridimensionare l'immagine in base alle dimensioni della finestra
        g.drawImage(newImage, 0, 0, null); //il metodo drawImage invocato su un oggetto Graphics permette di disegnare l'immagine nello spazio a disposizione
        //abbiamo usato l'overloading con i seguenti parametri: Image img, int x, int y, ImageObserver observer
        //la classe ImageObserver non ci interessa ma a quanto pare e' notificata quando la dimensione e i pixel dell'immagine sono disponibili
    }
 
    public CaricaImmagine() {
       try { //il costrutto try-catch permette di catturare le eccezioni
           img = ImageIO.read(new File("Snivy.jpg"));
       } catch (IOException e) {
         System.out.printf("Errore nel caricare l'immagine (messaggio: %s)\n", e.getMessage()); //e.getMessage() restituisce il messaggio di errore
       }
    }
 
    public Dimension getPreferredSize() { //overriding del metodo della classe JComponent
      //(la classe Dimension incapsula due interi: larghezza e altezza
      return img == null ? new Dimension(800,800) : new Dimension(img.getWidth(null), img.getHeight(null));
      /* ^ come restituire qualcosa in base a una condizione, su 1 riga: return (condizione) ? (valore se vera) : (valore se falsa);
       * 
       * equivalente a questo:
       * 
       * if (img == null) {
             return new Dimension(800,800);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
      */
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame("Carica immagine"); //crea la "cornice" della finestra, con titolo Carica immagine
        f.add(new CaricaImmagine()); //add e' un metodo ereditato da java.awt.Container e aggiunge un oggetto di classe Component alla "cornice" (in questo caso la classe CaricaImmagine e' sottoclasse di Canvas la quale estende Component)
        f.pack(); //pack e' un metodo ereditato da java.awt.Window e sistema le dimensioni della "cornice"
        f.setVisible(true); //setVisible e' un metodo ereditato da java.awt.Window e mostra la "cornice"
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setta l'operazione da fare quando viene chiusa la finestra, in questo caso terminare l'esecuzione
    }
}
