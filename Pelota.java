
package ejecucion;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class Pelota extends JLabel{
    
    private int valor;
    private String rutaImagenes,imagen;
    
    /**
     * Constructor por defecto de pelota.
     */
    public Pelota(){
        iniciarPelota();
    }
    
    /**
    * Método que inicializa los valores de la pelota.
    *
    */
    
    private void iniciarPelota(){
        rutaImagenes = "imagenes/"; // String para guardar la raíz de las imágenes
        valor = 0; // Entero para guardar el valor que alberga la pelota.
        cambiarImagen(); // Se setea la imagen inicial.
    }
    
    /**
     * Método que actualiza la imagen de la pelota.
     */
    public void cambiarImagen(){ // Método para cambiar la imagen de la pelota.
        
        imagen = rutaImagenes + String.valueOf(valor) + ".jpg"; // se arregla el string que contiene la ruta de la imagen.
        
            setIcon(new ImageIcon(imagen)); // Setea la nueva imagen según su valor.
            
    }
    
    /**
     *Método que apica un borde alrededor de la pelota.
     */
    public void ponerBorde(){
        Border borde = BorderFactory.createLineBorder(Color.GRAY, 1);
        setBorder(borde);
    }
    
    /**
     *Método que le retira el borde a la pelota.
     */
    public void quitarBorde(){
        setBorder(null);
    }
    
    // Setters

    /**
     * Cambia el valor de la pelota.
     * @param valor Nuevo valor de la pelota.
     */

    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Cambia la ruta de la imagen.
     * @param rutaImagenes Nueva ruta para la imagen.
     */
    public void setRutaImagenes(String rutaImagenes) {
        this.rutaImagenes = rutaImagenes;
    }

    /**
     * Método que cambia la imagen.
     * @param imagen Cambia la imagen.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    // Getters

    /**
     * Método que retorna el valor de la imagen
     * @return El valor de la imagen.
     */

    public int getValor() {
        return valor;
    }
    
}
