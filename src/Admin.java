import javax.swing.*;
import java.awt.*;

public class Admin {
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
        northPanel.add(new JButton("Gestion de Usuario"));
        northPanel.add(new JButton("Gestion de Gremios"));
        northPanel.add(new JButton("Estadisticas avanzadas"));
        northPanel.add(new JButton("Sistema"));
        northPanel.add(new JButton("Soporte"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Cerrar sesión");
        // Agregar ActionListener para el botón de inicio de sesión
        // Todavía no implementado

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Centro con BoxLayout y división en tres partes
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        // Panel para la lista de usuarios a la izquierda
        centerPanel.add(createUserListPanel());

        // Panel para la lista de personajes en la parte superior derecha
        centerPanel.add(createCharactersListPanel());

        // Panel para los cambios posibles en la parte inferior derecha
        centerPanel.add(createChangesPanel());

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método para crear un panel que contenga la lista de usuarios
    private static JPanel createUserListPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Usuarios"));

        // Simulación de lista de usuarios
        String[] usuarios = new String[30];
        for(int i = 0; i < usuarios.length; i++){
            usuarios[i] = "Usuario" + (i+1);
        }
        JList<String> userList = new JList<>(usuarios);
        JScrollPane scrollPane = new JScrollPane(userList);
        panel.add(scrollPane);

        return panel;
    }

    // Método para crear un panel que contenga la lista de personajes
    private static JPanel createCharactersListPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Personajes"));

        // Simulación de lista de personajes
        String[] personajes =  new String[30];
        for(int i = 0; i < personajes.length; i++){
            personajes[i] = "Personaje" + (i+1);
        }
        JList<String> charactersList = new JList<>(personajes);
        JScrollPane scrollPane = new JScrollPane(charactersList);
        panel.add(scrollPane);

        return panel;
    }

    // Método para crear un panel que contenga los cambios posibles
    private static JPanel createChangesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Cambios Posibles"));

        // Simulación de cambios posibles
        JLabel label = new JLabel("Aquí van los cambios posibles");
        panel.add(label);

        return panel;
    }
}
