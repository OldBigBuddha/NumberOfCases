package kyoto.freeprojects.oldbigbuddha.number_of_cases;

/**
 * Created by BigBuddha on 2017/08/08.
 */

public enum Symbol {
    PERMUTATION("p"),
    COMBINATION("c"),
    HOMOGENEOUSPRODUCT("h"),
    FACTORIAL("!"), ;
    public final String ope;
    Symbol(String ope) {
        this.ope = ope;
    }
}
