package chess;

import java.util.ArrayList;
import java.util.List;

public class TourMovement {
    private static final List<String> allPositionOrdonnes = List.of("a","b","c","d","e","f","g","h");
    private static final List<String> allPositionAbsice = List.of("1","2","3","4","5","6","7","8");
    private static final String TOUR = "T";
    private static final String EMPTY_STRING = "";
    private static final int POSITION_ZERO = 0;
    private static final int POSITION_ONE = 1;

    public static List<String> getPossibleMoves(String position, List<String> whitePieces, List<String> blackPieces){
        validatorInput(position,whitePieces,blackPieces);
        String y = position.split(EMPTY_STRING)[POSITION_ZERO];
        String x = position.split(EMPTY_STRING)[POSITION_ONE];
        List<String> result = new ArrayList<>();
        orderAsc(whitePieces, blackPieces, result, y, x);
        orderDesc(whitePieces,blackPieces, result, y, x);
        return result;
    }

    private static void validatorInput(String position, List<String> whitePieces, List<String> blackPieces) {
        List<String> allPosition = new ArrayList<>();
        allPosition.add(position);
        allPosition.addAll(whitePieces);
        allPosition.addAll(blackPieces);
        piecesPositionValid(allPosition);
    }

    private static void positionValid(boolean positionValid, String position){
        if(!positionValid) throw new IllegalArgumentException(String.format("This position %s does not exist in chess",position));
    }
    private static boolean validator(String position) {
        return position != null && position.length() == 2 && allPositionOrdonnes.contains(position.split(EMPTY_STRING)[POSITION_ZERO])
                && allPositionAbsice.contains(position.split(EMPTY_STRING)[POSITION_ONE]);
    }
    private static void piecesPositionValid(List<String> positions) {
        positions.forEach(it-> positionValid(validator(it),it));
    }

    private static void orderAsc(List<String> whitePieces, List<String> blackPieces, List<String> result, String y, String x) {
        for (int i = Integer.parseInt(x) + 1 ;i < 9; i++) {
            if (possibleMovesHorizontalAxis(whitePieces, blackPieces, result, y, x, i)) break;
        }
        for (int i = allPositionOrdonnes.indexOf(y) + 1; i < 8; i++) {
            if (possibleMovesAxisOfOrdinates(whitePieces, blackPieces, result, y, x, i)) break;
        }
    }

    private static boolean possibleMovesHorizontalAxis(List<String> whitePieces, List<String> blackPieces, List<String> result, String y, String x, int i) {
        String possibleMoves;
        possibleMoves = y + i;
        if (whitePieces.contains(possibleMoves)) {
            return true;
        }
        if (blackPieces.contains(possibleMoves)) {
            setPositionBlackPiecesOrderDesc(result, y, x, i);
            return true;
        }
        result.add(String.format("%s%s%s-%s", TOUR, y, x, possibleMoves));
        return false;
    }

    private static void orderDesc(List<String> whitePieces, List<String> blackPieces, List<String> result, String y, String x) {
        for(int i =Integer.parseInt(x) -1; i > 0 ; i--){
            if (possibleMovesHorizontalAxis(whitePieces, blackPieces, result, y, x, i)) break;
        }
        for (int i = allPositionOrdonnes.indexOf(y)-1; i >= 0; i--){
            if (possibleMovesAxisOfOrdinates(whitePieces, blackPieces, result, y, x, i)) break;
        }
    }

    private static boolean possibleMovesAxisOfOrdinates(List<String> whitePieces, List<String> blackPieces, List<String> result, String y, String x, int i) {
        String possibleMoves;
        possibleMoves = allPositionOrdonnes.get(i) + x ;
        if(whitePieces.contains(possibleMoves)){
            return true;
        }
        if (blackPieces.contains(possibleMoves)) {
            setPositionBlackPiecesOrderAsc(result, y, x, i);
            return true;
        }
        result.add(String.format("%s%s%s-%s",TOUR, y, x,possibleMoves));
        return false;
    }

    private static void setPositionBlackPiecesOrderDesc(List<String> result, String y, String x, int i) {
        String possibleMoves;
        possibleMoves = y + i;
        result.add(String.format("%s%s%sx%s", TOUR, y, x, possibleMoves));
    }

    private static void setPositionBlackPiecesOrderAsc(List<String> result, String y, String x, int i) {
        String possibleMoves;
        possibleMoves = allPositionOrdonnes.get(i) + x;
        result.add(String.format("%s%s%sx%s", TOUR, y, x, possibleMoves));
    }
}
