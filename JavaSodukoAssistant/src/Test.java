import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionListener;


public class Test {
    public static void main(String[] args) {
        System.out.println(1%3);
        int i = 9;
        int j = 9;

        int vt = (i/3 -1)*3;
        int vt2 = j/3;

        int v1 = ((i / 3 -1) * 3 + j / 3);

        System.out.println(vt2);
        System.out.println(vt);
        System.out.println(v1);


        /*
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 81; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setLabel((j + 1) + "");
                ActionListener actionListener = e -> {
                    button.setBackground(Color.black);
                };
                button.addActionListener(actionListener);
                panel.add(button);
            }
            frame.add(i+"",panel);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        JInternalFrame frame1 = new JInternalFrame("a", true, true, true, true);
        frame1.setSize(90, 90);
        frame1.setLayout(new GridLayout(3, 3));
        frame1.add(new Button("1"));
        frame1.add(new Button("2"));
        frame.add(frame1);

         */

        /*
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                JInternalFrame frame1 = new JInternalFrame();
                frame1.setLayout(new GridLayout(3, 3));
                frame1.setSize(100, 100);
                for (int k = 0; k < 9; k++){
                    Button button = new Button();
                    button.setBackground(Color.black);
                    button.setLabel((k+1)+"");
                    frame1.add(button);
                }
                frame.add((i*10+j)+"", frame1);
            }
        }

         */

    }
}
