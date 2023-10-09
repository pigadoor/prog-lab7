package com.pigadoor.network;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a response sent from a server to a client.
 */
public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234568L;

    /**
     * The message associated with the response.
     */
    private String message;

    /**
     * Default constructor for Response.
     */
    public Response() {}

    /**
     * Gets the message associated with the response.
     *
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the response.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns a string representation of this Response object.
     *
     * @return A string representation in the format "Response{message='message'}".
     */
    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }


}
