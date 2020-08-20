package org.unibl.etf.bp.store.util;

import org.unibl.etf.bp.store.data.DataAccessFactory;
import org.unibl.etf.bp.store.data.DataAccessFactoryType;

public class Utilities {
	
	private static DataAccessFactory factory;

	public static DataAccessFactory getDataAccessFactory() {
		if (factory == null) {
			factory = DataAccessFactory.getFactory(DataAccessFactoryType.MySQL);
		}
		return factory;
	}
	

	public static final String[] YES_NO_OPTIONS = new String[] {
			"   Da   ",
			"   Ne   "
	};

	public static boolean isTextValid(String text) {
		boolean retVal = true;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!(Character.isLetterOrDigit(c) || c == '-' || c == '_'
					|| c == ' ' || c == '.' || c == ',')) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	public static boolean isSearchPatternValid(String pattern) {
		boolean retVal = true;
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			if (!(Character.isLetterOrDigit(c) || c == '-' || c == '_'
					|| c == ' ' || c == '.' || c == ',' || c == '?' || c == '*')) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	public static boolean tryParseInt(String s) {
		boolean retVal = false;
		try {
			Integer.valueOf(s);
			retVal = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public static boolean tryParseShort(String s) {
		boolean retVal = false;
		try {
			Short.valueOf(s);
			retVal = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public static boolean tryParseByte(String s) {
		boolean retVal = false;
		try {
			Byte.valueOf(s);
			retVal = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public static boolean tryParseJMB(String s) {
		boolean retVal = false;
		try {
			if(s.length()!=13) {
				return retVal;
			}else {
				for(int i=0;i<s.length();i++) {
					char c=s.charAt(i);
					if(!Character.isDigit(c)) {
						return retVal;
					}
				}
				retVal=true;
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
}
