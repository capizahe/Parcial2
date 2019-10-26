package com.movil.parcial2;

public class RoomLight {

    private  String name;
    private Boolean status;

    @Override
    public String toString() {
        return "RoomLight{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public RoomLight(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
