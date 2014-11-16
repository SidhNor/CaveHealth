/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cavemen.cavehealth.gcm;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.gcm.command.TestCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.cavemen.cavehealth.util.LogUtils.LOGE;
import static com.cavemen.cavehealth.util.LogUtils.makeLogTag;

public class GCMIntentService extends IntentService {

    private static final String TAG = makeLogTag("GCM");

    private static final Map<String, GCMCommand> MESSAGE_RECEIVERS;

    static {
        // Known messages and their GCM message receivers
        Map<String, GCMCommand> receivers = new HashMap<String, GCMCommand>();
        receivers.put("test", new TestCommand());
        //TODO register all our commands here
        MESSAGE_RECEIVERS = Collections.unmodifiableMap(receivers);
    }

    public GCMIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int notificationId = 001;
        String message = intent.getStringExtra("message");
        String gameID = intent.getStringExtra("gameID");
        int iconRes = R.drawable.push_image;
        if(!gameID.isEmpty()){
            iconRes = R.drawable.icon_duel;
        }
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(getResources(),
                iconRes));
        style.setBigContentTitle("Caveman Health Monitor");
        style.setSummaryText(message);
        Notification notification =
                new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setStyle(style).setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        iconRes)).setContentTitle("Caveman Health Monitor").setContentText(message).
                    build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

// Issue the notification with notification manager.
        notificationManager.notify(notificationId, notification);
    }


}
