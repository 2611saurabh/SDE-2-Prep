package CreationalDesignPattern;

interface FurnitureFactory{
    Chair createChair();
    Table createTable();
    Sofa CreateSofa();

}


class ModernFurnitureFactory implements FurnitureFactory{

    public Chair createChair() {
            return new ModernChair();
    }

    public Table createTable() {
        return new ModernTable();
    }

    public Sofa CreateSofa() {

        return new ModernSofa();
    }

}

class ModernChair implements Chair{
    public void sitOn(){
        System.out.println("sit on Modern chair");
    }
}

class ModernTable implements Table{

    @Override
    public void sitOn() {
        System.out.println("sit on Modern table");
    }
}

class ModernSofa implements Sofa{

    public void sitOn(){
        System.out.println("sit on Modern sofa");
    }
}

class VictorianChair implements Chair{

    public void sitOn(){

        System.out.println("sit on Victorian chair");
    }
}

class VictorianTable implements Table{

    public void sitOn(){
        System.out.println("Sit on Vicotrian table");
    }
}

class VicotrianSofa implements Sofa{

    public void sitOn(){
        System.out.println("Sit on Vicotrian sofa");
    }
}

class VictorianFurnitureFactory implements FurnitureFactory{

    public Chair createChair() {
        return new VictorianChair();
    }

    public Table createTable() {
        return new VictorianTable();
    }

    public Sofa CreateSofa() {
        return new VicotrianSofa();
    }

}

interface Chair{

    void sitOn();
}

interface Table{
    void sitOn();
}

interface Sofa{

    void sitOn();
}

public class AbstractFactoryDesignPattern {

    public static void main(String[] args) {

        FurnitureFactory furnitureFactory = new VictorianFurnitureFactory();

        Chair chair = furnitureFactory.createChair();
        Table table = furnitureFactory.createTable();
        Sofa sofa = furnitureFactory.CreateSofa();

        chair.sitOn();
        table.sitOn();
        sofa.sitOn();
    }
}
