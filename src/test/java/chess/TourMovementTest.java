package chess;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TourMovementTest {
    @Test
    public void should_validate_init_position(){
        List<String> result = List.of("Td5-d4","Td5-d3","Td5-e5","Td5-f5","Td5-d6","Td5xd7","Td5-c5","Td5-b5","Td5-a5");
        assertThat(TourMovement.getPossibleMoves("d5", List.of("d2","g5"), List.of("d7","d3","g4"))).containsAll(result);
    }
}