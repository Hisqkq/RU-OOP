package ast;

import java.util.Set;
import java.util.HashSet;

public class CollectAtomVisitor implements FormulaVisitor<Void, Void> {
    private final Set<String> collectedAtoms = new HashSet<>();

    @Override
    public Void visit(Constant constant, Void a) {
        return null;
    }

    @Override
    public Void visit(Atom atom, Void a) {
        collectedAtoms.add(atom.getId());
        return null;
    }

    @Override
    public Void visit(Not notForm, Void a) {
        notForm.getOperand().accept(this, a);
        return null;
    }

    @Override
    public Void visit(BinaryOperator binForm, Void a) {
        binForm.getLeft().accept(this, a);
        binForm.getRight().accept(this, a);
        return null;
    }

    public Set<String> collectAtoms(Formula form) {
        form.accept(this, null);
        return getCollectedAtoms();
    }

    public Set<String> getCollectedAtoms() {
        return this.collectedAtoms;
    }
}

