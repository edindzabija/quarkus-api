package org.demoapp.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.demoapp.Entity.Book;

@ApplicationScoped
public class BookRepository {

    @Inject
    EntityManager entityManager;

    public List<Book> findAll() {
        return entityManager.createNamedQuery("Books.findAll", Book.class).getResultList();
    }

    public Book getBookById(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }
        return book;
    }

    @Transactional
    public void addBook(Book book) {
        entityManager.persist(book);
    }

    @Transactional
    public void updateBook(Long id, Book book) {
        Book bookToUpdate = getBookById(id);

        if (book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            bookToUpdate.setAuthor(book.getAuthor());
        }
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        entityManager.remove(book);
    }
}
