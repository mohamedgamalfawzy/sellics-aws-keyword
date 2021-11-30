package com.sellics.aws.handlers;

public interface Handler<TInput,TOutput> {
    TOutput handle(TInput entity);
}
