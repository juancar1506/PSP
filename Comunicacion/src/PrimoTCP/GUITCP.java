package PrimoTCP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUITCP  extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField textFNumero;
    private JTextField textFRespuesta;
    private JButton botonEnviar;
    private JLabel labelMensaje;
    private JLabel labelRespuesta;
    private JLabel labelEstado;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public GUITCP() {
        // Titulo
        super("¿ES PRIMO?");
        // Ancho y Alto de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu Principal
        // Formulario a enviar
        labelMensaje = new JLabel("Ingrese un número:");
        textFNumero = new JTextField(10);
        botonEnviar = new JButton("Enviar");
        // Acción del botón
        botonEnviar.addActionListener(this);
        // Nos mostrará el resultado
        labelRespuesta = new JLabel("Respuesta:");
        labelRespuesta.setBorder(new EmptyBorder(0,40,0,0));
        textFRespuesta = new JTextField();
        textFRespuesta.setEditable(false);
        // Mensaje de estado
        labelEstado = new JLabel("Desconectado");
        labelEstado.setBorder(new EmptyBorder(0,40,0,0));

        // Componentes principales
        JPanel panelMensaje = new JPanel(new FlowLayout());
        panelMensaje.add(labelMensaje);
        panelMensaje.add(textFNumero);
        panelMensaje.add(botonEnviar);
        add(panelMensaje, BorderLayout.NORTH);
        JPanel panelRespuesta = new JPanel(new GridLayout(2, 1));
        panelRespuesta.add(labelRespuesta);
        panelRespuesta.add(textFRespuesta);
        add(panelRespuesta, BorderLayout.CENTER);

        add(labelEstado, BorderLayout.SOUTH);
        // Visibilidad a true;
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Recuperamos el valor del número al pulssar el bóton Enviar.
        if (e.getSource() == botonEnviar) {
            String numeroIntroducido = textFNumero.getText().trim();
            // Se pide en otro panel la dirección del servidor y el puerto.
            try {
                if (clientSocket == null || clientSocket.isClosed()) {
                    String serverAddress = JOptionPane.showInputDialog(this, "Ingresa la dirección del servidor:");
                    int port = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingresa el número del puerto:"));
                    // Se conecta al servidor
                    clientSocket = new Socket(serverAddress, port);
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    labelEstado.setText("Conectado a " + serverAddress + ":" + port);
                }
                // Se envia el número introducido
                out.println(numeroIntroducido);
                // Se recibe el número y se muetra la solución
                String respuesta = in.readLine();
                textFRespuesta.setText(respuesta);
                textFRespuesta.setBorder(new EmptyBorder(0,20,0,0));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new GUITCP();
    }
}
