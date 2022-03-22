package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/* Класс, необходимый для отображения графического интерфейса
и обработки действий пользователя */

public class FractalExplorer {

    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    /* Конструктор, который принимает размер дисплея, сохраняет его и
     инициализирует объекты диапазона и фрактального генератора. */

    public FractalExplorer(int size) {
        //Сохраняет размер дисплея
        displaySize = size;
        //Инициализирует фрактальный генератор и объекты диапазона
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    /* Этот метод инициализирует графический интерфейс Swing с помощью JFrame, содержащий
    JImageDisplay объект и кнопку для сброса дисплея. */
    public void createAndShowGUI()
    {

        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal Explorer");
        // Добавляет и центрует объект изображения
        frame.add(display, BorderLayout.CENTER);

        //Создание кнопки сброса
        JButton resetButton = new JButton("Reset Display");
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);

        //Добавляем кнопку сброса в позицию BorderLayout.SOUTH
        frame.add(resetButton, BorderLayout.SOUTH);
        //Экземпляр MouseHandler в компоненте фрактального отображения
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        JPanel myPanel = new JPanel();
        frame.add(myPanel, BorderLayout.NORTH);

        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(resetButton);
        frame.add(myBottomPanel, BorderLayout.SOUTH);

        //Устанавливает операцию закрытия фрейма по умолчанию на "exit"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Разместить содержимое фрейма так, чтобы оно было видно, и запретить изменение размера окна
        frame.setTitle("fractal");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2 - 300,dimension.height/2 - 300, 600, 600);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    /* Вспомогательный метод для отображения фрактала. */
    private void drawFractal()
    {
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                /*
                Находим соответствующие координаты xCoord и yCoord
                в области отображения фрактала.
                */
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                /*
                Вычисляем количество итераций для координат в
                области отображения фрактала.
                */
                int iteration = fractal.numIterations(xCoord, yCoord);
                //Если количество итераций равно -1, установить черный пиксель
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }

                else {
                    //В противном случае выберите значение оттенка на основе числа итераций
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    //Обновляем отображение цветом для каждого пикселя
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        /* Когда все пиксели будут нарисованы, перекрасить JImageDisplay в соответствии с
        текущим изображением. */
        display.repaint();
    }
    /* Внутренний класс для обработки событий ActionListener от кнопки сброса */
    private class ResetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }
    //Внутренний класс для обработки событий MouseListener с дисплея.
    private class MouseHandler extends MouseAdapter
    {
        @Override //Переопределяем метод базового класса
        public void mouseClicked(MouseEvent e)
        {
            //Получаем координату x области отображения щелчка мыши
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);

            //Получаем координату y области отображения щелчка мышью
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);


            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            //Перерисовываем фрактал после изменения области отображения
            drawFractal();
        }
    }
    /*
    Статический метод main () для запуска FractalExplorer.
    */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}
