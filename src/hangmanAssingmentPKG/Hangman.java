/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanAssingmentPKG;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author keshinijayasinghe
 */
public class Hangman extends javax.swing.JFrame {
     //declaring all the attributes of the Hangman class
    public static  String word = "";
    private static String fWord = "";
    private static String guessWord = "";
    private static int attempts = 0;
    private static int attemptLimit = 7;
    private static boolean xThere = true;
    private static boolean won = false;
    private static boolean ansRight = false;
    private static String[] wordCollection = new String[10];
    private static boolean[] alreadyEntered;
    private static int randomInt = 0;
    public static String level = "";
    
    
    public Hangman(String lvl) {
        
        //resetting already entered array
        alreadyEntered = new boolean[26];
        //taking the passed contructor argument into a variable
        level = lvl;
        
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        //setting the background of the jframe
        ImageIcon img = new ImageIcon("images/hangman2.jpg");
        JLabel lblBack = new JLabel(img);
        setContentPane(lblBack);
        initComponents();
        openWindow();
    }
    
    public void removeLife(int number){
        /*
        In this method, a life will be deducted from the remaining lives. The number of the deducted life is passed as a parameter.
        An image of full black will replace the original image of the life icon.
        */
        if(number==1){
            lblLife1.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm1.png");
            lblImage.setIcon(img);
        } else if(number==2){
            lblLife2.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm2.png");
            lblImage.setIcon(img);
        } else if(number==3){
            lblLife3.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm3.png");
            lblImage.setIcon(img);
        } else if(number==4){
            lblLife4.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm4.png");
            lblImage.setIcon(img);
        } else if(number==5){
            lblLife5.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm5.png");
            lblImage.setIcon(img);
        } else if(number==6){
            lblLife6.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm6.png");
            lblImage.setIcon(img);
        } else {
            lblLife7.setIcon(new ImageIcon(new ImageIcon("images/nolife.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            ImageIcon img = new ImageIcon("images/hm.png");
            lblImage.setIcon(img);
        }
        lblImage.setVisible(true);
    }
    
    
    public static void createWordArray(String levels){
        /*
        This method will create the string array of ten elements. The words used dto populate the array
        will depend of the game level.
        */
        if(levels.equals("easy")){  //3-4 letter words
            wordCollection[0] = "dog";
            wordCollection[1] = "cat";
            wordCollection[2] = "pen";
            wordCollection[3] = "fax";
            wordCollection[4] = "hell";
            wordCollection[5] = "gym";
            wordCollection[6] = "zoo";
            wordCollection[7] = "love";
            wordCollection[8] = "cure";
            wordCollection[9] = "lion";
        }else if(levels.equals("medium")){ //5-7 letter words
            wordCollection[0] = "garage";
            wordCollection[1] = "tiger";
            wordCollection[2] = "shrimp";
            wordCollection[3] = "library";
            wordCollection[4] = "phone";
            wordCollection[5] = "seven";
            wordCollection[6] = "bread";
            wordCollection[7] = "purple";
            wordCollection[8] = "ghost";
            wordCollection[9] = "blouse";
        }else{ //8-10 letter words
            wordCollection[0] = "medicine";
            wordCollection[1] = "identical";
            wordCollection[2] = "eternity";
            wordCollection[3] = "waterfal";
            wordCollection[4] = "mozzarella";
            wordCollection[5] = "beautiful";
            wordCollection[6] = "nightmare";
            wordCollection[7] = "halloween";
            wordCollection[8] = "difficult";
            wordCollection[9] = "territory";
        }
    }
    
    public static void msgBox(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);       
    }
    
    private void openWindow(){
        //reinitiallizing variables
        word = "";
        fWord = "";
        guessWord = "";
        attempts = 0;
        attemptLimit = 7;
        xThere = true;
        won = false;
        ansRight = false;
        
        //creating the word array according to the level
        createWordArray(level);
        int len = wordCollection.length;
        //getting the random interger number
        randomInt = new Random().nextInt(((len-1) - 0) + 1);
       
        //adding images 
        ImageIcon img = new ImageIcon("images/null.png");
        lblImage.setIcon(img);
        lblAnswer.setText("XXXXXXX");
        lblAnswer.setHorizontalAlignment(lblAnswer.CENTER);
        //setting the lives
        
        //ImageIcon img = new ImageIcon("images/life.png");
        lblLife1.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife2.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife3.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife7.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife4.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife6.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        lblLife5.setIcon(new ImageIcon(new ImageIcon("images/life.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        
        //get random interger to use as index
                randomInt = new Random().nextInt(((len-1) - 0) + 1);
              
        //select random word
		word = wordCollection[randomInt];
	//turn it to UPPERCASE
		word = word.toUpperCase();
                
         //make fake word
		for(int i = 0; i<word.length(); i++){
			fWord = fWord + "x";
		}
        //DISPLAY THE WORD
                lblAnswer.setText(fWord);
                guessWord = fWord;
       
    }
    
    
    /**
     * 
     * Creates new form Hangman
     */
   

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainP = new javax.swing.JPanel();
        answerP = new javax.swing.JPanel();
        lblAnswer = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        imageP = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        userP = new javax.swing.JPanel();
        txtChar = new javax.swing.JTextField();
        btnEnter = new javax.swing.JButton();
        lblLife1 = new javax.swing.JLabel();
        lblLife2 = new javax.swing.JLabel();
        lblLife3 = new javax.swing.JLabel();
        lblLife7 = new javax.swing.JLabel();
        lblLife4 = new javax.swing.JLabel();
        lblLife6 = new javax.swing.JLabel();
        lblLife5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        mainP.setBackground(new java.awt.Color(255, 255, 255));
        mainP.setOpaque(false);

        answerP.setBackground(new java.awt.Color(0, 204, 51));
        answerP.setOpaque(false);

        lblAnswer.setFont(new java.awt.Font("Harrington", 0, 80)); // NOI18N
        lblAnswer.setForeground(new java.awt.Color(255, 255, 255));
        lblAnswer.setText("WORD!!");

        btnBack.setBackground(new java.awt.Color(239, 130, 13));
        btnBack.setFont(new java.awt.Font("Harrington", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Go Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout answerPLayout = new javax.swing.GroupLayout(answerP);
        answerP.setLayout(answerPLayout);
        answerPLayout.setHorizontalGroup(
            answerPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerPLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addGroup(answerPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, answerPLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, answerPLayout.createSequentialGroup()
                        .addComponent(lblAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))))
        );
        answerPLayout.setVerticalGroup(
            answerPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        imageP.setBackground(new java.awt.Color(255, 51, 153));
        imageP.setOpaque(false);

        lblImage.setBackground(new java.awt.Color(51, 0, 204));

        javax.swing.GroupLayout imagePLayout = new javax.swing.GroupLayout(imageP);
        imageP.setLayout(imagePLayout);
        imagePLayout.setHorizontalGroup(
            imagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePLayout.createSequentialGroup()
                .addContainerGap(295, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        imagePLayout.setVerticalGroup(
            imagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        userP.setBackground(new java.awt.Color(0, 153, 153));
        userP.setOpaque(false);

        txtChar.setFont(new java.awt.Font("Harrington", 0, 100)); // NOI18N
        txtChar.setForeground(new java.awt.Color(239, 130, 13));
        txtChar.setText("A");
        txtChar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCharActionPerformed(evt);
            }
        });

        btnEnter.setBackground(new java.awt.Color(239, 130, 13));
        btnEnter.setFont(new java.awt.Font("Harrington", 0, 30)); // NOI18N
        btnEnter.setForeground(new java.awt.Color(255, 255, 255));
        btnEnter.setText("ENTER");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userPLayout = new javax.swing.GroupLayout(userP);
        userP.setLayout(userPLayout);
        userPLayout.setHorizontalGroup(
            userPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(txtChar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        userPLayout.setVerticalGroup(
            userPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPLayout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addGroup(userPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );

        lblLife1.setBackground(new java.awt.Color(51, 51, 255));
        lblLife1.setText("b");

        lblLife2.setBackground(new java.awt.Color(51, 51, 255));
        lblLife2.setText("b");

        lblLife3.setBackground(new java.awt.Color(51, 51, 255));
        lblLife3.setText("b");

        lblLife7.setBackground(new java.awt.Color(51, 51, 255));
        lblLife7.setText("b");

        lblLife4.setBackground(new java.awt.Color(51, 51, 255));
        lblLife4.setText("b");

        lblLife6.setBackground(new java.awt.Color(51, 51, 255));
        lblLife6.setText("b");

        lblLife5.setBackground(new java.awt.Color(51, 51, 255));
        lblLife5.setText("b");

        jLabel1.setFont(new java.awt.Font("Harrington", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 130, 13));
        jLabel1.setText("Lives: ");

        javax.swing.GroupLayout mainPLayout = new javax.swing.GroupLayout(mainP);
        mainP.setLayout(mainPLayout);
        mainPLayout.setHorizontalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(lblLife7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLife1)
                        .addGap(755, 755, 755))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                        .addComponent(answerP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(776, 776, 776))))
            .addGroup(mainPLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(imageP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(userP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPLayout.setVerticalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLife5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addComponent(answerP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(mainP, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void txtCharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCharActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCharActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        
        ansRight = false;
        String letter = txtChar.getText();
        char let = letter.toUpperCase().charAt(0);
        //validating whther the character is a letter or a digit
        if(!Character.isLetter(let)){
            msgBox("This is not a letter. Please enter a letter.", "Invalid Input");
            txtChar.setText("");
            
        }else{
            
            int num = Character.getNumericValue(let);
            int index = num-10;
            //checking whether the letter has already been entered
            if(alreadyEntered[index]==true){
                JOptionPane.showMessageDialog(null, "You already entered this letter.", "Error", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
           
                for(int i = 0; i<word.length(); i++){
                        char current = word.charAt(i);

                        if(let==current){
                                guessWord.replace('X', let);
                                String newS = guessWord.substring(0, i) + let + guessWord.substring((i+1), word.length());
                                guessWord = newS;
                                ansRight = true;
                               

                        }

                }
                 alreadyEntered[index] = true;
            
            if(ansRight==false){
                attempts++;
                removeLife(attempts);
            }
            }  
        lblAnswer.setText(guessWord);
        //check if X is still there. 
        int xIndex = guessWord.indexOf('x');
        if(xIndex== -1){
                xThere = false;
                won = true;
        }
             
        if(attempts==7){
            //if lost
            YouLost ls = new YouLost(word);
            this.setVisible(false);
            ls.setVisible(true);
        }
        if(won){
            //if won
            won wn = new won(word);
            this.setVisible(false);
            wn.setVisible(true);
        }
        //resetting the textfield to enter letters
        txtChar.setText("");
            
        }
        
        
    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
            mainMenu mn = new mainMenu();
            this.setVisible(false);
            mn.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command
     * line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel answerP;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEnter;
    private javax.swing.JPanel imageP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLife1;
    private javax.swing.JLabel lblLife2;
    private javax.swing.JLabel lblLife3;
    private javax.swing.JLabel lblLife4;
    private javax.swing.JLabel lblLife5;
    private javax.swing.JLabel lblLife6;
    private javax.swing.JLabel lblLife7;
    private javax.swing.JPanel mainP;
    private javax.swing.JTextField txtChar;
    private javax.swing.JPanel userP;
    // End of variables declaration//GEN-END:variables
}
