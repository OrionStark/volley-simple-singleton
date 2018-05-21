import ServiceSingleton
/*
    Next update nanti kukasih cara pakai callback sama json parameter request.
 */

class ExampleForVolley {
    /* Ada beberapa jenis request yang bisa dilakukan dengan volley. Ada string request, json request, dll
     * Tapi kita pake StringRequest aja dulu, biar ngerti jalannya dulu.
    */
    private static String REQUEST_TAG = "example.request";
    public static void ExampleCall(String param_1, String param_2, Context context) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://localhost:8080/ini_contoh_api_urlnya", 
        new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                // Disini kita dapat response dari APInya
                // Disini responsenya dalam bentuk string, untuk ubah keJSON gini caranya
                // Parsing string keJSON hrus ada try catchnya. Karena bisa aja gagal. Siapa tau API ngeresponse string tapi bukan bentuk jsonstring
                try {
                    JSONObject json = new JSONObject(response);
                    // Misalkan didalam json ada array. Cara ambilnya gini
                    JSONObject jsonArray = json.getJSONArray("nama_properties_objectnya");
                    // Mau ambil properties yang isinya string gini
                    String nama = json.getString("nama_pengguna");
                } catch (Exception e) {
                    // Disini handle kalau ada error dari JSONParsingnya
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void OnErrorResponse(VolleyError error) {
                // Disini nanti kita handle errornya
            }
        }) {
            // Ini digunakan kalau API kita menerima request body berbentuk string.
            // Kalau JSON nnti dinext pull kukasih contoh.
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                    params.put("params_1", param_1);
                    params.put("params_2", param_2);
                return params;
            }
        };
        // Disini kita tambahkan request kita ke-dalam antrian
        // Usahakan setiap request tag dibuat berbeda, jadi nanti kalau ada masalah di antrian kita tau yang mana mau dihentikan
        ServiceSingleton.getInstance(context).addRequestToQueue(request, ExampleForVolley.REQUEST_TAG);
    }
}