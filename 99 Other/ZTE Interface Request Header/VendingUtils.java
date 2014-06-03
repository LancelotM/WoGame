package com.unicom.android.game.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowManager;

import com.unicom.android.game.R;
import com.unicom.android.game.config.UnicomPreferences;

/**
 * This class provides some helper methods to obtain the device's base info,
 * such as screen dimensions, IMEI, IMSI Etc.
 * 
 * @author wuss
 * @since 2013-3-8
 */
public class VendingUtils {

	/**
	 * Gets the device's screen dimensions.
	 * 
	 * @param context
	 *            The application context.
	 * @return dimensions
	 */
	public static Pair<Integer, Integer> getScreenDimensions(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		int width = Math.min(metrics.widthPixels, metrics.heightPixels);
		int height = Math.max(metrics.widthPixels, metrics.heightPixels);
		Pair<Integer, Integer> dimensions = new Pair<Integer, Integer>(
				Integer.valueOf(width), Integer.valueOf(height));
		return dimensions;
	}

	/**
	 * Gets the unique device ID
	 * 
	 * @param context
	 *            The application context.
	 * @return deviceId
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * Gets the unique subscriber ID
	 * 
	 * @param context
	 *            The application context.
	 * @return subscriberId
	 */
	public static String getIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSubscriberId();
	}

	/**
	 * Gets the phone number string.
	 * 
	 * @param context
	 *            The application context.
	 * @return phone number string
	 */
	public static String getPhoneNumber(Context context) {
		if (!TextUtils.isEmpty(UnicomPreferences.userName.get())) {
			return UnicomPreferences.userName.get();
		}
		String im = getIMSI(context);
		if (TextUtils.isEmpty(im)) {
			im = getIMEI(context);
		}
		if (TextUtils.isEmpty(im)) {
			im = "00000000000";
		}
		return im;
	}

	public static void setPhoneNumber(Context context) {
		String im = getIMSI(context);
		if (TextUtils.isEmpty(im)) {
			im = getIMEI(context);
		}
		if (TextUtils.isEmpty(im)) {
			im = "00000000000";
		}
		UnicomPreferences.userName.put(im);
	}

	public static String getPhoneNumberForNine(Context context) {
		return UnicomPreferences.unicomNumber.get();
	}

	/**
	 * Gets the model name of device.
	 * 
	 * @return
	 */
	public static String getDeviceModel() {
		return Build.MODEL;
	}

	/**
	 * Gets the OS version. MANUFACTURER
	 * 
	 * @return
	 */
	public static String getOsVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * Gets the custom user agent.
	 * 
	 * @return
	 */
	public static String getUserAgent() {
		StringBuilder agent = new StringBuilder();
		agent.append("Anroid/").append(getDeviceModel());
		return agent.toString();
	}

	public static String getHandua() {
		if (TextUtils.isEmpty(UnicomPreferences.handua.get())) {
			return "9000000000";
		}
		return UnicomPreferences.handua.get();
	}

	public static String getUaInfo() {
		if (TextUtils.isEmpty(UnicomPreferences.uaInfo.get())) {
			return "Android-v16>Common>V2.0.0>20120328>NA>NA>NA>beiyong>NA>NA";
		}
		return UnicomPreferences.uaInfo.get();
	}

	/**
	 * Gets the manufacturer of the product/hardware.
	 * 
	 * @return MANUFACTURER
	 */
	public static String getManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 * 获取当前应用的版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
		}
		return 0;
	}

	/**
	 * Gets the version name of game center.
	 * 
	 * @param context
	 * @return versionName
	 */
	public static String getVersionName(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
		}
		return "";
	}

	/**
	 * identifier
	 * */
	public static String findUDID(Context context) {
		String androidID = android.provider.Settings.Secure.getString(
				context.getContentResolver(),
				android.provider.Settings.Secure.ANDROID_ID);
		if (androidID == null) {
			try {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				androidID = tm.getDeviceId();
			} catch (SecurityException e) {
			}
			if (androidID != null && androidID.length() > 16) {
				androidID = androidID.substring(0, 16);
			}
		}
		// If there's no android ID, or if it's the magic universal 2.2 emulator
		// ID, we need to generate one.
		if (androidID != null && !androidID.equals("9774d56d682e549c")
				&& !androidID.equals("000000000000000")) {
			return "android-id-" + androidID;
		}
		return androidID;
	}

	/**
	 * hardware
	 * */
	public static String getModelString() {
		return "p(" + android.os.Build.PRODUCT + ")/m("
				+ android.os.Build.MODEL + ")";
	}

	public static String getMacAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifiManager.getConnectionInfo();
		String mac = info.getMacAddress();
		if (mac == null) {
			mac = "";
		}
		return mac;
	}

	public static String getOSVersionString() {
		return "v" + android.os.Build.VERSION.RELEASE + " ("
				+ android.os.Build.VERSION.INCREMENTAL + ")";
	}

	/**
	 * screen_resolution
	 * */
	public static String getScreenInfo(Context context) {
		DisplayMetrics metrics = getDisplayMetrics(context);
		return String.format("%dx%d (%f dpi)", metrics.widthPixels,
				metrics.heightPixels, metrics.density);
	}

	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	public static String getPhoneAccessMode(Context context) {
		String accessMode = "3";
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			switch (networkInfo.getType()) {
			case ConnectivityManager.TYPE_MOBILE:
				String extraInfo = networkInfo.getExtraInfo();
				if (extraInfo == null) {
					break;
				}
				if (extraInfo.toLowerCase().equals("3gwap")) {
					accessMode = "31";
				} else if (extraInfo.toLowerCase().equals("3gnet")) {
					accessMode = "32";
				} else {
					accessMode = "3";
				}
				break;
			case ConnectivityManager.TYPE_WIFI:
				accessMode = "3";
				break;
			default:
				accessMode = "3";
				break;
			}
		}
		return accessMode;
	}

	/**
	 * 将时间戳转为代表"距现在多久之前"的字符串
	 * 
	 * @param timeStr
	 *            时间戳
	 * @return
	 */
	public static String getStandardDate(long timeStr, Context context) {

		StringBuffer sb = new StringBuffer();

		long t = timeStr;
		long time = System.currentTimeMillis() - (t);
		long mill = (long) Math.ceil(time / 1000);// 秒前

		long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

		long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

		if (day - 1 > 0) {
			return DateUtil.dateToString(new Date(timeStr),
					DateUtil.FORMAT_DATE);
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				sb.append(context.getResources().getString(R.string.one_day));
			} else {
				sb.append(hour
						+ context.getResources().getString(R.string.hours));
			}
		} else if (minute - 1 > 0) {
			if (minute == 60) {
				sb.append(context.getResources().getString(R.string.one_hour));
			} else {
				sb.append(minute
						+ context.getResources().getString(R.string.minutes));
			}
		} else if (mill - 1 > 0) {
			if (mill == 60) {
				sb.append(context.getResources().getString(R.string.one_minute));
			} else {
				sb.append(mill
						+ context.getResources().getString(R.string.seconds));
			}
		} else {
			sb.append(context.getResources().getString(R.string.just_now));
		}
		if (!sb.toString().equals(
				context.getResources().getString(R.string.just_now))) {
			sb.append(context.getResources().getString(R.string.ago));
		}
		return sb.toString();
	}

	/**
	 * Although it is not recommended for systems that involve even the basic
	 * level of security (MD5 is considered broken and can be easily exploited),
	 * it is sometimes enough for basic tasks.
	 * 
	 * @param source
	 * @return
	 */
	public static final String md5(final String source) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(source.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
