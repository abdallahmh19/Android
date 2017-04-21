@Override
    public Loader <List <mMovie>> onCreateLoader(int id, Bundle args) {
         return new BookLoader(MainActivity.this ,finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<mMovie>> loader, List<mMovie> data) {
      //  bookApt.clear();
        if (data!=null && ! data.isEmpty()) {
            bookApt.addAll(data);
        }
        
    }
    @Override
    public void onLoaderReset(Loader<List<mMovie>> loader) {
      //  bookApt.clear();
      // bookApt.setBooks(new ArrayList<mMovie>());

    }
    public boolean isOnline() {
		//  use this permission <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }