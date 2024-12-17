package bsu.rfe.course2.group6.kupriyanov;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients; //коэффициенты многочлена
    private Double from; // начало диапазона значений X
    private Double to; // конец диапазона значений X
    private Double step; // шаг изменения X

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) { // конструктор
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    } // возвращает количество строк в таблице, которое определяется количеством шагов от from до to с шагом step

    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
            double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        } else {
            double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            double fractionalPart = result - Math.floor(result);
            if(fractionalPart == 0) return true;
            else return false;
        }
    }
    // Этот метод возвращает значение ячейки таблицы по её индексу (т.е. для первого столбца текущее значение X, во втором значение
    // многочлена, в третьем столбце bool


    // Возвращает название столбца в зависимости от индекса
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Точное значение";
        }
    }
//Возвращает тип данных для столбца
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
                return Double.class;
            default:
                return Boolean.class; // Возвращаем класс Boolean для третьего столбца
        }
    }
}
