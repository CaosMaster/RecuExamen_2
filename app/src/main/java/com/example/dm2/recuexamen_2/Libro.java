package com.example.dm2.recuexamen_2;

/**
 * Created by dm2 on 14/02/2018.
 */

public class Libro {

    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private int numpaginas;
    private String leido;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNumpaginas(int numpaginas) {
        this.numpaginas = numpaginas;
    }

    public void setLeido(String leido) {
        this.leido = leido;
    }

    public String getTitulo() {

        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumpaginas() {
        return numpaginas;
    }

    public String getLeido() {
        return leido;
    }

    public Libro(String titulo, String autor, String editorial, String isbn, int numpaginas, String leido) {

        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.numpaginas = numpaginas;
        this.leido = leido;
    }
}
