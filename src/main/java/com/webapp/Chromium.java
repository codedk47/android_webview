package com.webapp;

import android.app.Application;
import android.content.Context;
import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.android_webview.AwLocaleConfig;
import org.chromium.android_webview.shell.AwShellResourceProvider;
import org.chromium.android_webview.shell.AwShellSwitches;
import org.chromium.android_webview.test.AwTestContainerView;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.PathUtils;
import org.chromium.base.TraceEvent;
import org.chromium.ui.base.ResourceBundle;
public class Chromium extends Application {
    private static final String TAG = "AwShellActivity";
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        ContextUtils.initApplicationContext(this);
        PathUtils.setPrivateDataDirectorySuffix("webview", "WebView");
        CommandLine.initFromFile("/data/local/tmp/android-webview-command-line");
        ResourceBundle.setAvailablePakLocales(AwLocaleConfig.getWebViewSupportedPakLocales());
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AwShellResourceProvider.registerResources(this);
        AwBrowserProcess.loadLibrary(null);
        // This flag is deprecated. Print a hint instead.
        if (CommandLine.getInstance().hasSwitch(AwShellSwitches.ENABLE_ATRACE)) {
            Log.e(TAG, "To trace the test shell, run \"atrace webview\"");
        }
        TraceEvent.maybeEnableEarlyTracing(TraceEvent.ATRACE_TAG_WEBVIEW, /*readCommandLine=*/false);
        AwTestContainerView.installDrawFnFunctionTable(/*useVulkan=*/false);
        AwBrowserProcess.start();
    }
}
