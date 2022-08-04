/*
 * Created by dengshiwei on 2022/06/15.
 * Copyright 2015－2021 Sensors Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sensorsdata.analytics.android.sdk.core.event.imp;

import com.sensorsdata.analytics.android.sdk.AnalyticsMessages;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.core.event.EventProcessor;
import com.sensorsdata.analytics.android.sdk.core.event.InputData;
import com.sensorsdata.analytics.android.sdk.internal.beans.EventType;
import com.sensorsdata.analytics.android.sdk.internal.beans.InternalConfigOptions;

public class SendDataImpl implements EventProcessor.ISendData {
    private final InternalConfigOptions mInternalConfigs;

    public SendDataImpl(InternalConfigOptions internalConfigs) {
        this.mInternalConfigs = internalConfigs;
    }

    @Override
    public void sendData(InputData inputData, int errorCode) {
        try {
            AnalyticsMessages.getInstance(mInternalConfigs.context).flushEventMessage(errorCode < 0
                    || errorCode > mInternalConfigs.saConfigOptions.getFlushBulkSize()
                    || mInternalConfigs.debugMode.isDebugMode()
                    || inputData.getEventType() == EventType.TRACK_SIGNUP);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }
}
