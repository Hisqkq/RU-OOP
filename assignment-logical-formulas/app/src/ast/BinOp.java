package ast;

import java.util.function.BinaryOperator;

// defines string representation and precedence of operations
// Every BinOp should have a string representation, a precedence, and an evaluation function.

public enum BinOp implements BinaryOperator<Boolean>{
    AndOp("/\\", 3, (b1, b2) -> b1 && b2),
    OrOp("\\/", 2, (b1, b2) -> b1 || b2),
    ImpliesOp("=>", 1, (b1, b2) -> !b1 || b2);
    
    public final String string;
    public Integer predecessor;
    public final BinaryOperator<Boolean> op;
    
    private BinOp(String string, Integer a,  BinaryOperator<Boolean> op){
        this.string = string;
        this.predecessor = a;
        this.op = op;
    }

    @Override
    public Boolean apply(Boolean leftOperand, Boolean rightOperand) {
        return op.apply(leftOperand, rightOperand);
    }
    @Override
    public String toString() {
        return string;
    }
}
