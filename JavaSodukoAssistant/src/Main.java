import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<SodukoNode>> Soduco = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Soduco.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                Soduco.get(i).add(new SodukoNode(j, i, (((i)/3))*3 + ((j)/3)));
            }
        }

        for (int j = 0; j < 9; j++) {
            if(j != 8) {
                Soduco.get(0).get(j).addNeighbor(Soduco.get(0).get(j + 1));
                Soduco.get(0).get(j).addNeighbor(Soduco.get(1).get(j+1));
            }
            if(j!= 0) {
                Soduco.get(0).get(j).addNeighbor(Soduco.get(0).get(j - 1));
                Soduco.get(0).get(j).addNeighbor(Soduco.get(1).get(j-1));
            }
            Soduco.get(0).get(j).addNeighbor(Soduco.get(1).get(j));
        }
        for (int j = 0; j < 9; j++) {
            if(j != 8) {
                Soduco.get(8).get(j).addNeighbor(Soduco.get(8).get(j + 1));
                Soduco.get(8).get(j).addNeighbor(Soduco.get(7).get(j+1));
            }
            if(j!= 0) {
                Soduco.get(8).get(j).addNeighbor(Soduco.get(8).get(j - 1));
                Soduco.get(8).get(j).addNeighbor(Soduco.get(7).get(j-1));
            }
            Soduco.get(8).get(j).addNeighbor(Soduco.get(7).get(j));
        }
        for (int j = 1; j < 8; j++) {
            for (int k = 0; k < 9; k++) {
                if(k != 8) {
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j).get(k + 1));
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j + 1).get(k+1));
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j - 1).get(k+1));
                }
                if(k!=0){
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j).get(k - 1));
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j + 1).get(k-1));
                    Soduco.get(j).get(k).addNeighbor(Soduco.get(j - 1).get(k-1));
                }
                Soduco.get(j).get(k).addNeighbor(Soduco.get(j + 1).get(k));
                Soduco.get(j).get(k).addNeighbor(Soduco.get(j - 1).get(k));

            }
        }

        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 3));
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                SodukoNode node = Soduco.get(i).get(j);
                node.addPanel(panel);
                for (int k = 0; k < 9; k++) {
                    Button button = new Button();
                    button.setLabel((k + 1) + "");
                    button.addActionListener(new BALis(node,button,k));
                    panel.add(button);
                    node.addButton(button,k);
                }
                frame.add(i + "", panel);
            }
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
