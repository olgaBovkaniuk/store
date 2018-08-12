package com.gmail.olgabovkaniuk.app.dao.model;

public abstract class Identifier {
    protected Long id;

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
