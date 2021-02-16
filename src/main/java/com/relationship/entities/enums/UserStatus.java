package com.relationship.entities.enums;

public enum UserStatus {
	
	ACTIVE(1),
    SUSPENDED(2),
    BLOCKED(3);

    private int code;

    private UserStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    // Verifica se o código existe
    public static UserStatus valueOf(int code){
        for (UserStatus value: UserStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
