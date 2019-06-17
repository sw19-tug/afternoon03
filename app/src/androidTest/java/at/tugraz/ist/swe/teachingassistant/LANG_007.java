package at.tugraz.ist.swe.teachingassistant;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.Display;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LANG_007 {

    @Test
    public void addTestToDb() throws Exception {
        DatabaseHelper db = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        int beforeCount = db.getTotalCount();
        Log.i("Before count: ",Integer.toString(beforeCount));
        db.insert(12,10);
        int afterCount = db.getTotalCount();
        Log.i("After count: ",Integer.toString(afterCount));
        for (StoredTest test: db.getAllNotes()) {
            Log.i("Stored test: ",test.getId() + " " + test.getCorrect() + " " + test.getSize() + " " + test.getDate());
        }

        assertEquals(beforeCount+ 1 , afterCount);
    }
}

