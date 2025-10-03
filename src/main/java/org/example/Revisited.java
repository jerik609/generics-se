package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Revisited {

    public static class Flour {
    }

    public static class Cookie extends Flour {
        String name = "aaa";
    }

    public static class CookieToo extends Cookie {
    }

    public static void main(String[] args) {

        // upper bound -> Cookie is the highest in the hierarchy, the array contains only lower classes (children)
        List<? extends Cookie> cookiesChildren = new ArrayList<>();

        // can read Cookie and all it's superclasses, since we state that the list contains at least a Cookie as the most generic type
        // (Cookie is auto-casted to superclasses as they are more generic type (it's type safe))
        Cookie c = cookiesChildren.getFirst();
        Flour f = cookiesChildren.getFirst();
        //CookieToo = cookieChildren.getFirst(); // can't read specific child, even though children are stored in the list (can't auto cast)

        // cannot write into the list ...
        // the compiler does not know what's in the list and the collection must contain only elements of one specific child type, so it will not permit writes
        //cookieChildren.add(new Cookie());

        // ========================

        // lower bound - cookie is the lowest in the hierarchy, the array contains only it and it's parent
        List<? super Cookie> cookieParents = new ArrayList<>();

        // cannot read anything more specific from the list, except for an Object as the superclass of all
        Object object = cookieParents.getFirst();
        // who knows what exactly is inside, there can be any type higher than Cookie (not having Cookie's properties
        // since it can be a parent of cookie's parent) - no auto-cast would be safe, cast to Object is safe, Object is dumb
        //Cookie cc = cookieParents.getFirst();

        // but we can write into the list anything in the cookie hierarchy, lower than cookie, as cookie and any parent of cookie, is a parent of what's below cookie (or a cookie)
        cookieParents.add(new Cookie());
        cookieParents.add(new CookieToo());
        //cookieParents.add(new Flour()); // won't work - even though Flour is super, we don't know which super of cookie is inside the list

        // ==========================

        // but what about this - a Consumer, or a Supplier ... it's just like a list
        // PECS - producer extends, consumer super

        // ==========================

        // PRODUCER EXTENDS

        // A supplier is like a "list" from which we want to read.
        // Using upper bound, we guarantee, that the list contains Cookie and it's parents (or put differently, implements
        // the Cookie and/or Cookie's parents' "interfaces"). So we can always read at least a Cookie or Flour (or Object).

        Supplier<? extends Cookie> supplierExtends;

        // but, there's a catch here, first we need to deal with implementing the supplier
        // in supplier implementation, we need to think from INSIDE the fictional "list" - which can contain only children
        // of Cookie (or Cookie), which we need to provide
        supplierExtends = () -> new Cookie();
        supplierExtends = () -> new CookieToo();
        //supplierExtends = () -> new Flour(); // won't work

        // with this out of the way, let's move to what gets out of the supplier

        Cookie cookie = supplierExtends.get();
        Flour flour = supplierExtends.get();
        // won't work, since supplier can contain any child of Cookie (or Cookie), compiler can't auto-cast
        //CookieToo cookieToo = supplierExtends.get();

        // and again, "what if" we tried producer super?

        Supplier<? super Cookie> supplierSuper;

        // now we have a lower bound, so it will provide Cookie and parents

        // first, thinking "from inside the list"
        //supplierSuper = () -> new Flour(); // won't work, since it can be any parent, we can't auto-cast,
        // but we can provide Cookie and it's children, as these can take form (polymorphic) of any parent (or Cookie)
        supplierSuper = () -> new CookieToo();
        supplierSuper = () -> new Cookie();

        // but what do we really get from the supplier? not much ... the only specific type is Object, compiler won't
        // provide anything else, since supplier can give us any type of parent of Cookie, but it needs to know
        // one specific type - or a generic type - the only one that works is Object
        Object object1 = supplierSuper.get();
        //Cookie cookie1 = supplierSuper.get(); // nope
        //Flour flour1 = supplierSuper.get(); // sorry
        //CookieToo cookieToo = supplierSuper.get(); // out of luck

        // ------------------------------------------------------------------------------

        // CONSUMER SUPER

        // Think of Consumer as being like a "list" into which we want to write (see above on list operations).
        // Using lower bound (= represents Cookie and its parents), we're saying - we consume (= our "list" accepts/contains)
        // possibly any parent of Cookie thus we may only enter children of Cookie and/or Cookie itself, because they are
        // able to represent any/all possible parents of Cookie.

        // Thinking about Consumer as being a list, we want to fill it with data, which fulfill the Cookie "interface" and
        // all Cookie-parent interfaces. This is valid only for Cookie's children, as due to polymorphism, they can be ANY
        // PARENT (AND/OR COOKIE ITSELF) so when compiler validates, it says to itself - fine, if it were e.g. list of Flour,
        // (? extend Cookie) can be Flour - we're not mixing types - all is fine. This holds for any parent, including Object,
        // so all compiles fine in all cases if only Cookie's children (or Cookie) are entered.

        // !!! (We also intend to perform a Cookie specific action, why can we? - This is tricky and confusing! We know that
        // !!!  we have at least Cookie, because compiler takes care of that (will only permit children), so we can call any Cookie
        // !!!  methods. But don't worry - if we had a real list, we'd not have a Cookie anymore. If we wanted to read a Cookie,
        // !!!  it would not work. But only exactly when we're "inserting the item into the list" we know we have a Cookie, so
        // !!!  all is fine.)
        Consumer<? super Cookie> consumerSuper = (atLeastCookie) -> System.out.println(atLeastCookie.name);

        consumerSuper.accept(new Cookie());
        consumerSuper.accept(new CookieToo());
        //consumerSuper.accept(new Flour()); // this won't work - Flour cannot take the form of "any parent", compiler will not allow type mixing

        // "what if" we tried a consumer extends?
        Consumer<? extends Cookie> consumerExtends = (atLeastCookie) -> System.out.println(atLeastCookie.name);

        // We can write the lambda, even access the attributes of Cookie - because we guarantee, that only Cookie's children
        // or Cookie are on the input. But we cannot use it - the compiler will not permit it, since it only accepts one
        // specific type of the input object - which we cannot reliably provide - it can be Cookie itself, it can be
        // CookieToo - the compiler does not permit this.

        // So these are all invalid:
        //consumerExtends.accept(new Cookie());
        //consumerExtends.accept(new CookieToo());
        //consumerExtends.accept(new Object());
        //consumerExtends.accept(new Flour());
    }

}