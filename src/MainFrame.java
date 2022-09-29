import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.CharacterCodingException;

public class MainFrame extends JFrame {
    private JTextField textField1;
    private JButton prüfenButton;
    private JButton clearButton;
    private JLabel JLabel;
    private JPanel mainPanel;
    private JLabel JLabel1;



    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Kennzeichen-Prüfstelle");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        prüfenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Kennzeichen = textField1.getText();
                int bindestrichPosi1 = Kennzeichen.indexOf("-",0);
                int bindestrichPosi2 = Kennzeichen.indexOf("-", 4);

                String Unterscheidungsnummer = Kennzeichen.substring(0, bindestrichPosi1);

                if(Unterscheidungsnummer.length() < 2 ){

                    bindestrichPosi2 = Kennzeichen.indexOf("-",
                            2);
                }

                    String Erkennungsnummer = Kennzeichen.substring(bindestrichPosi1 + 1, bindestrichPosi2);
                    String Ziffern = Kennzeichen.substring(bindestrichPosi2 + 1);

                JLabel1.setText("Unterscheidungsnummer: " + Unterscheidungsnummer + " Erkennungsnummer: " + Erkennungsnummer + " Ziffern: " + Ziffern);

                try {
                    if( buchstabeUn(Unterscheidungsnummer) == true && buchstabeEn(Erkennungsnummer) && zahl(Ziffern) == true && Kennzeichen.matches( "(.*)-(.*)-(.*)")){
                        JLabel1.setText("Gültiges Kennzeichen: " + Unterscheidungsnummer + "-" + Erkennungsnummer + "-" + Ziffern);

                    } else {

                        JLabel1.setText("Ungültiges Kennzeichen: " + Unterscheidungsnummer + "-" + Erkennungsnummer + "-" + Ziffern);

                    }

                } catch(StringIndexOutOfBoundsException f) {
                    System.out.println("String index out of bounds. String length: " + Kennzeichen.length());
                }



            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             textField1.setText("");
             JLabel1.setText("");
            }
        });
    }


    public static void main(String[]args){

       MainFrame myFrame = new MainFrame();


    }
    public boolean zahl (String pZiffern){
        boolean istzahl = true;
        if(pZiffern.length() > 4 & !pZiffern.isEmpty()) return false;
        for (int i = 0;i< pZiffern.length();i++){
            if(!Character.isDigit(pZiffern.charAt(i))){
                istzahl = false;
            }
        }
        return istzahl;
    }

    public boolean buchstabeUn (String pUnterschiedsnummer){

        boolean istbuchstabe = true;
        if(pUnterschiedsnummer.length() > 3 & !pUnterschiedsnummer.isEmpty()) return false;
        for (int i = 0;i< pUnterschiedsnummer.length();i++){
            if(!Character.isLetter(pUnterschiedsnummer.charAt(i))  ){
                istbuchstabe = false;
            }
        }
        return istbuchstabe;

    }

    public boolean buchstabeEn (String pErkennungsnummer) {
        if(pErkennungsnummer.length() > 2  & !pErkennungsnummer.isEmpty()) return false;
        boolean istbuchstabe = true;
        for (int i = 0;i< pErkennungsnummer.length();i++){
            if(!Character.isLetter(pErkennungsnummer.charAt(i))  ){
                istbuchstabe = false;
            }
        }
        return istbuchstabe;
    }



}


