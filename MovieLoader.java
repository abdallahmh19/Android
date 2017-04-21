public class MovieLoader extends AsyncTaskLoader<List<mMovie>> {

    String url;

    public MovieLoader(Context context , String url) {
        super(context);
        this.url=url;
    }
    @Override
    public List<mMovie> loadInBackground() {
     //   List<mMovie> movies = Connection.bookData(url);
        return movies;
    }

}