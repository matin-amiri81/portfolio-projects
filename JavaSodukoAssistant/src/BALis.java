import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BALis implements ActionListener {

    SodukoNode node;
    Button button;
    int value;

    public BALis(SodukoNode node, Button button, int value) {
        this.node = node;
        this.button = button;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.node.getPossibleValues()[this.value]){
            this.node.setNodesValue(this.value+1);
        }
    }
}
