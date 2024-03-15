/**
 *
 *  @author Sobiech Mikołaj S27233
 *
 */

package Z.Z02;


import java.util.Currency;
import java.util.Locale;
import java.util.spi.CurrencyNameProvider;

public class Main {
  public static void main(String[] args) {
    Service s = new Service(""); //klasa currency
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
  }
}
