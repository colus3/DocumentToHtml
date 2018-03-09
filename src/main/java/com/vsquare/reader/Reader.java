package com.vsquare.reader;

public interface Reader<T> {

    T read(String filePath);
}
