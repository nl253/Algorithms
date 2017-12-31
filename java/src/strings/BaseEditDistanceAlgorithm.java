package strings;

@SuppressWarnings({"WeakerAccess", "ProtectedField", "ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor"})
abstract class BaseEditDistanceAlgorithm {

    protected final String a;
    protected final String b;

    BaseEditDistanceAlgorithm(final String a, final String b) {
        this.a = a;
        this.b = b;
    }

    abstract int editDistance();
}
