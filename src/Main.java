import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends JFrame {
    private static String saveUsername;

    private static Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        Conexion conexion = new Conexion("videojuegosdb");
        conn = conexion.conectar();
        // Main container with BorderLayout
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("News"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Log in");

        // Adding ActionListener for the log in button
        loginButton.addActionListener(e -> showLoginForm(frame));

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Center with GridLayout simulating a grid of games
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Creating custom panels to represent character types
        centerPanel.add(createCharacterPanel("Warrior", "Strong and resilient", "./photos/warrior.jfif"));
        centerPanel.add(createCharacterPanel("Archer", "Precise and lethal", "./photos/archer.jfif"));
        centerPanel.add(createCharacterPanel("Wizard", "Master of magical power", "./photos/wizard.jfif"));
        centerPanel.add(createCharacterPanel("Summoner", "Creature controller", "./photos/summoner.jfif"));
        centerPanel.add(createCharacterPanel("Hunter", "Voracious hunter", "./photos/granyhunter.png"));
        centerPanel.add(createCharacterPanel("Tank", "Team shield", "./photos/tank.jfif"));
        centerPanel.add(createCharacterPanel("Slayer", "Unstoppable berserker", "./photos/milfslayer.jfif"));
        centerPanel.add(createCharacterPanel("Assassin", "Swift and stealthy", "./photos/assassin.jfif"));
        centerPanel.add(createCharacterPanel("Paladin", "Team support", "./photos/paladin.jfif"));
        centerPanel.add(createCharacterPanel("Coming Soon", "Exciting things coming", "./photos/new.jfif"));

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Method to create a custom panel to represent a character type
    private static JPanel createCharacterPanel(String name, String description, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Label for the image
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(icon.getIconWidth() + 50, icon.getIconHeight() + 50, java.awt.Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(newImg));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                imageLabel.setIcon(icon);
            }
        });
        panel.add(imageLabel, BorderLayout.CENTER);

        // Panel for name and description
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        JLabel descriptionLabel = new JLabel(description);
        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static void showLoginForm(JFrame parentFrame) {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Log in");
        JButton cancelButton = new JButton("Cancel");

        ActionListener loginAction = e -> {
            String username = userText.getText();
            char[] password = passwordText.getPassword();
            String passwordString = new String(password);
            int userType = authenticateUser(username, passwordString);
            if (userType == 0) {
                saveUsername = username;
                // Log in as user
                loginFrame.dispose(); // Close login frame
                parentFrame.dispose(); // Close current frame
                JFrame userFrame = new JFrame("User"); // Create a new frame for the user
                userFrame.getContentPane().add(createUserPanel());
                userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                userFrame.setSize(1000, 600);
                userFrame.setVisible(true);
            } else if (userType == 1) {
                // Log in as admin
                loginFrame.dispose(); // Close login frame
                parentFrame.dispose(); // Close current frame
                JFrame adminFrame = new JFrame("Admin"); // Create a new frame for the admin
                adminFrame.getContentPane().add(createAdminPanel());
                adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adminFrame.setSize(1000, 600);
                adminFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password.");
            }
        };

        loginButton.addActionListener(loginAction);

        ActionListener enterAction = e -> loginAction.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, ""));

        // Add ActionListener for Enter key press to both text fields
        userText.addActionListener(enterAction);
        passwordText.addActionListener(enterAction);

        cancelButton.addActionListener(e -> loginFrame.dispose());

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(cancelButton);
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }


    // Method to authenticate the user
    private static int authenticateUser(String username, String password) {
        Map<String, String> usersMap = loadUsersFromFile();
        if (usersMap.containsKey(username)) {
            String userData = usersMap.get(username);
            if (userData.equals(password)) {
                return 0;
            }
        } else {
            if (Objects.equals(username.toLowerCase(), "admin") && Objects.equals(password.toLowerCase(), "admin")) {
                return 1;
            }
        }
        return -1;
    }

    // Method to load users from file
    private static Map<String, String> loadUsersFromFile() {
        Map<String, String> users = new HashMap<>();
        try {
            String userQuery = "SELECT NOMBRE,CLAVE FROM JUGADOR";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(userQuery);

            while (rs.next()) {
                users.put(rs.getString("NOMBRE"), rs.getString("CLAVE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        northPanel.add(new JButton("Messages"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("Guild"));
        northPanel.add(new JButton("News"));
        panel.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            // Log out and restart the application
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            topFrame.dispose(); // Close current frame
            createAndShowGUI(); // Restart the application
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Center with GridLayout simulating a grid of characters
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Creating custom panels to represent user's characters

        ArrayList<String[]> characters = usersCharactersList(saveUsername);

        if (characters != null) {
            for (String[] i : characters) {
                centerPanel.add(createCharacterPanel(i[0], i[2]+" Level: "+i[1], i[3]));
            }
        }
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private static ArrayList<String[]> usersCharactersList (String username) {
        try {
            ArrayList<String[]> characters = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT P.NOMBRE,P.NIVEL,P.TIPO,P.IMAGEN FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] characterInfo = new String[4];
                characterInfo[0] = rs.getString("NOMBRE");
                characterInfo[1] = String.valueOf(rs.getInt("NIVEL"));
                characterInfo[2] = rs.getString("TIPO");
                characterInfo[3] = "./photos/"+rs.getString("IMAGEN");
                characters.add(characterInfo);
            }

            return characters;
        } catch (SQLException e) {
            System.out.println("Error found while loading the characters!");
        }
        return null;
    }

    private static JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("User Management"));
        northPanel.add(new JButton("Guild Management"));
        northPanel.add(new JButton("Advanced Statistics"));
        northPanel.add(new JButton("System"));
        northPanel.add(new JButton("Support"));
        panel.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            // Log out and restart the application
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            topFrame.dispose(); // Close current frame
            createAndShowGUI(); // Restart the application
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Center with BoxLayout divided into three parts
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        // Panel for user list on the left
        JList<String> userList = createUserListPanel();
        centerPanel.add(new JScrollPane(userList));

        // Panel for character list on the top right
        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));

        // Panel for possible changes on the bottom right
        // You should implement or remove this panel based on your needs
        // centerPanel.add(createChangesPanel());

        panel.add(centerPanel, BorderLayout.CENTER);

        if (userList != null) {
            userList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    JList<String> list = (JList<String>) e.getSource();
                    String playerName = list.getSelectedValue();
                    createCharactersListPanel(charactersList, playerName);
                }
            });
        }

        return panel;
    }

    // Method to create a JList containing the list of users
    private static JList<String> createUserListPanel() {
        try {
            String userQuery = "SELECT NOMBRE FROM JUGADOR";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(userQuery);

            if (rs.next()) {
                DefaultListModel<String> userListModel = new DefaultListModel<>();
                do {
                    userListModel.addElement(rs.getString("NOMBRE"));
                } while (rs.next());
                return new JList<>(userListModel);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error was found while loading the users");
        }
        return null;
    }

    // Method to create a JList containing the list of characters from the database
    private static void createCharactersListPanel(JList<String> list, String playerName) {
        try {
            String charactersQuery = "SELECT P.NOMBRE FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?";
            PreparedStatement ps = conn.prepareStatement(charactersQuery);
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            DefaultListModel<String> charactersListModel = new DefaultListModel<>();
            list.setModel(charactersListModel);

            while (rs.next()) {
                charactersListModel.addElement(rs.getString("NOMBRE"));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while loading the characters: " + e.getMessage());
        }
    }
}
