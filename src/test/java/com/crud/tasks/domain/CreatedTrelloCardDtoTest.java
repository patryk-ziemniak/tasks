package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreatedTrelloCardDtoTest {

    @Test
    public void whenUsingNoArgsConstructorFieldsShouldBeNull() {
        //Given
        //When
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();

        //Then
        assertNull(createdTrelloCardDto.getId());
        assertNull(createdTrelloCardDto.getName());
        assertNull(createdTrelloCardDto.getShortUrl());
    }

    @Test
    public void whenUsingGettersShouldReturnCorrectFieldsContent() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("123", "test", "testUrl");

        //When
        //Then
        assertEquals("123", createdTrelloCardDto.getId());
        assertEquals("test", createdTrelloCardDto.getName());
        assertEquals("testUrl", createdTrelloCardDto.getShortUrl());
    }

    @Test
    public void whenUsingSettersFieldShouldGetUpdated() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("123", "test", "testUrl");

        //When
        createdTrelloCardDto.setId("456");
        createdTrelloCardDto.setName("test2");
        createdTrelloCardDto.setShortUrl("Url");

        //Then
        assertEquals("456", createdTrelloCardDto.getId());
        assertEquals("test2", createdTrelloCardDto.getName());
        assertEquals("Url", createdTrelloCardDto.getShortUrl());
    }
}
