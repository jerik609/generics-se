package org.example;

/*
    - Any subclass of Weirdo must supply a type argument to Weirdo (go check Weirdo's Son!).
    - That type argument must actually be a subclass of Weirdo.
    - Subclasses of Weirdo (like Weirdo'sSone) follow the idiom that the type argument they
        supply to Weirdo is themselves.
    - Weirdo has a method that returns AnySonOfWeirdo. Combined with the above idiom, this
        allows Weirdo to formulate a contract that says: “any subclass of me must implement
        subclassAwareDeepCopy() and they must declare that it returns that actual subclass”.


    To say that another way: this idiom allows a superclass (such as an Abstract Factory)
    to define methods whose argument types and return types are in terms of the subclass type,
    not the superclass type.
 */

public abstract class Weirdo<AnySonOfWeirdo extends Weirdo<AnySonOfWeirdo>> {

    public abstract AnySonOfWeirdo subclassAwareDeepCopy();

}




