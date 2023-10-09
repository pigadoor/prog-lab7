package com.pigadoor.network;

import com.pigadoor.data.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * The {@code Request} class represents a request sent to the server.
 */
public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    /**
     * The command associated with the request.
     */
    private String command;

    /**
     * The Space Marine object related to the request (if applicable).
     */
    private String spaceMarine;

    /**
     * The key associated with the request (if applicable).
     */
    private Integer key;

    /**
     * The health value associated with the request (if applicable).
     */
    private Integer health;

    /**
     * The height value associated with the request (if applicable).
     */
    private Float height;

    /**
     * The script associated with the request (if applicable).
     */
    private String script;

    private String login;

    private String password;

    /**
     * Constructs an empty {@code Request}.
     */
    public Request() {}

    /**
     * Gets the command associated with the request.
     *
     * @return The command associated with the request.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the Space Marine object associated with the request.
     *
     * @return The Space Marine object associated with the request.
     */
    public String getSpaceMarine() {
        return spaceMarine;
    }

    /**
     * Gets the key associated with the request.
     *
     * @return The key associated with the request.
     */
    public Integer getKey() {
        return key;
    }

    /**
     * Gets the health value associated with the request.
     *
     * @return The health value associated with the request.
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * Gets the height value associated with the request.
     *
     * @return The height value associated with the request.
     */
    public Float getHeight() {
        return height;
    }

    /**
     * Gets the script associated with the request.
     *
     * @return The script associated with the request.
     */
    public String getScript() {
        return script;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setSpaceMarine(String spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", spaceMarine=" + spaceMarine +
                ", key=" + key +
                ", health=" + health +
                ", height=" + height +
                ", script=" + script +
                ", login=" + login +
                ", password=" + password +
                '}';
    }

}
