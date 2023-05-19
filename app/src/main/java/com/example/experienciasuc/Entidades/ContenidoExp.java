package com.example.experienciasuc.Entidades;

public class ContenidoExp{

    private int id_experiencia;
    private String titulo;
    private String subtitulo;
    private String parrafo;

    private String tipocontenido;
    private  String urlcontenido;

    public ContenidoExp() {
    }

    public ContenidoExp(int id_experiencia, String titulo, String subtitulo, String parrafo, String tipocontenido, String urlcontenido) {
        this.id_experiencia = id_experiencia;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.parrafo = parrafo;
        this.tipocontenido = tipocontenido;
        this.urlcontenido = urlcontenido;
    }

    public int getId_experiencia() {
        return id_experiencia;
    }

    public void setId_experiencia(int id_experiencia) {
        this.id_experiencia = id_experiencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

    public String getTipocontenido() {
        return tipocontenido;
    }

    public void setTipocontenido(String tipocontenido) {
        this.tipocontenido = tipocontenido;
    }

    public String getUrlcontenido() {
        return urlcontenido;
    }

    public void setUrlcontenido(String urlcontenido) {
        this.urlcontenido = urlcontenido;
    }
}
