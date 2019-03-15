package serpientesescaleras.gui;

import utils.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

// TODO: 
// - Mensaje arriba cuando caigas en escalera
// - Añadir bien las casillas como en el juego
// - Jugadores aleatorios
// - Imágenes de serpientes y escaleras
/**
 *
 * @author Carlos Contreras
 */
public class SerpientesEscaleras extends JFrame implements ActionListener {

    final Color[] colorJugador = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    String mensaje;
    int turno, jugadorActual, dados, iterador, cantidadJugadores;
    JPanel informacion;
    JLabel title, lbJugadorActual, lbTurno, lbCasillaActual, lbMensaje, lbDados;
    Tablero panelTablero;
    JButton btnAvanzar, btnReiniciar, btnSalir;
    JLabel icon;
    NodoDBL<CasillaTablero>[] jugadores;

//    NodoDBL<Casilla>[] jugadores = new NodoDBL[new Random().nextInt(10 - 2 + 1) + 2];
//    NodoDBL<Casilla>[] jugadores = Rutinas.nextInt(2, 10:)
    public SerpientesEscaleras() {
        super("Serpientes y Escaleras");
        setSize(700, 600);
        setLayout(null);

        turno = 1;
        iterador = 0;

        // Generar jugadores
//        cantidadJugadores = Rutinas.nextInt(2, 10);
        cantidadJugadores = 3;
        jugadores = new NodoDBL[cantidadJugadores];

        title = new JLabel("Serpientes y Escaleras");
        title.setBounds(510, 40, 150, 35);
        add(title);
        lbMensaje = new JLabel();
        lbMensaje.setBounds(30, 10, 800, 20);
        add(lbMensaje);

        icon = new JLabel(Rutinas.AjustarImagen("./src/images/icon.png", 120, 120));
        icon.setBounds(480, 60, 200, 200);
        add(icon);

        informacion = new JPanel(new GridLayout(0, 2));
        informacion.setBounds(525, 310, 180, 50);
//        informacion.setBackground(Color.yellow);
        add(informacion);

        informacion.add(new JLabel("Jugador:   ", SwingConstants.RIGHT));
        lbJugadorActual = new JLabel(Integer.toString(iterador + 1));
//        lbJugadorActual.setBounds(480, 100, 100, 100);
        informacion.add(lbJugadorActual);

        informacion.add(new JLabel("Dados:   ", SwingConstants.RIGHT));
        lbDados = new JLabel("0");
        informacion.add(lbDados);

        informacion.add(new JLabel("Casilla actual:   ", SwingConstants.RIGHT));
        lbCasillaActual = new JLabel("0");
        informacion.add(lbCasillaActual);

        JPanel panelTurno = new JPanel(new FlowLayout());
//        panelTurno.setBounds(5, 538, 100, 30);
        panelTurno.setBounds(195, 538, 100, 30);
        panelTurno.add(new JLabel("Turno: "));
        lbTurno = new JLabel(Integer.toString(turno));
        panelTurno.add(lbTurno);
        add(panelTurno);
//        informacion.add(lbTurno);

        // Se crea el tablero
        panelTablero = new Tablero();
        panelTablero.setBounds(25, 35, 450, 500);
        add(panelTablero);

        // Se añaden botones de interfaz
        btnAvanzar= new JButton("Avanzar");
        btnAvanzar.setBounds(530, 400, 100, 35);
        btnAvanzar.addActionListener(this);
        add(btnAvanzar);

        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(530, 440, 100, 35);
        btnReiniciar.addActionListener(this);
        add(btnReiniciar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(530, 480, 100, 35);
        btnSalir.addActionListener(this);
        add(btnSalir);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton botonPulsado = (JButton) evt.getSource();
        if (botonPulsado == btnAvanzar) {
            tirarDados();
            return;
        }
        if (botonPulsado == btnReiniciar) {
            System.out.println("Entró Botón Reiniciar");
            // TODO: Reiniciar tablero, contadores, generarCasillas, etc. Última feature.
            return;
        }
        if (botonPulsado == btnSalir) {
            System.out.println("Entró Botón Salir");
            System.exit(NORMAL);
            return;
        }
    }

    private void tirarDados() {
        boolean juegoCompletado = false;
        NodoDBL<CasillaTablero> posicionActual = null;
        NodoDBL<CasillaTablero> casillaLlegada = null;

        while (true) {
            mensaje = "El Jugador ";
            turno++;
            dados = Rutinas.nextInt(2, 12);

            posicionActual = jugadores[iterador]; // Guardar posicion actual antes de moverse

            // Primer tiro de ese jugador
            if (posicionActual == null) {
                jugadores[iterador] = panelTablero.tablero.getFrente();
                for (int j = 0; j < dados - 1; jugadores[iterador] = jugadores[iterador].getSig(), j++);
//                jugadores[iterador].Info.setBackground(Color.PINK);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = mensaje + (iterador + 1) + " llegó a la casilla " + jugadores[iterador].Info.noCasilla;
                break;
            }

            posicionActual.Info.pintarCasilla(); // Regresa al color original

            // Me posiciono donde va a llegar el jugador
            for (int j = 0; j < dados; j++) {
                if (jugadores[iterador].getSig() == null) { // Se llega al final del tablero
                    // Se regresa
                    for (int k = 0; k < dados - j; jugadores[iterador] = jugadores[iterador].getAnt(), k++);
                    break;
                }
                jugadores[iterador] = jugadores[iterador].getSig();
            }

            casillaLlegada = jugadores[iterador]; // Guardar donde llegó inicialmente, antes de aplicar avance o retroceso
//            casillaLlegada.Info.setBackground(Color.PINK);
            casillaLlegada.Info.setBackground(colorJugador[iterador]);
            mensaje = mensaje + (iterador + 1) + " llegó a la casilla " + jugadores[iterador].Info.noCasilla;

            // Dependiendo si cayó en escalera o serpiente, moverlo.
            if (casillaLlegada.Info.tipoCasilla == 'E') {
                casillaLlegada.Info.setBackground(colorJugador[iterador]);
                update(getGraphics());
                casillaLlegada.Info.setBackground(java.awt.Color.LIGHT_GRAY);
                esperar();
                for (int j = 0; j < casillaLlegada.Info.posiciones; jugadores[iterador] = jugadores[iterador].getSig(), j++);
//                jugadores[iterador].Info.setBackground(Color.BLACK);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = "¡Escalera! " + mensaje + ", que es una escalera, por lo que avanzó a la casilla " + jugadores[iterador].Info.noCasilla;;
            } else if (casillaLlegada.Info.tipoCasilla == 'S') {
                casillaLlegada.Info.setBackground(colorJugador[iterador]);
                update(getGraphics());
                esperar();
                casillaLlegada.Info.setBackground(java.awt.Color.LIGHT_GRAY);
                for (int j = 0; j < casillaLlegada.Info.posiciones; jugadores[iterador] = jugadores[iterador].getAnt(), j++);
//                jugadores[iterador].Info.setBackground(Color.ORANGE);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = "¡Serpiente! " + mensaje + ", que es una serpiente, por lo que se regresó a la casilla " + jugadores[iterador].Info.noCasilla;;
            }
            break;

        }

        lbMensaje.setText(mensaje);
        lbDados.setText(Integer.toString(dados));
        lbCasillaActual.setText(Integer.toString(jugadores[iterador].Info.noCasilla));
        lbTurno.setText(Integer.toString(turno));
        lbJugadorActual.setText(Integer.toString(iterador + 1));

//         Se gana el juego
        if (jugadores[iterador].Info.noCasilla == 100) {
//        if (turno == 10) {
            String texto = "¡JUEGO TERMINADO! \nEl ganador es: Jugador " + (iterador + 1);
            JOptionPane.showMessageDialog(this, texto);
            System.exit(NORMAL);
        }

        // Ciclo turno jugadores
        iterador = ((iterador + 1) == cantidadJugadores) ? 0 : (iterador + 1);

    }

    private void esperar() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            Logger.getLogger(SerpientesEscaleras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Tablero extends JPanel {

        ListaDBL<CasillaTablero> tablero;

        private Tablero() {
            setLayout(new GridLayout(10, 10));
            generarTablero();
//            setBackground(Color.WHITE);
            setVisible(true);
        }

        private void generarTablero() {
            // Crear tablero original
            tablero = new ListaDBL<>();
            for (int i = 0; i < 100; tablero.InsertaFin(new CasillaTablero(++i, 'N', 0)));

            // Crear 5 escaleras
            for (int i = 0; i < 5; generarCasilla(tablero, 'E', 15, 70), i++);

            // Crear 5 serpientes
            for (int i = 0; i < 5; generarCasilla(tablero, 'S', 30, 95), i++);

            // TODO: Añadirlos ascendente y descendente, como el juego real
            // Añadir al panel
            NodoDBL<CasillaTablero> aux = tablero.getFrente();
            for (int i = 0; i < tablero.Length(); i++, aux = aux.getSig()) {
//                aux.Info.cambiarTexto();
                aux.Info.pintarCasilla();
                add(aux.Info);
            }
        }

        private void generarCasilla(ListaDBL<CasillaTablero> tablero, char tipoCasilla, int limiteInferior, int limiteSuperior) {
            int nodoValido = new Random().nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
            NodoDBL<CasillaTablero> aux = tablero.getFrente();

            for (int i = 0; i < nodoValido - 1; aux = aux.getSig(), i++); // Me posiciono. Condición -1 porque el getFrente "avanza 1 posición".
            while (aux.Info.tipoCasilla != 'N') {
                nodoValido = new Random().nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
                aux = tablero.getFrente();
                for (int i = 0; i < nodoValido; aux = aux.getSig(), i++);
            }
            aux.Info.tipoCasilla = tipoCasilla;

            int posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
            NodoDBL<CasillaTablero> auxTermina = aux;

            // Con esto hago el mismo método funcionable para generar ya sea Escaleras o Serpientes
            if (tipoCasilla == 'E') {
                for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getSig(), i++);
                while (auxTermina.Info.tipoCasilla != 'N') {
                    posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
                    auxTermina = aux;
                    for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getSig(), i++);
                }
                aux.Info.tipoCasilla = 'E';
                auxTermina.Info.tipoCasilla = '+';
            } else if (tipoCasilla == 'S') {
                for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getAnt(), i++);
                while (auxTermina.Info.tipoCasilla != 'N') {
                    posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
                    auxTermina = aux;
                    for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getAnt(), i++);
                }
                aux.Info.tipoCasilla = 'S';
                auxTermina.Info.tipoCasilla = '-';
            }
            aux.Info.posiciones = posicionesPorAvanzar;
//            auxTermina.Info.tipoCasilla = 'T';
        }

    }

    public static void main(String[] args) {
        new SerpientesEscaleras();
    }

}
