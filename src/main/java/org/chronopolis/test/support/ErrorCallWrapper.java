package org.chronopolis.test.support;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * Http call which returns an error based on the code and response passed in
 *
 * @author shake
 */
public class ErrorCallWrapper<E> extends CallWrapper<E> {

    private final int code;
    private final String response;
    private final MediaType mediaType = MediaType.parse("text/plain");

    public ErrorCallWrapper(E e, int code, String response) {
        super(e);
        this.code = code;
        this.response = response;
    }

    @Override
    public Response<E> execute() throws IOException {
        return Response.error(code, ResponseBody.create(mediaType, response));
    }

    @Override
    public void enqueue(Callback<E> callback) {
        callback.onResponse(this, Response.error(code, ResponseBody.create(mediaType, response)));
    }
}
