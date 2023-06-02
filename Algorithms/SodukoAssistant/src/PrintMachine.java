import java.util.ArrayList;

public class PrintMachine {
    public static void PrintItAll(ArrayList<ArrayList<SodukoNode>> graph){
        for (int i = 0; i < 9; i++){
            String topline = "";
            String midline = "";
            String bottomline = "";
            for (int j = 0; j < 9; j++){
                boolean[] nodeValues = graph.get(i).get(j).getPossibleValues();
                for (int k = 0; k < 3; k++) {
                    if(nodeValues[k]){
                        topline += " "+ (k + 1) + " ";
                    }
                    else{
                        topline += " X ";
                    }
                }
                for (int k = 3; k < 6; k++) {
                    if(nodeValues[k]){
                        midline += " "+ (k + 1) +" ";
                    }
                    else{
                        midline += " X ";
                    }
                }
                for (int k = 6; k < 9; k++) {
                    if(nodeValues[k]){
                        bottomline += " "+(k + 1) +" ";
                    }
                    else{
                        bottomline += " X ";
                    }
                }
                if((j+1)%3 == 0) {
                    topline += " || ";
                    midline += " || ";
                    bottomline += " || ";
                }
                else {
                    topline += " | ";
                    midline += " | ";
                    bottomline += " | ";
                }
            }
            System.out.println(topline);
            System.out.println(midline);
            System.out.println(bottomline);
            if((i+1)%3 == 0) {
                System.out.println("--------------------------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("");
            }
        }

    }
}
