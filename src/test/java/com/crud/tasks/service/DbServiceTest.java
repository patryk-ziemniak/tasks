package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void whenEmptyFetchAllTasksShouldReturnEmptyList() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        assertEquals(0, result.size());
    }

    @Test
    void whenFetchAllTasksShouldReturnListOfTasks() {
        //Given
        List<Task> tasks = List.of(
                new Task(1L, "task1", "content1"),
                new Task(2L, "task2", "content2")
        );

        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        assertEquals(2, result.size());
    }

    @Test
    void whenGetTaskByWrongIdShouldReturnTaskWithError() {
        //Given
        when(repository.findById(-1L)).thenReturn(Optional.empty());

        //When
        Task result = dbService.getTaskById(-1L);

        //Then
        assertEquals("No such taskId", result.getTitle());
        assertEquals("ERROR!", result.getContent());
    }

    @Test
    void whenGetTaskByCorrectIdShouldReturnTaskWithThatId() {
        //Given
        Task task = new Task(2L, "task", "description");
        when(repository.findById(2L)).thenReturn(Optional.of(task));

        //When
        Task result = dbService.getTaskById(2L);

        //Then
        assertEquals(2L, result.getId());
        assertEquals("task", result.getTitle());
        assertEquals("description", result.getContent());
    }

    @Test
    void whenSaveTaskShouldReturnTaskWithId() {
        //Given
        Task taskBefore = new Task(null, "test", "testDesc");
        Task taskAfter = new Task(1L, "test", "testDesc");
        when(repository.save(taskBefore)).thenReturn(taskAfter);

        //When
        Task result = dbService.saveTask(taskBefore);

        //Then
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteTask() {
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}
