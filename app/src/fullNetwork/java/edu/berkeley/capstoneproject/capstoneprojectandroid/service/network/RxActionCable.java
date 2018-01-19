package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import timber.log.Timber;

/**
 * Created by Alex on 16/01/2018.
 */

public class RxActionCable implements IRxWebSocket {

    private static final int NORMAL_CLOSURE = 1000;
    private static final int GOING_AWAY = 1001;
    private static final int PROTOCOL_ERROR = 1002;
    private static final int TYPE_ERROR = 1003;

    private static final String COMMAND_SUBSCRIBE = "subscribe";
    private static final String COMMAND_MESSAGE = "message";

    private static final String TYPE_CONFIRMED_SUBSCRIPTION = "confirm_subscription";

    private final OkHttpClient mOkHttpClient;
    private final Request mRequest;
    private final String mChannel;
    private final String mChannelParams[];

    private WebSocket mWebSocket;
    private boolean mSubscribed = false;

    private final List<ConnectionListener> mConnectionListeners = new ArrayList<>();
    private final List<MessageListener> mMessageListeners = new ArrayList<>();

    public RxActionCable(OkHttpClient client, Request request, String channel, String ...channelParams) {
        mOkHttpClient = client;
        mRequest = request;
        mChannel = channel;
        mChannelParams = channelParams;
    }

    @Override
    public Completable connect() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                mWebSocket = mOkHttpClient.newWebSocket(mRequest, getWebSocketListener());
                mConnectionListeners.add(new ConnectionListener() {
                    @Override
                    public void onConnected() {
                        e.onComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        e.onError(throwable);
                    }

                    @Override
                    public void onClosed() {

                    }
                });
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                close();
            }
        });
    }

    public Observable<String> listen() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                mMessageListeners.add(new MessageListener() {
                    @Override
                    public void onMessage(String message) {
                        e.onNext(message);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        e.onError(throwable);
                    }

                    @Override
                    public void onClosed() {
                        e.onComplete();
                    }
                });
            }
        });
    }

    private void subscribe() {
        try {
            JSONObject json = new JSONObject()
                    .put("command", COMMAND_SUBSCRIBE)
                    .put("identifier", getIdentifier());

            mWebSocket.send(json.toString());
        } catch (JSONException e) {
            Timber.e(e);
        }
    }

    private String getIdentifier() {
        StringBuilder identifier = new StringBuilder("{\"channel\":\"" + mChannel + "\"");
        for (String param: mChannelParams) {
            identifier.append(",");
            identifier.append(param);
        }
        identifier.append("}");
        return identifier.toString();
    }

    @Override
    public void send(final String data) {
        JSONObject json = null;
        try {
            json = new JSONObject()
                    .put("command", COMMAND_MESSAGE)
                    .put("identifier", getIdentifier())
                    .put("data", data);
            mWebSocket.send(json.toString());
        } catch (JSONException e) {
            Timber.w(e);
        }
    }

    private String parseData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject.getString("data");
        } catch (JSONException e) {
            Timber.w(e);
            return "";
        }
    }

    private String parseType(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject.getString("type");
        } catch (JSONException e) {
            Timber.w(e);
            return "";
        }
    }

    @Override
    public Completable disconnect() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                close();
            }
        });
    }

    private void close() {
        if (mWebSocket != null) {
            Timber.d("Disconnecting from ActionCable: %s", getIdentifier());
            mWebSocket.close(NORMAL_CLOSURE, "End of stream");
        }
    }





    private WebSocketListener getWebSocketListener() {
        return new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                mWebSocket = webSocket;
                RxActionCable.this.subscribe();
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                mWebSocket = webSocket;
                if (mSubscribed) {
                    for (MessageListener messageListener: mMessageListeners) {
                        messageListener.onMessage(text);
                    }
                }
                else if (parseType(text).equals(TYPE_CONFIRMED_SUBSCRIPTION)) {
                    Timber.d("Subscribed to ActionCable: %s", getIdentifier());
                    mSubscribed = true;
                    for (ConnectionListener connectionListener: mConnectionListeners) {
                        connectionListener.onConnected();
                    }
                }
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                mWebSocket = webSocket;
                if (mSubscribed) {
                    for (MessageListener messageListener: mMessageListeners) {
                        messageListener.onMessage(bytes.toString());
                    }
                }
                else if (parseType(bytes.toString()).equals(TYPE_CONFIRMED_SUBSCRIPTION)) {
                    Timber.d("Subscribed to ActionCable: %s", getIdentifier());
                    mSubscribed = true;
                    for (ConnectionListener connectionListener: mConnectionListeners) {
                        connectionListener.onConnected();
                    }
                }
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                mWebSocket = webSocket;
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                mWebSocket = webSocket;

                for (ConnectionListener connectionListener: mConnectionListeners) {
                    connectionListener.onClosed();
                }
                for (MessageListener messageListener: mMessageListeners) {
                    messageListener.onClosed();
                }
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                mWebSocket = webSocket;
                for (ConnectionListener connectionListener: mConnectionListeners) {
                    connectionListener.onError(t);
                }
                for (MessageListener messageListener: mMessageListeners) {
                    messageListener.onError(t);
                }
            }
        };
    }

    private interface Listener {
        void onError(Throwable throwable);
        void onClosed();
    }

    private interface ConnectionListener extends Listener {
        void onConnected();
    }

    private interface MessageListener extends Listener {
        void onMessage(String message);
    }
}
