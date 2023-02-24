package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // создание полей для вывода на экран нужных значений
    private Button buttonStart; // кнопка запуска секундомера
    private Button buttonPause; // кнопка паузы секундомера
    private Button buttonStop; // кнопка остановки секундомера
    private TextView stopwatchOut; // поле вывода результирующей информации



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание переменным активити элементов представления activity_main
        buttonStart = findViewById(R.id.buttonStart); // кнопка обработки
        buttonPause = findViewById(R.id.buttonPause); // кнопка обработки
        buttonStop = findViewById(R.id.buttonStop); // кнопка обработки
        stopwatchOut = findViewById(R.id.stopwatchOut); // поле вывода

        // выполнение действий при нажании кнопки
        buttonStart.setOnClickListener(listener); // обработка нажатия кнопки
        buttonPause.setOnClickListener(listener); // обработка нажатия кнопки
        buttonStop.setOnClickListener(listener); // обработка нажатия кнопки

    }
    // объект обработки нажатия всех кнопок
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // при нажатии кнопки во view записывается значение кнопки

            // через view.getId() можно прочитать значение ID кнопки

            switch (view.getId()) { // switch (значение выбранного ID кнопки)
                case R.id.buttonStart: // нажатие кнопки "Старт"
                    startTime = SystemClock.uptimeMillis(); // Миллисекунды с момента загрузки (не считая времени, проведенного в глубоком сне)
                    handler.postDelayed(updateTimerThread, 0); // запуск потока с нулевой задержкой
                    break;
                case R.id.buttonPause: // нажатие кнопки "Пауза"
                    timePause += timeInMilliseconds; // фиксирование времени в момент нажатия кнопки
                    handler.removeCallbacks(updateTimerThread); // удаление из очереди данного потока
                    break;
                case R.id.buttonStop: // нажатие кнопки "Стоп"
                    startTime = 0L;
                    timePause = 0L;
                    handler.removeCallbacks(updateTimerThread); // удаление из очереди данного потока
                    stopwatchOut.setText(getString(R.string.messageStop)); // загрузка сообщения о прекращении отсчёта времени
                    break;
            }
        }
    };
}