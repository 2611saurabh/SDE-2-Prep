package StructuralDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface Image{
    void display();
}

class RealImage implements Image{
    private String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
    }
    public void display(){
        System.out.println("display");
    }
}

class ProxyImage implements Image{
    private RealImage realImage = null;
    private String fileName;
    ProxyImage(String fileName){
        this.fileName = fileName;
    }
    public void display(){

        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

public class ProxyDesignPattern {

    public static void main(String[] args) {

        Image image = new ProxyImage("cat.jpg");

        image.display();
        image.display();
        image.display();
    }
}
