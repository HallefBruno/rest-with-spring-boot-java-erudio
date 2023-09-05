package br.com.erudio.service;

import br.com.erudio.controller.BookController;
import br.com.erudio.data.vo.v1.BookVOV1;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repository.BookRepository;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookVOV1 create(BookVOV1 bookVO) {
	if (Objects.isNull(bookVO)) {
	    throw new RequiredObjectIsNullException();
	}
	logger.info("create Book!");
	Book converterForBook = DozerMapper.parseObject(bookVO, Book.class);
	var teste = bookRepository.save(converterForBook);
	BookVOV1 bookVOV1 = DozerMapper.parseObject(teste, BookVOV1.class);
	bookVOV1.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
	return bookVOV1;
    }

    public BookVOV1 findById(Long id) {
	logger.info("Finding one book!");
	var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	var vo = DozerMapper.parseObject(entity, BookVOV1.class);
	vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
	return vo;
    }

    public List<BookVOV1> findAll() {
	logger.info("findAll one Book!");
	List<BookVOV1> personVOs = DozerMapper.parseListObjects(bookRepository.findAll(), BookVOV1.class);
	personVOs.forEach(person -> {
	    person.add(linkTo(methodOn(BookController.class).findById(person.getKey())).withSelfRel());
	});
	return personVOs;
    }
    
    public BookVOV1 update(BookVOV1 book) {

	if (book == null)
	    throw new RequiredObjectIsNullException();

	logger.info("Updating one book!");

	var entity = bookRepository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

	entity.setAuthor(book.getAuthor());
	entity.setLaunchDate(book.getLaunchDate());
	entity.setPrice(book.getPrice());
	entity.setTitle(book.getTitle());

	var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVOV1.class);
	vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
	return vo;
    }
    
    public void delete(Long id) {
	logger.info("Deleting one book!");
	var entity = bookRepository.findById(id);
	bookRepository.delete(entity.get());
    }

}
