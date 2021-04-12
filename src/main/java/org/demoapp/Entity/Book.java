package org.demoapp.Entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = "Books.findAll", query = "SELECT b FROM Book b ORDER BY b.id", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Book {

    @Id
    @SequenceGenerator(name = "bookSequence", sequenceName = "bookId_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSequence")
    private Long id;
    private String title;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
