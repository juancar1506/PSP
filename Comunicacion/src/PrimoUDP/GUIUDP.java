package PrimoUDP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUIUDP extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField textFNumero;
    private JTextField textFRespuesta;
    private JButton botonEnviar;
    private JLabel labelMensaje;
    private JLabel labelRespuesta;

    private DatagramSocket clientSocket;

    public GUIUDP() {
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
        botonEnviar.addActionListener(this);
        // Acción del botón
        botonEnviar.addActionListener(this);
        // Nos mostrará el resultado
        labelRespuesta = new JLabel("Respuesta:");
        labelRespuesta.setBorder(new EmptyBorder(0,40,0,0));
        textFRespuesta = new JTextField();
        textFRespuesta.setEditable(false);

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

        // Visibilidad a true;
        setVisible(true);

        // Se conecta al servidor
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("Error al iniciar el cliente");
            System.exit(1);
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Variables para leer y enviar los paquetes
        String numeroString = textFNumero.getText().trim();;
        byte[] sendBuffer = numeroString.getBytes();
        byte[] receiveBuffer = new byte[1024];
            try {
                InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
                int port = 12345;
                
                // Se envia el número introducido
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);
                clientSocket.send(sendPacket);

                // Se recive el número
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                String response = new String(receivePacket.getData(),0,receivePacket.getLength());
                textFRespuesta.setText(response);
                textFRespuesta.setBorder(new EmptyBorder(0,20,0,0));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        
    }
    
    public static void main(String[] args) {
        new GUIUDP();
    }
}
