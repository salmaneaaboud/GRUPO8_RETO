import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;

public class Admin {
    private static Map<String, ArrayList<String>> usuariosPersonajesMap;

    public static void main(String[] args) {
        usuariosPersonajesMap = new HashMap<>();

        // Inicialización del HashMap con algunos datos de muestra
        for (int i = 0; i < 10; i++) {
            String usuario = "Usuario" + (i + 1);
            ArrayList<String> personajes = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                personajes.add("Personaje" + (i * 5 + j + 1));
            }
            usuariosPersonajesMap.put(usuario, personajes);
        }

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin");
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
        JList<String> userList = createUserListPanel();
        centerPanel.add(new JScrollPane(userList));

        // Panel para la lista de personajes en la parte superior derecha
        JList<String> charactersList = createCharactersListPanel();
        centerPanel.add(new JScrollPane(charactersList));

        // Panel para los cambios posibles en la parte inferior derecha
        centerPanel.add(createChangesPanel());

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        // Listener para la selección de usuario
        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedUser = userList.getSelectedValue();
                    if (selectedUser != null && usuariosPersonajesMap.containsKey(selectedUser)) {
                        ArrayList<String> personajes = usuariosPersonajesMap.get(selectedUser);
                        DefaultListModel<String> model = new DefaultListModel<>();
                        for (String personaje : personajes) {
                            model.addElement(personaje);
                        }
                        charactersList.setModel(model);
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    // Método para crear un JList que contenga la lista de usuarios
    private static JList<String> createUserListPanel() {
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        for (String usuario : usuariosPersonajesMap.keySet()) {
            userListModel.addElement(usuario);
        }
        JList<String> userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return userList;
    }

    // Método para crear un JList que contenga la lista de personajes
    private static JList<String> createCharactersListPanel() {
        return new JList<>();
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
