package com.example.paymentsysteminjava.entity.transaction;

public enum TransactionState {
    CREATED(1),
    CHECKED(2),
    CHECK_ERROR(3),
    IN_PROCESS(4),
    PAYING(5),
    SUCCESS(6),
    PAY_ERROR(7);

    TransactionState(int state) {
        this.state = state;
    }

    private final int state;

    public int getState() {
        return state;
    }
}
