package com.andrukhiv.calendar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.andrukhiv.calendar.database.DbAdapter;
import com.rilixtech.agendacalendarview.AgendaCalendarView;
import com.rilixtech.agendacalendarview.CalendarManager;
import com.rilixtech.agendacalendarview.CalendarPickerController;
import com.rilixtech.agendacalendarview.models.BaseCalendarEvent;
import com.rilixtech.agendacalendarview.models.CalendarEvent;
import com.rilixtech.agendacalendarview.models.DayItem;
import com.rilixtech.agendacalendarview.models.IDayItem;
import com.rilixtech.agendacalendarview.models.IWeekItem;
import com.rilixtech.agendacalendarview.models.WeekItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity implements CalendarPickerController {

    DbAdapter mDbHelper;
    ArrayList<CalendarEvent> calendarEvents;

    private static final String LOG_TAG = CalendarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar mToolbar = findViewById(R.id.activity_toolbar);
        AgendaCalendarView mAgendaCalendarView = findViewById(R.id.agenda_calendar_view);
        setSupportActionBar(mToolbar);

        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        // минимальная и максимальная дата нашего календаря
        // 2 месяца позади, год впереди
        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        //minDate.set(2015, 12, 1);
//        minDate.set(Calendar.DAY_OF_MONTH, 1);
//        maxDate.set(2021, 11, 31);


        mDbHelper = DbAdapter.getInstance(getApplicationContext());
        calendarEvents = mDbHelper.getCalendarEvents();

        //List<CalendarEvent> eventList = new ArrayList<>();
        mockList(calendarEvents);

        mAgendaCalendarView.init(calendarEvents, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
        mAgendaCalendarView.enableCalenderView(true);


        CalendarManager calendarManager = CalendarManager.getInstance(getApplicationContext());
        calendarManager.buildCal(minDate, maxDate, Locale.getDefault(), new DayItem(), new WeekItem());
        calendarManager.loadEvents(calendarEvents, new BaseCalendarEvent());

        List<CalendarEvent> readyEvents = calendarManager.getEvents();
        List<IDayItem> readyDays = calendarManager.getDays();
        List<IWeekItem> readyWeeks = calendarManager.getWeeks();

        mAgendaCalendarView.init(Locale.getDefault(), readyWeeks, readyDays, readyEvents, this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
        mAgendaCalendarView.enableCalenderView(true);
    }

    @Override
    public void onDaySelected(IDayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    calendar.getDisplayName(
                            Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " +
                            calendar.get(Calendar.YEAR));
            //+ " " + calendar.get(Calendar.DATE)
        }
    }

    private void mockList(List<CalendarEvent> calendarEvents) {

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        startTime.set(Calendar.YEAR, 2019);
        startTime.set(Calendar.MONTH, 0);//січень
        startTime.set(Calendar.DATE, 12);
        endTime.set(Calendar.YEAR, 2019);
        endTime.set(Calendar.MONTH, 0);
        endTime.set(Calendar.DATE, 15);

//        BaseCalendarEvent event = new BaseCalendarEvent("16:00 - 17:00 Reunión de coordinación AGN 2", "i", "Despacho 3",
//                ContextCompat.getColor(this, R.color.theme_event_confirmed), startTime, endTime, true);
//        eventList.add(event);
//
//
//        Calendar startTime1 = Calendar.getInstance();
//        Calendar endTime1 = Calendar.getInstance();
//        startTime1.set(2018, 3, 5);//05.04
//        endTime1.set(2018, 3, 15);//15.04
//        BaseCalendarEvent event1 = new BaseCalendarEvent("Зходження снігу.", "", "Денне відкриття кущів.\n\nОбробка лози: 200г мідного купоросу і 800г сечовини на 10л води (або 250г залізного купоросу).",
//                ContextCompat.getColor(this, R.color.grapes), startTime1, endTime1, true);
//        eventList.add(event1);
//
//
//        Calendar startTime2 = Calendar.getInstance();
//        Calendar endTime2 = Calendar.getInstance();
//        startTime2.set(2018, 3, 10);//10.04
//        endTime2.set(2018, 3, 20);//20.04
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Плач лози» (тим не менш коріння працює!)", "", "Просушування голови куща.\n\nРозпушування і мульчування лунок.\n\nВнесення золи під голову куща.",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime2, endTime2, true);
//        eventList.add(event2);
//
//        Calendar startTime3 = Calendar.getInstance();
//        Calendar endTime3 = Calendar.getInstance();
//        startTime3.set(2018, 3, 15);//15.04
//        endTime3.set(2018, 3, 30);//30.04
//        BaseCalendarEvent event3 = new BaseCalendarEvent("Набрякання бруньок (тим не менш бруньки цілі!)", "", "Кореневе підживлення*: 15г аміачної селітри + 15г суперфосфату (розрахунок на один кущ тут і далі).",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime3, endTime3, true);
//        eventList.add(event3);
//
//        Calendar startTime4 = Calendar.getInstance();
//        Calendar endTime4 = Calendar.getInstance();
//        startTime4.set(2018, 3, 25);//25.04
//        endTime4.set(2018, 4, 05);//05.05
//        BaseCalendarEvent event4 = new BaseCalendarEvent("Розпускання бруньок (початок відліку)", "", "Оновлення шпалер і дроту. Захист від весняних заморозків (вода під кущем на целофані + зверху парник).",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime4, endTime4, true);
//        eventList.add(event4);
//
//        Calendar startTime5 = Calendar.getInstance();
//        Calendar endTime5 = Calendar.getInstance();
//        startTime5.set(2018, 4, 05);//05.05
//        endTime5.set(2018, 4, 15);//15.05
//        BaseCalendarEvent event5 = new BaseCalendarEvent("Активний ріст пагонів", "", "Зелені операції: видалення «зайвих» пагонів, «двійників-трійників», бур'янів. Підготовка місця, ям для посадки саджанців.",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime5, endTime5, true);
//        eventList.add(event5);
//
//
//        Calendar startTime6 = Calendar.getInstance();
//        Calendar endTime6 = Calendar.getInstance();
//        startTime6.set(2018, 4, 1);//01.05
//        //endTime6.set(2017, 4, 7);//07.05 - якщо закоментувати, то повторення відбувається щодня
//        DrawableCalendarEvent event6 = new DrawableCalendarEvent("Фестиваль «Сакура-фест»\n\n«Під цвітом сакури»\nвсеукраїнський фестиваль театрального мистецтва", "", "м. Ужгород, облмуздрамтеатр",
//                ContextCompat.getColor(this, R.color.colorUgo4anskaLoza), startTime6, endTime6, false, R.drawable.ugo4anska_loza);
//        eventList.add(event6);
//
//
//        Calendar startTime7 = Calendar.getInstance();
//        Calendar endTime7 = Calendar.getInstance();

//        startTime7.set(2018, 11, 12);
//        endTime7.set(2018, 11, 22);
//        BaseCalendarEvent event7 = new BaseCalendarEvent("Активний ріст пагонів", "", "Зелені операції: видалення «зайвих» пагонів, «двійників-трійників», бур'янів. Підготовка місця, ям для посадки саджанців.",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime7, endTime7, true);
//        eventList.add(event7);

        // todo: зчитати з бази даних не можу
        BaseCalendarEvent event8 = new BaseCalendarEvent(
                1,
                0xFF000000,
                "title",
                "description",
                "location",
                1544565600000L,
                1545688800000L,
                1,
                "duration");

        calendarEvents.add(event8);

//        DrawableCalendarEvent event9 = new DrawableCalendarEvent(
//                1,
//                0xFF000000,
//                "title",
//                "description",
//                "location",
//                1544565600000L,
//                1545688800000L,
//                1,
//                "duration",
//                R.drawable.ugo4anska_loza);
//        eventList.add(event9);



//        Calendar startTime10 = Calendar.getInstance();
//        Calendar endTime10 = Calendar.getInstance();
//        endTime10.add(Calendar.MONTH, 1);// З сьогоднішнього дня місяць вперед
//        BaseCalendarEvent event10 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime10, endTime10, true);
//        eventList.add(event10);
//
//
//        Calendar startTime20 = Calendar.getInstance();
//        startTime20.add(Calendar.DAY_OF_YEAR, 1); // Починається через 1 день
//        Calendar endTime20 = Calendar.getInstance();
//        endTime20.add(Calendar.DAY_OF_YEAR, 3); // Продовжується протягом 3 днів
//        BaseCalendarEvent event20 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(this, R.color.colorAccent), startTime20, endTime20, true);
//        eventList.add(event20);
//
//
//        Calendar startTime30 = Calendar.getInstance();
//        Calendar endTime30 = Calendar.getInstance();
//        startTime30.set(Calendar.HOUR_OF_DAY, 14);
//        startTime30.set(Calendar.MINUTE, 0);
//        endTime30.set(Calendar.HOUR_OF_DAY, 15);
//        endTime30.set(Calendar.MINUTE, 0);
//        DrawableCalendarEvent event30 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.calendar_divider_color), startTime30, endTime30, false, R.drawable.ugo4anska_loza);
//        eventList.add(event30);

    }

}

