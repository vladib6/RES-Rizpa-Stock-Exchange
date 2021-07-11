package com;

public class OnlineUserDTO {
    public OnlineUserDTO(String name,String type) {
        this.name=name;
        this.type=type;
    }
    private String type;
    private String name;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
