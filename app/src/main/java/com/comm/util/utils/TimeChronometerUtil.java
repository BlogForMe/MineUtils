package com.comm.util.utils;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Locale;

import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import timber.log.Timber;

/**
 * @author : John
 * @date : 2018/9/2
 */
public class TimeChronometerUtil {

//    private Context context;

    private static final String TAG = "Chronometer";
    private static final int MIN_IN_SEC = 60;
    private static final int HOUR_IN_SEC = MIN_IN_SEC * 60;
    private final Handler handler;
    private final Object[] mFormatterArgs = new Object[1];
    private final StringBuilder mRecycle = new StringBuilder(8);
    private long mBase;
    private long mNow; // the currently displayed time
    private boolean mVisible;
    private boolean mStarted;
    private boolean mRunning;
    private boolean mLogged;
    private String mFormat;
    private Formatter mFormatter;
    private Locale mFormatterLocale;
    private StringBuilder mFormatBuilder;
    private OnChronometerTickListener mOnChronometerTickListener;
    private boolean mCountDown;
    private final Runnable mTickRunnable = new Runnable() {
        @Override
        public void run() {
            if (mRunning) {
                updateText(SystemClock.elapsedRealtime());
                dispatchChronometerTick();
                handler.postDelayed(mTickRunnable, 1000);
            }
        }
    };

    /**
     * Initialize this Chronometer object.
     * Sets the base to the current time.
     */
    public TimeChronometerUtil() {
//        this.context = context;
        handler = new Handler();
//        final TypedArray a = context.obtainStyledAttributes(
//                attrs, com.android.internal.R.styleable.Chronometer, defStyleAttr, defStyleRes);
        setFormat("%s");
//        setCountDown(a.getBoolean(R.styleable.Chronometer_countDown, false));
//        a.recycle();
        setCountDown(false);

        init();
    }

    private static String formatDuration(long ms) {
        int duration = (int) (ms / DateUtils.SECOND_IN_MILLIS);
        if (duration < 0) {
            duration = -duration;
        }

        int h = 0;
        int m = 0;

        if (duration >= HOUR_IN_SEC) {
            h = duration / HOUR_IN_SEC;
            duration -= h * HOUR_IN_SEC;
        }
        if (duration >= MIN_IN_SEC) {
            m = duration / MIN_IN_SEC;
            duration -= m * MIN_IN_SEC;
        }
        final int s = duration;

        final ArrayList<Measure> measures = new ArrayList<Measure>();
        if (h > 0) {
            measures.add(new Measure(h, MeasureUnit.HOUR));
        }
        if (m > 0) {
            measures.add(new Measure(m, MeasureUnit.MINUTE));
        }
        measures.add(new Measure(s, MeasureUnit.SECOND));

        return MeasureFormat.getInstance(Locale.getDefault(), MeasureFormat.FormatWidth.WIDE)
                .formatMeasures(measures.toArray(new Measure[measures.size()]));
    }

    /**
     * @return whether this is the final countdown
     */
//    public boolean isTheFinalCountDown() {
//        try {
//            getContext().startActivity(
//                    new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/9jK-NcRmVcw"))
//                            .addCategory(Intent.CATEGORY_BROWSABLE)
//                            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT
//                                    | Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT));
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    private void init() {
        mBase = SystemClock.elapsedRealtime();
        updateText(mBase);
    }

    /**
     * @return whether this view counts down
     * @see #setCountDown(boolean)
     */
    public boolean isCountDown() {
        return mCountDown;
    }

    /**
     * Set this view to count down to the base instead of counting up from it.
     *
     * @param countDown whether this view should count down
     * @see #setBase(long)
     */
//    @android.view.RemotableViewMethod
    public void setCountDown(boolean countDown) {
        mCountDown = countDown;
        updateText(SystemClock.elapsedRealtime());
    }

    /**
     * Return the base time as set through {@link #setBase}.
     */
    public long getBase() {
        return mBase;
    }

    /**
     * Set the time that the count-up timer is in reference to.
     *
     * @param base Use the {@link SystemClock#elapsedRealtime} time base.
     */
    public void setBase(long base) {
        mBase = base;
        dispatchChronometerTick();
        updateText(SystemClock.elapsedRealtime());
    }

    /**
     * Returns the current format string as set through {@link #setFormat}.
     */
    public String getFormat() {
        return mFormat;
    }

    /**
     * Sets the format string used for display.  The Chronometer will display
     * this string, with the first "%s" replaced by the current timer value in
     * "MM:SS" or "H:MM:SS" form.
     * <p>
     * If the format string is null, or if you never call setFormat(), the
     * Chronometer will simply display the timer value in "MM:SS" or "H:MM:SS"
     * form.
     *
     * @param format the format string.
     */
    public void setFormat(String format) {
        mFormat = format;
        if (format != null && mFormatBuilder == null) {
            mFormatBuilder = new StringBuilder(format.length() * 2);
        }
    }

    /**
     * @return The listener (may be null) that is listening for chronometer change
     * events.
     */
    public OnChronometerTickListener getOnChronometerTickListener() {
        return mOnChronometerTickListener;
    }

    /**
     * Sets the listener to be called when the chronometer changes.
     *
     * @param listener The listener.
     */
    public void setOnChronometerTickListener(OnChronometerTickListener listener) {
        mOnChronometerTickListener = listener;
    }

//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        mVisible = false;
//        updateRunning();
//    }
//
//    @Override
//    protected void onWindowVisibilityChanged(int visibility) {
//        super.onWindowVisibilityChanged(visibility);
//        mVisible = visibility == VISIBLE;
//        updateRunning();
//    }
//
//    @Override
//    protected void onVisibilityChanged(View changedView, int visibility) {
//        super.onVisibilityChanged(changedView, visibility);
//        updateRunning();
//    }

    /**
     * Start counting up.  This does not affect the base as set from {@link #setBase}, just
     * the view display.
     * <p>
     * Chronometer works by regularly scheduling messages to the handler, even when the
     * Widget is not visible.  To make sure resource leaks do not occur, the user should
     * make sure that each start() call has a reciprocal call to {@link #stop}.
     */
    public void start() {
        mStarted = true;
        updateRunning();
    }

    /**
     * Stop counting up.  This does not affect the base as set from {@link #setBase}, just
     * the view display.
     * <p>
     * This stops the messages to the handler, effectively releasing resources that would
     * be held as the chronometer is running, via {@link #start}.
     */
    public void stop() {
        mStarted = false;
        updateRunning();
    }

    /**
     * The same as calling {@link #start} or {@link #stop}.
     *
     * @hide pending API council approval
     */
//    @android.view.RemotableViewMethod
    public void setStarted(boolean started) {
        mStarted = started;
        updateRunning();
    }

    private synchronized void updateText(long now) {
        mNow = now;
        long seconds = mCountDown ? mBase - now : now - mBase;
        seconds /= 1000;
        boolean negative = false;
        if (seconds < 0) {
            seconds = -seconds;
            negative = true;
        }
        String text = DateUtils.formatElapsedTime(mRecycle, seconds);
        if (negative) {
//            text = context.getResources().getString(R.string.negative_duration, text);
        }

        if (mFormat != null) {
            Locale loc = Locale.getDefault();
            if (mFormatter == null || !loc.equals(mFormatterLocale)) {
                mFormatterLocale = loc;
                mFormatter = new Formatter(mFormatBuilder, loc);
            }
            mFormatBuilder.setLength(0);
            mFormatterArgs[0] = text;
            try {
                mFormatter.format(mFormat, mFormatterArgs);
                text = mFormatBuilder.toString();
            } catch (IllegalFormatException ex) {
                if (!mLogged) {
                    Log.w(TAG, "Illegal format string: " + mFormat);
                    mLogged = true;
                }
            }
        }

        Timber.i("时间j计数 "+text);
//        setText(text);
    }

    private void updateRunning() {
        boolean running = mVisible && mStarted;
        if (running != mRunning) {
            if (running) {
                updateText(SystemClock.elapsedRealtime());
                dispatchChronometerTick();
                handler.postDelayed(mTickRunnable, 1000);
            } else {
                handler.removeCallbacks(mTickRunnable);
            }
            mRunning = running;
        }
    }

    void dispatchChronometerTick() {
        if (mOnChronometerTickListener != null) {
            mOnChronometerTickListener.onChronometerTick(this);
        }
    }

    public CharSequence getContentDescription() {
        return formatDuration(mNow - mBase);
    }

    public CharSequence getAccessibilityClassName() {
        return android.widget.Chronometer.class.getName();
    }

    /**
     * A callback that notifies when the chronometer has incremented on its own.
     */
    public interface OnChronometerTickListener {

        /**
         * Notification that the chronometer has changed.
         */
        void onChronometerTick(TimeChronometerUtil timeCountUtil);

    }
}
