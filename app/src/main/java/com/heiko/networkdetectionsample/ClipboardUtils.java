package com.heiko.networkdetectionsample;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * 剪切板工具类
 */
public class ClipboardUtils {

    /**
     * 复制到剪切板
     *
     * @param context
     * @param text
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 获取剪贴板指定Index的文本
     *
     * @param context
     * @param index
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getText(Context context, int index) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > index) {
            return String.valueOf(clip.getItemAt(index).coerceToText(context));
        }
        return null;
    }

    /**
     * 获取剪切板最后的文本
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getLatestText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }
}
