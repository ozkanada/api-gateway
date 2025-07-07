package com.oteller.rezervin.gateway.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
	EMAIL_NOT_UNIQUE_EXCEPTION(1500),
	ACTIVATION_NOTIFICATION_EXCEPTION(1501),
	USER_NOT_FOUND_EXCEPTION(1502),
	INVALID_TOKEN_EXCEPTION(1503),
	LOGOUT_SUCCESSFULY(1504);

    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }

    @Override
    public String getFriendlyMessageCodeToString() {
        return String.valueOf(value);
    }
}
