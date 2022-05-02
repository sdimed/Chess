package chess;

import java.util.List;

public class Chess {
    public static void main(String[] args) {
        List<String> result = getPossiblePosition();
        result.forEach(it-> System.out.println("-   " + it));
    }
    public static List<String> getPossiblePosition(){
       return TourMovement.getPossibleMoves("d5", List.of("d2","g5"), List.of("d7","d3","g4"));
    }

}
