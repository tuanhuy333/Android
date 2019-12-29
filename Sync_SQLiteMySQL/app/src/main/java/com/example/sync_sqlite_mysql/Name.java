package com.example.sync_sqlite_mysql;

public class Name  {
    private int id;
    private String name;
    private int status_sync;

    public Name() {
    }

    public Name(String name, int status_sync) {
        this.name = name;
        this.status_sync = status_sync;
    }

    public Name(int id, String name, int status_sync) {
        this.id = id;
        this.name = name;
        this.status_sync = status_sync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus_sync() {
        return status_sync;
    }

    public void setStatus_sync(int status_sync) {
        this.status_sync = status_sync;
    }
}
