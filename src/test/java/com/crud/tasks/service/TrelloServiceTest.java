package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void fetchTrelloBoardsShouldReturnEmptyList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(Collections.emptyList());

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, result.size());
    }

    @Test
    void fetchTrelloBoardsShouldReturnList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto(), new TrelloBoardDto());
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(2, result.size());
    }

    @Test
    void failedCreateTrelloCardShouldNotSendEmail() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "pos", "123");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNull(result);
        verify(emailService, times(0)).send(any());
    }

    @Test
    void successfulCreateTrelloCardShouldSendEmail() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "pos", "123");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("123", "test", "url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("mail");

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(result);
        verify(emailService, times(1)).send(any());
    }
}
