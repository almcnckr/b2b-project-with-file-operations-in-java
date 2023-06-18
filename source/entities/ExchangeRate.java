package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExchangeRate implements IEntity {
	private static String exchangeRate;

	public static String getExchangeRate() {
		try {
			File file = new File("exchangeRate.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedReader bReader = new BufferedReader(new FileReader(file));
			if ((exchangeRate = bReader.readLine()) == null) {
				exchangeRate = "0";
			}
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeRate + "TL";
	}

	public static void setExchangeRate(String exchangeRate) {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("exchangeRate.txt"));
			bWriter.write(exchangeRate);
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExchangeRate.exchangeRate = exchangeRate;
	}
}