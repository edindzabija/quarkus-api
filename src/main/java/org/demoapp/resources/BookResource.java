package org.demoapp.resources;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demoapp.Entity.Book;
import org.demoapp.repository.BookRepository;

@Path("books")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @GET
    public List<Book> get() {
        return bookRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") Long id) {
        Book data = bookRepository.getBookById(id);
        return Response.ok(data).build();
    }

    @POST
    public Response addBook(Book book) {
        bookRepository.addBook(book);
        return Response.status(201).build();
    }
        
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") Long id, Book book) {
        bookRepository.updateBook(id, book);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long bookId) {
        bookRepository.deleteBook(bookId);
        return Response.status(204).build();
    }

}
