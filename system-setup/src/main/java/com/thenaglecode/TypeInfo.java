package com.thenaglecode;

import com.sun.istack.internal.NotNull;

import java.util.Set;

/**
* Created with IntelliJ IDEA.
* User: Macindows
* Date: 9/23/13
* Time: 8:24 AM
*/
public class TypeInfo {
    private int code;
    private String description;
    private Set<Object> attributes;

    public TypeInfo(@NotNull int code, @NotNull String description, @NotNull Set<Object> attributes){
        setCode(code);
        setDescription(description);
        setAttributes(attributes);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Object> attributes) {
        this.attributes = attributes;
    }
}
