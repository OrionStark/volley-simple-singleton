public class ServiceSingleton {
    private RequestQueue requestQueue;
    private static ServiceSingleton singletonInstance;
    private static Context context;

    private ServiceSingleton(Context context){
        ServiceSingleton.context = context;
        this.requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if ( this.requestQueue == null ) {
            this.requestQueue = Volley.newRequestQueue(ServiceSingleton.context.getApplicationContext());
        }
        return this.requestQueue;
    }

    public static synchronized ServiceSingleton getInstance(Context context) {
        if ( ServiceSingleton.singletonInstance == null ) {
            ServiceSingleton.singletonInstance = new ServiceSingleton(context);
        }
        return ServiceSingleton.singletonInstance;
    }

    public <T> void addRequestToQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingResult(Object tag) {
        if ( this.requestQueue != null ) {
            this.requestQueue.cancelAll(tag);
        }
    }
}