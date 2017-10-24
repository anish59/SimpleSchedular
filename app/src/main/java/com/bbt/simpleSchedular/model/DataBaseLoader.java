package com.bbt.simpleSchedular.model;

import java.util.List;

public abstract class DataBaseLoader<T> {

    public abstract void success(List<T> t);

    public abstract void failed(Exception e);

    public void success(T t) {

    }
}