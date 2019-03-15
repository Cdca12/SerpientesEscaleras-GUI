package serpientesescaleras.gui;

import javafx.scene.paint.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.*;
import java.util.Locale;
import javax.swing.ImageIcon;
import utils.Rutinas;

/**
 *
 * @author Carlos Contreras
 */
public class CasillaTablero extends JLabel {

    static final int[] tamañoImagenes = {30, 30};
    static final ImageIcon serpienteInicio = Rutinas.AjustarImagen("./src/images/serpienteInicio.png", tamañoImagenes[0], tamañoImagenes[1]);
    static final ImageIcon serpienteFin = Rutinas.AjustarImagen("./src/images/serpienteFin.png", tamañoImagenes[0], tamañoImagenes[1]);
    static final ImageIcon escaleraInicio = Rutinas.AjustarImagen("./src/images/escaleraInicio.png", tamañoImagenes[0], tamañoImagenes[1]);
    static final ImageIcon escaleraFin = Rutinas.AjustarImagen("./src/images/escaleraFin.png", tamañoImagenes[0], tamañoImagenes[1]);

    public int noCasilla;       // 1-100
    public char tipoCasilla;    // N, E, S, T
    public int posiciones;      // con E, S valor aleatorio (5-20)

    public CasillaTablero(int noCasilla, char tipoCasilla, int posiciones) {
        this.noCasilla = noCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posiciones = posiciones;
        setOpaque(true);
        pintarCasilla();
        setBorder(BorderFactory.createRaisedBevelBorder());
        setText(Integer.toString(noCasilla));
    }

    public void pintarCasilla() {
        switch (tipoCasilla) {
            case 'E':
                setIcon(escaleraInicio);
                break;
            case 'S':
                setIcon(serpienteInicio);
                break;
            case '+':
                setIcon(escaleraFin);
                break;
            case '-':
                setIcon(serpienteFin);
                break;
        }
        setBackground(java.awt.Color.LIGHT_GRAY);

    }
}
