import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SimulacionEstadio extends JFrame {
    private Queue<Persona> filaInicial = new LinkedList<>();
    private Queue<Persona> filaAsociados = new LinkedList<>();
    private Queue<Persona> filaAbonados = new LinkedList<>();
    private Queue<Persona> filaBoletaSuelta = new LinkedList<>();

    private JPanel panelFilaInicial;
    private JPanel panelAsociados;
    private JPanel panelAbonados;
    private JPanel panelBoletaSuelta;

    public SimulacionEstadio() {
        setTitle("Simulación entrada Tribuna Occidental");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new BorderLayout());

       // "TRIBUNA OCCIDENTAL"
        JLabel lblTribuna = new JLabel("TRIBUNA OCCIDENTAL" , SwingConstants.CENTER);
        lblTribuna.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTribuna, BorderLayout.NORTH);

        // Imagen original
        ImageIcon iconoOriginal = new ImageIcon("C:\\Users\\Usuario\\Pictures\\Cali.png");

        // Escalar la imagen a un tamaño adecuado (por ejemplo, 60x60 píxeles)
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        // Añadir el icono escalado al JLabel
        lblTribuna.setIcon(iconoEscalado);
        lblTribuna.setHorizontalTextPosition(SwingConstants.LEFT);  // Texto a la izquierda de la imagen
        lblTribuna.setIconTextGap(10);  // Espacio entre la imagen y el texto

        add(lblTribuna, BorderLayout.NORTH);
        //Panel que contiene las columnas de cada fila
        JPanel panelCentral = new JPanel(new GridLayout(1,4)); // Cuatro columnas: Fila inicial, Asociados, Abonados, Boleta Suelta

        // Crear paneles para las filas
        panelFilaInicial = new JPanel();
        panelAsociados = new JPanel();
        panelAbonados = new JPanel();
        panelBoletaSuelta = new JPanel();

        panelFilaInicial.setLayout(new BoxLayout(panelFilaInicial, BoxLayout.Y_AXIS));
        panelAsociados.setLayout(new BoxLayout(panelAsociados, BoxLayout.Y_AXIS));
        panelAbonados.setLayout(new BoxLayout(panelAbonados, BoxLayout.Y_AXIS));
        panelBoletaSuelta.setLayout(new BoxLayout(panelBoletaSuelta, BoxLayout.Y_AXIS));

        // Añadir títulos a los paneles
        panelCentral.add(createLabeledPanel(panelFilaInicial, "Fila Inicial"));
        panelCentral.add(createLabeledPanel(panelAsociados, "Asociados (Puerta A)"));
        panelCentral.add(createLabeledPanel(panelAbonados, "Abonados (Puerta B)"));
        panelCentral.add(createLabeledPanel(panelBoletaSuelta, "Boleta Suelta (Puerta C)"));

        add(panelCentral, BorderLayout.CENTER); // Añadir el panel central al centro del layout

        // Botón para iniciar la simulación
        JButton btnSimular = new JButton("Iniciar Simulación");
        btnSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularIngreso();
            }
        });
        add(btnSimular, BorderLayout.SOUTH);

        // Llenar la fila inicial con los datos proporcionados
        agregarPersonasAFila();
        mostrarFilaInicial();
    }

    // Método para crear paneles con título
    private JPanel createLabeledPanel(JPanel panel, String title) {
        JPanel labeledPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        labeledPanel.add(label, BorderLayout.NORTH);
        labeledPanel.add(panel, BorderLayout.CENTER);
        return labeledPanel;
    }

    // Método para llenar la fila inicial con personas
    private void agregarPersonasAFila() {
        filaInicial.add(new Persona("Isabela Garcia", "Asociado", "A", "13455678"));
        filaInicial.add(new Persona("Juan Esteban Victoria", "Boleta Suelta", "C", "100234889"));
        filaInicial.add(new Persona("Tatiana Mosquera", "Abonado", "B", "113400913"));
        filaInicial.add(new Persona("Juan Esteban Cruz", "Asociado", "A", "999999999"));
        filaInicial.add(new Persona("Juan José Copete", "Boleta Suelta", "C", "111765990"));
        filaInicial.add(new Persona("Juan Camilo Estudillo", "Abonado", "B", "10100804321"));
        filaInicial.add(new Persona("Ana María Velez", "Asociado", "A", "102506777"));
        filaInicial.add(new Persona("Joseph Yulian Garcia", "Boleta Suelta", "C", "124009556"));
        filaInicial.add(new Persona("Juan Andres Perez", "Boleta Suelta", "B", "10100999321"));
        filaInicial.add(new Persona("Robinson Arias", "Abonado", "B", "60114809"));
        filaInicial.add(new Persona("Luis Carlos Morales", "Boleta Suelta", "C", "1234558907"));
        filaInicial.add(new Persona("Federico Copete", "Asociado", "A", "200789666"));
    }

    // Mostrar los botones de la fila inicial
    private void mostrarFilaInicial() {
        panelFilaInicial.removeAll();  // Limpiar el panel antes de añadir nuevos elementos
        for (Persona persona : filaInicial) {
            JButton button = crearBotonPersona(persona);
            panelFilaInicial.add(button);
        }
        panelFilaInicial.revalidate();
        panelFilaInicial.repaint();
    }

    // Método para crear un botón que represente una persona
    private JButton crearBotonPersona(Persona persona) {
        JButton boton = new JButton(persona.nombre);
        if (persona.tipo.equals("Asociado")) {
            boton.setBackground(Color.GREEN);  // Verde para asociados
        } else if (persona.tipo.equals("Abonado")) {
            boton.setBackground(Color.BLUE);   // Azul para abonados
        } else {
            boton.setBackground(Color.GRAY);   // Gris para boleta suelta
        }
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    // Método para simular el ingreso
    private void simularIngreso() {
        Timer timerAsociados = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean asociadosQuedan = moverAsociados();
                if (!asociadosQuedan) {
                    ((Timer) e.getSource()).stop();  // Detener el temporizador cuando ya no queden asociados
                    // Iniciar el temporizador para el resto de personas
                    Timer timerResto = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            boolean restoQuedan = moverRestantes();
                            if (!restoQuedan) {
                                ((Timer) e.getSource()).stop();  // Detener el temporizador cuando todos hayan sido movidos
                            }
                        }
                    });
                    timerResto.start();  // Iniciar el temporizador para el resto
                }
            }
        });
        timerAsociados.start();  // Iniciar el temporizador para asociados
    }

    // Método para mover los asociados a su fila
    private boolean moverAsociados() {
        for (Persona persona : filaInicial) {
            if (persona.tipo.equals("Asociado")) {
                filaAsociados.add(persona);  // Mover a la fila de asociados
                JButton boton = crearBotonPersona(persona);
                panelAsociados.add(boton);
                panelAsociados.revalidate();
                panelAsociados.repaint();
                filaInicial.remove(persona);  // Eliminar de la fila inicial
                mostrarFilaInicial();  // Actualizar la fila inicial
                return true;
            }
        }
        return false;  // No quedan asociados
    }

    // Método para mover los abonados y boleta suelta en orden de llegada
    private boolean moverRestantes() {
        if (!filaInicial.isEmpty()) {
            Persona persona = filaInicial.poll();  // Sacar de la fila inicial en orden
            JButton boton = crearBotonPersona(persona);

            if (persona.tipo.equals("Abonado")) {
                filaAbonados.add(persona);  // Mover a la fila de abonados
                panelAbonados.add(boton);
                panelAbonados.revalidate();
                panelAbonados.repaint();
            } else if (persona.tipo.equals("Boleta Suelta")) {
                filaBoletaSuelta.add(persona);  // Mover a la fila de boleta suelta
                panelBoletaSuelta.add(boton);
                panelBoletaSuelta.revalidate();
                panelBoletaSuelta.repaint();
            }
            mostrarFilaInicial();  // Actualizar la fila inicial
            return true;  // Quedan personas por mover
        }
        return false;  // No quedan personas
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimulacionEstadio().setVisible(true);
            }
        });
    }
}



