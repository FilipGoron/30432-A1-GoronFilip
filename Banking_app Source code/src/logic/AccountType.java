package logic;

import java.util.Arrays;

public enum AccountType {
	CHECKING, 
	SAVING, 
	SPENDING, 
	CREDIT;
	
	public static String[] getNames(Class<? extends Enum<?>> e) {
		return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

	public static AccountType getTypeFromString(String string) {
		if (string.equals("CHECKING")) {
			return AccountType.CHECKING;
		} else if (string.equals("SAVING")) {
			return AccountType.SAVING;
		} else if (string.equals("SPENDING")) {
			return AccountType.SPENDING;
		} else if (string.equals("CREDIT")) {
			return AccountType.CREDIT;
		}
		return null;
	}

	public static int getInderforType(AccountType type) {
		switch (type) {
		case CHECKING:
			return 0;
		case SAVING:
			return 1;
		case SPENDING:
			return 2;
		case CREDIT:
			return 3;
		}
		return -1;
	}
	
	public static String getStringFromType(AccountType type) {
		switch (type) {
		case CHECKING:
			return "CHECKING";
		case SAVING:
			return "SAVING";
		case SPENDING:
			return "SPENDING";
		case CREDIT:
			return "CREDIT";
		}
		return null;
	}
}
