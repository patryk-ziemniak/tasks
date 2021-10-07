package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    void shouldFilterOutTestBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("123", "test", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("456", "Project", new ArrayList<>());
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, result.size());
        result.forEach(trelloBoard -> assertFalse(trelloBoard.getName().equalsIgnoreCase("test")));
    }
}
