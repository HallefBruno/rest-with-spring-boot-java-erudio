package br.com.erudio.unittests.mapper.mocks;

import br.com.erudio.data.vo.v1.BookVOV1;
import br.com.erudio.model.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
	return mockEntity(0);
    }

    public BookVOV1 mockVO() {
	return mockVO(0);
    }

    public List<Book> mockEntityList() {
	List<Book> books = new ArrayList<>();
	for (int i = 0; i < 14; i++) {
	    books.add(mockEntity(i));
	}
	return books;
    }

    public List<BookVOV1> mockVOList() {
	List<BookVOV1> persons = new ArrayList<>();
	for (int i = 0; i < 14; i++) {
	    persons.add(mockVO(i));
	}
	return persons;
    }

    public Book mockEntity(Integer number) {
	Book book = new Book();
	book.setAuthor("Some Author" + number);
	book.setTitle("Some Title" + number);
	book.setPrice(BigDecimal.TEN);
	book.setLaunchDate(new Date(1693943739));
	book.setId(number.longValue());
	return book;
    }

    public BookVOV1 mockVO(Integer number) {
	BookVOV1 book = new BookVOV1();
	book.setKey(number.longValue());
	book.setAuthor("Some Author" + number);
	book.setTitle("Some Title" + number);
	book.setPrice(BigDecimal.TEN);
	book.setLaunchDate(new Date(1693943739));
	return book;
    }
}
