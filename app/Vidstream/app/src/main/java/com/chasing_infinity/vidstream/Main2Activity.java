package com.chasing_infinity.vidstream;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class Main2Activity extends AppCompatActivity {

    ListView vidname_list;
    final ArrayList<String> vid_name=new ArrayList<String>();
    RequestQueue mqueue;
    VideoView vidView ;
    MediaController vidControl;
    static String vidAddress="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vidView = (VideoView)findViewById(R.id.videoView);
        vidControl = new MediaController(this);
        vidname_list=(ListView) findViewById(R.id.vid_name_list);
        mqueue= Volley.newRequestQueue(this);

        /*
            handler is used to delay the list update so that it gives server time to load
         */

        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CustomAdapter list_adapter=new CustomAdapter();
                vidname_list.setAdapter(list_adapter);
            }
        },1000);

        jsonparse();
        /*
            jsonurl is called when a list item is clicked with its displayed text
         */

        vidname_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedfromlist= vid_name.get(i);
                jsonurl(selectedfromlist);

            }
        });



    }

    /*
        jsonurl is used to retrieve the image url which is situated in the server.
        url consists of api endpoint and the id of the selected video
     */

    public void jsonurl(String name){
        String url="http://192.168.43.17:8000/vidstream/get_vid"+"/"+name.charAt(0);
        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray=response.getJSONArray("url");
                        JSONObject vid = jsonArray.getJSONObject(0);
                        String vid_src=vid.getString("vid");
                        Log.i("url: ",vid_src);

                        vidAddress ="http://"+vid_src;
                        Log.i("url: ",vidAddress);
                        vidControl.setAnchorView(vidView);
                        vidView.setMediaController(vidControl);
                        vidView.setVideoPath(vidAddress);
                        vidView.start();



                    } catch (JSONException e) {
                    Log.i("error","er");

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mqueue.add(request);

    }

    /*
        jsonpase is used to get data from the api end point and filter the json object.
        consists of a success and error phase
     */

    public void jsonparse(){
        String url="http://192.168.43.17:8000/vidstream/get_vid_names";
        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.getJSONArray("vid");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject vid = jsonArray.getJSONObject(i);
                        String name = vid.getString("name");
                        int id = vid.getInt("id");
                        Log.i("json: ",name);
                        vid_name.add(id+". "+name);
                    }
                }catch (JSONException e) {
                    Log.i("error","er");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mqueue.add(request);



    }

    /*
        custom adaptor class used to change word font and add images to the (previews) on the side

     */

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return vid_name.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //setting the list view
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlist,null);
            ImageView im = (ImageView) view.findViewById(R.id.imageView_list);
            TextView txt = (TextView) view.findViewById(R.id.textView_list);
            //im.setImageResource();
            txt.setText(vid_name.get(i));
            txt.setTextColor(Color.parseColor("#ffffff"));

            return view;
        }
    }
}
