package com.honeylovely.center.common;

public class CenterConstants {
	
	public static final int BUY_MIN_NUM = 100;
	
	public enum ClientType {
        UNKNOWN(0, "unknown"),
        ANDROID(1, "android"),
        IOS(2, "ios"),
        WP(3, "wp"),
        HTML(4, "html");
        
        private int value;
        private String desc;

        ClientType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        
        public static ClientType getClientType(int value) {
            ClientType[] clientTypes = ClientType.values();
            for (ClientType type : clientTypes) {
                if (type.getValue() == value)
                    return type;
            }

            return null;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
	}
}
