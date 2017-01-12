/**
 * Created: 2016-11-28
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.common.util.random;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author junjie.ge
 *
 */
public abstract class RandomUtils {
	public static final int ALL_TYPE = 0;
	public static final int FIXCASE_CHARACTER_TYPE = 1;
	public static final int LOWERCASE_CHARACTER_TYPE = 2;
	public static final int UPPERCASE_CHARACTER_TYPE = 3;
	public static final int NUMBERIC_TYPE = 4;

	private static final Character[] UPPERCASE_CHARACTERS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final Character[] LOWERCASE_CHARACTERS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static final Character[] NUMBERIC = {'0','1','2','3','4','5','6','7','8','9'};

	public static Date getRandomDateFromZero() {
		long now = System.currentTimeMillis();
		long random = (long) (Math.random() * now);
		return new Date(random);
	}

	public static Date getRandomDate(String limitDate) {
		return getRandomDate(null, limitDate);
	}

	public static Date getRandomDate(String start, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (start == null || start.trim().length() == 0) {
			//北京时间的原点时间,因为我们在东八区
			start = "1970-01-01 08:00:00";
		}
		if (end == null || end.trim().length() == 0) {
			end = sdf.format(new Date());
		}
		long s = 0l;
		long e = 0l;
		try {
			s = sdf.parse(start).getTime();
			e = sdf.parse(end).getTime();
		} catch (ParseException ex) {
			return new Date(0);
		}
		long random = (long) (s + (Math.random() * (e-s)));
		return new Date(random);
	}

	public static String getRandomNumberic(int length) {
		return getRandomStringWithSymbol(NUMBERIC_TYPE, length, 0, null);
	}

	public static String getRandomNumbericWithSymbol(int length, int delay, String symbol) {
		return getRandomStringWithSymbol(NUMBERIC_TYPE, length, delay, symbol);
	}

	public static String getLowercaseRandomCharacters(int length) {
		return getRandomStringWithSymbol(LOWERCASE_CHARACTER_TYPE, length, 0, null);
	}

	public static String getLowercaseRandomCharactersWithSymbol(int length, int delay, String symbol) {
		return getRandomStringWithSymbol(LOWERCASE_CHARACTER_TYPE, length, delay, symbol);
	}

	public static String getUppercaseRandomCharacters(int length) {
		return getRandomStringWithSymbol(UPPERCASE_CHARACTER_TYPE, length, 0, null);
	}

	public static String getUppercaseRandomCharactersWithSymbol(int length, int delay, String symbol) {
		return getRandomStringWithSymbol(UPPERCASE_CHARACTER_TYPE, length, delay, symbol);
	}

	public static String getFixcaseRandomCharacters(int length) {
		return getRandomStringWithSymbol(FIXCASE_CHARACTER_TYPE, length, 0, null);
	}

	public static String getFixcaseRandomCharactersWithSymbol(int length, int delay, String symbol) {
		return getRandomStringWithSymbol(FIXCASE_CHARACTER_TYPE, length, delay, symbol);
	}

	public static String getFixcaseRandomCharactersAndNumberic(int length) {
		return getRandomStringWithSymbol(ALL_TYPE, length, 0, null);
	}

	public static String getFixcaseRandomCharactersAndNumbericWithSymbol(int length, int delay, String symbol) {
		return getRandomStringWithSymbol(ALL_TYPE, length, delay, symbol);
	}

	public static String getRandomStringWithSymbol(int type, int length, int delay, String symbol) {
		Character[] fixArray = null;
		switch (type) {
		case FIXCASE_CHARACTER_TYPE:
			fixArray = new Character[52];
			System.arraycopy(LOWERCASE_CHARACTERS, 0, fixArray, 0, LOWERCASE_CHARACTERS.length);
			System.arraycopy(UPPERCASE_CHARACTERS, 0, fixArray, LOWERCASE_CHARACTERS.length, UPPERCASE_CHARACTERS.length);
			break;

		case LOWERCASE_CHARACTER_TYPE:
			fixArray = new Character[26];
			System.arraycopy(LOWERCASE_CHARACTERS, 0, fixArray, 0, LOWERCASE_CHARACTERS.length);
			break;

		case UPPERCASE_CHARACTER_TYPE:
			fixArray = new Character[26];
			System.arraycopy(UPPERCASE_CHARACTERS, 0, fixArray, 0, UPPERCASE_CHARACTERS.length);
			break;

		case NUMBERIC_TYPE:
			fixArray = new Character[10];
			System.arraycopy(NUMBERIC, 0, fixArray, 0, NUMBERIC.length);
			break;

		default:
			// ALL_TYPE
			fixArray = new Character[62];
			System.arraycopy(LOWERCASE_CHARACTERS, 0, fixArray, 0, LOWERCASE_CHARACTERS.length);
			System.arraycopy(UPPERCASE_CHARACTERS, 0, fixArray, LOWERCASE_CHARACTERS.length, UPPERCASE_CHARACTERS.length);
			System.arraycopy(NUMBERIC, 0, fixArray, LOWERCASE_CHARACTERS.length + UPPERCASE_CHARACTERS.length, NUMBERIC.length);
			break;
		}
		StringBuilder sb = new StringBuilder();
		int cindex = 1;
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * fixArray.length);
			sb.append(fixArray[index]);
			if (delay > 0 && cindex % delay == 0) {
				sb.append(symbol == null ? "" : symbol);
			}
			cindex++;
		}
		String temp = sb.toString();
		if (symbol != null && temp.endsWith(symbol)) {
			temp = temp.substring(0, temp.length()-1);
		}
		return temp;
	}
}
