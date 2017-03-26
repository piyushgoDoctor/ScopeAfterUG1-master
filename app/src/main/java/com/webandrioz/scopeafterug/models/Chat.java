package com.webandrioz.scopeafterug.models;

/**
 * Created by gipsy_danger on 26/3/17.
 */

public class Chat {

    String name, msg,email;

    public Chat(String name, String msg, String email) {
        this.name = name;
        this.msg = msg;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
