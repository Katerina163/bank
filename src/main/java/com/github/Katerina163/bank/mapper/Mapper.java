package com.github.Katerina163.bank.mapper;

public interface Mapper<F, T> {
    T convert(F f);
}