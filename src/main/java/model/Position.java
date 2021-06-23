package model;

public enum Position {
    A1(Side.A),
    A2(Side.A),
    A3(Side.A),
    A4(Side.A),
    A5(Side.A),
    A6(Side.A),
    A7(Side.A),
    B1(Side.B),
    B2(Side.B),
    B3(Side.B),
    B4(Side.B),
    B5(Side.B),
    B6(Side.B),
    B7(Side.B);

    private final Side side;

    Position(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }
}
