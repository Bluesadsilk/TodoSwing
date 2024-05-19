package todoswing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

public class App extends JFrame implements ActionListener {
    private TreeSet<Note> notesArray = initializeTree();
    private JButton createButton;
    private JButton deleteButton;
    private JButton editButton;
    private File file;

    public String getGreeting() {
        return "Hello World!";
    }

    public TreeSet<Note> initializeTree() {
        TreeSet<Note> noteInit = new TreeSet<>();
        try {
            if (file != null && file.exists()) {
                try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(this.file))) {
                    Object noteMaintain = reader.readObject();
                    if (noteMaintain instanceof TreeSet) {
                        noteInit = (TreeSet<Note>) noteMaintain;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return noteInit;
    }

    public void saveChanges(TreeSet<Note> notesArray) {

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
            writer.writeObject(notesArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNote() {
        // Implementación del método para mostrar notas
    }

    public void createNote() {
        JTextField titleField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField contentField = new JTextField();

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JPanel labelsPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1, 2, 2));

        labelsPanel.add(new JLabel("Título:", SwingConstants.RIGHT));
        labelsPanel.add(new JLabel("Fecha (yyyy-MM-dd):", SwingConstants.RIGHT));
        labelsPanel.add(new JLabel("Contenido:", SwingConstants.RIGHT));

        fieldsPanel.add(titleField);
        fieldsPanel.add(dateField);
        fieldsPanel.add(contentField);

        inputPanel.add(labelsPanel, BorderLayout.WEST);
        inputPanel.add(fieldsPanel, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Crear Nueva Nota", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String date = dateField.getText();
            String content = contentField.getText();

            if (title.isEmpty() || date.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Note newNote = new Note(date, title, content);
                this.notesArray.add(newNote);
                System.out.println("Notas antes de agregar nueva nota: " + notesArray);
                this.notesArray.add(newNote);

                System.out.println("Notas antes de agregar nueva nota: " + notesArray);
                this.notesArray.add(newNote);
                System.out.println("Notas después de agregar nueva nota: " + notesArray);
                saveChanges(this.notesArray);

            }
        }
    }

    private JOptionPane editNote() {
        JOptionPane editPane = new JOptionPane();
        return editPane;
    }

    private JOptionPane deleteNote() {
        JOptionPane deletePane = new JOptionPane();
        return deletePane;
    }

    public void customExitOperation() {
        saveChanges(notesArray);
        System.exit(0);
    }

    public App() {
        super("Todo ElJose");
        this.file = new File("./notes.txt");
        this.notesArray = initializeTree();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customExitOperation();
            }
        });

        createButton = new JButton("Crear Nota");
        editButton = new JButton("Editar Nota");
        deleteButton = new JButton("Eliminar Nota");

        createButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        this.add(createButton);
        this.add(deleteButton);
        this.add(editButton);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object catcher = e.getSource();

        if (catcher == createButton) {
            createNote();

        }

        if (catcher == deleteButton) {
            this.add(deleteNote());
        }

        if (catcher == editButton) {
            this.add(editNote());
        }
    }
}
