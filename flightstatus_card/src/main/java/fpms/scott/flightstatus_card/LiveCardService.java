package fpms.scott.flightstatus_card;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import java.util.Random;

public class LiveCardService extends Service {

    private static final String LIVE_CARD_TAG = "LiveCardDemo";

    private LiveCard mLiveCard;
    private RemoteViews mLiveCardView;

    private int homeScore, awayScore;
    private Random mRnd;

    private final Handler mHandler = new Handler();
    private final UpdateLiveCardRunnable mUpdateLiveCardRunnable =
            new UpdateLiveCardRunnable();
    private static final long DELAY_MILLIS = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        mRnd = new Random();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {

            // Get an instance of a live card
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);

            // Inflate a layout into a remote view
            mLiveCardView = new RemoteViews(getPackageName(),
                    R.layout.route_status);

            // Set up initial RemoteViews values

            // Set up the live card's action with a pending intent
            // to show a menu when tapped
            Intent menuIntent = new Intent(this, LiveCardMenuActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(
                    this, 0, menuIntent, 0));

            // Publish the live card
            mLiveCard.publish(PublishMode.REVEAL);

            // Queue the update text runnable
            mHandler.post(mUpdateLiveCardRunnable);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            //Stop the handler from queuing more Runnable jobs
            mUpdateLiveCardRunnable.setStop(true);

            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }

    /**
     * Runnable that updates live card contents
     */
    private class UpdateLiveCardRunnable implements Runnable{
        private double lat = 31.7676; //UTEP CS building
        private double lng = -106.5020;
        private float latSpeed = 16; // m/s 16 = 72mph, 32=143mph, 64=268mph very rough
        private float longSpeed = 16; // Latitude[+N, -S] longitude[+W, -E]
        private float fuel = 250;
        private int altitude = 5000;
        private int airspeed = 128;
        private Location airportLoc;
        private double airportLat = 31.833;
        private double airportLong = -106.38;
        private double utepLat = 31.7676; //UTEP CS building
        private double utepLong = -106.5020;

        protected Location loc = new Location("FnPMS");

        private boolean mIsStopped = false;

        /*
         * Updates the card with a fake score every 30 seconds as a demonstration.
         * You also probably want to display something useful in your live card.
         *
         * If you are executing a long running task to get data to update a
         * live card(e.g, making a web call), do this in another thread or
         * AsyncTask.
         */
        public void run(){
            airportLoc = new Location("FnPMS");
            airportLoc.setLatitude(airportLat);
            airportLoc.setLongitude(airportLong);
            loc.setLatitude(utepLat);
            loc.setLongitude(utepLong);

            if(!isStopped()){
                //Location loc = new Location("FnPMS");
                for (int i = 0; i < 120; i++) {
                    try {
                        Thread.sleep(1000);  // sleep for 5s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextLoc();
                    airspeed += 2;
                    // Update the remote view with the new scores.
                    updateDisplay();
                }

                // First turn
                setSpeed(32, 16);
                for (int i = 0; i < 120; i++) {
                    try {
                        Thread.sleep(1000);  // sleep for 5s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextLoc();
                    altitude += 100;

                    updateDisplay();
                }


                // Second turn
                setSpeed(-16, 16);
                for (int i = 0; i < 120; i++) {
                    try {
                        Thread.sleep(1000);  // sleep for 5s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextLoc();
                    airspeed -= 2;
                    updateDisplay();
                }


            }
        }

        private void updateDisplay() {
            float heading = loc.bearingTo(airportLoc);

            String strLatlng = String.format("%.8f   %.8f",
                    loc.getLatitude(),
                    loc.getLongitude());
            mLiveCardView.setTextViewText(R.id.footer,
                    strLatlng);
            fuel -= 0.1;
            mLiveCardView.setTextViewText(R.id.speed_text_view,
                    String.format("%d", airspeed));
            mLiveCardView.setTextViewText(R.id.heading_text_view,
                    String.format("%.2f", heading));
            mLiveCardView.setTextViewText(R.id.altitude_text_view,
                    String.format("%d", altitude));
            mLiveCardView.setTextViewText(R.id.fuel_text_view,
                    String.format("%.0f", fuel));

            // Rotate map and update heading
            // This is some ugly hacky stuff
            int degree = Math.round(heading);
            Matrix mat = new Matrix();
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_airplane_64l);
            mat.postRotate(degree);     // angle to be rotated
            Bitmap bMapRotate = Bitmap.createBitmap(bMap, 0, 0,bMap.getWidth(),bMap.getHeight(), mat, true);
            mLiveCardView.setImageViewBitmap(R.id.imgHeading, bMapRotate);

            mLiveCard.setViews(mLiveCardView);
        }

        // Move the current location by lat and lngSpeed
        private void nextLoc() {
            loc.setLatitude(loc.getLatitude() + (latSpeed * 1e-4));
            loc.setLongitude(loc.getLongitude() + (longSpeed * 1e-4));
        }

        private void setSpeed(float latSpeed, float longSpeed) {
            this.latSpeed = latSpeed;
            this.longSpeed = longSpeed;
        }

        public boolean isStopped() {
            return mIsStopped;
        }

        public void setStop(boolean isStopped) {
            this.mIsStopped = isStopped;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
      /*
       * If you need to set up interprocess communication
       * (activity to a service, for instance), return a binder object
       * so that the client can receive and modify data in this service.
       *
       * A typical use is to give a menu activity access to a binder object
       * if it is trying to change a setting that is managed by the live card
       * service. The menu activity in this sample does not require any
       * of these capabilities, so this just returns null.
       */
        return null;
    }
}
