package serpientesescaleras.gui;

import utils.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Carlos Contreras
 */
public class SerpientesEscaleras extends JFrame implements ActionListener {

    int turno, jugadorActual;
    JPanel informacion;
    JLabel title, lbJugadorActual, lbTurno;
    Tablero tablero = new Tablero();
    JButton btnSiguiente, btnReiniciar, btnSalir;
    JLabel icon;

    public SerpientesEscaleras() {
        super("Serpientes y Escaleras");
        setSize(700, 600);
        setLayout(null);

        turno = 1;
        jugadorActual = 1;

        title = new JLabel("Serpientes y Escaleras");
        title.setBounds(510, 40, 150, 35);
        add(title);

        icon = new JLabel(Rutinas.AjustarImagen("./src/images/icon.png", 120, 120));
        icon.setBounds(480, 60, 200, 200);
        add(icon);

        informacion = new JPanel(new GridLayout(0, 2));
        informacion.setBounds(540, 310, 130, 50);
//        informacion.setBackground(Color.yellow);
        add(informacion);

        informacion.add(new JLabel("Jugador:   ", SwingConstants.RIGHT));
        lbJugadorActual = new JLabel(Integer.toString(jugadorActual));
//        lbJugadorActual.setBounds(480, 100, 100, 100);
        informacion.add(lbJugadorActual);

        informacion.add(new JLabel("Turno:   ", SwingConstants.RIGHT));
        lbTurno = new JLabel(Integer.toString(turno));
//        lbTurno.setBounds(480, 120, 100, 100);
        informacion.add(lbTurno);

        // Se agrega tablero
        tablero = new Tablero();
        tablero.setBounds(25, 35, 450, 500);
        add(tablero);

        // Se añaden botones de interfaz
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(530, 400, 100, 35);
        btnSiguiente.addActionListener(this);
        add(btnSiguiente);

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
        if (botonPulsado == btnSiguiente) {
            avanzarTurno();
            return;
        }
        if (botonPulsado == btnReiniciar) {
            System.out.println("Entró Botón Reiniciar");
            return;
        }
        if (botonPulsado == btnSalir) {
            System.out.println("Entró Botón Salir");
            System.exit(NORMAL);
            return;
        }
    }

    private void avanzarTurno() {
        lbTurno.setText(Integer.toString(++turno));
        System.out.println(turno);
    }

    private class Tablero extends JPanel {

        private Tablero() {
            setLayout(new GridLayout(10, 10));
            setBackground(Color.blue);
            setVisible(true);
        }
    }

}
