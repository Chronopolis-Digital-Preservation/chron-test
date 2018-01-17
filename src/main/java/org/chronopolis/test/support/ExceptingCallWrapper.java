package org.chronopolis.test.support;

import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * Http call which results in an Exception
 *
 * @author shake
 */
public class ExceptingCallWrapper<E> extends CallWrapper<E> {

    private static final String EXCEPTION_TEXT = "test ioexception";

    public ExceptingCallWrapper(E e) {
        super(e);
    }

    @Override
    public Response<E> execute() throws IOException {
        throw new IOException(EXCEPTION_TEXT);
    }

    @Override
    public void enqueue(Callback<E> callback) {
        callback.onFailure(this, new IOException(EXCEPTION_TEXT));
    }
}
