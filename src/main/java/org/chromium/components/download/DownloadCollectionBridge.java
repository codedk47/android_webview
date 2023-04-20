package org.chromium.components.download;

import android.annotation.TargetApi;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace("download")
public class DownloadCollectionBridge {
    private static final String TAG = "DownloadCollection";
    protected static class DisplayNameInfo {
        private final String mUri;
        private final String mDisplayName;

        public DisplayNameInfo(String uri, String displayName) {
            mUri = uri;
            mDisplayName = displayName;
        }

        @CalledByNative("DisplayNameInfo")
        private String getDownloadUri() {
            return mUri;
        }

        @CalledByNative("DisplayNameInfo")
        private String getDisplayName() {
            return mDisplayName;
        }
    }
    @CalledByNative
    private static String getDisplayName(final String downloadUri) {
        return null;
    }
    @CalledByNative
    @TargetApi(29)
    private static DisplayNameInfo[] getDisplayNamesForDownloads() {
        return null;
    }
}
