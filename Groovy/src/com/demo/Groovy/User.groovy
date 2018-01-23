package com.demo.Groovy

import java.time.LocalDate
import java.util.stream.Collectors

class User{
    int id
    String name
    int sex
    LocalDate birth
    List<Book> myBooks
    static void main(String[] args){
        Book book = new Book("id":10, "name":"wslyh123")
        Long id = book?.id
        println(id)
        List<Book> books = new ArrayList<>()
        books.add(book)
        List<String> collect = books.stream().map({ it.name }).collect(Collectors.toList())
        println collect.get(0)
//        Long id = this.GetBookId()
    }
}
