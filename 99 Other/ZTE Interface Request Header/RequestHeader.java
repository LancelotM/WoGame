	@Override
	public Map<String, String> createRequestHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(USER_AGENT, VendingUtils.getUserAgent());
		headers.put(STOREUA, VendingUtils.getUserAgent());
		headers.put(X_UP_CALLINHG_LINE_ID,
				VendingUtils.getPhoneNumber(UnicomApp.get()));
		headers.put(HAND_PHONE, VendingUtils.getPhoneNumber(UnicomApp.get()));
		headers.put(HANDUA, VendingUtils.getHandua());
		headers.put(SETTER_TYPE, "3");
		headers.put(VERSION, String.valueOf(PackageManagerHelper
				.getPackageVersionCode(UnicomApp.get().getPackageName())));
		headers.put(IMEI, VendingUtils.getIMEI(UnicomApp.get()));
		headers.put(IMSI, VendingUtils.getIMSI(UnicomApp.get()));
		headers.put(PREA_SSEMBLE, VendingUtils.getUaInfo());
		headers.put(COMPANY_LOGO, UnicomPreferences.channelId.get());
		headers.put(SESSION_ID, UnicomPreferences.zteSession.get());
		headers.put(APP_FROM, "openfeint");
		headers.put(NEW_CLIENT, "1");
		headers.put(PHONE_ACCESS_MODE,
				VendingUtils.getPhoneAccessMode(UnicomApp.get()));
		headers.put(USER_TYPE, "3");
		headers.put(CLIENT_CHANNEL_FLAG, "8");
		return headers;
	}

	Uri.Builder builder = ZTE_BASE_URI
			.buildUpon()
			.appendQueryParameter(ZTE_REQUEST_SERVICE_ID,
					ZTE_REQUEST_SERVICE_DETAIL)
			.appendQueryParameter(ZTE_REQUEST_PRODUCT_ID,
					String.valueOf(productId))
			.appendQueryParameter("state", "101")
			.appendQueryParameter("referer", "" + refid);
	return builder.build().toString();
