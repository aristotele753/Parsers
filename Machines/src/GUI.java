import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUI implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JTextArea textArea;
    private static JButton button;
    private Grammar grammar;

    public GUI() {

        frame = new JFrame(); // instanzia un frame, ovvero una finestra
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termina il programma alla chiusura del frame
        
        /* PANEL */
        panel = new JPanel(); // instanzia un pannello 
        frame.add(panel); // aggiunge il pannello alla finestra, ma ancora è privo di forma
        panel.setLayout(null);

        /* LABEL */
        label = new JLabel("<html>Digita la tua grammatica.<br/>Le doppie virgolette \", non i doppi apici, denotano la stringa vuota.</html>");
        label.setBounds(20, 10, 300, 40);
        panel.add(label);
        
        /* TEXT AREA, per accettare più produzioni */
        textArea = new JTextArea();
        textArea.setBounds(15, 60, 250, 250);
        panel.add(textArea);

        /* BUTTON */
        button = new JButton("Send");
        int textAreaPosition = textArea.getY() + (int)textArea.getHeight();
        button.setBounds(200, textAreaPosition + 10, 70, 20);
        button.addActionListener(this); // l'argomento del metodo DEVE essere l'instanza stessa dell'oggetto genitore
        panel.add(button);

        /* SI RENDE VISIBILE LA FINESTRA */
        frame.setVisible(true); // si rende il frame visibile

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Grammar tempGrammar = new Grammar();

        String input = textArea.getText();
        String[] tokenizedLines = input.split("\\r?\\n");

        for (String tokenizedLine : tokenizedLines) {                                    // itero sull'array String "String[]" di token a due a due
        
            String[] tokenizedProduction = tokenizedLine.split("( )*->( )*");      // tokenizzo la produzione in un array con due oggetti

            // tokenizedProduction è un array di {head, body}, quindi i = 0 è la head e i = 1 la production
            
            Symbol tempHead = null;                                                    // la testa della produzione, di posizione 0, è inizializzata a null
            for (int j = 0; j < tokenizedProduction[0].length(); j++) {                // qua si sta iterando su un oggetto "String" della head, di posizione 0 in tokenizedProduction[0]

                String[] headSymbolList = tokenizedProduction[0].split("\\s+");  // la testa potrebbe essere a più non terminali, e quindi non essere context-free, oppure si potrebbero essere digitati erroneamente degli spazi
                int headCounter = 0;                                                   // deve esattamente esserci un terminale 
                String headString = "";
                for (String symbol : headSymbolList) {
                    if (!symbol.isEmpty()) {
                        headCounter++;          // il non terminale è presente
                        headString = symbol;    // e in quel caso, mettilo da parte; supponendo che ce ne sia più di uno, verrà comunque lanciata un'eccezione
                    }
                }
                if (headCounter != 1) throw new IllegalArgumentException();
                tempHead = new Symbol(headString);

            }

            List<Symbol> tempBody = new ArrayList<>();
            String[] bodySymbolList = tokenizedProduction[1].split("\\s+");  // i simboli separati da spazi sono considerati due entità differenti

            for (String bodySymbol : bodySymbolList) {
                if (!bodySymbol.isBlank()) {
                    Symbol newBodySymbol = new Symbol(bodySymbol); 
                    tempBody.add(newBodySymbol);
                }
            }

            /*  USARE QUESTO SNIPPET DI CODICE SE SI VUOLE ESTRARRE IL SINGOLO SIMBOLO DALLA GRAMMATICA 
                    
                for (int k = 0; k < tokenizedProduction[1].length(); k++) {        // qua si sta iterando su un oggetto "String" del corpo, di posizione 1

                char charBodySymbol = tokenizedProduction[1].charAt(k);            // estraggo il singolo carattere
                String stringBodySymbol = Character.toString(charBodySymbol);      // lo casto in String per poterlo trattare
                if (!stringBodySymbol.isBlank()) {                                 // controllo anche che nel token non ci siano spazi vuoti
                    Symbol tempBodySymbol = new Symbol(stringBodySymbol);          // lo rendo un oggetto Symbol
                    tempBody.add(tempBodySymbol);                                  // lo aggiungo all'ArrayList del corpo della produzione
                }

            }*/

            Production tempProduction = new Production(tempHead, tempBody);        // ho generato la produzione di una singola riga
            tempGrammar.addProduction(tempProduction);                             // in questo modo non si salvano più grammatiche in memoria
            
        }

        this.grammar = new Grammar(tempGrammar.getProductionList());
        System.out.println(grammar);

        System.out.println(grammar.deriveEpsilon(grammar.getProduction(2).getHead()));

    }

}