package com.company;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Этот класс представляет собой пользовательский компонент Swing для представления одной ячейки карты в
 * 2D-карты.  Ячейка имеет несколько различных видов состояний, но самые основные
 * состояния - это то, является ли ячейка проходимой или нет.
 */

public class JMapCell extends JComponent
{
    private static final Dimension CELL_SIZE = new Dimension(12, 12);

    /**  **/
    boolean endpoint = false;


    /** Значение True указывает, что ячейка является конечной точкой, либо начальной, либо конечной. **/
    boolean passable = true;

    /**
     * Значение True указывает, что эта ячейка является частью пути между началом и концом.
     **/
    boolean path = false;

    /**
     * Создайте новую ячейку карты с указанной "possibility".  Ввод значения
     * true означает, что ячейка является проходимой.
     **/
    public JMapCell(boolean pass)
    {
        // Установите предпочтительный размер ячейки, чтобы определить начальный размер окна.
        setPreferredSize(CELL_SIZE);

        setPassable(pass);
    }

    /** Создайте новую ячейку карты, которая по умолчанию является проходимой. **/
    public JMapCell()
    {
        // Вызовите другой конструктор, указав значение true для "passable".
        this(true);
    }

    /** Помечает эту ячейку либо как начальную, либо как конечную ячейку. **/
    public void setEndpoint(boolean end)
    {
        endpoint = end;
        updateAppearance();
    }

    /**
     * Устанавливает эту ячейку проходимой или непроходимой.
     * Ввод значения true помечает ячейку как возможную;
     * ввод значения false помечает ее как недопустимую.
     **/
    public void setPassable(boolean pass)
    {
        passable = pass;
        updateAppearance();
    }

    /** Возвращает значение true, если эта ячейка является проходимой, или значение false в противном случае. **/
    public boolean isPassable()
    {
        return passable;
    }

    /** Переключает текущее "проходимое" состояние ячейки карты. **/
    public void togglePassable()
    {
        setPassable(!isPassable());
    }

    /** Помечает эту ячейку как часть пути, обнаруженного алгоритмом A*. **/
    public void setPath(boolean path)
    {
        this.path = path;
        updateAppearance();
    }

    /**
     * Этот вспомогательный метод обновляет цвет фона в соответствии с текущим внутренним состоянием ячейки.
     **/
    private void updateAppearance()
    {
        if (passable)
        {
            // Проходимая клетка.  Укажите его состояние с помощью границы.
            setBackground(Color.WHITE);

            if (endpoint)
                setBackground(Color.CYAN);
            else if (path)
                setBackground(Color.GREEN);
        }
        else
        {
            // Непроходимая клетка. Сделайте все это красным.
            setBackground(Color.RED);
        }
    }

    /**
     * Реализация метода paint для рисования фонового цвета в ячейке карты.
     **/
    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
