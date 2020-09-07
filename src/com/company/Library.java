package com.company;

import java.util.ArrayList;

public class Library {

    private Book book;
    private BookList bookList;
    private ISBNFormatError isbnFormatError;

    ArrayList<Book> addedBooks = new ArrayList<Book>();


    Library() {
        book = new Book();
        bookList = new BookList();
        isbnFormatError = new ISBNFormatError();
    }

    public void addBooks(String author, String title, String year, String isbn) {
        if(!(author.length() ==0) && !(title.length() ==0) && !((year.length()) ==0) && !(isbn.length() ==0)){
            book.setAuthor(author);
            book.setISBN(isbn);
            book.setTitle(title);
            book.setYear(year);

            boolean checker = isbnFormatError.chekcISBN(isbn);
            boolean duplicate = isbnFormatError.checkDuplicates(isbn, addedBooks);
            boolean length = isbnFormatError.checkLength(isbn);

            if (!checker) {
                System.out.println("ISBN can only contain digits from 0-9");
            }
            if (duplicate) {
                System.out.println("A book with the ISBN already exists");
            }
            if (length) {
                System.out.println("ISBN can only contain a mximum of 10 digits only");
            }

            if (checker && !length && !duplicate) {
                System.out.println(title + "\t was added to the library");
                addedBooks.add(book);
                bookList.serializelist(addedBooks);
            }
        }
        else {
           System.out.println("You can not leave fields empty");
        }

    }

    public ArrayList<Book> searchBooks(String query, String type) {
        ArrayList<Book> searchresults = new ArrayList<Book>();
        if(type.equalsIgnoreCase("1")){
            for (Book b : addedBooks) {
                if (query.equalsIgnoreCase(b.getTitle().toString())) {
                    searchresults.add(b);
                }
            }
        }else if(type.equalsIgnoreCase("2")){
            for (Book b : addedBooks) {
                if (query.equalsIgnoreCase(b.getAuthor().toString())) {
                    searchresults.add(b);
                }
            }
        }else if(type.equalsIgnoreCase("3")){
            for (Book b : addedBooks) {
                if (query.equalsIgnoreCase(b.getYear().toString())) {
                    searchresults.add(b);
                }
            }
        }else if(type.equalsIgnoreCase("4")){
            for (Book b : addedBooks) {
                if (query.equalsIgnoreCase(b.getISBN().toString())) {
                    searchresults.add(b);
                }
            }
        }

        return searchresults;
    }

    public ArrayList<Book> displayAll() {
        return addedBooks;
    }



    public void exitfromLibrary () {
        bookList.serializelist(addedBooks);
    }

    public void loadlibrary () {
        addedBooks = bookList.deserializelist();
        System.out.println("\nPreviously added books to tbe library");
        for (Book b : addedBooks) {
            System.out.println(b);
        }
    }

}
