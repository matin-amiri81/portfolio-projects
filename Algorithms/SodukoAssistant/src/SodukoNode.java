import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SodukoNode {

    private boolean[] possibleValues;

    private Button[] buttons;

    private int column;
    private int row;
    private int box;
    private ArrayList<SodukoNode> neighbors;
    private JPanel panel;

    public SodukoNode(int column, int row, int box) {
        possibleValues = new boolean[9];
        for (int i = 0; i < 9; i++) {
            possibleValues[i] = true;
        }
        this.column = column;
        this.row = row;
        this.box = box;
        neighbors = new ArrayList<>();
        this.buttons = new Button[9];
    }

    public void setNeighbors(ArrayList<SodukoNode> neighbors) {
        this.neighbors = neighbors;
    }
    public void addNeighbor(SodukoNode neighbor) {
        neighbors.add(neighbor);
    }
    public void addButton(Button button,int index) {
        this.buttons[index] = button;
    }
    public void addPanel(JPanel panel) {
        this.panel = panel;
    }
    public boolean[] getPossibleValues() {
        return possibleValues;
    }
    public ArrayList<SodukoNode> updateImposibleValue(int value, int designation, ArrayList<SodukoNode> previous) {
        if (!previous.contains(this)){
            possibleValues[value - 1] = false;
            buttons[value-1].setBackground(Color.black);
            previous.add(this);
            for (SodukoNode neighbor : neighbors) {
                int[] neighborCoordinates = neighbor.getCoordinates();
                switch (designation) {
                    case 0:
                        if (neighborCoordinates[0] == column) {
                            previous = neighbor.updateImposibleValue(value, 0, previous);
                        }
                        break;
                    case 1:
                        if (neighborCoordinates[1] == row) {
                            previous = neighbor.updateImposibleValue(value, 1,previous);
                        }
                        break;
                    case 2:
                        if (neighborCoordinates[2] == box) {
                            previous = neighbor.updateImposibleValue(value, 2,previous);
                        }
                        break;
                }
            }
        }
        return previous;
    }
    public void setNodesValue(int value) {
        ArrayList<SodukoNode> previous = new ArrayList<>();
        for (Component comp: this.panel.getComponents()) {
            panel.remove(comp);
        }
        panel.setLayout(new GridLayout(1, 1));
        Label label =new Label(value+"");
        label.setBackground(Color.orange);
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        panel.add(label);
        panel.revalidate();
        panel.repaint();

        previous.add(this);
        for (SodukoNode neighbor : neighbors) {
            int[] neighborCoordinates = neighbor.getCoordinates();
            if (neighborCoordinates[0] == column) {
                previous = neighbor.updateImposibleValue(value, 0,previous);
            }
        }
        for (SodukoNode neighbor : neighbors) {
            int[] neighborCoordinates = neighbor.getCoordinates();
            if (neighborCoordinates[1] == row) {
                previous = neighbor.updateImposibleValue(value, 1,previous);
            }
        }
        for (SodukoNode neighbor : neighbors) {
            int[] neighborCoordinates = neighbor.getCoordinates();
            if (neighborCoordinates[2] == box) {
                previous = neighbor.updateImposibleValue(value, 2,previous);
            }
        }

        for (int i = 0; i < 9; i++) {
            possibleValues[i] = false;
            buttons[i].setBackground(Color.black);
        }
        possibleValues[value - 1] = true;
    }

    public int[] getCoordinates() {
        int[] coordinates = {column, row, box};
        return coordinates;
    }
}
