package com.saftzy.latihanuki;

public class ToDo {

    String titletodo;
    String desctodo;
    String datetodo;
    String idtodo;

    public ToDo() {
    }

    public ToDo(String titletodo, String desctodo, String datetodo, String idtodo) {
        this.titletodo = titletodo;
        this.desctodo = desctodo;
        this.datetodo = datetodo;
        this.idtodo = idtodo;
    }

    public String getTitletodo() {
        return titletodo;
    }

    public void setTitletodo(String titletodo) {
        this.titletodo = titletodo;
    }

    public String getDesctodo() {
        return desctodo;
    }

    public void setDesctodo(String desctodo) {
        this.desctodo = desctodo;
    }

    public String getDatetodo() {
        return datetodo;
    }

    public void setDatetodo(String datetodo) {
        this.datetodo = datetodo;
    }

    public String getIdtodo() {
        return idtodo;
    }

    public void setIdtodo(String idtodo) {
        this.idtodo = idtodo;
    }
}

