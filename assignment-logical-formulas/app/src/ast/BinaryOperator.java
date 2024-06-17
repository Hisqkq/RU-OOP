package ast;



// use strategy pattern to realize different behaviours

// represents a node in the tree representation of a formula
public class BinaryOperator implements Formula {
    private BinOp binOp;
    private Formula leftOperand;
    private Formula rightOperand;
    
    public BinaryOperator(BinOp op, Formula left, Formula right) {
        this.binOp = op;
        this.leftOperand = left;
        this.rightOperand = right;
    }

    public Formula getLeft() {
        return this.leftOperand;
    }

    public Formula getRight() {
        return this.rightOperand;
    }

    public BinOp getOp() {
        return this.binOp;
    }
    
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }
}


