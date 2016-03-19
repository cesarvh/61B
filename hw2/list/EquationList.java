package list;
<<<<<<< HEAD
 
=======

>>>>>>> 090c93dc4065bbe7fed1839581959c1e0f32df5f
public class EquationList {
    public EquationList next;
    public String equation;
    public int result;

    /**
     * Example of how EquationLists work: this list has two stored equations. 
     * +-------------------+    +-------------------+
     * | EquationList      |    | EquationList      |
     * +-------------------+    +-------------------+
     * | equation: "1 + 2" |    | equation: "3 * 4" |
     * | result:   3       |    | result:   12      |
     * | next:     --------+--->| next:     null    |
     * +-------------------+    +-------------------+
    **/

    public EquationList(String equation, int result, EquationList next) {
        this.equation = equation;
        this.result = result;
        this.next = next;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 090c93dc4065bbe7fed1839581959c1e0f32df5f
