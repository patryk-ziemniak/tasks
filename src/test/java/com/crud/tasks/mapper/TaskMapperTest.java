package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper mapper;

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(123L, "test", "testContent");

        //When
        Task task = mapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(123L, "test", "testContent");

        //When
        TaskDto taskDto = mapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(123L, "test1", "testContent1");
        tasks.add(task1);
        Task task2 = new Task(456L, "test2", "testContent2");
        tasks.add(task2);

        //When
        List<TaskDto> result = mapper.mapToTaskDtoList(tasks);
        TaskDto taskDto1 = result.get(0);
        TaskDto taskDto2 = result.get(1);

        //Then
        assertEquals(task1.getId(), taskDto1.getId());
        assertEquals(task1.getTitle(), taskDto1.getTitle());
        assertEquals(task1.getContent(), taskDto1.getContent());
        assertEquals(task2.getId(), taskDto2.getId());
        assertEquals(task2.getTitle(), taskDto2.getTitle());
        assertEquals(task2.getContent(), task2.getContent());
    }
}
