package io;

public class SkinData {
    private boolean comprada;
    private String name;

    private int index;

    public SkinData(String name, boolean comprada){
        this.name = name;
        this.comprada = comprada;
    }

    public SkinData(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public boolean getComprada() {
        return comprada;
    }

    public void setComprada(boolean comprada) {
        this.comprada = comprada;
    }

    public void setIndex(int index){
        this.index = index;
    }
}
