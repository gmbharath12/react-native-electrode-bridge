package com.walmartlabs.electrode.reactnative.bridge;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.walmartlabs.electrode.reactnative.bridge.helpers.Logger;

@SuppressWarnings("unused")
public class EventDispatcherImpl implements EventDispatcher {

    private static final String TAG = EventDispatcherImpl.class.getSimpleName();

    private final EventRegistrar<ElectrodeBridgeEventListener<Bundle>> mEventRegistrar;
    private static final Bundle EMPTY_BUNDLE = new Bundle();

    public EventDispatcherImpl(EventRegistrar<ElectrodeBridgeEventListener<Bundle>> eventRegistrar) {
        mEventRegistrar = eventRegistrar;
    }

    @Override
    public void dispatchEvent(@NonNull ElectrodeBridgeEvent bridgeEvent) {
        for (ElectrodeBridgeEventListener<Bundle> eventListener : mEventRegistrar.getEventListeners(bridgeEvent.getName())) {
            Logger.d(TAG, "Event dispatcher is dispatching event(%s), id(%s) to listener(%s)", bridgeEvent.getName(), bridgeEvent.getName(), eventListener);
            eventListener.onEvent(bridgeEvent.getData());
        }
    }
}
