package com.demo.Groovy
class Book{
    Long id
    String name
    String title
    String author
    Double price
    String description
    void ChangeBookId(Book book, Long id){
        book.id = id
    }
}