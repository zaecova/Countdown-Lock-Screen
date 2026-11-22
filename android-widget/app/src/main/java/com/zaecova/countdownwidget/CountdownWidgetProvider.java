package com.zaecova.countdownwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class CountdownWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] widgetIds) {
        for (int widgetId : widgetIds) {
            updateWidget(context, manager, widgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (Intent.ACTION_DATE_CHANGED.equals(action)
                || Intent.ACTION_TIME_CHANGED.equals(action)
                || Intent.ACTION_TIMEZONE_CHANGED.equals(action)) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int[] widgetIds = manager.getAppWidgetIds(
                    new ComponentName(context, CountdownWidgetProvider.class));
            onUpdate(context, manager, widgetIds);
        }
    }

    private static void updateWidget(Context context, AppWidgetManager manager, int widgetId) {
        LocalDate today = LocalDate.now();
        LocalDate interview = LocalDate.of(today.getYear(), 12, 4);
        if (interview.isBefore(today)) {
            interview = interview.plusYears(1);
        }

        long daysLeft = ChronoUnit.DAYS.between(today, interview);
        String daysText;
        if (daysLeft == 0) {
            daysText = context.getString(R.string.interview_today);
        } else if (daysLeft == 1) {
            daysText = context.getString(R.string.one_day_left);
        } else {
            daysText = context.getString(R.string.days_left, daysLeft);
        }

        Locale locale = Locale.forLanguageTag("es-PE");
        String dateText = interview.format(DateTimeFormatter.ofPattern("dd MMM", locale));
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.countdown_widget);
        views.setTextViewText(R.id.widget_days, daysText);
        views.setTextViewText(R.id.widget_date, context.getString(R.string.interview_date, dateText));

        Intent openApp = new Intent(context, MainActivity.class);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openApp, flags);
        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

        manager.updateAppWidget(widgetId, views);
    }
}
