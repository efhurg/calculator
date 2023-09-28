package constant;

/**
 * 操作符
 */
public enum OP {
    ADD(1, '+'), SUB(2, '-'), MUL(3, '×'), DIV(4, '÷');
    int code;
    char sign;
    OP(int code, char sign) {
        this.code = code;
        this.sign = sign;
    }

    public int getCode() {
        return code;
    }

    public char getSign() {
        return sign;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
