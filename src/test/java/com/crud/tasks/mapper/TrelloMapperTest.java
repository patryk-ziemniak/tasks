package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper mapper;

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto boardDto1 = new TrelloBoardDto("123", "test1", new ArrayList<>());
        trelloBoardDtos.add(boardDto1);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("456", "test2", new ArrayList<>());
        trelloBoardDtos.add(boardDto2);

        //When
        List<TrelloBoard> result = mapper.mapToBoards(trelloBoardDtos);
        TrelloBoard board1 = result.get(0);
        TrelloBoard board2 = result.get(1);

        //Then
        assertEquals(boardDto1.getId(), board1.getId());
        assertEquals(boardDto1.getName(), board1.getName());
        assertEquals(new ArrayList<TrelloList>(), board1.getLists());
        assertEquals(boardDto2.getId(), board2.getId());
        assertEquals(boardDto2.getName(), board2.getName());
        assertEquals(new ArrayList<TrelloList>(), board2.getLists());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("123", "test1", new ArrayList<>());
        trelloBoards.add(board1);
        TrelloBoard board2 = new TrelloBoard("456", "test2", new ArrayList<>());
        trelloBoards.add(board2);

        //When
        List<TrelloBoardDto> result = mapper.mapToBoardsDto(trelloBoards);
        TrelloBoardDto boardDto1 = result.get(0);
        TrelloBoardDto boardDto2 = result.get(1);

        //Then
        assertEquals(board1.getId(), boardDto1.getId());
        assertEquals(board1.getName(), boardDto1.getName());
        assertEquals(new ArrayList<TrelloListDto>(), boardDto1.getLists());
        assertEquals(board2.getId(), boardDto2.getId());
        assertEquals(board2.getName(), boardDto2.getName());
        assertEquals(new ArrayList<TrelloListDto>(), boardDto2.getLists());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto listDto1 = new TrelloListDto("123", "test1", false);
        trelloListDtos.add(listDto1);
        TrelloListDto listDto2 = new TrelloListDto("456", "test2", true);
        trelloListDtos.add(listDto2);

        //When
        List<TrelloList> result = mapper.mapToList(trelloListDtos);
        TrelloList list1 = result.get(0);
        TrelloList list2 = result.get(1);

        //Then
        assertEquals(listDto1.getId(), list1.getId());
        assertEquals(listDto1.getName(), list1.getName());
        assertEquals(listDto1.isClosed(), list1.isClosed());
        assertEquals(listDto2.getId(), list2.getId());
        assertEquals(listDto2.getName(), list2.getName());
        assertEquals(listDto2.isClosed(), list2.isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList list1 = new TrelloList("123", "test1", false);
        trelloLists.add(list1);
        TrelloList list2 = new TrelloList("456", "test2", true);
        trelloLists.add(list2);

        //When
        List<TrelloListDto> result = mapper.mapToListDto(trelloLists);
        TrelloListDto listDto1 = result.get(0);
        TrelloListDto listDto2 = result.get(1);

        //Then
        assertEquals(list1.getId(), listDto1.getId());
        assertEquals(list1.getName(), listDto1.getName());
        assertEquals(list1.isClosed(), listDto1.isClosed());
        assertEquals(list2.getId(), listDto2.getId());
        assertEquals(list2.getName(), listDto2.getName());
        assertEquals(list2.isClosed(), listDto2.isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test desc", "testPos", "12345");

        //When
        TrelloCardDto result = mapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(), result.getName());
        assertEquals(trelloCard.getDescription(), result.getDescription());
        assertEquals(trelloCard.getPos(), result.getPos());
        assertEquals(trelloCard.getListId(), result.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test desc", "testPos", "12345");

        //When
        TrelloCard result = mapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), result.getName());
        assertEquals(trelloCardDto.getDescription(), result.getDescription());
        assertEquals(trelloCardDto.getPos(), result.getPos());
        assertEquals(trelloCardDto.getListId(), result.getListId());
    }
}
