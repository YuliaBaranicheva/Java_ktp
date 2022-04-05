package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;


/* Класс, необходимый для отображения графического интерфейса
и обработки действий пользователя */

public class FractalExplorer {

    private int rowsRemaining;
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    private JButton saveButton;
    private JButton resetButton;
    private JComboBox myComboBox;


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
    public void createAndShowGUI() {
        //Устанавливаем фрейм на использование java.awt.BorderLayout для его содержимого
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Fractal Explorer");
        //Добавляем объект отображения изображения в BorderLayout.CENTER
        myframe.add(display, BorderLayout.CENTER);
        //Создаем кнопку сброса
        resetButton = new JButton("Reset");
        //Экземпляр ResetHandler на кнопке сброса
        resetButton.addActionListener(new Button());
        //Экземпляр MouseHandler в компоненте фрактального отображения
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        //Устанавливает операцию закрытия фрейма по умолчанию на "exit"
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Установить комбо бокс
        myComboBox = new JComboBox();
        //Добавиь каждый объект фрактала в комбо бокс
        FractalGenerator Mandelbrot = new Mandelbrot();
        myComboBox.addItem(Mandelbrot);
        FractalGenerator Tricorn = new Tricorn();
        myComboBox.addItem(Tricorn);
        FractalGenerator BurningShip = new BurningShip();
        myComboBox.addItem(BurningShip);
        //Экземпляр ButtonHandler в поле со списком
        Button fractalChooser = new Button();
        myComboBox.addActionListener(fractalChooser);
        //Создать новые объекты JPanel,JLabel,JComboBox и добавить панель сверху
        JPanel myPanel = new JPanel();
        JLabel myLabel = new JLabel("Fractal:");
        myPanel.add(myLabel);
        myPanel.add(myComboBox);
        myframe.add(myPanel, BorderLayout.NORTH);
        /*
        Создать кнопку сохранения, добавить ее в JPanel в BorderLayout.SOUTH
        вместе с кнопкой сброса.
        */
        saveButton = new JButton("Save");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myframe.add(myBottomPanel, BorderLayout.SOUTH);
        //Экземпляр обработчика сохранения в кнопке сохранения.
        ActionListener save = new Button();
        saveButton.addActionListener(save);
        /*
        Разместить содержимое фрейма так, чтобы оно было видно, и
        запретить изменение размера окна.
        */
        myframe.setTitle("this is my fractal");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        myframe.setBounds(dimension.width/2 - 300,dimension.height/2 - 300, 600, 600);
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }

    /* Вспомогательный метод для отображения фрактала. */
    private void drawFractal() {
        /*
        Отключение всех элементов
        пользовательского интерфейса во время рисования
        */
        enableUI(false);
        /*
        Устанавливает значение «rows remaining» равное общему количеству строк,
        которые нужно нарисовать
        */
        rowsRemaining = displaySize;
        /*
        Вызываем Fractal Worker на каждой строке дисплея (вычисление значений
        цвета на каждой строке получается)
        */
        for (int x=0; x<displaySize; x++){
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
    }
    /*
    Включает или отключает кнопки интерфейса
    и поле со списком в зависимости от заданного значения
    */
    private void enableUI(boolean val) {
        myComboBox.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

    //Внутренний класс для обработки событий ActionListener от кнопки сброса
    private class Button implements ActionListener {
        //Проверка выборки источника
        public void actionPerformed(ActionEvent e) {
            //Получить источник действия
            String command = e.getActionCommand();
            /*
            Если источником является комбо бокс, получить фрактал, выбранный пользователем,
            и отобразить его.
            */
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
            }
            //Если источником является кнопка сохранения, сохранить изображение текущего фрактала
            else if (command.equals("Save")) {
                //Разрешить пользователю выбрать файл для сохранения изображения
                JFileChooser myFileChooser = new JFileChooser();
                //Сохранять только изображения PNG
                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");
                myFileChooser.setFileFilter(extensionFilter);
                //Гарантирует, что средство выбора файлов не разрешит не - ". Png" имена файлов
                myFileChooser.setAcceptAllFileFilterUsed(false);
                /*
                Всплывает окно «Сохранить файл», в котором пользователь может выбрать
                каталог и файл для сохранения.
                */
                int userSelection = myFileChooser.showSaveDialog(display);
                /*
                Если результат операции выбора файла
                APPROVE_OPTION, продолжить операцию сохранения файла.
                */
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //Получить файл и имя файла
                    java.io.File file = myFileChooser.getSelectedFile();
                    String file_name = file.toString();
                    //Сохранить фрактальное изображение на диск
                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    //Исключения и сообщения об исключениях
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, exception.getMessage(),
                                "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            //Если источником является кнопка сброса, сбросить дисплей и перерисовать фрактал
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else return;
        }
    }

    //Внутренний класс для обработки событий MouseListener с дисплея.
    private class MouseHandler extends MouseAdapter {
        @Override //Переопределяем метод базового класса
        public void mouseClicked(MouseEvent e) {
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

    private class FractalWorker extends SwingWorker<Object, Object> {
        // Целочисленная y-координата вычисляемой строки
        int yCoordinate;
        /* Массив чисел типа int для хранения
        вычисленных значений RGB для каждого пикселя в этой строке */
        int[] arrayRGB;

        /* Конструктор принимает координату y
        в качестве аргумента и сохраняет ее */
        private FractalWorker(int row) {
            yCoordinate = row;
        }

        protected Object doInBackground() {
            arrayRGB = new int[displaySize];
            //Цикл сохраняет каждое значение RGB в соответствующем элементе целочисленного массива
            for (int i = 0; i < arrayRGB.length; i++) {
                //Нахождение координат
                double xCoord = fractal.getCoord(range.x,
                        range.x + range.width, displaySize, i);
                double yCoord = fractal.getCoord(range.y,
                        range.y + range.height, displaySize, yCoordinate);
                // Вычисление количества итераций для координат
                int iteration = fractal.numIterations(xCoord, yCoord);
                //Если -1, то значения RGB int в черный цвет
                if (iteration == -1) {
                    arrayRGB[i] = 0;
                } else {
                    // В противном случае выберите
                    // значение оттенка на основе числа итераций
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    //Обновление массива
                    arrayRGB[i] = rgbColor;
                }
            }
            return null;
        }

        /*
        Вызывается после завершения фоновой задачи. Рисует пиксели
        для текущей строки и обновляет отображение
        */
        protected void done() {
            for (int i = 0; i < arrayRGB.length; i++) {
                display.drawPixel(i, yCoordinate, arrayRGB[i]);
            }
            //метод перерисовки
            display.repaint(0, 0, yCoordinate, displaySize, 1);
            //Уменьшение значения «rows remaining» на 1, как
            //последний шаг данной операции. Затем, если после уменьшения значение «rows
            //remaining» равно 0, вызов метода enableUI (true).
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

        /*
        Статический метод main () для запуска FractalExplorer.
        */
        public static void main(String[] args) {
            FractalExplorer displayExplorer = new FractalExplorer(600);
            displayExplorer.createAndShowGUI();
            displayExplorer.drawFractal();
        }
}
