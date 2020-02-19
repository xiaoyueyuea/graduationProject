package com.yuelei.util;

import java.util.HashMap;
import java.util.Map;

public class WebJsonResult {

    public static final int SUCCESS_STATE = 0;

    public static final String SUCCESS_MESSAGE = "success";

    public static final int FAILURE_STATE = 1;

    public static final String FAILURE_MESSAGE = "failure";

    public static final Object EMPTY_OBJECT = new Object[0];

    public static final WebJsonResult newSuccess() {
        return newSuccess(EMPTY_OBJECT);
    }

    public static final WebJsonResult newSuccess(Object data) {
        return newSuccess(SUCCESS_MESSAGE, data);
    }

    public static final WebJsonResult newSuccess(String message) {
        return newSuccess(message, EMPTY_OBJECT);
    }

    public static final WebJsonResult newSuccess(String message, Object data) {
        return new WebJsonResult(SUCCESS_STATE, message, data);
    }

    public static final WebJsonResult newFailure() {
        return newFailure(EMPTY_OBJECT);
    }

    public static final WebJsonResult newFailure(Object data) {
        return newFailure(FAILURE_MESSAGE, data);
    }

    public static final WebJsonResult newFailure(String message) {
        return newFailure(message, EMPTY_OBJECT);
    }

    public static final WebJsonResult newFailure(String message, Object data) {
        return new WebJsonResult(FAILURE_STATE, message, data);
    }

    private int state;

    private String message;

    private Object data;

    private final Map<String, Object> attributes;

    public WebJsonResult() {
        this(FAILURE_STATE, FAILURE_MESSAGE, EMPTY_OBJECT, new HashMap<String, Object>());
    }

    /**
     *
     * @param state
     * @param message
     * @param data
     */
    public WebJsonResult(int state, String message, Object data) {
        this(state, message, data, new HashMap<String, Object>());
    }

    /**
     *
     * @param state
     * @param message
     * @param data
     * @param attributes
     */
    public WebJsonResult(int state, String message, Object data, Map<String, Object> attributes) {
        this.state = state;
        this.message = message;
        this.data = data;
        this.attributes = attributes;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes.clear();
        this.attributes.putAll(attributes);
    }

    public void putAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }
}
