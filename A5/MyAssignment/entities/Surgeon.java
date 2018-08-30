package entities;

/**
 * A doctor with the Surgeon specialty.
 */
public class Surgeon extends Doctor {
    /**
     * Initialize the surgeon with the specified name
     * 
     * @param name the name for the new Surgeon object
     * @precond name != null && !name.equals("")
     */
    public Surgeon(String name) {
        super(name);
    }

    /**
     * Return a string representation of the Surgeon.
     * 
     * @return a string representation of the Surgeon
     */
    public String toString() {
        return "\nSurgeon" + super.toString();
    }

    /**
     * Carry out basic tests of the class.
     */
    public static void main(String[] args) {
        Surgeon s = new Surgeon("Peter");
        System.out.println("The surgeon with the name Peter and no patients is " + s);
    }
}
