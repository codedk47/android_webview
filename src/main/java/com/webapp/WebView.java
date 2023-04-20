package com.webapp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.widget.EditText;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwConsoleMessage;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsClient;
import org.chromium.android_webview.AwContentsClientBridge;
import org.chromium.android_webview.AwDevToolsServer;
import org.chromium.android_webview.AwGeolocationPermissions;
import org.chromium.android_webview.AwHttpAuthHandler;
import org.chromium.android_webview.AwRenderProcess;
import org.chromium.android_webview.AwRenderProcessGoneDetail;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.JsPromptResultReceiver;
import org.chromium.android_webview.JsResultReceiver;
import org.chromium.android_webview.R;
import org.chromium.android_webview.SafeBrowsingAction;
import org.chromium.android_webview.permission.AwPermissionRequest;
import org.chromium.android_webview.safe_browsing.AwSafeBrowsingResponse;
import org.chromium.android_webview.test.AwTestContainerView;
import org.chromium.base.Callback;
import org.chromium.base.Log;
import org.chromium.components.embedder_support.util.WebResourceResponseInfo;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.payload_reader.ChannelReader;

public class WebView extends Activity {
    private Handler mUiThreadHandler;
    private ActionBar actionBar;
    private static final String PREFERENCES_NAME = "AwShellPrefs";
    private AwTestContainerView awTestContainerView;
    private AwBrowserContext awBrowserContext;
    private AwContentsClient awContentsClient;
    private AwSettings awSettings;
    private final ArrayList<AwContents> awContents = new ArrayList<>();
    private AwDevToolsServer mDevToolsServer;
    private final List<String> supportSchemes = Arrays.asList("file", "blob", "http", "https");
    private static final int NEW_WEBVIEW_CREATED = 100;
    private static final int NEW_WEBVIEW_CLOSED = 101;
    private static final int UPLOAD_FILE_CHOOSER = 1;
    private Callback<String[]> mUploadFilePathsCallback;
    private static final String testPageUrl = "file:///android_asset/test.html";
    private static final String[] testWebSockets = new String[] {
//            "ws://websocket.example.com"
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.btn_close);
        actionBar.hide();



//        ApplicationInfo appInfo;
//        try {
//            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//            appName = appInfo.loadLabel(getPackageManager()) + "";
//        } catch (Exception e) {
//            appName = "webapp";
//        }

        awTestContainerView = new AwTestContainerView(this, true);

        awBrowserContext = new AwBrowserContext(
                getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE),
                AwBrowserContext.getDefault().getNativePointer(),
                true);

        awContentsClient = new WebViewClient();

        awSettings = new AwSettings(this,
                true,
                false,
                false,
                false,
                false) {{
                    setAllowContentAccess(true);
                    setAllowFileAccess(true);
                    setAllowFileAccessFromFileURLs(true);
                    setAllowUniversalAccessFromFileURLs(true);
                    setCacheMode(WebSettings.LOAD_DEFAULT);
                    setDatabaseEnabled(true);
                    setDomStorageEnabled(true);
                    setFullscreenSupported(true);
                    setJavaScriptCanOpenWindowsAutomatically(true);
                    setJavaScriptEnabled(true);
                    setLoadsImagesAutomatically(true);
                    setMediaPlaybackRequiresUserGesture(false);
                    setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                    setSupportMultipleWindows(true);
                    setSupportZoom(false);
                    setTextZoom(100);
                    setUseWideViewPort(true);
                    setUserAgentString(getUserAgentString() +
                        "; DID/".concat(did()) +
                        "; CID/".concat(cid()));


//                    setLoadWithOverviewMode(true);
//                    setBuiltInZoomControls(true);
//                    setDisplayZoomControls(false);
//                    setLayoutAlgorithm(AwSettings.LAYOUT_ALGORITHM_TEXT_AUTOSIZING);

        }};

        AwContents awContent = new AwContents(awBrowserContext,
                awTestContainerView,
                awTestContainerView.getContext(),
                awTestContainerView.getInternalAccessDelegate(),
                awTestContainerView.getNativeDrawFunctorFactory(),
                awContentsClient,
                awSettings);

        awTestContainerView.initialize(awContent);

        if (mDevToolsServer == null) {
            mDevToolsServer = new AwDevToolsServer();
            mDevToolsServer.setRemoteDebuggingEnabled(true);
        }

        awContent.loadUrl(testPageUrl);
        awContents.add(awContent);
        setContentView(awTestContainerView);
    }
    @Override
    public void onDestroy() {
        awTestContainerView.destroy();
        super.onDestroy();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        setContentView(awTestContainerView);
    }
    public boolean showTab() {
        AwContents awContent = awTestContainerView.getAwContents();
        if (awContent != awContents.get(0)) {
            actionBar.setTitle(awContent.getTitle());
            actionBar.setSubtitle(awContent.getOriginalUrl());
            actionBar.show();
            return true;
        }
        actionBar.hide();
        return false;
    }
    @Override
    public void onBackPressed() {
        if (awTestContainerView.getAwContents() == awContents.get(0)) {
            super.onBackPressed();
        } else {
            mUiThreadHandler.sendEmptyMessage(NEW_WEBVIEW_CLOSED);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mUiThreadHandler.sendEmptyMessage(NEW_WEBVIEW_CLOSED);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_WEBVIEW_CREATED) {
            String[] results = null;
            if(resultCode == Activity.RESULT_OK && data != null) {
                String content = data.getDataString();
                if (content != null) {
                    Uri[] files = new Uri[]{Uri.parse(content)};
                    results = new String[files.length];
                    for(int i = 0; i < files.length; i++) {
                        results[i] = files[i].toString();
                    }
                }
            }
            mUploadFilePathsCallback.onResult(results);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static class AwContentsTransport {
        private AwContents awContents;
        public synchronized void setAwContents(AwContents newAwContents) {
            awContents = newAwContents;
        }
        public synchronized AwContents getAwContents() {
            return awContents;
        }
    }
    private class WebViewClient extends AwContentsClient {

        private static final String TAG = "WebViewCallback";

        private View mCustomView;

        public WebViewClient() {
            super(Looper.myLooper());
            mUiThreadHandler = new Handler(Looper.myLooper()) {
                AwContents awContent;
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case NEW_WEBVIEW_CREATED:
                            AwContentsTransport t = (AwContentsTransport) msg.obj;
                            awContent = t.getAwContents();
                            awContents.add(awContent);
                            awTestContainerView.getAwContents().supplyContentsForPopup(awContent);
                            awTestContainerView.initialize(awContent);
                            setContentView(awTestContainerView);
                            return;
                        case NEW_WEBVIEW_CLOSED:
                            awContent = awTestContainerView.getAwContents();
                            if (awContent != awContents.get(0)) {
                                awContent.onDetachedFromWindow();
                                awContent.destroy();
                                awContents.remove(awContent);
                                awContent = awContents.size() > 1 ? awContents.get(awContents.size() - 1) : awContents.get(0);
                                awTestContainerView.initialize(awContent);
                                setContentView(awTestContainerView);
                                showTab();
                            }
                            return;
                        default:
                            super.handleMessage(msg);
                    }
//                    if (msg.what == NEW_WEBVIEW_CREATED)
//                    {
//                        AwContentsTransport t = (AwContentsTransport) msg.obj;
//                        awContent = t.getAwContents();
//                        awContents.add(awContent);
//                        awContents.get(0).supplyContentsForPopup(awContent);
//                        awTestContainerView.initialize(awContent);
//                        setContentView(awTestContainerView);
//                        showTab(awContent);
//
//
////                        if (newAwContents == null) {
////                            AwContentsTransport t = (AwContentsTransport) msg.obj;
////                            newAwContents = t.getAwContents();
////                            awContents.supplyContentsForPopup(newAwContents);
////                            awTestContainerView.initialize(newAwContents);
////                        } else {
////                            newAwContents.destroy();
////                            newAwContents = null;
////                            awTestContainerView.initialize(awContents);
////                        }
////                        setContentView(awTestContainerView);
//                    }
                }
            };
        }
        @Override
        public boolean onCreateWindow(boolean isDialog, boolean isUserGesture) {
            if (isUserGesture) {
                showTab();
                Message msg = mUiThreadHandler.obtainMessage(NEW_WEBVIEW_CREATED, new AwContentsTransport());
                WebView.AwContentsTransport transport = (WebView.AwContentsTransport) msg.obj;
                transport.setAwContents(new AwContents(awBrowserContext,
                        awTestContainerView,
                        awTestContainerView.getContext(),
                        awTestContainerView.getInternalAccessDelegate(),
                        awTestContainerView.getNativeDrawFunctorFactory(),
                        awContentsClient,
                        awSettings));
                msg.sendToTarget();
                return true;
            }
            return false;
        }
        @Override
        public void onCloseWindow() {
            mUiThreadHandler.sendEmptyMessage(NEW_WEBVIEW_CLOSED);
        }
        @Override
        public void handleJsAlert(String url, String message, JsResultReceiver receiver) {
            new AlertDialog.Builder(WebView.this)
                    .setOnDismissListener((DialogInterface dialog) -> receiver.cancel())
                    .setMessage(message)
                    .setPositiveButton("OK", (DialogInterface dialog, int which) -> receiver.confirm())
                    .create().show();
        }
        @Override
        public void handleJsConfirm(String url, String message, JsResultReceiver receiver) {
            new AlertDialog.Builder(WebView.this)
                    .setOnDismissListener((DialogInterface dialog) -> receiver.cancel())
                    .setMessage(message)
                    .setPositiveButton("OK", (DialogInterface dialog, int which) -> receiver.confirm())
                    .setNegativeButton("NO", (DialogInterface dialog, int which) -> receiver.cancel())
                    .create().show();
        }
        @Override
        public void handleJsPrompt(String url, String message, String defaultValue, JsPromptResultReceiver receiver) {
            EditText input = new EditText(WebView.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setText(defaultValue);
            new AlertDialog.Builder(WebView.this)
                    .setOnDismissListener((DialogInterface dialog) -> receiver.cancel())
                    .setMessage(message).setView(input)
                    .setPositiveButton("OK", (DialogInterface dialog, int which) -> receiver.confirm(input.getText().toString()))
                    .setNegativeButton("NO", (DialogInterface dialog, int which) -> receiver.cancel())
                    .create().show();
        }
        @Override
        public void onShowCustomView(View view, AwContentsClient.CustomViewCallback callback) {
            mCustomView = view;
            awTestContainerView.addView(mCustomView);
        }

        @Override
        public void onHideCustomView() {
            setContentView(awTestContainerView);
            awTestContainerView.removeView(mCustomView);
            mCustomView = null;
        }
        @Override
        public void showFileChooser(Callback<String[]> uploadFilePathsCallback, FileChooserParamsImpl fileChooserParams) {
            mUploadFilePathsCallback = uploadFilePathsCallback;
            startActivityForResult(fileChooserParams.createIntent(), UPLOAD_FILE_CHOOSER);
        }

        @Override
        public boolean shouldOverrideKeyEvent(KeyEvent event) {
            return event.getKeyCode() == KeyEvent.KEYCODE_BACK;
        }
        @Override
        public void onGeolocationPermissionsShowPrompt(
                String origin, AwGeolocationPermissions.Callback callback) {
            callback.invoke(origin, false, false);
        }
        @Override
        public boolean hasWebViewClient() {
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(AwContentsClient.AwWebResourceRequest request) {
            Uri uri = Uri.parse(request.url);
            if (supportSchemes.contains(uri.getScheme().toLowerCase())) {
                return false;
            }
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } catch (Exception e) {
                new AlertDialog.Builder(WebView.this).setMessage(e.getMessage()).show();
            }
            //onCloseWindow();
            return true;
        }

        @Override
        public void onUnhandledKeyEvent(KeyEvent event) {
        }

        @Override
        public void getVisitedHistory(Callback<String[]> callback) {}

        @Override
        public void doUpdateVisitedHistory(String url, boolean isReload) {
        }

        @Override
        public void onProgressChanged(int progress) {
        }

        @Override
        public WebResourceResponseInfo shouldInterceptRequest(
                AwContentsClient.AwWebResourceRequest request) {
            return null;
        }

        @Override
        public void onLoadResource(String url) {
        }

        @Override
        public boolean onConsoleMessage(AwConsoleMessage consoleMessage) {
            //Log.d("console("+consoleMessage.messageLevel()+")", consoleMessage.message());
            return false;
        }

        @Override
        public void onReceivedHttpAuthRequest(AwHttpAuthHandler handler, String host, String realm) {
            handler.cancel();
        }

        @Override
        public void onReceivedSslError(Callback<Boolean> callback, SslError error) {
            callback.onResult(false);
        }

        @Override
        public void onReceivedClientCertRequest(
                final AwContentsClientBridge.ClientCertificateRequestCallback callback,
                final String[] keyTypes, final Principal[] principals, final String host,
                final int port) {
            callback.proceed(null, null);
        }

        @Override
        public void onReceivedLoginRequest(String realm, String account, String args) {
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
        }

        @Override
        public void handleJsBeforeUnload(String url, String message, JsResultReceiver receiver) {
            android.util.Log.i(TAG, "handleJsBeforeUnload(" + url + ", " + message + ")");
            receiver.confirm();
        }

        @Override
        public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches,
                                         boolean isDoneCounting) {
        }

        @Override
        public void onNewPicture(Picture picture) {
        }

        @Override
        public void onPageStarted(String url) {
        }

        @Override
        public void onPageFinished(String url) {
            if (!showTab()) {
                if (testPageUrl.startsWith("file")) {
                    awContents.get(0).loadUrl(testWebSockets.length == 0
                            ? "javascript:postMessage(null)"
                            : "javascript:postMessage('"+String.join(",", testWebSockets)+"')"
                    );
                }
            }
        }
        @Override
        public void onPageCommitVisible(String url) {
        }

        @Override
        public void onReceivedError(int errorCode, String description, String failingUrl) {
        }

        @Override
        public void onReceivedError2(AwWebResourceRequest request, AwWebResourceError error) {
        }

        @Override
        public void onSafeBrowsingHit(AwWebResourceRequest request, int threatType,
                                      Callback<AwSafeBrowsingResponse> callback) {
            callback.onResult(new AwSafeBrowsingResponse(SafeBrowsingAction.SHOW_INTERSTITIAL,
                    /* reporting */ true));
        }

        @Override
        public void onReceivedHttpError(
                AwWebResourceRequest request, WebResourceResponseInfo response) {}

        @Override
        public void onFormResubmission(Message dontResend, Message resend) {
            dontResend.sendToTarget();
        }

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
            Log.d("onDownloadStart", url);
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            if (downloadManager == null) return;
            long id = downloadManager.enqueue(new DownloadManager.Request(Uri.parse(url)) {{
                String fileName = URLUtil.guessFileName(url, contentDisposition, mimeType);
                setTitle(fileName);
                setDescription(contentDisposition);
                setMimeType(mimeType);
                //request.allowScanningByMediaScanner();
                setAllowedOverMetered(true);
                setAllowedOverRoaming(true);
                setVisibleInDownloadsUi(true);
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                addRequestHeader("User-Agent", userAgent);
            }});
            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Cursor cursor = downloadManager.query(new DownloadManager.Query().setFilterById(id));
                    if (cursor.moveToFirst()) {
                        switch (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                            case DownloadManager.STATUS_PAUSED:
                                Log.d("onDownloadStart", "STATUS_PAUSED");
                                break;
                            case DownloadManager.STATUS_PENDING:
                                Log.d("onDownloadStart", "STATUS_PENDING");
                                break;
                            case DownloadManager.STATUS_RUNNING:
                                Log.d("onDownloadStart", "STATUS_RUNNING");
                                break;
                            case DownloadManager.STATUS_SUCCESSFUL:
                                Log.d("onDownloadStart", "STATUS_SUCCESSFUL");
                                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                                break;
                            case DownloadManager.STATUS_FAILED:
                                downloadManager.remove(id);
                                break;
                        }
                        unregisterReceiver(this);
                    }
                }
            }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            if (awTestContainerView.getAwContents().getOriginalUrl() == null) {
                mUiThreadHandler.sendEmptyMessage(NEW_WEBVIEW_CLOSED);
            }
        }

        @Override
        public void onRequestFocus() {
        }

        @Override
        public void onReceivedTouchIconUrl(String url, boolean precomposed) {
        }

        @Override
        public void onReceivedIcon(Bitmap bitmap) {
        }

        @Override
        public void onReceivedTitle(String title) {
        }

        @Override
        public void onScaleChangedScaled(float oldScale, float newScale) {
        }

        @Override
        protected View getVideoLoadingProgressView() {
            return null;
        }

        @Override
        public Bitmap getDefaultVideoPoster() {
            return null;
        }

        @Override
        public void onPermissionRequest(AwPermissionRequest awPermissionRequest) {
            awPermissionRequest.deny();
        }

        @Override
        public void onPermissionRequestCanceled(AwPermissionRequest awPermissionRequest) {
        }

        @Override
        public void onRendererUnresponsive(AwRenderProcess process) {}

        @Override
        public void onRendererResponsive(AwRenderProcess process) {}

        @Override
        public boolean onRenderProcessGone(AwRenderProcessGoneDetail detail) {
            return false;
        }
    }

    @SuppressLint("HardwareIds")
    private String did() {
        String did;
        try {
            did = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            did = Build.BOARD+
                    Build.BRAND +
                    Build.DEVICE +
                    Build.HARDWARE +
                    Build.ID +
                    Build.MODEL +
                    Build.PRODUCT +
                    Build.SERIAL;
        }
        long hash = 5381;
        int len = did.length();
        if (did.length() > 0) {
            for (int i = 0; i < len; ++i) {
                hash += (hash << 5) + did.charAt(i);
            }
        }
        did = "000000000000000" + Long.toHexString(hash);
        return did.substring(did.length() - 16);
    }
    private String cid() {
        try {
            return Objects.requireNonNull(ChannelReader.get(new File(this.getApplicationInfo().sourceDir))).getChannel();
        } catch (Exception e) {
            return "";
        }
    }
}
