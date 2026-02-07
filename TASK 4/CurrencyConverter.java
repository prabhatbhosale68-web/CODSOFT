import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static double fetchRate(String base, String target) throws Exception {

        String api = "https://open.er-api.com/v6/latest/" + base;

        URL url = new URL(api);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        String data = response.toString();

        int index = data.indexOf("\"" + target + "\":");
        if (index == -1)
            throw new Exception("Currency not found");

        int start = index + target.length() + 3;
        int end = data.indexOf(",", start);

        return Double.parseDouble(data.substring(start, end));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter base currency (USD, INR, EUR): ");
            String base = sc.next().toUpperCase();

            System.out.print("Enter target currency: ");
            String target = sc.next().toUpperCase();

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            double rate = fetchRate(base, target);
            double converted = amount * rate;

            System.out.println("\n💱 Converted Amount:");
            System.out.println(amount + " " + base + " = " + converted + " " + target);

        } catch (Exception e) {
            System.out.println("❌ Error: Invalid currency or internet issue.");
        }

        sc.close();
    }
}
