import javax.swing.*;
import java.awt.*;

public class Usuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Página Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Contenedor principal con BorderLayout
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Área Norte con FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Personajes"));
        northPanel.add(new JButton("Mensajes"));
        northPanel.add(new JButton("Clasificación"));
        northPanel.add(new JButton("Misiones"));
        northPanel.add(new JButton("Regiones"));
        northPanel.add(new JButton("Gremio"));
        northPanel.add(new JButton("Noticias"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        // Agregar ActionListener para el botón de inicio de sesión
        // Todavia no implementar

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Centro con GridLayout simulando una cuadrícula de juegos
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        // Crear paneles personalizados para representar los personajes del usuario
        centerPanel.add(createPersonajePanel("Gandalf", "Hechicero Nivel: 45", "./photos/hechicero.jfif"));
        centerPanel.add(createPersonajePanel("Chayane", "Controlador de criaturas", "./photos/invocador.jfif"));
        centerPanel.add(createPersonajePanel("Ibai", "Veloz y sigiloso", "./photos/tanque.jfif"));
        centerPanel.add(createPersonajePanel("Jesus", "Soporte del equipo", "./photos/paladin.jfif"));
        centerPanel.add(createPersonajePanel("Añadir nuevo personaje", "", "./photos/añadir.png"));

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método para crear un panel personalizado para representar un tipo de personaje
    private static JPanel createPersonajePanel(String nombre, String descripcion, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Etiqueta para la imagen
        JLabel imagenLabel = new JLabel(new ImageIcon(imagePath));
        panel.add(imagenLabel, BorderLayout.CENTER);

        // Panel para el nombre y descripción
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nombreLabel = new JLabel(nombre);
        JLabel descripcionLabel = new JLabel(descripcion);
        infoPanel.add(nombreLabel);
        infoPanel.add(descripcionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }
}
