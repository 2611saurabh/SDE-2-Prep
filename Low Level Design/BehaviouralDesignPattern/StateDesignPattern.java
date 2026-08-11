package BehaviouralDesignPattern;

/*
 ==========================================================
                    STATE DESIGN PATTERN
 ==========================================================

 Example: ATM

 The ATM can be in different states:

    1. NoCardState
    2. CardInsertedState
    3. AuthenticatedState

 The same operation behaves differently depending on
 the current state.

 For example:

    withdraw()

    NoCardState          -> "Please insert card"
    CardInsertedState    -> "Please enter PIN"
    AuthenticatedState   -> "Cash withdrawn"

 Instead of putting many if/else conditions inside ATM,
 we create separate classes for each state.

 ==========================================================
*/


// ==========================================================
//                  STATE INTERFACE
// ==========================================================

/*
 ATMState represents the common behavior that every ATM
 state must provide.

 Every state must know how to handle:

    insertCard()
    ejectCard()
    enterPin()
    withdraw()

 The behavior of these methods changes depending on
 the current state.
 */

interface ATMState {

    void insertCard();

    void ejectCard();

    void enterPin();

    void withdraw();
}


// ==========================================================
//                  NO CARD STATE
// ==========================================================

/*
 This state represents:

        ATM has NO card inserted.

 State:

        NoCardState

 Valid operation:

        insertCard()

 Other operations are not allowed.
 */

class NoCardState implements ATMState {

    // Reference to the ATM (Context)
    private ATM atm;

    /*
     Constructor receives ATM reference.

     Why?

     Because after inserting the card, this state needs
     to tell the ATM:

            "Change your state to CardInsertedState"
     */
    public NoCardState(ATM atm) {
        this.atm = atm;
    }


    @Override
    public void insertCard() {

        System.out.println("Card inserted");

        // State transition:
        //
        // NoCardState
        //      ↓
        // CardInsertedState

        atm.setState(new CardInsertedState(atm));
    }


    @Override
    public void ejectCard() {

        System.out.println("No card to eject");
    }


    @Override
    public void enterPin() {

        System.out.println("Please insert card first");
    }


    @Override
    public void withdraw() {

        System.out.println("Please insert card first");
    }
}


// ==========================================================
//                  CARD INSERTED STATE
// ==========================================================

/*
 This state represents:

        Card has been inserted.

 Now the user can:

        1. Enter PIN
        2. Eject card

 But cannot withdraw money yet because PIN is not verified.
 */

class CardInsertedState implements ATMState {

    private ATM atm;

    public CardInsertedState(ATM atm) {
        this.atm = atm;
    }


    @Override
    public void insertCard() {

        System.out.println("Card is already inserted");
    }


    @Override
    public void ejectCard() {

        System.out.println("Card ejected");

        // State transition:
        //
        // CardInsertedState
        //       ↓
        // NoCardState

        atm.setState(new NoCardState(atm));
    }


    @Override
    public void enterPin() {

        System.out.println("PIN entered successfully");

        // State transition:
        //
        // CardInsertedState
        //       ↓
        // AuthenticatedState

        atm.setState(new AuthenticatedState(atm));
    }


    @Override
    public void withdraw() {

        System.out.println("Please enter PIN first");
    }
}


// ==========================================================
//                  AUTHENTICATED STATE
// ==========================================================

/*
 This state represents:

        Card inserted
              +
        PIN verified

 Now the user can withdraw money.
 */

class AuthenticatedState implements ATMState {

    private ATM atm;

    public AuthenticatedState(ATM atm) {
        this.atm = atm;
    }


    @Override
    public void insertCard() {

        System.out.println("Card is already inserted");
    }


    @Override
    public void ejectCard() {

        System.out.println("Card ejected");

        // State transition:
        //
        // AuthenticatedState
        //       ↓
        // NoCardState

        atm.setState(new NoCardState(atm));
    }


    @Override
    public void enterPin() {

        System.out.println("PIN already entered");
    }


    @Override
    public void withdraw() {

        System.out.println("Cash withdrawn successfully");

        // After withdrawal, we can keep the user authenticated.
        // In a real ATM, additional rules would be required.
    }
}


// ==========================================================
//                       CONTEXT
// ==========================================================

/*
 ATM is the CONTEXT.

 The ATM does not implement state-specific behavior itself.

 Instead, it maintains a reference to the current state:

        currentState

 and delegates operations to that state.

 Example:

        atm.withdraw()
              ↓
        currentState.withdraw()
              ↓
        behavior depends on current state
 */

class ATM {

    // Current state of the ATM
    private ATMState currentState;


    /*
     Constructor

     Initially the ATM has no card.

        ATM
         ↓
      NoCardState
     */

    public ATM() {

        currentState = new NoCardState(this);
    }


    /*
     This method allows the current state to change.

     Example:

        NoCardState
             ↓
        CardInsertedState
     */

    public void setState(ATMState state) {

        this.currentState = state;
    }


    /*
     The ATM delegates insertCard() to the current state.
     */

    public void insertCard() {

        currentState.insertCard();
    }


    /*
     The ATM delegates ejectCard() to the current state.
     */

    public void ejectCard() {

        currentState.ejectCard();
    }


    /*
     The ATM delegates enterPin() to the current state.
     */

    public void enterPin() {

        currentState.enterPin();
    }


    /*
     The ATM delegates withdraw() to the current state.
     */

    public void withdraw() {

        currentState.withdraw();
    }
}


// ==========================================================
//                       CLIENT
// ==========================================================

public class StateDesignPattern {

    public static void main(String[] args) {

        /*
         Create ATM.

         Initial state:

                ATM
                 ↓
             NoCardState
         */

        ATM atm = new ATM();


        // ==================================================
        // 1. Try withdrawing without inserting card
        // ==================================================

        atm.withdraw();

        /*
        Output:

        Please insert card first
        */


        // ==================================================
        // 2. Insert card
        // ==================================================

        atm.insertCard();

        /*
        State transition:

        NoCardState
             ↓
        CardInsertedState
        */


        // ==================================================
        // 3. Try withdrawing before entering PIN
        // ==================================================

        atm.withdraw();

        /*
        Output:

        Please enter PIN first
        */


        // ==================================================
        // 4. Enter PIN
        // ==================================================

        atm.enterPin();

        /*
        State transition:

        CardInsertedState
             ↓
        AuthenticatedState
        */


        // ==================================================
        // 5. Withdraw money
        // ==================================================

        atm.withdraw();

        /*
        Output:

        Cash withdrawn successfully
        */


        // ==================================================
        // 6. Eject card
        // ==================================================

        atm.ejectCard();

        /*
        State transition:

        AuthenticatedState
             ↓
        NoCardState
        */


        // ==================================================
        // 7. Try withdrawing again
        // ==================================================

        atm.withdraw();

        /*
        Output:

        Please insert card first
        */
    }
}