package com.vsquare.formater;

public interface Formatter<T, U> {

    T format(String prefix, String suffix, String targetString, U data);
}
