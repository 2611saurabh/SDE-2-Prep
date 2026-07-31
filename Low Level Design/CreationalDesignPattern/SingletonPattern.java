package CreationalDesignPattern;

class Printer {

    private static Printer printer = new Printer();

    private Printer() {}

    public static Printer getInstance() {
        return printer;
    }
}

class Logger{

    private Logger() {}

    // here we decalre statci beacuse static type is class level instance so when class is lodaded during then this static is invoked
    //we make constructor private because private allow only class level intialization and constuctor used for creating object
    //now we can't create object in another class so we make here one method similar to getter seeter to access private type in class itself
    // so in getInstance method whose type is itself class and it's static means only invoked when class is loaded in that we return one object
    private static Logger logger = new Logger();

    public static Logger getInstanceLogger(){
        return logger;
    }
}

class DbConnection{

    private DbConnection(){}

    private static DbConnection dbConnection = new DbConnection();

    private static DbConnection getInstanceDB(){
        return dbConnection;
    }
}
public class SingletonPattern {

    Printer p1 = Printer.getInstance();

}
