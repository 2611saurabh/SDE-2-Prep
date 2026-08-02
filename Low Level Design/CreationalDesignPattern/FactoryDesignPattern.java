package CreationalDesignPattern;

interface Animal{
    public void speak();

}


class Cat implements Animal{

    public void speak(){
        System.out.println("I am a cat");
    }

    Cat(){}
}

class Dog implements Animal{
    public void speak(){
        System.out.println("I am a dog");
    }

    Dog(){}
}

class Lion implements Animal{
    public void speak(){
        System.out.println("I am a lion");
    }

    Lion(){}
}

class Factory{

    public Animal AnimalCreate(String type){

        if(type.equals("Cat")){
            return new Cat();

        }

        else if(type.equals("Dog")){

            return new Dog();
        }

        return null;
    }

    
}

public class FactoryDesignPattern {

    public static void main(String[] args) {

        Animal dog = new Dog();

        dog.speak();
    }
}
