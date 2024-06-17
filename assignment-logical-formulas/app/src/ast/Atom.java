package ast;

public class Atom implements Formula {
    private final String atomID;

    public Atom(String atomID) {
        this.atomID = atomID;
    }

    public String getId() {
        return this.atomID;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }
}
