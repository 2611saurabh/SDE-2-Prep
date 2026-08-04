package StructuralDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent{

    void showDetails();
    int getSize();
}

class File implements FileSystemComponent{

    private String name;
    private int size;

    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    public void showDetails() {
        System.out.println(name + " (" + size + " KB)");
    }

    public int getSize() {

        return size;
    }
}

class Folder implements FileSystemComponent{

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name){
        this.name = name;
    }

    public void add(FileSystemComponent component){

        children.add(component);
    }

    public void remove(FileSystemComponent component){
        children.remove(component);
    }

    public void showDetails() {
        System.out.println(name);
        for(FileSystemComponent file : children){
            file.showDetails();
        }
    }

    public int getSize() {
        int size = 0;
        for(int i = 0; i < children.size(); i++){
            size += children.get(i).getSize();
        }

        return size;
    }
}

public class CompositeDesignPattern {

    public static void main(String[] args) {

        Folder root = new Folder("Root");

        Folder projects = new Folder("Projects");

        projects.add(new File("Java.pdf",20));
        projects.add(new File("Spring.pdf",30));

        root.add(new File("Resume.pdf",10));
        root.add(new File("Notes.txt",5));
        root.add(projects);

        System.out.println(root.getSize());
        root.showDetails();

    }
}
