package librarysystem;

import java.util.ArrayList;

public class Financial {

    public ArrayList<Float> salaries = new ArrayList<Float>();
    public ArrayList<Float> spendings = new ArrayList<Float>();
    public ArrayList<Float> sales = new ArrayList<Float>();

    public float getAverageSalary() {
        if (salaries.size() == 0) {
            return 0;
        }
        float sum = 0;
        for (int i = 0; i < salaries.size(); i++) {
            sum += salaries.get(i);
        }
        return sum / salaries.size();
    }

    public float getTotalSales() {
        float sum = 0;
        for (int i = 0; i < sales.size(); i++) {
            sum += sales.get(i);
        }
        return sum;
    }

    public float getTotalSpendings() {
        float sum = 0;
        for (int i = 0; i < spendings.size(); i++) {
            sum += spendings.get(i);
        }
        return sum;
    }

    public float getTotalProfit() {
        float totalSpendings = getTotalSpendings();
        float totalSales = getTotalSales();
        return totalSales - totalSpendings;
    }
}
