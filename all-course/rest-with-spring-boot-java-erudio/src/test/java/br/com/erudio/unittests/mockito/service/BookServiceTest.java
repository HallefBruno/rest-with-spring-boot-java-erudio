
package br.com.erudio.unittests.mockito.service;

import br.com.erudio.data.vo.v1.BookVOV1;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.service.BookService;
import br.com.erudio.unittests.mapper.mocks.MockBook;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    MockBook input;

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUpMocks() {
	input = new MockBook();
	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
	Book person = input.mockEntity(1);
	person.setId(1L);
	when(bookRepository.findById(1L)).thenReturn(Optional.of(person));
	var result = bookService.findById(1L);
	assertNotNull(result);
	assertNotNull(result.getLinks());
	assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
	assertEquals("Some Author1", result.getAuthor());
    }
    
    @Test
    void testCreate() {
	Book entity = input.mockEntity(1);
	Book persisted = entity;
	persisted.setId(1L);

	BookVOV1 bookVOV1 = input.mockVO(1);
	when(bookRepository.save(entity)).thenReturn(persisted);
	var result = bookService.create(bookVOV1);

	assertNotNull(result);
	assertNotNull(result.getKey());
	assertNotNull(result.getLinks());

	assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
	assertEquals("Some Author1", result.getAuthor());
	assertEquals("Some Title1", result.getTitle());
	assertEquals(BigDecimal.TEN, result.getPrice());
	assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreateWithNullBook() {
	Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
	    bookService.create(null);
	});
	String expectedMessage = "It is not allowed to persist a null object!";
	String actualMessage = exception.getMessage();
	assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
	Book entity = input.mockEntity(1);

	Book persisted = entity;
	persisted.setId(1L);

	BookVOV1 vo = input.mockVO(1);
	vo.setKey(1L);

	when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
	when(bookRepository.save(entity)).thenReturn(persisted);

	var result = bookService.update(vo);

	assertNotNull(result);
	assertNotNull(result.getKey());
	assertNotNull(result.getLinks());

	assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
	assertEquals("Some Author1", result.getAuthor());
	assertEquals("Some Title1", result.getTitle());
	assertEquals(BigDecimal.TEN, result.getPrice());
	assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook() {
	Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
	    bookService.update(null);
	});
	String expectedMessage = "It is not allowed to persist a null object!";
	String actualMessage = exception.getMessage();
	assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
	Book entity = input.mockEntity(1);
	entity.setId(1L);
	when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
	bookService.delete(1L);
    }

    @Test
    void testFindAll() {
	List<Book> list = input.mockEntityList();
	when(bookRepository.findAll()).thenReturn(list);
	
	var people = bookService.findAll();
	assertNotNull(people);
	assertEquals(14, people.size());
	
	var bookOne = people.get(1);
	assertNotNull(bookOne);
	assertNotNull(bookOne.getKey());
	assertNotNull(bookOne.getLinks());
	assertTrue(bookOne.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
	assertEquals("Some Author1", bookOne.getAuthor());
	assertEquals("Some Title1", bookOne.getTitle());
	assertEquals(BigDecimal.TEN, bookOne.getPrice());
	assertNotNull(bookOne.getLaunchDate());

	var bookFour = people.get(4);
	assertNotNull(bookFour);
	assertNotNull(bookFour.getKey());
	assertNotNull(bookFour.getLinks());
	assertTrue(bookFour.getLinks().toString().contains("</api/book/v1/4>;rel=\"self\""));
	assertEquals("Some Author4", bookFour.getAuthor());
	assertEquals("Some Title4", bookFour.getTitle());
	assertEquals(BigDecimal.TEN, bookFour.getPrice());
	assertNotNull(bookFour.getLaunchDate());

	var bookSeven = people.get(7);
	assertNotNull(bookSeven);
	assertNotNull(bookSeven.getKey());
	assertNotNull(bookSeven.getLinks());
	assertTrue(bookSeven.getLinks().toString().contains("</api/book/v1/7>;rel=\"self\""));
	assertEquals("Some Author7", bookSeven.getAuthor());
	assertEquals("Some Title7", bookSeven.getTitle());
	assertEquals(BigDecimal.TEN, bookSeven.getPrice());
	assertNotNull(bookSeven.getLaunchDate());
    }

}
