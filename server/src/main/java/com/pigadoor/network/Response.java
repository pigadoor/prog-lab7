package com.pigadoor.network;

import java.io.Serial;
import java.io.Serializable;

/**
 * The {@code Response} class represents a response received from the server.
 */
public class Response implements Serializable  {

    @Serial
    private static final long serialVersionUID = 1234568L;

    /**
     * The message contained in the response.
     */
    private String message;

    /**
     * Constructs an empty {@code Response}.
     */
    public Response() {}

    /**
     * Gets the message contained in the response.
     *
     * @return The message contained in the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the response.
     *
     * @param message The message for the response.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }

    /**
     * Parses and returns the message from the response.
     *
     * @return The parsed message from the response.
     */
    public String parse() {
        return this.message;
    }

}
