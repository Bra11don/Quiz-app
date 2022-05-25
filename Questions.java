package MCQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Questions implements ActionListener {
    String [] questions = {
            "Where about in the body are the semi-circular canals?",
            "Which year was java created?",
            "Who set out the famous laws of universal gravitation in 1687?",
            "The Welland Ship Canal is in which country?",
            "What are leucocytes?",
            "What type of court would be held amongst prisoners?",
            "The first cable television transmission took place between Washington and where?",
            "Which number is opposite 4 on a dice?"
    };

    String [][] options = {  //this is a 2d array
            {"Heart", "Throat", "Ear", "Throat"},
            {"1989", "1996", "1972", "1492"},
            {"Isaac Newton", "Alexander Fleming", "George Eastman", "Galileo"},
            {"Belgium", "Cyprus", "Canada","Brazil"},
            {"Red Blood Cells", "Viruses", "Fungi","White Blood Cells"},
            {"Kangaroo", "Crocodile", "Donkey"," Leopard"},
            {"Chicago", "Cincinnati", "New York"," Houston"},
            {"2", "3", "5","6"}
            };

    char [] answers = {
            'C',
            'B',
            'A',
            'C',
            'D',
            'A',
            'C',
            'B'
    };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField(); //to hold the qn that we're on
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left =  new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            seconds --;
            seconds_left.setText(String.valueOf(seconds));

            if(seconds<=0){
                displayAnswer();
            }
        }
    }
    );

    //constructor
    public Questions() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(96, 79, 124)); //so that fullscreen is purple
        frame.setLayout(null);
        frame.setResizable(false); //hinders people from resizing the window

        textfield.setBounds(0,0,650,51);
        textfield.setBackground(new Color(96, 79, 124));
        textfield.setForeground(new Color(255,255,255));
        textfield.setFont(new Font("Castellar", Font.BOLD, 30));
//        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false); //people shouldnt change it


        textArea.setBounds(0,51,650,80); //moved the y 5 pixels so that its displayed under the textfield
        textArea.setLineWrap(true); //wrap to the next line incase it goes off the text area
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(96, 79, 124));
        textArea.setForeground(new Color(255,255,255));
        textArea.setFont(new Font("Bookman Old Style", Font.BOLD, 25)); //play with the font type
//        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false); //people shouldnt change it


        buttonA.setBounds(0,130,100,100);
        buttonA.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");
        buttonA.setBackground(new Color(10, 113, 190));
        buttonA.setForeground(new Color(255,255,255));

        buttonB.setBounds(0,230,100,100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        buttonB.setBackground(new Color(213, 180, 12));
        buttonB.setForeground(new Color(255,255,255));

        buttonC.setBounds(0,330,100,100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");
        buttonC.setBackground(new Color(213, 106, 2));
        buttonC.setForeground(new Color(255,255,255));

        buttonD.setBounds(0,430,100,100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");
        buttonD.setBackground(new Color(213, 48, 71));
        buttonD.setForeground(new Color(255,255,255));


        answer_labelA.setBounds(125, 130,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(255,255,255));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelB.setBounds(125, 230,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(255,255,255));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelC.setBounds(125, 330,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(255,255,255));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        answer_labelD.setBounds(125, 430,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(255,255,255));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));


        seconds_left.setBounds(535,510,100,100); //try displaying it at the center
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

//        //this is optional
//        time_label.setBounds(535,475,100,25);
//        time_label.setBackground(new Color(50,50,50));
//        time_label.setForeground(new Color(255,0,0));
//        time_label.setFont(new Font("MV Boli", Font.PLAIN, 15));
//        time_label.setHorizontalAlignment(JTextField.CENTER);
//        time_label.setText("Timer ⌛⏳"); //emojis dont work so no point
////        time_label.setText("Timer U+231B U+23F3"); //emojis dont work so no point

        number_right.setBounds(100,225,400,100);
        number_right.setBackground(new Color(66, 24, 119));
        number_right.setForeground(new Color(25,255,0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 35));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);//experiment what itll look like when its true


        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(66, 24, 119));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false); //try this too


        frame.add(textfield);
        frame.add(textArea);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(seconds_left);
        frame.add(time_label);
        frame.setVisible(true);

        nextQuestion(); //telling next question that its coming up next
    }

    //to move to the next question
    public void nextQuestion() {
        if (index >= total_questions){
            results();
        }
        else{
            //changing the qn displayed
            textfield.setText("Question " + (index+1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options [index][0]);
            answer_labelB.setText(options [index][1]);
            answer_labelC.setText(options [index][2]);
            answer_labelD.setText(options [index][3]);
            timer.start();
        }
    }

    //to handle button clicking effects
    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource()== buttonA){
            answer = 'A';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        if (e.getSource()== buttonB){
            answer = 'B';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        if (e.getSource()== buttonC){
            answer = 'C';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        if (e.getSource()== buttonD){
            answer = 'D';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }

        displayAnswer(); //so we can move to the next section

    }

    //to display the correct answer
    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A'){
            answer_labelA.setForeground(new Color(255,0,0));
        }

        if (answers[index] != 'B'){
            answer_labelB.setForeground(new Color(255,0,0));
        }

        if (answers[index] != 'C'){
            answer_labelC.setForeground(new Color(255,0,0));
        }

        if (answers[index] != 'D'){
            answer_labelD.setForeground(new Color(255,0,0));
        }

        //returning the red color back to original, then moving on to the next part
        Timer pause = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                answer_labelA.setForeground(new Color(255,255,255));
                answer_labelB.setForeground(new Color(255,255,255));
                answer_labelC.setForeground(new Color(255,255,255));
                answer_labelD.setForeground(new Color(255,255,255));

                answer = ' ' ;
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        }
        );

        pause.setRepeats(false); //so that the timer only plays once
        pause.start();

    }
    
    //to display results
    public void results() {
        //disabling the buttons so that people wont press again when theyve already pressed once
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions) *100);
        textfield.setText("RESULTS!");
        frame.setBackground(new Color(50,50,50));
        textArea.setText(" ");
        answer_labelA.setText(" ");
        buttonA.setVisible(false);
        answer_labelB.setText(" ");
        buttonB.setVisible(false);
        answer_labelC.setText(" ");
        buttonC.setVisible(false);
        answer_labelD.setText(" ");
        buttonD.setVisible(false);

        number_right.setText("YOUR SCORE: \n" + correct_guesses + "/" + total_questions);
        percentage.setText(result + "%");
        percentage.setFont(new Font("Cambria Math", Font.PLAIN, 40));

        frame.add(number_right);
        frame.add(percentage);
    }

}
