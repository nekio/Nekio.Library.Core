package nekio.library.framework.tools;

/**
 * @owner MSIS. Sinesio Ivan Carrillo Heredia
 * @author Nekio <nekio@outlook.com>
 * @version 2018/06/08
 *
 * Tool for create custom Random Texts
 */

// <editor-fold defaultstate="collapsed" desc="Libraries">
import java.security.SecureRandom;
import java.util.Calendar;
// </editor-fold>

public class TextRandomizer {
    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final int STANDARD_BASE = 36;
    private static final int ALPHABET_BASE = 26;
    private static final int VALUE_TO_A = 'a' - STANDARD_BASE;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private int[] digits;
    private int[] aux;

    private String counterpart;
    private int index;
    private int seed;
    private int base;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Behaviours">
    public String getLetter() {
        String result = "";

        SecureRandom randomizer = new SecureRandom();   
        int alphabetFactor = randomizer.nextInt(ALPHABET_BASE) + 1;
        int luckyFactor = randomizer.nextInt(10);
        	
        if (luckyFactor % 2 == 0) {
            result += (char) (96 + alphabetFactor);
        } else {
            result += (char) (64 + alphabetFactor);
        }

        return result;
    }
    
    public String getAlphanumeric() {
        String result = null;

        SecureRandom randomizer = new SecureRandom();
        int value = randomizer.nextInt(52);
        result = "" + Character.toUpperCase(Character.forDigit(value, STANDARD_BASE));

        return result;
    }
    
    public String process() {
    	return process(null);
    }

    public String process(Integer number) {
        synchronized (this) {
            init(number);
            transform();
        }

        return getResult();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Local Behaviours">
    private void init(Integer seed) {
        init(seed, STANDARD_BASE + ALPHABET_BASE);
    }

    private void init(Integer seed, Integer base) {
        this.base = base;
        if (seed == null) {
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            int dayOfYear = today.get(Calendar.DAY_OF_YEAR);

            Calendar thisMoment = Calendar.getInstance();
            int decisecondOfDay = (int) (thisMoment.getTimeInMillis() - today.getTimeInMillis()) / 100;

            String strDayOfYear = "" + dayOfYear;
            strDayOfYear = "000".substring(strDayOfYear.length()) + strDayOfYear;

            String stringSeed = strDayOfYear.substring(1, strDayOfYear.length()) + decisecondOfDay + strDayOfYear.substring(0, 1);
            seed = Integer.parseInt(stringSeed);
        }

        StringBuilder stringSeed = new StringBuilder(String.valueOf(seed));
        this.seed = Integer.parseInt(stringSeed.reverse().toString());

        // This factor only randomizes the most significant values of the seed (tens of millions)
        //so that leaves intact all the positions corresponding to the seed
        SecureRandom randomizer = new SecureRandom();   
        int luckyFactor = randomizer.nextInt(52)* 10000000;
        this.seed += luckyFactor;

        this.digits = new int[1];
        this.index = 0;
        this.counterpart = "";
    }

    private void transform() {
        if (seed < base) {
            digits[index] = seed;
            getSize();
        } else {
            digits[index] = seed % base;

            getSize();
            index++;
            seed = seed / base;
            transform();
        }
    }
    
    private void getSize() {
        aux = digits;
        digits = new int[aux.length + 1];
        System.arraycopy(aux, 0, digits, 0, aux.length);
    }

    private String getResult() {
        for (int j = digits.length - 2; j >= 0; j--) {
            int value = (int) digits[j];
            if (value < STANDARD_BASE) {
                counterpart += Character.toUpperCase(Character.forDigit(value, STANDARD_BASE));
            } else {
                counterpart += (char) (TextRandomizer.VALUE_TO_A + value);
            }
        }

        if (counterpart.length() < 5) {
            counterpart = "00000".substring(counterpart.length()) + counterpart;
        }else if (counterpart.length() > 5){
            counterpart = counterpart.substring(0, 5);
        }
        
        // Concatenar al inicio siempre una letra
        counterpart = getLetter() + counterpart;

        return counterpart;
    }
    // </editor-fold>
}
