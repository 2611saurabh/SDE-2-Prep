package CreationalDesignPattern;
/*
===========================================================
Design Pattern : Singleton Pattern
Author Notes
===========================================================

Definition:
------------
Singleton ensures that only ONE object of a class exists
per JVM/ClassLoader and provides a global access point to it.

When to use?
------------
✔ Logger
✔ ConfigurationManager
✔ CacheManager
✔ Database Connection Pool
✔ Runtime class
✔ Spring Beans (Default Scope)

When NOT to use?
----------------
✘ User
✘ Order
✘ Employee
✘ Product
✘ ShoppingCart

===========================================================
1. Eager Initialization Singleton
===========================================================

Concept:
--------
Object is created immediately when the class is loaded.

Advantages:
-----------
✔ Thread Safe
✔ Simple implementation

Disadvantages:
--------------
✘ Object is created even if never used.

Flow:
-----
JVM loads class
        ↓
Static variable initialized
        ↓
new Object()
        ↓
Future calls return same object

Git Commit:
------------
feat(singleton): implement eager initialization singleton pattern

===========================================================
*/

class Printer {

    // Static belongs to class, not object.
    // Created only once when JVM initializes this class.
    private static Printer printer = new Printer();

    // Private constructor prevents outside object creation.
    private Printer() {}

    // Global access point.
    public static Printer getInstance() {
        return printer;
    }
}

/*
===========================================================
2. Eager Singleton Example (Logger)
===========================================================

Real-world Example:
-------------------
Application Logger

Reason:
-------
Entire application requires only one Logger object.

Git Commit:
------------
feat(singleton): add logger singleton implementation
===========================================================
*/

class Logger {

    // Constructor is private so no other class can do:
    // new Logger();
    private Logger() {}

    // Created once during class initialization.
    private static Logger logger = new Logger();

    // Returns existing object.
    public static Logger getInstance() {
        return logger;
    }
}

/*
===========================================================
3. Lazy Initialization Singleton
===========================================================

Concept:
--------
Object is NOT created during class loading.

Object is created ONLY when getInstance() is called
for the first time.

Advantages:
-----------
✔ Saves memory
✔ Object created only when required

Disadvantages:
--------------
✘ NOT Thread Safe

Problem:
--------
If two threads enter simultaneously,
both can create separate objects.

Git Commit:
------------
feat(singleton): implement lazy initialization singleton

===========================================================
*/

class DbConnection {

    // Initially no object.
    private static DbConnection instance;

    private DbConnection() {}

    // Object created only when required.
    public static DbConnection getInstance() {

        if (instance == null) {
            instance = new DbConnection();
        }

        return instance;
    }
}

/*
===========================================================
4. Thread Safe Singleton using synchronized
===========================================================

Concept:
--------
Only one thread can execute getInstance()
at a time.

Advantages:
-----------
✔ Thread Safe

Disadvantages:
--------------
✘ Synchronization happens on EVERY call.
✘ Performance overhead.

Flow:
-----
Thread-1
    ↓
Lock acquired
    ↓
Create Object

Thread-2
    ↓
Wait

Thread-1 releases lock
    ↓
Thread-2 returns existing object

Git Commit:
------------
feat(singleton): implement synchronized lazy singleton

===========================================================
*/

class CacheManager {

    private static CacheManager instance;

    private CacheManager() {}

    // Entire method locked.
    public static synchronized CacheManager getInstance() {

        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }
}

/*
===========================================================
5. Double Checked Locking Singleton
===========================================================

Concept:
--------
Synchronize ONLY during first object creation.

Advantages:
-----------
✔ Thread Safe
✔ Better Performance
✔ Lock acquired only once

Why first if()?
---------------
Avoid locking after object already exists.

Why second if()?
----------------
Suppose:

Thread-1
Thread-2

Both enter first if()

Thread-1 acquires lock
Creates object

Thread-2 waits

After Thread-1 exits,
Thread-2 enters synchronized block.

Second if() checks again.

Since object already exists,
Thread-2 does NOT create another object.

Without second if(),
both threads would create objects.

Why volatile?
--------------
Without volatile JVM may reorder instructions.

Normal Object Creation:

1. Allocate Memory
2. Initialize Object
3. Assign Reference

Possible Reordering:

1. Allocate Memory
2. Assign Reference
3. Initialize Object

Another thread may receive a partially
constructed object.

volatile prevents instruction reordering.

Git Commit:
------------
feat(singleton): implement double checked locking singleton

===========================================================
*/

class DoubleCheck {

    // volatile prevents instruction reordering
    // and guarantees visibility among threads.
    private static volatile DoubleCheck instance;

    private DoubleCheck() {}

    public static DoubleCheck getInstance() {

        // First check (No Lock)
        if (instance == null) {

            // Lock only when object not created.
            synchronized (DoubleCheck.class) {

                // Second check
                if (instance == null) {

                    instance = new DoubleCheck();

                }
            }
        }

        return instance;
    }
}

/*
===========================================================
Interview Revision Notes
===========================================================

Q. Why constructor private?
A. Prevent outside object creation.

--------------------------------------------

Q. Why static instance?
A. Static belongs to class.
Only one copy exists.

--------------------------------------------

Q. Why static getInstance()?
A. We cannot create object first.
Need access using ClassName.getInstance()

--------------------------------------------

Q. Why lazy singleton not thread safe?
A. Multiple threads can create multiple objects.

--------------------------------------------

Q. Why synchronized?
A. Only one thread enters at a time.

--------------------------------------------

Q. Why synchronized is slower?
A. Lock acquired on every method call.

--------------------------------------------

Q. Why Double Checked Locking?
A. Lock only during first object creation.

--------------------------------------------

Q. Why volatile?
A. Prevent instruction reordering.

--------------------------------------------

Q. Best Production Choices?

✔ Bill Pugh Singleton
✔ Enum Singleton
✔ Double Checked Locking

/*
===========================================================
6. Bill Pugh Singleton
===========================================================

Invented By:
------------
Bill Pugh

Concept:
--------
Uses a private static inner class.

The Singleton object is NOT created
until getInstance() is called.

How it Works:
-------------
JVM does NOT load the inner class
until it is actually referenced.

Outer Class Loaded
        │
        ▼
No Object Created

getInstance() called
        │
        ▼
Inner Class Loaded
        │
        ▼
Static variable initialized
        │
        ▼
Object Created (Only Once)

Advantages:
-----------
✔ Lazy Initialization
✔ Thread Safe
✔ No synchronized keyword
✔ No performance overhead
✔ Uses JVM class loading mechanism

Disadvantages:
--------------
✘ Slightly difficult to understand initially

Git Commit:
------------
feat(singleton): implement bill pugh singleton
===========================================================
*/

class BillPughSingleton {

    // Private constructor prevents object creation.
    private BillPughSingleton() {}

    /*
     * Inner class is NOT loaded
     * until getInstance() is called.
     */
    private static class SingletonHelper {

        /*
         * JVM guarantees that static variables
         * are initialized only once when the class
         * is loaded.
         */
        private static final BillPughSingleton INSTANCE =
                new BillPughSingleton();
    }

    /*
     * First time getInstance() is called,
     * JVM loads SingletonHelper class.
     *
     * INSTANCE gets created only once.
     */
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}