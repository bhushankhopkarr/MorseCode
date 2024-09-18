import javax.swing.*;
import java.awt.*;

public class MorseCodeTranslatorGUI extends JFrame {
    //textInputArea - user input (test to be translated)
    //morseCodeArea - translated text into morse code
    private JTextArea textInputArea, morseCodeArea;

    public MorseCodeTranslatorGUI() {
        //adding title to the application
        super("Morse Code Translator");

        //sets the layout of the application
        setSize(new Dimension(540, 760));

        //prevent the application from being resized
        setResizable(false);

        // setting the layout of the application to null which allows us to place the components wherever we want
        setLayout(null);

        // exits the application when the close button is clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // setting the background color of the application to black
        getContentPane().setBackground(Color.BLACK);

        // places the application in the center of the screen
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        //title label
        JLabel titleLabel = new JLabel("Morse Code Translator");

        // changes the font size for the label and the font weight
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        //changes the font color of the label to white
        titleLabel.setForeground(Color.WHITE);

        // centeres text (relative to its container's width)
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // set the x, y position and width and height dimensions
        // to make sure that the title aligns ti the cnter of the GUI, we made it the same size
        titleLabel.setBounds(0, 20, 540, 100);
        
        // text input field
        JLabel textInputLabel = new JLabel("Enter text here:");
        textInputLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        textInputLabel.setForeground(Color.WHITE);
        textInputLabel.setBounds(20, 100, 200, 30);

        textInputArea = new JTextArea();
        textInputArea.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        // simulates padding of 10px in thr text area
        textInputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // makes it so that words wrap to the next line if they exceed the width of the text area
        textInputArea.setLineWrap(true);

        // makes it so that when the words do get wrap, the word doesn't get split off
        textInputArea.setWrapStyleWord(true);

        // creates a scroll bar for the text area
        JScrollPane textInputScroll = new JScrollPane(textInputArea);
        textInputScroll.setBounds(20, 132, 484, 236);

        // morse code input
        JLabel morseCodeInputLabel = new JLabel("Morse Code:");
        morseCodeInputLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        morseCodeInputLabel.setForeground(Color.WHITE);
        morseCodeInputLabel.setBounds(20, 390, 200, 30);

        morseCodeArea = new JTextArea();
        morseCodeArea.setFont(new Font("Dialog", Font.PLAIN, 18)); 
        morseCodeArea.setEditable(false);
        morseCodeArea.setWrapStyleWord(true);
        morseCodeArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // creates a scroll bar for the morse code text area
        JScrollPane morseCodeScroll = new JScrollPane(morseCodeArea);
        morseCodeScroll.setBounds(20, 430, 484, 236);

        //add to GUI 
        add(titleLabel);
        add(textInputLabel);
        add(textInputScroll);
        add(morseCodeInputLabel);
        add(morseCodeScroll);
    }
}